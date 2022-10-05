package com.stevancorre.cda.gui.clients;

import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import java.awt.*;

public class ClientsView extends JPanel {
    public ClientsView(final Shop shop) {
        super(new BorderLayout());

        final ClientsTablePanel clients = new ClientsTablePanel(shop);

        add(clients);
    }
}
