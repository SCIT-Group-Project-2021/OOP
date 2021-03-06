//============================================================================
// File Name   : CustomerGui.java
// Author(s)   : Gabriel Tickle Garcia, Ashley Deans
// ID#(s)      : 2007394, 2007275
// Description : Displays customer side gui and its functions
//============================================================================

package OOPproject.guiPKG;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import OOPproject.teleCompanyPKG.Customer;
import OOPproject.teleCompanyPKG.InvalidMMICode;
import OOPproject.teleCompanyPKG.InvalidTelephoneNumber;
import OOPproject.teleCompanyPKG.InvalidVoucherNumber;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.awt.geom.RoundRectangle2D;

public class CustomerGui {
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

    //private static Color flowColor;
    //private static Color digicelColor;
    private static Color textColor;

    private static Font Oswald;

    private static JPanel sidePanel;
    private static JPanel primaryPanel;
    private static JPanel generalPanel;
    private static JPanel customerPanel;

    private static JLabel Logo;

    // private static Icon digicelLogoIcon;
    // private static Icon flowLogoIcon;
    private static Icon defaultLogoIcon;

    private static JButton addCreditButton;
    private static JButton checkBalanceButton;
    private static JButton LogOutButton;
    private static JButton useVoucherButton;
    private static JButton refreshCheckBalance;

    private static JTextField voucherNumText;
    private static JTextField balanceMMIText;

    private static JLabel addCreditLabel;
    private static JLabel balanceMMILabel;

    private JFrame parentFrame;

    Customer cus = new Customer();

    public CustomerGui(/*int provider,*/ JFrame frame){

        parentFrame = frame;

        frame.setShape(new RoundRectangle2D.Double(0, 0, panelw, panelh, 30, 30));
        frame.setSize(panelw, panelh);
        frame.setLayout(new BorderLayout());
        createPanel();
        // #region Are for variables to be assigned

        // Obsolete because customer side is now neutral (Independant of Service provider)
        // flowColor = new Color(48, 60, 120);
        // digicelColor = Color.decode("#F3f3f3");
        
        Oswald = new Font("Oswald", Font.TYPE1_FONT, 15);

        // #endregion
        sidePanel = new JPanel();
        primaryPanel = new JPanel();

        // Assigns default image to variable
        defaultLogoIcon = new ImageIcon();
        Logo = new JLabel(defaultLogoIcon);
        textColor = Color.decode("#303030");
        // #region set Panel Backgrounds
        sidePanel.setBackground(new Color(190, 71, 11));
        primaryPanel.setBackground(new Color(207, 209, 219));
            // #endregion

        /* Switch to check provider (Obsolete)
        switch (provider) {
            
        case 1:
            digicelLogoIcon = new ImageIcon(
                    new ImageIcon(AdminGui.class.getResource("/OOPproject/Images/Digicel-Logo.png")).getImage()
                            .getScaledInstance(100, 60, Image.SCALE_DEFAULT));
            Logo = new JLabel(digicelLogoIcon);
            textColor = Color.decode("#000000");
            // #region set Panel Backgrounds
            sidePanel.setBackground(Color.decode("#a5141f"));
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
            textColor = Color.decode("#ffffff");
            // #endregion

            break;
        default:
            defaultLogoIcon = new ImageIcon(
                    new ImageIcon(AdminGui.class.getResource("/OOPproject/Images/Tech6.png")).getImage()
                            .getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            Logo = new JLabel(defaultLogoIcon);
            textColor = Color.decode("#303030");
            // #region set Panel Backgrounds
            sidePanel.setBackground(new Color(190, 71, 11));
            primaryPanel.setBackground(new Color(207, 209, 219));
            // #endregion
            break;
        }*/

        // sets layout to be null, to allow for free placement of JAttributes
        sidePanel.setLayout(null);
        primaryPanel.setLayout(null);
        // #endregion

        // #region Calling other methods to build ui

        createAddCreditButton();
        createCheckBalanceButton();
        addLogOutButton(frame);
        GuiElements.addExitButton();
        GuiElements.exitButton.setBounds(755, 0, 45, 45);
        primaryPanel.add(GuiElements.exitButton);
        GuiElements.exitButton.setForeground(textColor);

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

        customerPanel.setBounds(0, 0, panelw, panelh);
        customerPanel.setLocation(0, 0);
        customerPanel.setSize(panelw, panelh);
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
                showAddVoucher();
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
                showCheckBalance();
            }
        });
    }

    public void showAddVoucher(){

        generalPanel.removeAll();
        generalPanel.revalidate();
        generalPanel.repaint();
        primaryPanel.remove(generalPanel);
        primaryPanel.repaint();

        try {
            //Use ' to specify maskformatter characters or it wont register properly
            MaskFormatter fmt;
            fmt = new MaskFormatter("'*121'*#############'*876#######'#");
            fmt.setValidCharacters("0123456789*#");
            fmt.setPlaceholder("*121*0000000000000*8760000000#");
            voucherNumText = new JFormattedTextField(fmt);  
        } 
        catch (ParseException e) {
            e.getStackTrace();
        }

        addCreditLabel = new JLabel("Add Credit MMI Code:");
        addCreditLabel.setBounds(250, 210, 200, 40);
        addCreditLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addCreditLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, textColor));
        addCreditLabel.setOpaque(false);
        addCreditLabel.setBackground(null);
        addCreditLabel.setForeground(textColor);
        addCreditLabel.setFont(Oswald);

        voucherNumText.setBounds(220, 240, 260, 40);
        voucherNumText.setHorizontalAlignment(SwingConstants.CENTER);
        voucherNumText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, textColor));
        voucherNumText.setOpaque(false);
        voucherNumText.setBackground(null);
        voucherNumText.setForeground(textColor);
        voucherNumText.setFont(Oswald);
        voucherNumText.setCaretColor(textColor);
        voucherNumText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (voucherNumText.getText().equals("*121*0000000000000*8760000000#")) {
                    voucherNumText.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                //Commented Out because im using Placeholder Text instead
                /*if (voucherNumText.getText().equals("             ")) {
                    voucherNumText.setText("0000000000000");
                    
                }*/
            }
        });
        
        useVoucherButton = new JButton("Use Voucher");
        useVoucherButton.setBounds(250, 440, 200, 40);
        useVoucherButton.setOpaque(true);
        useVoucherButton.setFocusPainted(false);
        useVoucherButton.setContentAreaFilled(false);
        useVoucherButton.setForeground(textColor);
        useVoucherButton.setFont(Oswald);
        useVoucherButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        useVoucherButton.setBorder(new GuiElements.RoundedBorder(25));
        
        generalPanel.add(voucherNumText);
        generalPanel.add(useVoucherButton);
        generalPanel.add(addCreditLabel);
        useVoucherButton.repaint();

        useVoucherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float creditBalance = 0;
                try {
                    creditBalance = cus.addCredit(voucherNumText.getText());
                    JOptionPane.showMessageDialog(parentFrame,"$" + creditBalance + "0 has been added to your balance!","Credit Added Successfully",JOptionPane.INFORMATION_MESSAGE);
                } catch (InvalidTelephoneNumber | InvalidVoucherNumber | InvalidMMICode e1) {
                    JOptionPane.showMessageDialog(parentFrame,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                } 
            }
        });

        primaryPanel.add(generalPanel);
    }

    public void showCheckBalance(){

        generalPanel.removeAll();
        generalPanel.revalidate();
        generalPanel.repaint();
        primaryPanel.remove(generalPanel);
        primaryPanel.repaint();
                
        refreshCheckBalance = new JButton("Check Balance");
        refreshCheckBalance.setBounds(250, 440, 200, 40); 
        refreshCheckBalance.setOpaque(true);
        refreshCheckBalance.setFocusPainted(false);
        refreshCheckBalance.setContentAreaFilled(false);
        refreshCheckBalance.setForeground(textColor);
        refreshCheckBalance.setFont(Oswald);
        refreshCheckBalance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        refreshCheckBalance.setBorder(new GuiElements.RoundedBorder(25));

        try {
            MaskFormatter fmt;
            fmt = new MaskFormatter("'*120'*876#######'#");
            fmt.setValidCharacters("0123456789*#");
            fmt.setPlaceholder("*120*8760000000#");
            balanceMMIText = new JFormattedTextField(fmt);  
        } 
        catch (ParseException e) {
            e.getStackTrace();
        }

        balanceMMILabel = new JLabel("Check Balance MMI Code:");
        balanceMMILabel.setBounds(250, 210, 200, 40);
        balanceMMILabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceMMILabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, textColor));
        balanceMMILabel.setOpaque(false);
        balanceMMILabel.setBackground(null);
        balanceMMILabel.setForeground(textColor);
        balanceMMILabel.setFont(Oswald);
        

        balanceMMIText.setBounds(250, 240, 200, 40);
        balanceMMIText.setHorizontalAlignment(SwingConstants.CENTER);
        balanceMMIText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, textColor));
        balanceMMIText.setOpaque(false);
        balanceMMIText.setBackground(null);
        balanceMMIText.setForeground(textColor);
        balanceMMIText.setFont(Oswald);
        balanceMMIText.setCaretColor(textColor);
        balanceMMIText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (balanceMMIText.getText().equals("*120*8760000000#")) {
                    balanceMMIText.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //Commented Out because im using Placeholder Text instead
                /*if (balanceMMIText.getText().equals("             ")) {
                    balanceMMIText.setText("*120*0000000000#");
                    
                }*/
            }
        });
        generalPanel.add(balanceMMIText);
        generalPanel.add(balanceMMILabel);
        generalPanel.add(refreshCheckBalance);
        
        
        refreshCheckBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               float creditBalance = 0;
               try {
                    creditBalance = cus.checkBalance(balanceMMIText.getText());
                    JOptionPane.showMessageDialog(parentFrame," Your balance is $"+creditBalance + "0.","Current Balance",JOptionPane.INFORMATION_MESSAGE);
                } 
                catch (InvalidMMICode | InvalidTelephoneNumber e1) {
                    JOptionPane.showMessageDialog(parentFrame,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

        primaryPanel.add(generalPanel);
    }
}

