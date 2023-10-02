package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import java.awt.event.ActionEvent;
import java.util.Random;

public class SaveTemplateAction extends AbstractGerumapAction {

    public SaveTemplateAction() {
        super.putValue(SMALL_ICON, loadIcon("/images/saveTemplateAction.png"));
        super.putValue(NAME, "Save Template");
        super.putValue(SHORT_DESCRIPTION, "Save Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(MainFrame.getInstance().getTreeMap().getSelectedNode().getMapNode() instanceof MindMap)) return;

        Random rand = new Random();
        MindMap template = (MindMap) MainFrame.getInstance().getTreeMap().getSelectedNode().getMapNode();
        String path = "src/main/resources/templates/template";

        AppFramework.getAppFramework().getSerializer().saveTemplate(template, path+rand.nextInt(1000));
    }

}
