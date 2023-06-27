/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:45 PM All contents of "PopUpCreator" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.frontend;

public class PopUpCreator {


    /**
     * Creates a swing window with the provided message and title and displays it as a pop-up.
     *
     * @param message the message to display in the pop-up window. Must be a String.
     * @param title   the title to display in the pop-up window. Must be a String.
     */
    public static void createPopUp(String message, String title) {
        //Create a swing window with a message and a title and display it
        var frame = new javax.swing.JFrame();
        javax.swing.JOptionPane.showMessageDialog(frame, message, title, javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
}
