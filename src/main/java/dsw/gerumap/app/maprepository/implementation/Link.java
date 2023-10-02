package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.observer.StateSubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Link extends Element{

    private Node start;
    private Node end;
    private Point startPoint;
    private Point endPoint;

    transient List<StateSubscriber> stateSubscribers = new ArrayList<>();

    public Link(String name, MapNode parent, int[] elementColor, float stroke, Node start, Node end, Point startPoint, Point endPoint) {
        super(name, parent, elementColor, stroke);
        this.start = start;
        this.end = end;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
        ((Project) getParent().getParent()).setChanged(true);
        notifyStateSubscribers(null);
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        ((Project) getParent().getParent()).setChanged(true);
        notifyStateSubscribers(null);
    }

    @Override
    public void setCurrentColor(int[] currentColor) {
        super.setCurrentColor(currentColor);
    }

    @Override
    public void addSubscriber(StateSubscriber s) {
        if(s == null) return;
        if(this.stateSubscribers == null) this.stateSubscribers = new ArrayList<>();
        if(this.stateSubscribers.contains(s)) return;
        this.stateSubscribers.add(s);
    }

    @Override
    public void removeSubscriber(StateSubscriber s) {
        if(s == null ||  this.stateSubscribers == null || !this.stateSubscribers.contains(s)) return;
        this.stateSubscribers.remove(s);
    }

    @Override
    public void notifyStateSubscribers(Object o) {
        if(this.stateSubscribers == null || this.stateSubscribers.isEmpty()) return;

        stateSubscribers.forEach(listener->listener.update(o));
    }
}
