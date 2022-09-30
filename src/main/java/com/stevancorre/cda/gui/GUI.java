package com.stevancorre.cda.gui;

import com.stevancorre.cda.shop.Shop;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI(final String title, final Shop shop) {
        super(title);

        setSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new MainView(shop));
    }

    public void open() {
        setVisible(true);
    }
}
