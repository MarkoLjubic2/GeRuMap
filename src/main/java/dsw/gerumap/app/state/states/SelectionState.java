package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.gui.swing.view.painter.ElementPainter;
import dsw.gerumap.app.maprepository.implementation.Link;
import dsw.gerumap.app.maprepository.implementation.Node;
import dsw.gerumap.app.math.MathUtils;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SelectionState implements State {

    private Point origin;

    @Override
    public void clickPerform(int x, int y, MapTab mapTab) {
        resetElementsColors(mapTab);

        boolean isSingleSelect = selectNodeOrLink(x, y, mapTab);

        if (!isSingleSelect) {
            mapTab.setSelectionRectangle(new Rectangle2D.Double());
            mapTab.getSelectionRectangle().setRect(x, y, 0, 0);
            this.origin = new Point(x, y);
        }
    }

    private void resetElementsColors(MapTab mapTab) {
        for (ElementPainter painter : mapTab.getPainters()) {
            painter.getElement().setCurrentColor(painter.getElement().getElementColor());
        }
        mapTab.getSelectedNodes().clear();
    }

    private boolean selectNodeOrLink(int x, int y, MapTab mapTab) {
        for (ElementPainter painter : mapTab.getPainters()) {
            if (painter.getElement() instanceof Node && painter.elementAt(painter.getElement(), new Point(x, y))) {
                painter.getElement().setCurrentColor(painter.getElement().getSelectedColor());
                mapTab.getSelectedNodes().add(painter.getElement());
                return true;
            } else if (painter.getElement() instanceof Link && MathUtils.getInstance().isPointInLine(new Point(x, y), ((Link) painter.getElement()).getStartPoint(),
                    ((Link) painter.getElement()).getEndPoint())) {
                painter.getElement().setCurrentColor(painter.getElement().getSelectedColor());
                mapTab.getSelectedNodes().add(painter.getElement());
                return true;
            }
        }
        return false;
    }

    @Override
    public void dragPerform(int x, int y, MapTab mapTab) {
        dragPerformRectangleAdjustment(x, y, mapTab);
        updateSelectedNodes(mapTab);
    }

    public void dragPerformRectangleAdjustment(int x, int y, MapTab mapTab) {
        if (mapTab.getSelectionRectangle() != null) {
            int X = (int) origin.getX(), Y = (int) origin.getY();
            if (x > X && y > Y) {
                mapTab.getSelectionRectangle().setRect(X, Y, x - X, y - Y);
            } else if (x < X && y < Y) {
                mapTab.getSelectionRectangle().setRect(x, y, X - x, Y - y);
            } else if (x > X && y < Y) {
                mapTab.getSelectionRectangle().setRect(X, y, x - X, Y - y);
            } else if (x < X && y > Y) {
                mapTab.getSelectionRectangle().setRect(x, Y, X - x, y - Y);
            }
        }
    }

    public void updateSelectedNodes(MapTab mapTab) {
        for (ElementPainter painter : mapTab.getPainters()) {
            if (painter.getElement() instanceof Node) {
                int X1 = (int) ((Node) painter.getElement()).getPosition().getX();
                int Y1 = (int) ((Node) painter.getElement()).getPosition().getY();
                if (mapTab.getSelectionRectangle().intersects(X1, Y1, (int) ((Node) painter.getElement()).getSize()[0], (int) ((Node) painter.getElement()).getSize()[1])) {
                    painter.getElement().setCurrentColor(painter.getElement().getSelectedColor());
                    if (!mapTab.getSelectedNodes().contains(painter.getElement())) {
                        mapTab.getSelectedNodes().add(painter.getElement());
                    }
                } else {
                    painter.getElement().setCurrentColor(painter.getElement().getElementColor());
                    mapTab.getSelectedNodes().remove(painter.getElement());
                }
            }
        }
    }

    @Override
    public void releasePerform(int x, int y, MapTab mapTab) {
        mapTab.setSelectionRectangle(null);
    }
}
