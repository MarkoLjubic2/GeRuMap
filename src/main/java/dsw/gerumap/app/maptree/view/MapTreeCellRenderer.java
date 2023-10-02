package dsw.gerumap.app.maptree.view;

import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.maptree.model.MapTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MapTreeCellRenderer extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object cell, boolean selectionHighlight, boolean isExpanded, boolean isLeaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, cell, selectionHighlight, isExpanded, isLeaf, row, hasFocus);

        URL imageURL = null;

        if(((MapTreeItem)cell).getMapNode() instanceof ProjectExplorer)
            imageURL = getClass().getResource("/images/projectExplorer.png");

        else if(((MapTreeItem)cell).getMapNode() instanceof Project)
            imageURL = getClass().getResource("/images/treeProject.png");

        else if (((MapTreeItem)cell).getMapNode() instanceof MindMap)
            imageURL = getClass().getResource("/images/treeMap.png");

        else if (((MapTreeItem)cell).getMapNode() instanceof Element)
            imageURL = getClass().getResource("/images/treeElement.png");

        Icon icon = null;
        if(imageURL != null)
            icon = new ImageIcon(imageURL);
        this.setIcon(icon);

        return this;
    }

    @Override
    public Color getBackgroundNonSelectionColor() {
        return null;
    }

    @Override
    public Color getBackgroundSelectionColor() {
        return new Color(115,140,160);
    }

    @Override
    public Color getBackground() {
        return new Color(40,110,130);
    }

}
