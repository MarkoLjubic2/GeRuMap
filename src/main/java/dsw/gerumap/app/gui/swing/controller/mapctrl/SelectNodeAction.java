package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class SelectNodeAction extends AbstractGerumapAction {

    public SelectNodeAction() {
        super.putValue(NAME, "Select");
        super.putValue(SMALL_ICON, loadIcon("/images/selectNodeAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Select");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startSelectionState();
    }
}
