package com.stevancorre.cda.gui.generic;

import com.stevancorre.cda.shop.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class ModelsTablePanel<T> extends JSplitPane {
    private final ArrayList<T> data;
    private final String[] columns;

    private final EditModelPanel<T> editPanel;
    private final JTable table;
    private final DefaultTableModel model;

    protected ModelsTablePanel(
            final ArrayList<T> data,
            final String[] columns,
            final EditModelPanel<T> editPanel) {
        super(JSplitPane.HORIZONTAL_SPLIT);

        setDividerSize(0);

        this.data = data;
        this.columns = columns;
        this.model = new DefaultTableModel(0, 0);

        this.table = makeTable();
        this.editPanel = editPanel;
        this.editPanel.addModelChangedListener(this::updateData);
        this.editPanel.addModelEditingDoneListener(this::onEditingDone);

        add(makeTableScrollPanel(this.table));
        add(this.editPanel);
    }

    public void updateData() {
        model.setDataVector(
                data.stream()
                        .map(this::extractRow)
                        .toArray(Object[][]::new),
                columns
        );
    }

    protected abstract Object[] extractRow(final T object);

    private void onEditingDone() {
        setDividerSize(0);
        table.getSelectionModel().clearSelection();
    }

    private JTable makeTable() {
        return new JTable(model) {{
            updateData();

            setDefaultEditor(Object.class, null);
            setAutoCreateRowSorter(true);
            setFillsViewportHeight(true);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent event) {
                    final int index = table.rowAtPoint(event.getPoint());
                    handleSelectOrder(index);
                }
            });
        }};
    }

    private JScrollPane makeTableScrollPanel(final JTable table) {
        return new JScrollPane(table) {
            @Override
            public Dimension getMinimumSize() {
                int parentWidth = getParent().getSize().width;
                Dimension d = getSize();
                d.width = parentWidth - 370;

                return d;
            }
        };
    }

    private void handleSelectOrder(final int index) {
        if (index == -1) return;

        final T order = data.get(index);

        if (!editPanel.isVisible()) {
            setDividerSize(10);
            setDividerLocation(getParent().getSize().width - 276);
        }

        editPanel.select(order);
    }
}
