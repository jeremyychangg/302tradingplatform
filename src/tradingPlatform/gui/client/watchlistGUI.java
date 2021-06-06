package tradingPlatform.gui.client;

import tradingPlatform.exceptions.AssetTypeException;
import tradingPlatform.gui.common.Screen;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static tradingPlatform.gui.common.Screen.welcomeMessage;

public class watchlistGUI  extends JPanel {
    private JPanel panel = new JPanel();
    public GridBagConstraints gbc = new GridBagConstraints();

    /**
     *
     * @throws SQLException
     * @throws AssetTypeException
     */
    public watchlistGUI() throws SQLException, AssetTypeException {
        setUpPanel();
        welcomeMessage(panel);
        watchlistDisplay();
        add(panel);
    }


    /**
     *
     */
    public void setUpPanel(){
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, 0, Screen.border));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }


    /**
     *
     * @throws SQLException
     * @throws AssetTypeException
     */
    private void watchlistDisplay() throws SQLException, AssetTypeException {
        // Order History section
        JLabel orderHistoryHeading = new JLabel("Unit Watchlist");
        orderHistoryHeading.setFont(Screen.h1);
        orderHistoryHeading.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 10));

        this.gbc.gridx = 1;
        this.gbc.gridy = 4;

        panel.add(orderHistoryHeading, this.gbc);
        JPanel orderHistoryList = new JPanel();

        // Retrieving the orders pending/incomplete of the user - and their status
        String[] columns = new String[] {
                "     ID", "Name", "Type", "Date", "Price", "Quantity"
        };

//        ArrayList<Asset> data1 = Watchlist.GetWatchlist();
//        String[][] data = new String[data1.size()][];
        int i = 0;
//        for (Asset c : data1)
//        {
//            data[i] = new String[3];
//            data[i][0] = c.get(0);
//            data[i][1] = c.get(1);
//            data[i][2] = c.get(2);
//            i++;
//        }

//        Integer[] width = new Integer[] { 150, 550, 100, 150, 100, 150}; // has to equal

//        orderHistoryList.add(new Table(columns, data, width));

        orderHistoryList.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.gbc.gridx = 1;
        this.gbc.gridy = 5;

        panel.add(orderHistoryList, this.gbc);
    }
}