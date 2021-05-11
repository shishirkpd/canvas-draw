package com.skp.canvas.parser;

import com.skp.canvas.actions.*;
import com.skp.canvas.command.CreateCanvasCommand;
import com.skp.canvas.command.FillCommand;
import com.skp.canvas.command.LineCommand;
import com.skp.canvas.command.RectangleCommand;
import com.skp.canvas.model.Vector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void it_should_parse_raw_string(){
        List<ActionParser> actionParserList = new ArrayList<>();
        actionParserList.add(new CreateCanvasParser());

        Parser parser = new Parser(actionParserList);

        String input = "C 10 20";

        ActionType parse = parser.parse(input);
        assertEquals(new CreateCanvasCommand(new Vector(10, 20)), parse);
    }

    @Test
    public void it_should_parse_raw_string_line_parser(){
        List<ActionParser> actionParserList = new ArrayList<>();
        actionParserList.add(new LineParser());

        Parser parser = new Parser(actionParserList);

        String input = "L 10 20 10 25";

        ActionType parse = parser.parse(input);
        assertEquals(new LineCommand(new Vector(9, 19), new Vector(9, 24), 'x'), parse);
    }

    @Test
    public void it_should_parse_raw_string_fill_parser(){
        List<ActionParser> actionParserList = new ArrayList<>();
        actionParserList.add(new FillParser());

        Parser parser = new Parser(actionParserList);

        String input = "B 20 10 c";

        ActionType parse = parser.parse(input);
        assertEquals(new FillCommand(new Vector(19, 9), 'c'), parse);
    }

    @Test
    public void it_should_parse_raw_string_rectangle_parser(){
        List<ActionParser> actionParserList = new ArrayList<>();
        actionParserList.add(new RectangleParser());

        Parser parser = new Parser(actionParserList);

        String input = "R 14 1 18 3";

        ActionType parse = parser.parse(input);
        assertEquals(new RectangleCommand(new Vector(13, 0), new Vector(17, 2), 'x'), parse);
    }

}