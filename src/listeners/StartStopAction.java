package listeners;

import frontend.GameWindow;
import game.Game;
import utility.Utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartStopAction implements ActionListener {


    /**
     * Handles the action event of the start/stop button in the GUI. If the game is paused,
     * it either resets the board and settings if the game is already running or generates
     * a new random board and disables player and color selection if it is not running.
     *
     * @param e the ActionEvent triggered by the start/stop button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Game.getGame().isGamePaused()) return;

        if (Game.getGame().isGameRunning()) {
            GameWindow.getStartStopButton().setText("Start");
            GameWindow.getGameStatus().setText(" ");
            Utility.getTimer().stop();
            Utility.setTimerSeconds(0);
            GameWindow.getTimerLabel().setText("00:00");
            Utility.getDisplayPanel().clearBoard();
            Game.getGame().stopGame();
            GameWindow.getPlayerSelect().setEnabled(true);
            GameWindow.getColorSpinner().setEnabled(true);
            GameWindow.getRowSpinner().setEnabled(true);
            GameWindow.getColumnSpinner().setEnabled(true);
            GameWindow.getStrategySelect().setEnabled(true);
        } else {
            GameWindow.getPlayerSelect().setEnabled(false);
            GameWindow.getColorSpinner().setEnabled(false);
            GameWindow.getRowSpinner().setEnabled(false);
            GameWindow.getColumnSpinner().setEnabled(false);
            GameWindow.getStrategySelect().setEnabled(false);
            GameWindow.getStartStopButton().setText("Stop");
            GameWindow.getGameStatus().setText(" ");
            Utility.getDisplayPanel().generateRandomBoard();
            Utility.getColorPanel().refreshColors();
            Game.getGame().setGameRunning(true);
        }
    }

}
