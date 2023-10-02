package dsw.gerumap.app.state;

import dsw.gerumap.app.state.states.*;

public class StateManager {

    private State currentState;

    private NodeState nodeState;
    private LinkState linkState;
    private DeleteState deleteState;
    private SelectionState selectionState;
    private MoveState moveState;

    public StateManager() {
        init();
    }

    public void init() {
        this.nodeState = new NodeState();
        this.linkState = new LinkState();
        this.deleteState = new DeleteState();
        this.selectionState = new SelectionState();
        this.moveState = new MoveState();
        this.currentState = selectionState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void setNodeState() {
        this.currentState = nodeState;
    }

    public void setLinkState() {
        this.currentState = linkState;
    }

    public void setDeleteState() {
        this.currentState = deleteState;
    }

    public void setSelectionState() {
        this.currentState = selectionState;
    }

    public void setMoveState() {
        this.currentState = moveState;
    }
}
