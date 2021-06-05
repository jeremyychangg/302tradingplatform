package tradingPlatform.gui.client;

import tradingPlatform.Main;
import tradingPlatform.exceptions.AssetTypeException;
import tradingPlatform.gui.common.Screen;
import tradingPlatform.gui.common.loginGUI;
import tradingPlatform.gui.common.settingsGUI;
import tradingPlatform.user.Lead;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import static tradingPlatform.Main.getCurrentUser;

/**
 * Initiates the user interface for the Lead screen. Can only be called when in login the user
 * is found to be a lead. Listeners are also initiated within this class to ensure
 * the relevant buttons are being used.
 *
 * @author Natalie Smith
 * @version 3.0
 */
public class leadScreen extends Screen {
    Lead currentLead;
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
    private JButton settingsButton = new JButton();
    private JButton requestButton = new JButton();

    // Images when unselected
    private final ImageIcon mainIcon = new ImageIcon("src/img/mainLogo-01.png");
    private final ImageIcon logoutIcon = new ImageIcon("src/img/logout-01.jpg");
    private final ImageIcon requestIcon = new ImageIcon("src/img/request-01.png");
    private final ImageIcon portfolioIcon = new ImageIcon("src/img/portfolio-01.jpg");
    private final ImageIcon dashboardIcon = new ImageIcon("src/img/dashboard-01.jpg");
    private final ImageIcon watchlistIcon = new ImageIcon("src/img/watchlist-01.jpg");
    private final ImageIcon ordersIcon = new ImageIcon("src/img/order-01.jpg");
    private final ImageIcon settingIcon = new ImageIcon("src/img/settings-01.png");

    // Images when selected
    private final ImageIcon logoutIconS = new ImageIcon("src/img/logoutPress-01.jpg");
    private final ImageIcon requestIconS = new ImageIcon("src/img/request-Press-01.png");
    private final ImageIcon portfolioIconS = new ImageIcon("src/img/portfolioPress-01.jpg");
    private final ImageIcon dashboardIconS = new ImageIcon("src/img/dashboardPress-01.jpg");
    private final ImageIcon watchlistIconS = new ImageIcon("src/img/watchPress-01.jpg");
    private final ImageIcon ordersIconS = new ImageIcon("src/img/orderPress-01.jpg");
    private final ImageIcon settingIconS = new ImageIcon("src/img/settingsPress-01.png");


    /**
     * Constructor for the lead screen calls relevant methods to initialise the GUI
     */
    public leadScreen() throws SQLException {
        currentLead = new Lead(getCurrentUser());
        initUI();
        addButtonListeners();
        frame.addWindowListener(new ClosingListener());
    }


    /**
     * Method adds relevant button listeners for the lead screen. When pressed, would trigger the actionListener function.
     */
    private void addButtonListeners() {
        requestButton.addActionListener(this);
        dashboardButton.addActionListener(this);
        portfolioButton.addActionListener(this);
        watchlistButton.addActionListener(this);
        ordersButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }


    /**
     * Method used to initialise the Lead Screen. When run, on load, it sets the panel to the
     * dashboard. On the left side, it also initialises the sidebar buttons and the button action listeners.
     *
     * @throws SQLException Triggered if the dashboardGUI is unable to create GUI based on SQL database input - userID
     */
    private void initUI() throws SQLException {
        panel = new dashboardGUI();
        logoutPane = new JPanel();
        sidebarPanel = new JPanel();

        // Background Colour
        Color baseBlue = new Color(0, 140, 237);
        sidebarPanel.setBackground(baseBlue);
        logoutPane.setBackground(baseBlue);

        leadSidebar();
        this.frame = setupFrame("Venda - Lead", panel, frame, sidebarPanel);
    }


    /**
     * Initialises the lead sidebar, with the relevant elements and buttons. This
     * code is purely to create and setup up the layout and positioning of the buttons within the sidebar.
     * frame and panels. Given the sidebarPanel (which should be initiated already) the portfolio, dashboard,
     * watchlist, orders, settings, requests and logout buttons are placed. For larger screens/smaller screens,
     * the method should resize the padding between the main buttons and the bottom pane.
     *
     * @return a frame containing the lead main panel and sidebar panel
     */
    private void leadSidebar() {
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(310, screenHeight));

        // Resizing the size of Main Logo
        Image mainImg = mainIcon.getImage();
        Image mainScale = mainImg.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon newMainIcon = new ImageIcon(mainScale);
        logo.setIcon(newMainIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        // Setting up the sidebar buttons
        changeButton(requestIcon, requestButton);
        changeButton(portfolioIcon, portfolioButton);
        changeButton(dashboardIconS, dashboardButton);
        changeButton(watchlistIcon, watchlistButton);
        changeButton(ordersIcon, ordersButton);
        changeButton(settingIcon, settingsButton);
        changeButton(logoutIcon, logoutButton);

        // Setting the alignment
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        alignCenter(new JButton[]{portfolioButton, dashboardButton, watchlistButton, ordersButton, requestButton,
                settingsButton, logoutButton});

        // Adding each of the buttons to the sidebar
        sidebarPanel.add(logo);
        sidebarPanel.add(dashboardButton);
        sidebarPanel.add(portfolioButton);
        sidebarPanel.add(watchlistButton);
        sidebarPanel.add(ordersButton);
        sidebarPanel.add(requestButton);

        // Adding logout pane to sidebar
        logoutPane.add(settingsButton);
        logoutPane.add(logoutButton);
        sidebarPanel.add(logoutPane);

        int padding = Screen.screenHeight - 750;
        logoutPane.setBorder(BorderFactory.createEmptyBorder(padding, 0, 0, 0));
    }

    /**
     * Given a button is clicked on the lead graphical user interface, the actionPerformed points
     * the program to the sidebarListeners method to choose an outcome based on the button. Thus, for each button, the
     * method would remove the previous panel and initialise the relevant GUI associated with the button, alongside
     * setting other properties such as the title of the frame. Additionally, to make the user aware of their position
     * on the GUI, the program would change the button image to match the action - and clear buttons that are not the
     * current page.
     * @param e
     */
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
            frame.setTitle("Lead - Dashboard");
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
                pane.setPreferredSize(new Dimension(screenWidth + 10, screenHeight));
                frame.add(pane, BorderLayout.CENTER);
            } catch (Exception throwable) {
                throwable.printStackTrace();
            }
            frame.setTitle("Lead - Portfolio");
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
            } catch (AssetTypeException assetTypeException) {
                assetTypeException.printStackTrace();
            }
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Lead - Watchlist");
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

            frame.setTitle("Lead - Orders");
            try {
                panel = new orderGUI();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
            frame.setTitle("Lead - Requests");
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
        } else if (e.getSource() == settingsButton) {
            removePrevious();
            try {
                panel = new settingsGUI();
            } catch (Exception exception) {
            }
            frame.setTitle("Lead - Settings");
            try {
                panel = new settingsGUI();
            } catch (Exception exception) {
            }
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            panel.setVisible(true);
            // Changing the image for the button
            changeButton(ordersIcon, ordersButton);
            changeButton(portfolioIcon, portfolioButton);
            changeButton(dashboardIcon, dashboardButton);
            changeButton(watchlistIcon, watchlistButton);
            changeButton(settingIconS, settingsButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == logoutButton) {
            // insert reset functions
            Main.resetCurrentUser();
            new loginGUI();
            frame.dispose();
        }
    }


    /**
     * When this method is called, would remove the current panel within the frame, and also removes the elements
     * within the panel. Used to switch between panel selection.
     */
    public void removePrevious() {
        frame.remove(panel);
        frame.remove(pane);
        panel.removeAll();
    }
}