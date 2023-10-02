package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.state.StateManager;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel {

    private MapToolBar mapToolBar;
    private ProjectViewTop projectViewTop;
    private CloseableTabbedPane tabbedPane;

    private StateManager stateManager;

    public ProjectView() {
        init();
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(mapToolBar, BorderLayout.NORTH);
        topPanel.add(projectViewTop);
        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(tabbedPane);
    }

    public void init() {
        this.mapToolBar = new MapToolBar();
        this.projectViewTop = new ProjectViewTop();
        this.tabbedPane = new CloseableTabbedPane();
        this.stateManager = new StateManager();
    }

    public void clickPerform(int x, int y, MapTab mapTab) {
        this.stateManager.getCurrentState().clickPerform(x, y, mapTab);
    }

    public void releasePerform(int x, int y, MapTab mapTab) {
        this.stateManager.getCurrentState().releasePerform(x, y, mapTab);
    }

    public void dragPerform(int x, int y, MapTab mapTab) {
        this.stateManager.getCurrentState().dragPerform(x, y, mapTab);
    }

    public ProjectViewTop getProjectViewTop() {
        return projectViewTop;
    }

    public CloseableTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void startNodeState() {
        this.stateManager.setNodeState();
    }

    public void startLinkState() {
        this.stateManager.setLinkState();
    }

    public void startDeleteState() {
        this.stateManager.setDeleteState();
    }

    public void startMoveState() {
        this.stateManager.setMoveState();
    }

    public void startSelectionState() {
        this.stateManager.setSelectionState();
    }

}
