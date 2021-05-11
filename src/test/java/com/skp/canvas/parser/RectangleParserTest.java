package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;
import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleParserTest {

    RectangleParser rectangleParser = new RectangleParser();

    @Test
    public void it_should_parse_given_input() {
        String[] inputParams = "R 2 2 10 10".split(" ");
        ActionType actionType = rectangleParser.parseCommand(inputParams);
        assertEquals(actionType.getType(), ActionType.Type.DRAW_COMMAND);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void it_should_parse_given_input_throw_error() {
        String[] inputParams = "R 30".split(" ");
        ActionType actionType = rectangleParser.parseCommand(inputParams);
    }
}