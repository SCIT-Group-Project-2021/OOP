//============================================================================
// File Name   : AdminGui.java
// Author      : Gabriel Tickle Garcia
// ID#         : 2007394
// Description : Primary AdminGui class
//============================================================================

package OOPproject.guiPKG;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import OOPproject.teleCompanyPKG.Flow;
import OOPproject.teleCompanyPKG.InvalidTelephoneNumber;
import OOPproject.teleCompanyPKG.ServiceProvider;
import OOPproject.teleCompanyPKG.Telephone;
import OOPproject.teleCompanyPKG.UniqueValueException;
import OOPproject.teleCompanyPKG.Digicel;
import OOPproject.teleCompanyPKG.Customer;

//Changed file name to fix mainGUI issue
public class AdminGui {

    // #region Constants

    final int panelw = 1000;
    final int panelh = 600;
    final int uih = 25;
    final int spButtons = 220;
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
    private static Font Oswaldmini;

    private static JPanel sidePanel;
    private static JPanel primaryPanel;
    private static JPanel generalPanel;
    private static JPanel adminPanel;

    private static JLabel Logo;

    private static Icon digicelLogoIcon;
    private static Icon flowLogoIcon;

    private static JButton createUserButton;
    private static JButton viewAllCustomerButton;
    private static JButton createCreditButton;
    private static JButton viewCreditButton;
    private static JButton addUserButton;

    private static JTextField customerIdText;
    private static JTextArea addressText;
    private static JTextField phoneText;
    private static JTextField lastNameText;

    private int phoneProvider;
    private JFrame parentFrame;

    ServiceProvider adminUser;
    // #endregion

    public AdminGui(int provider, JFrame frame) {

        createPanel();
        parentFrame = frame;
        phoneProvider = provider;
        // #region Are for variables to be assigned
        // Shoul i just leave this as the values and remove the individual rgb constants
        // or not?

        flowColor = new Color(48, 60, 120);
        digicelColor = new Color(250, 253, 255); //250, 253, 255
        
        Oswald = new Font("Oswald", Font.TYPE1_FONT, 15);
        Oswaldmini = new Font("Oswald", Font.PLAIN, 15);

        // #endregion

        sidePanel = new JPanel();
        primaryPanel = new JPanel();

        switch (provider) {
        case 1:
            digicelLogoIcon = new ImageIcon(
                    new ImageIcon(AdminGui.class.getResource("/OOPproject/Images/Digicel-Logo.png")).getImage()
                            .getScaledInstance(100, 60, Image.SCALE_DEFAULT));
            Logo = new JLabel(digicelLogoIcon);
            adminUser = new Digicel();
            // #region set Panel Backgrounds
            sidePanel.setBackground(Color.gray);
            primaryPanel.setBackground(digicelColor);
            // #endregion

            break;

        case 2:
            flowLogoIcon = new ImageIcon(new ImageIcon(AdminGui.class.getResource("/OOPproject/Images/Flow-Logo.png"))
                    .getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
            Logo = new JLabel(flowLogoIcon);

            adminUser = new Flow();

            // #region set Panel Backgrounds
            sidePanel.setBackground(new Color(65, 108, 163));
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
        addViewAllCustomerButton();
        addCreateCreditButton();
        addViewCreditButton();
        guiElements.addExitButton();
        guiElements.exitButton.setBounds(755, 0, 45, 45);
        primaryPanel.add(guiElements.exitButton);
        // TODO Change UI Color to be correct
        guiElements.exitButton.setForeground(Color.black);

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
        
        generalPanel = new JPanel();
        generalPanel.setLayout(null);
        generalPanel.setPreferredSize(new Dimension(700,500));
        generalPanel.setBounds(50, 50, 700, 500);
        generalPanel.setOpaque(false);

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

        createUserButton = new JButton("CREATE USER");
        createUserButton.setBounds(0, spButtons, 200, 50);
        sidePanel.add(createUserButton);
        createUserButton.setOpaque(false);
        createUserButton.setFocusPainted(false);
        createUserButton.setContentAreaFilled(false);
        createUserButton.setForeground(Color.white);
        createUserButton.setFont(Oswald);
        //createUserButton.setBorder(new guiElements.RoundedBorder(25));
        createUserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createUserButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNewUserForm();
            }
        });
    }

    public void addViewAllCustomerButton() {

        viewAllCustomerButton = new JButton("VIEW ALL USERS");
        viewAllCustomerButton.setBounds(0, spButtons+60, 200, 50);
        sidePanel.add(viewAllCustomerButton);
        viewAllCustomerButton.setOpaque(false);
        viewAllCustomerButton.setFocusPainted(false);
        viewAllCustomerButton.setContentAreaFilled(false);
        viewAllCustomerButton.setForeground(Color.white);
        viewAllCustomerButton.setFont(Oswald);
        //viewAllCustomerButton.setBorder(new guiElements.RoundedBorder(25));
        viewAllCustomerButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        viewAllCustomerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        viewAllCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllUsersTable();
            }
        });
    }

    public void addCreateCreditButton() {

        createCreditButton = new JButton("CREATE CREDIT");
        createCreditButton.setBounds(0, spButtons+120, 200, 50);
        sidePanel.add(createCreditButton);
        createCreditButton.setOpaque(false);
        createCreditButton.setFocusPainted(false);
        createCreditButton.setContentAreaFilled(false);
        createCreditButton.setForeground(Color.white);
        createCreditButton.setFont(Oswald);
        //createCreditButton.setBorder(new guiElements.RoundedBorder(25));
        createCreditButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        

    }

    public void addViewCreditButton() {
        viewCreditButton = new JButton("VIEW CREDIT");
        viewCreditButton.setBounds(0, spButtons+180, 200, 50);
        sidePanel.add(viewCreditButton);
        viewCreditButton.setOpaque(false);
        viewCreditButton.setFocusPainted(false);
        viewCreditButton.setContentAreaFilled(false);
        viewCreditButton.setForeground(Color.white);
        viewCreditButton.setFont(Oswald);
        //viewCreditButton.setBorder(new guiElements.RoundedBorder(25));
        viewCreditButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
    }

    public void showAllUsersTable() {
        
        generalPanel.removeAll();
        generalPanel.revalidate();
        generalPanel.repaint();
        primaryPanel.remove(generalPanel);
        primaryPanel.repaint();
        
        //getCustID() + "\t" + c.getName() + "\t" + c.getCreditBalance() + "\t" + c.getTelephone() + "\t" +  c.getAddress() +  "\n";	
        String columns[] = {"Customer ID", "Name", "Credit Balance", "Telephone", "Address"};
        String data[][];
        data = adminUser.viewCustomerBase();

        System.out.println("In Table method " + data[2][1]);
         
        JTable jt=new JTable(data,columns);    
        jt.setBounds(0,0,700,500);  
        jt.setShowGrid(true);
        jt.setShowVerticalLines(true);        
        JScrollPane sp=new JScrollPane();  

        sp.getViewport().add(jt);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);

        sp.setBounds(0,0,700,500);
        sp.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        jt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        
        jt.setBackground(null);
        jt.setShowGrid(false);
        jt.setShowHorizontalLines(true);

        jt.setFont(Oswaldmini);
        jt.setForeground(Color.gray);
        jt.getTableHeader().setFont(Oswald);
        jt.getTableHeader().setForeground(Color.gray);

        jt.setRowHeight(40);
        jt.setOpaque(false);
        jt.setEnabled(false);

        generalPanel.add(sp); 
        primaryPanel.add(generalPanel);
    }

    private void showNewUserForm() {

        MaskFormatter fmt;
        MaskFormatter phoneFMT;

        generalPanel.removeAll();
        generalPanel.revalidate();
        generalPanel.repaint();
        primaryPanel.remove(generalPanel);
        primaryPanel.repaint();

        try {
            fmt = new MaskFormatter("###-###-###");
            customerIdText = new JFormattedTextField(fmt);  

            phoneFMT = new MaskFormatter("876-###-####");
            phoneText = new JFormattedTextField(phoneFMT);
            phoneText.setText("876-000-0000");
        } 
        catch (ParseException e) {
            e.getStackTrace();
        }

        customerIdText.setBounds(60, 100, 200, 40);
        customerIdText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        customerIdText.setOpaque(false);
        customerIdText.setBackground(null);
        customerIdText.setForeground(Color.gray);
        customerIdText.setFont(Oswald);
        
        lastNameText = new JTextField(25);
        lastNameText.setText("User Last Name");
        lastNameText.setBounds(300, 100, 200, 40);
        lastNameText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        lastNameText.setOpaque(false);
        lastNameText.setBackground(null);
        lastNameText.setForeground(Color.gray);
        lastNameText.setFont(Oswald);
        lastNameText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (lastNameText.getText().equals("User Last Name")) {
                    lastNameText.setText(null);
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (lastNameText.getText().equals("")) {
                    lastNameText.setText("User Last Name");

                }

            }

        });

        //phoneText.setText("User Phone number");
        phoneText.setBounds(60, 180, 200, 40);
        phoneText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        phoneText.setOpaque(false);
        phoneText.setBackground(null);
        phoneText.setForeground(Color.gray);
        phoneText.setFont(Oswald);
        phoneText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneText.getText().equals("876-000-0000")) {
                    phoneText.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (phoneText.getText().equals("876-   -    ")) {
                    phoneText.setText("876-000-0000");
                    
                }
            }
        });

        addressText = new JTextArea(10, 10);
        addressText.setText("Address");
        addressText.setBounds(60, 270, 400, 100);
        addressText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        addressText.setOpaque(false);
        addressText.setBackground(null);
        addressText.setForeground(Color.gray);
        addressText.setFont(Oswald);
        addressText.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        addressText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (addressText.getText().equals("Address")) {
                    addressText.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (addressText.getText().equals("")) {
                    addressText.setText("Address");

                }
            }
        });

        addUserButton = new JButton("Add Customer");
        addUserButton.setBounds(225, 440, 250, 40);
        addUserButton.setOpaque(true);
        addUserButton.setFocusPainted(false);
        addUserButton.setContentAreaFilled(false);
        addUserButton.setForeground(Color.gray);
        addUserButton.setFont(Oswald);
        addUserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addUserButton.setBorder(new guiElements.RoundedBorder(25));
        
        generalPanel.add(customerIdText);
        generalPanel.add(lastNameText);
        generalPanel.add(phoneText);
        generalPanel.add(addressText);
        generalPanel.add(addUserButton);
       
        addressText.repaint();
        addUserButton.repaint();

        
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c;
                String returnString = "";
                if(!customerIdText.getText().equals("   -   -   ") && !lastNameText.getText().equals("User Last Name") && !addressText.getText().equals("Address") && !phoneText.getText().equals("876-000-0000")){
                    try{
                        c = new Customer(customerIdText.getText(), lastNameText.getText(), addressText.getText(), new Telephone(Integer.parseInt(phoneText.getText().substring(0,3)), Integer.parseInt(phoneText.getText().substring(4,7)), Integer.parseInt(phoneText.getText().substring(8,12)), phoneProvider));
                        returnString = adminUser.addCustomer(c);
                        if(returnString != ""){
                            JOptionPane.showMessageDialog(parentFrame,returnString + " "+ phoneText.getText(),"Form Error",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                        JOptionPane.showMessageDialog(parentFrame,"Information Saved!","Form Submitted",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    catch(InvalidTelephoneNumber e1){
                        JOptionPane.showMessageDialog(parentFrame,e1.getMessage(),"Form Error",JOptionPane.ERROR_MESSAGE);
                    }
                    catch(UniqueValueException e2){
                        JOptionPane.showMessageDialog(parentFrame,e2.getMessage(),"Form Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(parentFrame,"All fields must be filled","Form Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        primaryPanel.add(generalPanel);
    }
}
