package dsw.gerumap.app.gui.swing.controller.projectctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.maptree.model.MapTreeItem;
import dsw.gerumap.app.messagegenerator.EventType;
import dsw.gerumap.app.messagegenerator.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractGerumapAction {

    public DeleteAction() {
        super.putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke("DELETE"));
        super.putValue(NAME, "Delete");
        super.putValue(SMALL_ICON, loadIcon("/images/deleteAction.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            MapTreeItem node = MainFrame.getInstance().getTreeMap().getSelectedNode();
            if(node.getMapNode() instanceof ProjectExplorer)
                AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Project Explorer cannot be deleted!", EventType.ERROR));

            else MainFrame.getInstance().getTreeMap().removeChild(node);
            }
        catch(Exception ignored) {}

    }
}
