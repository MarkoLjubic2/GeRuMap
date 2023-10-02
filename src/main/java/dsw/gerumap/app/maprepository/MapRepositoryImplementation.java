package dsw.gerumap.app.maprepository;

import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;

public class MapRepositoryImplementation implements MapRepository {

    private final ProjectExplorer projectExplorer;

    public MapRepositoryImplementation() {
        this.projectExplorer = new ProjectExplorer("ProjectExplorer", null);
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

}
