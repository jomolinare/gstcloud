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

package ae.com.sun.xml.bind.v2.runtime.unmarshaller;

import java.lang.reflect.Constructor;


import ae.com.sun.xml.bind.WhiteSpaceProcessor;
import ae.javax.xml.stream.Location;
import ae.javax.xml.stream.XMLStreamConstants;
import ae.javax.xml.stream.XMLStreamException;
import ae.javax.xml.stream.XMLStreamReader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Reads XML from StAX {@link XMLStreamReader} and
 * feeds events to {@link XmlVisitor}.
 * <p>
 * TODO:
 * Finding the optimized FI implementations is a bit hacky and not very
 * extensible. Can we use the service provider mechnism in general for 
 * concrete implementations of StAXConnector.
 *
 * @author Ryan.Shoemaker@Sun.COM
 * @author Kohsuke Kawaguchi
 * @version JAXB 2.0
 */
class StAXStreamConnector extends StAXConnector {

    /**
     * Creates a {@link StAXConnector} from {@link XMLStreamReader}.
     *
     * This method checks if the parser is FI parser and acts accordingly.
     */
    public static StAXConnector create(XMLStreamReader reader, XmlVisitor visitor) {
        // try optimized codepath
        final Class readerClass = reader.getClass();
        if (FI_STAX_READER_CLASS != null && FI_STAX_READER_CLASS.isAssignableFrom(readerClass) && FI_CONNECTOR_CTOR!=null) {
            try {
                return FI_CONNECTOR_CTOR.newInstance(reader,visitor);
            } catch (Exception t) {
            }
        }

        // Quick hack until SJSXP fixes 6270116
        boolean isZephyr = readerClass.getName().equals("com.sun.xml.stream.XMLReaderImpl");
        if (getBoolProp(reader,"org.codehaus.stax2.internNames") &&
            getBoolProp(reader,"org.codehaus.stax2.internNsUris"))
            ; // no need for interning
        else
        if (isZephyr)
            ; // no need for interning
        else
        if (checkImplementaionNameOfSjsxp(reader))
            ; // no need for interning.
        else
            visitor = new InterningXmlVisitor(visitor);

        if (STAX_EX_READER_CLASS!=null && STAX_EX_READER_CLASS.isAssignableFrom(readerClass)) {
            try {
                return STAX_EX_CONNECTOR_CTOR.newInstance(reader,visitor);
            } catch (Exception t) {
            }
        }

        return new StAXStreamConnector(reader,visitor);
    }

    private static boolean checkImplementaionNameOfSjsxp(XMLStreamReader reader) {
        try {
            Object name = reader.getProperty("http://java.sun.com/xml/stream/properties/implementation-name");
            return name!=null && name.equals("sjsxp");
        } catch (Exception e) {
            // be defensive against broken StAX parsers since javadoc is not clear
            // about when an error happens
            return false;
        }
    }

    private static boolean getBoolProp(XMLStreamReader r, String n) {
        try {
            Object o = r.getProperty(n);
            if(o instanceof Boolean)    return (Boolean)o;
            return false;
        } catch (Exception e) {
            // be defensive against broken StAX parsers since javadoc is not clear
            // about when an error happens
            return false;
        }
    }


    // StAX event source
    private final XMLStreamReader staxStreamReader;

    /**
     * SAX may fire consective characters event, but we don't allow it.
     * so use this buffer to perform buffering.
     */
    protected final StringBuilder buffer = new StringBuilder();

    /**
     * Set to true if the text() event is reported, and therefore
     * the following text() event should be suppressed.
     */
    protected boolean textReported = false;

    protected StAXStreamConnector(XMLStreamReader staxStreamReader, XmlVisitor visitor) {
        super(visitor);
        this.staxStreamReader = staxStreamReader;
    }

    public void bridge() throws XMLStreamException {

        try {
            // remembers the nest level of elements to know when we are done.
            int depth=0;

            // if the parser is at the start tag, proceed to the first element
            int event = staxStreamReader.getEventType();
            if(event == XMLStreamConstants.START_DOCUMENT) {
                // nextTag doesn't correctly handle DTDs
                while( !staxStreamReader.isStartElement() )
                    event = staxStreamReader.next();
            }


            if( event!=XMLStreamConstants.START_ELEMENT)
                throw new IllegalStateException("The current event is not START_ELEMENT\n but " + event);

            handleStartDocument(staxStreamReader.getNamespaceContext());

            OUTER:
            while(true) {
                // These are all of the events listed in the javadoc for
                // XMLEvent.
                // The spec only really describes 11 of them.
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT :
                        handleStartElement();
                        depth++;
                        break;
                    case XMLStreamConstants.END_ELEMENT :
                        depth--;
                        handleEndElement();
                        if(depth==0)    break OUTER;
                        break;
                    case XMLStreamConstants.CHARACTERS :
                    case XMLStreamConstants.CDATA :
                    case XMLStreamConstants.SPACE :
                        handleCharacters();
                        break;
                    // otherwise simply ignore
                }

                event=staxStreamReader.next();
            }

            staxStreamReader.next();    // move beyond the end tag.

            handleEndDocument();
        } catch (SAXException e) {
            throw new XMLStreamException(e);
        }
    }

    protected Location getCurrentLocation() {
        return staxStreamReader.getLocation();
    }

    protected String getCurrentQName() {
        return getQName(staxStreamReader.getPrefix(),staxStreamReader.getLocalName());
    }

    private void handleEndElement() throws SAXException {
        processText(false);

        // fire endElement
        tagName.uri = fixNull(staxStreamReader.getNamespaceURI());
        tagName.local = staxStreamReader.getLocalName();
        visitor.endElement(tagName);

        // end namespace bindings
        int nsCount = staxStreamReader.getNamespaceCount();
        for (int i = nsCount - 1; i >= 0; i--) {
            visitor.endPrefixMapping(fixNull(staxStreamReader.getNamespacePrefix(i)));
        }
    }

    private void handleStartElement() throws SAXException {
        processText(true);

        // start namespace bindings
        int nsCount = staxStreamReader.getNamespaceCount();
        for (int i = 0; i < nsCount; i++) {
            visitor.startPrefixMapping(
                fixNull(staxStreamReader.getNamespacePrefix(i)),
                fixNull(staxStreamReader.getNamespaceURI(i)));
        }

        // fire startElement
        tagName.uri = fixNull(staxStreamReader.getNamespaceURI());
        tagName.local = staxStreamReader.getLocalName();
        tagName.atts = attributes;

        visitor.startElement(tagName);
    }

    /**
     * Proxy of {@link Attributes} that read from {@link XMLStreamReader}.
     */
    private final Attributes attributes = new Attributes() {
        public int getLength() {
            return staxStreamReader.getAttributeCount();
        }

        public String getURI(int index) {
            String uri = staxStreamReader.getAttributeNamespace(index);
            if(uri==null)   return "";
            return uri;
        }

        public String getLocalName(int index) {
            return staxStreamReader.getAttributeLocalName(index);
        }

        public String getQName(int index) {
            String prefix = staxStreamReader.getAttributePrefix(index);
            if(prefix==null || prefix.length()==0)
                return getLocalName(index);
            else
                return prefix + ':' + getLocalName(index);
        }

        public String getType(int index) {
            return staxStreamReader.getAttributeType(index);
        }

        public String getValue(int index) {
            return staxStreamReader.getAttributeValue(index);
        }

        public int getIndex(String uri, String localName) {
            for( int i=getLength()-1; i>=0; i-- )
                if( localName.equals(getLocalName(i)) && uri.equals(getURI(i)))
                    return i;
            return -1;
        }

        // this method sholdn't be used that often (if at all)
        // so it's OK to be slow.
        public int getIndex(String qName) {
            for( int i=getLength()-1; i>=0; i-- ) {
                if(qName.equals(getQName(i)))
                    return i;
            }
            return -1;
        }

        public String getType(String uri, String localName) {
            int index = getIndex(uri,localName);
            if(index<0)     return null;
            return getType(index);
        }

        public String getType(String qName) {
            int index = getIndex(qName);
            if(index<0)     return null;
            return getType(index);
        }

        public String getValue(String uri, String localName) {
            int index = getIndex(uri,localName);
            if(index<0)     return null;
            return getValue(index);
        }

        public String getValue(String qName) {
            int index = getIndex(qName);
            if(index<0)     return null;
            return getValue(index);
        }
    };

    protected void handleCharacters() throws XMLStreamException, SAXException {
        if( predictor.expectText() )
            buffer.append(
                staxStreamReader.getTextCharacters(),
                staxStreamReader.getTextStart(),
                staxStreamReader.getTextLength() );
    }

    private void processText( boolean ignorable ) throws SAXException {
        if( predictor.expectText() && (!ignorable || !WhiteSpaceProcessor.isWhiteSpace(buffer))) {
            if(textReported) {
                textReported = false;
            } else {
                visitor.text(buffer);
            }
        }
        buffer.setLength(0);
    }



    /**
     * Reference to FI's StAXReader class, if FI can be loaded.
     */
    private static final Class FI_STAX_READER_CLASS = initFIStAXReaderClass();
    private static final Constructor<? extends StAXConnector> FI_CONNECTOR_CTOR = initFastInfosetConnectorClass();

    private static Class initFIStAXReaderClass() {
        try {
            Class fisr = UnmarshallerImpl.class.getClassLoader().
                    loadClass("org.jvnet.fastinfoset.stax.FastInfosetStreamReader");
            Class sdp = UnmarshallerImpl.class.getClassLoader().
                    loadClass("com.sun.xml.fastinfoset.stax.StAXDocumentParser");
            // Check if StAXDocumentParser implements FastInfosetStreamReader
            if (fisr.isAssignableFrom(sdp))
                return sdp;
            else
                return null;
        } catch (Throwable e) {
            return null;
        }
    }

    private static Constructor<? extends StAXConnector> initFastInfosetConnectorClass() {
        try {
            if (FI_STAX_READER_CLASS == null)
                return null;
            
            Class c = UnmarshallerImpl.class.getClassLoader().loadClass(
                    "ae.com.sun.xml.bind.v2.runtime.unmarshaller.FastInfosetConnector");                
            return c.getConstructor(FI_STAX_READER_CLASS,XmlVisitor.class);
        } catch (Throwable e) {
            return null;
        }
    }

    //
    // reference to StAXEx classes
    //
    private static final Class STAX_EX_READER_CLASS = initStAXExReader();
    private static final Constructor<? extends StAXConnector> STAX_EX_CONNECTOR_CTOR = initStAXExConnector();

    private static Class initStAXExReader() {
        try {
            return UnmarshallerImpl.class.getClassLoader().loadClass("org.jvnet.staxex.XMLStreamReaderEx");
        } catch (Throwable e) {
            return null;
        }
    }

    private static Constructor<? extends StAXConnector> initStAXExConnector() {
        try {
            Class c = UnmarshallerImpl.class.getClassLoader().loadClass("ae.com.sun.xml.bind.v2.runtime.unmarshaller.StAXExConnector");
            return c.getConstructor(STAX_EX_READER_CLASS,XmlVisitor.class);
        } catch (Throwable e) {
            return null;
        }
    }
}
