package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.abstraction.MapNodeComposite;
import dsw.gerumap.app.messagegenerator.EventType;
import dsw.gerumap.app.messagegenerator.Message;
import dsw.gerumap.app.observer.NamePublisher;
import dsw.gerumap.app.observer.NameSubscriber;
import dsw.gerumap.app.observer.StatePublisher;
import dsw.gerumap.app.observer.StateSubscriber;

import java.util.ArrayList;
import java.util.List;

public class MindMap extends MapNodeComposite implements NamePublisher, StatePublisher {

    transient List<NameSubscriber> nameSubscribers;
    transient List<StateSubscriber> stateSubscribers;

    public MindMap(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void setName(String text) {
        if(text.isEmpty()){
            AppFramework.getAppFramework().getMessageGenerator().notify(new Message("Map name cannot be empty!", EventType.ERROR));
            return;
        }
        super.setName(text);
        ((Project)getParent()).setChanged(true);
        notifySubscriber(this);
    }

    @Override
    public void addChild(MapNode child) {
        if (child instanceof Element) {
            Element mapElement = (Element) child;
            if (!this.getChildren().contains(mapElement)){
                this.getChildren().add(mapElement);
                ((Project)getParent()).setChanged(true);
            }
            notifyStateSubscribers(null);
        }
    }

    @Override
    public void removeChild(MapNode child) {
        if (child instanceof Element) {
            Element mapElement = (Element) child;
            this.getChildren().remove(mapElement);
            ((Project)getParent()).setChanged(true);
            notifyStateSubscribers(null);
        }
    }

    @Override
    public void addSubscriber(NameSubscriber s) {
        if(s == null)
            return;
        if(this.nameSubscribers == null)
            this.nameSubscribers = new ArrayList<>();
        if(this.nameSubscribers.contains(s))
            return;
        this.nameSubscribers.add(s);
    }

    @Override
    public void removeSubscriber(NameSubscriber s) {
        if(s == null ||  this.nameSubscribers == null || !this.nameSubscribers.contains(s))
            return;
        this.nameSubscribers.remove(s);
    }

    @Override
    public void notifySubscriber(Object o) {
        if(o == null || this.nameSubscribers == null || this.nameSubscribers.isEmpty())
            return;

        for(NameSubscriber listener : nameSubscribers){
            listener.updateRename(o);
        }
    }

    @Override
    public void addSubscriber(StateSubscriber s) {
        if(s == null)
            return;
        if(this.stateSubscribers == null)
            this.stateSubscribers = new ArrayList<>();
        if(this.stateSubscribers.contains(s))
            return;
        this.stateSubscribers.add(s);
    }

    @Override
    public void removeSubscriber(StateSubscriber s) {
        if(s == null ||  this.stateSubscribers == null || !this.stateSubscribers.contains(s))
            return;
        this.stateSubscribers.remove(s);
    }

    @Override
    public void notifyStateSubscribers(Object o) {
        if(this.stateSubscribers == null || this.stateSubscribers.isEmpty())
            return;

        for(StateSubscriber listener : stateSubscribers){
            listener.update(o);
        }
    }

}
