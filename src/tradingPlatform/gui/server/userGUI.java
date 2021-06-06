package tradingPlatform.gui.server;

import tradingPlatform.exceptions.EditUserException;
import tradingPlatform.exceptions.UnitException;
import tradingPlatform.exceptions.UserException;
import tradingPlatform.gui.common.Screen;
import tradingPlatform.user.Admin;
import tradingPlatform.user.Employee;
import tradingPlatform.user.Lead;
import tradingPlatform.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * The userGUI class extends the JPanel to create a panel that would display the necessary user server
 * related features. This would include creating new users, editing passwords and account types. This is determine
 * to be further iterated to include a more widespread range of features. The class constructs the different forms
 * based on a card layout, and the method implements ActionListeners to process these methods.
 *
 * @author Natalie Smith
 */
public class userGUI extends JPanel implements ActionListener {
    private JPanel panel;
    private JButton createButton = new JButton("CREATE NEW USER");
    private JButton editButton = new JButton("EDIT ACCOUNT TYPE");
    private JButton changeButton = new JButton("EDIT USER PASSWORD");
    private JButton submitNewUser = new JButton("SUBMIT NEW USER");

    private JButton changePassBtn = new JButton("CHANGE PASSWORD");
    private JButton editAccTypeBtn = new JButton("EDIT ACCOUNT");

    private JPanel functions;

    private JTextField firstName;
    private JTextField lastName;
    private JTextField unitID;
    private String accountTypeValue;
    private String accountTypeValueEdit;
    private JComboBox accountType;
    private JComboBox accountTypeEdit;
    private JTextField password;
    private JTextField userID;
    private JTextField userIDInput;
    private JTextField passwordChange;

    String[] userTypes = {"Admin", "Employee", "Lead"};

    CardLayout cardLayout = new CardLayout();

    /**
     * UserGUI constructor used to call the different methods used to construct the userGUI panel
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


    /**
     * Method adds relevant button listeners for the userGUI screen. When pressed, would trigger the actionListener
     * function.
     */
    private void buttonInit() {
        panel.add(createButton);
        panel.add(editButton);
        panel.add(changeButton);
        createButton.addActionListener(this);
        editButton.addActionListener(this);
        changeButton.addActionListener(this);
        submitNewUser.addActionListener(this);
        editAccTypeBtn.addActionListener(this);
        changePassBtn.addActionListener(this);
    }


    /**
     * Is used to call the card layouts for each of the forms.
     */
    private void cards() {
        newUserCard();
        editTypeCard();
        changePasswordCard();
    }



    /**
     * The new user card, when triggered will output a form for the administration to create a new user
     * and add it to the database. Given that all the necessary inputs are valid and correct, the text inputs
     * from the form will construct a new user that will be inputted into the database.
     */
    public void newUserCard() {
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

        accountTypeValue = "Employee";
        accountType = new JComboBox(userTypes);
        accountType.setSelectedIndex(0);
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

        // Layout the form create new user
        createLayout.putConstraint(SpringLayout.NORTH, createLabel, 50, SpringLayout.NORTH, newUserForm);
        createLayout.putConstraint(SpringLayout.WEST, createLabel, 50, SpringLayout.WEST, newUserForm);

        createLayout.putConstraint(SpringLayout.NORTH, firstNameL, 100, SpringLayout.NORTH, newUserForm);
        createLayout.putConstraint(SpringLayout.WEST, firstNameL, 50, SpringLayout.WEST, newUserForm);
        createLayout.putConstraint(SpringLayout.NORTH, firstName, 100, SpringLayout.NORTH, newUserForm);
        createLayout.putConstraint(SpringLayout.WEST, firstName, 50, SpringLayout.EAST, firstNameL);

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

    }



    /**
     * A method that constructs and builds the edit account type form. Once called, the card would be
     * constructed to show the inputs related - user ID and account type. Given that these inputs are
     * valid, and that the server does how these values, the method would set the text fields
     */
    public void editTypeCard() {
        SpringLayout editLayout = new SpringLayout();
        JLabel editLabel = new JLabel("Edit Account Type");
        JPanel editUserForm = new JPanel();

        editUserForm.setLayout(editLayout);
        editUserForm.setBackground(Color.white);
        JLabel userIDLabel = new JLabel("User ID");
        JLabel accountTypeEditLabel = new JLabel("Account Type");

        userIDInput = new JTextField(25);
        accountTypeEdit = new JComboBox(userTypes);
        accountTypeEdit.setSelectedIndex(1);
        accountTypeEdit.addActionListener(this);

        editAccTypeBtn = new JButton("SUBMIT");

        buttonStyle(editAccTypeBtn);

        editUserForm.add(editLabel);
        editUserForm.add(userIDLabel);
        editUserForm.add(userIDInput);
        editUserForm.add(accountTypeEditLabel);
        editUserForm.add(accountTypeEdit);
        editUserForm.add(editAccTypeBtn);
        editAccTypeBtn.addActionListener(this);

        editLayout.putConstraint(SpringLayout.NORTH, editLabel, 50, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, editLabel, 50, SpringLayout.WEST, editUserForm);

        editLayout.putConstraint(SpringLayout.NORTH, userIDLabel, 100, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, userIDLabel, 50, SpringLayout.WEST, editUserForm);
        editLayout.putConstraint(SpringLayout.NORTH, userIDInput, 90, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, userIDInput, 80, SpringLayout.EAST, userIDLabel);

        editLayout.putConstraint(SpringLayout.NORTH, accountTypeEditLabel, 50, SpringLayout.NORTH, userIDLabel);
        editLayout.putConstraint(SpringLayout.WEST, accountTypeEditLabel, 50, SpringLayout.WEST, editUserForm);
        editLayout.putConstraint(SpringLayout.NORTH, accountTypeEdit, 50, SpringLayout.NORTH, userIDInput);
        editLayout.putConstraint(SpringLayout.WEST, accountTypeEdit, 50, SpringLayout.EAST, accountTypeEditLabel);

        editLayout.putConstraint(SpringLayout.NORTH, editAccTypeBtn, 60, SpringLayout.NORTH, accountTypeEditLabel);
        editLayout.putConstraint(SpringLayout.WEST, editAccTypeBtn, 80, SpringLayout.EAST, accountTypeEditLabel);

        functions.add(editUserForm, "Edit");
    }


    /**
     * When called, this method is used to produce the change password form. It constructs the panel
     * using SpringLayout to display each of the different elements. In additin
     */
    public void changePasswordCard() {
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

        changePassBtn = new JButton("SUBMIT");
        changePassBtn.addActionListener(this);

        buttonStyle(changePassBtn);

        changePass.add(changeLabel);
        changePass.add(userIDLabel);
        changePass.add(userID);
        changePass.add(passwordLabel);
        changePass.add(passwordChange);
        changePass.add(changePassBtn);

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

        changeLayout.putConstraint(SpringLayout.NORTH, changePassBtn, 60, SpringLayout.NORTH, passwordLabel);
        changeLayout.putConstraint(SpringLayout.WEST, changePassBtn, 80, SpringLayout.EAST, passwordLabel);

        functions.add(changePass, "Change");
    }


    /**
     * Method used to initialise and setup up the existing JPanel. Sets it at the preferred screen width and height,
     * and adjusts the border.
     */
    private void setUpPanel() {
        this.panel = new JPanel();
        functions = new JPanel();
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));

        int borderBottom = Screen.screenHeight / 8;
        if (Screen.screenHeight > 1400) {
            borderBottom = Screen.screenHeight / 7;
        }

        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, borderBottom, Screen.border));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        functions.setPreferredSize(new Dimension(600, 500));
        functions.setLayout(cardLayout);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Change the card based on the card button
        if (e.getSource() == createButton) {
            cardLayout.show(functions, "Create");
        } else if (e.getSource() == editButton) {
            cardLayout.show(functions, "Edit");
        } else if (e.getSource() == changeButton) {
            cardLayout.show(functions, "Change");
        }
        // If the User pressed new user button - send form
        if (e.getSource() == submitNewUser) {
            try {
                addNewUser();
            } catch (UnitException unitException) {
                String msg = "New User Error: The inputted unit ID doesn't exist currently.";
                JOptionPane.showMessageDialog(null, msg);
            } catch (Exception exception) {
            }
        }
        // If the user changes combobox accounttype, change the value of account Type
        if (e.getSource() == accountType) {
            accountTypeValue = (String) accountType.getSelectedItem();
        }
        if (e.getSource() == accountTypeEdit) {
            accountTypeValueEdit = (String) accountTypeEdit.getSelectedItem();
        }
        // If user presses edit account button - send form
        if (e.getSource() == editAccTypeBtn) {
            try {
                editAccountType();
            } catch (SQLException exception) {
                String msg = "Change Account Type: Unable to change user account in database";
                JOptionPane.showMessageDialog(null, msg);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (e.getSource() == changePassBtn) {
            try {
                changePassword();
            } catch (Exception userException) {
                String msg = "Password Change Error: Unable to change user password in database";
                JOptionPane.showMessageDialog(null, msg);
            }
        }
    }


    /**
     * The method addNewUser is used to create a new user in the database from the server side i.e. the
     * administration user. Given the retrieved inputs from the form GUI, a new user is constructed and
     * a new user ID is given to this new user. If the inputs all fulfil the requirements, and no errors
     * are thrown, the user is created in the server side. Throws a unit exception if it is found that the
     * unit does not exist. Otherwise, throws user exceptions when the user is found to not fulfil or meet
     * certian requirements i.e. password field is empty etc..
     *
     * @throws Exception
     * @throws UnitException
     */
    private void addNewUser() throws Exception, UnitException {
        String firstNameInput = firstName.getText();
        String lastNameInput = lastName.getText();
        String unitInput = unitID.getText();
        String accountInput = accountTypeValue;
        String passwordInput = password.getText();
        User newUserInfo;

        // Create user type based on the commandbox input
        switch (accountInput) {
            case "Employee":
                newUserInfo = new Employee(firstNameInput, lastNameInput, unitInput, passwordInput);
                break;
            case "Lead":
                newUserInfo = new Lead(firstNameInput, lastNameInput, unitInput, passwordInput);
                break;
            case "Admin":
                newUserInfo = new Admin(firstNameInput, lastNameInput, unitInput, passwordInput);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + accountInput);
        }

        // Try adding this new user information to the database, if successful will return success prompt
        try {
            Admin.addUserToDatabase(newUserInfo);

            // If successful, output message dialog
            String msg = "New User " + firstNameInput + " " + lastNameInput + " with UserID "
                    + newUserInfo.returnUserID() + " successfully created";
            JOptionPane.showMessageDialog(null, msg);

            // Reset Values
            firstName.setText("");
            lastName.setText("");
            unitID.setText("");
            password.setText("");
        } catch (UserException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    /**
     * For the administration to change the password of a user, they are to input a valid userID and
     * a valid password to replace the old. If valid and correct, the password will be encrypted using a
     * salt and the combination of both the hash password and the salt key will be parsed into the database.
     *
     * @throws UserException
     */
    private void changePassword() throws EditUserException {
        String userIDInput = userID.getText();
        String passwordInput = passwordChange.getText();
        try {
            Admin.changeUserPassword(userIDInput, passwordInput);
            JOptionPane.showMessageDialog(null, "Change Password Success: " + userIDInput + " password successfully changed");
            // Reset Values
            userID.setText("");
            passwordChange.setText("");
        } catch (EditUserException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new EditUserException(e.getMessage());
        } catch (SQLException throwables) {
            throw new EditUserException("Change Password Error: Cannot Change" + userIDInput + " password");
        } catch (NullPointerException e) {
            String msg = "Change Password Error: User ID " + userIDInput + " is an invalid userID.";
            JOptionPane.showMessageDialog(null, msg);
            throw new EditUserException("Change Password Error: User ID " + userIDInput + " is an invalid userID.");
        }
    }


    /**
     * This method is used to call methods related to editing the account type of the user. Given that a user
     * has inputted valid user ID and account type, it would update the details within the server. Would throw
     * Exception and EditUserException given that these errors occur. If successful, would output a dialog box
     * with the userID and their new account type. Thus, the user should be able to access the new account on log
     * in.
     *
     * @throws Exception
     */
    private void editAccountType() throws Exception, EditUserException {
        String userChange = userIDInput.getText();
        String accountTypeInput = accountTypeValueEdit;
        try {
            Admin.editAccountType(userChange, accountTypeInput);
            String msg = "Change user " + userChange + " to " + accountTypeInput + " success!";
            JOptionPane.showMessageDialog(null, msg);

            // Reset values
            userIDInput.setText("");
        } catch (NullPointerException e) {
            String msg = "Edit User Error: Empty values. Please try again.";
            JOptionPane.showMessageDialog(null, msg);
            throw new NullPointerException(msg);
        } catch (SQLException e) {
            String msg = "Edit User SQL Error: Could not add to database.";
            JOptionPane.showMessageDialog(null, msg);
            throw new Exception(msg);
        } catch (EditUserException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    /**
     * Adjusts the JButton input given and styles to suit style guide.
     *
     * @param button JButton that submits form
     */
    public void buttonStyle(JButton button) {
        button.setMargin(new Insets(5, 20, 5, 20));
        button.setBackground(new Color(0, 140, 237));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }
}