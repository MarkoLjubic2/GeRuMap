package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.EditNodeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.messagegenerator.EventType;
import dsw.gerumap.app.messagegenerator.Message;

import java.awt.event.ActionEvent;

public class EditNodeAction extends AbstractGerumapAction {

    public EditNodeAction() {
        super.putValue(NAME, "Edit Node");
        super.putValue(SMALL_ICON, loadIcon("/images/editNodeAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Edit Node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((MapTab)MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).getSelectedNodes().isEmpty()) {
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Please select elements to modify.", EventType.NOTIFICATION));
            return;
        }
        EditNodeView edit = new EditNodeView((MapTab)MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent());
        edit.setVisible(true);
    }
}
