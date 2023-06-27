/*
 * Siehe Hinweise auf dem Aufgabenblatt.
 */

import frontend.GameWindow;

import javax.swing.*;

public class StartMain {
    //TODO: KI
    //TODO: TESTS


    /**
     * Executes the main method of the program. Initializes the GameWindow GUI using SwingUtilities.invokeLater
     * and passes the GameWindow::createAndShowGUI method as a lambda expression to create and display the window.
     * This method does not take any parameters and does not return anything.
     *
     * @param args an array of command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::createAndShowGUI);


    }

}
