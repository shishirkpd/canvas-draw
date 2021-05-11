package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;
import com.skp.canvas.actions.Error;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public abstract class AbstractIntParametrizedActionParser extends AbstractParser {
    private static final Logger log = LogManager.getLogger(AbstractIntParametrizedActionParser.class);

    protected AbstractIntParametrizedActionParser(String command, int paramCount) {
        super(command, paramCount);
    }

    @Override
    protected ActionType parseCommand(String[] split) {
        try {
            int[] params = Arrays.stream(split).skip(1).mapToInt(Integer::valueOf).toArray();

            return parseCommand(split[0], params);
        } catch (NumberFormatException e) {
            log.error("Bad number format", e);
            return new Error("Cannot parse int value: " + e.getMessage());
        }

    }


    protected abstract ActionType parseCommand(String command, int[] params);
}
