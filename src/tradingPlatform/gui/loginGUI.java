package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel side = new JPanel();
        side.setBorder(BorderFactory.createEmptyBorder(0,300, 0,0));
        side.setBackground(new Color(0, 140, 237));

        frame.add(side, BorderLayout.WEST);

        button = new JButton("LOGIN");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        button.setMargin(new Insets(5,20, 5, 20));
//        button.setPadding(new Insets(5,20, 5, 20));
        button.setFont(btnFont);
        button.setBackground(new Color(0, 140, 237));

        // Logo
        // Resizing the size of Main Logo
        Image mainImg = mainIcon.getImage();
        Image mainScale = mainImg.getScaledInstance(200, 150,  Image.SCALE_SMOOTH);
        ImageIcon newMainIcon = new ImageIcon(mainScale);
        logo.setIcon(newMainIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(150, 0, 50, 0));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new JTextField("Username");
        passwordField = new JPasswordField(25);

        //Aligning the text within the field
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 40));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 40));
        usernameField.setHorizontalAlignment(JTextField.LEFT);
        passwordField.setHorizontalAlignment(JTextField.LEFT);


        JPanel invisible = new JPanel();
        invisible.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
        JPanel invisible2 = new JPanel();
        invisible2.setBorder(BorderFactory.createEmptyBorder(20,0,5,0));

//        passwordField.setActionCommand(Login);

        panel.setBorder(BorderFactory.createEmptyBorder(0, 200, 200, 200));

        panel.add(logo, BorderLayout.CENTER);
        panel.add(usernameField, BorderLayout.CENTER);
        panel.add(invisible, BorderLayout.CENTER);
        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(invisible2, BorderLayout.CENTER);
        panel.add(button, BorderLayout.CENTER);

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Login");
        frame.pack();

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        loginGUI display = new loginGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        count++;
//        label.setText("Number of clicks: " + count);
        String usernameInput = usernameField.getText();
        System.out.println("The username inputted " + usernameInput);
        char[] passwordInput = passwordField.getPassword();
        for (char x : passwordInput) {
            System.out.print(x);
        }
    }

    //
//    //GUI
//    public boolean passwordCorrect(){
//
//    }

}
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
