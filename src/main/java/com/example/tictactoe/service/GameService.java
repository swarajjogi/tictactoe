package com.example.tictactoe.service;

import com.example.tictactoe.model.GameState;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final int[][] WIN_PATTERNS = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
        {0, 4, 8}, {2, 4, 6}              // diagonals
    };

    public void makeMove(int index, GameState state) {
        if (state.isGameOver()) return;
        if (index < 0 || index > 8) return;
        if (!state.getBoard()[index].isEmpty()) return;

        state.getBoard()[index] = state.getCurrentPlayer();

        int[] winLine = findWinningLine(state.getBoard());
        if (winLine != null) {
            state.setWinner(state.getCurrentPlayer());
            state.setWinningCells(winLine);
            state.setGameOver(true);
            return;
        }

        if (isBoardFull(state.getBoard())) {
            state.setDraw(true);
            state.setGameOver(true);
            return;
        }

        state.setCurrentPlayer(state.getCurrentPlayer().equals("X") ? "O" : "X");
    }

    private int[] findWinningLine(String[] board) {
        for (int[] pattern : WIN_PATTERNS) {
            String a = board[pattern[0]];
            if (!a.isEmpty() && a.equals(board[pattern[1]]) && a.equals(board[pattern[2]])) {
                return pattern;
            }
        }
        return null;
    }

    private boolean isBoardFull(String[] board) {
        for (String cell : board) {
            if (cell.isEmpty()) return false;
        }
        return true;
    }
}
