package dsw.gerumap.app.core;

import dsw.gerumap.app.gui.swing.SwingGui;
import dsw.gerumap.app.maprepository.MapRepositoryImplementation;
import dsw.gerumap.app.messagegenerator.ConsoleLog;
import dsw.gerumap.app.messagegenerator.FileLog;
import dsw.gerumap.app.messagegenerator.MessageGenImplementation;
import dsw.gerumap.app.serializer.SerializerImplementation;

public class AppFramework {

    private static AppFramework instance;

    protected Gui gui;
    protected MapRepository mapRepository;
    protected ErrorLogger fileLogger;
    protected ErrorLogger consoleLogger;
    protected MessageGenerator messageGenerator;
    protected Serializer serializer;

    public void run(){
        this.gui.start();
    }

    private AppFramework() {}

    public static AppFramework getAppFramework(){
        if(instance == null) {
            instance = new AppFramework();
        }
        return instance;
    }

    public void init() {
        mapRepository = new MapRepositoryImplementation();
        messageGenerator = new MessageGenImplementation();
        consoleLogger = new ConsoleLog(messageGenerator);
        fileLogger = new FileLog(messageGenerator);
        gui = new SwingGui(messageGenerator);
        serializer = new SerializerImplementation();
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public Gui getGui() {
        return gui;
    }

    public MapRepository getMapRepository() {
        return mapRepository;
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public Serializer getSerializer() {
        return serializer;
    }
}
