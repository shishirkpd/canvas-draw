package com.skp.canvas.actions;

import com.skp.canvas.command.CommandSupplier;
import com.skp.canvas.processor.Drawer;
import com.skp.canvas.parser.Parser;
import com.skp.canvas.render.Renderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ActionsProcessor {
    private static final Logger log = LogManager.getLogger(ActionsProcessor.class);
    private CommandSupplier commandSupplier;
    private Parser parser;
    private Renderer renderer;
    private Drawer drawer;

    private boolean completed = false;
    private boolean initialized = false;


    public void init(CommandSupplier commandSupplier, Parser parser, Renderer renderer) {
        this.commandSupplier = commandSupplier;
        this.parser = parser;
        this.renderer = renderer;
        completed = false;
        initialized = true;
    }

    public void run() {
        if (!initialized) {
            log.error("ActionsProcessor is not initialized");
            throw new IllegalStateException("Init application first!");
        }

        while (!completed) {
            Optional<String> command = commandSupplier.getNextCommand();
            if (!command.isPresent()) {
                log.error("No new commands, exiting");
                break;
            } else {
                process(command.get());
            }
        }
        renderer.close();
    }

    private void process(String command) {
        log.info("entered command {}", command);
        ActionType actionType = parser.parse(command);
        if (actionType.getType() == ActionType.Type.DRAW_COMMAND && drawer == null) {
            log.error("Canvas is not initialized");
            new Error("Canvas is not initialized").execute(this);
        } else {
            log.info("executing command {} as {}", command, actionType);
            try {
                actionType.execute(this);
            } catch (Exception e) {
                log.error("executing command {} as ", command, actionType);
                new Error("Error while executing command: " + command + " " + e.getMessage()).execute(this);
            }

        }
        if (drawer != null && !completed) {
            renderer.render(drawer);
        }
    }

    public void quit() {
        log.error("QuitCommand");
        completed = true;
    }

    public Drawer getDrawer() {
        return drawer;
    }

    public void setDrawer(Drawer drawer) {
        log.error("Drawer set");
        this.drawer = drawer;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
