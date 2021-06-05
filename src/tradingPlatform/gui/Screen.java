/**
 * @author Natalie Smith
 */
package tradingPlatform.gui;

import tradingPlatform.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public abstract class Screen implements ActionListener {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenHeight = (int) screenSize.getHeight();
    public static int screenWidth = (int) screenSize.getWidth() - 320;

    // Font styling
    public static Font font1 = new Font("Avenir", Font.BOLD, 40);
    public static Font heading = new Font("Avenir", Font.PLAIN, 50);
    public static Font h1 = new Font("Avenir", Font.PLAIN, 25);
    public static Font body = new Font("Avenir", Font.PLAIN, 13);
    public static Font btnFont = new Font("Avenir", Font.PLAIN, 15);

    public Screen() {
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

    protected class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }


}

