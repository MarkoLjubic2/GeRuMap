package dsw.gerumap.app.maprepository.abstraction;

public abstract class MapNode {

    private String name;
    private transient MapNode parent;

    private final String type = getClass().getName();

    public MapNode(String name, MapNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String text) {
        this.name = text;
    }

    public MapNode getParent() {
        return parent;
    }

    public void setParent(MapNode parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }
}
