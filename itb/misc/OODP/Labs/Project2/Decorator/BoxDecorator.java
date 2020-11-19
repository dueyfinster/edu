package Project2.Decorator;

import java.awt.*;

import javax.swing.*;

public class BoxDecorator extends Decorator {
    int x1, y1, w1, h1;

    public BoxDecorator(JComponent c) {
        super(c);
    }
    public void setBounds(int x, int y, int w, int h) {
        x1 = x; y1= y;
        w1 = w; h1 = h;
        super.setBounds(x, y, w, h);
    }
    public void paint(Graphics g) {
        super.paint(g);
        Dimension d = super.getSize();
        g.setColor(Color.red);
        g.drawRect(0, 0, d.width-1, d.height-1);
        g.drawLine(d.width-2, 0, d.width-2, d.height-1);
        g.drawLine(0, d.height-2, d.width-2, d.height-2);
    }
}
