package dsw.gerumap.app.gui.swing.controller.projectctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.messagegenerator.EventType;
import dsw.gerumap.app.messagegenerator.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveAction extends AbstractGerumapAction {

    public SaveAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        super.putValue(NAME, "Save");
        super.putValue(SMALL_ICON, loadIcon("/images/saveAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getTreeMap().getSelectedNode() == null) {
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Please select project!", EventType.ERROR));
            return;
        }
        if (!(MainFrame.getInstance().getTreeMap().getSelectedNode().getMapNode() instanceof Project)) {
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Please select project!", EventType.ERROR));
            return;
        }

        Project project = (Project) MainFrame.getInstance().getTreeMap().getSelectedNode().getMapNode();

        if (!project.isChanged()) return;

        if (project.getPath() != null && !project.getPath().isEmpty()) {
            AppFramework.getAppFramework().getSerializer().saveProject(project);
            project.setChanged(false);
        }
    }

}
