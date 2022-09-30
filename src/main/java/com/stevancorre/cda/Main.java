package com.stevancorre.cda;

import com.stevancorre.cda.gui.GUI;
import com.stevancorre.cda.shop.*;

import java.io.IOException;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {
    public static void main(String[] args) throws IOException  {
        final Shop shop = new Shop("src/main/resources/products.txt", "");

        invokeLater(() -> {
            final GUI gui = new GUI("Shop manager", shop);
            gui.open();
        });
    }
}