package com.game.game2048.model;

public class GameBoard {

    private int[][] board;
    private int score;
    private boolean gameOver;
    private boolean won;

    public GameBoard() {
        board = new int[4][4];
        score = 0;
        gameOver = false;
        won = false;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}