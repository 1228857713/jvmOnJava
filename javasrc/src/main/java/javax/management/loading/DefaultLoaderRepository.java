/*
 * Copyright (c) 2000, 2008, Oracle and/or its affiliates. All rights reserved.
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

package javax.management.loading;

import static com.sun.jmx.defaults.JmxProperties.MBEANSERVER_LOGGER;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;

/**
 * <p>Keeps the list of Class Loaders registered in the MBean Server.
 * It provides the necessary methods to load classes using the registered
 * Class Loaders.</p>
 *
 * <p>This deprecated class is maintained for compatibility.  In
 * previous versions of JMX, there was one
 * <code>DefaultLoaderRepository</code> shared by all MBean servers.
 * As of JMX 1.2, that functionality is approximated by using {@link
 * MBeanServerFactory#findMBeanServer} to find all known MBean
 * servers, and consulting the {@link ClassLoaderRepository} of each
 * one.  It is strongly recommended that code referencing
 * <code>DefaultLoaderRepository</code> be rewritten.</p>
 *
 * @deprecated Use
 * {@link javax.management.MBeanServer#getClassLoaderRepository()}}
 * instead.
 *
 * @since 1.5
 */
@Deprecated
public class DefaultLoaderRepository {

    /**
     * Go through the list of class loaders and try to load the requested
     * class.
     * The method will stop as soon as the class is found. If the class
     * is not found the method will throw a <CODE>ClassNotFoundException</CODE>
     * exception.
     *
     * @param className The name of the class to be loaded.
     *
     * @return the loaded class.
     *
     * @exception ClassNotFoundException The specified class could not be
     *            found.
     */
    public static Class<?> loadClass(String className)
        throws ClassNotFoundException {
        MBEANSERVER_LOGGER.logp(Level.FINEST,
                DefaultLoaderRepository.class.getName(),
                "loadClass", className);
        return load(null, className);
    }

    /**
     * Go through the list of class loaders but exclude the given
     * class loader, then try to load
     * the requested class.
     * The method will stop as soon as the class is found. If the class
     * is not found the method will throw a <CODE>ClassNotFoundException</CODE>
     * exception.
     *
     * @param className The name of the class to be loaded.
     * @param loader The class loader to be excluded.
     *
     * @return the loaded class.
     *
     * @exception ClassNotFoundException The specified class could not be
     *    found.
     */
    public static Class<?> loadClassWithout(ClassLoader loader,
                                         String className)
        throws ClassNotFoundException {
        MBEANSERVER_LOGGER.logp(Level.FINEST,
                DefaultLoaderRepository.class.getName(),
                "loadClassWithout", className);
        return load(loader, className);
    }

    private static Class<?> load(ClassLoader without, String className)
            throws ClassNotFoundException {
        final List<MBeanServer> mbsList = MBeanServerFactory.findMBeanServer(null);

        for (MBeanServer mbs : mbsList) {
            ClassLoaderRepository clr = mbs.getClassLoaderRepository();
            try {
                return clr.loadClassWithout(without, className);
            } catch (ClassNotFoundException e) {
                // OK : Try with next one...
            }
        }
        throw new ClassNotFoundException(className);
    }

 }
