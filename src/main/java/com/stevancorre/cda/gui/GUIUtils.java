package com.stevancorre.cda.gui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public final class GUIUtils {
    public static JButton makeButton(final String text) {
        return new JButton(text);
    }

    public static JButton makeButton(final String text, final Consumer<JButton> configure) {
        return new JButton(text) {{
            configure.accept(this);
        }};
    }

    public static JPanel makeTextPanel(final String text) {
        return new JPanel(false) {{
            JLabel filler = new JLabel(text) {{
                setHorizontalAlignment(JLabel.CENTER);
            }};

            add(filler);
        }};
    }

    public static JTextField makeTextField() {
        return new JTextField() {{
            setMaximumSize(new Dimension(Short.MAX_VALUE, 26));
        }};
    }

    public static JLabel makeLabel(final String text, final Consumer<JLabel> configure) {
        return new JLabel(text) {{
            configure.accept(this);
        }};
    }

    public static void showError(final String message) {
        JOptionPane.showMessageDialog(null, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
