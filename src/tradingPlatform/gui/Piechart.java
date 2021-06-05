/**
 * @author Natalie Smith
 */
package tradingPlatform.gui;

import tradingPlatform.InventoryItem;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * https://www.programmersought.com/article/64624246267/
 */
public class Piechart extends JPanel {
    public int x;
    public int y;
    public int r = 0;
    public int g = 0;
    public int b = 237;
    Color baseBlue = new Color(r, g, b);
    ArrayList<InventoryItem> values;
    double inventorySize;


    /**
     *
     * @param r
     * @param g
     * @param b
     * @param values
     * @param inventorySize
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
     *
     * @param graphic
     */
    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        int radius = (int) (Math.min(150, 150));

        int i = 0;
        double lastValue = 0;
        for (InventoryItem c : values)
        {
            // get the percentage
            double percentage = (c.quantity * c.purchasePrice)/inventorySize;
            this.g = (255 / values.size()) * i;
            this.baseBlue = new Color(r, g, b);
            graphic.setColor(baseBlue);
            graphic.fillArc(this.x / 2 - radius, this.y / 2 - radius, radius * 2, radius * 2, (int) Math.round(360 * lastValue),
                    (int) Math.round(360 * percentage));
            i++;
            lastValue += percentage;
            System.out.println(360 * lastValue);
        }

        if (inventorySize == 0){
            this.baseBlue = new Color(0, 140, 237);
            graphic.setColor(baseBlue);
            graphic.fillArc(this.x / 2 - radius, this.y / 2 - radius, radius * 2, radius * 2, (int) 0,
                    (int) 360);
        }

        radius = 170;
        graphic.setColor(new Color(238, 238, 238));
        graphic.fillOval(this.x/2 - radius/2, this.y  / 2 - radius/2, radius, radius);
    }
}