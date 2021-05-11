package com.skp.canvas.command;

import com.skp.canvas.render.ConsoleRenderer;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleCommandSupplier implements CommandSupplier {

    public static final String PROMPT = "enter command:";
    private final ConsoleRenderer renderer;
    private final Scanner scanner;


    public ConsoleCommandSupplier(ConsoleRenderer renderer) {
        scanner = new Scanner(System.in);
        this.renderer = renderer;
    }

    @Override
    public Optional<String> getNextCommand() {
        renderer.renderNewLine("");
        renderer.renderContent(PROMPT);
        Optional<String> readCommand = Optional.ofNullable(scanner.nextLine());
        return readCommand;
    }
}
