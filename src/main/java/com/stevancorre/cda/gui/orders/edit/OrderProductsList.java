package com.stevancorre.cda.gui.orders.edit;

import com.stevancorre.cda.shop.OrderProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static com.stevancorre.cda.gui.GUIUtils.makeLabel;

class OrderProductsList extends JPanel {
    private final OrderProduct[] products;

    private final DefaultTableModel model;
    private final JTable table;

    public OrderProductsList(final OrderProduct[] products, final boolean readonly) {
        setLayout(new BorderLayout());

        this.products = products;

        this.model = new DefaultTableModel();
        this.table = makeTable();
        this.table.setEnabled(!readonly);

        add(makeLabel("Products", l -> l.setLabelFor(table)));
        add(new JScrollPane(table));
    }

    private void updateData() {
        model.setDataVector(
                Arrays.stream(products)
                        .map(this::extractRow)
                        .toArray(Object[][]::new),
                new String[]{"Name", "Count", "Price per unit", "Total price"}
        );
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
                    if(!table.isEnabled()) return;
                    if(event.getClickCount() != 2) return;

                    final int index = table.rowAtPoint(event.getPoint());
                    if(index == -1) return;

                    handleEditOrderProduct(products[index]);
                }
            });
        }};
    }

    private Object[] extractRow(final OrderProduct entry) {
        return new Object[]{
                entry.getProduct().getName(),
                entry.getQuantity(),
                entry.getProduct().getPrice(),
                entry.getQuantity() * entry.getProduct().getPrice()
        };
    }

    private void handleEditOrderProduct(final OrderProduct entry) {
        final EditOrderProductOptionPanel panel = new EditOrderProductOptionPanel(entry);
        panel.prompt();
        updateData();
    }
}
