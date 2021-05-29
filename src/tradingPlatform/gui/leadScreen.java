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

    // Buttons
    private JLabel logo = new JLabel();
    private JButton logoutButton = new JButton();
    private JButton requestButton = new JButton();

    // Images when unselected
    private ImageIcon mainIcon = new ImageIcon("src/img/mainLogo-01.png");
    private ImageIcon logoutIcon = new ImageIcon("src/img/logout-01.jpg");
    private ImageIcon requestIcon = new ImageIcon("src/img/request-01.png");

    // Images when selected
    private ImageIcon logoutIconS = new ImageIcon("src/img/logoutPress-01.jpg");
    private ImageIcon requestIconS = new ImageIcon("src/img/request-Press-01.png");

    public leadScreen() throws SQLException {
        initUI();
        addButtonListeners();
        frame.addWindowListener(new ClosingListener());
    }

    private void addButtonListeners() {
        requestButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    private void initUI() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(1380, 1050));
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
        logoutPane.setBorder(BorderFactory.createEmptyBorder(500, 0, 0, 0));

        // Resizing the size of Main Logo
        Image mainImg = mainIcon.getImage();
        Image mainScale = mainImg.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon newMainIcon = new ImageIcon(mainScale);
        logo.setIcon(newMainIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        // Setting up the sidebar buttons
        changeButton(requestIcon, requestButton);
        changeButton(logoutIcon, logoutButton);

        // Setting the alignment
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        requestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding each of the buttons to the sidebar
        sidebarPanel.add(logo);
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
        frame.setTitle("Venda - Admin");
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == requestButton) {
//            System.out.println("Orders GUI");
            frame.setTitle("Requests");
            frame.remove(panel);
            panel.removeAll();
            panel = new requestGUI();
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            panel.setVisible(true);
        } else if (e.getSource() == logoutButton) {
            System.out.println("Logout GUI");
            // insert reset functions
            Main.resetCurrentUser();
            new loginGUI();
            frame.dispose();
        }
    }

}
