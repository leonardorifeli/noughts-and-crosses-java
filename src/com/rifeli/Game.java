package com.rifeli;

import java.util.Scanner;
import com.rifeli.config.GameDifficulty;
import com.rifeli.config.GameDifficultyException;
import com.rifeli.config.PlayerGame;

public class Game {

    public static void main(String[] args) {
        try {
            printMessage("Starting Noughts & Crosses gaming. \n What's your name? \n");

            // Getting player name
            Scanner keyboard = new Scanner(System.in);
            String playerName = keyboard.nextLine();

            // Setting the game difficulty level (using GameDifficulty enum)
            printMessage("%s, what's your game difficulty? \n - 1 = Easy \n - 2 = Intermediate \n - 3 = Advanced \n", playerName);
            GameDifficulty gameDifficulty = getGameDifficulty(keyboard.nextInt());

            // Call startGame method to orchestrate the gaming
            startGame(playerName, gameDifficulty);

            // Restart game when that's finished
            main(args);
        } catch (GameDifficultyException e) {
            // Checking exception to selected an invalid game difficulty
            printMessage("Error to game start with invalid input: %s. Restarting game.\n", e.getMessage());
            main(args);
        } catch (Exception e) {
            // Checking a generic exception and restart the game
            e.printStackTrace();
            printMessage("Error to game start: %s. Restarting game.\n", e.getMessage());
            main(args);
        }
    }

    // Private method to game orchestration
    private static void startGame(String playerName, GameDifficulty gameDifficulty) throws GameDifficultyException {
        // Instancing the player and printing her game letter
        PlayerGame actualPlayer = PlayerGame.HUMAN;
        Player player = new Player(playerName, gameDifficulty);
        printMessage("Your option is 'X' and Computer's 'O'. You see a board game example: \n");

        // Starting the BoardGaming and getting computer instance by difficulty
        BoardGaming boardGaming = new BoardGaming();
        Computer computer = getComputerByDifficulty(gameDifficulty);

        // Whiling when game is unfinished
        while(boardGaming.isUnfinished()) {
            // Printing started board and increase the round
            boardGaming.printBoard();
            boardGaming.increaseRound();

            // Checking the board is full (and no have winners). The game tied.
            if(boardGaming.isBoardFull()) {
                printMessage("\nThe game tied.\n");
                break;
            }

            // Checking the actual player is HUMAN player
            if(actualPlayer == PlayerGame.HUMAN) {
                // Getting player choice
                getPlayerChoice(boardGaming, player);

                // Validating player's a winner
                boolean isPlayerWinner = boardGaming.hasWinner(player.option);
                if(isPlayerWinner) {
                    boardGaming.congratsWinner(true, player.name);
                    boardGaming.printBoard();
                    break;
                }

                // Player's not a winner and set next player is COMPUTER
                actualPlayer = PlayerGame.COMPUTER;
                continue;
            }

            printMessage("Round %d. Computer is gaming. %s, please wait.", boardGaming.getRound(), player.name);

            // Getting computer game option (based on game difficulty)
            int computerOption = computer.getGameOption(boardGaming);

            // Set computer choice
            boardGaming.setGameResult(computerOption, computer.option);

            // Checking computer's a winner
            boolean isComputerWinner = boardGaming.hasWinner(computer.option);
            if(isComputerWinner) {
                boardGaming.congratsWinner(false, player.name);
                boardGaming.printBoard();
                break;
            }

            // Computer's not a winner and set next player is HUMAN
            actualPlayer = PlayerGame.HUMAN;
            continue;
        }
    }

    // Method to get user choice
    private static void getPlayerChoice(BoardGaming boardGaming, Player player) {
        // Getting the player choice
        printMessage("\nRound %d - %s, what's your next move? (1-9)?\n", boardGaming.getRound(), player.name);
        Scanner keyboard = new Scanner(System.in);
        int nextMove = keyboard.nextInt();

        // Setting the player choice to board gaming and validating that
        boolean setGameValue = boardGaming.setGameResult((nextMove - 1), player.option);
        if(!setGameValue) {
            // If not set game option (is because de choice doesnt free)
            printMessage("\nRound %d - %s you choose an invalid column or line. Restarting round.", boardGaming.getRound(), player.name);

            // Restarting the player choice get
            getPlayerChoice(boardGaming, player);
            return;
        }
    }

    private static Computer getComputerByDifficulty(GameDifficulty gameDifficulty) throws GameDifficultyException {
        if(gameDifficulty == GameDifficulty.EASY)
            return new EasyComputer();

        if(gameDifficulty == GameDifficulty.INTERMEDIATE)
            return new IntermediateComputer();

        if(gameDifficulty == GameDifficulty.ADVANCED)
            return new AdvancedComputer();

        // throw exception when's invalid option
        throw new GameDifficultyException(gameDifficulty.getDifficulty());
    }

    // Getting game difficulty enum by int keyboarded by player
    private static GameDifficulty getGameDifficulty(int gameDifficultyOption) throws GameDifficultyException {
        // Return EASY enum when option is 1
        if(gameDifficultyOption == 1)
            return GameDifficulty.EASY;

        // Return INTERMEDIATE enum when option is 2
        if(gameDifficultyOption == 2)
            return GameDifficulty.INTERMEDIATE;

        // Return ADVANCED enum when option is 3
        if(gameDifficultyOption == 3)
            return GameDifficulty.ADVANCED;

        // throw exception when's invalid option
        throw new GameDifficultyException(gameDifficultyOption);
    }

    // Method to abstract the printf option (do not duplicating many code)
    private static void printMessage(String message, Object... args) {
        System.out.printf(message, args);
    }

}