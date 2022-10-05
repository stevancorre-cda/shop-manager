package com.stevancorre.cda.gui.clients;

import com.stevancorre.cda.gui.generic.ModelsTablePanel;
import com.stevancorre.cda.shop.Client;
import com.stevancorre.cda.shop.Order;
import com.stevancorre.cda.shop.OrderStatus;
import com.stevancorre.cda.shop.Shop;

import java.util.List;

public class ClientsTablePanel extends ModelsTablePanel<Client> {
    private final Shop shop;

    protected ClientsTablePanel(final Shop shop) {
        super(
                shop.getClients(),
                new String[]{"First name", "Last name", "Active orders", "Finalized orders"},
                new EditClientPanel());

        this.shop = shop;

        updateData();
    }

    @Override
    protected Object[] extractRow(final Client client) {
        if (shop == null) return new Object[0];

        final List<Order> clientOrders = shop.getOrders()
                .stream()
                .filter(x -> x.getClient().getId() == client.getId())
                .toList();

        final long activeOrders = clientOrders
                .stream()
                .filter(x -> x.getStatus() == OrderStatus.Preparing || x.getStatus() == OrderStatus.Shipped)
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
