package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuitCommandParserTest {

    QuitParser quitParser = new QuitParser();

    @Test
    public void it_should_parse_given_input() {
        String[] inputParams = "Q".split(" ");
        ActionType actionType = quitParser.parseCommand(inputParams);
        assertEquals(actionType.getType(), ActionType.Type.APPLICATION_COMMAND);
    }

}