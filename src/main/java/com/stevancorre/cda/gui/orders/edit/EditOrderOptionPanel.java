package com.stevancorre.cda.gui.orders.edit;

import com.stevancorre.cda.shop.Order;
import com.stevancorre.cda.shop.OrderStatus;

import javax.swing.*;

import static com.stevancorre.cda.gui.GUIUtils.makeLabel;

public class EditOrderOptionPanel extends JPanel {
    public EditOrderOptionPanel(final Order order) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);

        final JTextField dateField = new JTextField() {{
            setText(order.getFormattedDate());
            setEnabled(false);
        }};
        final JTextField customerField = new JTextField() {{
            setText(order.getCustomer().getFullName());
            setEnabled(false);
        }};
        final OrderProductsList productsList = new OrderProductsList(order.getProducts(), order.getStatus() == OrderStatus.Finalized);

        add(makeLabel("Date", l -> l.setLabelFor(dateField)));
        add(dateField);
        add(makeLabel("Customer", l -> l.setLabelFor(customerField)));
        add(customerField);
        add(productsList);
    }

    public void prompt() {
        JOptionPane.showConfirmDialog(
                null,
                this,
                "Edit order",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }
}
