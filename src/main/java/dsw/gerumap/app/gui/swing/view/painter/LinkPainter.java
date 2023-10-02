package dsw.gerumap.app.gui.swing.view.painter;

import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.Link;
import dsw.gerumap.app.maprepository.implementation.Node;
import dsw.gerumap.app.math.MathUtils;

import java.awt.*;
import java.awt.geom.Line2D;

public class LinkPainter extends ElementPainter{

    public LinkPainter(Element element) {
        super(element);
    }

    @Override
    public void draw(Graphics2D g, Element element) {

        g.setColor(new Color(element.getCurrentColor()[0], element.getCurrentColor()[1], element.getCurrentColor()[2]));
        g.setStroke(new BasicStroke(element.getStroke()));
        double x1 = ((Link)element).getStartPoint().getX();
        double y1 = ((Link)element).getStartPoint().getY();
        double x2 = ((Link)element).getEndPoint().getX();
        double y2 = ((Link)element).getEndPoint().getY();
        Line2D line = new Line2D.Double(x1, y1, x2, y2);
        g.draw(line);

        int[][] linearTransformPoints = MathUtils.getInstance().linearTransformTriangle(new Point((int)x1, (int)y1), new Point((int)x2, (int)y2));
        g.fillPolygon(linearTransformPoints[0], linearTransformPoints[1], 3);

    }

    @Override
    public boolean elementAt(Element element, Point pos) {
        Node e = (Node)element;
        Point center = new Point( (int)(e.getPosition().getX()+e.getSize()[0]/2), (int)(e.getPosition().getY()+e.getSize()[1]/2) );
        int X = (int)pos.getX()-(int)center.getX(), Y =  (int)pos.getY()-(int)center.getY();
        return ((X*X)/((e.getSize()[0]/2)*(e.getSize()[0]/2))+(Y*Y)/((e.getSize()[1]/2)*(e.getSize()[1]/2)) <= 1);
    }
}
