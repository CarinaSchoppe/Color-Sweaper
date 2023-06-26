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
        //get current Player
        var row = panel.getRow();
        var col = panel.getColumn();
        var color = panel.getColor();
        if (!Game.getGame().isInitialized()) return;

        System.out.println("row: " + row + " col: " + col);
        System.out.println("Color: " + color);

        var currentPlayer = Game.getGame().getCurrentPlayer();
        if (currentPlayer instanceof AIPlayer aiPlayer) {
            currentPlayer.makeMove(row, col);
//            var position = aiPlayer.findPosition();
//            aiPlayer.makeMove(position[0], position[1]);
        } else {
            currentPlayer.makeMove(row, col);
        }
                
    }
}
