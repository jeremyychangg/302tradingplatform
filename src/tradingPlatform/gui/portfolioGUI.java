package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;

public class portfolioGUI extends JPanel {

    public portfolioGUI() {
        // setting up black JPanel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 400, 960, 800));

        panel.setBackground(Color.BLACK);

        // adding blackJPanel
        add(panel);
    }
}
