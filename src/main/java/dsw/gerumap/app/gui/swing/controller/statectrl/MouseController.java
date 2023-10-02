package dsw.gerumap.app.gui.swing.controller.statectrl;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapTab;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.function.BiConsumer;

public class MouseController implements MouseListener, MouseMotionListener {

    private final MapTab mapTab;

    public MouseController(MapTab mapTab) {
        this.mapTab = mapTab;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    private void performAction(MouseEvent e, BiConsumer<Integer, Integer> action) {
        int x = (int) (e.getX() / mapTab.getScalingFactor() + (mapTab.getDx() / mapTab.getScalingFactor()));
        int y = (int) (e.getY() / mapTab.getScalingFactor() + (mapTab.getDy() / mapTab.getScalingFactor()));
        action.accept(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            performAction(e, (x, y) -> MainFrame.getInstance().getProjectView().clickPerform(x, y, mapTab));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        performAction(e, (x, y) -> MainFrame.getInstance().getProjectView().releasePerform(x, y, mapTab));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        performAction(e, (x, y) -> MainFrame.getInstance().getProjectView().dragPerform(x, y, mapTab));
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
