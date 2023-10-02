package dsw.gerumap.app.maptree;

import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.maptree.model.MapTreeItem;
import dsw.gerumap.app.maptree.view.MapTreeView;
import dsw.gerumap.app.observer.TreePublisher;

public interface MapTree extends TreePublisher {

    MapTreeView generateTree(ProjectExplorer projectExplorer);

    void addChild(MapTreeItem parent);

    void removeChild(MapTreeItem child);

    MapTreeItem getSelectedNode();

    void setSelectedNode(MapNode node);

    void clearSelection();

    void fillElements(MapTreeItem parent);

    void loadProject(Project node);

    void loadTemplate(MindMap map);

}
