package dsw.gerumap.app.gui.swing.controller.mapctrl;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.maprepository.implementation.Node;
import dsw.gerumap.app.math.FixUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RootNodeAction extends AbstractGerumapAction {

    public RootNodeAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        super.putValue(NAME, "Root Node");
        super.putValue(SMALL_ICON, loadIcon("/images/rootNodeAction.png"));
        super.putValue(SHORT_DESCRIPTION, "Root Node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTab tab = (MapTab) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
        if (tab != null) {
            if (!tab.isHasRoot() && tab.getSelectedNodes().size() == 1 && tab.getSelectedNodes().get(0) instanceof Node) {
                int width = tab.getPanel().getWidth()/2;
                int height = tab.getPanel().getHeight()/2;
                Node root = (Node)tab.getSelectedNodes().get(0);
                root.setPosition(new Point((int)(width-root.getSize()[0]), (int)(height-root.getSize()[1])));
                FixUtils.getInstance().fixUpEnd(tab, root);
                FixUtils.getInstance().fixUpStart(tab, root);
                root.setName("Root Node");
                tab.setHasRoot(true);
            }
        }
    }

}
