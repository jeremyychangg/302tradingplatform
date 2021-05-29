package tradingPlatform.gui;

import tradingPlatform.Main;
import tradingPlatform.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class employeeScreen extends Screen {
    private JFrame frame;
    private JPanel panel;
    private JPanel sidebarPanel;
    private JPanel logoutPane;
    private JScrollPane pane;

    // Buttons
    private final JLabel logo = new JLabel();
    private JButton portfolioButton = new JButton();
    private JButton dashboardButton = new JButton();
    private JButton watchlistButton = new JButton();
    private JButton ordersButton = new JButton();
    private JButton logoutButton = new JButton();

    //images when unselected
    private final ImageIcon portfolioIcon = new ImageIcon("src/img/portfolio-01.jpg");
    private final ImageIcon dashboardIcon = new ImageIcon("src/img/dashboard-01.jpg");
    private final ImageIcon watchlistIcon = new ImageIcon("src/img/watchlist-01.jpg");
    private final ImageIcon ordersIcon = new ImageIcon("src/img/order-01.jpg");
    private final ImageIcon mainIcon = new ImageIcon("src/img/mainLogo-01.png");
    private final ImageIcon logoutIcon = new ImageIcon("src/img/logout-01.jpg");

    //images when selected
    private final ImageIcon portfolioIconS = new ImageIcon("src/img/portfolioPress-01.jpg");
    private final ImageIcon dashboardIconS = new ImageIcon("src/img/dashboardPress-01.jpg");
    private final ImageIcon watchlistIconS = new ImageIcon("src/img/watchPress-01.jpg");
    private final ImageIcon ordersIconS = new ImageIcon("src/img/orderPress-01.jpg");
    private final ImageIcon logoutIconS = new ImageIcon("src/img/logoutPress-01.jpg");

    public employeeScreen() throws SQLException {
        initUI();
        addButtonListeners();

//        System.out.println("Sidebar " + sidebarPanel.getHeight());
        System.out.println(frame.getWidth());
//        System.out.println(sidebarPanel.getWidth());
        System.out.println(frame.getHeight());

        frame.addWindowListener(new ClosingListener());
    }


    private void initUI() throws SQLException {
        // Setting up the frame and panels
        // panel = new JPanel();
        panel = new dashboardGUI();
        logoutPane = new JPanel();
        sidebarPanel = new JPanel();
//        panel.setPreferredSize(new Dimension(1380, 1050));

        // Background Colour
        Color baseBlue = new Color(0, 140, 237);
        sidebarPanel.setBackground(baseBlue);
        logoutPane.setBackground(baseBlue);

        employeeSidebar();
        setupEmployeeFrame();
    }

    private JFrame setupEmployeeFrame(){
        frame = new JFrame();

        // Adding the panes to the final sidebar frame
        frame.add(panel, BorderLayout.EAST);
        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.setTitle("Venda");
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    private void addButtonListeners(){
        dashboardButton.addActionListener(this);
        portfolioButton.addActionListener(this);
        watchlistButton.addActionListener(this);
        ordersButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }


    private void employeeSidebar(){
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(310, 1050));
        logoutPane.setBorder(BorderFactory.createEmptyBorder(500, 0, 0, 0));

        // Resizing the size of Main Logo
        Image mainImg = mainIcon.getImage();
        Image mainScale = mainImg.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon newMainIcon = new ImageIcon(mainScale);
        logo.setIcon(newMainIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        // Setting up the sidebar buttons
        changeButton(portfolioIcon, portfolioButton);
        changeButton(dashboardIconS, dashboardButton);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dashboardButton) {
//            System.out.println("Dashboard GUI");
            frame.remove(panel);
            frame.remove(pane);
            panel.removeAll();
            try {
                panel = new dashboardGUI();
                frame.add(panel, BorderLayout.CENTER);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            frame.setTitle("Dashboard");
            frame.pack();
            panel.setVisible(true);

            // Changing the image for the button
//            changeButton(dashboardIconS, dashboardButton);
//            changeButton(ordersIcon, ordersButton);
//            changeButton(portfolioIcon, portfolioButton);
//            changeButton(watchlistIcon, watchlistButton);
//            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == portfolioButton) {
//            System.out.println("Portfolio GUI");
            frame.remove(panel);
            panel.removeAll();
            try {
                panel = new portfolioGUI();
                pane = new JScrollPane(panel,
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                pane.setPreferredSize(new Dimension(1380, 1050));
                frame.add(pane, BorderLayout.CENTER);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            frame.setTitle("Portfolio");
            frame.pack();
            panel.setVisible(true);

            // Changing the image for the button
//            changeButton(portfolioIconS, portfolioButton);
//            changeButton(dashboardIcon, dashboardButton);
//            changeButton(ordersIcon, ordersButton);
//            changeButton(watchlistIcon, watchlistButton);
//            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == watchlistButton) {
//            System.out.println("Watchlist GUI");
            frame.setTitle("Watchlist");

            frame.remove(panel);
            panel.removeAll();
            panel = new watchlistGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Watchlist");
            frame.pack();
            panel.setVisible(true);
            // Changing the image for the button
//            changeButton(watchlistIconS, watchlistButton);
//            changeButton(portfolioIcon, portfolioButton);
//            changeButton(dashboardIcon, dashboardButton);
//            changeButton(ordersIcon, ordersButton);
//            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == ordersButton) {
//            System.out.println("Orders GUI");
            frame.setTitle("Orders");
            frame.remove(panel);
            panel.removeAll();
            panel = new orderGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            panel.setVisible(true);
            // Changing the image for the button
//            changeButton(ordersIconS, ordersButton);
//            changeButton(portfolioIcon, portfolioButton);
//            changeButton(dashboardIcon, dashboardButton);
//            changeButton(watchlistIcon, watchlistButton);
//            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == logoutButton) {
            System.out.println("Logout GUI");
            // insert reset functions
            Main.resetCurrentUser();
            new loginGUI();
            frame.dispose();
            System.out.println(Main.getCurrentUser());
        }
    }

    public static void creditBalancePanel(JPanel panel) throws SQLException {
        Font heading = new Font("Avenir", Font.PLAIN, 50);

        JPanel creditsPanel = new JPanel();
        JPanel ordersPanel = new JPanel();

        JLabel creditsUserLabel = new JLabel("Credit Balance");
        JLabel creditsUser = new JLabel(Float.toString(User.getCredits()));
        JLabel outstandingLabel = new JLabel("Outstanding Units");
        JLabel outstandingUser = new JLabel("5");

        outstandingUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        outstandingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsUserLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        creditsUser.setFont(heading);
        outstandingUser.setFont(heading);

        creditsPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        ordersPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));

        creditsPanel.add(creditsUser);
        creditsPanel.add(creditsUserLabel);
        ordersPanel.add(outstandingUser);
        ordersPanel.add(outstandingLabel);

        panel.add(creditsPanel);
        panel.add(ordersPanel);

        creditsPanel.setBackground(Color.white);
        ordersPanel.setBackground(Color.white);
        panel.setBackground(Color.white);
    }
}
