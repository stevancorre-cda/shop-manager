package com.stevancorre.cda.gui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.function.Consumer;

/**
 * Utils class
 */
public final class GUIUtils {
    /**
     * Make a button with some text
     */
    public static JButton makeButton(final String text) {
        return new JButton(text);
    }

    /**
     * Make a configurable button
     */
    public static JButton makeButton(final String text, final Consumer<JButton> configure) {
        return new JButton(text) {{
            configure.accept(this);
        }};
    }

    /**
     * Make a panel with plain text in it
     */
    public static JPanel makeTextPanel(final String text) {
        return new JPanel(false) {{
            JLabel filler = new JLabel(text) {{
                setHorizontalAlignment(JLabel.CENTER);
            }};

            add(filler);
        }};
    }

    /**
     * Make a text field
     */
    public static JTextField makeTextField() {
        return new JTextField() {{
            setMaximumSize(new Dimension(Short.MAX_VALUE, 26));
        }};
    }

    /**
     * Make a label with only text
     */
    public static JLabel makeLabel(final String text) {
        return new JLabel(text);
    }

    /**
     * Make a label and associate it to a component
     */
    public static JLabel makeLabel(final String text, final Consumer<JLabel> configure) {
        return new JLabel(text) {{
            configure.accept(this);
        }};
    }

    /**
     * Display an error in a message box
     */
    public static void showError(final String message) {
        JOptionPane.showMessageDialog(null, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Make a rigid area of with=5
     */
    public static Component makeHorizontalSpace() {
        return Box.createRigidArea(new Dimension(5, 0));
    }

    /**
     * Make a rigid area of height=5
     */
    public static Component makeVerticalSpace() {
        return Box.createRigidArea(new Dimension(0, 5));
    }

    /**
     * Make a text field that accepts only integers
     */
    public static JFormattedTextField makeIntTextField() {
        final NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);

        final NumberFormatter formatter = new NumberFormatter(format) {
            @Override
            public Object stringToValue(String text) throws ParseException {
                if (text.length() == 0)
                    return null;

                return super.stringToValue(text);
            }

            {
                setValueClass(Integer.class);
                setMinimum(0);
                setMaximum(Integer.MAX_VALUE);
                setAllowsInvalid(false);
                setCommitsOnValidEdit(true);
            }
        };

        return new JFormattedTextField(formatter);
    }

    /**
     * Make a text field that accepts only doubles
     */
    public static JFormattedTextField makeDoubleTextField() {
        final NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);

        final NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Double.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        return new JFormattedTextField(format);
    }
}
