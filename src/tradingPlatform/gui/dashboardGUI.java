package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class dashboardGUI implements ActionListener {
    int count = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    public dashboardGUI(){
        frame = new JFrame();
        panel = new JPanel();

        JButton button = new JButton("Home");
        button.addActionListener(this);

        label = new JLabel("Hello, welcome Steve");

        panel.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        panel.setLayout(new GridLayout(0,1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Dashboard");
        frame.pack();

        frame.setVisible(true);

    }
//    public static void main(String[] args){
//        new dashboardGUI();
//    }

    @Override
    public void actionPerformed(ActionEvent e){
        count++;
        label.setText("Number of clicks: " + count);
    }
}
