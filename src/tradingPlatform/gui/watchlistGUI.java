package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;

public class watchlistGUI  extends JPanel {
    public watchlistGUI(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1380, 1050));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 80));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(panel);
    }
}
