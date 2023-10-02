package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.gui.swing.view.ProjectView;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maptree.MapTree;
import dsw.gerumap.app.maptree.model.MapTreeItem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabGenerator {

    public TabGenerator() {

    }

    public void generateTabs(JTree projectExplorer) {
        ProjectView projectView= MainFrame.getInstance().getProjectView();
        MapTree mapTree=MainFrame.getInstance().getTreeMap();
        projectExplorer.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    MapTreeItem node = mapTree.getSelectedNode();
                    if (node.getMapNode() instanceof Project) {
                        projectView.getTabbedPane().refresh();
                        projectView.getProjectViewTop().setProject((Project)node.getMapNode());
                        projectView.getTabbedPane().setProject((Project)node.getMapNode());
                        for (MapNode child : ((Project) node.getMapNode()).getChildren()) {
                            MapTab mapTab = new MapTab((MindMap) child);
                            projectView.getTabbedPane().addTab(child.getName(), mapTab);
                        }
                    }
                }
            }
        });
    }

}
