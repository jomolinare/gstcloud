/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package ae.com.sun.xml.bind.v2.runtime.property;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


import ae.com.sun.xml.bind.api.AccessorException;
import ae.com.sun.xml.bind.v2.ClassFactory;
import ae.com.sun.xml.bind.v2.model.core.PropertyKind;
import ae.com.sun.xml.bind.v2.model.core.WildcardMode;
import ae.com.sun.xml.bind.v2.model.runtime.RuntimeElement;
import ae.com.sun.xml.bind.v2.model.runtime.RuntimeReferencePropertyInfo;
import ae.com.sun.xml.bind.v2.runtime.ElementBeanInfoImpl;
import ae.com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import ae.com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import ae.com.sun.xml.bind.v2.runtime.XMLSerializer;
import ae.com.sun.xml.bind.v2.runtime.reflect.Accessor;
import ae.com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import ae.com.sun.xml.bind.v2.runtime.unmarshaller.WildcardLoader;
import ae.com.sun.xml.bind.v2.util.QNameMap;
import ae.javax.xml.bind.JAXBElement;
import ae.javax.xml.bind.JAXBException;
import ae.javax.xml.bind.annotation.DomHandler;
import ae.javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

/**
 * @author Kohsuke Kawaguchi
 */
final class SingleReferenceNodeProperty<BeanT,ValueT> extends PropertyImpl<BeanT> {

    private final Accessor<BeanT,ValueT> acc;

    private final QNameMap<JaxBeanInfo> expectedElements = new QNameMap<JaxBeanInfo>();

    private final DomHandler domHandler;
    private final WildcardMode wcMode;

    public SingleReferenceNodeProperty(JAXBContextImpl context, RuntimeReferencePropertyInfo prop) {
        super(context,prop);
        acc = prop.getAccessor().optimize(context);

        for (RuntimeElement e : prop.getElements()) {
            expectedElements.put( e.getElementName(), context.getOrCreate(e) );
        }

        if(prop.getWildcard()!=null) {
            domHandler = (DomHandler) ClassFactory.create(prop.getDOMHandler());
            wcMode = prop.getWildcard();
        } else {
            domHandler = null;
            wcMode = null;
        }
    }

    public void reset(BeanT bean) throws AccessorException {
        acc.set(bean,null);
    }

    public String getIdValue(BeanT beanT) {
        return null;
    }

    public void serializeBody(BeanT o, XMLSerializer w, Object outerPeer) throws SAXException, AccessorException, IOException, XMLStreamException {
        ValueT v = acc.get(o);
        if(v!=null) {
            try {
                JaxBeanInfo bi = w.grammar.getBeanInfo(v,true);
                if(bi.jaxbType==Object.class && domHandler!=null)
                    // even if 'v' is a DOM node, it always derive from Object,
                    // so the getBeanInfo returns BeanInfo for Object
                    w.writeDom(v,domHandler,o,fieldName);
                else
                    bi.serializeRoot(v,w);
            } catch (JAXBException e) {
                w.reportError(fieldName,e);
                // recover by ignoring this property
            }
        }
    }

    public void buildChildElementUnmarshallers(UnmarshallerChain chain, QNameMap<ChildLoader> handlers) {
        for (QNameMap.Entry<JaxBeanInfo> n : expectedElements.entrySet())
            handlers.put(n.nsUri,n.localName, new ChildLoader(n.getValue().getLoader(chain.context,true),acc));

        if(domHandler!=null)
            handlers.put(CATCH_ALL,new ChildLoader(new WildcardLoader(domHandler,wcMode),acc));

    }

    public PropertyKind getKind() {
        return PropertyKind.REFERENCE;
    }

    @Override
    public Accessor getElementPropertyAccessor(String nsUri, String localName) {
        JaxBeanInfo bi = expectedElements.get(nsUri, localName);
        if(bi!=null) {
            if(bi instanceof ElementBeanInfoImpl) {
                final ElementBeanInfoImpl ebi = (ElementBeanInfoImpl) bi;
                // a JAXBElement. We need to handle JAXBElement for JAX-WS
                return new Accessor<BeanT,Object>(ebi.expectedType) {
                    public Object get(BeanT bean) throws AccessorException {
                        ValueT r = acc.get(bean);
                        if(r instanceof JAXBElement) {
                            return ((JAXBElement)r).getValue();
                        } else
                            // this is sloppy programming, but hey...
                            return r;
                    }

                    public void set(BeanT bean, Object value) throws AccessorException {
                        if(value!=null) {
                            try {
                                value = ebi.createInstanceFromValue(value);
                            } catch (IllegalAccessException e) {
                                throw new AccessorException(e);
                            } catch (InvocationTargetException e) {
                                throw new AccessorException(e);
                            } catch (InstantiationException e) {
                                throw new AccessorException(e);
                            }
                        }
                        acc.set(bean,(ValueT)value);
                    }
                };
            } else {
                // a custom element type, like @XmlRootElement class Foo { ... }
                return acc;
            }
        } else
            return null;
    }
}