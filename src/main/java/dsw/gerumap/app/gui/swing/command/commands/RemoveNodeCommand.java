package dsw.gerumap.app.gui.swing.command.commands;

import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.abstraction.MapNodeComposite;

import java.util.ArrayList;
import java.util.List;

public class RemoveNodeCommand extends AbstractCommand {

    private final MapNode parent;
    private final List<MapNode> children = new ArrayList<>();
    private final MapTab tab;


    public RemoveNodeCommand(MapNode parent, MapTab tab) {
        this.parent = parent;
        this.tab = tab;
    }

    @Override
    public void doCommand() {
        if (children.isEmpty() || parent == null) return;
        for (MapNode child : children) {
            ((MapNodeComposite) parent).removeChild(child);
        }
        tab.initPainters();
    }

    @Override
    public void undoCommand() {
        if (children.isEmpty() || parent == null) return;
        for (MapNode child : children) {
            ((MapNodeComposite) parent).addChild(child);
        }
        tab.initPainters();
    }

    public List<MapNode> getChildren() {
        return children;
    }
}


