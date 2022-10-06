package com.stevancorre.cda.gui.orders.make;

import com.stevancorre.cda.shop.OrderProduct;
import com.stevancorre.cda.shop.Product;

import javax.swing.*;
import java.util.ArrayList;

import static com.stevancorre.cda.gui.GUIUtils.*;

class AddOrderProductOptionPanel extends JPanel {
    private final JComboBox<Product> productField;
    private final JTextField quantityField;

    AddOrderProductOptionPanel(final ArrayList<Product> products) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);

        productField = new JComboBox<>(products.toArray(new Product[0]));
        quantityField = new JTextField();

        add(makeLabel("Product", l -> l.setLabelFor(productField)));
        add(productField);

        add(makeLabel("Quantity", l -> l.setLabelFor(quantityField)));
        add(quantityField);
    }

    public OrderProduct prompt() {
        int result = JOptionPane.showConfirmDialog(
                null,
                this,
                "Add order entry",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        try {
            if (result == JOptionPane.OK_OPTION)
                return new OrderProduct(
                        (Product) this.productField.getSelectedItem(),
                        Integer.parseInt(this.quantityField.getText()));
            else
                return null;
        } catch (NumberFormatException e) {
            showError("Invalid number format");

            return prompt();
        }
    }
}
