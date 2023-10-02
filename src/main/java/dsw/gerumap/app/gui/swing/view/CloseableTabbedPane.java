package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.command.CloseListener;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.observer.NameSubscriber;
import dsw.gerumap.app.observer.TreeSubscriber;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class CloseableTabbedPane extends JTabbedPane implements NameSubscriber, TreeSubscriber {

    private Project project;

    public CloseableTabbedPane() {
        super();
    }

    public void refresh() {
        this.removeAll();
    }

    @Override
    public void addTab(String title, Component component) {
        addTab(title, null, component);
        int count = this.getTabCount() - 1;
        setTabComponentAt(count, new CloseButtonTab(component, title));
    }

    @Override
    public void updateRename(Object o) {
        MindMap m = (MindMap)o;
        List<Component> components = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        Component save = this.getSelectedComponent();

        for (Component component : this.getComponents()) {

            if(component instanceof MapTab) {
                components.add(component);
                if(m == ((MapTab) component).getMindMap())
                    ((MapTab) component).setMindMap(m);

                titles.add(((MapTab) component).getMindMap().getName());
            }
        }
        this.removeAll();
        for (int i = 0; i < components.size(); i++) {
            this.addTab(titles.get(i), components.get(i));
        }
        this.setSelectedComponent(save);
    }

    @Override
    public void updateRemovedMap(Object o) {
        if(o instanceof Project && o == this.project) {
            this.removeAll();
            return;
        }
        if (!(o instanceof MindMap)) return;
        MindMap m = (MindMap)o;
        for (Component component : this.getComponents()) {

            if(component instanceof MapTab && m == ((MapTab) component).getMindMap()) {
                this.remove(component);
            }
        }
    }

    @Override
    public void updateAddedMap(Object o) {
        if(o instanceof MindMap && ((MindMap) o).getParent().equals(this.project)){
            this.addTab(((MindMap)o).getName(), new MapTab((MindMap)o));
        }
    }

    public void setProject(Project project) {
        this.project = project;
        List<MapNode> maps = project.getChildren();
        for (MapNode map : maps)
            ((MindMap) map).addSubscriber(this);
        MainFrame.getInstance().getTreeMap().addSubscriberTree(this);
    }

    private static class CloseButtonTab extends JPanel {

        public CloseButtonTab(final Component tab, String title) {
            setOpaque(false);
            FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
            setLayout(flowLayout);
            JLabel lbTitle = new JLabel(title);
            add(lbTitle);
            JButton button = new JButton(MetalIconFactory.getInternalFrameCloseIcon(13));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.addMouseListener(new CloseListener(tab));
            add(button);
        }
    }

}
