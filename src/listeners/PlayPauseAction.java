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

            Game.getGame().setGamePaused(false);
            if (Game.getGame().isInitialized()) {
                PopUpCreator.createPopUp("Game Resumed", "Game Resumed");
                Game.getGame().getCurrentPlayer().startPlayersTurn();
            }
            //initializes the game
            Game.getGame().initialize();
            GameWindow.getPlayPauseButton().setText("Pause");
        } else {
            GameWindow.getPlayPauseButton().setText("Play");
            
            Utility.getTimer().stop();
            //Disable the setting options
            Game.getGame().pauseGame();
        }


    }


}
