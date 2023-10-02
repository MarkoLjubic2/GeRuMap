package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;

import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractGerumapAction {

    public DeleteNodeAction() {
        super.putValue(NAME, "Delete");
        super.putValue(SMALL_ICON, loadIcon("/images/deleteNodeAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ((MapTab) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).resetSelection();
            MainFrame.getInstance().getProjectView().startDeleteState();
        } catch (Exception ignored) {}
    }
}
