package dsw.gerumap.app.observer;

public interface TreePublisher {

    void addSubscriberTree(TreeSubscriber s);

    void removeSubscriberTree(TreeSubscriber s);

    void notifyRemove(Object o);

    void notifyAdd(Object o);
}
