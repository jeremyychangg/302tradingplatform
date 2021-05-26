package tradingPlatform.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen implements ActionListener {
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JPanel sidebarPanel;
    private JPanel logoutPane;

    // Buttons
    private JLabel logo = new JLabel();
    private JButton portfolioButton = new JButton();
    private JButton dashboardButton = new JButton();
    private JButton watchlistButton = new JButton();
    private JButton ordersButton = new JButton();
    private JButton logoutButton = new JButton();

    //images when unselected
    private ImageIcon portfolioIcon = new ImageIcon("src/img/portfolio-01.jpg");
    private ImageIcon dashboardIcon = new ImageIcon("src/img/dashboard-01.jpg");
    private ImageIcon watchlistIcon = new ImageIcon("src/img/watchlist-01.jpg");
    private ImageIcon ordersIcon = new ImageIcon("src/img/order-01.jpg");
    private ImageIcon mainIcon = new ImageIcon("src/img/mainLogo-01.png");
    private ImageIcon logoutIcon = new ImageIcon("src/img/logout-01.jpg");

    //images when selected
    private ImageIcon portfolioIconS = new ImageIcon("src/img/portfolioPress-01.jpg");
    private ImageIcon dashboardIconS = new ImageIcon("src/img/dashboardPress-01.jpg");
    private ImageIcon watchlistIconS = new ImageIcon("src/img/watchPress-01.jpg");
    private ImageIcon ordersIconS = new ImageIcon("src/img/orderPress-01.jpg");
    private ImageIcon logoutIconS = new ImageIcon("src/img/logoutPress-01.jpg");


    public Screen(){
        // Setting up the frame and panels
        frame = new JFrame();
        panel = new JPanel();
        logoutPane = new JPanel();
        sidebarPanel = new JPanel();

        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 800));
        logoutPane.setBorder(BorderFactory.createEmptyBorder(500, 0, 0, 0));

        // Resizing the size of Main Logo
        Image mainImg = mainIcon.getImage();
        Image mainScale = mainImg.getScaledInstance(200, 150,  Image.SCALE_SMOOTH);
        ImageIcon newMainIcon = new ImageIcon(mainScale);
        logo.setIcon(newMainIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        // Setting up the sidebar buttons
        changeButton(portfolioIcon, portfolioButton);
        changeButton(dashboardIcon, dashboardButton);
        changeButton(watchlistIcon, watchlistButton);
        changeButton(ordersIcon, ordersButton);
        changeButton(logoutIcon, logoutButton);

        // Setting the alignment
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        portfolioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        watchlistButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ordersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding each of the buttons to the sidebar
        sidebarPanel.add(logo);
        sidebarPanel.add(dashboardButton);
        sidebarPanel.add(portfolioButton);
        sidebarPanel.add(watchlistButton);
        sidebarPanel.add(ordersButton);

        // Adding logout pane to sidebar
        logoutPane.add(logoutButton);
        sidebarPanel.add(logoutPane);

        // Background Colour
        Color baseBlue = new Color(0, 140, 237);
        sidebarPanel.setBackground(baseBlue);
        logoutPane.setBackground(baseBlue);

        // Adding the panes to the final sidebar frame
        frame.add(panel, BorderLayout.EAST);
        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.setTitle("Venda - Dashboard");
        frame.pack();
        frame.setVisible(true);

        dashboardButton.addActionListener(this);
        portfolioButton.addActionListener(this);
        watchlistButton.addActionListener(this);
        ordersButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }



    public static void main(String[] args){
        new Screen();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == dashboardButton){
            System.out.println("Dashboard GUI");

            // Changing the image for the button
            changeButton(dashboardIconS, dashboardButton);
            // Changing each of the other buttons
            changeButton(ordersIcon, ordersButton);
            changeButton(portfolioIcon, portfolioButton);
            changeButton(watchlistIcon, watchlistButton);
            changeButton(logoutIcon, logoutButton);
        }
        else if (e.getSource() == portfolioButton){
            System.out.println("Portfolio GUI");
            frame.remove(panel);
            panel.removeAll();
            frame.add(new portfolioGUI(), BorderLayout.CENTER);
            frame.setTitle("Portfolio");
            frame.pack();
            panel.setVisible(true);

            // Changing the image for the button
            changeButton(portfolioIconS, portfolioButton);
            // Changing each of the other buttons
//            changeButton(dashboardIcon, dashboardButton);
//            changeButton(ordersIcon, ordersButton);
//            changeButton(watchlistIcon, watchlistButton);
//            changeButton(logoutIcon, logoutButton);
        }
        else if (e.getSource() == watchlistButton){
            System.out.println("Watchlist GUI");

            // Changing the image for the button
            changeButton(watchlistIconS, watchlistButton);
            // Changing each of the other buttons
//            changeButton(portfolioIcon, portfolioButton);
//            changeButton(dashboardIcon, dashboardButton);
//            changeButton(ordersIcon, ordersButton);
//            changeButton(logoutIcon, logoutButton);
        }
        else if (e.getSource() == ordersButton){
            System.out.println("Orders GUI");

            // Changing the image for the button
            changeButton(ordersIconS, ordersButton);
            // Changing each of the other buttons
//            changeButton(portfolioIcon, portfolioButton);
//            changeButton(dashboardIcon, dashboardButton);
//            changeButton(watchlistIcon, watchlistButton);
//            changeButton(logoutIcon, logoutButton);
        }
        else if (e.getSource() == logoutButton){
            System.out.println("Logout GUI");

            // Changing the image for the button
            changeButton(logoutIconS, logoutButton);
            // Changing each of the other buttons
//            changeButton(portfolioIcon, portfolioButton);
//            changeButton(dashboardIcon, dashboardButton);
//            changeButton(ordersIcon, ordersButton);
//            changeButton(watchlistIcon, watchlistButton);
        }
    }


    private static JButton createSimpleButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }

    private static void changeButton(ImageIcon change, JButton button){
        Image changed = change.getImage();
        Image changedScale = changed.getScaledInstance(300, 50,  Image.SCALE_SMOOTH);
        ImageIcon newButtonIcon = new ImageIcon(changedScale);
        button.setIcon(newButtonIcon);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
    }
}

