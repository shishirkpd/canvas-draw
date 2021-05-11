package com.skp.canvas.processor;

import com.skp.canvas.actions.ActionsProcessor;
import com.skp.canvas.actions.ActionType;

public interface DrawAction extends ActionType {
    void validate(Canvas canvas);

    void apply(Canvas canvas);

    @Override
    default Type getType() {
        return Type.DRAW_COMMAND;
    }

    @Override
    default void execute(ActionsProcessor actionsProcessor) {
        actionsProcessor.getDrawer().addAction(this);
    }
}
