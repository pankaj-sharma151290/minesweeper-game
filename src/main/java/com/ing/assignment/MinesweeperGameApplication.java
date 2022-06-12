package com.ing.assignment;

import com.ing.assignment.enums.GameCommand;
import com.ing.assignment.handler.GameHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class MinesweeperGameApplication implements CommandLineRunner {

    /**
     * Starting point for game execution.
     *
     * @param args args
     */
    public static void main(String[] args) {
        GameHandler gameHandler = new GameHandler();
        boolean exitGame = false;
        try (Scanner scanner = new Scanner(System.in)) {
            gameHandler.startGame();
            do {
                System.out.print("$ ");
                String userInput;
                userInput = scanner.next();
                userInput = userInput.trim().toLowerCase();
                Optional<GameCommand> command = GameCommand.fromString(userInput);

                if (command.isPresent()) {
                    exitGame = gameHandler.processGame(command.get());
                } else {
                    System.out.println("Unknown command, Please enter valid command. enter help for list of acceptable commands.");
                }
            } while (!exitGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run(String... args) {
    }
}
