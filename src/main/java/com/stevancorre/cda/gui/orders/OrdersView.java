package com.stevancorre.cda.gui.orders;

import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import java.awt.*;

public class OrdersView extends JPanel {
    public OrdersView(final Shop shop) {
        super(new BorderLayout());

        final OrdersTablePanel orders = new OrdersTablePanel(shop.getOrders());

        add(orders);
    }
}
