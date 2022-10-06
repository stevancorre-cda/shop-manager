package com.stevancorre.cda.gui.orders;

import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import java.awt.*;

import static com.stevancorre.cda.gui.GUIUtils.*;

public class OrdersView extends JPanel {
    private final Shop shop;
    private final OrdersTablePanel ordersTable;

    public OrdersView(final Shop shop) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.shop = shop;
        this.ordersTable = new OrdersTablePanel(shop.getOrders());

        add(makeVerticalSpace());
        add(makeButtonsPanel());
        add(makeVerticalSpace());
        add(new JPanel() {{
            setLayout(new BorderLayout());
            add(ordersTable);
        }});
    }

    private JComponent makeButtonsPanel() {
        return new JPanel(new BorderLayout()) {{
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

            add(new JPanel() {{
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                setAlignmentX(Component.LEFT_ALIGNMENT);

                add(makeHorizontalSpace());
                add(makeButton("Make order", b -> b.addActionListener(e -> handleMakeOrder())));
            }});
        }};
    }

    private void handleMakeOrder() {
        final MakeOrderOptionPanel panel = new MakeOrderOptionPanel(shop);
        final MakeOrderData data = panel.prompt();

        if (data == null) return;

        shop.makeOrder(data.client(), data.products());
        ordersTable.updateData();
    }
}
