package com.stevancorre.cda.gui;

import com.stevancorre.cda.gui.clients.ClientsView;
import com.stevancorre.cda.gui.orders.OrdersView;
import com.stevancorre.cda.gui.products.ProductsPanel;
import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import java.awt.*;

/**
 * Describe the main view (with the three panels)
 */
public final class MainView extends JPanel {
    public MainView(final Shop shop) {
        super(new BorderLayout());

        add(new JTabbedPane() {{
            addTab("Products", new ProductsPanel(shop));
            addTab("Clients", new ClientsView(shop));
            addTab("Orders", new OrdersView(shop));
        }});
    }
}
