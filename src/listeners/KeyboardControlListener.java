package listeners;

import frontend.CellPanel;
import frontend.PopUpCreator;
import game.AIPlayer;
import game.Game;
import utility.Utility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;

public class KeyboardControlListener extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        var number = Integer.parseInt(e.getActionCommand());
        if (!Game.getGame().isInitialized()) return;
        if (number > Utility.getColorPanel().getColorPanels().length) return;

        var panel = Utility.getColorPanel().getColorPanels()[number - 1];
        var color = panel.getBackground();
        var adjCells = new HashSet<CellPanel>();
        var player = Game.getGame().getCurrentPlayer();
        var component = player.getComponent();
        for (var cell : component.getCells()) {
            adjCells.addAll(cell.getAdjacentCells(cell));
        }
        component.getCells().forEach(adjCells::remove);

        //any cell that has the color 
        var cell = adjCells.stream().filter(cellPanel -> cellPanel.getBackground().equals(color)).findFirst().orElse(null);
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
