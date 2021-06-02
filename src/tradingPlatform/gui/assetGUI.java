package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class assetGUI extends JPanel {
    private JPanel panel;

    Font submitFont = new Font("Avenir", Font.PLAIN, 15);
    Font headingFont = new Font("Avenir", Font.PLAIN, 50);

    /**
     * @throws Exception
     */
    public assetGUI() throws Exception {
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
        // setting up black JPanel
        this.panel = new JPanel();
        panel.setPreferredSize(new Dimension(1380, 1050));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 80));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }
}