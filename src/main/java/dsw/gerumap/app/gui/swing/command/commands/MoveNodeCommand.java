package dsw.gerumap.app.gui.swing.command.commands;

import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.Link;
import dsw.gerumap.app.maprepository.implementation.Node;

import java.awt.*;
import java.util.HashMap;

public class MoveNodeCommand  extends AbstractCommand {

    private final MapNode parent;
    private final HashMap<Point[], Element> oldState = new HashMap<>();
    private final HashMap<Point[], Element> newState = new HashMap<>();
    private final MapTab tab;

    public MoveNodeCommand(MapNode parent, MapTab tab) {
        this.parent = parent;
        this.tab = tab;
    }

    private void setPoints(HashMap<Point[], Element> state) {
        if (state.isEmpty() || parent == null) return;
        for (Point[] key : state.keySet()) {
            Element e = state.get(key);
            if (e instanceof Node) ((Node) e).setPosition(key[0]);
            else if (e instanceof Link) {
                ((Link) e).setStartPoint(key[0]);
                ((Link) e).setEndPoint(key[1]);
            }
        }
        tab.initPainters();
    }

    @Override
    public void doCommand() {
        setPoints(newState);
    }

    @Override
    public void undoCommand() {
        setPoints(oldState);
    }

    public HashMap<Point[], Element> getOldState() {
        return oldState;
    }

    public HashMap<Point[], Element> getNewState() {
        return newState;
    }
}
