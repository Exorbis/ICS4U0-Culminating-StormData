package initialpkg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TimelineCircle extends JPanel {

    private Shape circle;
    private Dimension dim = new Dimension(450, 300);
    private final ArrayList<Shape> shapes;
    private static Storm datum;
    private static int xpos;

    public TimelineCircle(Storm datum, int xpos) {
    	this.xpos = xpos;
        shapes = new ArrayList<>();
        this.circle = new Ellipse2D.Double(xpos, 100, 10, 10);
        shapes.add(this.circle);
        
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                for (Shape s : shapes) {

                    if (s.contains(me.getPoint())) {//check if mouse is clicked within shape
                        if (s instanceof Ellipse2D) {
                            System.out.println();
                        }

                    }
                }
            }
        });
    }
    
    public static void initComponents(JFrame frame){
    	frame.add(new TimelineCircle(datum, xpos), BorderLayout.PAGE_END);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Shape s : shapes) {
            g2d.draw(s);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return dim;
    }
	
}
