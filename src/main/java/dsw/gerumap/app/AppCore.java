package dsw.gerumap.app;

import dsw.gerumap.app.core.*;

public class AppCore {

    public static void main(String[] args) {
        AppFramework appCore = AppFramework.getAppFramework();
        appCore.init();
        appCore.run();
    }
}
