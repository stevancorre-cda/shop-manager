package com.stevancorre.cda.gui.clients;

import com.stevancorre.cda.gui.generic.EditModelPanel;
import com.stevancorre.cda.shop.Client;

import javax.swing.*;

import static com.stevancorre.cda.gui.GUIUtils.*;
import static com.stevancorre.cda.gui.GUIUtils.makeButton;

public class EditClientPanel  extends EditModelPanel<Client>  {
    private final JTextField idField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;

    public EditClientPanel() {
        super();

        this.idField = addReadonlyTextField("Id");
        add(makeVerticalSpace());

        this.firstNameField = addReadonlyTextField("First name");
        add(makeVerticalSpace());

        this.lastNameField = addReadonlyTextField("Last name");
        add(makeVerticalSpace());

        add(new JPanel() {{
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            add(makeHorizontalSpace());
            add(makeButton(
                    "Cancel",
                    b -> b.addActionListener(e -> close())));
        }});
    }

    @Override
    protected void updateView() {
        idField.setText(getSelection().getId().toString());
        firstNameField.setText(getSelection().getFirstName());
        lastNameField.setText(getSelection().getLastName());
    }
}
