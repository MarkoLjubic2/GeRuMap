package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class MoveNodeAction extends AbstractGerumapAction {

    public MoveNodeAction() {
        super.putValue(NAME, "Move");
        super.putValue(SMALL_ICON, loadIcon("/images/moveNodeAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Move");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startMoveState();
    }
}
