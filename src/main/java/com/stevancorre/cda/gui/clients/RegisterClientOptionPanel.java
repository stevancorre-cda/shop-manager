package com.stevancorre.cda.gui.clients;


import javax.swing.*;
import java.awt.*;

import static com.stevancorre.cda.gui.GUIUtils.makeLabel;
import static com.stevancorre.cda.gui.GUIUtils.showError;

class RegisterClientOptionPanel extends JPanel {
    private final JTextField firstNameField;
    private final JTextField lastNameField;

    RegisterClientOptionPanel() {
        super(new GridLayout(0, 1));

        this.firstNameField = new JTextField();
        this.lastNameField = new JTextField();

        add(makeLabel("First name", l -> l.setLabelFor(this.firstNameField)));
        add(this.firstNameField);

        add(makeLabel("Last name", l -> l.setLabelFor(this.lastNameField)));
        add(this.lastNameField);
    }

    public RegisterClientData prompt() {
        int result = JOptionPane.showConfirmDialog(
                null,
                this,
                "Register new client",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        try {
            if (result == JOptionPane.OK_OPTION) {
                final String firstName = this.firstNameField.getText();
                final String lastName = this.lastNameField.getText();

                if (firstName.isBlank() || lastName.isBlank())
                    throw new Exception();

                return new RegisterClientData(firstName, lastName);
            } else
                return null;
        } catch (Exception e) {
            showError("Invalid input");

            return prompt();
        }
    }
}

