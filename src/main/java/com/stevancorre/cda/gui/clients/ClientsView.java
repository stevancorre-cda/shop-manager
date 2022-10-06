package com.stevancorre.cda.gui.clients;

import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import java.awt.*;

import static com.stevancorre.cda.gui.GUIUtils.*;

public class ClientsView extends JPanel {
    private final Shop shop;
    private final ClientsTablePanel clientsTable;

    public ClientsView(final Shop shop) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.shop = shop;
        this.clientsTable = new ClientsTablePanel(shop);

        add(makeVerticalSpace());
        add(makeButtonsPanel());
        add(makeVerticalSpace());
        add(new JPanel() {{
            setLayout(new BorderLayout());
            add(clientsTable);
        }});
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

    private void handleRegisterClient() {
        final RegisterClientOptionPanel panel = new RegisterClientOptionPanel();
        final RegisterClientData data = panel.prompt();

        if(data == null) return;

        shop.registerClient(data.firstName(), data.lastName());
        clientsTable.updateData();
    }
}
