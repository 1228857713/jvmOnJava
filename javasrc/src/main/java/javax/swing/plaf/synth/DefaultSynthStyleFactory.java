/*
 * Copyright (c) 2003, 2008, Oracle and/or its affiliates. All rights reserved.
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
package javax.swing.plaf.synth;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.util.*;
import java.util.regex.*;
import sun.swing.plaf.synth.*;
import sun.swing.BakedArrayList;

/**
 * Factory used for obtaining styles. Supports associating a style based on
 * the name of the component as returned by <code>Component.getName()</code>,
 * and the <code>Region</code> associated with the <code>JComponent</code>.
 * Lookup is done using regular expressions.
 *
 * @author Scott Violet
 */
class DefaultSynthStyleFactory extends SynthStyleFactory {
    /**
     * Used to indicate the lookup should be done based on Component name.
     */
    public static final int NAME = 0;
    /**
     * Used to indicate the lookup should be done based on region.
     */
    public static final int REGION = 1;

    /**
     * List containing set of StyleAssociations used in determining matching
     * styles.
     */
    private List<StyleAssociation> _styles;
    /**
     * Used during lookup.
     */
    private BakedArrayList _tmpList;

    /**
     * Maps from a List (BakedArrayList to be precise) to the merged style.
     */
    private Map<BakedArrayList, SynthStyle> _resolvedStyles;

    /**
     * Used if there are no styles matching a widget.
     */
    private SynthStyle _defaultStyle;


    DefaultSynthStyleFactory() {
        _tmpList = new BakedArrayList(5);
        _styles = new ArrayList<StyleAssociation>();
        _resolvedStyles = new HashMap<BakedArrayList, SynthStyle>();
    }

    public synchronized void addStyle(DefaultSynthStyle style,
                         String path, int type) throws PatternSyntaxException {
        if (path == null) {
            // Make an empty path match all.
            path = ".*";
        }
        if (type == NAME) {
            _styles.add(StyleAssociation.createStyleAssociation(
                            path, style, type));
        }
        else if (type == REGION) {
            _styles.add(StyleAssociation.createStyleAssociation(
                            path.toLowerCase(), style, type));
        }
    }

    /**
     * Returns the style for the specified Component.
     *
     * @param c Component asking for
     * @param id ID of the Component
     */
    public synchronized SynthStyle getStyle(JComponent c, Region id) {
        BakedArrayList matches = _tmpList;

        matches.clear();
        getMatchingStyles(matches, c, id);

        if (matches.size() == 0) {
            return getDefaultStyle();
        }
        // Use a cached Style if possible, otherwise create a new one.
        matches.cacheHashCode();
        SynthStyle style = getCachedStyle(matches);

        if (style == null) {
            style = mergeStyles(matches);

            if (style != null) {
                cacheStyle(matches, style);
            }
        }
        return style;
    }

    /**
     * Returns the style to use if there are no matching styles.
     */
    private SynthStyle getDefaultStyle() {
        if (_defaultStyle == null) {
            _defaultStyle = new DefaultSynthStyle();
            ((DefaultSynthStyle)_defaultStyle).setFont(
                new FontUIResource(Font.DIALOG, Font.PLAIN,12));
        }
        return _defaultStyle;
    }

    /**
     * Fetches any styles that match the passed into arguments into
     * <code>matches</code>.
     */
    private void getMatchingStyles(List matches, JComponent c,
                                   Region id) {
        String idName = id.getLowerCaseName();
        String cName = c.getName();

        if (cName == null) {
            cName = "";
        }
        for (int counter = _styles.size() - 1; counter >= 0; counter--){
            StyleAssociation sa = _styles.get(counter);
            String path;

            if (sa.getID() == NAME) {
                path = cName;
            }
            else {
                path = idName;
            }

            if (sa.matches(path) && matches.indexOf(sa.getStyle()) == -1) {
                matches.add(sa.getStyle());
            }
        }
    }

    /**
     * Caches the specified style.
     */
    private void cacheStyle(List styles, SynthStyle style) {
        BakedArrayList cachedStyles = new BakedArrayList(styles);

        _resolvedStyles.put(cachedStyles, style);
    }

    /**
     * Returns the cached style from the passed in arguments.
     */
    private SynthStyle getCachedStyle(List styles) {
        if (styles.size() == 0) {
            return null;
        }
        return _resolvedStyles.get(styles);
    }

    /**
     * Creates a single Style from the passed in styles. The passed in List
     * is reverse sorted, that is the most recently added style found to
     * match will be first.
     */
    private SynthStyle mergeStyles(List styles) {
        int size = styles.size();

        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            return (SynthStyle)((DefaultSynthStyle)styles.get(0)).clone();
        }
        // NOTE: merging is done backwards as DefaultSynthStyleFactory reverses
        // order, that is, the most specific style is first.
        DefaultSynthStyle style = (DefaultSynthStyle)styles.get(size - 1);

        style = (DefaultSynthStyle)style.clone();
        for (int counter = size - 2; counter >= 0; counter--) {
            style = ((DefaultSynthStyle)styles.get(counter)).addTo(style);
        }
        return style;
    }
}
