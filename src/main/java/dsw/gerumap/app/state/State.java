package dsw.gerumap.app.state;

import dsw.gerumap.app.gui.swing.view.MapTab;

public interface State {

    void clickPerform(int x, int y, MapTab mapTab);

    void dragPerform(int x, int y, MapTab mapTab);

    void releasePerform(int x, int y, MapTab mapTab);

}
