package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static tradingPlatform.gui.Screen.body;
import static tradingPlatform.gui.Screen.screenWidth;
import static tradingPlatform.user.User.changePassword;

public class settingsGUI extends JPanel implements ActionListener {
    private JPanel panel = new JPanel();
    ;
    private JPasswordField oldPassField;
    private JPasswordField newPassField;
    private JPasswordField reEnterField;
    private JButton updateButton;

    private final JLabel oldPass = new JLabel("Old Password");
    private final JLabel newPass = new JLabel("New Password");
    private final JLabel reEnter = new JLabel("Re-enter Password");

    /**
     * The settings GUI constructor initiates and builds a panel containing the
     * elements of the interface used to update the user's settings. In particular,
     * this would construct the form used to change the user's password. Additionally,
     * it would initiate and add an action listener to the update button, so that if the
     * user does enter values into the fields and presses the button, it would call methods
     * to update the password.
     *
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
        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, (Screen.screenHeight / 4), (int) (screenWidth * 0.4)));
//        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    /**
     * The settings Section is used to initiate and construct the settings section of the page i.e.
     * builds the container for of which the user can update their password. It is consisted of
     * a main overall panel that holds a settings panel, heading and button. Within this settings panel,
     * different text fields are drawn beside text labels for users to change their password. The styling
     * for this section are applied in the method.
     */
    private void settingsSection() {
        // Initialise an overall panel for this section
        JPanel overall = new JPanel();
        overall.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));

        // Initialise the settings panel
        JPanel settings = new JPanel();
        SpringLayout layout = new SpringLayout();
        settings.setBackground(Color.white);
        settings.setLayout(layout);

        // Add heading
        JLabel settingsLabel = new JLabel("Change Password");
        settingsLabel.setFont(Screen.h1);
        panel.add(settingsLabel);

        // Initiate password fields
        oldPassField = new JPasswordField(25);
        newPassField = new JPasswordField(25);
        reEnterField = new JPasswordField(25);

        // Initiate and style button
        updateButton = new JButton("UPDATE");
        updateButton.setMargin(new Insets(5, 20, 5, 20));
        updateButton.setBackground(new Color(0, 140, 237));
        updateButton.setOpaque(true);
        updateButton.setBorderPainted(false);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);

        // Adds the form to the settings panel
        settings.add(oldPass);
        settings.add(oldPassField);
        settings.add(newPass);
        settings.add(newPassField);
        settings.add(reEnter);
        settings.add(reEnterField);

        // Set the fonts of the label
        oldPass.setFont(body);
        newPass.setFont(body);
        reEnter.setFont(body);

        // Apply constraints to the layout, and thus organise form based on this structure
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

        // Add the settings panel to the overall panel
        overall.add(settings, BorderLayout.CENTER);

        // Add button to the overall panel
        overall.add(updateButton);

        // Add all elements to the panel
        panel.add(settings, BorderLayout.WEST);
        panel.add(updateButton, BorderLayout.EAST);
    }


    /**
     * This overrides the actionPerformed method by allowing the user to press the update button and
     * reset their password given the values within the password fields. If any of the input fields
     * are empty, the method prevents from continuing with the method.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        updatePassword();
    }

    /**
     * Method used to update the users password. Kept in a private method to ensure that it cannot be called
     * by any other class etc. The user is only able to update their own password, and within, they are to
     * enter three parameters - old, new and reentered password - to successfully change their password. If
     * these conditions are not met, exceptions are thrown.
     */
    private void updatePassword(){
        // Retrieve the inputted values of the fields
        char[] old = oldPassField.getPassword();
        char[] newP = newPassField.getPassword();
        char[] reEnter = reEnterField.getPassword();
        try {
            if (!String.valueOf(oldPassField.getPassword()).equals("")
                    && !String.valueOf(newPassField.getPassword()).equals("")
                    && !String.valueOf(reEnterField.getPassword()).equals("")) {
                changePassword(String.valueOf(old), String.valueOf(newP), String.valueOf(reEnter));
            } else {
                throw new Exception("Empty string");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
