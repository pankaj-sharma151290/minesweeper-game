package com.ing.assignment.model.dto;

import com.ing.assignment.enums.GameStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data
class Board {

    private Tile[][] tiles;
    private int nCols, nRows, nMines, nUncoveredClearCells = 0;
    private GameStatus gameStatus;
    private int nFlags;
    private boolean minesPlaced;

    public Board(int nRows, int nCols, int mines) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.nMines = mines;
        this.gameStatus = GameStatus.RUNNING;
        this.tiles = new Tile[nRows][nCols];
        this.minesPlaced=false;
        initializeBoard(this.nRows, this.nCols);
    }

    private void initializeBoard(int nRows, int nCols) {
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                tiles[row][col] = new Tile(row, col);
            }
        }
    }

}
