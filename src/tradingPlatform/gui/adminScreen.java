/**
 * @author Natalie Smith
 */
package tradingPlatform.gui;

import tradingPlatform.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * Initiates the user interface for the Admin screen. Can only be called when the usertype
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
    private JButton requestButton = new JButton();

    // Images when unselected
    private ImageIcon mainIcon = new ImageIcon("src/img/mainLogo-01.png");
    private ImageIcon logoutIcon = new ImageIcon("src/img/logout-01.jpg");
    private ImageIcon usersIcon = new ImageIcon("src/img/users-01.png");
    private ImageIcon unitsIcon = new ImageIcon("src/img/units-01.png");
    private ImageIcon assetsIcon = new ImageIcon("src/img/assets-01.png");
    private ImageIcon requestIcon = new ImageIcon("src/img/request-01.png");

    // Images when selected
    private ImageIcon logoutIconS = new ImageIcon("src/img/logoutPress-01.jpg");
    private ImageIcon usersIconS = new ImageIcon("src/img/userPress-01.png");
    private ImageIcon unitsIconS = new ImageIcon("src/img/unitPress-01.png");
    private ImageIcon assetsIconS = new ImageIcon("src/img/assetPress-01.png");
    private ImageIcon requestIconS = new ImageIcon("src/img/request-Press-01.png");

    public adminScreen() throws SQLException {
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
//        panel.setPreferredSize(new Dimension(1380, 1050));

        adminSidebar();
        setupAdminFrame();
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
    }


    /**
     * Makes a JFrame that would initialise and set up the Admin UI window. This
     * code is purely to create and setup up the frame and panels. The frame would
     * add the main sidebar panel - with BorderLayout - to keep on the WEST of the frame,
     * whilst the main panel (a.k.a. panel) would be set up to sit on the east of the frame.
     * The title of the window is set to Admin, and all of the data would be packed to return
     * a frame to the main constructor.
     *
     * @return a frame containing each the main panel and sidebar panel
     */
    private JFrame setupAdminFrame() {
        // Initialize the frame for the admin UI
        frame = new JFrame();

        // Adding the panes to the final sidebar frame
        frame.add(panel, BorderLayout.EAST);
        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.setTitle("Venda - Admin");
        frame.pack();
        frame.setVisible(true);
        return frame;
    }


    private void adminSidebar() {
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
//        panel.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 970));
        sidebarPanel.setPreferredSize(new Dimension(310, 1000));
//        panel = new requestGUI();
        logoutPane.setBorder(BorderFactory.createEmptyBorder(500, 0, 0, 0));

        // Resizing the size of Main Logo
        Image mainImg = mainIcon.getImage();
        Image mainScale = mainImg.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon newMainIcon = new ImageIcon(mainScale);
        logo.setIcon(newMainIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        // Setting up the sidebar buttons
//        changeButton(requestIcon, requestButton);
        changeButton(usersIcon, userButton);
        changeButton(unitsIcon, unitButton);
        changeButton(assetsIcon, assetButton);
        changeButton(logoutIcon, logoutButton);
        changeButton(requestIcon, requestButton);

        // Setting the alignment
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        unitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        assetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        requestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding each of the buttons to the sidebar
        sidebarPanel.add(logo);
        sidebarPanel.add(requestButton);
        sidebarPanel.add(userButton);
        sidebarPanel.add(unitButton);
        sidebarPanel.add(assetButton);

        // Adding logout pane to sidebar
        logoutPane.add(logoutButton, BorderLayout.SOUTH);
        sidebarPanel.add(logoutPane, BorderLayout.SOUTH);

        // Background Colour
        Color baseBlue = new Color(0, 140, 237);
        sidebarPanel.setBackground(baseBlue);
        logoutPane.setBackground(baseBlue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sidebarListeners(e);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     *
     */
    public void removePrevious(){
        frame.remove(panel);
        panel.removeAll();
    }


    public void sidebarListeners(ActionEvent e) throws Exception {
        if (e.getSource() == userButton) {
            removePrevious();
            panel = new userGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Lead - Users");
            frame.pack();
            panel.setVisible(true);

            // Changing the image for the button
            changeButton(usersIconS, userButton);
            changeButton(unitsIcon, unitButton);
            changeButton(assetsIcon, assetButton);
            changeButton(requestIcon, requestButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == unitButton) {
            removePrevious();
            panel = new unitGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Lead - Unit");
            frame.pack();
            panel.setVisible(true);

            // Changing the image for the button
            changeButton(usersIcon, userButton);
            changeButton(unitsIconS, unitButton);
            changeButton(assetsIcon, assetButton);
            changeButton(requestIcon, requestButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == assetButton) {
            removePrevious();
            panel = new assetGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Lead - Asset");
            frame.pack();
            panel.setVisible(true);
            // Changing the image for the button
            changeButton(usersIcon, userButton);
            changeButton(unitsIcon, unitButton);
            changeButton(assetsIconS, assetButton);
            changeButton(requestIcon, requestButton);
            changeButton(logoutIcon, logoutButton);
        } else if (e.getSource() == requestButton) {
            removePrevious();
            panel = new requestGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.setTitle("Lead - Request");
            frame.pack();
            panel.setVisible(true);
            // Changing the image for the button
            changeButton(usersIcon, userButton);
            changeButton(unitsIcon, unitButton);
            changeButton(assetsIcon, assetButton);
            changeButton(requestIconS, requestButton);
            changeButton(logoutIcon, logoutButton);
        }
        if (e.getSource() == logoutButton) {
            // insert reset functions
            Main.resetCurrentUser();
            new loginGUI();
            frame.dispose();
        }
    }

}
