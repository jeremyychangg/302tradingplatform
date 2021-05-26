package tradingPlatform.gui;

import tradingPlatform.user.User;

import javax.swing.*;
import java.awt.*;

public class portfolioGUI extends JPanel {
    private final User currentUser;

    public portfolioGUI(User user) {
        this.currentUser = user;

        // Font styling
        Font font1 = new Font("Avenir", Font.BOLD, 40);
        Font heading = new Font("Avenir", Font.PLAIN, 50);
        Font h1 = new Font("Avenir", Font.PLAIN, 25);


        // setting up black JPanel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 80, 100, 80));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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

        // Here make the graphical chart
        JPanel chartSection = new JPanel();
        chartSection.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        JLabel chart = new JLabel("Chart");
        chart.setBorder(BorderFactory.createEmptyBorder(50, 0,100,0));
        chartSection.setAlignmentX(Component.LEFT_ALIGNMENT);
        chartSection.add(chart);
        panel.add(chartSection);

        // Setting up the container for the summary
        JPanel summaryInfo = new JPanel();

        // input getCredits thing here
        JLabel creditsUser = new JLabel(Float.toString(500));
        creditsUser.setFont(heading);
        JLabel oustandingUser = new JLabel("5");
        oustandingUser.setFont(heading);

        creditsUser.setBorder(BorderFactory.createEmptyBorder(10,300, 10, 100));
        oustandingUser.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 400));

        summaryInfo.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        summaryInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryInfo.add(creditsUser);
        summaryInfo.add(oustandingUser);


//        JLabel creditsUnitsL = new JLabel("Credit Units");
//        creditsUnitsL.setAlignmentX(Component.LEFT_ALIGNMENT);
//        summaryInfo.add(creditsUnitsL);

        summaryInfo.setBackground(Color.white);

        panel.add(summaryInfo);

        // Order History section
        JLabel orderHistoryHeading = new JLabel("Order History");
        orderHistoryHeading.setFont(h1);
        orderHistoryHeading.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 10));
        panel.add(orderHistoryHeading);
        JPanel orderHistoryList = new JPanel();
        orderHistoryList.add(new Table());
        orderHistoryList.setAlignmentX(Component.LEFT_ALIGNMENT);
        orderHistoryList.setBackground(Color.WHITE);

        panel.add(orderHistoryList);

        // adding blackJPanel
        add(panel);
    }
}
