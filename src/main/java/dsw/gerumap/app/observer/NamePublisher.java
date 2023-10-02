package dsw.gerumap.app.observer;

public interface NamePublisher {

    void addSubscriber(NameSubscriber s);

    void removeSubscriber(NameSubscriber s);

    void notifySubscriber(Object o);


}
