package com.stevancorre.cda.gui.orders.edit;

import com.stevancorre.cda.shop.OrderProduct;

import javax.swing.*;

import static com.stevancorre.cda.gui.GUIUtils.*;

class EditOrderProductOptionPanel extends JPanel {
    private final JTextField quantityField;
    private final OrderProduct entry;

    EditOrderProductOptionPanel(final OrderProduct entry) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);

        this.entry = entry;

        final JTextField productField = new JTextField(entry.getProduct().toString()) {{
            setEnabled(false);
        }};
        quantityField = makeIntTextField();
        quantityField.setText(String.valueOf(entry.getQuantity()));

        add(makeLabel("Product", l -> l.setLabelFor(productField)));
        add(productField);

        add(makeLabel("Quantity", l -> l.setLabelFor(quantityField)));
        add(quantityField);
    }

    public void prompt() {
        int result = JOptionPane.showConfirmDialog(
                null,
                this,
                "Edit order entry",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        try {
            if (result == JOptionPane.OK_OPTION)
                this.entry.setQuantity(Integer.parseInt(quantityField.getText()));
        } catch (NumberFormatException e) {
            showError("Invalid number format");

            prompt();
        }
    }
}
