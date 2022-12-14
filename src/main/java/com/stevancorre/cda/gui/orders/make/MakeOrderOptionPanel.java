package com.stevancorre.cda.gui.orders.make;

import com.stevancorre.cda.shop.Customer;
import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import java.util.Vector;

import static com.stevancorre.cda.gui.GUIUtils.makeLabel;
import static com.stevancorre.cda.gui.GUIUtils.showError;

public class MakeOrderOptionPanel extends JPanel {
    private final JComboBox<Customer> customerField;
    private final OrderProductsTable productsList;

    public MakeOrderOptionPanel(final Shop shop) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);

        this.customerField = new JComboBox<>(new Vector<>(shop.getCustomers()));
        this.productsList = new OrderProductsTable(shop.getProducts());

        add(makeLabel("Customer", l -> l.setLabelFor(this.customerField)));
        add(this.customerField);
        add(this.productsList);
    }

    public MakeOrderData prompt() {
        int result = JOptionPane.showConfirmDialog(
                null,
                this,
                "Make new order",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        try {
            if (result == JOptionPane.OK_OPTION) {
                if (this.productsList.getProducts().length == 0)
                    throw new Exception();

                return new MakeOrderData(
                        (Customer) this.customerField.getSelectedItem(),
                        this.productsList.getProducts());
            } else
                return null;
        } catch (final Exception e) {
            showError("Invalid input");

            return prompt();
        }
    }
}

