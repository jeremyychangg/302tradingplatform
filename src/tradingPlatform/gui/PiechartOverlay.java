package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;

//https://www.programmersought.com/article/64624246267/

class PiechartOverlay extends JPanel {
    Font font = new Font("Times", Font.PLAIN, 16);
    public int x;
    public int y;
    public int r = 238;
    public int g = 238;
    public int b = 238;
    public Color defaultColor = new Color (r,g,b);

    public PiechartOverlay( int r, int g, int b) {
        this.x = 600;
        this.y = 400;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        int radius = (int) (Math.min(100, 100));

        graphic.setColor(Color.red);
        graphic.fillArc(this.x  / 2 - radius, this.y / 2 - radius, radius * 2, radius * 2, (int) (0),
                (int) (360));
    }

}
