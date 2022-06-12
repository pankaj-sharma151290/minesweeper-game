package com.ing.assignment.util;

import com.ing.assignment.enums.TileState;
import com.ing.assignment.enums.TileType;
import com.ing.assignment.model.Board;
import com.ing.assignment.model.Tile;

public class GameUtil {

    private static final int[][] adjacentTileIndices = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, 0}, {1, 1}, {1, -1}};

    public static int[][] getAdjacentTileIndices() {
        return adjacentTileIndices;
    }

    public static boolean isTileIndexOutOfRange(int rowIndex, int colIndex, Board board) {
        return rowIndex < 0 || colIndex < 0 || rowIndex >= board.getNRows() || colIndex >= board.getNCols();
    }

    public static void printBoard(Board board){
        System.out.println("Game Status :" + board.getGameStatus());
        System.out.println("No of Covered Tiles :" + board.getNUncoveredClearCells());
        System.out.println("No of Flagged Tiles :" + board.getNFlags());
        System.out.println("No of Mines left :" + (board.getNMines() - board.getNFlags()));
        System.out.println();
        for (Tile[] tile : board.getTiles()) {
            for (Tile t : tile) {
                if (t.getType() == TileType.MINED && t.getState() == TileState.UNCOVERED) {
                    System.out.print("\t " + "*");
                } else {
                    if (t.getState() == TileState.UNCOVERED) {
                        System.out.print("\t " + t.getNMinesAround());
                    } else if (t.getState() == TileState.FLAGGED) {
                        System.out.print("\t " + "F");
                    } else {
                        System.out.print("\t -");
                    }
                }
            }
            System.out.println();
        }
    }
}
