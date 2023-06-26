package listeners;

import frontend.PopUpCreator;
import game.AIPlayer;
import game.Game;
import utility.Utility;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyboardControlListener extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        var number = Integer.parseInt(e.getActionCommand());
        if (!Game.getGame().isInitialized()) return;
        if (number > Utility.getColorPanel().getColorPanels().length) return;

        var panel = Utility.getColorPanel().getColorPanels()[number - 1];
        var color = panel.getBackground();
        var player = Game.getGame().getCurrentPlayer();
        var component = player.getComponent();

        //any cell that has the color 
        var cell = component.adjacentCellsOfComponent().stream().filter(cellPanel -> cellPanel.getBackground().equals(color)).findFirst().orElse(null);
        if (cell == null) {
            PopUpCreator.createPopUp("No possible color for that key!", "Invalid Move");
            return;
        }
        var row = cell.getRow();
        var col = cell.getColumn();
        if (player instanceof AIPlayer) return;
        player.makeMove(row, col);

    }
}
