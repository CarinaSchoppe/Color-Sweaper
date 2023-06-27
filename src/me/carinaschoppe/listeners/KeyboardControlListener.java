/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:50 PM All contents of "KeyboardControlListener" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.listeners;

import me.carinaschoppe.frontend.PopUpCreator;
import me.carinaschoppe.game.AIPlayer;
import me.carinaschoppe.game.Game;
import me.carinaschoppe.utility.Utility;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyboardControlListener extends AbstractAction {

    /**
     * Method to handle the action performed by the user.
     * This method is called automatically when a certain action is performed by the user,
     * and the corresponding event is triggered.
     *
     * @param e Action event occurred
     */
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
