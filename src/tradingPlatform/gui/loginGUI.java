package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginGUI implements ActionListener {
    int count = 0;
    private JLabel logo;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public loginGUI() {
        frame = new JFrame();
        panel = new JPanel();

        JButton button = new JButton("Login");
        button.addActionListener(this);

//        label = new JLabel("Hello, welcome Steve");
        usernameField = new JTextField("Username");
        passwordField = new JPasswordField(25);

        //Aligning the text within the field
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setHorizontalAlignment(JTextField.CENTER);

//        passwordField.setActionCommand(Login);

        panel.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));
        panel.setLayout(new GridLayout(0, 1));
//        panel.add(label);

        panel.add(usernameField, BorderLayout.CENTER);
        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(button);

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
