package listeners;

import frontend.CellPanel;
import game.AIPlayer;
import game.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectColorAction extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        //clicked on appropiate panel
        var panel = (CellPanel) e.getSource();
        //get the color of the panel
        var color = panel.getBackground();
        //get current Player
        var x = panel.getRow();
        var y = panel.getColumn();
        if (!Game.getGame().isInitialized()) return;

        var currentPlayer = Game.getGame().getCurrentPlayer();
        if (currentPlayer instanceof AIPlayer aiPlayer) {
            currentPlayer.makeMove(x, y);
//            var position = aiPlayer.findPosition();
//            aiPlayer.makeMove(position[0], position[1]);
        } else {
            currentPlayer.makeMove(x, y);
        }
                
    }
}
