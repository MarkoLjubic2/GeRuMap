package dsw.gerumap.app.observer;

public interface StatePublisher {

    void addSubscriber(StateSubscriber s);

    void removeSubscriber(StateSubscriber s);

    void notifyStateSubscribers(Object o);

}
