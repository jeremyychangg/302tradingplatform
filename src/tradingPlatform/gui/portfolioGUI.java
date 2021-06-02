/**
 * @author Natalie Smith
 */
package tradingPlatform.gui;

import tradingPlatform.Inventory;
import tradingPlatform.InventoryItem;
import tradingPlatform.user.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static tradingPlatform.user.User.*;

public class portfolioGUI extends JPanel {
    private JPanel panel;
    // Font styling
    Font font1 = new Font("Avenir", Font.BOLD, 40);
    Font heading = new Font("Avenir", Font.PLAIN, 50);
    Font h1 = new Font("Avenir", Font.PLAIN, 25);

    public int heightPage = 1300;

    public GridBagConstraints gbc = new GridBagConstraints();

    public portfolioGUI() throws Exception {
        setUpPanel();
        welcomeMessagePortfolio(panel);
        chartSection();
        summaryDisplay();
        orderHistoryDisplay();

        add(panel, this.gbc);
    }


    private void setUpPanel() throws SQLException {
        // setting up black JPanel
        this.heightPage = 1300 + retrieveOrderLength() * 50;
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1380, heightPage));
        this.panel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 80));
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.panel.setLayout(new GridBagLayout());
    }


    protected void welcomeMessagePortfolio(JPanel panel) throws SQLException {
        JPanel message = new JPanel();
        message.setLayout(new BoxLayout(message, BoxLayout.Y_AXIS));

        Font font1 = new Font("Avenir", Font.BOLD, 40);

        JLabel welcome = new JLabel("Hi,");
        welcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        welcome.setFont(font1);

        message.add(welcome);

        JLabel name = new JLabel(User.getFirstName());
        name.setFont(font1);
        name.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        message.add(name);
        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbc.anchor = GridBagConstraints.LINE_START;
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.CENTER;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;

        panel.add(message, this.gbc);
    }

    private void chartSection() throws Exception {
        // Here make the graphical chart
        JPanel chartSection = new JPanel();
        chartSection.setPreferredSize(new Dimension(1220, 350));
//        chartSection.setLayout(new BoxLayout(chartSection, BoxLayout.Y_AXIS));
        chartSection.setLayout(new GridBagLayout());
        GridBagConstraints chartGBC = new GridBagConstraints();
        chartGBC.gridx = 1;
        chartGBC.gridy = 0;
        chartGBC.weightx = 1.0;
        chartGBC.weighty = 1.0;
        chartGBC.anchor = GridBagConstraints.LINE_START;
        chartGBC.fill = GridBagConstraints.HORIZONTAL;
        chartGBC.fill = GridBagConstraints.BOTH;


        Inventory values = new Inventory(getUnitID());
        ArrayList<InventoryItem> inventory = values.unitInventory;

        chartSection.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        Piechart pie = new Piechart(0, 0, 237, inventory, values.inventorySize);
        pie.setBorder(BorderFactory.createEmptyBorder(50, 500, 500, 0));
        pie.setAlignmentX(Component.LEFT_ALIGNMENT);
        chartSection.add(pie, chartGBC);

        chartGBC.gridx = 2;
        chartGBC.gridy = 0;

        JPanel legend = new JPanel();
        legend.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        JLabel chart = new JLabel("Chart");
//        chart.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
//        legend.setAlignmentX(Component.LEFT_ALIGNMENT);

        legend.add(chart);
        legend.setBackground(Color.white);
        chartSection.add(legend, chartGBC);

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


    private void orderHistoryDisplay() throws SQLException {
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
                "     ID", "Name","Type", "Date", "Price", "Quantity", "Status"
        };

        ArrayList<ArrayList<String>> data1 = retrieveOrders();
        System.out.println(data1.size());
        String[][] data = new String[data1.size()][];
        int i = 0;
        for (ArrayList<String> c : data1)
        {
            data[i] = new String[7];
            data[i][0] = c.get(0);
            data[i][1] = c.get(1);
            data[i][2] = c.get(2);
            data[i][3] = c.get(3);
            data[i][4] = c.get(4);
            data[i][5] = c.get(5);
            data[i][6] = c.get(6);
            i++;
        }

        Integer[] width = new Integer[] { 150, 400, 100, 150, 100, 150, 150}; // has to equal

        orderHistoryList.add(new Table(columns, data, width));

        orderHistoryList.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.gbc.gridx = 1;
        this.gbc.gridy = 5;

        panel.add(orderHistoryList, this.gbc);
    }
}
