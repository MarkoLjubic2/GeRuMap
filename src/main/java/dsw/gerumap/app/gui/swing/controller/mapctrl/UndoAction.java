package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractGerumapAction {

    public UndoAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
        super.putValue(NAME, "Undo");
        super.putValue(SMALL_ICON, loadIcon("/images/undoAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AppFramework.getAppFramework().getGui().getCommandManager().undoCommand();
    }

}
