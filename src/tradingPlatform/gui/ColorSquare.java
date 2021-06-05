package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;

public class ColorSquare extends JPanel {
    public int width;
    public int height;
    public int x = 0;
    public int y = 0;
    public int r = 0;
    public int g = 0;
    public int b = 237;


    /**
     *
     * @param g
     * @param x
     * @param y
     */
    public ColorSquare(int g, int x, int y){
        this.width = 20;
        this.height = 20;
        this.g = g;
        this.x = x;
        this.y = y;
    }


    /**
     *
     * @param graphic
     */
    @Override
    protected void paintComponent(Graphics graphic){
        super.paintComponent(graphic);
        graphic.setColor(new Color(this.r, this.g, this.b));
        graphic.fillRect(20, this.getHeight()/2 - 10, this.width, this.height);
    }
}