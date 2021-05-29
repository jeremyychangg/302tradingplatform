package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;

public class requestGUI extends JPanel {

    // Customise font and size for submit button
    Font submitFont = new Font("Avenir", Font.PLAIN, 15);
    Font headingFont = new Font("Avenir", Font.PLAIN, 50);

    // Import Swing components
    JFrame frame;
    JPanel panel;
    JLabel label;
    JTextField userIDField;

    public requestGUI() {
        frame = new JFrame();

        JLabel requestHeading = new JLabel("Make A Request");
        requestHeading.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        requestHeading.setFont(headingFont);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(1380, 1050));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 80));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(requestHeading);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Request");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new requestGUI();
    }

}
