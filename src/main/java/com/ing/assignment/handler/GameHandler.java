package com.ing.assignment.handler;

import com.ing.assignment.enums.GameCommand;
import com.ing.assignment.enums.GameStatus;
import com.ing.assignment.model.Board;
import com.ing.assignment.service.GameService;
import com.ing.assignment.service.impl.GameServiceImpl;
import com.ing.assignment.util.GameUtil;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GameHandler {

    private GameService gameService = new GameServiceImpl();
    private Scanner scanner = new Scanner(System.in);
    private Board board;
    private int nBoardRow = 0, nBoardCol = 0, nMines = 0;

    public void startGame() {
        System.out.println();
        System.out.println("Welcome to Minesweeper Game.");
        System.out.println();
        do {
            System.out.println("Enter the grid height (Should be >0 &  <50) : ");
            nBoardRow = scanner.nextInt();
            System.out.println("Enter the grid width (Should be >0 &  <50) : ");
            nBoardCol = scanner.nextInt();
            System.out.println("Enter the number of mines (should be < height*width  & >0): ");
            nMines = scanner.nextInt();
        } while (isBoardSizeOutOfRange());
        System.out.println("Starting Game:");
        board = gameService.startGame(nBoardRow, nBoardCol, nMines);
        help();
        GameUtil.printBoard(board);
    }

    /**
     * Displays the acceptable commands as user input for the game.
     */
    public void help() {
        System.out.println();
        System.out.println("Valid Commands:");
        System.out.println("           1. \"help\": opens the help menu");
        System.out.println("           2. \"select\": specify which tile you want to check");
        System.out.println("           3. \"flag\": specify which tile you want to flag or un-flag");
        System.out.println("           4. \"restart\": start a new game");
        System.out.println("           5. \"exit\": to quit the game");
        System.out.println("           6. \"showBoard\": to display the current status");
        System.out.println();
    }

    /**
     * Processes the game as per user input.
     *
     * @param command user input
     */
    public boolean processGame(GameCommand command) {
        boolean exitGame = false;
        switch (command) {
            case HELP:
                help();
                break;
            case SELECT:
                selectTile();
                break;
            case FLAG:
                flagUnFlagTile();
                break;
            case RESTART:
                startGame();
                break;
            case EXIT:
                exitGame = exitGame();
                break;
            case SHOW_BOARD:
                GameUtil.printBoard(board);
                break;
        }
        return exitGame;
    }

    public void selectTile() {
        int rowIndex, colIndex;
        boolean noTilesLeft;
        System.out.println("Enter row no from 1 to " + nBoardRow + ": ");
        rowIndex = scanner.nextInt() - 1;
        System.out.println("Enter column no from 1 to " + nBoardCol + ": ");
        colIndex = scanner.nextInt() - 1;
        if (isTileIndicesInRange(rowIndex, colIndex)) {
            noTilesLeft = gameService.selectTile(board, rowIndex, colIndex);
            if (noTilesLeft && board.getNUncoveredClearCells() == (board.getNRows() * board.getNCols()) - board.getNMines()) {
                System.out.println("\n\nCongratulations, You have Won!!");
                board.setGameStatus(GameStatus.WON);
                GameUtil.printBoard(board);
                System.out.println("\n\nYou can restart or exit the game!!");
            } else if (noTilesLeft) {
                System.out.println("\n\nYou have hit the mine, try again!!");
                GameUtil.printBoard(board);
            } else {
                System.out.println("\n\n");
                GameUtil.printBoard(board);
                System.out.println("\n\n");
            }
        } else {
            System.out.println("You have entered incorrect row/column no, Please try again with entering command.");
        }
    }

    public void flagUnFlagTile() {
        int rowIndex, colIndex;
        System.out.println("Enter row no from 1 to " + nBoardRow + ": ");
        rowIndex = scanner.nextInt() - 1;
        System.out.println("Enter column no from 1 to " + nBoardCol + ": ");
        colIndex = scanner.nextInt() - 1;
        if (isTileIndicesInRange(rowIndex, colIndex)) {
            gameService.flagUnFlagTile(board, rowIndex, colIndex);
            System.out.println("\n\n");
            GameUtil.printBoard(board);
            System.out.println("\n\n");
        } else {
            System.out.println("You have entered incorrect row/column no, Please try again.");
        }
    }

    public boolean exitGame() {
        System.out.println("Exit Game.");
        scanner.close();
        return true;
    }

    private boolean isTileIndicesInRange(int rowIndex, int colIndex) {
        return (rowIndex >= 0 && rowIndex < nBoardRow) && (colIndex >= 0 && colIndex < nBoardCol);
    }

    private boolean isBoardSizeOutOfRange() {
        return nBoardRow < 1 || nBoardRow > 50 || nBoardCol < 1 || nBoardCol > 50 || nMines < 1 || nMines > nBoardRow * nBoardCol;
    }

}
