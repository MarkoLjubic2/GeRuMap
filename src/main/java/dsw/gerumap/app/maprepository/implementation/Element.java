package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.observer.StatePublisher;

public abstract class Element extends MapNode implements StatePublisher {

    private int[] currentColor;
    private int[] elementColor;
    private final int[] selectedColor = {15, 30, 90};
    private float stroke;

    public Element(String name, MapNode parent) {
        super(name, parent);
    }

    public Element(String name, MapNode parent, int[] elementColor, float stroke) {
        super(name, parent);
        this.elementColor = elementColor;
        this.currentColor = elementColor;
        this.stroke = stroke;
    }

    public int[] getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(int[] currentColor) {
        this.currentColor = currentColor;
    }

    public float getStroke() {
        return stroke;
    }

    public void setStroke(float stroke) {
        ((Project)(getParent()).getParent()).setChanged(true);
        this.stroke = stroke;
    }

    public int[] getSelectedColor() {
        return selectedColor;
    }

    public int[] getElementColor() {
        return elementColor;
    }

    public void setElementColor(int[] elementColor) {
        ((Project)(getParent()).getParent()).setChanged(true);
        this.elementColor = elementColor;
    }
}
