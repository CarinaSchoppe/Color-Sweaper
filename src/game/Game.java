package game;

import frontend.DisplayPanel;
import frontend.GameWindow;
import frontend.PopUpCreator;
import logic.Strategies;
import utility.Utility;

import java.util.Objects;

public class Game {
    private Player player1;
    private Player player2;
    private DisplayPanel displayPanel;
    private int unchangedMovesCount;
    private boolean isInitialized;

    private static Game game;

    private boolean gameRunning;
    private boolean gamePaused = true;
    private Player currentPlayer;


    public Game(DisplayPanel displayPanel) {
        this.displayPanel = displayPanel;
        this.unchangedMovesCount = 0;
        game = this;
    }

    public Player getOpponent(Player player) {
        return player == player1 ? player2 : player1;
    }


    public static Game getGame() {
        if (game == null) {
            game = new Game(Utility.getDisplayPanel());
        }
        return game;
    }

    public static void setGame(Game game) {
        Game.game = game;
    }

    public void initialize() {

        if (isInitialized) return;
        isInitialized = true;
        System.out.println(Utility.getDisplayPanel().getRows() + " rowe");
        System.out.println(Utility.getDisplayPanel().getColumns() + " cols");
        System.out.println(Utility.getDisplayPanel().getCellPanels().length + " length col");
        System.out.println(Utility.getDisplayPanel().getCellPanels()[0].length + " length row");
        var topRight = Utility.getDisplayPanel().createComponent(Utility.getDisplayPanel().getRows() - 1, 0);
        var lowLeft = Utility.getDisplayPanel().createComponent(0, Utility.getDisplayPanel().getColumns() - 1);
        var strategy = Strategies.getMatchingName(Objects.requireNonNull(GameWindow.getStrategySelect().getSelectedItem()).toString());
        this.player1 = new Player(lowLeft, "S1");
        this.player2 = new AIPlayer(topRight, "S2", strategy);
        //select a random player as the current player
        player1.setGame(this);
        player2.setGame(this);
        player2.getComponent().tracePath();
        player1.getComponent().tracePath();
        var currentPlayerName = GameWindow.getPlayerSelect().getSelectedItem().toString();
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

    private boolean isGameOver() {
        //Check if the board is in final configuration
        if (displayPanel.isFinalConfiguration()) {
            return true;
        }

        if (displayPanel.noPossibleMove(getOpponent(currentPlayer))) {
            return true;
        }

        return unchangedMovesCount >= 4;
    }


    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void switchCurrentPlayers(Player player) {
        currentPlayer = getOpponent(player);
    }


    public DisplayPanel getDisplayPanel() {
        return displayPanel;
    }

    public void setDisplayPanel(DisplayPanel displayPanel) {
        this.displayPanel = displayPanel;
    }

    public int getUnchangedMovesCount() {
        return unchangedMovesCount;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }

    public void pauseGame() {
        if (game.isGamePaused()) return;

        game.setGamePaused(true);

        PopUpCreator.createPopUp("Game Paused", "Game Paused");
    }


    public void setUnchangedMovesCount(int unchangedMovesCount) {
        this.unchangedMovesCount = unchangedMovesCount;
    }


    public void stopGame() {
        game.setInitialized(false);
        game.setGameRunning(false);
        Game.setGame(null);
    }
}
