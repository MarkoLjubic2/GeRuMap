package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.ActionType;

import javax.swing.*;

public class ToolBar extends JToolBar {

    public ToolBar() {

        super(SwingConstants.HORIZONTAL);
        super.setFloatable(false);
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.OPEN));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.SAVE_AS));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.SAVE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.NEW));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.DELETE));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.ADD_AUTHOR));
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getAction(ActionType.RENAME));

    }
}
