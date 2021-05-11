package com.skp.canvas.parser;

import com.skp.canvas.command.RectangleCommand;
import com.skp.canvas.actions.ActionType;
import com.skp.canvas.model.Vector;

public class RectangleParser extends AbstractIntParametrizedActionParser {

    private static final char color = 'x';

    public RectangleParser() {
        super("R", 4);
    }

    @Override
    protected ActionType parseCommand(String command, int[] params) {
        return new RectangleCommand(new Vector(params[0], params[1]).shift(OFFSET), new Vector(params[2], params[3]).shift(OFFSET), color);
    }
}
