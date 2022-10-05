package com.stevancorre.cda.gui.orders;

import com.stevancorre.cda.gui.generic.ModelsTablePanel;
import com.stevancorre.cda.shop.Order;

import java.util.ArrayList;

public class OrdersTablePanel extends ModelsTablePanel<Order> {
    protected OrdersTablePanel(final ArrayList<Order> data) {
        super(
                data,
                new String[]{"Status", "Client", "Date", "Products count", "Total"},
                new EditOrderPanel());
    }

    @Override
    protected Object[] extractRow(final Order order) {
        return new Object[]{
                order.getStatus(),
                order.getClient().getName(),
                order.getFormattedDate(),
                order.getProductsCount(),
                String.format("%.2f EUR", order.getTotalPrice()),
        };
    }
}
