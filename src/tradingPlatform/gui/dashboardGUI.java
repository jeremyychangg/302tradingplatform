/**
 * @author Natalie Smith
 */
package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class dashboardGUI extends JPanel implements ActionListener {
    private JPanel summary;
    private JPanel left;

    public dashboardGUI() throws SQLException {
        // setting up black JPanel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));

        panel.setLayout(new BorderLayout());
        panel.add(Box.createHorizontalGlue());

        left = new JPanel();
        left.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        summary = new JPanel();
        summary.setBorder(BorderFactory.createEmptyBorder(Screen.screenWidth/4, 0, 0, 0));
        summary.setBackground(Color.white);
        summary.setLayout(new BoxLayout(summary, BoxLayout.Y_AXIS));
        summary.setPreferredSize(new Dimension(500, 1200));
        employeeScreen.creditBalancePanel(summary);
        summary.setAlignmentY(Component.CENTER_ALIGNMENT);

        Screen.welcomeMessage(left);

        panel.add(left, BorderLayout.LINE_START);
        panel.add(summary, BorderLayout.LINE_END);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
