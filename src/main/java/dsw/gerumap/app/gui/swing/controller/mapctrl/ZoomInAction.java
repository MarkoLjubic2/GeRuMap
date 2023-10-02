package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;

import java.awt.event.ActionEvent;

public class ZoomInAction extends AbstractGerumapAction {

    public ZoomInAction() {
        super.putValue(NAME, "Zoom In");
        super.putValue(SMALL_ICON, loadIcon("/images/zoomInAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Zoom In");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTab tab = (MapTab) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if (tab != null) {
            double sf = tab.getScalingFactor();
            if (sf*1.2 > 4) sf = 4;
            else sf*=1.2;
            tab.setScalingFactor(sf);
        }
    }
}
