package dsw.gerumap.app.gui.swing.controller.projectctrl;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportAction extends AbstractGerumapAction {

    public ExportAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
        super.putValue(NAME, "Export");
        super.putValue(SMALL_ICON, loadIcon("/images/exportAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Export");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTab tab = (MapTab) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if (tab != null) {
            JFileChooser fileChooser = new JFileChooser();
            File projectFile;

            JPanel panel = tab.getPanel();
            BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
            panel.paint(image.getGraphics());
            if (fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = fileChooser.getSelectedFile();
            }
            else return;

            try {
                ImageIO.write(image, "PNG", new File(projectFile.getPath()+".png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
