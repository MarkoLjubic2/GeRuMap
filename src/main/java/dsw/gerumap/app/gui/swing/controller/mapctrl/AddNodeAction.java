package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;

import java.awt.event.ActionEvent;

public class AddNodeAction extends AbstractGerumapAction {

    public AddNodeAction() {
        super.putValue(NAME, "Add");
        super.putValue(SMALL_ICON, loadIcon("/images/addNodeAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Add");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            ((MapTab)MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).resetSelection();
            MainFrame.getInstance().getProjectView().startNodeState();
        } catch (Exception ignored){}
    }
}
