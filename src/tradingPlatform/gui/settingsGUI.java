package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static tradingPlatform.gui.Screen.*;
import static tradingPlatform.user.User.changePassword;

public class settingsGUI extends JPanel implements ActionListener {
    private JPanel panel= new JPanel();;
    private JPasswordField oldPassField;
    private JPasswordField newPassField;
    private JPasswordField reEnterField;
    private JButton updateButton;

    private final JLabel oldPass = new JLabel("Old Password");
    private final JLabel newPass = new JLabel("New Password");
    private final JLabel reEnter = new JLabel("Re-enter Password");

    /**
     * @throws Exception
     */
    public settingsGUI() throws Exception {
        setUpPanel();

        Screen.welcomeMessage(panel);
        settingsSection();

        updateButton.addActionListener(this);
        add(panel);
    }


    /**
     * @throws SQLException
     */
    private void setUpPanel() throws SQLException {
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, (Screen.screenHeight/4), (int) (screenWidth * 0.4)));
//        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    private void settingsSection() {
        JPanel overall = new JPanel();
        overall.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        JPanel settings = new JPanel();
        settings.setBackground(Color.white);


        SpringLayout layout = new SpringLayout();
        settings.setLayout(layout);


        JLabel settingsLabel = new JLabel("Change Password");
        settingsLabel.setFont(Screen.h1);
        panel.add(settingsLabel);

        oldPassField = new JPasswordField(25);
        newPassField = new JPasswordField(25);
        reEnterField = new JPasswordField(25);
        updateButton = new JButton("UPDATE");
        updateButton.setMargin(new Insets(5, 20, 5, 20));
        updateButton.setBackground(new Color(0, 140, 237));
        updateButton.setOpaque(true);
        updateButton.setBorderPainted(false);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);

        settings.add(oldPass);
        settings.add(oldPassField);
        settings.add(newPass);
        settings.add(newPassField);
        settings.add(reEnter);
        settings.add(reEnterField);

        oldPass.setFont(body);
        newPass.setFont(body);
        reEnter.setFont(body);

        layout.putConstraint(SpringLayout.WEST, oldPass, 100, SpringLayout.WEST, settings);
        layout.putConstraint(SpringLayout.NORTH, oldPass, 100, SpringLayout.NORTH, settings);
        layout.putConstraint(SpringLayout.WEST, oldPassField, 40, SpringLayout.EAST, oldPass);
        layout.putConstraint(SpringLayout.NORTH, oldPassField, 100, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, newPass, 100, SpringLayout.WEST, settings);
        layout.putConstraint(SpringLayout.NORTH, newPass, 30, SpringLayout.NORTH, oldPass);
        layout.putConstraint(SpringLayout.WEST, newPassField, 35, SpringLayout.EAST, newPass);
        layout.putConstraint(SpringLayout.NORTH, newPassField, 30, SpringLayout.NORTH, oldPassField);

        layout.putConstraint(SpringLayout.WEST, reEnter, 100, SpringLayout.WEST, settings);
        layout.putConstraint(SpringLayout.NORTH, reEnter, 30, SpringLayout.NORTH, newPass);
        layout.putConstraint(SpringLayout.WEST, reEnterField, 12, SpringLayout.EAST, reEnter);
        layout.putConstraint(SpringLayout.NORTH, reEnterField, 30, SpringLayout.NORTH, newPassField);
        overall.add(settings, BorderLayout.CENTER);

        overall.add(updateButton);

        panel.add(settings, BorderLayout.WEST);
        panel.add(updateButton, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Pressed " +
                String.format(String.valueOf(oldPassField.getPassword())) +
                String.format(String.valueOf(newPassField.getPassword())) +
                String.format(String.valueOf(reEnterField.getPassword())));
        char[] old = oldPassField.getPassword();
        char[] newP = newPassField.getPassword();
        char[] reEnter = reEnterField.getPassword();
        try {
            if (!String.valueOf(oldPassField.getPassword()).equals("")
                    && !String.valueOf(newPassField.getPassword()).equals("")
                    && !String.valueOf(reEnterField.getPassword()).equals("")){
                changePassword(String.valueOf(old), String.valueOf(newP), String.valueOf(reEnter));
            }
            else {
                throw new Exception("Empty string");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}