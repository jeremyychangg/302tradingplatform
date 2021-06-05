package tradingPlatform.gui;

import tradingPlatform.user.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class userGUI extends JPanel implements ActionListener {
    private JPanel panel;
    private JButton createButton = new JButton("CREATE NEW USER");
    private JButton editButton = new JButton("EDIT USER");
    private JButton changeButton = new JButton("EDIT USER PASSWORD");

    private JButton submitNewUser = new JButton("SUBMIT");
    private JButton editAccType;
    private JPanel functions;

    private JTextField firstName;
    private JTextField lastName;
    private JTextField unitID;
    private String accountTypeValue;
    private JComboBox accountType;
    private JComboBox accountTypeEdit;
    private JTextField password;
    private JTextField userID;
    private JTextField passwordChange;

    private JButton editAccTypeBtn;


    String[] userTypes = { "Admin", "Employee", "Lead" };

    CardLayout cardLayout = new CardLayout();

    /**
     * @throws Exception
     */
    public userGUI() throws Exception {
        setUpPanel();
        Screen.welcomeMessage(panel);
        buttonInit();
        cards();
        panel.add(functions);
        add(panel);
    }


    private void buttonInit(){
        panel.add(createButton);
        panel.add(editButton);
        panel.add(changeButton);
        createButton.addActionListener(this);
        editButton.addActionListener(this);
        changeButton.addActionListener(this);
        submitNewUser.addActionListener(this);
    }


    private void cards(){
        newUserCard();
        editTypeCard();
        changePasswordCard();


    }

    public void newUserCard(){
        SpringLayout createLayout = new SpringLayout();
        JLabel createLabel = new JLabel("Create New User");
        JLabel firstNameL = new JLabel("First Name");
        JLabel lastNameL = new JLabel("Last Name");
        JLabel unitIDL = new JLabel("Unit ID");
        JLabel accountTypeL = new JLabel("Account Type");
        JLabel passwordL = new JLabel("Password");

        firstName = new JTextField(25);
        lastName = new JTextField(25);
        unitID = new JTextField(25);
//        accountType = new JTextField(25);

        accountType = new JComboBox(userTypes);
        accountType.setSelectedIndex(1);
        accountType.addActionListener(this);

        password = new JTextField(25);

        buttonStyle(submitNewUser);

        JPanel newUserForm = new JPanel();
        newUserForm.setBackground(Color.white);
        newUserForm.setPreferredSize(new Dimension(400, 500));
        newUserForm.add(createLabel);
        newUserForm.add(firstNameL);
        newUserForm.add(firstName);
        newUserForm.add(lastName);
        newUserForm.add(lastNameL);
        newUserForm.add(unitID);
        newUserForm.add(unitIDL);
        newUserForm.add(accountType);
        newUserForm.add(accountTypeL);
        newUserForm.add(password);
        newUserForm.add(passwordL);
        newUserForm.add(submitNewUser);
        newUserForm.setLayout(createLayout);

        createLayout.putConstraint(SpringLayout.NORTH, createLabel, 50, SpringLayout.NORTH, newUserForm);
        createLayout.putConstraint(SpringLayout.WEST, createLabel, 50, SpringLayout.WEST, newUserForm);

        createLayout.putConstraint(SpringLayout.NORTH, firstNameL, 100, SpringLayout.NORTH, newUserForm);
        createLayout.putConstraint(SpringLayout.WEST, firstNameL, 50, SpringLayout.WEST, newUserForm);
        createLayout.putConstraint(SpringLayout.NORTH, firstName, 100, SpringLayout.NORTH, newUserForm);
        createLayout.putConstraint(SpringLayout.WEST, firstName, 50, SpringLayout.EAST, firstNameL);
//
        createLayout.putConstraint(SpringLayout.NORTH, lastNameL, 50, SpringLayout.NORTH, firstNameL);
        createLayout.putConstraint(SpringLayout.WEST, lastNameL, 50, SpringLayout.WEST, newUserForm);
        createLayout.putConstraint(SpringLayout.NORTH, lastName, 50, SpringLayout.NORTH, firstName);
        createLayout.putConstraint(SpringLayout.WEST, lastName, 50, SpringLayout.EAST, lastNameL);

        createLayout.putConstraint(SpringLayout.NORTH, unitIDL, 50, SpringLayout.NORTH, lastNameL);
        createLayout.putConstraint(SpringLayout.WEST, unitIDL, 50, SpringLayout.WEST, newUserForm);
        createLayout.putConstraint(SpringLayout.NORTH, unitID, 50, SpringLayout.NORTH, lastName);
        createLayout.putConstraint(SpringLayout.WEST, unitID, 70, SpringLayout.EAST, unitIDL);

        createLayout.putConstraint(SpringLayout.NORTH, accountTypeL, 50, SpringLayout.NORTH, unitIDL);
        createLayout.putConstraint(SpringLayout.WEST, accountTypeL, 50, SpringLayout.WEST, newUserForm);
        createLayout.putConstraint(SpringLayout.NORTH, accountType, 50, SpringLayout.NORTH, unitID);
        createLayout.putConstraint(SpringLayout.WEST, accountType, 30, SpringLayout.EAST, accountTypeL);

        createLayout.putConstraint(SpringLayout.NORTH, passwordL, 50, SpringLayout.NORTH, accountTypeL);
        createLayout.putConstraint(SpringLayout.WEST, passwordL, 50, SpringLayout.WEST, newUserForm);
        createLayout.putConstraint(SpringLayout.NORTH, password, 50, SpringLayout.NORTH, accountType);
        createLayout.putConstraint(SpringLayout.WEST, password, 30, SpringLayout.EAST, accountTypeL);

        createLayout.putConstraint(SpringLayout.NORTH, submitNewUser, 60, SpringLayout.NORTH, passwordL);
        createLayout.putConstraint(SpringLayout.WEST, submitNewUser, 80, SpringLayout.EAST, passwordL);

        functions.add(newUserForm, "Create");

        accountType.addActionListener(this);
    }

    public void editTypeCard(){
        SpringLayout editLayout = new SpringLayout();
        JLabel editLabel = new JLabel("Edit Account Type");
        JPanel editUserForm = new JPanel();

        editUserForm.setLayout(editLayout);
        editUserForm.setBackground(Color.white);
        JLabel userIDLabel = new JLabel("User ID");
        JLabel accountTypeEditLabel = new JLabel("Account Type");

        userID = new JTextField(25);
        accountTypeEdit = new JComboBox(userTypes);
        accountTypeEdit.setSelectedIndex(1);
        accountTypeEdit.addActionListener(this);
        editAccTypeBtn = new JButton("SUBMIT");

        buttonStyle(editAccTypeBtn);

        editUserForm.add(editLabel);
        editUserForm.add(userIDLabel);
        editUserForm.add(userID);
        editUserForm.add(accountTypeEditLabel);
        editUserForm.add(accountTypeEdit);
        editUserForm.add(editAccTypeBtn);

        editAccTypeBtn.addActionListener(this);

        editLayout.putConstraint(SpringLayout.NORTH, editLabel, 50, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, editLabel, 50, SpringLayout.WEST, editUserForm);

        editLayout.putConstraint(SpringLayout.NORTH, userIDLabel, 100, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, userIDLabel, 50, SpringLayout.WEST, editUserForm);
        editLayout.putConstraint(SpringLayout.NORTH, userID, 90, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, userID, 80, SpringLayout.EAST, userIDLabel);

        editLayout.putConstraint(SpringLayout.NORTH, accountTypeEditLabel, 50, SpringLayout.NORTH, userIDLabel);
        editLayout.putConstraint(SpringLayout.WEST, accountTypeEditLabel, 50, SpringLayout.WEST, editUserForm);
        editLayout.putConstraint(SpringLayout.NORTH, accountTypeEdit, 50, SpringLayout.NORTH, userID);
        editLayout.putConstraint(SpringLayout.WEST, accountTypeEdit, 50, SpringLayout.EAST, accountTypeEditLabel);

        editLayout.putConstraint(SpringLayout.NORTH, editAccType, 60, SpringLayout.NORTH, accountTypeEditLabel);
        editLayout.putConstraint(SpringLayout.WEST, editAccType, 80, SpringLayout.EAST, accountTypeEditLabel);

        functions.add(editUserForm, "Edit");

        accountTypeEdit.addActionListener(this);

    }


    public void changePasswordCard(){
        SpringLayout changeLayout = new SpringLayout();

        JLabel changeLabel = new JLabel("Change User Password");
        JPanel changePass = new JPanel();
        changePass.setLayout(changeLayout);
        changePass.setBackground(Color.white);


        JLabel userIDLabel = new JLabel("User ID");
        userID = new JTextField(25);
        JLabel passwordLabel = new JLabel("New Password");
        passwordChange = new JTextField(25);

        changePass.add(changeLabel);
        changePass.add(userID);
        changePass.add(passwordChange);

        editAccType = new JButton("SUBMIT");

        buttonStyle(editAccType);

        changePass.add(changeLabel);
        changePass.add(userIDLabel);
        changePass.add(userID);
        changePass.add(passwordLabel);
        changePass.add(passwordChange);
        changePass.add(editAccType);

        changeLayout.putConstraint(SpringLayout.NORTH, changeLabel, 50, SpringLayout.NORTH, changePass);
        changeLayout.putConstraint(SpringLayout.WEST, changeLabel, 50, SpringLayout.WEST, changePass);

        changeLayout.putConstraint(SpringLayout.NORTH, userIDLabel, 100, SpringLayout.NORTH, changePass);
        changeLayout.putConstraint(SpringLayout.WEST, userIDLabel, 50, SpringLayout.WEST, changePass);
        changeLayout.putConstraint(SpringLayout.NORTH, userID, 90, SpringLayout.NORTH, changePass);
        changeLayout.putConstraint(SpringLayout.WEST, userID, 90, SpringLayout.EAST, userIDLabel);

        changeLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 50, SpringLayout.NORTH, userIDLabel);
        changeLayout.putConstraint(SpringLayout.WEST, passwordLabel, 50, SpringLayout.WEST, changePass);
        changeLayout.putConstraint(SpringLayout.NORTH, passwordChange, 50, SpringLayout.NORTH, userID);
        changeLayout.putConstraint(SpringLayout.WEST, passwordChange, 50, SpringLayout.EAST, passwordLabel);

        changeLayout.putConstraint(SpringLayout.NORTH, editAccType, 60, SpringLayout.NORTH, passwordLabel);
        changeLayout.putConstraint(SpringLayout.WEST, editAccType, 80, SpringLayout.EAST, passwordLabel);


        functions.add(changePass, "Change");

        accountTypeEdit.addActionListener(this);

    }


    /**
     * @throws SQLException
     */
    private void setUpPanel() throws SQLException {
        // setting up blank JPanel
        this.panel = new JPanel();
        functions = new JPanel();
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));
        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, 400, Screen.border));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        functions.setPreferredSize(new Dimension(600, 500));
//        panel.setBackground(Color.red);
//        functions.setBorder(BorderFactory.createEmptyBorder(100,200,100,100));
        functions.setLayout(cardLayout);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton){
            cardLayout.show(functions, "Create");
        }
        else if (e.getSource() == editButton){
            cardLayout.show(functions, "Edit");
        }
        else if (e.getSource() == changeButton){
            cardLayout.show(functions, "Change");
        }
        if (e.getSource() == submitNewUser){
            addNewUser();
        }
        if (e.getSource() == accountType){
            accountTypeValue = (String) accountType.getSelectedItem();
        }
        if (e.getSource() == accountType){
            accountTypeValue = (String) accountType.getSelectedItem();
        }
        if (e.getSource() == editAccType){

        }
        if (e.getSource() == accountTypeEdit){
            accountTypeValue = (String) accountTypeEdit.getSelectedItem();
        }
    }

    private void addNewUser(){
        String firstNameInput = firstName.getText();
        String lastNameInput = lastName.getText();
        String unitInput = unitID.getText();
        String accountInput = accountTypeValue;
        String passwordInput = password.getText();
        System.out.println(accountInput);
    }

    private void changePassword(){
        String userIDInput = userID.getText();
        String passwordInput = passwordChange.getText();
    }

    private void editAccountType() throws Exception {
        String userIDInput = userID.getText();
        String accountTypeInput = accountTypeValue;
        Admin.editAccountType(userIDInput, accountTypeInput);
    }

    public void buttonStyle(JButton button){
        button.setMargin(new Insets(5, 20, 5, 20));
        button.setBackground(new Color(0, 140, 237));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }
}