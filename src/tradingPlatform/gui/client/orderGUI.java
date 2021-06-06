package tradingPlatform.gui.client;

import tradingPlatform.*;
import tradingPlatform.exceptions.*;
import tradingPlatform.gui.common.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static tradingPlatform.gui.common.Screen.welcomeMessage;

public class orderGUI extends JPanel implements ActionListener {
    private Asset currentAsset;
    private JPanel panel = new JPanel();

    JButton buyButton = new JButton("BUY");
    JButton sellButton = new JButton("SELL");

    JTextField priceIn = new JTextField("Price");
    JTextField quantIn = new JTextField("Quantity");

    public orderGUI(String unitID, String assetID) throws SQLException, InvalidAssetException, UnitException {
        currentAsset = Asset.findAsset(assetID);
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, 0, Screen.border));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        welcomeMessage(panel);
        


        JLabel name = new JLabel(currentAsset.GetAssetName());
        name.setFont(new Font("Avenir", Font.BOLD, 70));

        JLabel type = new JLabel(currentAsset.assetType);
        type.setFont(new Font("Avenir", Font.PLAIN, 30));
        type.setForeground(Color.GRAY);


        JLabel price = new JLabel(String.valueOf(currentAsset.GetPrice()) + " CU");
        price.setFont(new Font("Avenir", Font.BOLD, 60));
        price.setBorder(BorderFactory.createEmptyBorder(80, 0, 200, 10));
        price.setForeground(new Color(24, 114, 229));

//        JLabel currentBalance = new JLabel(String.valueOf(Unit.getUnit(unitID).getCreditBalance()));
//        currentBalance.setFont(new Font("Avenir", Font.BOLD, 100));
//        currentBalance.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 10));
//        name.setFont(new Font("Avenir", Font.BOLD, 70));

        quantIn.setPreferredSize(new Dimension(1,1));
//        quantIn.setBorder(BorderFactory.createEmptyBorder(80, 0, 80, 10));


        priceIn.setPreferredSize(new Dimension(1,1));
//        priceIn.setBorder(BorderFactory.createEmptyBorder(80, 0, 80, 10));

        ImageIcon buyImage =  new ImageIcon("src/img/buy.png");
        buyButton.setIcon(buyImage);
        buyButton.setBorder(BorderFactory.createEmptyBorder(100, 0, 20, 0));

        ImageIcon sellImage =  new ImageIcon("src/img/sell.png");
        sellButton.setIcon(sellImage);
        sellButton.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        panel.add(name);
        panel.add(type);
        panel.add(price);
        panel.add(quantIn);

        panel.add(priceIn);
//        panel.add(currentBalance, BorderLayout.EAST);

        panel.add(buyButton, BorderLayout.LINE_START);
        panel.add(sellButton, BorderLayout.LINE_END);

        buyButton.addActionListener(this);
        sellButton.addActionListener(this);


        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyButton) {
            int quant = 0;
            try {
                quant = Integer.parseInt(quantIn.getText());
            } catch (Exception ex) {
                throw ex;
            }

            double price = 0;
            try {
                price = Double.parseDouble(priceIn.getText());
            } catch (Exception ex) {
                throw ex;
            }


            try {
                new BuyOrder(Main.getCurrentUser(), currentAsset.GetAssetID(), price, quant );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (InvalidAssetException invalidAssetException) {
                invalidAssetException.printStackTrace();
            } catch (InvalidOrderException invalidOrderException) {
                invalidOrderException.printStackTrace();
            } catch (NegativePriceException negativePriceException) {
                negativePriceException.printStackTrace();
            } catch (InsuffcientFundsException insuffcientFundsException) {
                insuffcientFundsException.printStackTrace();
            } catch (UnitException unitException) {
                unitException.printStackTrace();
            }
        }

        if (e.getSource() == sellButton) {
            int quant = 0;
            try {
                quant = Integer.parseInt(quantIn.getText());
            } catch (Exception ex) {
                throw ex;
            }

            double price = 0;
            try {
                price = Double.parseDouble(priceIn.getText());
            } catch (Exception ex) {
                throw ex;
            }

            try {
                new SellOrder(Main.getCurrentUser(), currentAsset.GetAssetID(), price, quant);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (InvalidAssetException invalidAssetException) {
                invalidAssetException.printStackTrace();
            } catch (InvalidOrderException invalidOrderException) {
                invalidOrderException.printStackTrace();
            } catch (NegativePriceException negativePriceException) {
                negativePriceException.printStackTrace();
            } catch (InsufficientInventoryException insufficientInventoryException) {
                insufficientInventoryException.printStackTrace();
            } catch (UnitException unitException) {
                unitException.printStackTrace();
            }

        }
    }
}
