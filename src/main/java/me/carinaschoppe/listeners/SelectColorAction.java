/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:50 PM All contents of "SelectColorAction" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.listeners;

import me.carinaschoppe.frontend.CellPanel;
import me.carinaschoppe.game.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectColorAction extends MouseAdapter {

    /**
     * Responds to a mouse click event on the given CellPanel, registering a move made by the current player
     *
     * @param e the MouseEvent triggered by clicking on the CellPanel
     */
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

        var currentPlayer = Game.getGame().getCurrentPlayer();
        currentPlayer.makeMove(row, col);


    }
}
