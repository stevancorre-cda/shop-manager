package com.stevancorre.cda.gui.orders.make;

import com.stevancorre.cda.shop.Client;
import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import java.util.Vector;

import static com.stevancorre.cda.gui.GUIUtils.makeLabel;

public class MakeOrderOptionPanel extends JPanel {
    private final JComboBox<Client> clientField;
    private final OrderProductsList productsList;

    public MakeOrderOptionPanel(final Shop shop) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);

        this.clientField = new JComboBox<>(new Vector<>(shop.getClients()));
        this.productsList = new OrderProductsList(shop.getProducts());

        add(makeLabel("Client", l -> l.setLabelFor(this.clientField)));
        add(this.clientField);
        add(this.productsList);
    }

    public MakeOrderData prompt() {
        int result = JOptionPane.showConfirmDialog(
                null,
                this,
                "Make new order",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION)
            return new MakeOrderData(
                    (Client) this.clientField.getSelectedItem(),
                    this.productsList.getProducts());
        else
            return null;
    }
}

