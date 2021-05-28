package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class portfolioGUI extends JPanel {
    private JPanel panel;
    // Font styling
    Font font1 = new Font("Avenir", Font.BOLD, 40);
    Font heading = new Font("Avenir", Font.PLAIN, 50);
    Font h1 = new Font("Avenir", Font.PLAIN, 25);

    public portfolioGUI() throws SQLException {
        setUpPanel();
        Screen.welcomeMessage(panel);
        chartSection();
        summaryDisplay();

        add(panel);
    }

    private void orderHistoryDisplay(){
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
    }

    private void summaryDisplay() throws SQLException {
        // Setting up the container for the summary
        JPanel summaryInfo = new JPanel();
        employeeScreen.creditBalancePanel(summaryInfo);
        summaryInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panel.add(summaryInfo);
    }

    private void setUpPanel(){
        // setting up black JPanel
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(1380, 1050));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 80));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    private void chartSection() {
        // Here make the graphical chart
        JPanel chartSection = new JPanel();
        chartSection.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        JLabel chart = new JLabel("Chart");
        chart.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));
        chartSection.setAlignmentX(Component.LEFT_ALIGNMENT);
        chartSection.add(chart);
        panel.add(chartSection);
    }


}
