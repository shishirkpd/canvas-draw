package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;
import com.skp.canvas.actions.Error;
import com.skp.canvas.model.Vector;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractParser implements ActionParser {

    public static final Vector OFFSET = new Vector(-1, -1);
    private static final Logger log = LogManager.getLogger(AbstractParser.class);
    private final String command;
    private final int paramCount;

    public AbstractParser(String command, int paramCount) {
        this.command = command;
        this.paramCount = paramCount;
    }

    @Override
    public ActionType parse(String raw) {
        if (StringUtils.isBlank(raw)) {
            log.debug("command is blank");
            return null;
        }
        String[] split = StringUtils.split(raw, PARAMETER_SEPARATOR);
        if (!command.equals(split[0])) {
            return null;
        }
        if (split.length != paramCount + 1) {
            log.error("Command {} - wrong params", raw);
            return new Error("Expected " + paramCount + " params for command " + command + " but got " + (split.length - 1));
        }

        return parseCommand(split);
    }

    protected abstract ActionType parseCommand(String[] split);
}
