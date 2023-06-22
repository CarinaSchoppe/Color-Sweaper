package listeners;

import frontend.GameWindow;
import game.Game;
import utility.Utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPauseAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!Game.isGameRunning()) return;
        
        if (Game.isGamePaused()) {
            //Resume game.Game
            GameWindow.getPlayPauseButton().setText("Play");
            Utility.getTimer().start();
            Utility.getDisplayPanel().checkGameOver();

            //Re-enable the setting options
            GameWindow.getPlayerSelect().setEnabled(true);
            GameWindow.getColorSpinner().setEnabled(true);
            GameWindow.getRowSpinner().setEnabled(true);
            GameWindow.getColumnSpinner().setEnabled(true);
            GameWindow.getStrategySelect().setEnabled(true);
            Game.getGame().startGame();
        } else {
            //Pause game.Game
            GameWindow.getPlayPauseButton().setText("Pause");
            Utility.getTimer().stop();
            Utility.getDisplayPanel().checkGameOver();

            //Disable the setting options
            GameWindow.getPlayerSelect().setEnabled(false);
            GameWindow.getColorSpinner().setEnabled(false);
            GameWindow.getRowSpinner().setEnabled(false);
            GameWindow.getColumnSpinner().setEnabled(false);
            GameWindow.getStrategySelect().setEnabled(false);
            Game.getGame().pauseGame();
        }
        Game.setGamePaused(!Game.isGamePaused());;
    }


}
