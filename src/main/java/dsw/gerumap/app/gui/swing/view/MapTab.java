package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.controller.statectrl.MouseController;
import dsw.gerumap.app.gui.swing.view.painter.ElementPainter;
import dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import dsw.gerumap.app.gui.swing.view.painter.NodePainter;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.Link;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Node;
import dsw.gerumap.app.observer.StateSubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MapTab extends JPanel implements StateSubscriber, AdjustmentListener {

    private MindMap mindMap;

    private double scalingFactor = 1.0, dx = 0.0, dy = 0.0;
    private final List<Element> selectedNodes = new ArrayList<>();
    private final JPanel panel;
    private final JScrollBar hScrollBar;
    private final JScrollBar vScrollBar;

    private final List<ElementPainter> painters = new ArrayList<>();
    private Rectangle2D selectionRectangle;
    private boolean hasRoot = false;

    public MapTab(MindMap map) {
        this.mindMap = map;
        this.mindMap.addSubscriber(this);


        hScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100, 0, 1000);
        vScrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 100, 0, 1000);
        this.setLayout(new BorderLayout());
        panel = new Workspace();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(100, 150, 180));
        this.add(hScrollBar, BorderLayout.SOUTH);
        this.add(vScrollBar, BorderLayout.EAST);
        this.add(panel);

        panel.addMouseListener(new MouseController(this));
        panel.addMouseMotionListener(new MouseController(this));

        hScrollBar.addAdjustmentListener(this);
        vScrollBar.addAdjustmentListener(this);

        initPainters();
    }

    public void initPainters() {
        this.painters.clear();
        for (MapNode child : mindMap.getChildren()) {
            if (child instanceof Node) {
                Node e = (Node)child;
                this.painters.add(new NodePainter(e, new Ellipse2D.Double(e.getPosition().getX(), e.getPosition().getY(), e.getSize()[0], e.getSize()[1])));
                e.addSubscriber(this);
            }
            else if (child instanceof Link) {
                Link e = (Link)child;
                this.painters.add(new LinkPainter((Link)child));
                e.addSubscriber(this);
            }
        }
    }

    @Override
    public void update(Object o) {
        MainFrame.getInstance().getTreeMap().setSelectedNode(mindMap);
        MainFrame.getInstance().getTreeMap().fillElements(MainFrame.getInstance().getTreeMap().getSelectedNode());
        this.panel.repaint();
    }

    public JPanel getPanel() {
        return panel;
    }

    public boolean isHasRoot() {
        return hasRoot;
    }

    public void setHasRoot(boolean hasRoot) {
        this.hasRoot = hasRoot;
    }

    public MindMap getMindMap() {
        return mindMap;
    }

    public List<ElementPainter> getPainters() {
        return painters;
    }

    public Rectangle2D getSelectionRectangle() {
        return selectionRectangle;
    }

    public void setSelectionRectangle(Rectangle2D selectionRectangle) {
        this.selectionRectangle = selectionRectangle;
        panel.repaint();
    }

    public void setMindMap(MindMap mindMap) {
        this.mindMap = mindMap;
    }

    public List<Element> getSelectedNodes() {
        return selectedNodes;
    }

    public double getScalingFactor() {
        return scalingFactor;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setScalingFactor(double scalingFactor) {
        this.scalingFactor = scalingFactor;
        vScrollBar.setMaximum((int) (1000*scalingFactor));
        hScrollBar.setMaximum((int) (1000*scalingFactor));
        this.repaint();
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        JScrollBar js = (JScrollBar) e.getSource();
        if(js.getOrientation() == JScrollBar.HORIZONTAL) dx = e.getValue() - panel.getX();
        else dy = e.getValue() - panel.getY();
        panel.repaint();
    }

    public void resetSelection(){
        for (Element selectedNode : selectedNodes) {
            selectedNode.setCurrentColor(selectedNode.getElementColor());
        }
        selectedNodes.clear();
    }

    private class Workspace extends JPanel{
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            AffineTransform tx = new AffineTransform();
            tx.translate(-dx,-dy);
            tx.scale(scalingFactor,scalingFactor);
            g2.transform(tx);

            for (ElementPainter painter : painters) {
                painter.draw(g2, painter.getElement());
            }
            if (selectionRectangle != null) {
                g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{10.0f}, 0.0f));
                g2.draw(selectionRectangle);
                g2.setColor( new Color(180, 210, 255, 127));
                g2.fill(selectionRectangle);

            }
        }
    }

}
