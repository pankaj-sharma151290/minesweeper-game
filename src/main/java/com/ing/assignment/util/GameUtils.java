package com.ing.assignment.util;

import com.ing.assignment.enums.GameCommand;
import com.ing.assignment.enums.GameStatus;
import com.ing.assignment.model.dto.Board;
import com.ing.assignment.service.GameService;
import com.ing.assignment.service.impl.GameServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class GameUtils {

    private static GameService gameService = new GameServiceImpl();
    private static Scanner scanner = new Scanner(System.in);
    private static Board board;
    private static int nBoardRow = 0, nBoardCol = 0, nMines = 0;

    public static Board startGame() {
        System.out.println();
        System.out.println("Welcome to Minesweeper Game.");
        do {
            System.out.println("Enter the grid height (Should not be >50) : ");
            nBoardRow = scanner.nextInt();
            System.out.println("Enter the grid width (Should not be >50) : ");
            nBoardCol = scanner.nextInt();
            System.out.println("Enter the number of mines : ");
            nMines = scanner.nextInt();
        } while (nBoardRow > 50 || nBoardCol > 50);
        System.out.println("Starting Game:");
        board = gameService.startGame(nBoardRow, nBoardCol, nMines);
        GameUtils.help();
        board.toString();
        return board;
    }

    public static void help() {
        System.out.println();
        System.out.println("Valid Commands:");
        System.out.println("           1. \"help\": opens the help menu");
        System.out.println("           2. \"select\": specify which tile you want to check");
        System.out.println("           3. \"flag\": specify which tile you want to flag");
        System.out.println("           4. \"restart\": start a new game");
        System.out.println("           5. \"quit\": to quit the game");
        System.out.println("           6. \"showBoard\": to display the current status");
        System.out.println();
    }

    public static void selectTile() {
        int rowIndex = 0, colIndex = 0;
        boolean gameOver = false;
        System.out.println("Enter row no from 1 to " + nBoardRow + ": ");
        rowIndex = scanner.nextInt() - 1;
        System.out.println("Enter column no from 1 to " + nBoardCol + ": ");
        colIndex = scanner.nextInt() - 1;

        if ((rowIndex >= 0 && rowIndex < nBoardRow) && (colIndex >= 0 && colIndex < nBoardCol)) {
            gameOver = gameService.selectTile(board, rowIndex, colIndex);
            if (gameOver && board.getNUncoveredClearCells() == (board.getNRows() * board.getNCols()) - board.getNMines()) {
                System.out.println("\n\nYou have Won!!");
                board.setGameStatus(GameStatus.WON);
                board.toString();
                System.out.println("\n\nYou can restart or exit the game!!");
            } else if (gameOver) {
                System.out.println("\n\nYou hit the mine, try again!!");
                board.toString();
            } else {
                System.out.println("\n\n");
                board.toString();
                System.out.println("\n\n");
            }
        } else {
            System.out.println("You have entered incorrect row/column no, Please try again with entering command.");
        }
    }

    public static void flagTile() {
        int rowIndex = 0, colIndex = 0;
        System.out.println("Enter row no from 1 to " + nBoardRow + ": ");
        rowIndex = scanner.nextInt() - 1;

        System.out.println("Enter column no from 1 to " + nBoardCol + ": ");
        colIndex = scanner.nextInt() - 1;

        if ((rowIndex >= 0 && rowIndex < nBoardRow) && (colIndex >= 0 && colIndex < nBoardCol)) {
            gameService.selectFlag(board, rowIndex, colIndex);
            System.out.println("\n\n");
            board.toString();
            System.out.println("\n\n");
        } else {
            System.out.println("You have entered incorrect row/column no, Please try again.");
        }
    }

    public static void processGame(Optional<GameCommand> command) {
        if (command.isPresent()) {
            switch (command.get()) {
                case HELP:
                    GameUtils.help();
                    break;
                case SELECT:
                    GameUtils.selectTile();
                    break;
                case FLAG:
                    GameUtils.flagTile();
                    break;
                case RESTART:
                    GameUtils.startGame();
                    break;
                case EXIT:
                    System.out.println("Exit Game.");
                    System.exit(0);
                    break;
                case SHOW_BOARD:
                    GameUtils.showGameBoard();
                    break;
            }
        }
        else{
            System.out.println("Unknown command, Please enter valid command. enter help for list of acceptable commands.");
        }
    }

    public static void showGameBoard() {
        board.toString();
    }
}
