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


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

    int count = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JPanel sidebarPanel;

    // Buttons and icons
    private JLabel logo = new JLabel();
    private JButton portfolioButton = new JButton("Portfolio");
    private JButton dashboardButton = new JButton("Dashboard");
    private JButton watchlistButton = new JButton("Watchlist");
    private JButton ordersButton = new JButton("Orders");


    public dashboardGUI(){
        frame = new JFrame();
        panel = new JPanel();

        sidebarPanel = new JPanel();
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 400, 0));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

//        logo.setIcon(new ImageIcon("src/img/logo.png"));
        sidebarPanel.add(logo);
//        portfolioButton.setIcon(new ImageIcon())

        portfolioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        watchlistButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ordersButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        portfolioButton.setBackground(Color.blue);
        dashboardButton.setBackground(Color.blue);
        watchlistButton.setBackground(Color.blue);
        ordersButton.setBackground(Color.blue);

        portfolioButton.setPreferredSize(new Dimension(100, 10));

//        portfolioButton.setBorder(new );
        portfolioButton.setBorder(null);

//        Border line = new LineBorder(Color.BLACK);
//        Border margin = new EmptyBorder(30, 100, 30, 100);
//        Border compound = new CompoundBorder(line, margin);
//        portfolioButton.setBorder(compound);

        portfolioButton.setFocusPainted(true);
        dashboardButton.setFocusPainted(false);
        watchlistButton.setFocusPainted(false);
        ordersButton.setFocusPainted(false);


        sidebarPanel.add(portfolioButton);
        sidebarPanel.add(dashboardButton);
        sidebarPanel.add(watchlistButton);
        sidebarPanel.add(ordersButton);


        JButton button = new JButton("Home");
        button.addActionListener(this);

        label = new JLabel("Hello, welcome Steve");

        panel.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        panel.setLayout(new GridLayout(0,1));
//        panel.add(button);
//        panel.add(label);

        Color baseBlue = new Color(0, 140, 237);
        sidebarPanel.setBackground(baseBlue);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.setTitle("Dashboard");
        frame.pack();

        frame.setVisible(true);

    }



    public static void main(String[] args){
        new dashboardGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        count++;
        label.setText("Number of clicks: " + count);
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
