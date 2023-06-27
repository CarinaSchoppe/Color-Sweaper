/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:49 PM All contents of "MoveValidator" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.logic;


import me.carinaschoppe.frontend.CellPanel;

public interface MoveValidator {


    /**
     * This method is used to validate a move for a given CellPanel object.
     *
     * @param panel the CellPanel object to validate move for
     * @return true if the move is valid, false otherwise
     */
    boolean validateMove(CellPanel panel);

}
