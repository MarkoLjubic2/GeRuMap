package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.ActionType;

import javax.swing.*;
import java.awt.*;

public class MapToolBar extends JToolBar {

    public MapToolBar() {

        super(SwingConstants.HORIZONTAL);
        super.setFloatable(false);
        this.setBackground(new Color(45,70,100));
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.ADD_NODE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.DELETE_NODE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.LINK_NODE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.ROOT_NODE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.SELECT_NODE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.MOVE_NODE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.ZOOM_IN));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.ZOOM_OUT));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.EDIT_NODE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.UNDO));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.REDO));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.LOAD_TEMPLATE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.SAVE_TEMPLATE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.EXPORT));
    }

}
