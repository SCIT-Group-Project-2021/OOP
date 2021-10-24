package OOPproject.guiPKG;

import java.awt.*;

import javax.swing.*;

public class AdminGui {

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

    private static JPanel sidePanel;
    private static JPanel primaryPanel;
    private static JPanel adminPanel;

    private static JLabel Logo;

    private static Icon digicelLogoIcon;
    private static Icon flowLogoIcon;

    private static JButton createUserButton;
    private static JButton viewAllCustomerButton;
    private static JButton createCreditButton;
    private static JButton viewCreditButton;

    // #endregion

    public AdminGui(int providor, JFrame frame) {

        createPanel();

        // #region Are for variables to be assigned
        // Shoul i just leave this as the values and remove the individual rgb constants
        // or not?

        flowColor = new Color(7, 116, 188);
        digicelColor = new Color(58, 27, 128);

        Oswald = new Font("Oswald", Font.TYPE1_FONT, 15);

        // #endregion

        sidePanel = new JPanel();
        primaryPanel = new JPanel();

        switch (providor) {
        case 1:
            digicelLogoIcon = new ImageIcon(
                    new ImageIcon(AdminGui.class.getResource("/OOPproject/Images/Digicel-Logo.png")).getImage()
                            .getScaledInstance(100, 60, Image.SCALE_DEFAULT));
            Logo = new JLabel(digicelLogoIcon);

            // #region set Panel Backgrounds
            sidePanel.setBackground(new Color(17, 17, 215));
            primaryPanel.setBackground(digicelColor);
            // #endregion

            break;

        case 2:
            flowLogoIcon = new ImageIcon(new ImageIcon(AdminGui.class.getResource("/OOPproject/Images/Flow-Logo.png"))
                    .getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
            Logo = new JLabel(flowLogoIcon);

            // #region set Panel Backgrounds
            sidePanel.setBackground(new Color(17, 147, 215));
            primaryPanel.setBackground(flowColor);
            // #endregion

            break;
        default:
            break;
        }
        // Assigns default image to variable

        addCreateUserButton();

        // sets layout to be null, to allow for free placement of JAttributes
        sidePanel.setLayout(null);
        primaryPanel.setLayout(null);
        // #endregion

        // #region Calling other methods to build ui

        addCreateUserButton();
        addViewAllCustomerButtonButton();
        addCreateCreditButton();
        addViewCreditButton();
        guiElements.addExitButton();
        guiElements.exitButton.setBounds(755, 0, 45, 45);
        primaryPanel.add(guiElements.exitButton);

        // #endregion

        // adds Logo to top of panel
        sidePanel.add(Logo);

        // Sets size and location of picLabel in fomat of ( x, y, width, height)
        Logo.setBounds(0, 0, 200, 150);

        // #endregion

        // adds created panels to main Panel
        sidePanel.setPreferredSize(new Dimension(200, 600));
        adminPanel.add(sidePanel, BorderLayout.WEST);

        primaryPanel.setPreferredSize(new Dimension(800, 600));
        adminPanel.add(primaryPanel, BorderLayout.CENTER);

        frame.add(adminPanel);

    }

    public void createPanel() {

        adminPanel = new JPanel();
        // adds Base panel to the background frame for everything else to be mounted to
        // frame.add(adminPanel);

        adminPanel.setBounds(0, 0, 1000, 600);
        adminPanel.setLayout(new BorderLayout());
        adminPanel.setBackground(Color.black);

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
