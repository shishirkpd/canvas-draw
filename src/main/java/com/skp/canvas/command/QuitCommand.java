package com.skp.canvas.command;

import com.skp.canvas.actions.ActionType;
import com.skp.canvas.actions.ActionsProcessor;

public class QuitCommand implements ActionType {

    @Override
    public void execute(ActionsProcessor actionsProcessor) {
        actionsProcessor.quit();
    }

    @Override
    public Type getType() {
        return Type.APPLICATION_COMMAND;
    }
}
