package OOPproject.guiPKG;

import javax.swing.*;
import OOPproject.teleCompanyPKG.Customer;
import java.awt.*;
import java.awt.event.*;

// TODO uncomment this mess of warnings
/*import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import OOPproject.teleCompanyPKG.Flow;
import OOPproject.teleCompanyPKG.InvalidTelephoneNumber;
import OOPproject.teleCompanyPKG.ServiceProvider;
import OOPproject.teleCompanyPKG.Telephone;
import OOPproject.teleCompanyPKG.UniqueValueException;
import OOPproject.teleCompanyPKG.Digicel;
import OOPproject.teleCompanyPKG.Customer;*/


public class customerGui {

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

    private static JPanel sidePanel;
    private static JPanel primaryPanel;
    private static JPanel generalPanel;
    private static JPanel customerPanel;

    private static JLabel Logo;

    private static Icon digicelLogoIcon;
    private static Icon flowLogoIcon;

    private static JButton addCreditButton;
    private static JButton checkBalanceButton;
    private static JButton LogOutButton;

    // TODO put these to use, pass values to other functions and frame fore dialog box
    private int phoneProvider;
    private JFrame parentFrame;


    public customerGui(int provider, JFrame frame, Customer c){
        createPanel();
        parentFrame = frame;
        phoneProvider = provider;
        // #region Are for variables to be assigned
        // Shoul i just leave this as the values and remove the individual rgb constants
        // or not?

        flowColor = new Color(48, 60, 120);
        digicelColor = new Color(250, 253, 255); //250, 253, 255
        
        Oswald = new Font("Oswald", Font.TYPE1_FONT, 15);

        // #endregion

        sidePanel = new JPanel();
        primaryPanel = new JPanel();

        // Assigns default image to variable

        switch (provider) {
        case 1:
            digicelLogoIcon = new ImageIcon(
                    new ImageIcon(AdminGui.class.getResource("/OOPproject/Images/Digicel-Logo.png")).getImage()
                            .getScaledInstance(100, 60, Image.SCALE_DEFAULT));
            Logo = new JLabel(digicelLogoIcon);
            // #region set Panel Backgrounds
            sidePanel.setBackground(Color.gray);
            primaryPanel.setBackground(digicelColor);
            // #endregion

            break;

        case 2:
            flowLogoIcon = new ImageIcon(new ImageIcon(AdminGui.class.getResource("/OOPproject/Images/Flow-Logo.png"))
                    .getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
            Logo = new JLabel(flowLogoIcon);

            // #region set Panel Backgrounds
            sidePanel.setBackground(new Color(65, 108, 163));
            primaryPanel.setBackground(flowColor);
            // #endregion

            break;
        default:
            break;
        }

        // sets layout to be null, to allow for free placement of JAttributes
        sidePanel.setLayout(null);
        primaryPanel.setLayout(null);
        // #endregion

        // #region Calling other methods to build ui

        createAddCreditButton();
        createCheckBalanceButton();
        addLogOutButton(frame);
        guiElements.addExitButton();
        guiElements.exitButton.setBounds(755, 0, 45, 45);
        primaryPanel.add(guiElements.exitButton);
        guiElements.exitButton.setForeground(Color.black);

        // #endregion

        // adds Logo to top of panel
        sidePanel.add(Logo);

        // Sets size and location of picLabel in fomat of ( x, y, width, height)
        Logo.setBounds(0, 0, 200, 150);

        // #endregion

        // adds created panels to main Panel
        sidePanel.setPreferredSize(new Dimension(200, 600));
        customerPanel.add(sidePanel, BorderLayout.WEST);

        primaryPanel.setPreferredSize(new Dimension(800, 600));
        customerPanel.add(primaryPanel, BorderLayout.CENTER);

        frame.add(customerPanel);
        
        generalPanel = new JPanel();
        generalPanel.setLayout(null);
        generalPanel.setPreferredSize(new Dimension(700,500));
        generalPanel.setBounds(50, 50, 700, 500);
        generalPanel.setOpaque(false);

    }

    public void createPanel() {

        customerPanel = new JPanel();
        // adds Base panel to the background frame for everything else to be mounted to
        // frame.add(customerPanel);

        customerPanel.setBounds(0, 0, 1000, 600);
        customerPanel.setLayout(new BorderLayout());
        customerPanel.setBackground(Color.black);

    }

    public void createAddCreditButton() {

        addCreditButton = new JButton("ADD CREDIT");
        addCreditButton.setBounds(0, spButtons+60, 200, 50);
        sidePanel.add(addCreditButton);
        addCreditButton.setOpaque(false);
        addCreditButton.setFocusPainted(false);
        addCreditButton.setContentAreaFilled(false);
        addCreditButton.setForeground(Color.white);
        addCreditButton.setFont(Oswald);
        //addCreditButton.setBorder(new guiElements.RoundedBorder(25));
        addCreditButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addCreditButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        addCreditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // showNewUserForm();
            }
        });
    }

    public void addLogOutButton(JFrame frame) {

        LogOutButton = new JButton("LOGOUT");
        LogOutButton.setBounds(0, spButtons+180, 200, 50);
        sidePanel.add(LogOutButton);
        LogOutButton.setOpaque(false);
        LogOutButton.setFocusPainted(false);
        LogOutButton.setContentAreaFilled(false);
        LogOutButton.setForeground(Color.white);
        LogOutButton.setFont(Oswald);
        LogOutButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        LogOutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        LogOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(customerPanel);;
                new LoginGui(frame);
            }
        });
    }

    public void createCheckBalanceButton() {

        checkBalanceButton = new JButton("CHECK BALANCE");
        checkBalanceButton.setBounds(0, spButtons+120, 200, 50);
        sidePanel.add(checkBalanceButton);
        checkBalanceButton.setOpaque(false);
        checkBalanceButton.setFocusPainted(false);
        checkBalanceButton.setContentAreaFilled(false);
        checkBalanceButton.setForeground(Color.white);
        checkBalanceButton.setFont(Oswald);
        //checkBalanceButton.setBorder(new guiElements.RoundedBorder(25));
        checkBalanceButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        checkBalanceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  showAllUsersTable();
            }
        });
    }

}

