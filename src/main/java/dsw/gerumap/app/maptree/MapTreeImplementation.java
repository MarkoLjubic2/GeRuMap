package dsw.gerumap.app.maptree;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.maprepository.factory.FactoryUtils;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.abstraction.MapNodeComposite;
import dsw.gerumap.app.maprepository.implementation.*;
import dsw.gerumap.app.maptree.model.MapTreeItem;
import dsw.gerumap.app.maptree.view.MapTreeView;
import dsw.gerumap.app.observer.TreeSubscriber;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class MapTreeImplementation implements MapTree {

    private MapTreeView mtView;
    private DefaultTreeModel dftModel;

    List<TreeSubscriber> subscribers;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        dftModel = new DefaultTreeModel(root);
        mtView = new MapTreeView(dftModel);
        return mtView;
    }

    private MapNode generateChild(MapNode parent){
        return FactoryUtils.getInstance().createNode(parent);
    }

    @Override
    public void addChild(MapTreeItem parent) {
        if(parent == null || parent.getMapNode() instanceof MindMap || !(parent.getMapNode() instanceof MapNodeComposite))
            return;

        MapNode child = generateChild(parent.getMapNode());
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        mtView.expandPath(mtView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mtView);
        this.notifyAdd(child);
    }

    @Override
    public void fillElements(MapTreeItem parent) {
        if(parent == null || !(parent.getMapNode() instanceof MindMap))
            return;

        MapNodeComposite map = (MapNodeComposite) parent.getMapNode();
        parent.removeAllChildren();
        for (MapNode element : map.getChildren()) {
            parent.add(new MapTreeItem(element));
        }
        mtView.expandPath(mtView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mtView);
    }

    @Override
    public void loadProject(Project node) {
        MapTreeItem loadedProject = new MapTreeItem(node);
        MapTreeItem root = (MapTreeItem) dftModel.getRoot();
        root.add(loadedProject);
        MapNodeComposite mapNode = (MapNodeComposite) root.getMapNode();
        mapNode.addChild(node);

        for (MapNode mindMap : node.getChildren()) {
            mindMap.setParent(node);
            MapTreeItem map = new MapTreeItem(mindMap);
            loadedProject.add(map);
            for (MapNode element : ((MindMap)mindMap).getChildren()) {
                map.add(new MapTreeItem(element));
                element.setParent(mindMap);
                if (element instanceof Link) {
                    for (MapNode child : ((MindMap) mindMap).getChildren()) {
                        if (child instanceof Node && ((Link) element).getStart().getName().equals(child.getName()))
                            ((Link) element).setStart((Node) child);
                        if (child instanceof Node && ((Link) element).getEnd().getName().equals(child.getName()))
                            ((Link) element).setEnd((Node) child);
                    }
                }
            }
        }
        mtView.expandPath(mtView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mtView);
    }

    @Override
    public void loadTemplate(MindMap map) {
        MapTab tab = (MapTab) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        for (MapNode child : map.getChildren()) {
            tab.getMindMap().addChild(child);
            child.setParent(map);
        }
        tab.initPainters();
        mtView.expandPath( mtView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mtView);
    }

    @Override
    public void removeChild(MapTreeItem child) {
        if(child.getMapNode() instanceof ProjectExplorer || child.getMapNode() instanceof Element)
            return;

        ((MapTreeItem) child.getParent()).remove(child);
        MapNodeComposite parent = (MapNodeComposite) child.getMapNode().getParent();
        parent.removeChild(child.getMapNode());

        mtView.expandPath(mtView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mtView);
        clearSelection();
        this.notifyRemove(child.getMapNode());
    }

    @Override
    public void setSelectedNode(MapNode node) {
        if (node instanceof MindMap) {
            MapTreeItem root = (MapTreeItem) dftModel.getRoot();
            for (int i = 0; i < root.getChildCount(); i++) {
                if (((MapTreeItem)root.getChildAt(i)).getMapNode() == node.getParent()) {
                    for (int j = 0; j < root.getChildAt(i).getChildCount(); j++) {
                        MapTreeItem map = (MapTreeItem) root.getChildAt(i).getChildAt(j);
                        if (map.getMapNode() == node) {
                            TreePath path = new TreePath(dftModel.getPathToRoot(map));
                            mtView.setSelectionPath(path);
                        }
                    }
                }
            }
        }
    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) mtView.getLastSelectedPathComponent();
    }

    @Override
    public void clearSelection() {
        mtView.setSelectionRow(0);
    }

    @Override
    public void addSubscriberTree(TreeSubscriber s) {
        if(s == null)
            return;
        if(this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(s))
            return;
        this.subscribers.add(s);
    }

    @Override
    public void removeSubscriberTree(TreeSubscriber s) {
        if(s == null ||  this.subscribers == null || !this.subscribers.contains(s))
            return;
        this.subscribers.remove(s);
    }

    @Override
    public void notifyRemove(Object o) {
        if(o == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for(TreeSubscriber subscriber : subscribers){
            subscriber.updateRemovedMap(o);
        }
    }

    @Override
    public void notifyAdd(Object o) {
        if (o == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (TreeSubscriber subscriber : subscribers) {
            subscriber.updateAddedMap(o);
        }

    }
}
