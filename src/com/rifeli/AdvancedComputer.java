package com.rifeli;

import com.rifeli.config.GameDifficulty;

// Implementation of advanced computer level (based on Computer class)
public class AdvancedComputer extends Computer {

    // Method to get a valid game option to computer set into board
    public int getGameOption(BoardGaming boardGaming) {
        // Copy board to apply many tests
        String[] boardCopy = boardGaming.getCopyActualGame();

        // Creating empty instance of BoardGaming to set coped board
        BoardGaming boardGamingTest = new BoardGaming();
        boardGamingTest.setGameResultCompleted(boardCopy);

        // Checking if the player can win the game and blocked him
        for(int i = 0; i <= 8; i++)
            if(boardGamingTest.isSpaceFree(i)) {
                boardGamingTest.setGameResult(i, "X");
                if(boardGamingTest.hasWinner("X"))
                    return i;
            }

        // Get a next choice to win the game
        for(int i = 0; i <= 8; i++)
            if(boardGamingTest.isSpaceFree(i)) {
                boardGamingTest.setGameResult(i, this.option);
                if(boardGamingTest.hasWinner(this.option))
                    return i;
            }

        // Instantiating intermediate computer to get center, sides & corner possibles
        IntermediateComputer intermediateComputer = new IntermediateComputer();
        return intermediateComputer.getGameOption(boardGaming);
    }

    // Method to return gameDifficulty
    public GameDifficulty getDifficulty() {
        return GameDifficulty.ADVANCED;
    }
}
