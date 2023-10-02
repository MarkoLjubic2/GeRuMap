package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;

import java.awt.event.ActionEvent;

public class LinkNodeAction extends AbstractGerumapAction {

    public LinkNodeAction() {
        super.putValue(NAME, "Link");
        super.putValue(SMALL_ICON, loadIcon("/images/linkNodeAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Link");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ((MapTab) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).resetSelection();
            MainFrame.getInstance().getProjectView().startLinkState();
        }catch (Exception ignored){}
    }
}
