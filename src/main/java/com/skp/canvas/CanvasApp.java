package com.skp.canvas;

import com.skp.canvas.actions.ActionsProcessor;
import com.skp.canvas.command.ConsoleCommandSupplier;
import com.skp.canvas.parser.Parser;
import com.skp.canvas.parser.*;
import com.skp.canvas.render.ConsoleRenderer;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class CanvasApp {

    private static final Logger log = LogManager.getLogger(CanvasApp.class);

    public static void main(String[] args) {
        try {
            Options options = new Options();

            CommandLineParser parser = new DefaultParser();
            try {
                CommandLine line = parser.parse(options, args);
                    runApplication(line);
            } catch (ParseException exp) {
                log.error("Exception while parsing: ", exp.getMessage());
            }

        } catch (Throwable e) {
            log.error(e);
        }

    }

    private static void runApplication(CommandLine line) {
        ConsoleRenderer renderer =  new ConsoleRenderer();
        ConsoleCommandSupplier consoleCommandSupplier = new ConsoleCommandSupplier(renderer);
        Parser parser = createParser();
        ActionsProcessor actionsProcessor = new ActionsProcessor();
        actionsProcessor.init(consoleCommandSupplier, parser, renderer);
        actionsProcessor.run();
    }


    private static Parser createParser() {
        return new Parser(Arrays.asList(
                new LineParser(),
                new FillParser(),
                new RectangleParser(),
                new QuitParser(),
                new CreateCanvasParser()
        ));
    }
}
