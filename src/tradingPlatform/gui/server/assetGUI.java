package tradingPlatform.gui.server;

import tradingPlatform.exceptions.UnitException;
import tradingPlatform.gui.common.Screen;
import tradingPlatform.user.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * A class object used to display the Admin asset related actions. Within, the user
 * is able to create a unit, edit credit balance, and add inventory items to the related
 * unit ID's table. The unit GUI is displayed through a JPanel component, and constructs
 * a card layout to sort through each of the different actions that can be applied. Action
 * listeners are implemented to listen for buttons when processing the forms.
 *
 * @author Natalie Smith
 */
public class assetGUI extends JPanel implements ActionListener {
    private JPanel panel;
    private JButton createButton = new JButton("ADD NEW ASSET");
    private JButton submitNewAsset = new JButton("SUBMIT NEW ASSET");
    private JComboBox assetTypeSelect;
    private JPanel functions;

    public String type;
    public JTextField price;
    private JTextField assetName;

    String[] assetType = {"Computer Accessories", "Furniture", "Office Supplies", "Services", "Technology", "Miscellaneous"};

    CardLayout cardLayout = new CardLayout();

    /**
     * UnitGUI constructor used to call the different methods used to construct the userGUI panel
     * @throws Exception
     */
    public assetGUI() throws Exception {
        setUpPanel();
        Screen.welcomeMessage(panel);
        panel.add(createButton);
        cards();
        buttonInit();
        panel.add(functions);
        add(panel);
    }

    /**
     * This method is used to initalise the relevant button listeners and also
     * adds them to the panel.
     */
    private void buttonInit() {
        createButton.addActionListener(this);
        submitNewAsset.addActionListener(this);
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


    /**
     * Is used to call the card layouts for each of the forms.
     */
    private void cards() {
        newAssetCard();
    }


    /**
     * The new user card, when triggered will output a form for the administration to create a new unit
     * and add it to the database. Given that all the necessary inputs are valid and correct, the text inputs
     * from the form will construct a new user that will be inputted into the database.
     */
    public void newAssetCard() {
        SpringLayout createLayout = new SpringLayout();
        JLabel createLabel = new JLabel("Create New Asset");
        JLabel assetNameL = new JLabel("Asset Name");
        JLabel creditL = new JLabel("Price");
        JLabel typeL = new JLabel("Asset Type");

        assetName = new JTextField(25);
        price = new JTextField(25);
        assetTypeSelect = new JComboBox(assetType);
        assetTypeSelect.setSelectedIndex(1);
        assetTypeSelect.addActionListener(this);

        buttonStyle(submitNewAsset);

        JPanel newAssetForm = new JPanel();
        newAssetForm.setBackground(Color.white);
        newAssetForm.setPreferredSize(new Dimension(400, 500));
        newAssetForm.add(createLabel);
        newAssetForm.add(assetNameL);
        newAssetForm.add(assetName);
        newAssetForm.add(creditL);
        newAssetForm.add(price);
        newAssetForm.add(typeL);
        newAssetForm.add(assetTypeSelect);
        newAssetForm.add(submitNewAsset);
        newAssetForm.setLayout(createLayout);
        submitNewAsset.addActionListener(this);

        // Layout the form create new unit
        createLayout.putConstraint(SpringLayout.NORTH, createLabel, 50, SpringLayout.NORTH, newAssetForm);
        createLayout.putConstraint(SpringLayout.WEST, createLabel, 50, SpringLayout.WEST, newAssetForm);

        createLayout.putConstraint(SpringLayout.NORTH, assetNameL, 100, SpringLayout.NORTH, newAssetForm);
        createLayout.putConstraint(SpringLayout.WEST, assetNameL, 50, SpringLayout.WEST, newAssetForm);
        createLayout.putConstraint(SpringLayout.NORTH, assetName, 100, SpringLayout.NORTH, newAssetForm);
        createLayout.putConstraint(SpringLayout.WEST, assetName, 50, SpringLayout.EAST, assetNameL);

        createLayout.putConstraint(SpringLayout.NORTH, creditL, 50, SpringLayout.NORTH, assetNameL);
        createLayout.putConstraint(SpringLayout.WEST, creditL, 50, SpringLayout.WEST, newAssetForm);
        createLayout.putConstraint(SpringLayout.NORTH, price, 50, SpringLayout.NORTH, assetName);
        createLayout.putConstraint(SpringLayout.WEST, price, 30, SpringLayout.EAST, creditL);

        createLayout.putConstraint(SpringLayout.NORTH, typeL, 50, SpringLayout.NORTH, creditL);
        createLayout.putConstraint(SpringLayout.WEST, typeL, 70, SpringLayout.WEST, newAssetForm);
        createLayout.putConstraint(SpringLayout.NORTH, assetTypeSelect, 50, SpringLayout.NORTH, price);
        createLayout.putConstraint(SpringLayout.WEST, assetTypeSelect, 70, SpringLayout.EAST, typeL);

        createLayout.putConstraint(SpringLayout.NORTH, submitNewAsset, 60, SpringLayout.NORTH, typeL);
        createLayout.putConstraint(SpringLayout.WEST, submitNewAsset, 80, SpringLayout.EAST, typeL);

        functions.add(newAssetForm, "Create");
    }

    /**
     * Given a button is clicked (trigggered by the actionListener initiated), the actionPerformed points
     * the program to the sidebarListeners method to choose an outcome based on the button.
     *
     * @param e Action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Change the card based on the card button
        if (e.getSource() == createButton) {
            cardLayout.show(functions, "Create");
        }
        // If the User pressed new user button - send form
        if (e.getSource() == submitNewAsset) {
            try {
                addnewAsset();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            } catch (UnitException unitException) {
                JOptionPane.showMessageDialog(null, unitException.getMessage());
            }
        }
        if (e.getSource() == assetTypeSelect){
            type = (String) assetTypeSelect.getSelectedItem();
        }
    }


    /**
     * The method addnewAsset is used to create a new user in the database from the server side i.e. the
     * administration user. Given the retrieved inputs from the form GUI, a new user is constructed and
     * a new user ID is given to this new user. If the inputs all fulfil the requirements, and no errors
     * are thrown, the user is created in the server side. Throws a unit exception if it is found that the
     * unit does not exist. Otherwise, throws user exceptions when the user is found to not fulfil or meet
     * certian requirements i.e. password field is empty etc..
     *
     * @throws Exception
     * @throws UnitException
     */
    private void addnewAsset() throws Exception, UnitException {
        String assetNameInput = assetName.getText();
        String priceInput = price.getText();
        String typeInput = type;

        System.out.println(assetNameInput +  priceInput + typeInput);
        try {
            if (priceInput.equals(null) || !priceInput.equals(" ") || !priceInput.equals("")){
                Admin.newAsset(assetNameInput, Double.parseDouble(priceInput), typeInput);
                JOptionPane.showMessageDialog(null, "New Asset: Asset successfully created!" );
            }
            else {
                Admin.newAsset(assetNameInput, typeInput);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Reset Values
        assetName.setText("");
        price.setText("");
    }

    /**
     * Method used to style a button whilst matching the style guide.
     *
     * @param button
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