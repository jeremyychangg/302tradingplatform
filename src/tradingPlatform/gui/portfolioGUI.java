/**
 * @author Natalie Smith
 */
package tradingPlatform.gui;

import tradingPlatform.user.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class portfolioGUI extends JPanel {
    private JPanel panel;
    // Font styling
    Font font1 = new Font("Avenir", Font.BOLD, 40);
    Font heading = new Font("Avenir", Font.PLAIN, 50);
    Font h1 = new Font("Avenir", Font.PLAIN, 25);

    public GridBagConstraints gbc = new GridBagConstraints();

    public portfolioGUI() throws SQLException {
        setUpPanel();
        welcomeMessagePortfolio(panel);
        chartSection();
        summaryDisplay();
        orderHistoryDisplay();

        add(panel, this.gbc);
    }


    private void setUpPanel(){
        // setting up black JPanel
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1380, 1200));
        this.panel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 80));
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.panel.setLayout(new GridBagLayout());
    }


    protected void welcomeMessagePortfolio(JPanel panel) throws SQLException {
        Font font1 = new Font("Avenir", Font.BOLD, 40);

        JLabel welcome = new JLabel("Hi,");
        welcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        welcome.setFont(font1);

        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbc.anchor = GridBagConstraints.LINE_START;
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.CENTER;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;

        panel.add(welcome, this.gbc);

        JLabel name = new JLabel(User.getFirstName());
        name.setFont(font1);
        name.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        this.gbc.gridx = 1;
        this.gbc.gridy = 1;

        panel.add(name, this.gbc);
    }

    private void chartSection() {
        // Here make the graphical chart
        JPanel chartSection = new JPanel();
        chartSection.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        JLabel chart = new JLabel("Chart");
        chart.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));
        chartSection.setAlignmentX(Component.LEFT_ALIGNMENT);
        chartSection.add(chart);

        this.gbc.gridx = 1;
        this.gbc.gridy = 2;

        panel.add(chartSection, this.gbc);
    }

    private void summaryDisplay() throws SQLException {
        // Setting up the container for the summary
        JPanel summaryInfo = new JPanel();
        employeeScreen.creditBalancePanel(summaryInfo);
        summaryInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.gbc.gridx = 1;
        this.gbc.gridy = 3;

        panel.add(summaryInfo, this.gbc);
    }


    private void orderHistoryDisplay(){
        // Order History section
        JLabel orderHistoryHeading = new JLabel("Order History");
        orderHistoryHeading.setFont(h1);
        orderHistoryHeading.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 10));

        this.gbc.gridx = 1;
        this.gbc.gridy = 4;

        panel.add(orderHistoryHeading, this.gbc);
        JPanel orderHistoryList = new JPanel();

        // Retrieving the orders pending/incomplete of the user - and their status
        String[] columns = new String[] {
                "Order ID", "Asset", "Quantity", "Price", "Date"
        };

        Object[][] data = new Object[][] {
                {1, "Printing Paper", 50, "$" + 100, 10/02/2020 },
                {2, "CPU Hours", 4, "$" + 100, 10/02/2020 },
                {3, "Mousepad", 5, "$" + 100, 10/02/2020 },
        };

        Integer[] width = new Integer[] { 155, 600, 150, 150, 155};

        orderHistoryList.add(new Table(columns, data, width));

        orderHistoryList.setAlignmentX(Component.LEFT_ALIGNMENT);
//        orderHistoryList.setBackground(Color.WHITE);

        this.gbc.gridx = 1;
        this.gbc.gridy = 5;

        panel.add(orderHistoryList, this.gbc);
    }
}
