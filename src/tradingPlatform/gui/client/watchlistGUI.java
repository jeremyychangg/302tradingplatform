package tradingPlatform.gui.client;

import tradingPlatform.Asset;
import tradingPlatform.Main;
import tradingPlatform.Watchlist;
import tradingPlatform.exceptions.AssetTypeException;
import tradingPlatform.exceptions.InvalidAssetException;
import tradingPlatform.gui.common.Screen;
import tradingPlatform.gui.common.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static tradingPlatform.gui.common.Screen.welcomeMessage;

public class watchlistGUI extends JPanel {
    private JPanel panel = new JPanel();
    public GridBagConstraints gbc = new GridBagConstraints();
    Watchlist watchlist = new Watchlist(Main.getCurrentUnit());

    /**
     *
     * @throws SQLException
     * @throws AssetTypeException
     */
    public watchlistGUI() throws Exception {
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
    private void watchlistDisplay() throws SQLException, AssetTypeException, InvalidAssetException {
        // Order History section
        JLabel currentWatchlist = new JLabel("Unit Watchlist");
        currentWatchlist.setFont(Screen.h1);
        currentWatchlist.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 10));
        this.gbc.gridx = 1;
        this.gbc.gridy = 4;
        panel.add(currentWatchlist, this.gbc);


        int watchlistSize = watchlist.GetWatchlistCount();

        if (watchlistSize > 0 ) {
            JPanel watchlistTable = new JPanel();
            // Retrieving the orders pending/incomplete of the user - and their status
            String[] columns = new String[] {
                    "     ID", "Name", "Type", "Current Price"
            };


            ArrayList<Asset> list = watchlist.GetWatchlist();

            String[][] data = new String[list.size()][];

            for (int i = 0; i < list.size(); i++) {
                data[i] = new String[4];
                data[i][0] = list.get(i).GetAssetID();
                data[i][1] = list.get(i).GetAssetName();
                data[i][2] = list.get(i).assetType;
                data[i][3] = String.valueOf(list.get(i).GetPrice());
            }

            // Adjust the sizing of each of the columns based on the screen width
            int length = 0;
            if (Screen.screenWidth > 1400) {
                length = (int) (Screen.screenWidth - Screen.screenWidth * 0.2 - Screen.screenWidth / 3.7);
            } else {
                length = (int) (Screen.screenWidth - Screen.screenWidth / 3.7);
            }
            Integer[] width = new Integer[]{length / 4, length / 3, length / 3, length / 5}; // has to equal

            watchlistTable.add(new Table(columns, data, width));

            watchlistTable.setAlignmentX(Component.LEFT_ALIGNMENT);
            watchlistTable.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
            this.gbc.gridx = 1;
            this.gbc.gridy = 5;

            // Add to the panel
            panel.add(watchlistTable, this.gbc);


        } else {
            JLabel noAssets =
                    new JLabel("Currently, this unit doesn't have assets in watchlist.");
            noAssets.setFont(Screen.body);
            noAssets.setBorder(BorderFactory.createEmptyBorder(100, 0, 40, 0));
            panel.add(noAssets);
        }



//        panel.add(currentWatchlist, this.gbc);
    }


}
