package com.ing.assignment.service.impl;

import com.ing.assignment.enums.TileState;
import com.ing.assignment.enums.TileType;
import com.ing.assignment.model.Board;
import com.ing.assignment.model.Tile;
import com.ing.assignment.service.GameService;
import com.ing.assignment.util.GameUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public Board startGame(int nRows, int nCols, int mines) {
        return new Board(nRows, nCols, mines);
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

    private void placeMines(Board board, Tile firstSelectedTile) {
        board.setMinesPlaced(true);
        Random random = new Random();
        int nMines = board.getNMines();
        while (nMines > 0) {
            int rowIndex = random.nextInt(board.getNRows() - 1);
            int colIndex = random.nextInt(board.getNCols() - 1);
            Tile randomTile = board.getTiles()[rowIndex][colIndex];
            if (randomTile.getType() != TileType.MINED && !randomTile.equals(firstSelectedTile)) {
                randomTile.setType(TileType.MINED);
                updateAdjacentTiles(board, rowIndex, colIndex);
                nMines--;
            }
        }
    }

    private void updateAdjacentTiles(Board board, int rowIndex, int colIndex) {
        for (int[] adjacentTileIndex : GameUtil.getAdjacentTileIndices()) {
            int adjacentTileRowIndex = rowIndex + adjacentTileIndex[0];
            int adjacentTileColIndex = colIndex + adjacentTileIndex[1];
            if (GameUtil.isTileIndexOutOfRange(adjacentTileRowIndex, adjacentTileColIndex, board))
                continue;
            Tile adjacentTile = board.getTiles()[adjacentTileRowIndex][adjacentTileColIndex];
            if (adjacentTile.getType() != TileType.MINED) {
                adjacentTile.setNMinesAround(adjacentTile.getNMinesAround() + 1);
                adjacentTile.setType(TileType.NUMBER);
            }
        }
    }

    /*
     * Uncover all possible neighbors and their neighbors recursively.
     *
     */
    private void uncoverTiles(Board board, Tile selectedTile, int rowIndex, int colIndex) {

        if (selectedTile.getNMinesAround() == 0) { // uncover neighboring tiles when its empty tile
            selectedTile.setState(TileState.UNCOVERED);
            board.setNUncoveredClearCells(board.getNUncoveredClearCells() + 1);
            for (int[] adjacentTileIndex : GameUtil.getAdjacentTileIndices()) {
                int adjacentTileRowIndex = rowIndex + adjacentTileIndex[0];
                int adjacentTileColIndex = colIndex + adjacentTileIndex[1];
                if (GameUtil.isTileIndexOutOfRange(adjacentTileRowIndex, adjacentTileColIndex, board))
                    continue;
                Tile adjacentTile = board.getTiles()[adjacentTileRowIndex][adjacentTileColIndex];
                if (TileType.MINED != adjacentTile.getType() && adjacentTile.isCovered()) {
                    uncoverTiles(board, adjacentTile, adjacentTileRowIndex, adjacentTileColIndex);
                }
            }
        }
        else {
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
    public void flagUnFlagTile(Board board, int rowIndex, int colIndex) {
        Tile selectedTile = board.getTiles()[rowIndex][colIndex];
        if (selectedTile.getState() == TileState.COVERED) {
            selectedTile.setState(TileState.FLAGGED);
            board.setNFlags(board.getNFlags() + 1);
        } else if (selectedTile.getState() == TileState.FLAGGED) {
            selectedTile.setState(TileState.COVERED);
            board.setNFlags(board.getNFlags() - 1);
        }
    }
}
