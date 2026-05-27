package com.example.tictactoe.service;

import com.example.tictactoe.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private GameService service;
    private GameState state;

    @BeforeEach
    void setUp() {
        service = new GameService();
        state = new GameState();
    }

    @Test
    void playerXGoesFirst() {
        assertEquals("X", state.getCurrentPlayer());
    }

    @Test
    void makeMove_placesMark() {
        service.makeMove(0, state);
        assertEquals("X", state.getBoard()[0]);
    }

    @Test
    void makeMove_switchesPlayer() {
        service.makeMove(0, state);
        assertEquals("O", state.getCurrentPlayer());
    }

    @Test
    void makeMove_cannotOverwriteCell() {
        service.makeMove(0, state); // X plays 0
        service.makeMove(0, state); // O tries 0 — should be ignored
        assertEquals("X", state.getBoard()[0]);
        assertEquals("O", state.getCurrentPlayer());
    }

    @Test
    void detectsRowWin() {
        service.makeMove(0, state); // X
        service.makeMove(3, state); // O
        service.makeMove(1, state); // X
        service.makeMove(4, state); // O
        service.makeMove(2, state); // X wins top row
        assertTrue(state.isGameOver());
        assertEquals("X", state.getWinner());
    }

    @Test
    void detectsDraw() {
        int[] moves = {0, 1, 2, 4, 3, 5, 7, 6, 8};
        for (int m : moves) service.makeMove(m, state);
        assertTrue(state.isGameOver());
        assertTrue(state.isDraw());
    }

    @Test
    void resetClearsBoard() {
        service.makeMove(0, state);
        state.reset();
        assertFalse(state.isGameOver());
        assertEquals("X", state.getCurrentPlayer());
        for (String cell : state.getBoard()) assertEquals("", cell);
    }
}
