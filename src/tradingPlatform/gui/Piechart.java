/**
 * @author Natalie Smith
 */
package tradingPlatform.gui;

import tradingPlatform.InventoryItem;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * The Piechart class is used to create the object of a piechart. Extending a
 * JPanel, when called, given the
 * This class was adapted from:
 * https://www.programmersought.com/article/64624246267/
 */
public class Piechart extends JPanel {
    private int x;
    private int y;
    private int r;
    private int g;
    private int b;
    Color baseBlue = new Color(r, g, b);
    ArrayList<InventoryItem> values;
    double inventorySize;

    /**
     * The piechart constructor is used to draw the piechart panel onto the frame, given
     * the parameters. This should construct a whole pie chart with a hollow inside.
     *
     * @param r Associated Red value in RGB colormode
     * @param g Associated Green value in RGB colormode
     * @param b Associated Blue value in RGB colormode
     * @param values an ArrayList of inventory items and their quantity, price
     * @param inventorySize the value of inventory associated to unit
     */
    public Piechart(int r, int g, int b, ArrayList<InventoryItem> values, double inventorySize) {
        this.x = 600;
        this.y = 400;
        this.r = r;
        this.g = g;
        this.b = b;
        this.values = values;
        this.inventorySize = inventorySize;
    }


    /**
     * This method overrides the super paintComponent method, and is used to construct the pie chart
     * sections. Given the assigned r, g,b, values and inventory size, the pie chart is constructed
     * to match the portions of each inventory against the rest.
     * @param graphic A graphic is parsed
     */
    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        // Radius of pie chart
        int radius = Math.min(150, 150);

        // For each of the items in inventory, draw sections of the pie chart based on percentage
        // of inventory
        int i = 0;
        double lastValue = 0;
        for (InventoryItem c : values)
        {
            // get the percentage of item against inventory
            double percentage = (c.quantity * c.purchasePrice)/inventorySize;

            // Increase color
            this.g = (255 / values.size()) * i;
            this.baseBlue = new Color(r, g, b);
            graphic.setColor(baseBlue);

            // Draw section with associated percentage
            graphic.fillArc(this.x / 2 - radius, this.y / 2 - radius, radius * 2, radius * 2, (int) Math.round(360 * lastValue),
                    (int) Math.round(360 * percentage));
            i++;
            lastValue += percentage;
        }

        // Return a chart with a single color if no items in inventory
        if (inventorySize == 0){
            this.baseBlue = new Color(0, 140, 237);
            graphic.setColor(baseBlue);
            graphic.fillArc(this.x / 2 - radius, this.y / 2 - radius, radius * 2, radius * 2, (int) 0,
                    (int) 360);
        }

        // To give chart matching aesthetic, circle drawn above the graph
        radius = 170;
        graphic.setColor(new Color(238, 238, 238));
        graphic.fillOval(this.x/2 - radius/2, this.y  / 2 - radius/2, radius, radius);
    }
}