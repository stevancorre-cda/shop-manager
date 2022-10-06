package com.stevancorre.cda.gui;

import com.stevancorre.cda.gui.customers.CustomersView;
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

        final ProductsPanel productsPanel = new ProductsPanel(shop);
        final CustomersView customersView = new CustomersView(shop);
        add(new JTabbedPane() {{
            addTab("Products", productsPanel);
            addTab("Clients", customersView);
            addTab("Orders", new OrdersView(shop));

            addChangeListener(e -> {
                productsPanel.updateData();
                customersView.updateData();
            });
        }});
    }
}
