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
package javax.swing;

import java.awt.FocusTraversalPolicy;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.util.HashMap;
import java.util.HashSet;
import java.io.*;


/**
 * A FocusTraversalPolicy which provides support for legacy applications which
 * handle focus traversal via JComponent.setNextFocusableComponent or by
 * installing a custom DefaultFocusManager. If a specific traversal has not
 * been hard coded, then that traversal is provided either by the custom
 * DefaultFocusManager, or by a wrapped FocusTraversalPolicy instance.
 *
 * @author David Mendenhall
 */
final class LegacyGlueFocusTraversalPolicy extends FocusTraversalPolicy
    implements Serializable
{
    private transient FocusTraversalPolicy delegatePolicy;
    private transient DefaultFocusManager delegateManager;

    private HashMap<Component, Component> forwardMap = new HashMap<Component, Component>(),
        backwardMap = new HashMap<Component, Component>();

    LegacyGlueFocusTraversalPolicy(FocusTraversalPolicy delegatePolicy) {
        this.delegatePolicy = delegatePolicy;
    }
    LegacyGlueFocusTraversalPolicy(DefaultFocusManager delegateManager) {
        this.delegateManager = delegateManager;
    }

    void setNextFocusableComponent(Component left, Component right) {
        forwardMap.put(left, right);
        backwardMap.put(right, left);
    }
    void unsetNextFocusableComponent(Component left, Component right) {
        forwardMap.remove(left);
        backwardMap.remove(right);
    }

    public Component getComponentAfter(Container focusCycleRoot,
                                       Component aComponent) {
        Component hardCoded = aComponent, prevHardCoded;
        HashSet<Component> sanity = new HashSet<Component>();

        do {
            prevHardCoded = hardCoded;
            hardCoded = forwardMap.get(hardCoded);
            if (hardCoded == null) {
                if (delegatePolicy != null &&
                    prevHardCoded.isFocusCycleRoot(focusCycleRoot)) {
                    return delegatePolicy.getComponentAfter(focusCycleRoot,
                                                            prevHardCoded);
                } else if (delegateManager != null) {
                    return delegateManager.
                        getComponentAfter(focusCycleRoot, aComponent);
                } else {
                    return null;
                }
            }
            if (sanity.contains(hardCoded)) {
                // cycle detected; bail
                return null;
            }
            sanity.add(hardCoded);
        } while (!accept(hardCoded));

        return hardCoded;
    }
    public Component getComponentBefore(Container focusCycleRoot,
                                        Component aComponent) {
        Component hardCoded = aComponent, prevHardCoded;
        HashSet<Component> sanity = new HashSet<Component>();

        do {
            prevHardCoded = hardCoded;
            hardCoded = backwardMap.get(hardCoded);
            if (hardCoded == null) {
                if (delegatePolicy != null &&
                    prevHardCoded.isFocusCycleRoot(focusCycleRoot)) {
                    return delegatePolicy.getComponentBefore(focusCycleRoot,
                                                       prevHardCoded);
                } else if (delegateManager != null) {
                    return delegateManager.
                        getComponentBefore(focusCycleRoot, aComponent);
                } else {
                    return null;
                }
            }
            if (sanity.contains(hardCoded)) {
                // cycle detected; bail
                return null;
            }
            sanity.add(hardCoded);
        } while (!accept(hardCoded));

        return hardCoded;
    }
    public Component getFirstComponent(Container focusCycleRoot) {
        if (delegatePolicy != null) {
            return delegatePolicy.getFirstComponent(focusCycleRoot);
        } else if (delegateManager != null) {
            return delegateManager.getFirstComponent(focusCycleRoot);
        } else {
            return null;
        }
    }
    public Component getLastComponent(Container focusCycleRoot) {
        if (delegatePolicy != null) {
            return delegatePolicy.getLastComponent(focusCycleRoot);
        } else if (delegateManager != null) {
            return delegateManager.getLastComponent(focusCycleRoot);
        } else {
            return null;
        }
    }
    public Component getDefaultComponent(Container focusCycleRoot) {
        if (delegatePolicy != null) {
            return delegatePolicy.getDefaultComponent(focusCycleRoot);
        } else {
            return getFirstComponent(focusCycleRoot);
        }
    }
    private boolean accept(Component aComponent) {
        if (!(aComponent.isVisible() && aComponent.isDisplayable() &&
              aComponent.isFocusable() && aComponent.isEnabled())) {
            return false;
        }

        // Verify that the Component is recursively enabled. Disabling a
        // heavyweight Container disables its children, whereas disabling
        // a lightweight Container does not.
        if (!(aComponent instanceof Window)) {
            for (Container enableTest = aComponent.getParent();
                 enableTest != null;
                 enableTest = enableTest.getParent())
            {
                if (!(enableTest.isEnabled() || enableTest.isLightweight())) {
                    return false;
                }
                if (enableTest instanceof Window) {
                    break;
                }
            }
        }

        return true;
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        if (delegatePolicy instanceof Serializable) {
            out.writeObject(delegatePolicy);
        } else {
            out.writeObject(null);
        }

        if (delegateManager instanceof Serializable) {
            out.writeObject(delegateManager);
        } else {
            out.writeObject(null);
        }
    }
    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        delegatePolicy = (FocusTraversalPolicy)in.readObject();
        delegateManager = (DefaultFocusManager)in.readObject();
    }
}
