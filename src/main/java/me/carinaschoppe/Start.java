/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:47 PM All contents of "Start" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe;

/*
 * Siehe Hinweise auf dem Aufgabenblatt.
 */

import me.carinaschoppe.frontend.GameWindow;

import javax.swing.*;

public class Start {
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
