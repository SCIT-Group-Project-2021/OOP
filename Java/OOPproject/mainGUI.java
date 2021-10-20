package OOPproject;

import OOPproject.adminGui;
import OOPproject.customerGui;

import java.awt.geom.RoundRectangle2D;

import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

import java.awt.Graphics;

import java.awt.Shape;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class mainGUI {

    final int panelw = 1000;
    final int panelh = 600;
    final int uih = 25;
    final int admin = 1;
    final int customer = 0;

    private int panelStatus = 0;

    private static JFrame frame;

    private static JPanel imagePanel;
    private static JPanel loginPanel;
    private static JPanel basePanel;

    private static JLabel Welcome;
    private static JLabel picLabel;

    private static Icon tech;
    private static Icon server;

    private static JTextField phoneText;
    private static JTextField userText;
    private static JTextArea adminSwap;

    private static JComboBox providerBox;
    private static JPasswordField passwordText;

    private static JToggleButton adminButton;
    private static JButton loginButton;

    private static JButton exitButton;

    private static String[] providors = { "Digicel", "Flow" };

    public mainGUI() {

        tech = new ImageIcon("C:/Users/sptic/Desktop/tech1.png");
        server = new ImageIcon("C:/Users/sptic/Desktop/server1.png");

        createFrame();

        basePanel.setLayout(new GridLayout(1, 2));

        imagePanel = new JPanel();
        loginPanel = new JPanel();

        picLabel = new JLabel(tech);
        picLabel.setBounds(10, 10, 480, 580);

        basePanel.add(imagePanel);
        basePanel.add(loginPanel);

        imagePanel.setLayout(null);
        loginPanel.setLayout(null);

        addExitButton();

        addUserLogin();

        addAdminLogin();

        addLoginButton();

        Welcome = new JLabel("WELCOME!", SwingConstants.CENTER);
        Welcome.setBounds(150, 150, 200, 50);
        Welcome.setForeground(Color.white);
        Welcome.setFont(new Font("Oswald", Font.TYPE1_FONT, 34));

        loginPanel.add(Welcome);

        imagePanel.add(picLabel);

        imagePanel.setBackground(new Color(250, 245, 255));
        loginPanel.setBackground(new Color(120, 25, 255));

    }

    public void createPanel() {

        basePanel = new JPanel();
        frame.add(basePanel);

        basePanel.setBounds(0, 0, panelw, panelh);
        basePanel.setLayout(null);

    }

    public void createFrame() {

        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        frame.setBackground(null);
        createPanel();

        frame.setUndecorated(true);
        frame.setShape(new RoundRectangle2D.Double(0, 0, panelw, panelh, 30, 30));
        frame.setSize(panelw, panelh);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void addUserLogin() {

        phoneText = new JTextField(25);
        phoneText.setText("User Phone number");
        phoneText.setBounds(125, 250, 250, uih);

        phoneText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        // phoneText.setOpaque(false);

        phoneText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                phoneText.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (phoneText.getText() == null) {
                    phoneText.setText("User Phone number");

                }

            }

        });

        userText = new JTextField(25);
        userText.setText("User First Name");
        userText.setBounds(125, 310, 250, uih);

        userText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        // phoneText.setOpaque(false);

        userText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub
                userText.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
                if (userText.getText() == null) {
                    userText.setText("User First Name");

                }

            }

        });

        passwordText = new JPasswordField("Password");
        passwordText.setBounds(125, 370, 250, uih);
        passwordText.setVisible(true);
        passwordText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordText.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordText.getText() == null) {
                    passwordText.setText("Password");
                }

            }

        });

        loginPanel.add(phoneText);
        loginPanel.add(userText);
        loginPanel.add(passwordText);

    }

    final int adminsetX = 125;
    final int adminsetY = 470;
    final int adminoffsetX = adminsetX + 5;
    final int adminoffsetY = adminsetY + 9;

    public void addAdminLogin() {

        adminSwap = new JTextArea("Are You and admin user at either service providor?");
        adminSwap.setEditable(false);
        adminSwap.setBounds(adminsetX, adminsetY, 200, 150);
        adminSwap.setLineWrap(true);
        adminSwap.setWrapStyleWord(true);
        adminSwap.setOpaque(false);
        adminSwap.setFont(new Font("Oswald", Font.TYPE1_FONT, 10));
        adminSwap.setForeground(Color.white);
        adminSwap.setBackground(new Color(0, 0, 0, 0));
        adminSwap.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        adminButton = new JToggleButton("<HTML><U>Admin</U></HTML>");
        adminButton.setBounds(adminoffsetX, adminoffsetY, 100, uih);
        adminButton.setForeground(Color.white);
        adminButton.setFont(new Font("Oswald", Font.TYPE1_FONT, 10));

        adminButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {

                adminPressed(itemEvent);

            }
        });
        adminButton.setBorderPainted(false);
        adminButton.setContentAreaFilled(false);
        adminButton.setFocusPainted(false);
        adminButton.setOpaque(false);

        providerBox = new JComboBox(providors);
        providerBox.setBounds(125, 270, 250, uih);
        providerBox.setVisible(false);

        loginPanel.add(providerBox);

        loginPanel.add(adminButton);

        loginPanel.add(adminSwap);

    }

    public void addExitButton() {

        exitButton = new JButton("X");
        exitButton.setForeground(Color.white);
        exitButton.setBounds(455, 0, 45, 45);
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
        loginPanel.add(exitButton);

    }

    public void addLoginButton() {

        loginButton = new JButton("Login");
        loginButton.setBounds(125, 425, 100, uih);
        loginPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (panelStatus == customer) {

                    String username = userText.getText();
                    String phone = phoneText.getText();
                    String password = passwordText.getText();

                    System.out.println("Customer Information\n" + "Username:\t" + username + "\n" + "Password:\t"
                            + password + "\n"+ "Phone Number:\t" + phone);

                } else if (panelStatus == admin) {

                    int providor = providerBox.getSelectedIndex();
                    String password = passwordText.getText();

                    switch (providor) {
                    case 0:

                        System.out.println(
                                "Admin Information\n" + "Providor:\t" + "Digicel" + "\n" + "Password:\t" + password);

                        break;

                    case 1:

                        System.out.println(
                                "Admin Information\n" + "Providor:\t" + "Flow" + "\n" + "Password:\t" + password);

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

            picLabel.setIcon(server);

            // passwordText.setVisible(true);
            // Welcome.setVisible(false);
            Welcome.setText("Admin Panel");
            adminSwap.setText("Are you a Customer at either service providor?");
            adminButton.setText("<HTML><U>User</U></HTML>");

            panelStatus = admin;
        } else {
            phoneText.setVisible(true);
            userText.setVisible(true);
            passwordText.setBounds(125, 370, 250, uih);
            providerBox.setVisible(false);

            picLabel.setIcon(tech);

            // passwordText.setVisible(false);
            // Welcome.setVisible(true);
            Welcome.setText("Welcome!");
            adminButton.setText("<HTML><U>Admin</U></HTML>");
            adminSwap.setText("Are you an Admin user at either service providor?");

            panelStatus = customer;
        }

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
    }

}
