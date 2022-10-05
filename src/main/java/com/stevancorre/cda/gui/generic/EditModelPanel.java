package com.stevancorre.cda.gui.generic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.stevancorre.cda.gui.GUIUtils.makeLabel;

public abstract class EditModelPanel<T> extends JPanel {
    private final ArrayList<ModelChangedListener> modelChangedListeners;
    private final ArrayList<ModelEditingDoneListener> editingDoneListeners;

    private T selection;

    protected EditModelPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(false);

        this.modelChangedListeners = new ArrayList<>();
        this.editingDoneListeners = new ArrayList<>();
    }

    protected abstract void updateView();

    public void select(final T selection) {
        this.selection = selection;

        updateView();

        setVisible(true);
    }

    public void addModelChangedListener(final ModelChangedListener listener) {
        this.modelChangedListeners.add(listener);
    }

    public void addModelEditingDoneListener(final ModelEditingDoneListener listener) {
        this.editingDoneListeners.add(listener);
    }

    protected T getSelection() {
        return selection;
    }

    protected void close() {
        clearSelection();

        for (final ModelEditingDoneListener listener : this.editingDoneListeners)
            listener.editingDone();
    }

    protected void clearSelection() {
        selection = null;

        setVisible(false);
    }

    protected void triggerModelChangedEvents() {
        for (final ModelChangedListener listener : this.modelChangedListeners)
            listener.modelChanged();
    }

    protected JTextField addReadonlyTextField(final String label) {
        final JTextField field = new JTextField() {{
            setMaximumSize(new Dimension(Short.MAX_VALUE, 26));
            setEnabled(false);
        }};

        add(makeLabel(label, l -> l.setLabelFor(field)));
        add(field);

        return field;
    }
}
