package dsw.gerumap.app.gui.swing.command.commands;

import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.abstraction.MapNodeComposite;

public class AddNodeCommand extends AbstractCommand {

    private final MapNode parent;
    private final MapNode child;
    private final MapTab tab;


    public AddNodeCommand(MapNode parent, MapNode child, MapTab tab) {
        this.parent = parent;
        this.child = child;
        this.tab = tab;
    }

    @Override
    public void doCommand() {
        if(child == null ||  parent == null) return;
        ((MapNodeComposite) parent).addChild(child);
        tab.initPainters();
    }

    @Override
    public void undoCommand() {
        if(child == null ||  parent==null) return;
        ((MapNodeComposite) parent).removeChild(child);
        tab.initPainters();
    }

}
