package dsw.gerumap.app.gui.swing.controller;

import javax.swing.*;
import java.net.URL;

public abstract class AbstractGerumapAction extends AbstractAction {

    public Icon loadIcon(String file) {

        URL imageURL = getClass().getResource(file);
        if (imageURL == null) System.err.println("Invalid file name");
        return (imageURL != null) ? (new ImageIcon(imageURL)) : (null);

    }

}
