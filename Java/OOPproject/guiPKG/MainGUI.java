//============================================================================
// File Name   : MainGUI.java
// Author      : Gabriel Tickle Garcia
// ID#         : 2007394
// Description : Primary GUI class
//============================================================================

package OOPproject.guiPKG;

import java.io.IOException;

import javax.swing.*;

public class MainGUI {

    // #region Constants

    final int panelw = 1000;
    final int panelh = 600;
    final int uih = 25;
    final int admin = 1;
    final int customer = 0;
    final int adminsetX = 140;
    final int adminsetY = 520;
    final int adminoffsetX = adminsetX + 60;
    final int adminoffsetY = adminsetY + 9;
    // #endregion

    // #region Variables

    private static JFrame frame;

    // #endregion

    public MainGUI() {

        // #region Try Catch block For frame creation
        // Calls Function To create main background Plate

        try {
            BaseFrame baseFrame = new BaseFrame();
            frame = baseFrame.getBaseFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // #endregion
        new LoginGui(frame);

    }

}
