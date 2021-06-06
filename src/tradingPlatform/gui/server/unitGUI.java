package tradingPlatform.gui.server;

import tradingPlatform.exceptions.UnitException;
import tradingPlatform.exceptions.UserException;
import tradingPlatform.gui.common.Screen;
import tradingPlatform.user.Admin;
import tradingPlatform.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class unitGUI extends JPanel implements ActionListener {
    private JPanel panel;
    private JButton createButton = new JButton("CREATE NEW UNIT");
    private JButton editButton = new JButton("EDIT UNIT");
    private JButton changeButton = new JButton("EDIT UNIT INVENTORY");

    private JButton submitNewUnit = new JButton("SUBMIT NEW UNIT");
    private JButton changeBalanceBtn = new JButton("CHANGE BALANCE");
    private JButton editBalance = new JButton("EDIT INVENTORY");

    private JPanel functions;

    public JTextField unitID;
    public JTextField creditBalance;
    public JTextField limit;
    private JTextField unitName;

    CardLayout cardLayout = new CardLayout();

    /**
     * @throws Exception
     */
    public unitGUI() throws Exception {
        setUpPanel();
        Screen.welcomeMessage(panel);
        buttonInit();
        cards();
        panel.add(functions);
        add(panel);
    }


    private void buttonInit() {
        panel.add(createButton);
        panel.add(editButton);
        panel.add(changeButton);
        createButton.addActionListener(this);
        editButton.addActionListener(this);
        changeButton.addActionListener(this);
        submitNewUnit.addActionListener(this);
        editBalance.addActionListener(this);
        changeBalanceBtn.addActionListener(this);
    }

    /**
     *
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


    private void cards() {
        newUnitCard();
//        editBalanceCard();
//        editInventory();
    }


    /**
     * The new user card, when triggered will output a form for the administration to create a new unit
     * and add it to the database. Given that all the necessary inputs are valid and correct, the text inputs
     * from the form will construct a new user that will be inputted into the database.
     */
    public void newUnitCard() {
        SpringLayout createLayout = new SpringLayout();
        JLabel createLabel = new JLabel("Create New Unit");
        JLabel unitNameL = new JLabel("Unit Name");
        JLabel creditL = new JLabel("Credit Balance");
        JLabel limitL = new JLabel("Limit");

        unitName = new JTextField(25);
        creditBalance = new JTextField(25);
        limit = new JTextField(25);

        buttonStyle(submitNewUnit);

        JPanel newUnitForm = new JPanel();
        newUnitForm.setBackground(Color.white);
        newUnitForm.setPreferredSize(new Dimension(400, 500));
        newUnitForm.add(createLabel);
        newUnitForm.add(unitNameL);
        newUnitForm.add(unitName);
        newUnitForm.add(creditL);
        newUnitForm.add(creditBalance);
        newUnitForm.add(limitL);
        newUnitForm.add(limit);
        newUnitForm.add(submitNewUnit);
        newUnitForm.setLayout(createLayout);

        // Layout the form create new unit
        createLayout.putConstraint(SpringLayout.NORTH, createLabel, 50, SpringLayout.NORTH, newUnitForm);
        createLayout.putConstraint(SpringLayout.WEST, createLabel, 50, SpringLayout.WEST, newUnitForm);

        createLayout.putConstraint(SpringLayout.NORTH, unitNameL, 100, SpringLayout.NORTH, newUnitForm);
        createLayout.putConstraint(SpringLayout.WEST, unitNameL, 50, SpringLayout.WEST, newUnitForm);
        createLayout.putConstraint(SpringLayout.NORTH, unitName, 100, SpringLayout.NORTH, newUnitForm);
        createLayout.putConstraint(SpringLayout.WEST, unitName, 50, SpringLayout.EAST, unitNameL);

        createLayout.putConstraint(SpringLayout.NORTH, creditL, 50, SpringLayout.NORTH, unitNameL);
        createLayout.putConstraint(SpringLayout.WEST, creditL, 50, SpringLayout.WEST, newUnitForm);
        createLayout.putConstraint(SpringLayout.NORTH, creditBalance, 50, SpringLayout.NORTH, unitName);
        createLayout.putConstraint(SpringLayout.WEST, creditBalance, 30, SpringLayout.EAST, creditL);

        createLayout.putConstraint(SpringLayout.NORTH, limitL, 50, SpringLayout.NORTH, creditL);
        createLayout.putConstraint(SpringLayout.WEST, limitL, 70, SpringLayout.WEST, newUnitForm);
        createLayout.putConstraint(SpringLayout.NORTH, limit, 50, SpringLayout.NORTH, creditBalance);
        createLayout.putConstraint(SpringLayout.WEST, limit, 70, SpringLayout.EAST, limitL);

        createLayout.putConstraint(SpringLayout.NORTH, submitNewUnit, 60, SpringLayout.NORTH, limitL);
        createLayout.putConstraint(SpringLayout.WEST, submitNewUnit, 80, SpringLayout.EAST, limitL);

        functions.add(newUnitForm, "Create");
    }

    public void editBalanceCard() {
        SpringLayout editLayout = new SpringLayout();
        JLabel editLabel = new JLabel("Edit Account Type");
        JPanel editUserForm = new JPanel();

        editUserForm.setLayout(editLayout);
        editUserForm.setBackground(Color.white);
        JLabel unitIDLabel = new JLabel("Unit ID");
        JLabel accountTypeEditLabel = new JLabel("Credit Balance");

        unitID = new JTextField(25);
        editBalance = new JButton("SUBMIT");

        buttonStyle(editBalance);

        editUserForm.add(editLabel);
        editUserForm.add(unitIDLabel);
        editUserForm.add(unitID);
        editUserForm.add(editBalance);


        editLayout.putConstraint(SpringLayout.NORTH, editLabel, 50, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, editLabel, 50, SpringLayout.WEST, editUserForm);

        editLayout.putConstraint(SpringLayout.NORTH, unitIDLabel, 100, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, unitIDLabel, 50, SpringLayout.WEST, editUserForm);
        editLayout.putConstraint(SpringLayout.NORTH, unitID, 90, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, unitID, 80, SpringLayout.EAST, unitIDLabel);

        editLayout.putConstraint(SpringLayout.NORTH, editBalance, 60, SpringLayout.NORTH, unitIDLabel);
        editLayout.putConstraint(SpringLayout.WEST, editBalance, 80, SpringLayout.EAST, unitIDLabel);

        functions.add(editUserForm, "Edit");
    }

//
//    public void editInventory() {
//        SpringLayout changeLayout = new SpringLayout();
//
//        JLabel changeLabel = new JLabel("Change User Password");
//        JPanel changePass = new JPanel();
//        changePass.setLayout(changeLayout);
//        changePass.setBackground(Color.white);
//
//
//        JLabel unitIDLabel = new JLabel("User ID");
//        unitID = new JTextField(25);
//        JLabel passwordLabel = new JLabel("New Password");
//        passwordChange = new JTextField(25);
//
//        changePass.add(changeLabel);
//        changePass.add(unitID);
//        changePass.add(passwordChange);
//
//        changeBalanceBtn = new JButton("SUBMIT");
//        changeBalanceBtn.addActionListener(this);
//
//        buttonStyle(changeBalanceBtn);
//
//        changePass.add(changeLabel);
//        changePass.add(unitIDLabel);
//        changePass.add(unitID);
//        changePass.add(passwordLabel);
//        changePass.add(passwordChange);
//        changePass.add(changeBalanceBtn);
//
//        changeLayout.putConstraint(SpringLayout.NORTH, changeLabel, 50, SpringLayout.NORTH, changePass);
//        changeLayout.putConstraint(SpringLayout.WEST, changeLabel, 50, SpringLayout.WEST, changePass);
//
//        changeLayout.putConstraint(SpringLayout.NORTH, unitIDLabel, 100, SpringLayout.NORTH, changePass);
//        changeLayout.putConstraint(SpringLayout.WEST, unitIDLabel, 50, SpringLayout.WEST, changePass);
//        changeLayout.putConstraint(SpringLayout.NORTH, unitID, 90, SpringLayout.NORTH, changePass);
//        changeLayout.putConstraint(SpringLayout.WEST, unitID, 90, SpringLayout.EAST, unitIDLabel);
//
//        changeLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 50, SpringLayout.NORTH, unitIDLabel);
//        changeLayout.putConstraint(SpringLayout.WEST, passwordLabel, 50, SpringLayout.WEST, changePass);
//        changeLayout.putConstraint(SpringLayout.NORTH, passwordChange, 50, SpringLayout.NORTH, unitID);
//        changeLayout.putConstraint(SpringLayout.WEST, passwordChange, 50, SpringLayout.EAST, passwordLabel);
//
//        changeLayout.putConstraint(SpringLayout.NORTH, changeBalanceBtn, 60, SpringLayout.NORTH, passwordLabel);
//        changeLayout.putConstraint(SpringLayout.WEST, changeBalanceBtn, 80, SpringLayout.EAST, passwordLabel);
//
//        functions.add(changePass, "Change");
//
//    }



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
        if (e.getSource() == submitNewUnit) {
            try {
                addnewUnit();
            } catch (Exception exception) {
            } catch (UnitException unitException) {
            }
        }
        // If user presses edit account button - send form
        if (e.getSource() == editBalance) {
            try {
                editBalance();
            } catch (UserException userException) {
            }
        }
        if (e.getSource() == changeBalanceBtn) {

        }
    }


    /**
     * The method addnewUnit is used to create a new user in the database from the server side i.e. the
     * administration user. Given the retrieved inputs from the form GUI, a new user is constructed and
     * a new user ID is given to this new user. If the inputs all fulfil the requirements, and no errors
     * are thrown, the user is created in the server side. Throws a unit exception if it is found that the
     * unit does not exist. Otherwise, throws user exceptions when the user is found to not fulfil or meet
     * certian requirements i.e. password field is empty etc..
     *
     * @throws Exception
     * @throws UnitException
     */
    private void addnewUnit() throws Exception, UnitException {
        String unitNameInput = unitName.getText();
        String creditBalanceInput = creditBalance.getText();
        String unitInput = unitID.getText();
        User newUnitInfo;

        // Reset Values
        unitName.setText("");
        creditBalance.setText("");
        unitID.setText("");
    }


    /**
     * For the administration to change the password of a user, they are to input a valid unitID and
     * a valid password to replace the old. If valid and correct, the password will be encrypted using a
     * salt and the combination of both the hash password and the salt key will be parsed into the database.
     *
     * @throws UserException
     */
    private void editBalance() throws UserException {
        String unitIDInput = unitID.getText();
        String creditInput = creditBalance.getText();
        try {
            Admin.changeUserPassword(unitIDInput, creditInput);
            JOptionPane.showMessageDialog(null, "Change Password Success: " + unitIDInput + " password successfully changed");
            // Reset Values
            unitID.setText("");
            creditBalance.setText("");
        } catch (UserException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new UserException(e.getMessage());
        } catch (SQLException throwables) {
            throw new UserException("Change Password Error: Cannot Change" + unitIDInput + " password");
        } catch (NullPointerException e) {
            String msg = "Change Password Error: User ID " + unitIDInput + " is an invalid unitID.";
            JOptionPane.showMessageDialog(null, msg);
            throw new UserException("Change Password Error: User ID " + unitIDInput + " is an invalid unitID.");
        }
    }


//    /**
//     * For the administration to change the password of a user, they are to input a valid unitID and
//     * a valid password to replace the old. If valid and correct, the password will be encrypted using a
//     * salt and the combination of both the hash password and the salt key will be parsed into the database.
//     *
//     * @throws UserException
//     */
//    private void editBalance() throws UserException {
//        String unitIDInput = unitID.getText();
//        String passwordInput = passwordChange.getText();
//        try {
//            Admin.changeUserPassword(unitIDInput, passwordInput);
//            JOptionPane.showMessageDialog(null, "Change Password Success: " + unitIDInput + " password successfully changed");
//            // Reset Values
//            unitID.setText("");
//            passwordChange.setText("");
//        } catch (UserException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            throw new UserException(e.getMessage());
//        } catch (SQLException throwables) {
//            throw new UserException("Change Password Error: Cannot Change" + unitIDInput + " password");
//        } catch (NullPointerException e) {
//            String msg = "Change Password Error: User ID " + unitIDInput + " is an invalid unitID.";
//            JOptionPane.showMessageDialog(null, msg);
//            throw new UserException("Change Password Error: User ID " + unitIDInput + " is an invalid unitID.");
//        }
//    }

    public void buttonStyle(JButton button) {
        button.setMargin(new Insets(5, 20, 5, 20));
        button.setBackground(new Color(0, 140, 237));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }
}