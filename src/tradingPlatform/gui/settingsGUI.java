package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static tradingPlatform.user.User.changePassword;

public class settingsGUI extends JPanel implements ActionListener {
    private JPanel panel;
    private JPasswordField oldPassField;
    private JPasswordField newPassField;
    private JPasswordField reEnterField;
    private JButton updateButton;

    private final JLabel oldPass = new JLabel("Old Password");
    private final JLabel newPass = new JLabel("New Password");
    private final JLabel reEnter = new JLabel("Re-enter Password");
    public GridBagConstraints gbc = new GridBagConstraints();

    Font submitFont = new Font("Avenir", Font.PLAIN, 15);
    Font headingFont = new Font("Avenir", Font.PLAIN, 50);

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
        // setting up black JPanel
        this.panel = new JPanel();
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 80, (int) (Screen.screenHeight / 1.5), Screen.screenWidth / 2));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BorderLayout());
    }

    private void settingsSection() {
        JPanel overall = new JPanel();
        overall.setLayout(new BoxLayout(overall, BoxLayout.Y_AXIS));
        overall.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        overall.setBackground(Color.white);
        JPanel settings = new JPanel();
        settings.setBackground(Color.white);


        SpringLayout layout = new SpringLayout();
        settings.setLayout(layout);


        JLabel settingsLabel = new JLabel("Change Password");
        settingsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        settingsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        overall.add(settingsLabel);

        oldPassField = new JPasswordField(25);
        newPassField = new JPasswordField(25);
        reEnterField = new JPasswordField(25);
        updateButton = new JButton("UPDATE");
        updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        layout.putConstraint(SpringLayout.WEST, reEnterField, 8, SpringLayout.EAST, reEnter);
        layout.putConstraint(SpringLayout.NORTH, reEnterField, 30, SpringLayout.NORTH, newPassField);
        overall.add(settings, BorderLayout.CENTER);


        overall.add(updateButton, BorderLayout.CENTER);

        panel.add(overall);
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