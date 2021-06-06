package tradingPlatform.gui.common;

import tradingPlatform.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Is the abstract class parent to the Employee, Admin, and Lead screens. Is used to
 * call methods that are common among the different
 */
public abstract class Screen implements ActionListener {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenHeight = (int) screenSize.getHeight();
    public static int screenWidth = (int) screenSize.getWidth() - 320;
    public static int border;

    // Font styling
    public static Font font1 = new Font("Avenir", Font.BOLD, 40);
    public static Font heading = new Font("Avenir", Font.PLAIN, 50);
    public static Font h1 = new Font("Avenir", Font.PLAIN, 25);
    public static Font body = new Font("Avenir", Font.PLAIN, 13);
    public static Font btnFont = new Font("Avenir", Font.PLAIN, 15);

    public Screen() {
        if (Screen.screenWidth > 1400) {
            this.border = (int) (Screen.screenWidth * 0.2);
        } else {
            this.border = 80;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    protected static void changeButton(ImageIcon change, JButton button) {
        Image changed = change.getImage();
        Image changedScale = changed.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        ImageIcon newButtonIcon = new ImageIcon(changedScale);
        button.setIcon(newButtonIcon);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
    }

    public static void welcomeMessage(JPanel panel) throws SQLException {
        Font font1 = new Font("Avenir", Font.BOLD, 40);

        JLabel welcome = new JLabel("Hi,");
        welcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        welcome.setFont(font1);
        panel.add(welcome, BorderLayout.NORTH);

        JLabel name = new JLabel(User.getFirstName());
        name.setFont(font1);
        name.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        panel.add(name, BorderLayout.WEST);
    }

    public class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    /**
     * Makes a JFrame that would initialise and set up the GUI window. This
     * code is purely to create and setup up the frame and panels. The frame would
     * add the main sidebar panel - with BorderLayout - to keep on the WEST of the frame,
     * whilst the main panel (a.k.a. panel) would be set up to sit on the east of the frame.
     * The title of the window is set to the respective user type, and all of the data would
     * be packed to return a frame to the main constructor. The appearance of the GUI is
     * ultimately decided on the inputted parameters.
     *
     * @param frame
     * @param panel
     * @param sidebarPanel
     * @param title
     * @return a frame containing each the main panel and sidebar panel
     */
    protected JFrame setupFrame(String title, JPanel panel, JFrame frame, JPanel sidebarPanel) {
        frame = new JFrame();

        // Adding the panes to the final sidebar frame
        frame.add(panel, BorderLayout.EAST);
        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.setTitle(title);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    /**
     * A method used to align a list of buttons in the center of a component.
     *
     * @param elements a JButton list of elements that are to be aligned to center
     */
    public void alignCenter(JButton[] elements) {
        for (JButton component : elements) {
            component.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }

    /**
     * @param panel
     * @throws SQLException
     */
    public static void creditBalancePanel(JPanel panel) throws SQLException {
        Font heading = new Font("Avenir", Font.PLAIN, 50);

        JPanel creditsPanel = new JPanel();
        JPanel ordersPanel = new JPanel();

        JLabel creditsUserLabel = new JLabel("Credit Balance");
        JLabel creditsUser = new JLabel(Float.toString(User.getCredits()));
        JLabel outstandingLabel = new JLabel("Outstanding Orders");
        JLabel outstandingUser = new JLabel(Integer.toString(User.getOutstandingOrders()));

        outstandingUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        outstandingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsUserLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        creditsUser.setFont(heading);
        outstandingUser.setFont(heading);

        creditsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        ordersPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));

        creditsPanel.add(creditsUser);
        creditsPanel.add(creditsUserLabel);
        ordersPanel.add(outstandingUser);
        ordersPanel.add(outstandingLabel);

        panel.add(creditsPanel);
        panel.add(ordersPanel);

        creditsPanel.setBackground(Color.white);
        ordersPanel.setBackground(Color.white);
        panel.setBackground(Color.white);
    }
}

