/*
 * Siehe Hinweise auf dem Aufgabenblatt.
 */

import frontend.GameWindow;
import game.Game;

import javax.swing.*;

public class StartMain {

    public static void main(String[] args) {
        Game.getGame();
        SwingUtilities.invokeLater(GameWindow::createAndShowGUI);

    }

}
