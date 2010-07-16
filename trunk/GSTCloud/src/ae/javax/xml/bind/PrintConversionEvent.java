/*
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package ae.javax.xml.bind;

import ae.javax.xml.bind.Marshaller;
import ae.javax.xml.bind.ValidationEvent;
import ae.javax.xml.bind.ValidationEventHandler;

/**
 * This event indicates that a problem was encountered while converting data
 * from the Java content tree into its lexical representation.
 * 
 * @author <ul><li>Ryan Shoemaker, Sun Microsystems, Inc.</li><li>Kohsuke Kawaguchi, Sun Microsystems, Inc.</li><li>Joe Fialli, Sun Microsystems, Inc.</li></ul> 
 * @version $Revision: 1.1 $
 * @see ValidationEvent
 * @see ValidationEventHandler
 * @see Marshaller
 * @since JAXB1.0
 */
public interface PrintConversionEvent extends ValidationEvent {

}
