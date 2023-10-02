package dsw.gerumap.app.gui.swing.controller.projectctrl;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maptree.model.MapTreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends AbstractGerumapAction {

    public NewAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        super.putValue(NAME, "New");
        super.putValue(SMALL_ICON, loadIcon("/images/newAction.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem node = MainFrame.getInstance().getTreeMap().getSelectedNode();
        MainFrame.getInstance().getTreeMap().addChild(node);
    }
}
