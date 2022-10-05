package com.stevancorre.cda.gui.orders;

import com.stevancorre.cda.gui.generic.EditModelPanel;
import com.stevancorre.cda.shop.Order;
import com.stevancorre.cda.shop.OrderStatus;

import javax.swing.*;

import static com.stevancorre.cda.gui.GUIUtils.*;

public class EditOrderPanel extends EditModelPanel<Order> {
    private final JTextField idField;
    private final JTextField dateField;
    private final JTextField statusField;
    private final JButton updateStatusButton;

    public EditOrderPanel() {
        super();

        this.idField = addReadonlyTextField("Id");
        add(makeVerticalSpace());

        this.dateField = addReadonlyTextField("Date");
        add(makeVerticalSpace());

        this.statusField = addReadonlyTextField("Status");
        add(makeVerticalSpace());

        updateStatusButton = makeButton(
                "Update status",
                b -> b.addActionListener(e -> handleUpdateStatus()));

        add(new JPanel() {{
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            add(updateStatusButton);
            add(makeHorizontalSpace());
            add(makeButton(
                    "Cancel",
                    b -> b.addActionListener(e -> close())));
        }});
    }

    @Override
    protected void updateView() {
        idField.setText(getSelection().getId().toString());
        dateField.setText(getSelection().getFormattedDate());
        statusField.setText(getSelection().getStatus().toString());

        updateStatusButton.setEnabled(getSelection().getStatus() != OrderStatus.Finalized);
    }

    private void handleUpdateStatus() {
        getSelection().updateStatus();

        updateView();

        triggerModelChangedEvents();
    }
}
