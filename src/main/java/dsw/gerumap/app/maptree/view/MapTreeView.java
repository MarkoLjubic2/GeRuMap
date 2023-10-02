package dsw.gerumap.app.maptree.view;

import dsw.gerumap.app.maptree.controller.MapTreeCellEditor;
import dsw.gerumap.app.maptree.controller.MapTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeView extends JTree {

    public MapTreeView(DefaultTreeModel dftTreeModel) {
        super.setModel(dftTreeModel);
        MapTreeCellRenderer mapTreeCellRenderer = new MapTreeCellRenderer();
        super.addTreeSelectionListener(new MapTreeSelectionListener());
        super.setCellEditor(new MapTreeCellEditor(this, mapTreeCellRenderer));
        super.setCellRenderer(mapTreeCellRenderer);

        super.setEditable(true);
    }
}
