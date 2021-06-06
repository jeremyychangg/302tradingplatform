package tradingPlatform.gui.client;

import tradingPlatform.gui.common.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static tradingPlatform.gui.common.Screen.welcomeMessage;

public class orderGUI extends JPanel implements ActionListener {
    private JPanel summary;
    private JPanel left;

    public orderGUI() throws SQLException {
//        JPanel panel = new JPanel();
//
//        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
//        welcomeMessage(panel);
//        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, 0, Screen.border));
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//        left = new JPanel();
//        left.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
//        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
//        Screen.welcomeMessage(left);
//
//        add(panel);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, Screen.border, 0, 0));
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
