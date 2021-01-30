/*
 * Copyright (c) 2001, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.imageio.plugins.jpeg;

import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.metadata.IIOInvalidTreeException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.w3c.dom.Node;

/**
 * A Comment marker segment.  Retains an array of bytes representing the
 * comment data as it is read from the stream.  If the marker segment is
 * constructed from a String, then local default encoding is assumed
 * when creating the byte array.  If the marker segment is created from
 * an <code>IIOMetadataNode</code>, the user object, if present is
 * assumed to be a byte array containing the comment data.  If there is
 * no user object then the comment attribute is used to create the
 * byte array, again assuming the default local encoding.
 */
class COMMarkerSegment extends MarkerSegment {
    private static final String ENCODING = "ISO-8859-1";

    /**
     * Constructs a marker segment from the given buffer, which contains
     * data from an <code>ImageInputStream</code>.  This is used when
     * reading metadata from a stream.
     */
    COMMarkerSegment(JPEGBuffer buffer) throws IOException {
        super(buffer);
        loadData(buffer);
    }

    /**
     * Constructs a marker segment from a String.  This is used when
     * modifying metadata from a non-native tree and when transcoding.
     * The default encoding is used to construct the byte array.
     */
    COMMarkerSegment(String comment) {
        super(JPEG.COM);
        data = comment.getBytes(); // Default encoding
    }

    /**
     * Constructs a marker segment from a native tree node.  If the node
     * is an <code>IIOMetadataNode</code> and contains a user object,
     * that object is used rather than the string attribute.  If the
     * string attribute is used, the default encoding is used.
     */
    COMMarkerSegment(Node node) throws IIOInvalidTreeException{
        super(JPEG.COM);
        if (node instanceof IIOMetadataNode) {
            IIOMetadataNode ourNode = (IIOMetadataNode) node;
            data = (byte []) ourNode.getUserObject();
        }
        if (data == null) {
            String comment =
                node.getAttributes().getNamedItem("comment").getNodeValue();
            if (comment != null) {
                data = comment.getBytes(); // Default encoding
            } else {
                throw new IIOInvalidTreeException("Empty comment node!", node);
            }
        }
    }

    /**
     * Returns the array encoded as a String, using ISO-Latin-1 encoding.
     * If an application needs another encoding, the data array must be
     * consulted directly.
     */
    String getComment() {
        try {
            return new String (data, ENCODING);
        } catch (UnsupportedEncodingException e) {}  // Won't happen
        return null;
    }

    /**
     * Returns an <code>IIOMetadataNode</code> containing the data array
     * as a user object and a string encoded using ISO-8895-1, as an
     * attribute.
     */
    IIOMetadataNode getNativeNode() {
        IIOMetadataNode node = new IIOMetadataNode("com");
        node.setAttribute("comment", getComment());
        if (data != null) {
            node.setUserObject(data.clone());
        }
        return node;
    }

    /**
     * Writes the data for this segment to the stream in
     * valid JPEG format, directly from the data array.
     */
    void write(ImageOutputStream ios) throws IOException {
        length = 2 + data.length;
        writeTag(ios);
        ios.write(data);
    }

    void print() {
        printTag("COM");
        System.out.println("<" + getComment() + ">");
    }
}
