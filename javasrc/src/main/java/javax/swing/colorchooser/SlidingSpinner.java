/*
 * Copyright (c) 2008, Oracle and/or its affiliates. All rights reserved.
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

package javax.swing.colorchooser;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

final class SlidingSpinner implements ChangeListener {

    private final ColorPanel panel;
    private final JComponent label;
    private final SpinnerNumberModel model = new SpinnerNumberModel();
    private final JSlider slider = new JSlider();
    private final JSpinner spinner = new JSpinner(this.model);
    private float value;
    private boolean internal;

    SlidingSpinner(ColorPanel panel, JComponent label) {
        this.panel = panel;
        this.label = label;
        this.slider.addChangeListener(this);
        this.spinner.addChangeListener(this);
        DefaultEditor editor = (DefaultEditor) this.spinner.getEditor();
        ValueFormatter.init(3, false, editor.getTextField());
        editor.setFocusable(false);
        this.spinner.setFocusable(false);
    }

    JComponent getLabel() {
        return this.label;
    }

    JSlider getSlider() {
        return this.slider;
    }

    JSpinner getSpinner() {
        return this.spinner;
    }

    float getValue() {
        return this.value;
    }

    void setValue(float value) {
        int min = this.slider.getMinimum();
        int max = this.slider.getMaximum();
        this.internal = true;
        this.slider.setValue(min + (int) (value * (float) (max - min)));
        this.spinner.setValue(Integer.valueOf(this.slider.getValue()));
        this.internal = false;
        this.value = value;
    }

    void setRange(int min, int max) {
        this.internal = true;
        this.slider.setMinimum(min);
        this.slider.setMaximum(max);
        this.model.setMinimum(Integer.valueOf(min));
        this.model.setMaximum(Integer.valueOf(max));
        this.internal = false;
    }

    void setVisible(boolean visible) {
        this.label.setVisible(visible);
        this.slider.setVisible(visible);
        this.spinner.setVisible(visible);
    }

    public void stateChanged(ChangeEvent event) {
        if (!this.internal) {
            if (this.spinner == event.getSource()) {
                Object value = this.spinner.getValue();
                if (value instanceof Integer) {
                    this.internal = true;
                    this.slider.setValue((Integer) value);
                    this.internal = false;
                }
            }
            int value = this.slider.getValue();
            this.internal = true;
            this.spinner.setValue(Integer.valueOf(value));
            this.internal = false;
            int min = this.slider.getMinimum();
            int max = this.slider.getMaximum();
            this.value = (float) (value - min) / (float) (max - min);
            this.panel.colorChanged();
        }
    }
}
