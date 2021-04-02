package com.rifeli;

import com.rifeli.config.GameDifficulty;

// Implementation of intermediate computer level (based on Computer class)
public class IntermediateComputer extends Computer {

    // Method to get a valid game option to computer set into board
    public int getGameOption(BoardGaming boardGaming) {
        // Try set choice on center;
        if(boardGaming.isSpaceFree(4))
            return 4;

        // Try to set choice on corners
        int[] cornerPossibles = {0,2,6,8};
        int moveCorner = boardGaming.getValidGame(cornerPossibles);
        if(moveCorner > -1)
            return moveCorner;

        // Try to set choice on sides
        int[] sidePossibles = {1,3,5,7};
        int moveSide = boardGaming.getValidGame(sidePossibles);
        if(moveSide > -1)
            return moveSide;

        // Set valid game to random choice
        int[] defaultValues = {};
        return boardGaming.getValidGame(defaultValues);
    }

    // Method to return gameDifficulty
    public GameDifficulty getDifficulty() {
        return GameDifficulty.INTERMEDIATE;
    }

}
