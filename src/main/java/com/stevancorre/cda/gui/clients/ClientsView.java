package com.stevancorre.cda.gui.clients;

import com.stevancorre.cda.shop.Client;
import com.stevancorre.cda.shop.Order;
import com.stevancorre.cda.shop.OrderStatus;
import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import static com.stevancorre.cda.gui.GUIUtils.*;

public class ClientsView extends JPanel {
    private final Shop shop;

    private final DefaultTableModel model;
    private final JTable table;

    public ClientsView(final Shop shop) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.shop = shop;
        this.model = new DefaultTableModel();
        this.table = makeTable();

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
                add(makeButton("Register client", b -> b.addActionListener(e -> handleRegisterClient())));
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

    public void updateData() {
        model.setDataVector(shop.getClients()
                        .stream()
                        .map(this::extractRow)
                        .toArray(Object[][]::new),
                new String[]{"First name", "Last name", "Active orders", "Finalized orders"}
        );
    }

    private void handleRegisterClient() {
        final RegisterClientOptionPanel panel = new RegisterClientOptionPanel();
        final RegisterClientData data = panel.prompt();

        if(data == null) return;

        shop.registerClient(data.firstName(), data.lastName());
        updateData();
    }

    private Object[] extractRow(final Client client) {
        if (shop == null) return new Object[0];

        final List<Order> clientOrders = shop.getOrders()
                .stream()
                .filter(x -> client.equals(x.getClient()))
                .toList();

        final long activeOrders = clientOrders
                .stream()
                .filter(x -> x.getStatus() == OrderStatus.Preparing)
                .count();
        final long finalizedOrders = clientOrders.size() - activeOrders;

        return new Object[]{
                client.getFirstName(),
                client.getLastName(),
                activeOrders,
                finalizedOrders
        };
    }
}