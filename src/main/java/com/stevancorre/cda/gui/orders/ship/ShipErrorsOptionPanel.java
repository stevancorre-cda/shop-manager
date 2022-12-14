package com.stevancorre.cda.gui.orders.ship;

import com.stevancorre.cda.shop.OrderErrors;
import com.stevancorre.cda.shop.ShipError;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static com.stevancorre.cda.gui.GUIUtils.makeLabel;
import static com.stevancorre.cda.gui.GUIUtils.makeVerticalSpace;

/**
 * Popup thrown when you don't have enough stocks to ship your orders. Display errors for multiple orders
 */
public class ShipErrorsOptionPanel extends JPanel {
    private final JTable table;

    public ShipErrorsOptionPanel(final int ordersCount, final double total, final List<OrderErrors> errors) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        makeVerticalSpace();

        add(makeLabel(String.format(
                "%d %s shipped, for a total of %.2f EUR",
                ordersCount,
                ordersCount == 1 ? "order was" : "orders were",
                total)));

        this.table = makeTable(errors);
        add(new JScrollPane(this.table));
    }

    private JTable makeTable(final List<OrderErrors> errors) {
        final Object[][] rowData = errors
                .stream()
                .map(x -> new Object[]{
                        x.order().getFormattedDate(),
                        x.order().getCustomer().toString(),
                        x.errors()
                                .stream()
                                .mapToInt(ShipError::quantity)
                                .sum()
                })
                .toArray(Object[][]::new);
        final Object[] columnNames = {"Order date", "Customer", "Missing quantity"};

        return new JTable(rowData, columnNames) {{
            setDefaultEditor(Object.class, null);
            setAutoCreateRowSorter(true);
            setFillsViewportHeight(true);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent event) {
                    if (event.getClickCount() != 2) return;

                    final int index = table.rowAtPoint(event.getPoint());
                    if (index == -1) return;

                    handleShowOrderErrors(errors.get(index));
                }
            });
        }};
    }

    private void handleShowOrderErrors(final OrderErrors errors) {
        new OrderErrorsOptionPanel(errors).prompt();
    }

    /**
     * Open the form
     */
    public void prompt() {
        JOptionPane.showConfirmDialog(
                null,
                this,
                "Not enough stocks",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }
}
