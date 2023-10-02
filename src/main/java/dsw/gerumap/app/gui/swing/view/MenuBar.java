package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.ActionType;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

    public MenuBar() {

        JMenu mFile = new JMenu("File");
        JMenu mEdit = new JMenu("Edit");
        JMenu mHelp = new JMenu("Help");
        mFile.add(MainFrame.getInstance().getActionManager().getAction(ActionType.OPEN));
        mFile.add(MainFrame.getInstance().getActionManager().getAction(ActionType.SAVE_AS));
        mFile.add(MainFrame.getInstance().getActionManager().getAction(ActionType.SAVE));
        mFile.add(MainFrame.getInstance().getActionManager().getAction(ActionType.NEW));
        mFile.setMnemonic(KeyEvent.VK_F);
        mFile.add(MainFrame.getInstance().getActionManager().getAction(ActionType.DELETE));
        mFile.setMnemonic(KeyEvent.VK_DELETE);
        mFile.add(MainFrame.getInstance().getActionManager().getAction(ActionType.ADD_AUTHOR));
        mFile.add(MainFrame.getInstance().getActionManager().getAction(ActionType.RENAME));
        mFile.setMnemonic(KeyEvent.VK_I);
        this.add(mFile);
        this.add(mEdit);
        this.add(mHelp);

    }
}
