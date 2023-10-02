package dsw.gerumap.app.core;

import dsw.gerumap.app.messagegenerator.Message;
import dsw.gerumap.app.messagegenerator.MessageSubscriber;

public interface ErrorLogger extends MessageSubscriber {

    void log(Message message);

}
