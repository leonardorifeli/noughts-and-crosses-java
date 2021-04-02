package com.rifeli;

import com.rifeli.config.GameDifficulty;

// Abstract class named computer
public abstract class Computer {

    // Set computer display letter to board
    String option = "O";

    // Define required methods
    public abstract int getGameOption(BoardGaming boardGaming);
    public abstract GameDifficulty getDifficulty();

}
