package com.skp.canvas.command;

import com.skp.canvas.processor.Canvas;
import com.skp.canvas.model.Vector;
import org.junit.Test;

public class LineCommandTest {

    Vector vector1 = new Vector(10, 3);
    Vector vector2 = new Vector(10, 9);

    LineCommand lineCommand = new LineCommand(vector1, vector2, 'x');

    @Test
    public void it_should_validate() {
        lineCommand.validate(new Canvas(30, 15));
    }

    @Test(expected = IllegalArgumentException.class)
    public void it_should_validate_and_throw_exception() {
        lineCommand.validate(new Canvas(9, 15));
    }

    @Test
    public void it_should_apply() {
        lineCommand.apply(new Canvas(22, 12));
    }
}