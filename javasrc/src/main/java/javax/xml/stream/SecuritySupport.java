/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
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

package javax.xml.stream;

import java.security.*;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * This class is duplicated for each JAXP subpackage so keep it in sync.
 * It is package private and therefore is not exposed as part of the JAXP
 * API.
 *
 * Security related methods that only work on J2SE 1.2 and newer.
 */
class SecuritySupport  {


    ClassLoader getContextClassLoader() throws SecurityException{
        return (ClassLoader)
                AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ClassLoader cl = null;
                //try {
                cl = Thread.currentThread().getContextClassLoader();
                //} catch (SecurityException ex) { }

                if (cl == null)
                    cl = ClassLoader.getSystemClassLoader();

                return cl;
            }
        });
    }

    String getSystemProperty(final String propName) {
        return (String)
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    return System.getProperty(propName);
                }
            });
    }

    FileInputStream getFileInputStream(final File file)
        throws FileNotFoundException
    {
        try {
            return (FileInputStream)
                AccessController.doPrivileged(new PrivilegedExceptionAction() {
                    public Object run() throws FileNotFoundException {
                        return new FileInputStream(file);
                    }
                });
        } catch (PrivilegedActionException e) {
            throw (FileNotFoundException)e.getException();
        }
    }

    InputStream getResourceAsStream(final ClassLoader cl,
                                           final String name)
    {
        return (InputStream)
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    InputStream ris;
                    if (cl == null) {
                        ris = Object.class.getResourceAsStream(name);
                    } else {
                        ris = cl.getResourceAsStream(name);
                    }
                    return ris;
                }
            });
    }

    boolean doesFileExist(final File f) {
    return ((Boolean)
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    return new Boolean(f.exists());
                }
            })).booleanValue();
    }

}
