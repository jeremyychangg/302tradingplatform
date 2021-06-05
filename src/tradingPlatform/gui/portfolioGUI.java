/**
 * @author Natalie Smith
 */
package tradingPlatform.gui;

import tradingPlatform.Inventory;
import tradingPlatform.InventoryItem;
import tradingPlatform.user.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static tradingPlatform.user.User.retrieveOrderLength;
import static tradingPlatform.user.User.retrieveOrders;

public class portfolioGUI extends JPanel {
    private JPanel panel;
    public int heightPage = Screen.screenHeight + 300;
    public GridBagConstraints gbc = new GridBagConstraints();
    public int border;

    /**
     *
     * @throws Exception
     */
    public portfolioGUI() throws Exception {
        setUpPanel();
        welcomeMessagePortfolio(panel);
        chartSection();
        summaryDisplay();
        orderHistoryDisplay();

        add(panel, this.gbc);
    }


    /**
     *
     * @throws SQLException
     */
    private void setUpPanel() throws SQLException {
        if (retrieveOrderLength() > 0){
            this.heightPage = this.heightPage + retrieveOrderLength() * 50;
        }
        else {
            this.heightPage = Screen.screenHeight + 300;
        }
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(Screen.screenWidth, heightPage));
        this.panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, 0, Screen.border));
        this.panel.setLayout(new GridBagLayout());
    }



    /**
     *
     * @param panel
     * @throws SQLException
     */
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



    /**
     *
     * @throws Exception
     */
    private void chartSection() throws Exception {
        // Here make the graphical chart
        this.gbc.gridx = 1;
        this.gbc.gridy = 1;
        JLabel inventoryLabel = new JLabel("Your Inventory");
        inventoryLabel.setFont(Screen.h1);
        panel.add(inventoryLabel, this.gbc);

        JPanel chartSection = new JPanel();
        chartSection.setPreferredSize(new Dimension(1220, 200));
        chartSection.setLayout(new GridBagLayout());
        GridBagConstraints chartGBC = new GridBagConstraints();
        chartGBC.gridx = 1;
        chartGBC.gridy = 0;
        chartGBC.weightx = 1.0;
        chartGBC.weighty = 1.0;
        chartGBC.anchor = GridBagConstraints.LINE_START;
        chartGBC.fill = GridBagConstraints.HORIZONTAL;
        chartGBC.fill = GridBagConstraints.BOTH;

        Inventory values = new Inventory(User.getUnitID());
        ArrayList<InventoryItem> inventory = values.unitInventory;

        chartSection.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        Piechart pie = new Piechart(0, 0, 237, inventory, values.inventorySize);
        pie.setBorder(BorderFactory.createEmptyBorder(50, 500, 500, 0));
        pie.setBorder(new LineBorder(Color.BLACK, 3));
        pie.setAlignmentX(Component.LEFT_ALIGNMENT);
        chartSection.add(pie, chartGBC);

        chartGBC.gridx = 2;
        chartGBC.gridy = 0;

        JPanel legend = new JPanel();
        legend.setBorder(BorderFactory.createEmptyBorder(80, 100, 80, 100));
//        legend.setPreferredSize(new Dimension(150, 100));
//        legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS));
        legend.setLayout(new GridLayout(4, inventory.size()));

        int i = 0;
        for (InventoryItem c : inventory){
            JPanel legendRow = new JPanel();
            legendRow.setLayout(new GridLayout(0, 4));

//            legendRow.setBackground(Color.RED);
            int percentage = (int) ((int) 100 * ((c.quantity * c.purchasePrice)/values.inventorySize));
            int g = (255 / inventory.size()) * i;
            JLabel inventoryPercent = new JLabel(String.format(String.valueOf(percentage)) + "%");
            JLabel inventoryName = new JLabel(c.asset.assetName);
            JLabel inventoryPrice = new JLabel(String.format(String.valueOf(Math.floor(c.purchasePrice * c.quantity))) + " CPU");

            ColorSquare square = new ColorSquare(g, 0, 0);

            legendRow.add(square);
            legendRow.add(inventoryPercent);
            legendRow.add(inventoryName);
            legendRow.add(inventoryPrice);
            legend.add(legendRow);
            i++;

            /*
                        JPanel legendRow = new JPanel();
            legendRow.setLayout(new BoxLayout(legendRow, BoxLayout.X_AXIS));
//            legendRow.setBackground(Color.RED);
            int percentage = (int) ((int) 100 * ((c.quantity * c.purchasePrice)/values.inventorySize));
            int g = (255 / inventory.size()) * i;
            int height = 20;
            ColorSquare square = new ColorSquare(g, 0, height);
            legendRow.add(square, BorderLayout.WEST);

            JLabel inventoryPercent = new JLabel(String.format(String.valueOf(percentage)) + "%");
            JLabel inventoryName = new JLabel(c.asset.assetName);
            JLabel inventoryPrice = new JLabel(String.format(String.valueOf(Math.floor(c.purchasePrice * c.quantity))) + " CPU");

            inventoryPercent.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 30));
            inventoryName.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 30));
            inventoryPrice.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 30));

            legendRow.add(inventoryPercent, BorderLayout.WEST);
            legendRow.add(inventoryName, BorderLayout.CENTER);
            legendRow.add(inventoryPrice, BorderLayout.EAST);
            legend.add(legendRow, BorderLayout.LINE_START);
            i++;
             */
        }


        if (inventory.size() == 0){
            ColorSquare square = new ColorSquare(0, 50, 50);
            legend.add(square, BorderLayout.LINE_START);
            JLabel inventoryItem = new JLabel("None");
            legend.add(inventoryItem, BorderLayout.CENTER);
        }
        chartSection.add(legend, chartGBC);

        this.gbc.gridx = 1;
        this.gbc.gridy = 2;
        panel.add(chartSection, this.gbc);
    }


    /**
     *
     * @throws SQLException
     */
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


    /**
     * In this function, when called, it displays the order history section based on the inputted userID.
     * @throws SQLException
     */
    private void orderHistoryDisplay() throws SQLException {
        // Order History section
        JLabel orderHistoryHeading = new JLabel("Order History");
        orderHistoryHeading.setFont(Screen.h1);
        orderHistoryHeading.setBorder(BorderFactory.createEmptyBorder(80, 0, 80, 10));

        this.gbc.gridx = 1;
        this.gbc.gridy = 4;

        panel.add(orderHistoryHeading, this.gbc);

        if (retrieveOrderLength() > 0){
            JPanel orderHistoryList = new JPanel();

            // Retrieving the orders pending/incomplete of the user - and their status
            String[] columns = new String[] {
                    "     ID", "Name","Type", "Date", "Price", "Quantity", "Status"
            };

            ArrayList<ArrayList<String>> data1 = retrieveOrders();
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
            int length = 0;
            if (Screen.screenWidth > 1400){
                length = (int) (Screen.screenWidth - Screen.screenWidth * 0.2 - Screen.screenWidth/3.7);
            }
            else {
                length = (int) (Screen.screenWidth - Screen.screenWidth/3.7);
            }
            Integer[] width = new Integer[] { length/7, length/3, length/11, length/7, length/7, length/7, length/7}; // has to equal

            orderHistoryList.add(new Table(columns, data, width));

            orderHistoryList.setAlignmentX(Component.LEFT_ALIGNMENT);

            this.gbc.gridx = 1;
            this.gbc.gridy = 5;

            panel.add(orderHistoryList, this.gbc);
        }
        else if (retrieveOrderLength() <= 0){
            JLabel noOrders = new JLabel("Currently, this unit doesn't have any orders in their history up to date.");
            noOrders.setFont(Screen.body);
            noOrders.setBorder(BorderFactory.createEmptyBorder(100, 0,40,0));
            panel.add(noOrders, this.gbc);
        }
    }
}
