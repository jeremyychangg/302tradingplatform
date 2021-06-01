package tradingPlatform.gui;

import tradingPlatform.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class leadScreen extends Screen {
    private JFrame frame;
    private JPanel panel;
    private JPanel sidebarPanel;
    private JPanel logoutPane;
    private JScrollPane pane = new JScrollPane();

    // Buttons
    private JLabel logo = new JLabel();
    private JButton logoutButton = new JButton();
    private JButton portfolioButton = new JButton();
    private JButton dashboardButton = new JButton();
    private JButton watchlistButton = new JButton();
    private JButton ordersButton = new JButton();
    private JButton requestButton = new JButton();

    // Images when unselected
    private final ImageIcon mainIcon = new ImageIcon("src/img/mainLogo-01.png");
    private final ImageIcon logoutIcon = new ImageIcon("src/img/logout-01.jpg");
    private final ImageIcon requestIcon = new ImageIcon("src/img/request-01.png");
    private final ImageIcon portfolioIcon = new ImageIcon("src/img/portfolio-01.jpg");
    private final ImageIcon dashboardIcon = new ImageIcon("src/img/dashboard-01.jpg");
    private final ImageIcon watchlistIcon = new ImageIcon("src/img/watchlist-01.jpg");
    private final ImageIcon ordersIcon = new ImageIcon("src/img/order-01.jpg");

    // Images when selected
    private final ImageIcon logoutIconS = new ImageIcon("src/img/logoutPress-01.jpg");
    private final ImageIcon requestIconS = new ImageIcon("src/img/request-Press-01.png");
    private final ImageIcon portfolioIconS = new ImageIcon("src/img/portfolioPress-01.jpg");
    private final ImageIcon dashboardIconS = new ImageIcon("src/img/dashboardPress-01.jpg");
    private final ImageIcon watchlistIconS = new ImageIcon("src/img/watchPress-01.jpg");
    private final ImageIcon ordersIconS = new ImageIcon("src/img/orderPress-01.jpg");

    public leadScreen() throws SQLException {
        initUI();
        addButtonListeners();
        frame.addWindowListener(new ClosingListener());
    }

    private void addButtonListeners() {
        requestButton.addActionListener(this);
        dashboardButton.addActionListener(this);
        portfolioButton.addActionListener(this);
        watchlistButton.addActionListener(this);
        ordersButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    private void initUI() throws SQLException {
        panel = new dashboardGUI();
//        panel.setPreferredSize(new Dimension(1380, 1050));
//        panel = new requestGUI();
        logoutPane = new JPanel();
        sidebarPanel = new JPanel();

        // Background Colour
        Color baseBlue = new Color(0, 140, 237);
        sidebarPanel.setBackground(baseBlue);
        logoutPane.setBackground(baseBlue);


        leadSidebar();
        setupLeadFrame();
    }

    private void leadSidebar() {
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(310, 1000));
        logoutPane.setBorder(BorderFactory.createEmptyBorder(440, 0, 0, 0));

        // Resizing the size of Main Logo
        Image mainImg = mainIcon.getImage();
        Image mainScale = mainImg.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon newMainIcon = new ImageIcon(mainScale);
        logo.setIcon(newMainIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        // Setting up the sidebar buttons
        changeButton(requestIcon, requestButton);
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
        requestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding each of the buttons to the sidebar
        sidebarPanel.add(logo);
        sidebarPanel.add(dashboardButton);
        sidebarPanel.add(portfolioButton);
        sidebarPanel.add(watchlistButton);
        sidebarPanel.add(ordersButton);
        sidebarPanel.add(requestButton);

        // Adding logout pane to sidebar
        logoutPane.add(logoutButton);
        sidebarPanel.add(logoutPane);
    }

    private JFrame setupLeadFrame() {
        // Setting up the frame and panels
        frame = new JFrame();

        // Adding the panes to the final sidebar frame
        frame.add(panel, BorderLayout.EAST);
        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.setTitle("Venda - Lead");
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dashboardButton) {
            removePrevious();
            try {
                panel = new dashboardGUI();
                frame.add(panel, BorderLayout.CENTER);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            frame.setTitle("Dashboard");
            frame.pack();
            panel.setVisible(true);

            // Changing the image for the button
            changeButton(dashboardIconS, dashboardButton);
            changeButton(ordersIcon, ordersButton);
            changeButton(portfolioIcon, portfolioButton);
            changeButton(watchlistIcon, watchlistButton);
            changeButton(requestIcon, requestButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == portfolioButton) {
            removePrevious();
            try {
                panel = new portfolioGUI();
                pane = new JScrollPane(panel,
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                pane.getVerticalScrollBar().setUnitIncrement(7);
                pane.setPreferredSize(new Dimension(1390, 1060));
                frame.add(pane, BorderLayout.CENTER);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            frame.setTitle("Portfolio");
            frame.pack();
            panel.setVisible(true);

            // Changing the image for the button
            changeButton(portfolioIconS, portfolioButton);
            changeButton(dashboardIcon, dashboardButton);
            changeButton(ordersIcon, ordersButton);
            changeButton(watchlistIcon, watchlistButton);
            changeButton(requestIcon, requestButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == watchlistButton) {
            removePrevious();

            try {
                panel = new watchlistGUI();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Watchlist");
            frame.pack();
            panel.setVisible(true);
            // Changing the image for the button
            changeButton(watchlistIconS, watchlistButton);
            changeButton(portfolioIcon, portfolioButton);
            changeButton(dashboardIcon, dashboardButton);
            changeButton(ordersIcon, ordersButton);
            changeButton(requestIcon, requestButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == ordersButton) {
            removePrevious();

            frame.setTitle("Orders");
            panel = new orderGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            panel.setVisible(true);
            // Changing the image for the button
            changeButton(ordersIconS, ordersButton);
            changeButton(portfolioIcon, portfolioButton);
            changeButton(dashboardIcon, dashboardButton);
            changeButton(watchlistIcon, watchlistButton);
            changeButton(requestIcon, requestButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == requestButton) {
            frame.setTitle("Requests");
            frame.remove(panel);
            panel.removeAll();
            panel = new requestGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            panel.setVisible(true);
            // Changing the image for the button
            changeButton(ordersIcon, ordersButton);
            changeButton(portfolioIcon, portfolioButton);
            changeButton(dashboardIcon, dashboardButton);
            changeButton(watchlistIcon, watchlistButton);
            changeButton(requestIconS, requestButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == logoutButton) {
            // insert reset functions
            Main.resetCurrentUser();
            new loginGUI();
            frame.dispose();
        }
    }

    public void removePrevious(){
        frame.remove(panel);
        frame.remove(pane);
        panel.removeAll();
    }
}
