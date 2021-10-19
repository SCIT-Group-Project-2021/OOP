package OOPproject;
import OOPproject.adminGui;
import OOPproject.customerGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout; 
import java.awt.GridLayout; 
import java.awt.event.*; 

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;



public class mainGUI {

    final int panelw = 1000;
    final int panelh = 600;
    final int uih = 25;

    private static JFrame frame;
    private static JPanel imagePanel;
    private static JPanel loginPanel;
    private static JPanel basePanel;
    private static JComboBox providerBox;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JTextField phoneText;
    private static JToggleButton adminButton;
    private static JButton loginButton;
    private static JLabel Welcome;

    

    public mainGUI()  {

        String[] providors = {"Digicel", "Flow"};

        frame = new JFrame();
        basePanel = new JPanel();
        imagePanel = new JPanel();
        loginPanel = new JPanel();

        frame.setIconImage(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("");
        frame.setLayout(null);

        adminButton = new JToggleButton("Admin");
        adminButton.setBounds(450, 500, 100, uih);
        adminButton.addItemListener (new ItemListener() { 
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
 
                adminPressed(itemEvent);
                
            } 
          } );

        frame.add(adminButton);
        
        frame.add(basePanel);
        basePanel.setBounds(0, 0, panelw, panelh);
        basePanel.setLayout(new GridLayout(1,2));
        
        basePanel.add(imagePanel);
        basePanel.add(loginPanel);
        loginPanel.setLayout(null);

        phoneText = new JTextField("User Phone number", 25);
        phoneText.setBounds(125, 285, 250, uih);
        phoneText.addFocusListener  (new FocusListener () {
            @Override
            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub
                phoneText.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
                if (phoneText.getText() != null) {
                    phoneText.setText("User Phone number");

                }
                
            } 
            
          } );

        providerBox = new JComboBox(providors);
        providerBox.setBounds(125, 240, 250, uih);
        providerBox.setVisible(false);

        passwordText = new JPasswordField("Password", 25);
        passwordText.setBounds(125, 300, 250, uih);
        passwordText.setVisible(false);
        passwordText.addFocusListener  (new FocusListener () {
            @Override
            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub
                passwordText.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
                if (passwordText.getText() != null) {
                    passwordText.setText("Password");
                }
                
            } 
            
          } );

        loginButton = new JButton("Login");
        loginButton.setBounds(200, 350, 100, uih);

        Welcome = new JLabel("Welcome!");
        Welcome.setBounds(200, 250, 80, uih);

        loginPanel.add(Welcome);
        loginPanel.add(phoneText);
        loginPanel.add(providerBox);
        loginPanel.add(passwordText);
        loginPanel.add(loginButton);

        imagePanel.setBackground(Color.white);
        loginPanel.setBackground(Color.blue); 

        frame.setUndecorated(true);
        frame.setSize(panelw, panelh);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }



    protected void adminPressed(ItemEvent itemEvent) {

        int state = itemEvent.getStateChange();
        
        if (state == ItemEvent.SELECTED) {
            phoneText.setVisible(false);
            providerBox.setVisible(true);
            passwordText.setVisible(true);
            Welcome.setVisible(false);
            adminButton.setText("User");
        }else{
            phoneText.setVisible(true);
            providerBox.setVisible(false);
            passwordText.setVisible(false);
            Welcome.setVisible(true);
            adminButton.setText("Admin");
        }

            
        
    }

    

}
