package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.abstraction.MapNodeComposite;
import dsw.gerumap.app.messagegenerator.EventType;
import dsw.gerumap.app.messagegenerator.Message;
import dsw.gerumap.app.observer.NamePublisher;
import dsw.gerumap.app.observer.NameSubscriber;

import java.util.ArrayList;
import java.util.List;

public class Project extends MapNodeComposite implements NamePublisher {

    private String author;
    private String path;

    private transient boolean changed = true;

    transient List<NameSubscriber> subscribers;

    @Override
    public void setName(String text) {
        if(text.isEmpty()){
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Project name cannot be empty!", EventType.ERROR));
            return;
        }
        super.setName(text);
        setChanged(true);
        notifySubscriber(this);
    }

    public Project(String name, MapNode parent, String author, String path) {
        super(name, parent);
        this.author = author;
        this.path = path;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author.isEmpty()){
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Author name cannot be empty!", EventType.ERROR));
            return;
        }
        this.author = author;
        setChanged(true);
        notifySubscriber(this);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void addChild(MapNode child) {
        if (child instanceof MindMap) {
            MindMap mindMap = (MindMap)child;
            if (!this.getChildren().contains(mindMap)) {
                this.getChildren().add(mindMap);
                setChanged(true);
            }
        }
    }

    @Override
    public void removeChild(MapNode child) {
        if (child instanceof MindMap) {
            MindMap map = (MindMap) child;
            this.getChildren().remove(map);
            setChanged(true);
        }
    }

    @Override
    public void addSubscriber(NameSubscriber s) {
        if(s == null)
            return;
        if(this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(s))
            return;
        this.subscribers.add(s);
    }

    @Override
    public void removeSubscriber(NameSubscriber s) {
        if(s == null ||  this.subscribers == null || !this.subscribers.contains(s))
            return;
        this.subscribers.remove(s);
    }

    @Override
    public void notifySubscriber(Object s) {
        if(s == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(NameSubscriber subscriber : subscribers){
            subscriber.updateRename(s);
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
