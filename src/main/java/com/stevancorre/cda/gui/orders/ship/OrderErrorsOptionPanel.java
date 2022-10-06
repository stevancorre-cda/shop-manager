package com.stevancorre.cda.gui.orders.ship;

import com.stevancorre.cda.shop.OrderErrors;

import javax.swing.*;

/**
 * Read only errors display for a single order
 */
public class OrderErrorsOptionPanel extends JPanel {
    public OrderErrorsOptionPanel(final OrderErrors errors) {
        add(new JScrollPane(makeTable(errors)));
    }

    private JTable makeTable(final OrderErrors errors) {
        final Object[][] rowData = errors.errors()
                .stream()
                .map(x -> new Object[]{
                        x.product().getName(),
                        x.quantity()
                })
                .toArray(Object[][]::new);
        final Object[] columnNames = {"Product", "Missing quantity"};

        return new JTable(rowData, columnNames) {{
            setDefaultEditor(Object.class, null);
            setAutoCreateRowSorter(true);
            setFillsViewportHeight(true);
        }};
    }

    /**
     * Open the form
     */
    public void prompt() {
        JOptionPane.showConfirmDialog(
                null,
                this,
                "Missing stocks",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }
}
