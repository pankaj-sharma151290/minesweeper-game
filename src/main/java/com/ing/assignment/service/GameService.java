package com.ing.assignment.service;

import com.ing.assignment.model.dto.Board;

public interface GameService {

    Board startGame(int nRows, int nCols, int mines);

    boolean selectTile(Board board, int rowIndex, int colIndex);

    Board resetBoardWithSameMines(Board board);

    void addFlag(Board board, int rowIndex, int colIndex);
}
