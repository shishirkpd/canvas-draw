package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;

public interface ActionParser {
    public static final String PARAMETER_SEPARATOR = " ";

    ActionType parse(String raw);
}
