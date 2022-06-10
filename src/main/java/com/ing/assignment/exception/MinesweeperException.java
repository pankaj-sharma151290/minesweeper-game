package com.ing.assignment.exception;

public class MinesweeperException extends RuntimeException {

    private static final long serialVersionUID = -514406192720810498L;

    private final String message;

    public MinesweeperException(String message) {

        this.message = message;
    }

    public String getMessage() {

        return message;
    }
}
