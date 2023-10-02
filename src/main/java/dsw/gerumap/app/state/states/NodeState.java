package dsw.gerumap.app.state.states;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.command.commands.AddNodeCommand;
import dsw.gerumap.app.gui.swing.view.EditNodeView;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.gui.swing.view.painter.NodePainter;
import dsw.gerumap.app.maprepository.abstraction.MapNode;
import dsw.gerumap.app.maprepository.implementation.Node;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class NodeState implements State {

    @Override
    public void clickPerform(int x, int y, MapTab mapTab) {
        MapNode parent = mapTab.getMindMap();
        Random rand = new Random();
        Node node = new Node("Node"+(""+rand.nextGaussian()).substring(3,8), parent, new int[] {15, 30, 90}, 2, "", new Point(x, y), new double[] {150, 80});
        node.addSubscriber(mapTab);
        mapTab.getMindMap().addChild(node);
        mapTab.getPainters().add(new NodePainter(node, new Ellipse2D.Double(x, y, node.getSize()[0], node.getSize()[1])));
        AbstractCommand command = new AddNodeCommand(mapTab.getMindMap(), node, mapTab);
        AppFramework.getAppFramework().getGui().getCommandManager().addCommand(command);
        mapTab.getSelectedNodes().add(node);
        EditNodeView editNodeView = new EditNodeView(mapTab);
        editNodeView.setVisible(true);
    }

    @Override
    public void dragPerform(int x, int y, MapTab mapTab) {

    }

    @Override
    public void releasePerform(int x, int y, MapTab mapTab) {

    }
}
