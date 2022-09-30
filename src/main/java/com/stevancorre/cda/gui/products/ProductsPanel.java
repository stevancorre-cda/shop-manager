package com.stevancorre.cda.gui.products;

import com.stevancorre.cda.shop.Product;
import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static com.stevancorre.cda.gui.GUIUtils.makeButton;

public class ProductsPanel extends JPanel {
    private final Shop shop;
    private final DefaultTableModel model;

    public ProductsPanel(final Shop shop) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.shop = shop;
        this.model = new DefaultTableModel(0, 0);

        add(Box.createRigidArea(new Dimension(Integer.MAX_VALUE, 5)));
        add(makeButtonsPanel());
        add(Box.createRigidArea(new Dimension(Integer.MAX_VALUE, 5)));
        add(makeTable());
    }

    private JComponent makeButtonsPanel() {
        return new JPanel(new BorderLayout()) {{
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

            add(new JPanel() {{
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                setAlignmentX(Component.LEFT_ALIGNMENT);

                add(Box.createRigidArea(new Dimension(5, 0)));
                add(makeButton("New product", b -> b.addActionListener(e -> handleCreateProduct())));
            }});
        }};
    }

    private JComponent makeTable() {
        updateData();

        final JTable table = new JTable(model) {{
            setDefaultEditor(Object.class, null);
            setAutoCreateRowSorter(true);
            setFillsViewportHeight(true);
        }};

        return new JScrollPane(table);
    }

    private void updateData() {
        model.setDataVector(
                shop.getProducts()
                        .stream()
                        .map(this::extractProductData)
                        .toArray(Object[][]::new),
                new String[]{"Name", "Price", "Quantity"}
        );
    }

    private Object[] extractProductData(final Product product) {
        return new Object[]{
                product.getName(),
                String.format("%.2f EUR", product.getPrice()),
                product.getAvailableQuantity()
        };
    }

    private void handleCreateProduct() {
        final CreateProductOptionPanel panel = new CreateProductOptionPanel();
        final CreateProductData data = panel.prompt();

        if(data == null) return;

        shop.createProduct(data.name(), data.price(), data.quantity());
        updateData();
    }
}
