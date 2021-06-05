package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static tradingPlatform.gui.Screen.welcomeMessage;

public class orderGUI extends JPanel {
    public orderGUI() throws SQLException {
        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(1380, 1050));
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
        welcomeMessage(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 80));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(panel);
    }
}
