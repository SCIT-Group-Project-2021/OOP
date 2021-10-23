package OOPproject.guiPKG;

import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;

import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.text.MaskFormatter;

public class adminGui {

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

    private static Color flowColor;
    private static Color digicelColor;

    private static Font Oswald;

    private static JFrame frame;

    private static JPanel sidePanel;
    private static JPanel mainPanel;
    private static JPanel basePanel;

    private static JLabel Logo;

    private static Icon digicelLogoIcon;
    private static Icon flowLogoIcon;

    private static JButton createUserButton;
    private static JButton viewAllCustomerButton;
    private static JButton createCreditButton;
    private static JButton viewCreditButton;

    // #endregion

    public adminGui(int providor) {

        // #region Are for variables to be assigned
        // Shoul i just leave this as the values and remove the individual rgb constants
        // or not?

        flowColor = new Color(7, 116, 188);
        digicelColor = new Color(58, 27, 128);

        Oswald = new Font("Oswald", Font.TYPE1_FONT, 15);

        // #endregion

        // #region Try Catch block For frame creation
        // Calls Function To create main background Plate
        try {
            createFrame();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // #endregion

        // #region Basic panel setup
        /*
         * Sets the Primary Panel Layout to a 2x1 Grid to auto align the two
         * sections(loginPanel and imagePanel)
         */

        sidePanel = new JPanel();
        mainPanel = new JPanel();

        switch (providor) {
        case 1:
            digicelLogoIcon = new ImageIcon(
                    new ImageIcon(adminGui.class.getResource("/OOPproject/Images/Digicel-Logo.png")).getImage()
                            .getScaledInstance(100, 60, Image.SCALE_DEFAULT));
            Logo = new JLabel(digicelLogoIcon);

            // #region set Panel Backgrounds
        sidePanel.setBackground(new Color(17, 17, 215));
        mainPanel.setBackground(digicelColor);
        // #endregion

            break;

        case 2:
            flowLogoIcon = new ImageIcon(new ImageIcon(adminGui.class.getResource("/OOPproject/Images/Flow-Logo.png"))
                    .getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
            Logo = new JLabel(flowLogoIcon);

            // #region set Panel Backgrounds
        sidePanel.setBackground(new Color(17, 147, 215));
        mainPanel.setBackground(flowColor);
        // #endregion

            break;
        default:
            break;
        }
        // Assigns default image to variable

        addCreateUserButton();

        // sets layout to be null, to allow for free placement of JAttributes
        sidePanel.setLayout(null);
        mainPanel.setLayout(null);
        // #endregion

        // #region Calling other methods to build ui

        addCreateUserButton();
        addViewAllCustomerButtonButton();
        addCreateCreditButton();
        addViewCreditButton();
        guiElements.addExitButton();
        guiElements.exitButton.setBounds(755, 0, 45, 45);
        mainPanel.add(guiElements.exitButton);

        // #endregion

        // adds Logo to top of panel
        sidePanel.add(Logo);

        // Sets size and location of picLabel in fomat of ( x, y, width, height)
        Logo.setBounds(0, 0, 200, 150);

        // #endregion

        // adds created panels to main Panel
        basePanel.add(sidePanel, BorderLayout.EAST);
        sidePanel.setBounds(0, 0, 200, 600);
        basePanel.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBounds(200, 0, 800, 600);

        // #region Enables Undecorated Frame drag movement
        guiElements.FrameDragListener frameDragListener = new guiElements.FrameDragListener(frame);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);
        // #endregion

    }

    public void createPanel() {

        basePanel = new JPanel();
        // adds Base panel to the background frame for everything else to be mounted to
        frame.add(basePanel);

        basePanel.setBounds(0, 0, panelw, panelh);
        basePanel.setLayout(new GridLayout());
        basePanel.setBackground(Color.black);

    }

    public void createFrame() throws IOException {

        frame = new JFrame();

        // Sets the default opereaction when the exit button is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Sets layout to null to allow free placement
        frame.setLayout(null);

        frame.setBackground(null);
        // Calls Creat Panel Function to create and add base panel
        createPanel();

        // Removes title bar, rounds the edge of the frame and sets default size
        frame.setUndecorated(true);
        frame.setShape(new RoundRectangle2D.Double(0, 0, panelw, panelh, 30, 30));
        frame.setSize(panelw, panelh);
        // sets location of the frame to the center of the screen
        frame.setLocationRelativeTo(null);
        // makes frame visible
        frame.setVisible(true);

    }

    public void addCreateUserButton() {

        createUserButton = new JButton("Create User");
        createUserButton.setBounds(30, 280, 140, uih);
        sidePanel.add(createUserButton);
        createUserButton.setOpaque(false);
        createUserButton.setFocusPainted(false);
        createUserButton.setContentAreaFilled(false);
        createUserButton.setForeground(Color.white);
        createUserButton.setFont(Oswald);
        createUserButton.setBorder(new guiElements.RoundedBorder(25));

    }

    public void addViewAllCustomerButtonButton() {

        viewAllCustomerButton = new JButton("View All User");
        viewAllCustomerButton.setBounds(30, 340, 140, uih);
        sidePanel.add(viewAllCustomerButton);
        viewAllCustomerButton.setOpaque(false);
        viewAllCustomerButton.setFocusPainted(false);
        viewAllCustomerButton.setContentAreaFilled(false);
        viewAllCustomerButton.setForeground(Color.white);
        viewAllCustomerButton.setFont(Oswald);
        viewAllCustomerButton.setBorder(new guiElements.RoundedBorder(25));

    }

    public void addCreateCreditButton() {

        createCreditButton = new JButton("Create Credit");
        createCreditButton.setBounds(30, 400, 140, uih);
        sidePanel.add(createCreditButton);
        createCreditButton.setOpaque(false);
        createCreditButton.setFocusPainted(false);
        createCreditButton.setContentAreaFilled(false);
        createCreditButton.setForeground(Color.white);
        createCreditButton.setFont(Oswald);
        createCreditButton.setBorder(new guiElements.RoundedBorder(25));

    }

    public void addViewCreditButton() {

        viewCreditButton = new JButton("View Credit");
        viewCreditButton.setBounds(30, 460, 140, uih);
        sidePanel.add(viewCreditButton);
        viewCreditButton.setOpaque(false);
        viewCreditButton.setFocusPainted(false);
        viewCreditButton.setContentAreaFilled(false);
        viewCreditButton.setForeground(Color.white);
        viewCreditButton.setFont(Oswald);
        viewCreditButton.setBorder(new guiElements.RoundedBorder(25));

    }

}
