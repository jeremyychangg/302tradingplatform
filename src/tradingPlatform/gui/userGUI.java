package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class userGUI extends JPanel {
    private JPanel panel;
    /**
     * @throws Exception
     */
    public userGUI() throws Exception {
        setUpPanel();

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
        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, 0, Screen.border));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }
}