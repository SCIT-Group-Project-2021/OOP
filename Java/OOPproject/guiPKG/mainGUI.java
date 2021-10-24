//============================================================================
// File Name   : mainGUI.java
// Author      : Gabriel Tickle Garcia
// ID#         : 2007394
// Description : Primary GUI class
//============================================================================

package OOPproject.guiPKG;

import java.io.IOException;

import javax.swing.*;

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

    private static JFrame frame;

    // #endregion

    public mainGUI() {

        // #region Try Catch block For frame creation
        // Calls Function To create main background Plate


        try {
            baseFrame baseFrame = new baseFrame();
            frame = baseFrame.getBaseFrame();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // #endregion

        new LoginGui(frame);


    }

}