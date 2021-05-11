package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;
import com.skp.canvas.actions.Error;
import com.skp.canvas.command.FillCommand;
import com.skp.canvas.model.Vector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class FillParser extends AbstractParser {
    private static final Logger log = LogManager.getLogger(FillParser.class);

    public FillParser() {
        super("B", 3);
    }

    @Override
    protected ActionType parseCommand(String[] split) {
        try {
            int[] params = Arrays.stream(split).skip(1).limit(2).mapToInt(Integer::valueOf).toArray();
            String color = split[split.length - 1];
            if (color.length() != 1) {
                return new Error("Color should be 1 char");
            }
            return new FillCommand(new Vector(params[0], params[1]).shift(OFFSET), color.charAt(0));
        } catch (NumberFormatException e) {
            log.error("Bad number format", e);
            return new Error("Cannot parse int value: " + e.getMessage());
        }
    }
}
