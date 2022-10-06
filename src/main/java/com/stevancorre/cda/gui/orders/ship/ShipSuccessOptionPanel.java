package com.stevancorre.cda.gui.orders.ship;

import javax.swing.*;

public class ShipSuccessOptionPanel extends JPanel {
    private final String title;
    private final String message;

    public ShipSuccessOptionPanel(final int ordersCount, final double total) {
        this.title = String.format("%s shipped", ordersCount == 1 ? "Order was" : "Orders were");

        this.message = String.format(
                "%d %s shipped, for a total of %.2f EUR",
                ordersCount,
                ordersCount == 1 ? "order was" : "orders were",
                total);
    }

    public void prompt() {
        JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }
}
