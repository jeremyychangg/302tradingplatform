package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This was a class constructed for the legend. When called, it draws a square
 * that would be used to visually represent the data in the chart.
 *
 * @author Natalie Smith
 */
public class ColorSquare extends JPanel {
    public int width;
    public int height;
    public int x = 0;
    public int y = 0;
    public int r = 0;
    public int g = 0;
    public int b = 237;


    /**
     * This constructor is used to draw a square icon. This is used specifically
     * in the legend to associate the information to the chart. Given the inputted
     * parameters, the size and colour of the colour square can be manipulated.
     *
     * @param g G value of RGB colour mode
     * @param x X integer for horizontal position
     * @param y Y integer for vertical postion
     */
    public ColorSquare(int g, int x, int y) {
        this.width = 20;
        this.height = 20;
        this.g = g;
        this.x = x;
        this.y = y;
    }


    /**
     * Draw a square based on the parameters. Would thus create a JPanel of the graphic
     *
     * @param graphic
     */
    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        graphic.setColor(new Color(this.r, this.g, this.b));
        graphic.fillRect(20, this.getHeight() / 2 - 10, this.width, this.height);
    }
}
