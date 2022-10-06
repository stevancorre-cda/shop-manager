package com.stevancorre.cda.gui.customers;


import javax.swing.*;
import java.awt.*;

import static com.stevancorre.cda.gui.GUIUtils.makeLabel;
import static com.stevancorre.cda.gui.GUIUtils.showError;

/**
 * Popup form to register a new customer
 */
class RegisterCustomerOptionPanel extends JPanel {
    private final JTextField firstNameField;
    private final JTextField lastNameField;

    RegisterCustomerOptionPanel() {
        super(new GridLayout(0, 1));

        this.firstNameField = new JTextField();
        this.lastNameField = new JTextField();

        add(makeLabel("First name", l -> l.setLabelFor(this.firstNameField)));
        add(this.firstNameField);

        add(makeLabel("Last name", l -> l.setLabelFor(this.lastNameField)));
        add(this.lastNameField);
    }

    public RegisterCustomerData prompt() {
        int result = JOptionPane.showConfirmDialog(
                null,
                this,
                "Register new customer",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        try {
            if (result == JOptionPane.OK_OPTION) {
                final String firstName = this.firstNameField.getText();
                final String lastName = this.lastNameField.getText();

                if (firstName.isBlank() || lastName.isBlank())
                    throw new Exception();

                return new RegisterCustomerData(firstName, lastName);
            } else
                return null;
        } catch (Exception e) {
            showError("Invalid input");

            return prompt();
        }
    }
}

