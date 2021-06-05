package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class unitGUI extends JPanel {
    private JPanel panel;

    /**
     * @throws Exception
     */
    public unitGUI() throws Exception {
        setUpPanel();
//        JLabel requestHeading = new JLabel("Make A Request");
//        requestHeading.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        requestHeading.setFont(headingFont);

        /////////////////////////////////////////////////////
        //
        //
        //                  INSERT HERE
        //
        //
        ////////////////////////////////////////////////////
        Screen.welcomeMessage(panel);
        add(panel);
    }


    /**
     * @throws SQLException
     */
    private void setUpPanel() throws SQLException {
        // setting up blank JPanel
        this.panel = new JPanel();
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 80));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }
}