package tradingPlatform.gui;

import tradingPlatform.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class dashboardGUI extends JPanel implements ActionListener {
    private JLabel label;
    private JPanel panel;
    private JPanel summary;
    private JPanel left;

    public dashboardGUI() throws SQLException {
        // Font styling
        Font font1 = new Font("Avenir", Font.BOLD, 40);
        Font heading = new Font("Avenir", Font.PLAIN, 50);
        Font h1 = new Font("Avenir", Font.PLAIN, 25);

        // setting up black JPanel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.green);

        left = new JPanel();
        left.setBorder(BorderFactory.createEmptyBorder(100,0,0,600));

        summary = new JPanel();
        summary.setBackground(Color.white);
        summary.setBorder(BorderFactory.createEmptyBorder(0, 200, 1040, 200));

        JLabel welcome = new JLabel("Hi,");
        welcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        welcome.setSize(200,200);
        welcome.setFont(font1);
        panel.add(welcome, BorderLayout.NORTH);

        JLabel name = new JLabel(User.getFirstName());
        name.setFont(font1);
        name.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        left.add(welcome, BorderLayout.NORTH);
        left.add(name, BorderLayout.WEST);
        panel.add(left, BorderLayout.LINE_START);
        panel.add(summary, BorderLayout.LINE_END);

        add(panel);
    }
    public static void main(String[] args) throws SQLException {

        new dashboardGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e){
    }
}
