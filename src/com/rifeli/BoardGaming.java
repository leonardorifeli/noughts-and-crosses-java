package com.rifeli;

import com.rifeli.config.GameStatus;

import java.util.ArrayList;
import java.util.Random;

// BoardGaming it's a class to Board Game orchestration
public class BoardGaming {

    // Define the board with 9 options (I assumed it would be easier than rows and columns). Ex:
    // -----------
    // 1 | 2 | 3 |
    // 4 | 5 | 6 |
    // 7 | 8 | 9 |
    // -----------
    private String gameResult[] = new String[9];
    private GameStatus status = GameStatus.UNFINISHED;
    private int round = 0;

    // Method to round increment
    public void increaseRound() {
        this.round++;
    }

    // Get round number
    public int getRound() {
        return this.round;
    }

    // Set a pre-created board
    public void setGameResultCompleted(String values[]) {
        this.gameResult = values;
    }

    // Set choice to game
    public boolean setGameResult(int choice, String value) {
        boolean isSpaceFree = this.isSpaceFree(choice);

        // Check the choice it's a free space and setting him
        if(isSpaceFree)
            gameResult[choice] = value;

        return isSpaceFree;
    }

    // Return a copy of board game
    public String[] getCopyActualGame() {
        return gameResult.clone();
    }

    // Method to validate the game has winner (by player letter)
    public boolean hasWinner(String option) {
        // checking all lines
        boolean checkLine = (this.checkHasOption(0, option) && this.checkHasOption(1, option) && this.checkHasOption(2, option)) ||
                (this.checkHasOption(3, option) && this.checkHasOption(4, option) && this.checkHasOption(5, option)) ||
                (this.checkHasOption(6, option) && this.checkHasOption(7, option) && this.checkHasOption(8, option));

        // checking all columns
        boolean checkColumn = (this.checkHasOption(0, option) && this.checkHasOption(3, option) && this.checkHasOption(6, option)) ||
                (this.checkHasOption(1, option) && this.checkHasOption(4, option) && this.checkHasOption(7,  option)) ||
                (this.checkHasOption(2, option) && this.checkHasOption(5, option) && this.checkHasOption(8, option));

        // checking the diagonals
        boolean checkDiagonal = (this.checkHasOption(0, option) && this.checkHasOption(4, option) && this.checkHasOption(8, option)) ||
                (this.checkHasOption(2, option) && this.checkHasOption(4, option) && this.checkHasOption(6, option));

        // return true when has a winner by player letter (ex 'X' or 'O')
        return checkDiagonal || checkColumn || checkLine;
    }

    // Method to check the board is full (and doesn't have a winner)
    public boolean isBoardFull() {
        // Check if has a free space
        for(int i=0; i<=8; i++)
            if(isSpaceFree(i))
                return false;

        return true;
    }

    // Checking in choice has a pre-defined player letter
    public boolean checkHasOption(int choice, String option) {
        try {
            return gameResult[choice].equals(option);
        } catch (NullPointerException e) {
            return false;
        }
    }

    // Get a random valid and empty choice to use him
    public int getValidGame(int[] possibles) {
        // Creating empty array list to new possibles
        ArrayList<Integer> newPossibles = new ArrayList<Integer>();

        // Setting default total with 8 (0-8 values)
        int total = 8;

        // Checking the possibles have options to try a valid choice
        if(possibles.length > 0)
            total = possibles.length == 0 ? possibles.length : (possibles.length - 1);

        // Checking has free space into informed values
        for(int i = 0; i <= total; i++) {
            if(this.isSpaceFree(i))
                newPossibles.add(Integer.valueOf(i));
        }

        // If doesn't have possibles return -1 to validate that
        if(newPossibles.size() == 0)
            return -1;

        // Get a random choice based array list with all valid possibles
        return newPossibles.get(new Random().nextInt(newPossibles.size()));
    }

    // Method to print congratulation to winner or bad news to lost player.
    public void congratsWinner(boolean isPlayer, String name) {
        if(isPlayer)
            System.out.printf("\n\nCongratulation %s, you won!\n", name);
        else
            System.out.printf("\n\nOh! %s you lost!\n", name);
    }

    // Method to check the choice is free or empty space
    public boolean isSpaceFree(int choice) {
        try {
            String checkValue = this.gameResult[choice];

            // Checking choice has a informed value and check that information
            if(checkValue.contains("X") || checkValue.contains("O"))
                return false;
        } catch (NullPointerException e) {
            // validating null pointer exception and returning true
            return true;
        }

        return false;
    }

    // Method to print default board;
    public void printBoard() {
        System.out.println("\n-----------");
        System.out.printf("%s | %s | %s |\n", this.getGameValue(0), this.getGameValue(1), this.getGameValue(2));
        System.out.printf("%s | %s | %s |\n", this.getGameValue(3), this.getGameValue(4), this.getGameValue(5));
        System.out.printf("%s | %s | %s |\n", this.getGameValue(6), this.getGameValue(7), this.getGameValue(8));
        System.out.println("-----------");
    }

    // method to check gaming status is unfinished
    public boolean isUnfinished() {
        return this.status == GameStatus.UNFINISHED;
    }

    // Get existing letter into a choice
    private String getGameValue(int choice) {
        if(this.isSpaceFree(choice))
            return String.valueOf(choice + 1);
        return this.gameResult[choice];
    }

}
