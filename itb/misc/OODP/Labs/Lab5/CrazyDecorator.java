package Lab5;

import java.awt.*;
import javax.swing.*;

public class CrazyDecorator extends Decorator {
    int x1, y1, w1, h1;

    public CrazyDecorator(JComponent c) {
        super(c);
    }
    public void setBounds(int x, int y, int w, int h) {
        x1 = x; y1= y;
        w1 = w; h1 = h;
        super.setBounds(x, y, w, h);
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        g.drawRect(x1, y1, h1, w1);
        g.fillRect(x1, y1, h1, w1);
    }
}
