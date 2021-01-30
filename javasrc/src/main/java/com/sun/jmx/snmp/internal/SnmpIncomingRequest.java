/*
 * Copyright (c) 2001, 2003, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.jmx.snmp.internal;

import java.net.InetAddress;

import com.sun.jmx.snmp.SnmpSecurityParameters;
import com.sun.jmx.snmp.SnmpTooBigException;
import com.sun.jmx.snmp.SnmpStatusException;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpMsg;

import com.sun.jmx.snmp.SnmpUnknownSecModelException;
import com.sun.jmx.snmp.SnmpBadSecurityLevelException;

/**
<P> An <CODE>SnmpIncomingRequest</CODE> handles both sides of an incoming SNMP request:
<ul>
<li> The request. Unmarshalling of the received message. </li>
<li> The response. Marshalling of the message to send. </li>
</ul>
 * <p><b>This API is a Sun Microsystems internal API  and is subject
 * to change without notice.</b></p>
 * @since 1.5
 */
public interface SnmpIncomingRequest {
    /**
     * Once the incoming request decoded, returns the decoded security parameters.
     * @return The decoded security parameters.
     */
    public SnmpSecurityParameters getSecurityParameters();
     /**
     * Tests if a report is expected.
     * @return boolean indicating if a report is to be sent.
     */
    public boolean isReport();
    /**
     * Tests if a response is expected.
     * @return boolean indicating if a response is to be sent.
     */
    public boolean isResponse();

    /**
     * Tells this request that no response will be sent.
     */
    public void noResponse();
    /**
     * Gets the incoming request principal.
     * @return The request principal.
     **/
    public String getPrincipal();
    /**
     * Gets the incoming request security level. This level is defined in {@link com.sun.jmx.snmp.SnmpEngine SnmpEngine}.
     * @return The security level.
     */
    public int getSecurityLevel();
    /**
     * Gets the incoming request security model.
     * @return The security model.
     */
    public int getSecurityModel();
    /**
     * Gets the incoming request context name.
     * @return The context name.
     */
    public byte[] getContextName();
    /**
     * Gets the incoming request context engine Id.
     * @return The context engine Id.
     */
    public byte[] getContextEngineId();
    /**
     * Gets the incoming request context name used by Access Control Model in order to allow or deny the access to OIDs.
     */
    public byte[] getAccessContext();
    /**
     * Encodes the response message to send and puts the result in the specified byte array.
     *
     * @param outputBytes An array to receive the resulting encoding.
     *
     * @exception ArrayIndexOutOfBoundsException If the result does not fit
     *                                           into the specified array.
     */
    public int encodeMessage(byte[] outputBytes)
        throws SnmpTooBigException;

    /**
     * Decodes the specified bytes and initializes the request with the incoming message.
     *
     * @param inputBytes The bytes to be decoded.
     *
     * @exception SnmpStatusException If the specified bytes are not a valid encoding or if the security applied to this request failed and no report is to be sent (typically trap PDU).
     */
    public void decodeMessage(byte[] inputBytes,
                              int byteCount,
                              InetAddress address,
                              int port)
        throws SnmpStatusException, SnmpUnknownSecModelException,
               SnmpBadSecurityLevelException;

     /**
     * Initializes the response to send with the passed Pdu.
     * <P>
     * If the encoding length exceeds <CODE>maxDataLength</CODE>,
     * the method throws an exception.
     *
     * @param p The PDU to be encoded.
     * @param maxDataLength The maximum length permitted for the data field.
     *
     * @exception SnmpStatusException If the specified <CODE>pdu</CODE>
     *     is not valid.
     * @exception SnmpTooBigException If the resulting encoding does not fit
     * into <CODE>maxDataLength</CODE> bytes.
     * @exception ArrayIndexOutOfBoundsException If the encoding exceeds
     *   <CODE>maxDataLength</CODE>.
     */
    public SnmpMsg encodeSnmpPdu(SnmpPdu p,
                                 int maxDataLength)
        throws SnmpStatusException, SnmpTooBigException;

    /**
     * Gets the request PDU encoded in the received message.
     * <P>
     * This method decodes the data field and returns the resulting PDU.
     *
     * @return The resulting PDU.
     * @exception SnmpStatusException If the encoding is not valid.
     */
    public SnmpPdu decodeSnmpPdu()
        throws SnmpStatusException;

    /**
     * Returns a stringified form of the received message.
     * @return The message state string.
     */
    public String printRequestMessage();
    /**
     * Returns a stringified form of the message to send.
     * @return The message state string.
     */
    public String printResponseMessage();
}
