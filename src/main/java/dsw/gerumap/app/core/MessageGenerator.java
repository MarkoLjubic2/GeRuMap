package dsw.gerumap.app.core;

import dsw.gerumap.app.messagegenerator.Message;
import dsw.gerumap.app.messagegenerator.MessagePublisher;

public interface MessageGenerator extends MessagePublisher {

    String generate(Message message);

}
