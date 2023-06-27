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
