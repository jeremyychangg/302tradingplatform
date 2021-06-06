package tradingPlatform.gui.client;

import tradingPlatform.Request;
import tradingPlatform.gui.common.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class requestGUI extends JPanel implements ActionListener {
    // Create panel
    private JPanel panel;
    private JPanel requestFields;
    // Create submit button
    private JButton submitBtn = new JButton("SUBMIT");

    private JTextField userID;
    private JComboBox reqTyp;
    private JTextField requestMessage;

    String[] requestTypes = {"New User Request", "New Account Type Request", "New Unit Request",
            "Change Unit Request", "Edit Balance Request", "Edit Limit Request", "Edit Assets Owned Request",
            "New Asset Request", "New Asset Type Request", "Permission Request"};

    CardLayout cardLayout = new CardLayout();

    // Create labels for text fields
    JLabel jl = new JLabel();

    /**
     * Constructor sets up request GUI panel
     */
    public requestGUI() throws Exception {
        setupPanel();
        Screen.welcomeMessage(panel);
        //buttonInit();
        //cards();
        //panel.add(requestFields);
        //add(panel);
    }

    private void setupPanel() {
        this.panel = new JPanel();
        requestFields = new JPanel();
        panel.setPreferredSize(new Dimension(Screen.screenWidth, Screen.screenHeight));

        panel.setBorder(BorderFactory.createEmptyBorder(80, Screen.border, 0, Screen.border));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        requestFields.setPreferredSize(new Dimension(600, 500));
        requestFields.setLayout(cardLayout);
    }


//    public void newUserCard() {
//        SpringLayout createLayout = new SpringLayout();
//        JLabel createLabel = new JLabel("Create New User");
//        JLabel firstNameL = new JLabel("First Name");
//        JLabel lastNameL = new JLabel("Last Name");
//        JLabel unitIDL = new JLabel("Unit ID");
//        JLabel accountTypeL = new JLabel("Account Type");
//        JLabel passwordL = new JLabel("Password");
//
//        firstName = new JTextField(25);
//        lastName = new JTextField(25);
//        unitID = new JTextField(25);
//
//        accountTypeValue = "Employee";
//        accountType = new JComboBox(userTypes);
//        accountType.setSelectedIndex(1);
//        accountType.addActionListener(this);
//
//        password = new JTextField(25);
//
//        buttonStyle(submitNewUser);
//
//        JPanel newUserForm = new JPanel();
//        newUserForm.setBackground(Color.white);
//        newUserForm.setPreferredSize(new Dimension(400, 500));
//        newUserForm.add(createLabel);
//        newUserForm.add(firstNameL);
//        newUserForm.add(firstName);
//        newUserForm.add(lastName);
//        newUserForm.add(lastNameL);
//        newUserForm.add(unitID);
//        newUserForm.add(unitIDL);
//        newUserForm.add(accountType);
//        newUserForm.add(accountTypeL);
//        newUserForm.add(password);
//        newUserForm.add(passwordL);
//        newUserForm.add(submitNewUser);
//        newUserForm.setLayout(createLayout);
//
//        // Layout the form create new user
//        createLayout.putConstraint(SpringLayout.NORTH, createLabel, 50, SpringLayout.NORTH, newUserForm);
//        createLayout.putConstraint(SpringLayout.WEST, createLabel, 50, SpringLayout.WEST, newUserForm);
//
//        createLayout.putConstraint(SpringLayout.NORTH, firstNameL, 100, SpringLayout.NORTH, newUserForm);
//        createLayout.putConstraint(SpringLayout.WEST, firstNameL, 50, SpringLayout.WEST, newUserForm);
//        createLayout.putConstraint(SpringLayout.NORTH, firstName, 100, SpringLayout.NORTH, newUserForm);
//        createLayout.putConstraint(SpringLayout.WEST, firstName, 50, SpringLayout.EAST, firstNameL);
//
//        createLayout.putConstraint(SpringLayout.NORTH, lastNameL, 50, SpringLayout.NORTH, firstNameL);
//        createLayout.putConstraint(SpringLayout.WEST, lastNameL, 50, SpringLayout.WEST, newUserForm);
//        createLayout.putConstraint(SpringLayout.NORTH, lastName, 50, SpringLayout.NORTH, firstName);
//        createLayout.putConstraint(SpringLayout.WEST, lastName, 50, SpringLayout.EAST, lastNameL);
//
//        createLayout.putConstraint(SpringLayout.NORTH, unitIDL, 50, SpringLayout.NORTH, lastNameL);
//        createLayout.putConstraint(SpringLayout.WEST, unitIDL, 50, SpringLayout.WEST, newUserForm);
//        createLayout.putConstraint(SpringLayout.NORTH, unitID, 50, SpringLayout.NORTH, lastName);
//        createLayout.putConstraint(SpringLayout.WEST, unitID, 70, SpringLayout.EAST, unitIDL);
//
//        createLayout.putConstraint(SpringLayout.NORTH, accountTypeL, 50, SpringLayout.NORTH, unitIDL);
//        createLayout.putConstraint(SpringLayout.WEST, accountTypeL, 50, SpringLayout.WEST, newUserForm);
//        createLayout.putConstraint(SpringLayout.NORTH, accountType, 50, SpringLayout.NORTH, unitID);
//        createLayout.putConstraint(SpringLayout.WEST, accountType, 30, SpringLayout.EAST, accountTypeL);
//
//        createLayout.putConstraint(SpringLayout.NORTH, passwordL, 50, SpringLayout.NORTH, accountTypeL);
//        createLayout.putConstraint(SpringLayout.WEST, passwordL, 50, SpringLayout.WEST, newUserForm);
//        createLayout.putConstraint(SpringLayout.NORTH, password, 50, SpringLayout.NORTH, accountType);
//        createLayout.putConstraint(SpringLayout.WEST, password, 30, SpringLayout.EAST, accountTypeL);
//
//        createLayout.putConstraint(SpringLayout.NORTH, submitNewUser, 60, SpringLayout.NORTH, passwordL);
//        createLayout.putConstraint(SpringLayout.WEST, submitNewUser, 80, SpringLayout.EAST, passwordL);
//
//        requestFields.add(newUserForm, "Create");
//
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        Request r = new Request();
    }

}
