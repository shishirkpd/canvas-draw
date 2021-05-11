package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;
import com.skp.canvas.command.CreateCanvasCommand;
import com.skp.canvas.model.Vector;

public class CreateCanvasParser extends AbstractIntParametrizedActionParser {
    public CreateCanvasParser() {
        super("C", 2);
    }

    @Override
    protected ActionType parseCommand(String command, int[] params) {
        return new CreateCanvasCommand(new Vector(params[0], params[1]));
    }
}