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

package com.sun.source.tree;

/**
 * Provides methods to convert between character positions and line numbers
 * for a compilation unit.
 *
 * @since 1.6
 */
@jdk.Exported
public interface LineMap {
    /**
     * Find the start position of a line.
     *
     * @param line line number (beginning at 1)
     * @return     position of first character in line
     * @throws  IndexOutOfBoundsException
     *           if {@code lineNumber < 1}
     *           if {@code lineNumber > no. of lines}
     */
    long getStartPosition(long line);

    /**
     * Find the position corresponding to a (line,column).
     *
     * @param   line    line number (beginning at 1)
     * @param   column  tab-expanded column number (beginning 1)
     *
     * @return  position of character
     * @throws  IndexOutOfBoundsException
     *           if {@code line < 1}
     *           if {@code line > no. of lines}
     */
    long getPosition(long line, long column);

    /**
     * Find the line containing a position; a line termination
     * character is on the line it terminates.
     *
     * @param   pos  character offset of the position
     * @return the line number of pos (first line is 1)
     */
    long getLineNumber(long pos);

    /**
     * Find the column for a character position.
     * Tab characters preceding the position on the same line
     * will be expanded when calculating the column number.
     *
     * @param  pos   character offset of the position
     * @return       the tab-expanded column number of pos (first column is 1)
     */
    long getColumnNumber(long pos);

}
