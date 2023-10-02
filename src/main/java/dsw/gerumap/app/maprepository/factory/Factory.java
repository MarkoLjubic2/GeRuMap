package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.maprepository.abstraction.MapNode;

public abstract class Factory {

    public abstract MapNode createNode();

    public abstract MapNode getMapNode();

}
