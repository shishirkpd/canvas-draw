package com.skp.canvas.actions;

public interface ActionType {
    void execute(ActionsProcessor actionsProcessor);

    Type getType();

    enum Type {
        DRAW_COMMAND,
        APPLICATION_COMMAND
    }
}
