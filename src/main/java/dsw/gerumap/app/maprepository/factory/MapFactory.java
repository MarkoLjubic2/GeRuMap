package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;

public class MapFactory extends Factory{

    private static int count = 1;

    @Override
    public MapNode createNode() {
        MapNode parent = getMapNode();
        return new MindMap("Map" + count++, parent);
    }

    @Override
    public MapNode getMapNode() {
        return MainFrame.getInstance().getTreeMap().getSelectedNode().getMapNode();
    }
}
