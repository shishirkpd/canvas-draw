package com.skp.canvas.command;

import com.skp.canvas.processor.Canvas;
import com.skp.canvas.processor.DrawAction;
import com.skp.canvas.model.Vector;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class RectangleCommand implements DrawAction {

    private final List<LineCommand> lineCommands;
    private final Vector v1;
    private final Vector v2;
    private final char color;

    public RectangleCommand(Vector v1, Vector v2, char color) {
        this.v1 = v1;
        this.v2 = v2;
        this.color = color;

        Vector v3 = new Vector(v1.getX(), v2.getY());
        Vector v4 = new Vector(v2.getX(), v1.getY());

        lineCommands = Arrays.asList(
                new LineCommand(v1, v3, color),
                new LineCommand(v1, v4, color),
                new LineCommand(v2, v3, color),
                new LineCommand(v2, v4, color)
        );
    }

    @Override
    public void validate(Canvas canvas) {
        lineCommands.forEach(l -> l.validate(canvas));
    }

    @Override
    public void apply(Canvas canvas) {
        lineCommands.forEach(l -> l.apply(canvas));
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("lineCommands", lineCommands)
                .append("v1", v1)
                .append("v2", v2)
                .append("color", color)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RectangleCommand rectangle = (RectangleCommand) o;
        return color == rectangle.color &&
                Objects.equals(lineCommands, rectangle.lineCommands) &&
                Objects.equals(v1, rectangle.v1) &&
                Objects.equals(v2, rectangle.v2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lineCommands, v1, v2, color);
    }
}
