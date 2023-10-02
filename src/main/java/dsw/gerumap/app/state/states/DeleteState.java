package dsw.gerumap.app.state.states;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.command.commands.RemoveNodeCommand;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.gui.swing.view.painter.ElementPainter;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.Link;
import dsw.gerumap.app.maprepository.implementation.Node;
import dsw.gerumap.app.math.MathUtils;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class DeleteState implements State {

    @Override
    public void clickPerform(int x, int y, MapTab mapTab) {
        Element target = findTargetElement(x, y, mapTab);

        if (target == null) return;

        RemoveNodeCommand command = createRemoveNodeCommand(target, mapTab);

        updatePaintersAndMapTab(target, command, mapTab);
    }

    private Element findTargetElement(int x, int y, MapTab mapTab) {
        for (ElementPainter painter : mapTab.getPainters()) {
            if (painter.getElement() instanceof Node && painter.elementAt(painter.getElement(), new Point(x, y))) {
                mapTab.setHasRoot(false);
                return painter.getElement();
            } else if (painter.getElement() instanceof Link &&
                    MathUtils.getInstance().isPointInLine(new Point(x, y), ((Link) painter.getElement()).getStartPoint(), ((Link) painter.getElement()).getEndPoint())) {
                return painter.getElement();
            }
        }
        return null;
    }

    private RemoveNodeCommand createRemoveNodeCommand(Element target, MapTab mapTab) {
        RemoveNodeCommand command = new RemoveNodeCommand(mapTab.getMindMap(), mapTab);
        AppFramework.getAppFramework().getGui().getCommandManager().addCommand(command);
        command.getChildren().add(target);
        return command;
    }

    private void updatePaintersAndMapTab(Element target, RemoveNodeCommand command, MapTab mapTab) {
        List<ElementPainter> update = new ArrayList<>(mapTab.getPainters());
        for (ElementPainter painter : mapTab.getPainters()) {
            if (painter.getElement() instanceof Link && (target == ((Link) painter.getElement()).getStart() || target == ((Link) painter.getElement()).getEnd())) {
                update.remove(painter);
                command.getChildren().add(painter.getElement());
                mapTab.getMindMap().removeChild(painter.getElement());
            }
        }
        mapTab.getPainters().clear();
        mapTab.getPainters().addAll(update);

        mapTab.getPainters().removeIf(painter -> painter.getElement() == target);
        mapTab.getMindMap().removeChild(target);
    }

    @Override
    public void dragPerform(int x, int y, MapTab mapTab) {

    }

    @Override
    public void releasePerform(int x, int y, MapTab mapTab) {

    }
}
