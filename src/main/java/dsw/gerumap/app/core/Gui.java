package dsw.gerumap.app.core;

import dsw.gerumap.app.gui.swing.command.CommandManager;
import dsw.gerumap.app.messagegenerator.MessageSubscriber;

public interface Gui extends MessageSubscriber {

    void start();

    CommandManager getCommandManager();

}
