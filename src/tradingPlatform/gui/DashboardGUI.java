package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

<<<<<<< HEAD:src/tradingPlatform/gui/dashboardGUI.java
public class dashboardGUI extends JPanel implements ActionListener {
=======
public class DashboardGUI implements ActionListener {
    int count = 0;
>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242:src/tradingPlatform/gui/DashboardGUI.java
    private JLabel label;
    private JPanel panel;
<<<<<<< HEAD:src/tradingPlatform/gui/dashboardGUI.java
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
        panel.setPreferredSize(new Dimension(1380, 1050));
        panel.setLayout(new BorderLayout());
        panel.add(Box.createHorizontalGlue());

        left = new JPanel();
        left.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 600));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        summary = new JPanel();
        summary.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));
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
=======

    public DashboardGUI(){
        frame = new JFrame();
        panel = new JPanel();

        JButton button = new JButton("Home");
        button.addActionListener(this);

        label = new JLabel("Hello, welcome Steve");

        panel.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        panel.setLayout(new GridLayout(0,1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Dashboard");
        frame.pack();

        frame.setVisible(true);

    }
//    public static void main(String[] args){
//        new dashboardGUI();
//    }
>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242:src/tradingPlatform/gui/DashboardGUI.java

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
