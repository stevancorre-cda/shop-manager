package com.stevancorre.cda.gui.products;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

import static com.stevancorre.cda.gui.GUIUtils.makeLabel;
import static com.stevancorre.cda.gui.GUIUtils.showError;

class CreateProductOptionPanel extends JPanel {
    private final JTextField nameField;
    private final JTextField priceField;
    private final JTextField quantityField;

    CreateProductOptionPanel() {
        super(new GridLayout(0, 1));

        final NumberFormat paymentFormat = NumberFormat.getCurrencyInstance();

        this.nameField = new JTextField();
        this.priceField = new JTextField();
        this.quantityField = new JTextField();

        add(makeLabel("Name", l -> l.setLabelFor(this.nameField)));
        add(this.nameField);

        add(makeLabel("Price", l -> l.setLabelFor(this.priceField)));
        add(this.priceField);

        add(makeLabel("Quantity", l -> l.setLabelFor(this.quantityField)));
        add(this.quantityField);
    }

    public CreateProductData prompt() {
        int result = JOptionPane.showConfirmDialog(
                null,
                this,
                "Create new product",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        try {
            if (result == JOptionPane.OK_OPTION)
                return new CreateProductData(
                        this.nameField.getText(),
                        Double.parseDouble(this.priceField.getText()),
                        Integer.parseInt(this.quantityField.getText())
                );
            else
                return null;
        }
        catch (NumberFormatException e) {
            showError("Invalid number format");

            return prompt();
        }
    }
}

