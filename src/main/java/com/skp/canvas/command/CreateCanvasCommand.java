package com.skp.canvas.command;

import com.skp.canvas.actions.ActionType;
import com.skp.canvas.actions.ActionsProcessor;
import com.skp.canvas.processor.Drawer;
import com.skp.canvas.model.Vector;

import java.util.Objects;

public class CreateCanvasCommand implements ActionType {

    private final Vector size;

    public CreateCanvasCommand(Vector size) {
        this.size = size;
    }

    @Override
    public void execute(ActionsProcessor actionsProcessor) {
        actionsProcessor.setDrawer(new Drawer(size));
    }

    @Override
    public Type getType() {
        return Type.APPLICATION_COMMAND;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCanvasCommand that = (CreateCanvasCommand) o;
        return Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {

        return Objects.hash(size);
    }
}
