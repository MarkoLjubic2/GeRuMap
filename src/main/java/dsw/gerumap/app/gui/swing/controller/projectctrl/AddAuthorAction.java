package dsw.gerumap.app.gui.swing.controller.projectctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.AddAuthorView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maptree.model.MapTreeItem;
import dsw.gerumap.app.messagegenerator.EventType;
import dsw.gerumap.app.messagegenerator.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddAuthorAction extends AbstractGerumapAction {

    public AddAuthorAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        super.putValue(NAME, "Add Author");
        super.putValue(SMALL_ICON, loadIcon("/images/addAuthorAction.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem node = MainFrame.getInstance().getTreeMap().getSelectedNode();
        if(node == null) {
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Nothing is selected!", EventType.ERROR));
            return;
        }
        if (node.getMapNode() instanceof Project) {
            AddAuthorView dialog = new AddAuthorView(MainFrame.getInstance(), true, ((Project) node.getMapNode()).getAuthor());
            dialog.setVisible(true);
        }else AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Author can only be added to the project.", EventType.ERROR));
    }
}
