package dsw.gerumap.app.core;

import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;

import java.io.File;

public interface Serializer {

    void loadProject(File file);
    void saveProject(Project node);
    void loadTemplate(File file);
    void saveTemplate(MindMap node, String path);

}
