//============================================================================
// File Name   : GuiElements.java
// Author      : Gabriel Tickle Garcia
// ID#         : 2007394
// Description : Primary guiElements class //@todo TODO Lmao make a better description
//============================================================================

package OOPproject.guiPKG;

import java.awt.geom.RoundRectangle2D;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

import javax.swing.border.Border;

public class GuiElements {

    //@todo TODO Does this empty method need to be here? Let me know if you get rid of it
    public GuiElements() {

    }

    public static JButton exitButton;

    public static void addExitButton() {

        exitButton = new JButton("X");
        exitButton.setForeground(Color.white);
        exitButton.setLayout(new GridLayout(1, 1));
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    class RoundedJTextField extends JTextField {
        private Shape shape;

        public RoundedJTextField(int size) {
            super(size);
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }

        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            }
            return shape.contains(x, y);
        }
        // got from
        // https://stackoverflow.com/questions/8515601/java-swing-rounded-border-for-jtextfield
    }

    public static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
        // got from
        // https://stackoverflow.com/questions/8515601/java-swing-rounded-border-for-jtextfield
    }

    // Allows the program to be dragged across the screen
    public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
        // got from
        // https://stackoverflow.com/questions/16046824/making-a-java-swing-frame-movable-and-setundecorated
    }

}
