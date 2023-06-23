package listeners;

import frontend.GameWindow;
import frontend.PopUpCreator;
import game.Game;
import utility.Utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPauseAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Game.getGame().isGameRunning()) return;

        if (Game.getGame().isGamePaused()) {
            //check if game is initialized
            
            Utility.getTimer().start();

            //Re-enable the setting options
            GameWindow.getPlayerSelect().setEnabled(true);
            GameWindow.getColorSpinner().setEnabled(true);
            GameWindow.getRowSpinner().setEnabled(true);
            GameWindow.getColumnSpinner().setEnabled(true);
            GameWindow.getStrategySelect().setEnabled(true);

            Game.getGame().setGamePaused(false);
            if (Game.getGame().isInitialized()) {
          
                PopUpCreator.createPopUp("Game Resumed", "Game Resumed");
                Game.getGame().getCurrentPlayer().startPlayersTurn();
            }
            Game.getGame().initialize();

            GameWindow.getPlayPauseButton().setText("Pause");
        } else {
            GameWindow.getPlayPauseButton().setText("Play");
            
            Utility.getTimer().stop();
            //Disable the setting options
            GameWindow.getPlayerSelect().setEnabled(false);
            GameWindow.getColorSpinner().setEnabled(false);
            GameWindow.getRowSpinner().setEnabled(false);
            GameWindow.getColumnSpinner().setEnabled(false);
            GameWindow.getStrategySelect().setEnabled(false);
            Game.getGame().pauseGame();
        }


    }


}
