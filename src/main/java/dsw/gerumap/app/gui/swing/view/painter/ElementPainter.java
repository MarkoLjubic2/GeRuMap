package dsw.gerumap.app.gui.swing.view.painter;

import dsw.gerumap.app.maprepository.implementation.Element;

import java.awt.*;

public abstract class ElementPainter {

    private Element element;

    public ElementPainter(Element element) {
        this.element = element;
    }

    public abstract void draw(Graphics2D g, Element element);

    public abstract boolean elementAt(Element element, Point pos);

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
