package dsw.gerumap.app.gui.swing.controller.projectctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenAction extends AbstractGerumapAction {

    public OpenAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        super.putValue(NAME, "Open");
        super.putValue(SMALL_ICON, loadIcon("/images/openAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Open");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                AppFramework.getAppFramework().getSerializer().loadProject(file);

            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }

}
