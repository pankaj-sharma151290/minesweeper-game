package com.ing.assignment.model.dto;

import com.ing.assignment.enums.GameStatus;
import com.ing.assignment.enums.TileState;
import com.ing.assignment.enums.TileType;
import lombok.Data;

public @Data
class Board {

    private Tile[][] tiles;
    private int nCols, nRows, nMines, nUncoveredClearCells = 0;
    private GameStatus gameStatus;
    private int[][] adjacentTiles = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, 0}, {1, 1}, {1, -1}};
    private int nFlags;
    private boolean minesPlaced = false;

    public Board(int nRows, int nCols, int mines) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.nMines = mines;
        this.gameStatus = GameStatus.RUNNING;
        this.tiles = new Tile[nRows][nCols];
        initializeBoard(this.nRows, this.nCols);
    }

    private void initializeBoard(int nRows, int nCols) {
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                tiles[row][col] = new Tile(row, col);
            }
        }
    }

    @Override
    public String toString() {
        System.out.println("Game Status :" + this.gameStatus);
        System.out.println("No of Covered Tiles :" + this.getNUncoveredClearCells());
        System.out.println("No of Flagged Tiles :" + this.getNFlags());
        System.out.println("No of Mines left :" + (this.getNMines() - this.getNFlags()));
        System.out.println();
        for (Tile[] tile : tiles) {
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
        return "Board2{}";
    }


}
