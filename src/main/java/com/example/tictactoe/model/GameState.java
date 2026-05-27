package com.example.tictactoe.model;

import java.io.Serializable;
import java.util.Arrays;

public class GameState implements Serializable {

    private String[] board;       // "X", "O", or "" (empty)
    private String currentPlayer; // "X" or "O"
    private String winner;        // "X", "O", or ""
    private boolean gameOver;
    private boolean draw;
    private int[] winningCells;

    public GameState() {
        reset();
    }

    public void reset() {
        board = new String[9];
        Arrays.fill(board, "");
        currentPlayer = "X";
        winner = "";
        gameOver = false;
        draw = false;
        winningCells = null;
    }

    public boolean isWinningCell(int index) {
        if (winningCells == null) return false;
        for (int cell : winningCells) {
            if (cell == index) return true;
        }
        return false;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────

    public String[] getBoard() { return board; }
    public void setBoard(String[] board) { this.board = board; }

    public String getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(String currentPlayer) { this.currentPlayer = currentPlayer; }

    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }

    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }

    public boolean isDraw() { return draw; }
    public void setDraw(boolean draw) { this.draw = draw; }

    public int[] getWinningCells() { return winningCells; }
    public void setWinningCells(int[] winningCells) { this.winningCells = winningCells; }
}
