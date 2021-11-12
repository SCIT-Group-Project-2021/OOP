//============================================================================
// File Name   : LoginGui.java
// Author      : Gabriel Tickle Garcia
// ID#         : 2007394
// Description : Primary LoginGui class
//============================================================================

package OOPproject.guiPKG;

import java.awt.*;
import java.awt.event.*;

import java.text.ParseException;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.text.MaskFormatter;
import java.awt.geom.RoundRectangle2D;

import OOPproject.teleCompanyPKG.*;

public class LoginGui {

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

    private static JPanel imagePanel;
    private static JPanel mainPanel;
    private static JPanel loginPanel;

    private static JLabel welcomeLabel;
    private static JLabel welcomeCustomerLabel;
    private static JLabel picLabel;

    private static Icon tech;
    private static Icon server;

    private static JTextField phoneText;
    private static JTextField userText;
    private static JTextArea adminSwap;

    private static JComboBox<String> providerBox;
    private static JPasswordField passwordText;

    private static JToggleButton adminButton;
    private static JButton loginButton;

    private static String[] providers = { "Digicel", "Flow" };
    // #endregion

    public LoginGui(JFrame frame) {
        frame.setShape(new RoundRectangle2D.Double(0, 0, panelw, panelh, 30, 30));
        frame.setSize(panelw, panelh);
        createPanel(frame);

        // #region Are for variables to be assigned
        // Shoul i just leave this as the values and remove the individual rgb constants
        // or not?
        userPicColor = new Color(207, 209, 219);
        userLogColor = new Color(190, 71, 11);

        adminPicColor = new Color(216, 227, 241);
        adminLogColor = new Color(58, 87, 128);

        Oswald = new Font("Oswald", Font.TYPE1_FONT, 15);

        tech = new ImageIcon(new ImageIcon(MainGUI.class.getResource("/OOPproject/Images/tech6.png")).getImage()
                .getScaledInstance(500, 550, Image.SCALE_DEFAULT));
        server = new ImageIcon(new ImageIcon(MainGUI.class.getResource("/OOPproject/Images/server6.png")).getImage()
                .getScaledInstance(500, 380, Image.SCALE_DEFAULT));

        loginPanel.setLayout(new GridLayout(1, 2));

        imagePanel = new JPanel();
        mainPanel = new JPanel();

        // Assigns default image to variable
        picLabel = new JLabel(tech);

        // Sets size and location of picLabel in fomat of ( x, y, width, height)
        picLabel.setBounds(0, 0, 500, 600);

        // adds created panels to main Panel
        loginPanel.add(imagePanel);
        loginPanel.add(mainPanel);

        // sets layout to be null, to allow for free placement of JAttributes
        imagePanel.setLayout(null);
        mainPanel.setLayout(null);
        // #endregion

        // #region Calling other methods to build ui
        // Calls Function To create and add Exit Button
        GuiElements.addExitButton();
        GuiElements.exitButton.setBounds(455, 0, 45, 45);
        mainPanel.add(GuiElements.exitButton);

        // Calls Function To create and add User Login interface
        addUserLogin();

        // Calls Function To create and add Admin functionalities to the user interface
        addAdminLogin();

        // Calls Function To create and add Login button
        addLoginButton(frame);
        // #endregion

        // #region For welcomeLabel Message
        // Creates and defines welcomeLabel! message
        welcomeLabel = new JLabel("WELCOME", SwingConstants.CENTER);
        welcomeLabel.setBounds(150, 150, 200, 50);
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setFont(new Font("Oswald", Font.TYPE1_FONT, 34));

        welcomeCustomerLabel = new JLabel("CUSTOMER!", SwingConstants.CENTER);
        welcomeCustomerLabel.setBounds(150, 200, 200, 50);
        welcomeCustomerLabel.setForeground(Color.white);
        welcomeCustomerLabel.setFont(new Font("Oswald", Font.TYPE1_FONT, 34));

        // adds welcomeLabel! message and adds picture to left panel
        mainPanel.add(welcomeLabel);
        mainPanel.add(welcomeCustomerLabel);
        imagePanel.add(picLabel);
        // #endregion

        // #region set Panel Backgrounds
        imagePanel.setBackground(userPicColor);
        mainPanel.setBackground(userLogColor);
        // #endregion

        frame.add(loginPanel);

        

    }

    public void createPanel(JFrame frame) {

        loginPanel = new JPanel();
        // adds Base panel to the background frame for everything else to be mounted to

        loginPanel.setBounds(0, 0, panelw, panelh);
        loginPanel.setLayout(null);

    }

    public void addUserLogin() {

        // #region Phone Number input Box Setup
        MaskFormatter fmt;
        try {
            fmt = new MaskFormatter("876-###-####");
            fmt.setPlaceholder("876-111-1111");
            phoneText = new JFormattedTextField(fmt);
            //phoneText.setText("876-111-1111");
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        phoneText.setText("User Phone number");
        phoneText.setBounds(125, 270, 250, uih);
        phoneText.setHorizontalAlignment(SwingConstants.CENTER);
        phoneText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        phoneText.setBackground(null);
        phoneText.setForeground(Color.white);
        phoneText.setFont(Oswald);
        phoneText.setCaretColor(Color.white);
        phoneText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneText.getText().equals("876-111-1111")) {
                    phoneText.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

               /* if (phoneText.getText().equals("876-   -    ")) {
                    phoneText.setText("876-111-1111");

                }*/

            }

        });
        // #endregion

        // #region Username input Box Setup
        userText = new JTextField(25);
        userText.setText("User Last Name");
        userText.setBounds(125, 350, 250, uih);
        userText.setHorizontalAlignment(SwingConstants.CENTER);
        userText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        userText.setBackground(null);
        userText.setForeground(Color.white);
        userText.setFont(Oswald);
        userText.setCaretColor(Color.white);
        userText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userText.getText().equals("User Last Name")) {
                    userText.setText(null);
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userText.getText().equals("")) {
                    userText.setText("User Last Name");

                }

            }

        });
        // #endregion

        // #region Password input Box Setup
        passwordText = new JPasswordField("Password");
        passwordText.setBounds(125, 370, 250, uih);
        passwordText.setVisible(false);
        passwordText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        passwordText.setBackground(null);
        passwordText.setForeground(Color.white);
        passwordText.setFont(Oswald);
        passwordText.setCaretColor(Color.white);
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

        //mainPanel.add(phoneText);
        //mainPanel.add(userText);
        mainPanel.add(passwordText);

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
        adminButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Adds Functionality to the button
        adminButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {

                adminPressed(itemEvent);

            }
        });

        // Creates Combo Box to select Service Provider account
        providerBox = new JComboBox<String>(providers);
        // Sets the ComboBox to the style of the ui
        providerBox.setBounds(125, 270, 250, uih);
        providerBox.setVisible(false);
        providerBox.setOpaque(false);
        providerBox.setBackground(null);
        providerBox.setForeground(Color.white);
        providerBox.setFont(oswald_Small);
        // providerBox.setBorder(new RoundedBorder(25));

        // Adds the previous attributes to the login panel
        mainPanel.add(providerBox);
        mainPanel.add(adminButton);
        mainPanel.add(adminSwap);

    }

    public void addLoginButton(JFrame frame) {

        loginButton = new JButton("Login");
        loginButton.setBounds(200, 340, 100, uih);
        mainPanel.add(loginButton);
        loginButton.setOpaque(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setForeground(Color.white);
        loginButton.setFont(Oswald);
        loginButton.setBorder(new GuiElements.RoundedBorder(25));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (panelStatus == customer) {
                    
                    loginPanel.setVisible(false);
                    loginPanel.removeAll();
                    frame.remove(loginPanel);
                    new CustomerGui(/*provider,*/ frame);


                } else if (panelStatus == admin) {

                    int providor = providerBox.getSelectedIndex();
                    char[] passwordchar = passwordText.getPassword();
                    String password = String.valueOf(passwordchar);

                    switch (providor) {
                    case 0:
                        if (Digicel.login(password)) {
                            loginPanel.setVisible(false);
                            loginPanel.removeAll();
                            frame.remove(loginPanel);
                            new AdminGui(1, frame);

                        } else {
                            JOptionPane.showMessageDialog(frame, "Incorrect Password!","Unable to log in",JOptionPane.ERROR_MESSAGE);
                        }

                        break;

                    case 1:
                        if (Flow.login(password)) {
                            loginPanel.setVisible(false);
                            loginPanel.removeAll();
                            frame.remove(loginPanel);
                            new AdminGui(2, frame);

                        } else {

                            JOptionPane.showMessageDialog(frame, "Incorrect Password!","Unable to log in",JOptionPane.ERROR_MESSAGE);
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

            //phoneText.setVisible(false);
            //userText.setVisible(false);
            welcomeCustomerLabel.setVisible(false);
            passwordText.setVisible(true);
            passwordText.setBounds(125, 350, 250, uih);
            providerBox.setVisible(true);
            loginButton.setBounds(200, 440, 100, uih);

            picLabel.setBounds(-20, 0, 500, 600);
            imagePanel.setBackground(adminPicColor);
            mainPanel.setBackground(adminLogColor);

            passwordText.setText("Password");

            picLabel.setIcon(server);

            welcomeLabel.setText("Admin Panel");
            adminSwap.setText("Are you a Customer at either service providor?");

            panelStatus = admin;

        } else {
            //phoneText.setVisible(true);
            //userText.setVisible(true);
            welcomeCustomerLabel.setVisible(true);
            passwordText.setVisible(false);
            passwordText.setBounds(125, 370, 250, uih);
            providerBox.setVisible(false);
            loginButton.setBounds(200, 340, 100, uih);

            picLabel.setBounds(0, 0, 500, 600);
            imagePanel.setBackground(userPicColor);
            mainPanel.setBackground(userLogColor);

            passwordText.setText("Password");

            picLabel.setIcon(tech);

            welcomeLabel.setText("WELCOME");
            adminSwap.setText("Are you an Admin user at either service providor?");

            panelStatus = customer;
        }

    }

}
