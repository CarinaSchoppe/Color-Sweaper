/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:54 PM All contents of "Game" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.game;

import lombok.Getter;
import me.carinaschoppe.frontend.DisplayPanel;
import me.carinaschoppe.frontend.GameWindow;
import me.carinaschoppe.frontend.PopUpCreator;
import me.carinaschoppe.logic.Strategies;
import me.carinaschoppe.utility.Utility;

import java.util.Objects;

public class Game {
    /**
     * The "game" variable holds a reference to an instance of the Game class. It is declared as private
     * and static to limit its visibility to the containing class, and to allow access to it from static
     * methods within the class.
     * <p>
     * The Game class is responsible for managing the game state, running the game loop, and rendering the
     * game graphics. By keeping a reference to a single instance of the Game class in the "game" variable,
     * we can ensure that there is only one active game instance running at any given time.
     * <p>
     * This variable should be used with caution, as it can be easily manipulated by other parts of the code
     * if not properly protected. It is recommended to use the public methods provided by the Game class to
     * interact with the game state and avoid direct manipulation of the "game" variable.
     */
    private static Game game;
    /**
     * Represents the first player of the game.
     * -- GETTER --
     *  Returns an instance of the Player class representing player 1.
     *
     * @return instance of Player representing player 1

     */
    @Getter
    private Player player1;
    /**
     * Represents the second player in a game.
     * -- GETTER --
     *  Returns the player 2 object.
     *
     * @return the player 2 object

     */
    @Getter
    private Player player2;
    /**
     * The display panel used to show the content to the user.
     * -- GETTER --
     *  Retrieves the DisplayPanel object that represents the main UI panel for this application.
     *
     * @return The DisplayPanel object for this application.

     */
    @Getter
    private DisplayPanel displayPanel;
    /**
     * The unchangedMovesCount variable stores the number of consecutive moves that have been made without changing the state of the game board.
     * This variable is used in various strategies that depend on making moves that result in a change in the board state, such as detecting a stuck state.
     * -- GETTER --
     *  Returns the count of unchanged moves.
     *
     * @return the count of unchanged moves

     */
    @Getter
    private int unchangedMovesCount;
    /**
     * Represents the initialization state of a variable.
     * <p>
     * The boolean value of this variable indicates whether an object has been initialized.
     * A value of true indicates that the object has been initialized,
     * while a value of false indicates that the object is still uninitialized.
     */
    private boolean isInitialized;
    /**
     * Represents the current state of the game.
     * If the game is running, this value is set to true; otherwise, it is set to false.
     * -- GETTER --
     *  Returns a boolean indicating whether the game is currently running or not.
     *
     * @return true if the game is running, false otherwise.

     */
    @Getter
    private boolean gameRunning;
    /**
     * Represents whether the game is currently paused or not.
     * If the value is true, the game is paused.
     * If the value is false, the game is currently running.
     * -- GETTER --
     *  This method checks if the game is currently paused or not.
     *
     * @return true if the game is paused, false otherwise

     */
    @Getter
    private boolean gamePaused = true;
    /**
     * Holds the current player of the game.
     * <p>
     * This variable is used to keep track of which player's turn it is in the game.
     * It is of type Player, which represents a player of the game.
     * Once a player takes their turn, this variable is updated to the next player.
     * -- GETTER --
     *  Retrieves the current player object.
     *
     * @return the current player object

     */
    @Getter
    private Player currentPlayer;


    /**
     * Constructs a new Game with the given display panel
     *
     * @param displayPanel the display panel to use for the game
     */
    public Game(DisplayPanel displayPanel) {
        this.displayPanel = displayPanel;
        this.unchangedMovesCount = 0;
        game = this;
    }

    /**
     * Returns an instance of the Game class. If the instance does not exist, a new instance is created.
     *
     * @return an instance of the Game class
     */
    public static Game getGame() {
        if (game == null) {
            game = new Game(Utility.getDisplayPanel());
        }
        return game;
    }

    /**
     * Sets the current game instance to the provided game object
     *
     * @param game an instance of the Game class that represents the current game being played
     */
    public static void setGame(Game game) {
        Game.game = game;
    }

    /**
     * Returns the opponent of the given player
     *
     * @param player the player whose opponent is to be returned
     * @return the opponent of the given player
     */
    public Player getOpponent(Player player) {
        return player == player1 ? player2 : player1;
    }

    /**
     * Initializes a new game with two players and sets the current player to start their turn
     * <p>
     * This method initializes and sets up the two players, sets the strategy for the AI player, creates the player components,
     * and selects a random player to start the game. It also sets the isInitialized variable to true to prevent duplicate
     * initialization.
     */
    public void initialize() {

        if (isInitialized) return;
        isInitialized = true;
        var topRight = Utility.getDisplayPanel().createComponent(Utility.getDisplayPanel().getRows() - 1, 0);
        var lowLeft = Utility.getDisplayPanel().createComponent(0, Utility.getDisplayPanel().getColumns() - 1);
        var strategy = Strategies.getMatchingName(Objects.requireNonNull(GameWindow.getStrategySelect().getSelectedItem()).toString());
        this.player1 = new Player(lowLeft, "Spieler1");
        if (strategy != Strategies.MULTIPLAYER)
            this.player2 = new AIPlayer(topRight, "AI-Spieler2", strategy);
        else
            this.player2 = new Player(topRight, "Spieler2");

        //select a random player as the current player
        player1.setGame(this);
        player2.setGame(this);
        player2.getComponent().tracePath();
        player1.getComponent().tracePath();
        var currentPlayerName = Objects.requireNonNull(GameWindow.getPlayerSelect().getSelectedItem()).toString();
        currentPlayer = currentPlayerName.equals(player1.getName()) ? player1 : player2;
        currentPlayer.startPlayersTurn();
    }


    private Player getWinner() {

        if (player1.getComponent().getCells().size() > player2.getComponent().getCells().size()) {
            return player1;
        } else if (player1.getComponent().getCells().size() < player2.getComponent().getCells().size()) {
            return player2;
        } else {
            return null; //Draw
        }
    }

    /**
     * Checks if the game has ended and creates a PopUp window displaying the winner or a draw message
     *
     * @return a boolean value indicating whether the game has ended or not
     */
    public boolean checkWinning() {
        if (isGameOver()) {
            var winner = getWinner();
            if (winner == null) {
                PopUpCreator.createPopUp("It's a draw!", "Game Over");
            } else {
                PopUpCreator.createPopUp("The winner is " + winner.getName(), "Game Over");
            }
            gameRunning = false;
            return true;
        }
        return false;
    }

    /**
     * Determines if the game is over.
     * <p>
     * This method checks if the game is over by tracing the path of the current player and their opponent, and then checking
     * if the board is in final configuration or if there are no possible moves left for the current player's opponent.
     * Additionally, if the count of unchanged moves is greater than or equal to four, the game is considered over.
     *
     * @return true if the game is over, false otherwise
     */
    private boolean isGameOver() {
        currentPlayer.getComponent().tracePath();
        getOpponent(currentPlayer).getComponent().tracePath();
        //Check if the board is in final configuration
        if (displayPanel.isFinalConfiguration()) {
            return true;
        }
        if (displayPanel.noPossibleMove(getOpponent(currentPlayer))) {
            return true;
        }

        return unchangedMovesCount >= 4;
    }


    /**
     * Sets the first player for the game.
     *
     * @param player1 the Player object representing the first player of the game
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    /**
     * Set the Player 2 object in the game.
     *
     * @param player2 The Player object representing the second player in the game.
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * Switches the current player to his/her opponent
     *
     * @param player the current player to switch from
     */
    public void switchCurrentPlayers(Player player) {
        currentPlayer = getOpponent(player);
    }


    /**
     * Sets the display panel for this object.
     *
     * @param displayPanel The panel to be set as the new display panel.
     */
    public void setDisplayPanel(DisplayPanel displayPanel) {
        this.displayPanel = displayPanel;
    }

    /**
     * Sets the number of unchanged moves made by the player during the game.
     *
     * @param unchangedMovesCount the number of unchanged moves made by the player
     */
    public void setUnchangedMovesCount(int unchangedMovesCount) {
        this.unchangedMovesCount = unchangedMovesCount;
    }

    /**
     * Sets the current player.
     *
     * @param currentPlayer the new current player to be set. Must not be null.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Sets the state of the game to running or not running.
     *
     * @param gameRunning if true, the game is set to running, if false, the game is not running.
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    /**
     * Returns whether the object has been initialized or not.
     *
     * @return {@code true} if the object has been initialized; {@code false} otherwise
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Sets the value of the isInitialized flag.
     *
     * @param initialized the boolean value to be set for the isInitialized flag
     */
    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    /**
     * Sets the paused state of the game.
     *
     * @param gamePaused a boolean value indicating whether the game is paused or not
     */
    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }

    public void pauseGame() {
        if (game.isGamePaused()) return;

        game.setGamePaused(true);

        PopUpCreator.createPopUp("Game Paused", "Game Paused");
    }

    public void stopGame() {
        game.setInitialized(false);
        game.setGameRunning(false);
        Game.setGame(null);
    }
}
