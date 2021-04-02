package com.rifeli;

import com.rifeli.config.GameDifficulty;

// Implementation of easy computer level (based on Computer class)
public class EasyComputer extends Computer{

    // Method to get a valid game option to computer set into board
    public int getGameOption(BoardGaming boardGaming) {
        // Set valid game to random choice
        int[] values = {};
        return boardGaming.getValidGame(values);
    }

    // Method to return gameDifficulty
    public GameDifficulty getDifficulty() {
        return GameDifficulty.EASY;
    }

}
