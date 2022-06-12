package com.ing.assignment.enums;

import java.util.Arrays;
import java.util.Optional;

public enum GameCommand {

    HELP("help"), SELECT("select"), FLAG("flag"), SHOW_BOARD("showboard"), EXIT("exit"), RESTART("restart");

    private String command;

    GameCommand(final String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public static Optional<GameCommand> fromString(String command) {
        return Arrays.stream(values()).filter(gc -> gc.command.equalsIgnoreCase(command)).findFirst();
    }

}
