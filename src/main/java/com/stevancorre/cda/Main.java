package com.stevancorre.cda;

import com.stevancorre.cda.gui.GUI;
import com.stevancorre.cda.shop.*;

import java.io.IOException;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {
    public static void main(String[] args) throws IOException  {
        final Shop shop = new Shop("src/main/resources/products.txt", "");

        final Product product = shop.createProduct("baguette de pain super cher", 10, 10);
        shop.createProduct("baguette de pain super cher", 10, 100);
        shop.createProduct("baguette de pain super cher", 10, 100);
        final Client client = shop.registerClient("michel", "bleas");
        shop.makeOrder(client, new OrderProduct[]{new OrderProduct(product, 2)});

        invokeLater(() -> {
            final GUI gui = new GUI("Shop manager", shop);
            gui.open();
        });
    }
}