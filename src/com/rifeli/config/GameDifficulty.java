package com.rifeli.config;

public enum GameDifficulty {
    EASY(1), INTERMEDIATE(2), ADVANCED(3);

    private final int difficulty;

    GameDifficulty(int gameDifficulty) {
        difficulty = gameDifficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

}