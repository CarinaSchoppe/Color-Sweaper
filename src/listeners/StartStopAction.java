package listeners;

import frontend.GameWindow;
import game.Game;
import utility.Utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartStopAction implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (Game.isGameRunning()) {
            GameWindow.getStartStopButton().setText("Start");
            GameWindow.getGameStatus().setText(" ");
            Utility.getTimer().stop();
            Utility.setTimerSeconds(0);
            GameWindow.getTimerLabel().setText("00:00");
            Utility.getDisplayPanel().clearBoard();
        } else {
            GameWindow.getStartStopButton().setText("Stop");
            GameWindow.getGameStatus().setText(" ");
            Utility.getColorPanel().refreshColors();
            Utility.getDisplayPanel().generateRandomBoard();

        }
        Game.setGameRunning(!Game.isGameRunning());
    }
    
}
