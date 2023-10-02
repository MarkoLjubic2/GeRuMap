package dsw.gerumap.app.gui.swing;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.gui.swing.command.CommandManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.messagegenerator.Message;
import dsw.gerumap.app.core.MessageGenerator;

import javax.swing.*;

public class SwingGui implements Gui {

    private final CommandManager commandManager;

    public SwingGui(MessageGenerator messageGen) {
        commandManager = new CommandManager();
        messageGen.addSubscriber(this);
    }

    @Override
    public void start() {
        MainFrame.getInstance().setVisible(true);
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void update(Object o) {
        Message m = (Message)o;
        JOptionPane.showMessageDialog(MainFrame.getInstance(), AppFramework.getAppFramework().getMessageGenerator().generate(m));
    }


}
