package dsw.gerumap.app.messagegenerator;

import dsw.gerumap.app.core.ErrorLogger;
import dsw.gerumap.app.core.MessageGenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLog implements ErrorLogger {

    private final MessageGenerator messageGenerator;

    public FileLog(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
        messageGenerator.addSubscriber(this);
    }

    @Override
    public void log(Message message) {
        try {
            Files.write(Paths.get("src/main/resources/log.txt"), (messageGenerator.generate(message)+"\n").getBytes(), StandardOpenOption.APPEND);
        } catch (Exception ignored) {}
    }

    @Override
    public void update(Object o) {
        log((Message)o);
    }
}
