package dsw.gerumap.app.messagegenerator;

public class Message {

    private final String text;

    private final EventType type;

    public Message(String text, EventType type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public EventType getType() {
        return type;
    }
}
