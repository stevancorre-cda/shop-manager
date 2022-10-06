package com.stevancorre.cda.gui.products;

import javax.swing.*;
import java.awt.*;

import static com.stevancorre.cda.gui.GUIUtils.*;

/**
 * Popup form to create a new product
 */
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

    /**
     * Open the form
     *
     * @return Validated product data
     */
    public CreateProductData prompt() {
        int result = JOptionPane.showConfirmDialog(
                null,
                this,
                "Create new product",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        try {
            if (result == JOptionPane.OK_OPTION) {

                final String name = this.nameField.getText();
                final double price = Double.parseDouble(this.priceField.getText());
                final int quantity = Integer.parseInt(this.quantityField.getText());

                if (name.isBlank())
                    throw new Exception();

                return new CreateProductData(
                        name,
                        price,
                        quantity
                );
            } else
                return null;
        } catch (Exception e) {
            showError("Invalid input");

            return prompt();
        }
    }
}

