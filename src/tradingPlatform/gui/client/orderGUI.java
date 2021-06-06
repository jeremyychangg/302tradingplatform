package tradingPlatform.gui.client;

import tradingPlatform.gui.common.Screen;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static tradingPlatform.gui.common.Screen.welcomeMessage;

public class orderGUI extends JPanel {
    public orderGUI() throws SQLException {
        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(1380, 1050));
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
        welcomeMessage(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, 0, Screen.border));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(panel);
    }
}
