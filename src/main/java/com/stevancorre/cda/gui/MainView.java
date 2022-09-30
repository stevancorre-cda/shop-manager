package com.stevancorre.cda.gui;

import com.stevancorre.cda.gui.products.ProductsPanel;
import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import java.awt.*;

import static com.stevancorre.cda.gui.GUIUtils.makeTextPanel;

public final class MainView extends JPanel {
    public MainView(final Shop shop) {
        super(new BorderLayout());

        add(new JTabbedPane() {{
            addTab("Products", new ProductsPanel(shop));
            addTab("Clients", makeTextPanel("Panel #2s"));
            addTab("Orders", makeTextPanel("Panel #3"));
        }});
    }
}
