package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.abstraction.MapNodeComposite;
import dsw.gerumap.app.messagegenerator.EventType;
import dsw.gerumap.app.messagegenerator.Message;

public class ProjectExplorer extends MapNodeComposite{


    public ProjectExplorer(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(MapNode child) {
       if (child instanceof Project) {
           Project project = (Project)child;
           if (!this.getChildren().contains(project))
               this.getChildren().add(project);
       }
    }

    @Override
    public void setName(String text) {
        if(!text.equals("ProjectExplorer")) {
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Project Explorer's name cannot be changed!", EventType.ERROR));
            return;
        }
        super.setName(text);
    }

    @Override
    public void removeChild(MapNode child) {

    }
}
