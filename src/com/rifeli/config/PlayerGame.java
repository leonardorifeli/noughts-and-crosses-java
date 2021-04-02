package com.rifeli.config;

public enum PlayerGame {
    HUMAN(1), COMPUTER(2);

    private final int player;

    PlayerGame(int actualPlayer) {
        player = actualPlayer;
    }

    public int getPlayerGame() {
        return player;
    }

}
