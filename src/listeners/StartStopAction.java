package listeners;

import frontend.GameWindow;
import game.Game;
import utility.Utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartStopAction implements ActionListener {


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
            Game.getGame().setGameRunning(false);

        } else {
            GameWindow.getStartStopButton().setText("Stop");
            GameWindow.getGameStatus().setText(" ");
            Utility.getColorPanel().refreshColors();
            var colors = Utility.getDisplayPanel().generateRandomBoard();
            Game.getGame().getPlayer1().setColor(colors[0]);
            Game.getGame().getPlayer2().setColor(colors[1]);
            Game.getGame().setGameRunning(true);
        }
    }
    
}
