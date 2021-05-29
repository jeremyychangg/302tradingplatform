package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static tradingPlatform.Main.connection;
import static tradingPlatform.Main.setCurrentUser;
import static tradingPlatform.user.User.getAccountType;

public class loginGUI implements ActionListener {
    Font btnFont = new Font("Avenir", Font.PLAIN, 15);

    int count = 0;
    private JLabel logo = new JLabel();
    //    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton button;

    private ImageIcon mainIcon = new ImageIcon("src/img/loginLogo-01.png");

    public loginGUI() {
        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel side = new JPanel();
        side.setBorder(BorderFactory.createEmptyBorder(0, 300, 0, 0));
        side.setBackground(new Color(0, 140, 237));

        frame.add(side, BorderLayout.WEST);

        button = new JButton("LOGIN");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        button.setMargin(new Insets(5, 20, 5, 20));
//        button.setPadding(new Insets(5,20, 5, 20));
        button.setFont(btnFont);
        button.setBackground(new Color(0, 140, 237));
        button.setFocusable(false);

        // Logo
        // Resizing the size of Main Logo
        Image mainImg = mainIcon.getImage();
        Image mainScale = mainImg.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon newMainIcon = new ImageIcon(mainScale);
        logo.setIcon(newMainIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(150, 0, 50, 0));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

//        JPanel usernamePanel = new JPanel();
//        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.PAGE_AXIS));
//        usernameField = new JTextField( "Username", 15);
        usernameField = new HintTextField( "Username");
        passwordField = new JPasswordField(25);


        JLabel userTxt = new JLabel("Username");
        JLabel passwordTxt = new JLabel("Password");

        //Aligning the text within the field
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 40));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 40));
        usernameField.setHorizontalAlignment(JTextField.LEFT);
        passwordField.setHorizontalAlignment(JTextField.LEFT);


        JPanel invisible = new JPanel();
        invisible.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        JPanel invisible2 = new JPanel();
        invisible2.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));

//        passwordField.setActionCommand(Login);

//        usernamePanel.add(userTxt, BorderLayout.CENTER);
//        usernamePanel.add(usernameField, BorderLayout.CENTER);


        panel.setBorder(BorderFactory.createEmptyBorder(0, 200, 200, 200));

        panel.add(logo, BorderLayout.CENTER);
//        panel.add(usernamePanel, BorderLayout.CENTER);
        panel.add(usernameField, BorderLayout.CENTER);
        panel.add(invisible, BorderLayout.CENTER);
        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(invisible2, BorderLayout.CENTER);
        panel.add(button, BorderLayout.CENTER);

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Login");
        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new ClosingListener());
        frame.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usernameInput = usernameField.getText();
        System.out.println("The username inputted " + usernameInput);
        char[] passwordInput = passwordField.getPassword();
        try {
            if (passwordCorrect(usernameInput, passwordInput)) {
                frame.dispose();
                setCurrentUser(usernameInput);

                switch (getAccountType()) {
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
                        throw new Exception("Not a valid user");
                }
                usernameField.setText("");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * This code was adapted from https://stackoverflow.com/questions/1738966/java-jtextfield-with-input-hint
     */
    public class HintTextField extends JTextField {
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

    //GUI
    public boolean passwordCorrect(String usernameInput, char[] passwordInput) throws SQLException {
        Statement loginCheck = connection.createStatement();
        System.out.println(passwordInput);

        String loginInput = "SELECT userID, password from users WHERE userID = '" + usernameInput + "' AND password = '" + String.valueOf(passwordInput) + "';";
        System.out.println(loginInput);
        ResultSet loginResults = loginCheck.executeQuery(loginInput);

        String userReturn = null;
        String passwordReturn = null;
        while (loginResults.next()) {
            userReturn = loginResults.getString("userID");
            passwordReturn = loginResults.getString("password");
        }

        if (userReturn.equals(usernameInput) && passwordReturn.equals(getString(passwordInput))) {
            return true;
        } else {
            return false;
        }
    }

    public String getString(char[] passwordInput) {
        StringBuilder passwordString = new StringBuilder();
        for (Character ch : passwordInput) {
            passwordString.append(ch);
        }
        return passwordString.toString();
    }

    protected class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}

//
//    //GUI
//    public boolean passwordCorrect(){
//
//    }


//    /**
//     * In the circumstance where the user actually focuses on the input field. Delete the default text.
//     * @param e
//     */
//    @Override
//    public void focusGained(FocusEvent e){
//        passwordField.setText("");
//    }
//
//    @Override
//    public void focusLost(FocusEvent e){
//        passwordField.setText("Password");
//    }


