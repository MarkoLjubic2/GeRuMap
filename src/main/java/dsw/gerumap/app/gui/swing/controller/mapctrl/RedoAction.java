package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractGerumapAction {

    public RedoAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        super.putValue(NAME, "Redo");
        super.putValue(SMALL_ICON, loadIcon("/images/redoAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Redo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AppFramework.getAppFramework().getGui().getCommandManager().doCommand();
    }

}
