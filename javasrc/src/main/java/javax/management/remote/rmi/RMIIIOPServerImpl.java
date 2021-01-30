/*
 * Copyright (c) 2003, 2007, Oracle and/or its affiliates. All rights reserved.
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

package javax.management.remote.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import java.util.Collections;
import javax.security.auth.Subject;

import com.sun.jmx.remote.internal.IIOPHelper;

/**
 * <p>An {@link RMIServerImpl} that is exported through IIOP and that
 * creates client connections as RMI objects exported through IIOP.
 * User code does not usually reference this class directly.</p>
 *
 * @see RMIServerImpl
 *
 * @since 1.5
 */
public class RMIIIOPServerImpl extends RMIServerImpl {
    /**
     * <p>Creates a new {@link RMIServerImpl}.</p>
     *
     * @param env the environment containing attributes for the new
     * <code>RMIServerImpl</code>.  Can be null, which is equivalent
     * to an empty Map.
     *
     * @exception IOException if the RMI object cannot be created.
     */
    public RMIIIOPServerImpl(Map<String,?> env)
            throws IOException {
        super(env);

        this.env = (env == null) ? Collections.<String, Object>emptyMap() : env;

        callerACC = AccessController.getContext();
    }

    protected void export() throws IOException {
        IIOPHelper.exportObject(this);
    }

    protected String getProtocol() {
        return "iiop";
    }

    /**
     * <p>Returns an IIOP stub.</p>
     * The stub might not yet be connected to the ORB. The stub will
     * be serializable only if it is connected to the ORB.
     * @return an IIOP stub.
     * @exception IOException if the stub cannot be created - e.g the
     *            RMIIIOPServerImpl has not been exported yet.
     **/
    public Remote toStub() throws IOException {
        // javax.rmi.CORBA.Stub stub =
        //    (javax.rmi.CORBA.Stub) PortableRemoteObject.toStub(this);
        final Remote stub = IIOPHelper.toStub(this);
        // java.lang.System.out.println("NON CONNECTED STUB " + stub);
        // org.omg.CORBA.ORB orb =
        //    org.omg.CORBA.ORB.init((String[])null, (Properties)null);
        // stub.connect(orb);
        // java.lang.System.out.println("CONNECTED STUB " + stub);
        return stub;
    }

    /**
     * <p>Creates a new client connection as an RMI object exported
     * through IIOP.
     *
     * @param connectionId the ID of the new connection.  Every
     * connection opened by this connector server will have a
     * different ID.  The behavior is unspecified if this parameter is
     * null.
     *
     * @param subject the authenticated subject.  Can be null.
     *
     * @return the newly-created <code>RMIConnection</code>.
     *
     * @exception IOException if the new client object cannot be
     * created or exported.
     */
    protected RMIConnection makeClient(String connectionId, Subject subject)
            throws IOException {

        if (connectionId == null)
            throw new NullPointerException("Null connectionId");

        RMIConnection client =
            new RMIConnectionImpl(this, connectionId, getDefaultClassLoader(),
                                  subject, env);
        IIOPHelper.exportObject(client);
        return client;
    }

    protected void closeClient(RMIConnection client) throws IOException {
        IIOPHelper.unexportObject(client);
    }

    /**
     * <p>Called by {@link #close()} to close the connector server by
     * unexporting this object.  After returning from this method, the
     * connector server must not accept any new connections.</p>
     *
     * @exception IOException if the attempt to close the connector
     * server failed.
     */
    protected void closeServer() throws IOException {
        IIOPHelper.unexportObject(this);
    }

    @Override
    RMIConnection doNewClient(final Object credentials) throws IOException {
        if (callerACC == null) {
            throw new SecurityException("AccessControlContext cannot be null");
        }
        try {
            return AccessController.doPrivileged(
                new PrivilegedExceptionAction<RMIConnection>() {
                    public RMIConnection run() throws IOException {
                        return superDoNewClient(credentials);
                    }
            }, callerACC);
        } catch (PrivilegedActionException pae) {
            throw (IOException) pae.getCause();
        }
    }

    RMIConnection superDoNewClient(Object credentials) throws IOException {
        return super.doNewClient(credentials);
    }

    private final Map<String, ?> env;
    private final AccessControlContext callerACC;
}
