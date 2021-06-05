package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;

public class requestAdminGUI extends JPanel {

    // Customise font and size for submit button
    Font submitFont = new Font("Avenir", Font.PLAIN, 15);
    Font headingFont = new Font("Avenir", Font.PLAIN, 50);

    // Import Swing components
    JFrame frame;
    JPanel panel;

    public requestAdminGUI() {
        frame = new JFrame();

        JLabel requestHeading = new JLabel("Admin Requests");
        requestHeading.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        requestHeading.setFont(headingFont);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
//        panel.setPreferredSize(new Dimension(1380, 1050));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 80));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(requestHeading);

        add(panel);
    }
}
