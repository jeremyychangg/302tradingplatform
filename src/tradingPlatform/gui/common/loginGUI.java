package tradingPlatform.gui.common;

import tradingPlatform.gui.client.employeeScreen;
import tradingPlatform.gui.client.leadScreen;
import tradingPlatform.gui.server.adminScreen;

import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static tradingPlatform.Main.connection;
import static tradingPlatform.Main.setCurrentUser;
import static tradingPlatform.passwordEncryption.verifyPassword;
import static tradingPlatform.user.User.getAccountType;

/**
 * @implements ActionListener
 */
public class loginGUI implements ActionListener {
    private final JLabel logo = new JLabel();
    private final JFrame frame;
    private final JPanel panel;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton button;

    private final ImageIcon mainIcon = new ImageIcon("src/img/loginLogo-01.png");

    public loginGUI() {
        // Initialise the frame and panel used
        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        // Create the blue side in the login page
        JPanel side = new JPanel();
        side.setBorder(BorderFactory.createEmptyBorder(0, 300, 0, 0));
        side.setBackground(new Color(0, 140, 237));
        frame.add(side, BorderLayout.WEST);

        // Initialise and style the login button
        button = new JButton("LOGIN");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        button.setMargin(new Insets(5, 20, 5, 20));
        button.setFont(Screen.btnFont);
        button.setBackground(new Color(0, 140, 237));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);

        // Logo
        // Resizing the size of Main Logo
        Image mainImg = mainIcon.getImage();
        Image mainScale = mainImg.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon newMainIcon = new ImageIcon(mainScale);
        logo.setIcon(newMainIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(150, 0, 50, 0));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new HintTextField("Username");
        passwordField = new JPasswordField(25);

        // Aligning the text within the field
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 40));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 40));
        usernameField.setHorizontalAlignment(JTextField.LEFT);
        passwordField.setHorizontalAlignment(JTextField.LEFT);

        JPanel invisible = new JPanel();
        invisible.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        JPanel invisible2 = new JPanel();
        invisible2.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));

        panel.setBorder(BorderFactory.createEmptyBorder(0, 200, 200, 200));

        // Add elements to the panel
        panel.add(logo, BorderLayout.CENTER);
        panel.add(usernameField, BorderLayout.CENTER);
        panel.add(invisible, BorderLayout.CENTER);
        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(invisible2, BorderLayout.CENTER);
        panel.add(button, BorderLayout.CENTER);

        // Add panel to frame and display the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Venda - Login");
        frame.pack();
        frame.setVisible(true);

        // Add Window listener
        frame.addWindowListener(new ClosingListener());
        frame.requestFocusInWindow();
    }


    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String usernameInput = usernameField.getText();
        char[] passwordInput = passwordField.getPassword();
        try {
            if (passwordCorrect(usernameInput, passwordInput)) {
                frame.dispose();
                setCurrentUser(usernameInput);

                switch (Objects.requireNonNull(getAccountType())) {
                    case Employee:
                        new employeeScreen();
                        break;
                    case Lead:
                        new leadScreen();
                        break;
                    case Admin:
                        new adminScreen();
                        break;
                    default:
                        throw new LoginException("Not a valid user");
                }
                usernameField.setText("");
            } else {
                String msg = "Login password doesn't match username.";
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (LoginException | SQLException exception) {
            String msg = "User login details inputted invalid. Try again";
            try {
                throw new LoginException(msg);
            } catch (LoginException failedLoginException) {
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception error) {
            String msg = "Login does not exist in system.";
            JOptionPane.showMessageDialog(null, msg);
        }
    }


    /**
     * This code was adapted from https://stackoverflow.com/questions/1738966/java-jtextfield-with-input-hint
     */
    public static class HintTextField extends JTextField {
        public HintTextField(final String hint) {
            setText(hint);
            setForeground(Color.GRAY);

            this.addFocusListener(new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(hint)) {
                        setText("");
                    } else {
                        setText(getText());
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().equals(hint) || getText().length() == 0) {
                        setText(hint);
                        setForeground(Color.GRAY);
                    } else {
                        setText(getText());
                        setForeground(Color.BLACK);
                    }
                }
            });
        }
    }


    /**
     * This function is used to verify the user login and password by finding their corresponding userID
     * within the database and matching the values - the encrypted hash of the login password and the hash
     * and salt kept within the database - to determine whether the hashes are equivalent to each other. Within,
     * it retrieves the comparison data by querying the database, and returning a present username and password
     * that matches the userID. If there is not value within, or it does not match, the method returns false
     * as the password login is incorrect.
     *
     * @param usernameInput
     * @param passwordInput
     * @return
     * @throws SQLException
     */
    public static boolean passwordCorrect(String usernameInput, char[] passwordInput) throws SQLException {
        Statement loginCheck = connection.createStatement();

        String loginInput = "SELECT userID, password from users WHERE userID = '" + usernameInput + "';";
        ResultSet loginResults = loginCheck.executeQuery(loginInput);

        String userReturn = null;
        String passwordReturn = null;
        while (loginResults.next()) {
            userReturn = loginResults.getString("userID");
            passwordReturn = loginResults.getString("password");
        }
        String salt = passwordReturn.substring(88);
        String passDatabase = passwordReturn.substring(0, 88);
        return verifyPassword(String.valueOf(passwordInput), passDatabase, salt);
    }



    /**
     * Window closing listener. When the window is closed, the program exits.
     */
    protected static class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }


//
//    /**
//     * @param passwordInput
//     * @return
//     */
//    public static String getString(char[] passwordInput) {
//        StringBuilder passwordString = new StringBuilder();
//        for (Character ch : passwordInput) {
//            passwordString.append(ch);
//        }
//        return passwordString.toString();
//    }
}