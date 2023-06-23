package game;

import frontend.DisplayPanel;
import frontend.GameWindow;
import frontend.PopUpCreator;
import utility.Component;
import utility.Utility;

public class Game {
    private Player player1;
    private Player player2;
    private DisplayPanel displayPanel;
    private int unchangedMovesCount;

    private static Game game;

    private boolean gameRunning;
    private boolean gamePaused = true;
    private Player currentPlayer;


    public Game(Player player1, Player player2, DisplayPanel displayPanel) {
        this.player1 = player1;
        this.player2 = player2;
        this.displayPanel = displayPanel;
        this.unchangedMovesCount = 0;
        game = this;
    }

    public Player getOpponent(Player player) {
        return player == player1 ? player2 : player1;
    }
    private boolean isInitialized;

    public static Game getGame() {
        if (game == null) {
            game = new Game(new Player(Utility.getDisplayPanel().getComponent(Utility.getDisplayPanel().getColumns() - 1, 0), Utility.getDisplayPanel().getCellPanels()[Utility.getDisplayPanel().getColumns() - 1][0].getColor(), "S1"), new AIPlayer(Utility.getDisplayPanel().getComponent(0, Utility.getDisplayPanel().getRows() - 1), Utility.getDisplayPanel().getCellPanels()[0][Utility.getDisplayPanel().getRows() - 1].getColor(), "S2"), Utility.getDisplayPanel());
            System.out.println("new game initialized");
        }
        return game;
    }

    public static void setGame(Game game) {
        Game.game = game;
    }

    public void initialize() {
        if (isInitialized) return;
        isInitialized = true;
        //select a random player as the current player
        player1.setGame(this);
        player2.setGame(this);
        var currentPlayerName = GameWindow.getPlayerSelect().getSelectedItem().toString();
        currentPlayer = currentPlayerName.equals(player1.getName()) ? player1 : player2;
        currentPlayer.startPlayersTurn();
    }

    //TODO: Make it better!
    public Component updateComponent(Player player) {
        var prevComponentSize = player.getComponent().getCells().size();
        var component = player.getComponent();

        //TODO Correct?
        for (var cell : component.getCells()) {
            cell.setColor(player.getColor());
            for (var neighbor : displayPanel.getNeighbors(cell)) {
                if (neighbor.getColor() == player.getColor() && !component.getCells().contains(neighbor)) {
                    component.getCells().add(neighbor);
                }

            }
        }
        //Check if the component size has changed
        if (player.getComponent().getCells().size() == prevComponentSize) {
            unchangedMovesCount++;
        } else {
            unchangedMovesCount = 0;
        }
        return component;
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
        Game.setGame(null);
    }
}
