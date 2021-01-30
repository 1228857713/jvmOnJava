/*
 * Copyright (c) 2003, 2013, Oracle and/or its affiliates. All rights reserved.
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

package java.lang;

/** The CharacterData class encapsulates the large tables found in
    Java.lang.Character. */

class CharacterDataPrivateUse extends CharacterData {

    int getProperties(int ch) {
        return 0;
    }

    int getType(int ch) {
	return (ch & 0xFFFE) == 0xFFFE
	    ? Character.UNASSIGNED
	    : Character.PRIVATE_USE;
    }

    boolean isJavaIdentifierStart(int ch) {
		return false;
    }

    boolean isJavaIdentifierPart(int ch) {
		return false;
    }

    boolean isUnicodeIdentifierStart(int ch) {
		return false;
    }

    boolean isUnicodeIdentifierPart(int ch) {
		return false;
    }

    boolean isIdentifierIgnorable(int ch) {
		return false;
    }

    int toLowerCase(int ch) {
		return ch;
    }

    int toUpperCase(int ch) {
		return ch;
    }

    int toTitleCase(int ch) {
		return ch;
    }

    int digit(int ch, int radix) {
		return -1;
    }

    int getNumericValue(int ch) {
		return -1;
    }

    boolean isWhitespace(int ch) {
		return false;
    }

    byte getDirectionality(int ch) {
	return (ch & 0xFFFE) == 0xFFFE
	    ? Character.DIRECTIONALITY_UNDEFINED
	    : Character.DIRECTIONALITY_LEFT_TO_RIGHT;
    }

    boolean isMirrored(int ch) {
		return false;
    }

    static final CharacterData instance = new CharacterDataPrivateUse();
    private CharacterDataPrivateUse() {};
}

	
