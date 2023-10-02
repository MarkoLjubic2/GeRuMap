package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.observer.StateSubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Node extends Element{

    private String text;
    private Point position;
    private double[] size;

    transient List<StateSubscriber> stateSubscribers = new ArrayList<>();

    public Node(String name, MapNode parent, int[] elementColor, float stroke, String text, Point position, double[] size) {
        super(name, parent, elementColor, stroke);
        this.text = text;
        this.position = position;
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        ((Project) getParent().getParent()).setChanged(true);
        notifyStateSubscribers(null);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
        ((Project) getParent().getParent()).setChanged(true);
        notifyStateSubscribers(null);
    }

    public double[] getSize() {
        return size;
    }

    public void setSize(double[] size) {
        this.size = size;
        ((Project) getParent().getParent()).setChanged(true);
    }

    @Override
    public void setCurrentColor(int[] currentColor) {
        super.setCurrentColor(currentColor);
        notifyStateSubscribers(null);
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

        for(StateSubscriber listener : stateSubscribers){
            listener.update(o);
        }
    }
}
