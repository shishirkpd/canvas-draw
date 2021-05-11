package com.skp.canvas.parser;

import com.skp.canvas.command.LineCommand;
import com.skp.canvas.actions.ActionType;
import com.skp.canvas.model.Vector;

public class LineParser extends AbstractIntParametrizedActionParser {

    private static final char color = 'x';

    public LineParser() {
        super("L", 4);
    }

    @Override
    protected ActionType parseCommand(String command, int[] params) {
        return new LineCommand(new Vector(params[0], params[1]).shift(OFFSET), new Vector(params[2], params[3]).shift(OFFSET), color);
    }
}
