package com.stevancorre.cda.gui.orders.make;

import com.stevancorre.cda.shop.OrderProduct;
import com.stevancorre.cda.shop.Product;

import javax.swing.*;

import static com.stevancorre.cda.gui.GUIUtils.makeLabel;
import static com.stevancorre.cda.gui.GUIUtils.showError;

class EditOrderProductOptionPanel extends JPanel {
    private final JTextField quantityField;
    private final Product product;

    EditOrderProductOptionPanel(final OrderProduct entry) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);

        this.product = entry.getProduct();

        final JTextField productField = new JTextField(entry.getProduct().toString()) {{
            setEnabled(false);
        }};
        quantityField = new JTextField() {{
            setText(String.valueOf(entry.getQuantity()));
        }};

        add(makeLabel("Product", l -> l.setLabelFor(productField)));
        add(productField);

        add(makeLabel("Quantity", l -> l.setLabelFor(quantityField)));
        add(quantityField);
    }

    public Integer prompt() {
        int result = JOptionPane.showConfirmDialog(
                null,
                this,
                "Edit order entry",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        try {
            if (result == JOptionPane.OK_OPTION)
                return Integer.parseInt(this.quantityField.getText());
            else
                return null;
        } catch (NumberFormatException e) {
            showError("Invalid number format");

            return prompt();
        }
    }
}
