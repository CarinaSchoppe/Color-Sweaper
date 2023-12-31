/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:50 PM All contents of "PlayPauseAction" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.listeners;

import me.carinaschoppe.frontend.GameWindow;
import me.carinaschoppe.frontend.PopUpCreator;
import me.carinaschoppe.game.Game;
import me.carinaschoppe.utility.Utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPauseAction implements ActionListener {

    /**
     * Handles the action performed on the play/pause button.
     * If the game is not running, this method does nothing.
     * If the game is paused, the game will be resumed by starting the timer and enabling setting options.
     * If the game is not paused, the game will be paused by stopping the timer and disabling setting options.
     *
     * @param e the ActionEvent object representing the user's interaction with the play/pause button
     */
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
