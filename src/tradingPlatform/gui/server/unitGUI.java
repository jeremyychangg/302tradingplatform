package tradingPlatform.gui.server;

import tradingPlatform.Unit;
import tradingPlatform.exceptions.NegativePriceException;
import tradingPlatform.exceptions.UnitException;
import tradingPlatform.exceptions.UserException;
import tradingPlatform.gui.common.Screen;
import tradingPlatform.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static tradingPlatform.Unit.getUnit;

/**
 * A class object used to display the Admin unit related actions. Within, the user
 * is able to create a unit, edit credit balance, and add inventory items to the related
 * unit ID's table. The unit GUI is displayed through a JPanel component, and constructs
 * a card layout to sort through each of the different actions that can be applied. Action
 * listeners are implemented to listen for buttons when processing the forms.
 *
 * @author Natalie Smith
 */
public class unitGUI extends JPanel implements ActionListener {
    private JPanel panel;
    private JButton createButton = new JButton("CREATE NEW UNIT");
    private JButton editButton = new JButton("EDIT UNIT");
    private JButton changeButton = new JButton("EDIT UNIT INVENTORY");

    private JButton submitNewUnit = new JButton("SUBMIT NEW UNIT");
    private JButton changeBalanceBtn = new JButton("CHANGE BALANCE");
    private JButton editBalance = new JButton("EDIT BALANCE");

    private JPanel functions;

    public JTextField unitID;
    public JTextField creditBalance;
    public JTextField limit;
    private JTextField unitName;
    private JTextField creditBalanceChange;

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

    /**
     * This method is used to initalise the relevant button listeners and also
     * adds them to the panel.
     */
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
        editBalanceCard();
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
        JLabel creditLabel = new JLabel("Credit Balance");

        unitID = new JTextField(25);
        creditBalanceChange = new JTextField(25);
        editBalance = new JButton("SUBMIT");

        buttonStyle(changeBalanceBtn);

        editUserForm.add(editLabel);
        editUserForm.add(unitIDLabel);
        editUserForm.add(unitID);
        editUserForm.add(creditLabel);
        editUserForm.add(creditBalanceChange);
        editUserForm.add(changeBalanceBtn);

        editLayout.putConstraint(SpringLayout.NORTH, editLabel, 50, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, editLabel, 50, SpringLayout.WEST, editUserForm);

        editLayout.putConstraint(SpringLayout.NORTH, unitIDLabel, 100, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, unitIDLabel, 50, SpringLayout.WEST, editUserForm);
        editLayout.putConstraint(SpringLayout.NORTH, unitID, 90, SpringLayout.NORTH, editUserForm);
        editLayout.putConstraint(SpringLayout.WEST, unitID, 80, SpringLayout.EAST, unitIDLabel);

        editLayout.putConstraint(SpringLayout.NORTH, creditLabel, 50, SpringLayout.NORTH, unitIDLabel);
        editLayout.putConstraint(SpringLayout.WEST, creditLabel, 50, SpringLayout.WEST, editUserForm);
        editLayout.putConstraint(SpringLayout.NORTH, creditBalanceChange, 50, SpringLayout.NORTH, unitID);
        editLayout.putConstraint(SpringLayout.WEST, creditBalanceChange, 80, SpringLayout.EAST, unitIDLabel);

        editLayout.putConstraint(SpringLayout.NORTH, changeBalanceBtn, 50, SpringLayout.NORTH, creditLabel);
        editLayout.putConstraint(SpringLayout.WEST, changeBalanceBtn, 80, SpringLayout.EAST, creditLabel);

        functions.add(editUserForm, "Edit");
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
        if (e.getSource() == submitNewUnit) {
            try {
                addnewUnit();
            } catch (UnitException unitException) {
            } catch (Exception exception) {
            }
        }
        // If user presses edit account button - send form
        if (e.getSource() == changeBalanceBtn) {
            try {
                editBalance();
            } catch (UserException | UnitException | NegativePriceException userException) {
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
     * @throws UserException
     */
    private void editBalance() throws UserException, UnitException, NegativePriceException {
        String unitIDInput = unitID.getText();
        String creditBalanceInput = creditBalanceChange.getText();
        System.out.println(unitIDInput + " " + creditBalanceInput);
        try {
            Unit changing = getUnit(unitIDInput);
            changing.ChangeUnitBalance(changing.unitID, Double.parseDouble(creditBalanceInput));
        } catch (NullPointerException e) {
            String msg = "Edit User Error: Empty values. Please try again.";
            JOptionPane.showMessageDialog(null, msg);
            throw new NullPointerException(msg);
        } catch (SQLException e) {
            String msg = "Edit User SQL Error: Could not add to database.";
            JOptionPane.showMessageDialog(null, msg);
            throw new UnitException(msg);
        }
    }

    public void buttonStyle(JButton button) {
        button.setMargin(new Insets(5, 20, 5, 20));
        button.setBackground(new Color(0, 140, 237));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }
}