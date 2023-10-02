package dsw.gerumap.app.maptree.model;

import dsw.gerumap.app.maprepository.abstraction.MapNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class MapTreeItem extends DefaultMutableTreeNode {

    private MapNode mapNode;

    public MapTreeItem(MapNode nodeModel) {
        this.mapNode = nodeModel;
    }

    @Override
    public String toString() {
        return mapNode.getName();
    }

    public void setName(String name) {
        this.mapNode.setName(name);
    }

    public MapNode getMapNode() {
        return mapNode;
    }

    public void setMapNode(MapNode mapNode) {
        this.mapNode = mapNode;
    }
}
