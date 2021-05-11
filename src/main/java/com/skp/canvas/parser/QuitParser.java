package com.skp.canvas.parser;

import com.skp.canvas.command.QuitCommand;
import com.skp.canvas.actions.ActionType;

public class QuitParser extends AbstractParser {
    public QuitParser() {
        super("Q", 0);
    }

    @Override
    protected ActionType parseCommand(String[] split) {
        return new QuitCommand();
    }
}
