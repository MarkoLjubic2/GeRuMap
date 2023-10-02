package dsw.gerumap.app.math;

import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.gui.swing.view.painter.ElementPainter;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.Link;
import dsw.gerumap.app.maprepository.implementation.Node;

import java.awt.*;

public class FixUtils {

    public static FixUtils instance;

    private FixUtils() {}

    public Point findP(Node e, Link l, boolean isReverse) {
        double k = MathUtils.getInstance().findDirection(l.getStartPoint(), l.getEndPoint());
        double n = MathUtils.getInstance().findYIntercept(l.getStartPoint(), l.getEndPoint());
        return MathUtils.getInstance().intersectionEllipseLine(new double[]{e.getSize()[0]/2, e.getSize()[1]/2,
                        e.getPosition().getX()+e.getSize()[0]/2, e.getPosition().getY()+e.getSize()[1]/2}, new double[] {k, n}, l.getStartPoint(),  l.getEndPoint() , isReverse);
    }

    public void fixUpStart(MapTab mapTab, Element node) {
        for (ElementPainter painterNode : mapTab.getPainters()) {
            if (painterNode.getElement() instanceof Node && painterNode.getElement() == node) {
                for (ElementPainter painterLink : mapTab.getPainters()) {
                    if (painterLink.getElement() instanceof Link && (((Link) painterLink.getElement()).getStart() == node)) {
                        Node e = (Node) node;
                        Point center = new Point((int)(e.getPosition().getX()+e.getSize()[0]/2), (int)(e.getPosition().getY()+e.getSize()[1]/2));
                        ((Link) painterLink.getElement()).setStartPoint(center);
                        Point p = findP(e, (Link) painterLink.getElement(), true);
                        if (p != null) ((Link) painterLink.getElement()).setStartPoint(p);
                        mapTab.getMindMap().notifyStateSubscribers(null);
                    }
                }
            }
        }
    }

    public void fixUpEnd(MapTab mapTab, Element node) {
        for (ElementPainter painterNode : mapTab.getPainters()) {
            if (painterNode.getElement() instanceof Node && painterNode.getElement() == node) {
                for (ElementPainter painterLink : mapTab.getPainters()) {
                    if (painterLink.getElement() instanceof Link && (((Link) painterLink.getElement()).getEnd() == node)) {
                        Node e = (Node) node;
                        Point center = new Point((int)(e.getPosition().getX()+e.getSize()[0]/2), (int)(e.getPosition().getY()+e.getSize()[1]/2));
                        ((Link) painterLink.getElement()).setEndPoint(center);
                        Point p = findP(e, (Link) painterLink.getElement(), false);
                        if (p != null) ((Link) painterLink.getElement()).setEndPoint(p);
                        mapTab.getMindMap().notifyStateSubscribers(null);
                    }
                }
            }
        }
    }

    public void calculateNextPosition(MapTab mapTab, Element node, int x, int y) {
        Node e = (Node) node;
        Point center = new Point( (int)(e.getPosition().getX()+e.getSize()[0]/2), (int)(e.getPosition().getY()+e.getSize()[1]/2) );
        for (ElementPainter linkPainter : mapTab.getPainters()) {
            if (linkPainter.getElement() instanceof Link && (((Link) linkPainter.getElement()).getStart() == e)) {
                int dx = (int)center.getX() - (int)((Link) linkPainter.getElement()).getStartPoint().getX();
                int dy = (int)center.getY() - (int)((Link) linkPainter.getElement()).getStartPoint().getY();
                ((Link) linkPainter.getElement()).setStartPoint(new Point(x - dx, y - dy));

            }
            else if (linkPainter.getElement() instanceof Link && (((Link) linkPainter.getElement()).getEnd() == e)) {
                int dx = (int)center.getX() - (int)((Link) linkPainter.getElement()).getEndPoint().getX();
                int dy = (int)center.getY() - (int)((Link) linkPainter.getElement()).getEndPoint().getY();
                ((Link) linkPainter.getElement()).setEndPoint(new Point(x - dx, y - dy));
            }
        }
    }

    public void fixUpAfterMove(MapTab mapTab, Element node, int x, int y) {
        Node e = (Node)node;

        for (ElementPainter link : mapTab.getPainters()) {
            if (link.getElement() instanceof Link && (mapTab.getSelectedNodes().contains(((Link) link.getElement()).getStart()) || mapTab.getSelectedNodes().contains(((Link) link.getElement()).getEnd()))) {
                int dx1 = (int) (e.getPosition().getX()-((Link) link.getElement()).getStartPoint().getX());
                int dy1 = (int) (e.getPosition().getY()-((Link) link.getElement()).getStartPoint().getY());
                int dx2 = (int) (e.getPosition().getX()-((Link) link.getElement()).getEndPoint().getX());
                int dy2 = (int) (e.getPosition().getY()-((Link) link.getElement()).getEndPoint().getY());
                if (mapTab.getSelectedNodes().contains(((Link) link.getElement()).getStart()) && mapTab.getSelectedNodes().contains(((Link) link.getElement()).getEnd())) {
                    ((Link) link.getElement()).setStartPoint(new Point(x-(int)(e).getSize()[0]/2-dx1, y-(int)(e).getSize()[1]/2-dy1));
                    ((Link) link.getElement()).setEndPoint(new Point(x-(int)(e).getSize()[0]/2-dx2, y-(int)(e).getSize()[1]/2-dy2));
                }
                else if (mapTab.getSelectedNodes().contains(((Link) link.getElement()).getStart())) {
                    ((Link) link.getElement()).setStartPoint(new Point(x-(int)(e).getSize()[0]/2-dx1, y-(int)(e).getSize()[1]/2-dy1));
                }
                else if (mapTab.getSelectedNodes().contains(((Link) link.getElement()).getEnd())) {
                    ((Link) link.getElement()).setEndPoint(new Point(x-(int)(e).getSize()[0]/2-dx2, y-(int)(e).getSize()[1]/2-dy2));
                }
            }
        }

    }

    public static FixUtils getInstance() {
        if (instance == null) {
            instance = new FixUtils();
        }
        return instance;
    }
}
