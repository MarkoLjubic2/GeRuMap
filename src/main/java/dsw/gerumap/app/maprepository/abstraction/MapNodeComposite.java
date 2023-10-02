package dsw.gerumap.app.maprepository.abstraction;

import java.util.ArrayList;
import java.util.List;

public abstract class MapNodeComposite extends MapNode {

    private final List<MapNode> children = new ArrayList<>();

    public MapNodeComposite(String name, MapNode parent) {
        super(name, parent);
    }

    public List<MapNode> getChildren() {
        return children;
    }

    public abstract void addChild(MapNode child);

    public abstract void removeChild(MapNode child);

}
