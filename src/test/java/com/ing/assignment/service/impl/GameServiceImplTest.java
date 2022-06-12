package com.ing.assignment.service.impl;

import com.ing.assignment.enums.GameStatus;
import com.ing.assignment.enums.TileState;
import com.ing.assignment.enums.TileType;
import com.ing.assignment.model.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@ExtendWith(SpringExtension.class)
public class GameServiceImplTest {

    @InjectMocks
    GameServiceImpl gameService;

    @Test
    @DisplayName(value = "Test StartGame")
    public void testStartGame() {
        Board board = gameService.startGame(5, 5, 5);
        assertThat(board.getNCols(), is(5));
        assertThat(board.getNCols(), is(5));
        assertThat(board.getNMines(), is(5));
        assertThat(board.getGameStatus(), is(GameStatus.RUNNING));
        assertThat(board.isMinesPlaced(), is(false));
    }

    @Test
    @DisplayName(value = "Test selectTile when tile selected first time.")
    public void testSelectTileFirstTime() {
        Board board = gameService.startGame(5, 5, 5);
        boolean result = gameService.selectTile(board, 4, 4);
        assertThat(board.isMinesPlaced(), is(true));
        assertThat(board.getTiles()[4][4].getState(), is(TileState.UNCOVERED));
        assertThat(board.getTiles()[4][4].getType(), not(TileType.MINED));
        assertThat(board.getNUncoveredClearCells(), not(0));
        assertThat(result, is(false));
    }

    @Test
    @DisplayName(value = "Test selectTile for MINED Tile.")
    public void testSelectTileMined() {
        Board board = gameService.startGame(5, 5, 5);
        gameService.selectTile(board, 4, 4);
        board.getTiles()[2][1].setType(TileType.MINED);
        boolean gameOver = gameService.selectTile(board, 2, 1);
        assertThat(board.getTiles()[2][1].getType(), is(TileType.MINED));
        assertThat(gameOver, is(true));
    }

    @Test
    @DisplayName(value = "Test selectTile for COVERED Tile.")
    public void testSelectTileCovered() {
        Board board = gameService.startGame(5, 5, 5);
        gameService.selectTile(board, 4, 4);
        board.getTiles()[2][1].setState(TileState.UNCOVERED);
        board.getTiles()[2][1].setType(TileType.NUMBER);
        boolean gameOver = gameService.selectTile(board, 2, 1);
        assertThat(board.getTiles()[2][1].getState(), is(TileState.UNCOVERED));
        assertThat(gameOver, is(false));
    }

    @Test
    @DisplayName(value = "Test addFlag.")
    public void testAddFlag() {
        Board board = gameService.startGame(5, 5, 5);
        gameService.selectTile(board, 4, 4);
        board.getTiles()[2][1].setState(TileState.COVERED);
        gameService.addFlag(board, 2, 1);
        assertThat(board.getTiles()[2][1].getState(), is(TileState.FLAGGED));
        assertThat(board.getNFlags(), is(1));
    }

    @Test
    @DisplayName(value = "Test addFlag for flagged Tile.")
    public void testReAddFlag() {
        Board board = gameService.startGame(5, 5, 5);
        gameService.selectTile(board, 4, 4);
        board.getTiles()[2][1].setState(TileState.COVERED);
        gameService.addFlag(board, 2, 1);
        gameService.addFlag(board, 2, 1);
        assertThat(board.getTiles()[2][1].getState(), not(TileState.FLAGGED));
        assertThat(board.getNFlags(), is(0));
    }

}
