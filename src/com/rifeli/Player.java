package com.rifeli;

import com.rifeli.config.GameDifficulty;

public class Player {
    String name;
    GameDifficulty gameDifficulty;
    String option = "X";

    public Player(String name, GameDifficulty gameDifficulty) {
        this.name = name;
        this.gameDifficulty = gameDifficulty;
    }

    public void info() {
        System.out.println(this.name);
        System.out.println(this.gameDifficulty);
    }

}
