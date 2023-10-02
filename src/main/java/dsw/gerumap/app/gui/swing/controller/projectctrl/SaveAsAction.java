package dsw.gerumap.app.gui.swing.controller.projectctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.messagegenerator.EventType;
import dsw.gerumap.app.messagegenerator.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAsAction extends AbstractGerumapAction {

    public SaveAsAction() {
        super.putValue(NAME, "Save As");
        super.putValue(SMALL_ICON, loadIcon("/images/saveAsAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Save As");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getTreeMap().getSelectedNode() == null) {
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Please select project!", EventType.ERROR));
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        if (!(MainFrame.getInstance().getTreeMap().getSelectedNode().getMapNode() instanceof Project)) {
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Please select project!", EventType.ERROR));
            return;
        }

        Project project = (Project) MainFrame.getInstance().getTreeMap().getSelectedNode().getMapNode();
        File projectFile = null;

        if (fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            projectFile = fileChooser.getSelectedFile();
            project.setPath(projectFile.getPath());
        }
        if (projectFile == null || projectFile.getPath().isEmpty()) return;
        AppFramework.getAppFramework().getSerializer().saveProject(project);
        project.setChanged(false);
    }

}
