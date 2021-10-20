package OOPproject;

import OOPproject.adminGui;
import OOPproject.customerGui;

import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;

import java.awt.Graphics;

import java.awt.Shape;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class mainGUI {

    final int panelw = 1000;
    final int panelh = 600;
    final int uih = 25;
    final int admin = 1;
    final int customer = 0;

    private static Font Oswald;

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

        Oswald = new Font("Oswald", Font.TYPE1_FONT, 15);

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

        MaskFormatter fmt;
        

        try {
            fmt = new MaskFormatter("###-###-####");
            DefaultFormatterFactory factory = new DefaultFormatterFactory(fmt);
            phoneText = new JFormattedTextField(fmt);
            //phoneText.set
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // phoneText = new JTextField(11);
        phoneText.setText("User Phone number");
        phoneText.setBounds(125, 250, 250, uih);
        phoneText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        phoneText.setBackground(null);
        phoneText.setForeground(Color.white);
        phoneText.setFont(Oswald);

        // phoneText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        // phoneText.setOpaque(false);

        phoneText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneText.getText().equals("User Phone number")) {
                    phoneText.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (phoneText.getText().equals("")) {
                    phoneText.setText("User Phone number");

                }

            }

        });

        userText = new JTextField(25);
        userText.setText("User First Name");
        userText.setBounds(125, 310, 250, uih);
        userText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        userText.setBackground(null);
        userText.setForeground(Color.white);
        userText.setFont(Oswald);

        // userText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        // phoneText.setOpaque(false);

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

        passwordText = new JPasswordField("Password");
        passwordText.setBounds(125, 370, 250, uih);
        passwordText.setVisible(true);
        passwordText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        passwordText.setBackground(null);
        passwordText.setForeground(Color.white);
        passwordText.setFont(Oswald);
        passwordText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordText.getText().equals("Password")) {
                    passwordText.setText(null);
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordText.getText().equals("")) {
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

        Font oswald_Small = new Font("Oswald", Font.TYPE1_FONT, 10);

        adminSwap = new JTextArea("Are You and admin user at either service providor?");
        adminSwap.setEditable(false);
        adminSwap.setBounds(adminsetX, adminsetY, 200, 150);
        adminSwap.setLineWrap(true);
        adminSwap.setWrapStyleWord(true);
        adminSwap.setOpaque(false);
        adminSwap.setFont(oswald_Small);
        adminSwap.setForeground(Color.white);
        adminSwap.setBackground(null);
        adminSwap.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        adminButton = new JToggleButton("<HTML><U>Admin</U></HTML>");
        adminButton.setBounds(adminoffsetX, adminoffsetY, 100, uih);
        adminButton.setForeground(Color.white);
        adminButton.setFont(oswald_Small);

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
        providerBox.setOpaque(false);
        providerBox.setBackground(null);
        providerBox.setForeground(Color.white);
        providerBox.setFont(oswald_Small);
        // providerBox.setBorder(new RoundedBorder(25));

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
        loginButton.setOpaque(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setForeground(Color.white);
        loginButton.setFont(Oswald);
        loginButton.setBorder(new RoundedBorder(25));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (panelStatus == customer) {

                    String username = userText.getText();
                    String phone = phoneText.getText();
                    String password = passwordText.getText();

                    System.out.println("Customer Information\n" + "Username:\t" + username + "\n" + "Password:\t"
                            + password + "\n" + "Phone Number:\t" + phone);

                } else if (panelStatus == admin) {

                    int providor = providerBox.getSelectedIndex();
                    String password = passwordText.getText();

                    switch (providor) {
                    case 0:

                        if (password.equals("TheBiggerBetterNetwork2021")) {
                            System.out.println("Successfully Logged in!");
                        } else {
                            System.out.println("Incorrect Password!");
                        }

                        System.out.println(
                                "Admin Information\n" + "Providor:\t" + "Digicel" + "\n" + "Password:\t" + password);

                        break;

                    case 1:

                        if (password.equals("TheWayIFlow2021")) {
                            System.out.println("Successfully Logged in!");
                        } else {
                            System.out.println("Incorrect Password!");
                        }

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

            passwordText.setText("Password");

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

            passwordText.setText("Password");

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

    private static class RoundedBorder implements Border {

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
    }

}
