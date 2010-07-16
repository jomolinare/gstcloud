/*
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package ae.javax.xml.bind.helpers;

import ae.javax.xml.bind.helpers.ValidationEventImpl;

import ae.javax.xml.bind.ValidationEventLocator;

/**
 * Default implementation of the NotIdentifiableEvent interface.
 * 
 * <p>
 * JAXB providers are allowed to use whatever class that implements
 * the ValidationEvent interface. This class is just provided for a
 * convenience.
 *
 * @author <ul><li>Ryan Shoemaker, Sun Microsystems, Inc.</li></ul> 
 * @version $Revision: 1.1 $
 * @see ae.javax.xml.bind.NotIdentifiableEvent
 * @see ae.javax.xml.bind.Validator
 * @see ae.javax.xml.bind.ValidationEventHandler
 * @see ae.javax.xml.bind.ValidationEvent
 * @see ae.javax.xml.bind.ValidationEventLocator
 * @since JAXB1.0
 */
public class NotIdentifiableEventImpl
    extends ValidationEventImpl
    implements ae.javax.xml.bind.NotIdentifiableEvent {

    /**
     * Create a new NotIdentifiableEventImpl.
     * 
     * @param _severity The severity value for this event.  Must be one of
     * ValidationEvent.WARNING, ValidationEvent.ERROR, or 
     * ValidationEvent.FATAL_ERROR
     * @param _message The text message for this event - may be null.
     * @param _locator The locator object for this event - may be null.
     * @throws IllegalArgumentException if an illegal severity field is supplied
     */
    public NotIdentifiableEventImpl( int _severity, String _message,
                                      ValidationEventLocator _locator) {
            
        super(_severity, _message, _locator);
    }

    /**
     * Create a new NotIdentifiableEventImpl.
     * 
     * @param _severity The severity value for this event.  Must be one of
     * ValidationEvent.WARNING, ValidationEvent.ERROR, or 
     * ValidationEvent.FATAL_ERROR
     * @param _message The text message for this event - may be null.
     * @param _locator The locator object for this event - may be null.
     * @param _linkedException An optional linked exception that may provide
     * additional information about the event - may be null.
     * @throws IllegalArgumentException if an illegal severity field is supplied
     */
    public NotIdentifiableEventImpl( int _severity, String _message,
                                      ValidationEventLocator _locator,
                                      Throwable _linkedException) {
            
        super(_severity, _message, _locator, _linkedException);
    }

}
