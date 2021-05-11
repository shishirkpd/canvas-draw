package com.skp.canvas.actions;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class Error implements ActionType {

    private final String desc;
    private final Throwable error;

    public Error(String desc) {
        this.desc = desc;
        error = null;
    }

    public Error(String desc, Throwable error) {
        this.desc = desc + ":" + error.getMessage();
        this.error = error;
    }

    @Override
    public void execute(ActionsProcessor actionsProcessor) {
        actionsProcessor.getRenderer().renderNewLine(desc);
    }

    @Override
    public Type getType() {
        return Type.APPLICATION_COMMAND;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("desc", desc)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error that = (Error) o;
        return Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(desc);
    }


}
