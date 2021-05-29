package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class portfolioGUI extends JPanel {
    public portfolioGUI() throws SQLException {
        // Font styling
        Font font1 = new Font("Avenir", Font.BOLD, 40);
        Font heading = new Font("Avenir", Font.PLAIN, 50);
        Font h1 = new Font("Avenir", Font.PLAIN, 25);

        // setting up black JPanel
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1380, 1050));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 80));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

<<<<<<< HEAD
        Screen.welcomeMessage(panel);
=======
        JLabel welcome = new JLabel("Hi,");
        welcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        welcome.setSize(200,200);
        welcome.setFont(font1);
        panel.add(welcome, BorderLayout.NORTH);

        JLabel name = new JLabel("Peter");
//        JLabel name = new JLabel(currentUser.getName());
        name.setFont(font1);
        name.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(name, BorderLayout.WEST);
>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242

        // Here make the graphical chart
        JPanel chartSection = new JPanel();
        chartSection.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        JLabel chart = new JLabel("Chart");
        chart.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));
        chartSection.setAlignmentX(Component.LEFT_ALIGNMENT);
        chartSection.add(chart);
        panel.add(chartSection);

        // Setting up the container for the summary
        JPanel summaryInfo = new JPanel();
        employeeScreen.creditBalancePanel(summaryInfo);
        summaryInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, -20, 0));
        panel.add(summaryInfo);

        // Order History section
        JLabel orderHistoryHeading = new JLabel("Order History");
        orderHistoryHeading.setFont(h1);
        orderHistoryHeading.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 10));
        panel.add(orderHistoryHeading);
        JPanel orderHistoryList = new JPanel();
        orderHistoryList.add(new Table());
        orderHistoryList.setAlignmentX(Component.LEFT_ALIGNMENT);
        orderHistoryList.setBackground(Color.WHITE);

        panel.add(orderHistoryList);

        add(panel);
    }


}
