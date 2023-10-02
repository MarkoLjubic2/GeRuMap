package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maptree.MapTree;
import dsw.gerumap.app.observer.NameSubscriber;
import dsw.gerumap.app.observer.TreeSubscriber;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class ProjectViewTop extends JPanel implements NameSubscriber, TreeSubscriber {

    private final JLabel lbAuthor = new JLabel();
    private final JLabel lbAuthorValue = new JLabel();
    private final JLabel lbSpacing = new JLabel();
    private final JLabel lbProject = new JLabel();
    private final JLabel lbProjectValue = new JLabel();

    private Project project;
    private final MapTree mapTree;

    public ProjectViewTop() {

        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        this.add(panel);

        panel.add(lbAuthor);
        panel.add(lbAuthorValue);
        panel.add(lbSpacing);
        panel.add(lbProject);
        panel.add(lbProjectValue);

        mapTree = MainFrame.getInstance().getTreeMap();
        mapTree.addSubscriberTree(this);
    }

    public void setProject(Project project) {
        this.project = project;
        project.addSubscriber(this);
        updateRename(project);
    }

    @Override
    public void updateRename(Object o) {
        Project p = (Project)o;
        lbAuthor.setText("Author: ");
        lbProject.setText("Project: ");
        lbAuthorValue.setText(p.getAuthor());
        lbProjectValue.setText(p.getName());
        this.revalidate();
        this.repaint();
    }


    @Override
    public void updateRemovedMap(Object o) {
        if (o instanceof Project && (o) == this.project) {
            lbAuthor.setText("");
            lbProject.setText("");
            lbProjectValue.setText("");
            lbAuthorValue.setText("");
        }
    }

    @Override
    public void updateAddedMap(Object o) {

    }
}

