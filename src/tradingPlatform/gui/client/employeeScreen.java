/**
 * @author Natalie Smith
 */
package tradingPlatform.gui.client;

import tradingPlatform.Asset;
import tradingPlatform.Main;
import tradingPlatform.exceptions.AssetTypeException;
import tradingPlatform.exceptions.InvalidAssetException;
import tradingPlatform.exceptions.UnitException;
import tradingPlatform.gui.common.Screen;
import tradingPlatform.gui.common.loginGUI;
import tradingPlatform.gui.common.settingsGUI;
import tradingPlatform.gui.server.requestAdminGUI;
import tradingPlatform.user.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import static tradingPlatform.Main.getCurrentUser;

/**
 * Initiates the user interface for the Employee screen. Can only be called when in login the user
 * is found to be an employee. Listeners are also initiated within this class to ensure
 * the relevant buttons are being used.
 */
public class employeeScreen extends Screen {
    Admin currentAdmin;
    private JFrame frame;
    private JPanel panel;
    private JPanel sidebarPanel;
    private JPanel logoutPane;
    private JScrollPane pane = new JScrollPane();

    // Buttons
    private final JLabel logo = new JLabel();
    private JButton portfolioButton = new JButton();
    private JButton dashboardButton = new JButton();
    private JButton watchlistButton = new JButton();
    private JButton ordersButton = new JButton();
    private JButton settingsButton = new JButton();
    private JButton logoutButton = new JButton();

    // Button images when unselected
    private final ImageIcon portfolioIcon = new ImageIcon("src/img/portfolio-01.jpg");
    private final ImageIcon dashboardIcon = new ImageIcon("src/img/dashboard-01.jpg");
    private final ImageIcon watchlistIcon = new ImageIcon("src/img/watchlist-01.jpg");
    private final ImageIcon ordersIcon = new ImageIcon("src/img/order-01.jpg");
    private final ImageIcon mainIcon = new ImageIcon("src/img/mainLogo-01.png");
    private final ImageIcon settingIcon = new ImageIcon("src/img/settings-01.png");
    private final ImageIcon logoutIcon = new ImageIcon("src/img/logout-01.jpg");

    // Button images when selected
    private final ImageIcon portfolioIconS = new ImageIcon("src/img/portfolioPress-01.jpg");
    private final ImageIcon dashboardIconS = new ImageIcon("src/img/dashboardPress-01.jpg");
    private final ImageIcon watchlistIconS = new ImageIcon("src/img/watchPress-01.jpg");
    private final ImageIcon ordersIconS = new ImageIcon("src/img/orderPress-01.jpg");
    private final ImageIcon settingIconS = new ImageIcon("src/img/settingsPress-01.png");


    /**
     * Constructor for the employee screen calls relevant methods to initialise the GUI
     */
    public employeeScreen() throws SQLException {
        currentAdmin = new Admin(getCurrentUser());
        initUI();
        addButtonListeners();
        frame.addWindowListener(new ClosingListener());
    }


    /**
     * Method used to initialise the Employee Screen. When run, on load, it sets the panel
     * to the dashboard. On the left side, it also initialises the sidebar buttons and the
     * button action listeners.
     *
     * @throws SQLException Triggered if the dashboardGUI is unable to create GUI based on SQL database input - userID
     */
    private void initUI() throws SQLException {
        // Setting up the frame and panels
        panel = new dashboardGUI();
        logoutPane = new JPanel();
        sidebarPanel = new JPanel();

        // Background Colour
        Color baseBlue = new Color(0, 140, 237);
        sidebarPanel.setBackground(baseBlue);
        logoutPane.setBackground(baseBlue);

        employeeSidebar();
        this.frame = setupFrame("Venda - Employee", panel, frame, sidebarPanel);
    }


    /**
     * Method adds relevant button listeners for the employee screen. When pressed, would trigger the actionListener
     * function.
     */
    private void addButtonListeners() {
        dashboardButton.addActionListener(this);
        portfolioButton.addActionListener(this);
        watchlistButton.addActionListener(this);
        ordersButton.addActionListener(this);
        settingsButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }


    /**
     * Initialises the employee sidebar, with the relevant elements and buttons. This
     * code is purely to create and setup up the layout and positioning of the buttons within the sidebar.
     * frame and panels. Given the sidebarPanel (which should be initiated already) the portfolio, dashboard,
     * watchlist, orders, settings and logout buttons are placed. For larger screens/smaller screens, the method
     * should resize the padding between the main buttons and the bottom pane.
     *
     * @return a frame containing the lead main panel and sidebar panel
     */
    private void employeeSidebar() {
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
        changeButton(portfolioIcon, portfolioButton);
        changeButton(dashboardIconS, dashboardButton);
        changeButton(watchlistIcon, watchlistButton);
        changeButton(ordersIcon, ordersButton);
        changeButton(settingIcon, settingsButton);
        changeButton(logoutIcon, logoutButton);

        // Setting the alignment
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        alignCenter(new JButton[]{portfolioButton, dashboardButton, watchlistButton, ordersButton,
                settingsButton, logoutButton});

        // Adding each of the buttons to the sidebar
        sidebarPanel.add(logo);
        sidebarPanel.add(dashboardButton);
        sidebarPanel.add(portfolioButton);
        sidebarPanel.add(watchlistButton);
        sidebarPanel.add(ordersButton);

        // Adding logout pane to sidebar
        logoutPane.add(settingsButton);
        logoutPane.add(logoutButton);
        sidebarPanel.add(logoutPane);

        int padding = Screen.screenHeight - 700;
        logoutPane.setBorder(BorderFactory.createEmptyBorder(padding, 0, 0, 0));
    }


    /**
     * When an actionListener is triggered - via, in this case, mostly button event listeners - this method is
     * triggered to change the panel display and also change the styling of the button to show what page the user is
     * on.
     *
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
            }
            frame.setTitle("Employee - Dashboard");
            frame.pack();
            panel.setVisible(true);

            // Changing the image for the button
            changeButton(dashboardIconS, dashboardButton);
            changeButton(ordersIcon, ordersButton);
            changeButton(portfolioIcon, portfolioButton);
            changeButton(watchlistIcon, watchlistButton);
            changeButton(settingIcon, settingsButton);
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
            }
            frame.setTitle("Employee - Portfolio");
            frame.pack();
            panel.setVisible(true);

            // Changing the image for the button
            changeButton(portfolioIconS, portfolioButton);
            changeButton(dashboardIcon, dashboardButton);
            changeButton(ordersIcon, ordersButton);
            changeButton(watchlistIcon, watchlistButton);
            changeButton(settingIcon, settingsButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == watchlistButton) {
            removePrevious();

            try {
                panel = new watchlistGUI();
            } catch (AssetTypeException assetTypeException) {
            } catch (Exception throwables) {
                System.out.println(throwables.getMessage());
                throwables.printStackTrace();
            }
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Employee - Watchlist");
            frame.pack();
            panel.setVisible(true);
            // Changing the image for the button
            changeButton(watchlistIconS, watchlistButton);
            changeButton(portfolioIcon, portfolioButton);
            changeButton(dashboardIcon, dashboardButton);
            changeButton(ordersIcon, ordersButton);
            changeButton(settingIcon, settingsButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == ordersButton) {
            removePrevious();

            frame.setTitle("Employee - Orders");
            String assetID = JOptionPane.showInputDialog(null,"Search Asset");
            try {
                Asset orderAsset = Asset.findAsset(assetID);
                panel = new orderGUI(Main.getCurrentUnit(), orderAsset.GetAssetID());
            } catch (InvalidAssetException exception){
                JOptionPane.showMessageDialog(null, exception.getMessage());
                panel = new requestAdminGUI();
            } catch (SQLException | UnitException throwables) {
                JOptionPane.showMessageDialog(null, e.getSource());
            }
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            panel.setVisible(true);
            // Changing the image for the button
            changeButton(ordersIconS, ordersButton);
            changeButton(portfolioIcon, portfolioButton);
            changeButton(dashboardIcon, dashboardButton);
            changeButton(watchlistIcon, watchlistButton);
            changeButton(settingIcon, settingsButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == settingsButton) {
            removePrevious();

            frame.setTitle("Employee - Settings");
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
