//============================================================================
// File Name   : mainGUI.java
// Author      : Gabriel Tickle Garcia
// ID#         : 2007394
// Description : Primary GUI class
//============================================================================

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

import OOPproject.teleCompanyPKG.Digicel;
import OOPproject.teleCompanyPKG.Flow;
import OOPproject.guiPKG.guiElements;
import OOPproject.guiPKG.adminGui;

public class mainGUI {

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
    private static Color userPicColor;
    private static Color userLogColor;

    private static Color adminPicColor;
    private static Color adminLogColor;

    private static Font Oswald;

    private int panelStatus = 0;

    private static JFrame frame;

    private static JPanel imagePanel;
    private static JPanel loginPanel;
    private static JPanel basePanel;

    private static JLabel Welcome;
    private static JLabel picLabel;

    private static Image mainIcon;

    private static Icon tech;
    private static Icon server;

    private static JTextField phoneText;
    private static JTextField userText;
    private static JTextArea adminSwap;

    private static JComboBox<String> providerBox;
    private static JPasswordField passwordText;

    private static JToggleButton adminButton;
    private static JButton loginButton;

    private static String[] providors = { "Digicel", "Flow" };
    // #endregion

    public mainGUI() {

        // #region Are for variables to be assigned
        // Shoul i just leave this as the values and remove the individual rgb constants
        // or not?
        userPicColor = new Color(207, 209, 219);
        userLogColor = new Color(190, 71, 11);

        adminPicColor = new Color(216, 227, 241);
        adminLogColor = new Color(58, 87, 128);

        Oswald = new Font("Oswald", Font.TYPE1_FONT, 15);

        tech = new ImageIcon(new ImageIcon(mainGUI.class.getResource("/OOPproject/Images/tech6.png")).getImage()
                .getScaledInstance(500, 550, Image.SCALE_DEFAULT));
        server = new ImageIcon(new ImageIcon(mainGUI.class.getResource("/OOPproject/Images/server6.png")).getImage()
                .getScaledInstance(500, 380, Image.SCALE_DEFAULT));

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
        basePanel.setLayout(new GridLayout(1, 2));

        imagePanel = new JPanel();
        loginPanel = new JPanel();

        // Assigns default image to variable
        picLabel = new JLabel(tech);

        // Sets size and location of picLabel in fomat of ( x, y, width, height)
        picLabel.setBounds(0, 0, 500, 600);

        // adds created panels to main Panel
        basePanel.add(imagePanel);
        basePanel.add(loginPanel);

        // sets layout to be null, to allow for free placement of JAttributes
        imagePanel.setLayout(null);
        loginPanel.setLayout(null);
        // #endregion

        // #region Calling other methods to build ui
        // Calls Function To create and add Exit Button
        guiElements.addExitButton();
        guiElements.exitButton.setBounds(455, 0, 45, 45);
        loginPanel.add(guiElements.exitButton);

        // Calls Function To create and add User Login interface
        addUserLogin();

        // Calls Function To create and add Admin functionalities to the user interface
        addAdminLogin();

        // Calls Function To create and add Login button
        addLoginButton();
        // #endregion

        // #region For Welcome Message
        // Creates and defines Welcome! message
        Welcome = new JLabel("WELCOME!", SwingConstants.CENTER);
        Welcome.setBounds(150, 150, 200, 50);
        Welcome.setForeground(Color.white);
        Welcome.setFont(new Font("Oswald", Font.TYPE1_FONT, 34));

        // adds Welcome! message and adds picture to left panel
        loginPanel.add(Welcome);
        imagePanel.add(picLabel);
        // #endregion

        // #region set Panel Backgrounds
        imagePanel.setBackground(userPicColor);// was 222,235,252
        loginPanel.setBackground(userLogColor);
        // #endregion

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
        basePanel.setLayout(null);

    }

    public void createFrame() throws IOException {

        frame = new JFrame();

        mainIcon = ImageIO.read(getClass().getResource("/OOPproject/Images/tech2.png"));

        frame.setIconImage(mainIcon);

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

    public void addUserLogin() {

        // #region Phone Number input Box Setup
        MaskFormatter fmt;
        try {
            fmt = new MaskFormatter("876-###-####");
            phoneText = new JFormattedTextField(fmt);
            phoneText.setText("876-111-1111");
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        phoneText.setText("User Phone number");
        phoneText.setBounds(125, 250, 250, uih);
        phoneText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        phoneText.setBackground(null);
        phoneText.setForeground(Color.white);
        phoneText.setFont(Oswald);
        phoneText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneText.getText().equals("876-111-1111")) {
                    phoneText.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (phoneText.getText().equals("876-   -    ")) {
                    phoneText.setText("876-111-1111");

                }

            }

        });
        // #endregion

        // #region Username input Box Setup
        userText = new JTextField(25);
        userText.setText("User First Name");
        userText.setBounds(125, 310, 250, uih);
        userText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        userText.setBackground(null);
        userText.setForeground(Color.white);
        userText.setFont(Oswald);
        userText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userText.getText().equals("User First Name")) {
                    userText.setText(null);
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userText.getText().equals("")) {
                    userText.setText("User First Name");

                }

            }

        });
        // #endregion

        // #region Password input Box Setup
        passwordText = new JPasswordField("Password");
        passwordText.setBounds(125, 370, 250, uih);
        passwordText.setVisible(true);
        passwordText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        passwordText.setBackground(null);
        passwordText.setForeground(Color.white);
        passwordText.setFont(Oswald);
        passwordText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                char[] passwordchar = passwordText.getPassword();
                String password = String.valueOf(passwordchar);
                if (password.equals("Password")) {
                    passwordText.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                char[] passwordchar = passwordText.getPassword();
                String password = String.valueOf(passwordchar);
                if (password.equals("")) {
                    passwordText.setText("Password");
                }

            }

        });
        // #endregion

        loginPanel.add(phoneText);
        loginPanel.add(userText);
        loginPanel.add(passwordText);

    }

    public void addAdminLogin() {

        Font oswald_Small = new Font("Oswald", Font.TYPE1_FONT, 10);

        adminSwap = new JTextArea("Are you an admin user at either service providor?");

        adminSwap.setEditable(false);
        adminSwap.setBounds(adminsetX, adminsetY, 220, 150);

        // Sets text area so that the words wrap properly in the box
        adminSwap.setLineWrap(true);
        adminSwap.setWrapStyleWord(true);
        // Sets the text box to the style of the ui
        adminSwap.setOpaque(false);
        adminSwap.setFont(oswald_Small);
        adminSwap.setForeground(Color.white);
        adminSwap.setBackground(null);
        adminSwap.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        // Adds Functionality to the button
        adminButton = new JToggleButton("<HTML><U>Click Here</U></HTML>");
        adminButton.setBounds(adminoffsetX, adminoffsetY, 100, uih);
        // Sets the button to the style of the ui
        adminButton.setForeground(Color.white);
        adminButton.setFont(oswald_Small);
        adminButton.setBorderPainted(false);
        adminButton.setContentAreaFilled(false);
        adminButton.setFocusPainted(false);
        adminButton.setOpaque(false);

        // Adds Functionality to the button
        adminButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {

                adminPressed(itemEvent);

            }
        });

        // Creates Combo Bocx to select Service Providor account
        providerBox = new JComboBox<String>(providors);
        // Sets the ComboBox to the style of the ui
        providerBox.setBounds(125, 270, 250, uih);
        providerBox.setVisible(false);
        providerBox.setOpaque(false);
        providerBox.setBackground(null);
        providerBox.setForeground(Color.white);
        providerBox.setFont(oswald_Small);
        // providerBox.setBorder(new RoundedBorder(25));

        // Adds the previous attributes to the login panel
        loginPanel.add(providerBox);
        loginPanel.add(adminButton);
        loginPanel.add(adminSwap);

    }

    public void addLoginButton() {

        loginButton = new JButton("Login");
        loginButton.setBounds(200, 440, 100, uih);
        loginPanel.add(loginButton);
        loginButton.setOpaque(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setForeground(Color.white);
        loginButton.setFont(Oswald);
        loginButton.setBorder(new guiElements.RoundedBorder(25));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (panelStatus == customer) {

                    String username = userText.getText();
                    String phone = phoneText.getText();
                    char[] passwordchar = passwordText.getPassword();
                    String password = String.valueOf(passwordchar);

                    System.out.println("Customer Information\n" + "Username:\t" + username + "\n" + "Password:\t"
                            + password + "\n" + "Phone Number:\t" + phone);

                } else if (panelStatus == admin) {

                    int providor = providerBox.getSelectedIndex();
                    char[] passwordchar = passwordText.getPassword();
                    String password = String.valueOf(passwordchar);

                    switch (providor) {
                    case 0:
                        if (Digicel.login(password)) {
                            // TODO Login Successful
                            System.out.println("Successfuly logged in confirmed");
                            frame.setVisible(false);
                            new adminGui(1);
                            
                        } else {
                            // TODO Password was incorrect
                        }

                        break;

                    case 1:
                        if (Flow.login(password)) {
                            // TODO Login Successful
                            System.out.println("Successfuly logged in confirmed");
                            frame.setVisible(false);
                            new adminGui(2);
                            
                        } else {
                            // TODO Password was incorrect
                        }

                        break;

                    default:
                        break;
                    }

                }

            }

        });

    }

    protected void adminPressed(ItemEvent itemEvent) {

        int state = itemEvent.getStateChange();

        if (state == ItemEvent.SELECTED) {

            phoneText.setVisible(false);
            userText.setVisible(false);
            passwordText.setBounds(125, 350, 250, uih);
            providerBox.setVisible(true);

            picLabel.setBounds(-20, 0, 500, 600);
            imagePanel.setBackground(adminPicColor);
            loginPanel.setBackground(adminLogColor);

            passwordText.setText("Password");

            picLabel.setIcon(server);

            Welcome.setText("Admin Panel");
            adminSwap.setText("Are you a Customer at either service providor?");

            panelStatus = admin;

        } else {
            phoneText.setVisible(true);
            userText.setVisible(true);
            passwordText.setBounds(125, 370, 250, uih);
            providerBox.setVisible(false);

            picLabel.setBounds(0, 0, 500, 600);
            imagePanel.setBackground(userPicColor);
            loginPanel.setBackground(userLogColor);

            passwordText.setText("Password");

            picLabel.setIcon(tech);

            Welcome.setText("Welcome!");
            adminSwap.setText("Are you an Admin user at either service providor?");

            panelStatus = customer;
        }

    }

}
