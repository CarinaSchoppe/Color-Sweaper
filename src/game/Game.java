package game;

import frontend.CellPanel;
import frontend.DisplayPanel;
import utility.Component;

public class Game {
    private Player player1;
    private Player player2;
    private DisplayPanel displayPanel;
    private int unchangedMovesCount;

    
    private static Game game;
    
    

 

    private static boolean gameRunning;
    private static boolean gamePaused;
    private static Player currentPlayer;


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

    public Component updateComponent(Player player) {
        int prevComponentSize = player.getComponent().getCells().size();
        Component component = player.getComponent();

        //TODO Correct?
        for (CellPanel cell : component.getCells()) {
            cell.setColor(player.getColor());
            for (CellPanel neighbor : displayPanel.getNeighbors(cell)) {
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

    public boolean isGameOver() {
        //Check if the board is in final configuration
        if (displayPanel.isFinalConfiguration()) {
            return true;
        }
        if (unchangedMovesCount >= 4) {
            return true;
        }
        return false;
    }

    public Player getWinner() {
        if (player1.getComponent().getCells().size() > player2.getComponent().getCells().size()) {
            return player1;
        } else if (player1.getComponent().getCells().size() < player2.getComponent().getCells().size()) {
            return player2;
        } else {
            return null; //Draw
        }
    }


    public void startGame(){
//TODO: Implement
    }

    public void pauseGame(){
//TODO: Implement

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

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        Game.currentPlayer = currentPlayer;
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


    public static boolean isGamePaused() {
        return gamePaused;
    }
    public static boolean isGameRunning() {
        return gameRunning;
    }

    public static Game getGame() {
        if(game == null)
            game = new Game(null,null,null);
        return game;
    }

    public static void setGameRunning(boolean gameRunning) {
        Game.gameRunning = gameRunning;
    }
    public static void setGamePaused(boolean gamePaused) {
        Game.gamePaused = gamePaused;
    }
    
   

    public void setUnchangedMovesCount(int unchangedMovesCount) {
        this.unchangedMovesCount = unchangedMovesCount;
    }
}
