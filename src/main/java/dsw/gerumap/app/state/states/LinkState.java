package dsw.gerumap.app.state.states;

import dsw.gerumap.app.core.AppFramework;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.command.commands.AddNodeCommand;
import dsw.gerumap.app.gui.swing.view.MapTab;
import dsw.gerumap.app.gui.swing.view.painter.ElementPainter;
import dsw.gerumap.app.gui.swing.view.painter.LinkPainter;
import dsw.gerumap.app.maprepository.implementation.Link;
import dsw.gerumap.app.maprepository.implementation.Node;
import dsw.gerumap.app.math.FixUtils;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.util.*;
import java.util.List;

public class LinkState implements State {

    @Override
    public void clickPerform(int x, int y, MapTab mapTab) {
        for (ElementPainter painter : mapTab.getPainters()) {
            if (painter.getElement() instanceof Node && painter.elementAt(painter.getElement(), new Point(x, y))) {
                Node e = (Node)painter.getElement();
                Random rand = new Random();
                Link link = new Link("Link"+(""+rand.nextGaussian()).substring(3,8), mapTab.getMindMap(), new int[] {0, 0, 0}, 3, e, null, new Point(x, y), new Point(x, y));
                link.addSubscriber(mapTab);
                mapTab.getPainters().add(new LinkPainter(link));
                return;
            }
        }
    }

    @Override
    public void dragPerform(int x, int y, MapTab mapTab) {
        for (ElementPainter painter : mapTab.getPainters()) {
            if (painter.getElement() instanceof Link) {
                if (((Link) painter.getElement()).getEnd() == null && ((Link) painter.getElement()).getStart() != null) {
                    ((Link) painter.getElement()).setEndPoint(new Point(x, y));
                }
            }
        }
    }

    @Override
    public void releasePerform(int x, int y, MapTab mapTab) {
        boolean isConnected = false;
        Point p = null;
        Link save = null;

        for (ElementPainter painterNode : mapTab.getPainters()) {
            if (painterNode.getElement() instanceof Node && painterNode.elementAt(painterNode.getElement(), new Point(x, y))) {
                for (ElementPainter painterLink : mapTab.getPainters()) {
                    if (painterLink.getElement() instanceof Link && painterNode.elementAt(painterNode.getElement(), ((Link) painterLink.getElement()).getEndPoint())) {
                        ((Link) painterLink.getElement()).setEnd((Node)painterNode.getElement());
                        p = FixUtils.getInstance().findP((Node)painterNode.getElement(), (Link) painterLink.getElement(), false);
                        ((Link) painterLink.getElement()).setEndPoint(p);
                        isConnected = true;
                    }
                }
            }
        }

        if (isConnected && p != null) {
            for (ElementPainter painterLink : mapTab.getPainters()) {
                if (painterLink.getElement() instanceof Link && ((Link) painterLink.getElement()).getEndPoint().getX() == p.getX() && ((Link) painterLink.getElement()).getEndPoint().getY() == p.getY()) {
                    for (ElementPainter painterNode : mapTab.getPainters()) {
                        if (painterNode.getElement() instanceof Node && painterNode.elementAt(painterNode.getElement(), ((Link) painterLink.getElement()).getStartPoint())) {
                            p = FixUtils.getInstance().findP((Node)painterNode.getElement(), (Link) painterLink.getElement(), true);
                            ((Link) painterLink.getElement()).setStartPoint(p);
                            mapTab.getMindMap().addChild(painterLink.getElement());
                            save = (Link) painterLink.getElement();
                        }
                    }
                }
            }
        }

        if (!isConnected) {
            mapTab.getPainters().removeIf(painterLink -> painterLink.getElement() instanceof Link && (((Link) painterLink.getElement()).getStart() == null || ((Link) painterLink.getElement()).getEnd() == null));
            mapTab.getMindMap().notifyStateSubscribers(null);
        }
        else {
            AbstractCommand command = new AddNodeCommand(mapTab.getMindMap(), save, mapTab);
            AppFramework.getAppFramework().getGui().getCommandManager().addCommand(command);
        }
    }

}
