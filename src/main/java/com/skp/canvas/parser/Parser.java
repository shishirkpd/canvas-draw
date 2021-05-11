package com.skp.canvas.parser;

import com.skp.canvas.actions.ActionType;
import com.skp.canvas.actions.Error;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Parser {
    private static final Logger log = LogManager.getLogger(Parser.class);
    private final List<ActionParser> parsers;

    public Parser(List<ActionParser> parsers) {
        this.parsers = parsers;
    }

    public ActionType parse(String raw) {
        List<ActionType> result = new ArrayList<>();
        try {
            result = parsers.stream()
                    .map(p -> p.parse(raw))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Exception parsing {}", raw, e);
            return new Error("Exception when parsing the action: " + e);
        }

        if (result.size() > 1) {
            log.error("More than one action match command {} : {} ", raw, result);
            return new Error("More than one action match command " + raw + " : " + result);
        }

        if (result.isEmpty()) {
            log.error("Unknown command: {}", raw);
            return new Error("Unknown command: " + raw);
        }
        log.info("Command {} parsed as {} ", raw, result);
        return result.get(0);

    }
}
