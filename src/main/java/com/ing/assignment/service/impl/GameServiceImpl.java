package com.ing.assignment.service.impl;

import com.ing.assignment.enums.TileState;
import com.ing.assignment.enums.TileType;
import com.ing.assignment.model.dto.Board;
import com.ing.assignment.model.dto.Tile;
import com.ing.assignment.service.GameService;

import java.util.Arrays;
import java.util.Random;

public class GameServiceImpl implements GameService {

    @Override
    public Board startGame(int nRows, int nCols, int mines) {
        Board board = new Board(nRows, nCols, mines);
        return board;
    }

    private void placeMines(Board board, Tile firstSelectedTile) {
        board.setMinesPlaced(true);
        Random random = new Random();
        int nMines = board.getNMines();
        while (nMines > 0) {
            int rowIndex = random.nextInt(board.getNRows());
            int colIndex = random.nextInt(board.getNCols());
            Tile randomTile = board.getTiles()[rowIndex][colIndex];
            if (randomTile.getType() != TileType.MINED && !randomTile.equals(firstSelectedTile)) {
                randomTile.setType(TileType.MINED);
                updateAdjacentTiles(board, rowIndex, colIndex);
                nMines--;
            }
        }
    }

    private void updateAdjacentTiles(Board board, int i, int j) {
        for (int[] adjacentTileIndex : board.getAdjacentTiles()) {
            int adjacentTileRowIndex = i + adjacentTileIndex[0];
            int adjacentTileColIndex = j + adjacentTileIndex[1];
            if (adjacentTileRowIndex < 0 || adjacentTileColIndex < 0 || adjacentTileRowIndex >= board.getNRows() || adjacentTileColIndex >= board.getNCols())
                continue;
            if (board.getTiles()[adjacentTileRowIndex][adjacentTileColIndex].getType() != TileType.MINED) {
                board.getTiles()[adjacentTileRowIndex][adjacentTileColIndex].setNMinesAround(board.getTiles()[adjacentTileRowIndex][adjacentTileColIndex].getNMinesAround() + 1);
            }
        }
    }

    @Override
    public boolean selectTile(Board board, int rowIndex, int colIndex) {
        Tile selectedTile = board.getTiles()[rowIndex][colIndex];
        if (!board.isMinesPlaced())
            placeMines(board, selectedTile);
        if (!selectedTile.isCovered())
            return false;
        if (TileType.MINED == selectedTile.getType()) {
            resetBoardWithSameMines(board);
            return true;
        }
        uncoverTiles(board, selectedTile, rowIndex, colIndex);
        return board.getNUncoveredClearCells() == (board.getNRows() * board.getNCols()) - board.getNMines();
    }

    /*
     * Reveals all possible neighbors and their neighbors recursively.
     *
     */
    private void uncoverTiles(Board board, Tile selectedTile, int rowIndex, int colIndex) {

        if (selectedTile.getNMinesAround() == 0) { // uncover neighboring tiles
            selectedTile.setState(TileState.UNCOVERED);
            board.setNUncoveredClearCells(board.getNUncoveredClearCells() + 1);
            for (int[] nei : board.getAdjacentTiles()) {
                if (rowIndex + nei[0] < 0 || colIndex + nei[1] < 0 || rowIndex + nei[0] >= board.getNRows() || colIndex + nei[1] >= board.getNCols())
                    continue;

                Tile adjacentTile = board.getTiles()[rowIndex + nei[0]][colIndex + nei[1]];
                if (TileType.MINED != adjacentTile.getType() && adjacentTile.isCovered()) {
                    uncoverTiles(board, adjacentTile, rowIndex + nei[0], colIndex + nei[1]);
                }
            }
        } else {
            selectedTile.setState(TileState.UNCOVERED);
            board.setNUncoveredClearCells(board.getNUncoveredClearCells() + 1);
        }
    }

    @Override
    public Board resetBoardWithSameMines(Board board) {
        Arrays.stream(board.getTiles()).forEach(
                (boardRow) -> Arrays.stream(boardRow).forEach((tile) -> tile.setState(TileState.COVERED))
        );
        board.setNFlags(0);
        board.setNUncoveredClearCells(0);
        return board;
    }

    @Override
    public Board selectFlag(Board board, int rowIndex, int colIndex) {

        Tile selectedTile = board.getTiles()[rowIndex][colIndex];
        if (selectedTile.getState() == TileState.COVERED) {
            selectedTile.setState(TileState.FLAGGED);
            board.setNFlags(board.getNFlags() + 1);
        } else if (selectedTile.getState() == TileState.FLAGGED) {
            selectedTile.setState(TileState.COVERED);
            board.setNFlags(board.getNFlags() - 1);
        }
        return board;
    }

    private void revealAllMines(Board board) {
        for (Tile[] tile : board.getTiles()) {
            for (Tile t : tile) {
                if (t.getType() == TileType.MINED) {
                    t.setState(TileState.UNCOVERED);
                    board.setNUncoveredClearCells(board.getNUncoveredClearCells() + 1);
                }
            }
        }
    }
}
