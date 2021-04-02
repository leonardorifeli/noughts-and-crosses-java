package com.rifeli.config;

public class GameDifficultyException extends Exception {

    public GameDifficultyException(int gameDifficultyOption) {
        super("Invalid game difficulty option " + gameDifficultyOption);
    }

}
