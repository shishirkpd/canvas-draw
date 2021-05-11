package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;
import com.skp.canvas.actions.Error;
import org.junit.Test;

import static org.junit.Assert.*;

public class FillParserTest {

    FillParser fillParser = new FillParser();

    @Test
    public void it_should_parse_given_input() {
        String[] inputParams = "B 30 10 z".split(" ");
        ActionType actionType = fillParser.parseCommand(inputParams);
        assertEquals(actionType.getType(), ActionType.Type.DRAW_COMMAND);
    }

    @Test
    public void it_should_parse_given_input_throw_error() {
        String[] inputParams = "B 30".split(" ");
        ActionType actionType = fillParser.parseCommand(inputParams);
        assertEquals(actionType, new Error("Color should be 1 char"));
    }
}