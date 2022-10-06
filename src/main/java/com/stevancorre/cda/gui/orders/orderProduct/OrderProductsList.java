package com.stevancorre.cda.gui.orders.orderProduct;

import com.stevancorre.cda.shop.OrderProduct;
import com.stevancorre.cda.shop.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.stevancorre.cda.gui.GUIUtils.makeButton;
import static com.stevancorre.cda.gui.GUIUtils.makeLabel;

public class OrderProductsList extends JPanel {
    private final ArrayList<Product> products;

    private final ArrayList<OrderProduct> entries;
    private final DefaultTableModel model;
    private final JTable table;

    public OrderProductsList(final ArrayList<Product> products) {
        setLayout(new BorderLayout());

        this.products = products;
        this.entries = new ArrayList<>();

        this.model = new DefaultTableModel();
        this.table = makeTable();

        add(new JPanel() {{
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            add(makeLabel("Products", l -> l.setLabelFor(table)));
            add(new JScrollPane(table));
            add(makeButton("Add product", b -> b.addActionListener(e -> handleAddOrderProduct())));
        }});
    }

    public OrderProduct[] getProducts() {
        return entries.toArray(new OrderProduct[0]);
    }

    private void updateData() {
        model.setDataVector(
                entries.stream()
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
                    if(event.getClickCount() != 2) return;

                    final int index = table.rowAtPoint(event.getPoint());
                    if(index == -1) return;

                    handleEditOrderProduct(entries.get(index));
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

    private void handleAddOrderProduct() {
        final AddOrderProductOptionPanel panel = new AddOrderProductOptionPanel(products);
        final OrderProduct data = panel.prompt();

        if (data == null) return;

        entries.add(data);
        updateData();
    }

    private void handleEditOrderProduct(final OrderProduct entry) {
        final EditOrderProductOptionPanel panel = new EditOrderProductOptionPanel(entry);
        final Integer data = panel.prompt();

        if(data == null) return;

        entry.setQuantity(data);
        updateData();
    }
}
