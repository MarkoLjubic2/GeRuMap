package dsw.gerumap.app.gui.swing.view.painter;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.Node;
import dsw.gerumap.app.math.FixUtils;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class NodePainter extends ElementPainter{

    private Shape shape;

    public NodePainter(Element element, Shape shape) {
        super(element);
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g, Element element) {
        Node node = (Node)element;
        setNodeSize(node, g);
        setFontAndStyle(g, element);
        drawNode(g, element);
    }

    private void setNodeSize(Node node, Graphics2D g) {
        int width = g.getFontMetrics().stringWidth(node.getText()) + 80;
        if (node.getSize()[0] != width && node.getSize()[1] != width/3.0) {
            MapTab tab = (MapTab) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
            node.setSize(new double[] {width, width/3.0});
            if (!tab.getSelectedNodes().isEmpty()) {
                try {
                    FixUtils.getInstance().fixUpStart(tab, tab.getSelectedNodes().get(tab.getSelectedNodes().indexOf(node)));
                    FixUtils.getInstance().fixUpEnd(tab, tab.getSelectedNodes().get(tab.getSelectedNodes().indexOf(node)));
                }
                catch (Exception ignored) {}
            }
        }
    }

    private void setFontAndStyle(Graphics2D g, Element element) {
        if (element.getName().equals("Root Node")) {
            g.setStroke(new BasicStroke(element.getStroke()+2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0.0f));
            g.setFont(new Font("New York", Font.BOLD,12));
        }
        else {
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.setStroke(new BasicStroke(element.getStroke(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0.0f));
        }
    }

    private void drawNode(Graphics2D g, Element element) {
        Node node = (Node)element;
        this.shape = new Ellipse2D.Double(node.getPosition().getX(), node.getPosition().getY(), node.getSize()[0], node.getSize()[1]);
        g.setColor(new Color(element.getCurrentColor()[0], element.getCurrentColor()[1], element.getCurrentColor()[2]));
        g.fill(this.shape);
        g.setColor(Color.BLACK);
        g.drawString(node.getText(), (int)node.getPosition().getX() + 40, (int)node.getPosition().getY() + (int)node.getSize()[1]/2 + 5);
        g.draw(this.shape);
    }


    @Override
    public boolean elementAt(Element element, Point pos) {
        return this.shape.contains(pos.getX(), pos.getY());
    }
}
