// testing table function from https://www.codejava.net/java-se/swing/a-simple-jtable-example-for-display

package tradingPlatform.gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;


public class Table extends JPanel {
    Font tableSize = new Font("Avenir", Font.PLAIN, 16);
    Font headerSize = new Font("Avenir", Font.BOLD, 18);

    public Table(String[] columns, Object[][] data, Integer[] width) {
        //headers for the table
        JPanel panel = new JPanel();

        //actual data for the table in a 2d array

        //create table with data
        JTable table = new JTable(data, columns);
        JTableHeader header = table.getTableHeader();

        table.setAlignmentX(Component.LEFT_ALIGNMENT);
        table.setRowHeight(35);
        table.setOpaque(false);
        table.setFont(tableSize);

        header.setPreferredSize(new Dimension(1200, 50));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setOpaque(false);
        header.setFont(headerSize);

        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table.setModel(tableModel);

        // changing the width of the columns
        TableColumnModel columnModel = table.getColumnModel();
        TableColumnModel headerModel = header.getColumnModel();

        int index = 0;
        for (String column : columns){
            columnModel.getColumn(index).setPreferredWidth(width[index]);

            // Render the table contents to be in the centre of the table
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            headerModel.getColumn(index).setCellRenderer(centerRenderer);
            columnModel.getColumn(index).setCellRenderer( centerRenderer );
            index++;
        }


        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);
//        panel.add(table);
        add(panel);
    }
}