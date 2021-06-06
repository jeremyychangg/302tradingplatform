package tradingPlatform.gui.server;

import tradingPlatform.gui.common.Screen;

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
        Screen.welcomeMessage(panel);
        add(panel);
    }


    /**
     * @throws SQLException
     */
    private void setUpPanel() throws SQLException {
        // setting up black JPanel
        this.panel = new JPanel();
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, 0, Screen.border));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }
}