package dsw.gerumap.app.serializer;

import com.google.gson.*;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.implementation.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SerializerImplementation implements Serializer {

    private Gson gson = new Gson();

    @Override
    public void loadProject(File file) {
        try {
            FileReader fileReader = new FileReader(file);

            RuntimeTypeAdapterFactory<MapNode> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(MapNode.class, "type")
                            .registerSubtype(Project.class, Project.class.getName()).registerSubtype(MindMap.class, MindMap.class.getName())
                            .registerSubtype(Node.class, Node.class.getName()).registerSubtype(Link.class, Link.class.getName());

            gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

            Project project = gson.fromJson(fileReader, Project.class);
            MainFrame.getInstance().getTreeMap().loadProject(project);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveProject(Project node) {
        if (node.getPath() == null || node.getPath().isEmpty()) return;
        try (FileWriter writer = new FileWriter(node.getPath())) {
            gson.toJson(node, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTemplate(File file) {
        try  {
            FileReader fileReader = new FileReader(file);

            RuntimeTypeAdapterFactory<MapNode> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(MapNode.class, "type")
                    .registerSubtype(MindMap.class, MindMap.class.getName()).registerSubtype(Node.class, Node.class.getName()).registerSubtype(Link.class, Link.class.getName());

            gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

            MindMap template = gson.fromJson(fileReader, MindMap.class);
            MainFrame.getInstance().getTreeMap().loadTemplate(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveTemplate(MindMap node, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(node, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
