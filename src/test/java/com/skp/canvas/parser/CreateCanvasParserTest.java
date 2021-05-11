package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;
import com.skp.canvas.actions.Error;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateCanvasParserTest {

    CreateCanvasParser createCanvasParser = new CreateCanvasParser();

    @Test
    public void it_should_parse_given_input() {
        String[] inputParams = "C 30 10".split(" ");
        ActionType actionType = createCanvasParser.parseCommand(inputParams);
        assertEquals(ActionType.Type.APPLICATION_COMMAND, actionType.getType());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void it_should_parse_given_input_throw_error() {
        String[] inputParams = "C 30".split(" ");
        ActionType actionType = createCanvasParser.parseCommand(inputParams);
    }

    @Test
    public void it_should_parse_given_input_throw_number_format_exception() {
        String[] inputParams = "C b 9".split(" ");
        ActionType actionType = createCanvasParser.parseCommand(inputParams);
        assertEquals(new Error("Cannot parse int value: For input string: \"b\""), actionType);
    }
}