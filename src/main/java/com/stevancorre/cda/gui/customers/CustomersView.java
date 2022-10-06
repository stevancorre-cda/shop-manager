package com.stevancorre.cda.gui.customers;

import com.stevancorre.cda.shop.Customer;
import com.stevancorre.cda.shop.Order;
import com.stevancorre.cda.shop.OrderStatus;
import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static com.stevancorre.cda.gui.GUIUtils.*;

/**
 * Customers table view
 */
public class CustomersView extends JPanel {
    private final Shop shop;

    private final DefaultTableModel model;

    public CustomersView(final Shop shop) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.shop = shop;
        this.model = new DefaultTableModel();
        JTable table = makeTable();

        add(makeVerticalSpace());
        add(makeButtonsPanel());
        add(makeVerticalSpace());
        add(new JScrollPane(table));
    }

    private JComponent makeButtonsPanel() {
        return new JPanel(new BorderLayout()) {{
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

            add(new JPanel() {{
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                setAlignmentX(Component.LEFT_ALIGNMENT);

                add(makeHorizontalSpace());
                add(makeButton("Register customer", b -> b.addActionListener(e -> handleRegisterCustomer())));
            }});
        }};
    }

    private JTable makeTable() {
        return new JTable(model) {{
            updateData();

            setDefaultEditor(Object.class, null);
            setAutoCreateRowSorter(true);
            setFillsViewportHeight(true);
        }};
    }

    /**
     * Update table data
     */
    public void updateData() {
        model.setDataVector(shop.getCustomers()
                        .stream()
                        .map(this::extractRow)
                        .toArray(Object[][]::new),
                new String[]{"First name", "Last name", "Active orders", "Finalized orders"}
        );
    }

    /**
     * Open the register customer popup
     */
    private void handleRegisterCustomer() {
        final RegisterCustomerOptionPanel panel = new RegisterCustomerOptionPanel();
        final RegisterCustomerData data = panel.prompt();

        if (data == null) return;

        shop.registerCustomer(data.firstName(), data.lastName());
        updateData();
    }

    /**
     * Convert customer data to row data
     */
    private Object[] extractRow(final Customer customer) {
        if (shop == null) return new Object[0];

        // count finalized and active orders
        final List<Order> customerOrders = shop.getOrders()
                .stream()
                .filter(x -> customer.equals(x.getCustomer()))
                .toList();

        final long activeOrders = customerOrders
                .stream()
                .filter(x -> x.getStatus() == OrderStatus.Preparing)
                .count();
        final long finalizedOrders = customerOrders.size() - activeOrders;

        return new Object[]{
                customer.getFirstName(),
                customer.getLastName(),
                activeOrders,
                finalizedOrders
        };
    }
}