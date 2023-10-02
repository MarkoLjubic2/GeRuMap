package dsw.gerumap.app.messagegenerator;

public interface MessagePublisher {

    void addSubscriber(MessageSubscriber messageSubscriber);

    void removeSubscriber(MessageSubscriber messageSubscriber);

    void notify(Object o);

}
