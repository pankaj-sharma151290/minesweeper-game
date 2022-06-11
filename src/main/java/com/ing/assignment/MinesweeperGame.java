package com.ing.assignment;

import com.ing.assignment.enums.GameCommand;
import com.ing.assignment.util.GameUtils;

import java.util.Optional;
import java.util.Scanner;

public class MinesweeperGame {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            GameUtils.startGame();
            do {
                System.out.print("$ ");
                String userInput;
                userInput = scanner.next();
                userInput = userInput.trim().toLowerCase();
                Optional<GameCommand> command = GameCommand.fromString(userInput);
                GameUtils.processGame(command);
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

