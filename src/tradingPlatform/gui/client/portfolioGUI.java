/**
 * @author Natalie Smith
 */
package tradingPlatform.gui.client;

import tradingPlatform.Inventory;
import tradingPlatform.InventoryItem;
import tradingPlatform.gui.common.Table;
import tradingPlatform.gui.common.ColorSquare;
import tradingPlatform.gui.common.Piechart;
import tradingPlatform.gui.common.Screen;
import tradingPlatform.user.User;

import javax.swing.*;
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
     * The portfolio GUI constructor initiates and builds a panel containing the
     * elements of the interface used to update the user's portfolio. In particular,
     * this would construct the piechart, summary of details, and order history associated
     * to the user and their respective unit ID. Additionally, it would initiate and add an
     * action listener to the update button, so that if the
     * user does enter values into the fields and presses the button, it would call methods
     * to update the password.
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
     * This method is used to set up the panel for the portfolio. Note that this panel is different
     * to the others, and is scrollable. Thus, in order to adjust the height (which is affected by the
     * order history list) the number of rows in the order history list are estimated and added to the height
     * to ensure the GUI isn't affected by the GridBagLayout set.
     *
     * @throws SQLException
     */
    private void setUpPanel() throws SQLException {
        // If there are orders, add the height of the table at bottom
        if (retrieveOrderLength() > 0) {
            this.heightPage = this.heightPage + retrieveOrderLength() * 50;
        } else {
            this.heightPage = Screen.screenHeight + 300;
        }
        // Initiate the panel
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(Screen.screenWidth, heightPage));
        this.panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, 0, Screen.border));
        this.panel.setLayout(new GridBagLayout());
    }


    /**
     * The welcome message for this method is used to send the personalised message to the user. To achieve
     * this, the user's name is retrieved by relevant methods, and outputted to the program in a label.
     * Additional properties and settings are applied to achieve the layout for the GUI.
     *
     * @param panel
     * @throws SQLException
     */
    protected void welcomeMessagePortfolio(JPanel panel) throws SQLException {
        // New message panel
        JPanel message = new JPanel();
        message.setLayout(new BoxLayout(message, BoxLayout.Y_AXIS));

        // Write the personalised message, and set up layout and other properties and styles
        JLabel welcome = new JLabel("Hi,");
        welcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        welcome.setFont(Screen.font1);
        message.add(welcome);

        JLabel name = new JLabel(User.getFirstName());
        name.setFont(Screen.font1);
        name.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        message.setPreferredSize(new Dimension(Screen.screenWidth - border, 60));
        message.add(name);
        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbc.anchor = GridBagConstraints.LINE_START;
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.CENTER;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;

        // Add message to the panel
        panel.add(message, this.gbc);
    }


    /**
     * This method is used to construct the chart section a.k.a the pie chart section.
     * It is used to visualise the inventory (current) of the user by retrieving their
     * unitID and associated inventory items.
     *
     * @throws Exception
     */
    private void chartSection() throws Exception {
        // Here make the graphical chart
        // Add heading
        this.gbc.gridx = 1;
        this.gbc.gridy = 1;
        JLabel inventoryLabel = new JLabel("Your Inventory");
        inventoryLabel.setFont(Screen.h1);
        panel.add(inventoryLabel, this.gbc);

        // Create section panel for the chart
        JPanel chartSection = new JPanel();
        chartSection.setPreferredSize(new Dimension(1220, 200));
        chartSection.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        chartSection.setLayout(new GridBagLayout());
        GridBagConstraints chartGBC = new GridBagConstraints();
        chartGBC.gridx = 1;
        chartGBC.gridy = 0;
        chartGBC.weightx = 1.0;
        chartGBC.weighty = 1.0;
        chartGBC.anchor = GridBagConstraints.LINE_START;
        chartGBC.fill = GridBagConstraints.HORIZONTAL;
        chartGBC.fill = GridBagConstraints.BOTH;

        // Retrieve the inventory values of the user, and output pie chart based on these values
        Inventory values = new Inventory(User.getUnitID());
        ArrayList<InventoryItem> inventory = values.unitInventory;

        // Draw the pie chart based on inventory values
        Piechart pie = new Piechart(0, 0, 237, inventory, values.inventorySize);
        // Initialise border to be smaller if screen is bigger
        int pieBorder = 240;
        if (Screen.screenHeight > 1400) {
            pieBorder = 200;
        }
        pie.setBorder(BorderFactory.createEmptyBorder(50, 500, pieBorder, 0));
        pie.setAlignmentX(Component.LEFT_ALIGNMENT);
        chartSection.add(pie, chartGBC);

        chartGBC.gridx = 2;
        chartGBC.gridy = 0;

        // Create a legend panel, and thus, based on the pie chart inventory, display these values
        JPanel legend = new JPanel();
        legend.setBorder(BorderFactory.createEmptyBorder(80, 100, 80, 100));
        legend.setLayout(new GridLayout(4, inventory.size()));

        int i = 0;
        for (InventoryItem c : inventory) {
            // Construct a 'row' for each of the legend values
            JPanel legendRow = new JPanel();
            legendRow.setLayout(new GridLayout(0, 4));

            // Output details about each of the inventory items - percentage, name, price
            int percentage = (int) ((int) 100 * ((c.quantity * c.purchasePrice) / values.inventorySize));
            JLabel inventoryPercent = new JLabel(String.format(String.valueOf(percentage)) + "%");
            JLabel inventoryName = new JLabel(c.asset.assetName);
            JLabel inventoryPrice =
                    new JLabel(String.format(String.valueOf(Math.floor(c.purchasePrice * c.quantity))) + " CPU");

            // Increasingly adjust color based on item
            int g = (255 / inventory.size()) * i;
            ColorSquare square = new ColorSquare(g, 0, 0);

            // Add to the legend row
            legendRow.add(square);
            legendRow.add(inventoryPercent);
            legendRow.add(inventoryName);
            legendRow.add(inventoryPrice);
            legend.add(legendRow);
            i++;
        }

        // Output a blank chart if there were no inventory items from the associate unit
        if (inventory.size() == 0) {
            legend = new JPanel();
            legend.setPreferredSize(new Dimension(50, 4));
            legend.setLayout(new GridLayout(1, 2));
            ColorSquare square = new ColorSquare(140, 0, 0);
            legend.add(square);
            JLabel inventoryItem = new JLabel("None");
            legend.add(inventoryItem);
        }

        // Add the legend to the chart
        chartSection.add(legend, chartGBC);
        this.gbc.gridx = 1;
        this.gbc.gridy = 2;

        // Add the complete chart section to the panel
        panel.add(chartSection, this.gbc);
    }


    /**
     * The summary display is a method used to display the users associated current credit balance
     * and outstanding orders, given the unitID and userID. This is placed in a container.
     * @throws SQLException
     */
    private void summaryDisplay() throws SQLException {
        // Setting up the container for the summary
        JPanel summaryInfo = new JPanel(new GridLayout(1, 0));
        employeeScreen.creditBalancePanel(summaryInfo);
        summaryInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.gbc.gridx = 1;
        this.gbc.gridy = 3;
        panel.add(summaryInfo, this.gbc);
    }


    /**
     * In this function, when called, it displays the order history section based on the inputted userID. This
     * would be displayed in a table format, displaying the asset ordered, it's ID, price, quantity, status, date
     * applied and current status.
     * @throws SQLException
     */
    private void orderHistoryDisplay() throws SQLException {
        // Order History section
        // Add heading to the section
        JLabel orderHistoryHeading = new JLabel("Order History");
        orderHistoryHeading.setFont(Screen.h1);
        orderHistoryHeading.setBorder(BorderFactory.createEmptyBorder(80, 0, 80, 10));
        this.gbc.gridx = 1;
        this.gbc.gridy = 4;
        panel.add(orderHistoryHeading, this.gbc);

        // Create a table of order history if there are more than 0 entries
        if (retrieveOrderLength() > 0) {
            JPanel orderHistoryList = new JPanel();

            // Retrieving the orders pending/incomplete of the user - and their status
            String[] columns = new String[]{
                    "     ID", "Name", "Type", "Date", "Price", "Quantity", "Status"
            };

            // Retrieves orders and Construct a string nested array based on the order array list
            ArrayList<ArrayList<String>> orders = retrieveOrders();
            String[][] data = new String[orders.size()][];
            int i = 0;
            for (ArrayList<String> c : orders) {
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

            // Adjust the sizing of each of the columns based on the screen width
            int length = 0;
            if (Screen.screenWidth > 1400) {
                length = (int) (Screen.screenWidth - Screen.screenWidth * 0.2 - Screen.screenWidth / 3.7);
            } else {
                length = (int) (Screen.screenWidth - Screen.screenWidth / 3.7);
            }
            Integer[] width = new Integer[]{length / 7, length / 3, length / 11,
                    length / 7, length / 7, length / 7, length / 7}; // has to equal

            orderHistoryList.add(new Table(columns, data, width));
            orderHistoryList.setAlignmentX(Component.LEFT_ALIGNMENT);
            orderHistoryList.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
            this.gbc.gridx = 1;
            this.gbc.gridy = 5;

            // Add to the panel
            panel.add(orderHistoryList, this.gbc);
        } else if (retrieveOrderLength() <= 0) {
            // If no orders, replace with a message
            JLabel noOrders =
                    new JLabel("Currently, this unit doesn't have any orders in their history up to date.");
            noOrders.setFont(Screen.body);
            noOrders.setBorder(BorderFactory.createEmptyBorder(100, 0, 40, 0));
            panel.add(noOrders, this.gbc);
        }
    }
}
