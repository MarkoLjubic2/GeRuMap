package dsw.gerumap.app.state.states;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.command.commands.MoveNodeCommand;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.gui.swing.view.painter.ElementPainter;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.Link;
import dsw.gerumap.app.maprepository.implementation.Node;
import dsw.gerumap.app.math.FixUtils;
import dsw.gerumap.app.state.State;

import java.awt.*;

public class MoveState implements State {

    private Node target;
    private MoveNodeCommand command;

    @Override
    public void clickPerform(int x, int y, MapTab mapTab) {

        for (ElementPainter painter : mapTab.getPainters()) {
            if (painter.getElement() instanceof Node) {
                if (painter.elementAt(painter.getElement(), new Point(x, y))){
                    this.target = (Node) painter.getElement();
                    break;
                }
            }
        }

        if (target != null) {
            command = new MoveNodeCommand(mapTab.getMindMap(), mapTab);
            AppFramework.getAppFramework().getGui().getCommandManager().addCommand(command);
            for (Element selectedNode : mapTab.getSelectedNodes()) {
                if (selectedNode instanceof Node) command.getOldState().put(new Point[]{((Node) selectedNode).getPosition()}, selectedNode);
            }
            for (ElementPainter painter : mapTab.getPainters()) {
                if (painter.getElement() instanceof Link) {
                    if (painter.getElement() instanceof Link && (mapTab.getSelectedNodes().contains(((Link) painter.getElement()).getStart()) || mapTab.getSelectedNodes().contains(((Link) painter.getElement()).getEnd())))
                        command.getOldState().put(new Point[]{((Link) painter.getElement()).getStartPoint(),((Link) painter.getElement()).getEndPoint()}, painter.getElement());
                }
            }
        }
    }

    public void dragSingleElement(int x, int y, MapTab mapTab, ElementPainter nodePainter) {
        if (x - (int)((Node) nodePainter.getElement()).getSize()[0] / 2 < 0 || y - (int)((Node) nodePainter.getElement()).getSize()[1] / 2 < 0) {
            return;
        }

        FixUtils.getInstance().calculateNextPosition(mapTab, nodePainter.getElement(), x, y);
        ((Node) nodePainter.getElement()).setPosition(new Point(x - (int)((Node) nodePainter.getElement()).getSize()[0] / 2, y - (int)((Node) nodePainter.getElement()).getSize()[1] / 2));
    }

    public void dragMultipleElements(int x, int y, MapTab mapTab, ElementPainter nodePainter) {
        for (Element l : mapTab.getSelectedNodes()) {
            if (l == nodePainter.getElement()) continue;

            int Dx = ((int)((Node) nodePainter.getElement()).getPosition().getX() - (int)((Node) l).getPosition().getX());
            int Dy = (int)((Node) nodePainter.getElement()).getPosition().getY() - (int)((Node) l).getPosition().getY();
            int px = x - (int)((Node) nodePainter.getElement()).getSize()[0] / 2;
            int py = y - (int)((Node) nodePainter.getElement()).getSize()[1] / 2;
            ((Node) l).setPosition(new Point(px - Dx, py - Dy));
        }

        FixUtils.getInstance().fixUpAfterMove(mapTab, nodePainter.getElement(), x, y);
        ((Node) nodePainter.getElement()).setPosition(new Point(x - (int)((Node) nodePainter.getElement()).getSize()[0] / 2, y - (int)((Node) nodePainter.getElement()).getSize()[1] / 2));
    }

    @Override
    public void dragPerform(int x, int y, MapTab mapTab) {
        for (ElementPainter nodePainter : mapTab.getPainters()) {
            if (nodePainter.getElement() instanceof Node) {
                if (mapTab.getSelectedNodes().size() == 1 && mapTab.getSelectedNodes().contains(nodePainter.getElement())) {
                    dragSingleElement(x, y, mapTab, nodePainter);
                } else if (nodePainter.getElement() == this.target && mapTab.getSelectedNodes().size() > 1 && mapTab.getSelectedNodes().contains(nodePainter.getElement())) {
                    dragMultipleElements(x, y, mapTab, nodePainter);
                }
            }
        }
    }

    @Override
    public void releasePerform(int x, int y, MapTab mapTab) {
        if (target != null) {
            for (Element selectedNode : mapTab.getSelectedNodes()) {
                if (selectedNode instanceof Node) command.getNewState().put(new Point[]{((Node) selectedNode).getPosition()}, selectedNode);
            }
            for (ElementPainter painter : mapTab.getPainters()) {
                if (painter.getElement() instanceof Link) {
                    if (painter.getElement() instanceof Link && (mapTab.getSelectedNodes().contains(((Link) painter.getElement()).getStart()) || mapTab.getSelectedNodes().contains(((Link) painter.getElement()).getEnd())))
                        command.getNewState().put(new Point[]{((Link) painter.getElement()).getStartPoint(),((Link) painter.getElement()).getEndPoint()}, painter.getElement());
                }
            }
        }
        this.target = null;
    }
}
