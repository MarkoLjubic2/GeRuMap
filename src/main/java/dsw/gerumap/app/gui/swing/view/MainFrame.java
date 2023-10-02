package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.controller.ActionManager;
import dsw.gerumap.app.gui.swing.controller.TabGenerator;
import dsw.gerumap.app.maptree.MapTreeImplementation;
import dsw.gerumap.app.maptree.MapTree;


import javax.swing.*;
import java.awt.*;


@SuppressWarnings("FieldCanBeLocal")
public class MainFrame extends JFrame {

    private static MainFrame instance;

    private ActionManager actionManager;
    private MapTree treeMap;

    private JMenuBar menuBar;
    private JToolBar toolBar;
    private ProjectView projectView;

    private JScrollPane scrollPaneTree;
    private JSplitPane splitPane;

    private MainFrame() {}

    private void init() {
        actionManager = new ActionManager();
        treeMap = new MapTreeImplementation();

        setWindow();
        setMenuBar();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.init();
        }
        return instance;
    }

    private void setWindow(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setSize(1280,800);
        Image icon = tk.getImage(getClass().getResource("/images/Icon.png"));
        this.setIconImage(icon);
        this.setTitle("GeRuMap");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void setMenuBar(){
        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);
        toolBar = new ToolBar();
        toolBar.setBackground(new Color(140,225,205));
        this.add(toolBar, BorderLayout.NORTH);

        JTree projectExplorer = treeMap.generateTree(AppFramework.getAppFramework().getMapRepository().getProjectExplorer());

        projectView = new ProjectView();

        scrollPaneTree = new JScrollPane(projectExplorer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        projectExplorer.setBackground(new Color(40,110,130));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPaneTree, projectView);
        this.add(splitPane);

        TabGenerator tabGenerator = new TabGenerator();
        tabGenerator.generateTabs(projectExplorer);

        scrollPaneTree.setMinimumSize(new Dimension(200,150));
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public MapTree getTreeMap() {
        return treeMap;
    }

    public ProjectView getProjectView() {
        return projectView;
    }
}