package dsw.gerumap.app.messagegenerator;

import dsw.gerumap.app.core.ErrorLogger;
import dsw.gerumap.app.core.MessageGenerator;

public class ConsoleLog implements ErrorLogger {

    private final MessageGenerator messageGenerator;

    public ConsoleLog(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
        messageGenerator.addSubscriber(this);
    }

    @Override
    public void log(Message message) {
        System.out.println(messageGenerator.generate(message));
    }

    @Override
    public void update(Object o) {
        log((Message)o);
    }
}
