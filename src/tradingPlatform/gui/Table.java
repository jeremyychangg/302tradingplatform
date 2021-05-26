// testing table function from https://www.codejava.net/java-se/swing/a-simple-jtable-example-for-display

package tradingPlatform.gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;


public class Table extends JPanel {
    Font tableSize = new Font("Avenir", Font.PLAIN, 18);

    public Table() {
        //headers for the table
        JPanel panel = new JPanel();
        String[] columns = new String[] {
                "Order ID", "Asset", "Quantity", "Price", "Date"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
                {1, "Printing Paper", 50, "$" + 100, 10/02/2020 },
                {2, "CPU Hours", 4, "$" + 100, 10/02/2020 },
                {3, "Mousepad", 5, "$" + 100, 10/02/2020 },
        };
        //create table with data
        JTable table = new JTable(data, columns);
        table.setAlignmentX(Component.LEFT_ALIGNMENT);
        table.setRowHeight(35);
        table.setOpaque(false);
        table.setFont(tableSize);
        // changing the width of the columns
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(70);
        columnModel.getColumn(3).setPreferredWidth(70);
        columnModel.getColumn(4).setPreferredWidth(100);



        //add the table to the frame
//        new JScrollPane(table)
        panel.add(table);
        add(panel);
    }
}