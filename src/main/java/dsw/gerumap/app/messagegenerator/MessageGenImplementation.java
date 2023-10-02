package dsw.gerumap.app.messagegenerator;

import dsw.gerumap.app.core.MessageGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGenImplementation implements MessageGenerator {

    List<MessageSubscriber> subscribers;

    @Override
    public String generate(Message message) {
        return "["+message.getType()+"] " + " ["+ LocalDate.now()+" " +(""+ LocalTime.now()).substring(0, 8)+"] "+message.getText();
    }

    @Override
    public void addSubscriber(MessageSubscriber s) {
        if(s == null) return;
        if(this.subscribers == null) this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(s)) return;
        this.subscribers.add(s);
    }

    @Override
    public void removeSubscriber(MessageSubscriber s) {
        if(s == null ||  this.subscribers == null || !this.subscribers.contains(s)) return;
        this.subscribers.remove(s);
    }

    @Override
    public void notify(Object o) {
        if (o == null || this.subscribers == null || this.subscribers.isEmpty()) return;
        for (MessageSubscriber subscriber : subscribers) {
            subscriber.update(o);
        }
    }
}
