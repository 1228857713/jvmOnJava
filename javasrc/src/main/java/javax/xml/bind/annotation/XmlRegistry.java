/*
 * Copyright (c) 2004, 2013, Oracle and/or its affiliates. All rights reserved.
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

package javax.xml.bind.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marks a class that has {@link XmlElementDecl}s.
 *
 * @author <ul><li>Kohsuke Kawaguchi, Sun Microsystems, Inc.</li><li>Sekhar Vajjhala, Sun Microsystems, Inc.</li></ul>
 * @since JAXB 2.0
 * @see XmlElementDecl
 */
@Retention(RUNTIME)
@Target({TYPE})
public @interface XmlRegistry {
}
