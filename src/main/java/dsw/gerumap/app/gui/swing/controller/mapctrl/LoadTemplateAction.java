package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadTemplateAction extends AbstractGerumapAction {

    public LoadTemplateAction() {
        super.putValue(SMALL_ICON, loadIcon("/images/loadTemplateAction.png"));
        super.putValue(NAME, "Load Template");
        super.putValue(SHORT_DESCRIPTION, "Load Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                AppFramework.getAppFramework().getSerializer().loadTemplate(file);

            } catch (Exception ignored) {}
        }
    }

}
