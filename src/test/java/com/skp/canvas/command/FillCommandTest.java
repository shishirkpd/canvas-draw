package com.skp.canvas.command;

import com.skp.canvas.processor.Canvas;
import com.skp.canvas.model.Vector;
import org.junit.Test;

public class FillCommandTest {

    Vector vector = new Vector(10, 3);

    FillCommand fillCommand = new FillCommand(vector, 'c');

    @Test
    public void it_should_validate() {
        fillCommand.validate(new Canvas(30, 15));
    }

    @Test(expected = IllegalArgumentException.class)
    public void it_should_validate_and_throw_exception() {
        fillCommand.validate(new Canvas(9, 15));
    }

    @Test
    public void it_should_apply() {
        fillCommand.apply(new Canvas(22, 12));
    }
}