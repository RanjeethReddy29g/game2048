package com.game.game2048.service;

import com.game.game2048.model.GameBoard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private GameBoard gameBoard;

    public GameService() {
        newGame();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public GameBoard newGame() {

        gameBoard = new GameBoard();

        addRandomTile();
        addRandomTile();

        return gameBoard;
    }

    public GameBoard move(String direction) {

        boolean moved = false;

        switch (direction.toUpperCase()) {
            case "LEFT":
                moved = moveLeft();
                break;
            case "RIGHT":
                moved = moveRight();
                break;
            case "UP":
                moved = moveUp();
                break;
            case "DOWN":
                moved = moveDown();
                break;
        }

        if (moved) {
            addRandomTile();
        }

        checkWin();
        checkGameOver();

        return gameBoard;
    }

    private boolean moveLeft() {

        boolean changed = false;
        int[][] board = gameBoard.getBoard();

        for (int row = 0; row < 4; row++) {

            List<Integer> values = new ArrayList<>();

            for (int col = 0; col < 4; col++) {
                if (board[row][col] != 0) {
                    values.add(board[row][col]);
                }
            }

            for (int i = 0; i < values.size() - 1; i++) {

                if (values.get(i).equals(values.get(i + 1))) {

                    int merged = values.get(i) * 2;

                    values.set(i, merged);
                    values.remove(i + 1);

                    gameBoard.setScore(
                            gameBoard.getScore() + merged
                    );
                }
            }

            int[] newRow = new int[4];

            for (int i = 0; i < values.size(); i++) {
                newRow[i] = values.get(i);
            }

            for (int col = 0; col < 4; col++) {

                if (board[row][col] != newRow[col]) {
                    changed = true;
                }

                board[row][col] = newRow[col];
            }
        }

        return changed;
    }

    private boolean moveRight() {

        rotate180();
        boolean changed = moveLeft();
        rotate180();

        return changed;
    }

    private boolean moveUp() {

        rotateLeft();
        boolean changed = moveLeft();
        rotateRight();

        return changed;
    }

    private boolean moveDown() {

        rotateRight();
        boolean changed = moveLeft();
        rotateLeft();

        return changed;
    }

    private void rotateLeft() {

        int[][] board = gameBoard.getBoard();
        int[][] temp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[3 - j][i] = board[i][j];
            }
        }

        gameBoard.setBoard(temp);
    }

    private void rotateRight() {

        int[][] board = gameBoard.getBoard();
        int[][] temp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[j][3 - i] = board[i][j];
            }
        }

        gameBoard.setBoard(temp);
    }

    private void rotate180() {

        rotateLeft();
        rotateLeft();
    }

    private void addRandomTile() {

        int[][] board = gameBoard.getBoard();

        List<int[]> emptyCells = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (board[i][j] == 0) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }

        if (emptyCells.isEmpty()) {
            return;
        }

        int[] cell =
                emptyCells.get(
                        new Random().nextInt(emptyCells.size())
                );

        board[cell[0]][cell[1]] =
                Math.random() < 0.9 ? 2 : 4;
    }

    private void checkWin() {

        int[][] board = gameBoard.getBoard();

        for (int[] row : board) {
            for (int cell : row) {

                if (cell == 2048) {
                    gameBoard.setWon(true);
                    return;
                }
            }
        }
    }

    private void checkGameOver() {

        int[][] board = gameBoard.getBoard();

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {

                if (board[row][col] == 0) {
                    gameBoard.setGameOver(false);
                    return;
                }

                if (row < 3 &&
                        board[row][col] == board[row + 1][col]) {
                    gameBoard.setGameOver(false);
                    return;
                }

                if (col < 3 &&
                        board[row][col] == board[row][col + 1]) {
                    gameBoard.setGameOver(false);
                    return;
                }
            }
        }

        gameBoard.setGameOver(true);
    }
}