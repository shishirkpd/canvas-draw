package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineParserTest {

    LineParser lineParser = new LineParser();

    @Test
    public void it_should_parse_given_input() {
        String[] inputParams = "L 30 10 30 20".split(" ");
        ActionType actionType = lineParser.parseCommand(inputParams);
        assertEquals(actionType.getType(), ActionType.Type.DRAW_COMMAND);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void it_should_parse_given_input_throw_error() {
        String[] inputParams = "L 30".split(" ");
        ActionType actionType = lineParser.parseCommand(inputParams);
    }

}