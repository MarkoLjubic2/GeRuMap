package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;

import java.awt.event.ActionEvent;

public class ZoomOutAction extends AbstractGerumapAction {

    public ZoomOutAction() {
        super.putValue(NAME, "Zoom Out");
        super.putValue(SMALL_ICON, loadIcon("/images/zoomOutAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Zoom Out");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTab tab = (MapTab) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if (tab != null) {
            double sf = tab.getScalingFactor();
            if (sf/1.2 < 1) sf = 1;
            else sf/=1.2;
            tab.setScalingFactor(sf);
        }
    }
}
