package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.ActionType;
import dsw.gerumap.app.gui.swing.controller.mapctrl.*;
import dsw.gerumap.app.gui.swing.controller.projectctrl.*;

import java.util.EnumMap;
import java.util.Map;

public class ActionManager {

    private final Map<ActionType, AbstractGerumapAction> actions = new EnumMap<>(ActionType.class);

    public ActionManager() {
        init();
    }

    private void init() {
        actions.put(ActionType.EXIT, new ExitAction());
        actions.put(ActionType.NEW, new NewAction());
        actions.put(ActionType.DELETE, new DeleteAction());
        actions.put(ActionType.ADD_AUTHOR, new AddAuthorAction());
        actions.put(ActionType.RENAME, new RenameAction());
        actions.put(ActionType.ADD_NODE, new AddNodeAction());
        actions.put(ActionType.LINK_NODE, new LinkNodeAction());
        actions.put(ActionType.DELETE_NODE, new DeleteNodeAction());
        actions.put(ActionType.MOVE_NODE, new MoveNodeAction());
        actions.put(ActionType.SELECT_NODE, new SelectNodeAction());
        actions.put(ActionType.EDIT_NODE, new EditNodeAction());
        actions.put(ActionType.ZOOM_IN, new ZoomInAction());
        actions.put(ActionType.ZOOM_OUT, new ZoomOutAction());
        actions.put(ActionType.UNDO, new UndoAction());
        actions.put(ActionType.REDO, new RedoAction());
        actions.put(ActionType.OPEN, new OpenAction());
        actions.put(ActionType.SAVE_AS, new SaveAsAction());
        actions.put(ActionType.SAVE, new SaveAction());
        actions.put(ActionType.EXPORT, new ExportAction());
        actions.put(ActionType.ROOT_NODE, new RootNodeAction());
        actions.put(ActionType.SAVE_TEMPLATE, new SaveTemplateAction());
        actions.put(ActionType.LOAD_TEMPLATE, new LoadTemplateAction());
    }

    public AbstractGerumapAction getAction(ActionType actionType) {
        return actions.get(actionType);
    }

}
