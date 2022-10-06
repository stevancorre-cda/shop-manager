package com.stevancorre.cda.gui.orders;

import com.stevancorre.cda.gui.orders.edit.EditOrderOptionPanel;
import com.stevancorre.cda.gui.orders.make.MakeOrderData;
import com.stevancorre.cda.gui.orders.make.MakeOrderOptionPanel;
import com.stevancorre.cda.shop.Order;
import com.stevancorre.cda.shop.OrderProduct;
import com.stevancorre.cda.shop.OrderStatus;
import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static com.stevancorre.cda.gui.GUIUtils.*;

public class OrdersView extends JPanel {
    private final Shop shop;

    private final DefaultTableModel model;
    private final JTable table;

    public OrdersView(final Shop shop) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.shop = shop;

        add(makeVerticalSpace());
        add(makeToolbarPanel());
        add(makeVerticalSpace());

        this.model = new DefaultTableModel();
        this.table = makeTable();
        add(new JScrollPane(table));
    }

    private JComponent makeToolbarPanel() {
        return new JPanel(new BorderLayout()) {{
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

            add(new JPanel() {{
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                setAlignmentX(Component.LEFT_ALIGNMENT);

                add(makeHorizontalSpace());
                add(makeButton("Make order", b -> b.addActionListener(e -> handleMakeOrder())));
                add(makeHorizontalSpace());
                add(makeButton("Ship orders", b -> b.addActionListener(e -> handleShipOrders())));
                add(makeHorizontalSpace());

                add(Box.createHorizontalGlue());
                final double total = shop.getOrders()
                        .stream()
                        .filter(x -> x.getStatus() == OrderStatus.Finalized)
                        .mapToDouble(Order::getTotalPrice)
                        .sum();
                add(makeLabel(String.format("Finalized orders total: %.2f EUR", total)));
            }});
        }};
    }

    private void updateData() {
        model.setDataVector(shop.getOrders()
                        .stream()
                        .map(this::extractRow)
                        .toArray(Object[][]::new),
                new String[]{"Status", "Date", "Client", "Products count", "Total price"}
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
                    if (event.getClickCount() != 2) return;

                    final int index = table.rowAtPoint(event.getPoint());
                    if (index == -1) return;

                    handleEditorOrder(shop.getOrders().get(index));
                }
            });
        }};
    }

    private Object[] extractRow(final Order order) {
        return new Object[]{
                order.getStatus(),
                order.getFormattedDate(),
                order.getClient().getFullName(),
                Arrays.stream(order.getProducts()).mapToInt(OrderProduct::getQuantity).sum(),
                order.getTotalPrice()
        };
    }

    private void handleMakeOrder() {
        final MakeOrderOptionPanel panel = new MakeOrderOptionPanel(shop);
        final MakeOrderData data = panel.prompt();

        if (data == null) return;

        shop.makeOrder(data.client(), data.products());
        updateData();
    }

    private void handleEditorOrder(final Order order) {
        final EditOrderOptionPanel panel = new EditOrderOptionPanel(order);
        panel.prompt();
        updateData();
    }

    private void handleShipOrders() {
        for (final Order order : shop.getOrders()) {
            final String errors = shop.tryShip(order);
            System.out.println(errors);
        }

        updateData();
    }
}
