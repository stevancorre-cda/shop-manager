package com.stevancorre.cda.gui.products;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

import static com.stevancorre.cda.gui.GUIUtils.*;

class CreateProductOptionPanel extends JPanel {
    private final JTextField nameField;
    private final JTextField priceField;
    private final JTextField quantityField;

    CreateProductOptionPanel() {
        super(new GridLayout(0, 1));

        this.nameField = new JTextField();
        this.priceField = makeDoubleTextField();
        this.quantityField = makeIntTextField();

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
            final String name = this.nameField.getText();
            final double price = Double.parseDouble(this.priceField.getText());
            final int quantity = Integer.parseInt(this.quantityField.getText());

            if(name.isBlank())
                throw new Exception();

            if (result == JOptionPane.OK_OPTION)
                return new CreateProductData(
                        name,
                        price,
                        quantity
                );
            else
                return null;
        }
        catch (Exception e) {
            showError("Invalid input");

            return prompt();
        }
    }
}

