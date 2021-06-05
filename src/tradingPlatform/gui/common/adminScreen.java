/**
 * @author Natalie Smith
 */
package tradingPlatform.gui.common;

import tradingPlatform.Main;
import tradingPlatform.gui.client.requestAdminGUI;
import tradingPlatform.gui.client.requestGUI;
import tradingPlatform.gui.server.assetGUI;
import tradingPlatform.gui.server.unitGUI;
import tradingPlatform.gui.server.userGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Initiates the user interface for the Admin screen. Can only be called when in login the user
 * is found to be an admin. Listeners are also initiated within this class to ensure
 * the relevant buttons are being used.
 */
public class adminScreen extends Screen {
    private JFrame frame;
    private JPanel panel;
    private JPanel sidebarPanel;
    private JPanel logoutPane;

    // Buttons
    private JLabel logo = new JLabel();
    private JButton userButton = new JButton();
    private JButton unitButton = new JButton();
    private JButton assetButton = new JButton();
    private JButton logoutButton = new JButton();
    private JButton settingsButton = new JButton();
    private JButton requestButton = new JButton();

    // Images when unselected
    private ImageIcon mainIcon = new ImageIcon("src/img/mainLogo-01.png");
    private ImageIcon logoutIcon = new ImageIcon("src/img/logout-01.jpg");
    private ImageIcon usersIcon = new ImageIcon("src/img/users-01.png");
    private ImageIcon unitsIcon = new ImageIcon("src/img/units-01.png");
    private ImageIcon assetsIcon = new ImageIcon("src/img/assets-01.png");
    private ImageIcon settingIcon = new ImageIcon("src/img/settings-01.png");
    private ImageIcon requestIcon = new ImageIcon("src/img/request-01.png");

    // Images when selected
    private ImageIcon usersIconS = new ImageIcon("src/img/userPress-01.png");
    private ImageIcon unitsIconS = new ImageIcon("src/img/unitPress-01.png");
    private ImageIcon assetsIconS = new ImageIcon("src/img/assetPress-01.png");
    private ImageIcon requestIconS = new ImageIcon("src/img/request-Press-01.png");
    private ImageIcon settingIconS = new ImageIcon("src/img/settingsPress-01.png");


    /**
     * Constructor for the administration screen calls relevant methods to initialise the GUI
     */
    public adminScreen() {
        initUI();
        addButtonListeners();
        frame.addWindowListener(new ClosingListener());
    }


    /**
     * Initialises the panels used and calls the relative methods to set up the frame.
     * Would essentially be the main code that is used to create each of the elements
     * within the frame. Sets the main panel to a preferred dimension and also initialises
     * the sidebar buttons.
     */
    private void initUI() {
        // Setting up the frame and panels
        panel = new requestGUI();
        logoutPane = new JPanel();
        sidebarPanel = new JPanel();
        adminSidebar();
        this.frame = setupFrame("Venda - Admin", panel, frame, sidebarPanel);
    }


    /**
     * Adds the button listeners relevant to the admin user.
     */
    private void addButtonListeners() {
        logoutButton.addActionListener(this);
        unitButton.addActionListener(this);
        assetButton.addActionListener(this);
        userButton.addActionListener(this);
        requestButton.addActionListener(this);
        settingsButton.addActionListener(this);
    }


    /**
     * Initialises the administration sidebar, alongside their relevant elements and buttons. This
     * code is purely to create and setup up the layout and positioning of the buttons within the sidebar.
     * frame and panels. Given the sidebarPanel (which should be initiated already) the user, unit, asset, logout,
     * settings and requests buttons are placed. For larger screens/smaller screens, the method should resize the
     * padding between the main buttons and the bottom pane.
     *
     * @return a frame containing the admin main panel and sidebar panel
     */
    private void adminSidebar() {
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
        changeButton(usersIcon, userButton);
        changeButton(unitsIcon, unitButton);
        changeButton(assetsIcon, assetButton);
        changeButton(logoutIcon, logoutButton);
        changeButton(settingIcon, settingsButton);
        changeButton(requestIconS, requestButton);

        // Setting the alignment
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        alignCenter(new JButton[]{userButton, unitButton, assetButton, requestButton, logoutButton});

        // Adding each of the buttons to the sidebar
        sidebarPanel.add(logo);
        sidebarPanel.add(requestButton);
        sidebarPanel.add(userButton);
        sidebarPanel.add(unitButton);
        sidebarPanel.add(assetButton);

        // Adding logout pane to sidebar
        logoutPane.add(settingsButton);
        logoutPane.add(logoutButton, BorderLayout.SOUTH);
        sidebarPanel.add(logoutPane, BorderLayout.SOUTH);

        // Adjust padding based on the screen height of the device
        int padding = Screen.screenHeight - 700;
        logoutPane.setBorder(BorderFactory.createEmptyBorder(padding, 0, 0, 0));

        // Background Colour
        Color baseBlue = new Color(0, 140, 237);
        sidebarPanel.setBackground(baseBlue);
        logoutPane.setBackground(baseBlue);
    }


    /**
     * Given a button is clicked (trigggered by the actionListener initiated), the actionPerformed points
     * the program to the sidebarListeners method to choose an outcome based on the button.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sidebarListeners(e);
        } catch (Exception exception) {
        }
    }


    /**
     * Given that the actionPerformed method has been called, and pointed the program to this method, based on the
     * action - the button pressed - it would change the GUI panel to match the event. Thus, for each button, the
     * method would remove the previous panel and initialise the relevant GUI associated with the button, alongside
     * setting other properties such as the title of the frame. Additionally, to make the user aware of their position
     * on the GUI, the program would change the button image to match the action - and clear buttons that are not the
     * current page.
     *
     * @param e
     * @throws Exception
     */
    public void sidebarListeners(ActionEvent e) throws Exception {
        if (e.getSource() == userButton) {
            removePrevious();
            panel = new userGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Admin - Users");
            frame.pack();
            panel.setVisible(true);

            // Change button to show on users
            changeButton(usersIconS, userButton);
            changeButton(unitsIcon, unitButton);
            changeButton(assetsIcon, assetButton);
            changeButton(requestIcon, requestButton);
            changeButton(settingIcon, settingsButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == unitButton) {
            removePrevious();
            panel = new unitGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Admin - Unit");
            frame.pack();
            panel.setVisible(true);

            // Change button to show on Unit
            changeButton(usersIcon, userButton);
            changeButton(unitsIconS, unitButton);
            changeButton(assetsIcon, assetButton);
            changeButton(requestIcon, requestButton);
            changeButton(settingIcon, settingsButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == assetButton) {
            removePrevious();
            panel = new assetGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Admin - Asset");
            frame.pack();
            panel.setVisible(true);

            // Change the image to show the user on assets
            changeButton(usersIcon, userButton);
            changeButton(unitsIcon, unitButton);
            changeButton(assetsIconS, assetButton);
            changeButton(requestIcon, requestButton);
            changeButton(settingIcon, settingsButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == requestButton) {
            removePrevious();
            panel = new requestAdminGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Admin - Request");
            frame.pack();
            panel.setVisible(true);

            // Change the image to show user on requests
            changeButton(usersIcon, userButton);
            changeButton(unitsIcon, unitButton);
            changeButton(assetsIcon, assetButton);
            changeButton(requestIconS, requestButton);
            changeButton(settingIcon, settingsButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == settingsButton) {
            removePrevious();
            frame.setTitle("Admin - Settings");
            try {
                panel = new settingsGUI();
            } catch (Exception exception) {
            }
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            panel.setVisible(true);

            // Changing the image to show on settings
            changeButton(usersIcon, userButton);
            changeButton(unitsIcon, unitButton);
            changeButton(assetsIcon, assetButton);
            changeButton(requestIcon, requestButton);
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
        panel.removeAll();
    }
}