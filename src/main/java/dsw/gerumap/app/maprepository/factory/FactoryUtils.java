package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;

public class FactoryUtils {

    public static FactoryUtils instance;
    public ProjectFactory projectFactory;
    public MapFactory mapFactory;
    private FactoryUtils() {}

    private void init() {
        this.projectFactory = new ProjectFactory();
        this.mapFactory = new MapFactory();
    }

    public MapNode createNode(MapNode parent) {
        if (parent instanceof ProjectExplorer)
            return projectFactory.createNode();
        if (parent instanceof Project)
            return mapFactory.createNode();
        return null;
    }

    public static FactoryUtils getInstance() {

        if (instance == null) {
            instance = new FactoryUtils();
            instance.init();
        }
        return instance;

    }

}
