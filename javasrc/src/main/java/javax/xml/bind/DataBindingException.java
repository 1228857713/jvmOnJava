/*
 * Copyright (c) 2006, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package javax.xml.bind;

/**
 * Exception that represents a failure in a JAXB operation.
 *
 * <p>
 * This exception differs from {@link JAXBException} in that
 * this is an unchecked exception, while <tt>JAXBException</tt>
 * is a checked exception.
 *
 * @see JAXB
 * @since JAXB2.1
 */
public class DataBindingException extends RuntimeException {
    public DataBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBindingException(Throwable cause) {
        super(cause);
    }
}
