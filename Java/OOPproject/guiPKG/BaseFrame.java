//============================================================================
// File Name   : BaseFrame.java
// Author      : Gabriel Tickle Garcia
// ID#         : 2007394
// Description : Primary baseFrame class
//============================================================================
package OOPproject.guiPKG;

import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import OOPproject.guiPKG.GuiElements.FrameDragListener;

public class BaseFrame {

    // #region Constants

    final int panelw = 1000;
    final int panelh = 600;
    final int uih = 25;
    final int admin = 1;
    final int customer = 0;
    final int adminsetX = 140;
    final int adminsetY = 520;// was 470
    final int adminoffsetX = adminsetX + 60;
    final int adminoffsetY = adminsetY + 9;
    // #endregion

    // #region Variables

    private static JFrame frame;

    private static Image mainIcon;

    // #endregion

    public aseFrame() throws IOException {

        frame = new JFrame();

        mainIcon = ImageIO.read(getClass().getResource("/OOPproject/Images/tech6.png"));

        frame.setIconImage(mainIcon);

        // Sets the default opereaction when the exit button is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Sets layout to null to allow free placement
        frame.setLayout(null);

        frame.setBackground(null);
        // Calls Creat Panel Function to create and add base panel

        // Removes title bar, rounds the edge of the frame and sets default size
        frame.setUndecorated(true);
        frame.setShape(new RoundRectangle2D.Double(0, 0, panelw, panelh, 30, 30));
        frame.setSize(panelw, panelh);
        // sets location of the frame to the center of the screen
        frame.setLocationRelativeTo(null);
        // makes frame visible
        frame.setVisible(true);

        // #region Enables Undecorated Frame drag movement
        FrameDragListener frameDragListener = new FrameDragListener(frame);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);
        // #endregion

    }

    public JFrame getBaseFrame() {

        return frame;

    }

}
