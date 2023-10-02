package dsw.gerumap.app.gui.swing.command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CloseListener implements MouseListener
{
    private final Component tab;

    public CloseListener(Component tab){
        this.tab=tab;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton clickedButton = (JButton) e.getSource();
            JTabbedPane tabbedPane = (JTabbedPane) clickedButton.getParent().getParent().getParent();
            tabbedPane.remove(tab);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
