package com.rifeli.config;

public enum GameStatus {
    LOSS(1), VICTORY(2), DEADLOCKED(3), UNFINISHED(4);

    private final int gameStatus;

    GameStatus(int status) {
        gameStatus = status;
    }

    public int getGameStatus() {
        return gameStatus;
    }

}