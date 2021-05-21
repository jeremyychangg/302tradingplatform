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

    //images
    private ImageIcon portfolioIcon = new ImageIcon("src/img/portfolio-01.jpg");
    private ImageIcon dashboardIcon = new ImageIcon("src/img/dashboard-01.jpg");
    private ImageIcon watchlistIcon = new ImageIcon("src/img/watchlist-01.jpg");
    private ImageIcon ordersIcon = new ImageIcon("src/img/order-01.jpg");
    private ImageIcon mainIcon = new ImageIcon("src/img/mainLogo-01.png");
    private ImageIcon logoutIcon = new ImageIcon("src/img/logout-01.jpg");


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

        // Setting the Portfolio icon image
        Image portImg = portfolioIcon.getImage();
        Image portScale = portImg.getScaledInstance(300, 50,  Image.SCALE_SMOOTH);
        ImageIcon newPortIcon = new ImageIcon(portScale);
        portfolioButton.setIcon(newPortIcon);
        portfolioButton.setBorder(BorderFactory.createEmptyBorder());
        portfolioButton.setContentAreaFilled(false);

        // Setting the Dashboard icon image
        Image dashImg = dashboardIcon.getImage();
        Image dashScale = dashImg.getScaledInstance(300, 50,  Image.SCALE_SMOOTH);
        ImageIcon newDashIcon = new ImageIcon(dashScale);
        dashboardButton.setIcon(newDashIcon);
        dashboardButton.setBorder(BorderFactory.createEmptyBorder());
        dashboardButton.setContentAreaFilled(false);

        // Setting the Watchlist icon image
        Image watchImg = watchlistIcon.getImage();
        Image watchScale = watchImg.getScaledInstance(300, 50,  Image.SCALE_SMOOTH);
        ImageIcon newWatchIcon = new ImageIcon(watchScale);
        watchlistButton.setIcon(newWatchIcon);
        watchlistButton.setBorder(BorderFactory.createEmptyBorder());
        watchlistButton.setContentAreaFilled(false);

        // Setting the Orders Icon Image
        Image orderImg = ordersIcon.getImage();
        Image orderScale = orderImg.getScaledInstance(300, 50,  Image.SCALE_SMOOTH);
        ImageIcon newOrderIcon = new ImageIcon(orderScale);
        ordersButton.setIcon(newOrderIcon);
        ordersButton.setBorder(BorderFactory.createEmptyBorder());
        ordersButton.setContentAreaFilled(false);

        // Setting the Logout Icon Image
        Image logoutImg = logoutIcon.getImage();
        Image logoutScale = logoutImg.getScaledInstance(300, 50,  Image.SCALE_SMOOTH);
        ImageIcon newLogoutIcon = new ImageIcon(logoutScale);
        logoutButton.setIcon(newLogoutIcon);
        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        logoutButton.setContentAreaFilled(false);

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
        frame.add(panel, BorderLayout.CENTER);
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
        }
        else if (e.getSource() == portfolioButton){
            System.out.println("Portfolio GUI");
        }
        else if (e.getSource() == watchlistButton){
            System.out.println("Watchlist GUI");
        }
        else if (e.getSource() == ordersButton){
            System.out.println("Orders GUI");
        }
        else if (e.getSource() == logoutButton){
            System.out.println("Portfolio GUI");
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
}

