/*
 * Siehe Hinweise auf dem Aufgabenblatt.
 */

import frontend.GameWindow;

import javax.swing.*;

public class StartMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::createAndShowGUI);

        //TODO: KI -> Greedy & stagnation -> clone the board calculate all adjecent cells and perform a clone move. calculate the gain and choose the best move.
    }

}
