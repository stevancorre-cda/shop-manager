package com.stevancorre.cda;

import com.stevancorre.cda.gui.GUI;
import com.stevancorre.cda.shop.Customer;
import com.stevancorre.cda.shop.OrderProduct;
import com.stevancorre.cda.shop.Product;
import com.stevancorre.cda.shop.Shop;

import java.io.IOException;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {
    public static void main(String[] args) throws IOException {
        final Shop shop = new Shop(
                "src/main/resources/products.txt",
                "src/main/resources/customers.txt",
                "src/main/resources/orders.txt");

        final Product product = shop.createProduct("baguette de pain super cher", 10, 10);
        final Product product2 = shop.createProduct("eau de fou", 10, 50);
        final Product product3 = shop.createProduct("brosse a chiotte en or", 1000, 10);
        final Customer customer = shop.registerCustomer("michel", "bleas");
        final Customer customer2 = shop.registerCustomer("ccaa", "oiugrubgr");
        shop.makeOrder(customer, new OrderProduct[]{new OrderProduct(product, 2)});
        shop.makeOrder(customer, new OrderProduct[]{new OrderProduct(product, 200)});
        shop.makeOrder(customer2, new OrderProduct[]{new OrderProduct(product2, 200), new OrderProduct(product2, 20)});
        shop.makeOrder(customer2, new OrderProduct[]{new OrderProduct(product3, 10)});
        shop.makeOrder(customer2, new OrderProduct[]{new OrderProduct(product3, 100), new OrderProduct(product, 20), new OrderProduct(product2, 200)});

        invokeLater(() -> {
            final GUI gui = new GUI("Shop manager", shop);
            gui.open();
        });
    }
}