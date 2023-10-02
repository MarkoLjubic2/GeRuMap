package dsw.gerumap.app.gui.swing.controller.projectctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.RenameView;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maptree.model.MapTreeItem;
import dsw.gerumap.app.messagegenerator.EventType;
import dsw.gerumap.app.messagegenerator.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameAction extends AbstractGerumapAction {

    public RenameAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        super.putValue(NAME, "Rename");
        super.putValue(SMALL_ICON, loadIcon("/images/renameAction.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem node = MainFrame.getInstance().getTreeMap().getSelectedNode();
        if(node == null) {
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Nothing is selected!", EventType.ERROR));
            return;
        }
        if (node.getMapNode() instanceof Project || node.getMapNode() instanceof MindMap) {
            RenameView renameView = new RenameView(MainFrame.getInstance(), true, node.getMapNode().getName());
            renameView.setVisible(true);
        }else AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Project Explorer's name cannot be changed!", EventType.ERROR));
    }
}
