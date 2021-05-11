package com.skp.canvas.command;

import com.skp.canvas.processor.Canvas;
import com.skp.canvas.processor.DrawAction;
import com.skp.canvas.validation.Validation;
import com.skp.canvas.model.Vector;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class FillCommand implements DrawAction {

    private final Vector start;
    private final char color;
    private final boolean cornersAsBoundaries;

    public FillCommand(Vector start, char color) {
        this(start, color, true);
    }

    public FillCommand(Vector start, char color, boolean cornersAsBoundaries) {
        this.start = start;
        this.color = color;
        this.cornersAsBoundaries = cornersAsBoundaries;
    }

    @Override
    public void validate(Canvas canvas) {
        Validation.validateInside(start, canvas.getSize());

    }

    @Override
    public void apply(Canvas canvas) {
        char colorToFill = canvas.getColor(start);

        Queue<Vector> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Vector next = queue.poll();
            canvas.setColor(next, color);
            tryAddNext(canvas, queue, next.shift(0, 1), colorToFill);
            tryAddNext(canvas, queue, next.shift(0, -1), colorToFill);
            tryAddNext(canvas, queue, next.shift(1, 0), colorToFill);
            tryAddNext(canvas, queue, next.shift(-1, 0), colorToFill);

            if (!cornersAsBoundaries) {
                tryAddNext(canvas, queue, next.shift(1, 1), colorToFill);
                tryAddNext(canvas, queue, next.shift(1, -1), colorToFill);
                tryAddNext(canvas, queue, next.shift(-1, 1), colorToFill);
                tryAddNext(canvas, queue, next.shift(-1, -1), colorToFill);
            }

        }
    }

    private void tryAddNext(Canvas canvas, Queue<Vector> queue, Vector vector, char colorToFill) {
        if (vector.lessThan(canvas.getSize()) && !Validation.isNegative(vector) && canvas.getColor(vector) == colorToFill) {
            queue.add(vector);
        }

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("start", start)
                .append("color", color)
                .append("cornersAsBoundaries", cornersAsBoundaries)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FillCommand fillCommand = (FillCommand) o;
        return color == fillCommand.color &&
                cornersAsBoundaries == fillCommand.cornersAsBoundaries &&
                Objects.equals(start, fillCommand.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, color, cornersAsBoundaries);
    }
}
