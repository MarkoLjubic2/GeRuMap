package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.implementation.Project;

public class ProjectFactory extends Factory{

    private static int count = 1;

    @Override
    public MapNode createNode() {
        MapNode parent = getMapNode();
        return new Project("Project" + count++, parent, "Unknown", "");
    }

    @Override
    public MapNode getMapNode() {
        return MainFrame.getInstance().getTreeMap().getSelectedNode().getMapNode();
    }
}
