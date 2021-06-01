package tradingPlatform.gui;

import javax.swing.*;
import java.awt.*;

//https://www.programmersought.com/article/64624246267/

class Piechart extends JPanel {
    Font font = new Font("Times", Font.PLAIN, 16);
    public int x;
    public int y;

    public Piechart(int width, int height) {
        this.x = width;
        this.y = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int radius = (int) (Math.min(200, 200));


        System.out.println(this.x);
        String str;
        g.setColor(Color.RED);
        g.fillArc( this.x / 2 - radius, this.y / 2 - radius, radius * 2, radius * 2, 0, (int) (360 * 0.2));

        g.setColor(Color.BLUE);
        g.fillArc(this.x  / 2 - radius, this.y / 2 - radius, radius * 2, radius * 2, (int) (360 * 0.2),
                (int) (360 * 0.1));

        g.setColor(Color.GREEN);
        g.fillArc(this.x  / 2 - radius, this.y / 2 - radius, radius * 2, radius * 2, (int) (360 * 0.3),
                (int) (360 * 0.3));

        g.setColor(Color.ORANGE);
        g.fillArc(this.x  / 2 - radius, this.y / 2 - radius, radius * 2, radius * 2, (int) (360 * 0.6),
                (int) (360 * 0.4));

//        g.setFont(font);
//        g.setColor(Color.BLACK);
//        str = "Project -- 20%";
//        g.drawString(str, this.x  / 2 + radius, this.y / 2 - 20);
//
//        g.setColor(Color.BLACK);
//        str = "Quizzes -- 10%";
//        g.drawString(str, this.x  / 2, this.y / 2 - radius);
//
//        g.setColor(Color.BLACK);
//        str = "Midtems -- 30%";
//        g.drawString(str, this.x  / 2 - radius - 20 * 2, this.y / 2);
//
//        g.setColor(Color.BLACK);
//        str = "Final -- 40%";
//        g.drawString(str, this.x  / 2 + 20, this.y / 2 + radius);
    }
}
