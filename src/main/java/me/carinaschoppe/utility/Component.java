/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:50 PM All contents of "Component" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.utility;

import lombok.Getter;
import me.carinaschoppe.frontend.CellPanel;
import me.carinaschoppe.frontend.GameWindow;
import me.carinaschoppe.game.Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Component {
    /**
     * This is a private instance variable consisting of a List of CellPanel objects.
     * CellPanel is a user-defined class representing a square cell in a grid pattern.
     * The 'final' keyword signifies that the reference variable 'cells' can only be assigned once
     * i.e., it is a constant and can't be changed after initialization.
     * <p>
     * This variable can be accessed only within the class where it is declared.
     * <p>
     * Example usage:
     * List<CellPanel> cells = new ArrayList<>();
     * cells.add(new CellPanel()); // Add a new cell to the list
     *
     *
     * -- GETTER --
     *  Returns a List of CellPanel objects that represent individual cells in a game board
     *
     @see CellPanel
      * @return List<CellPanel> - a List of instances of the CellPanel class that represents individual cells on the game board
     */
    private final List<CellPanel> cells;

    /**
     * Initializes a new instance of the Component class.
     *
     * <p>
     * This method creates a new Component object with an empty ArrayList of cells.
     * </p>
     */
    public Component() {
        this.cells = new ArrayList<>();
    }

    public void addCell(CellPanel cell) {
        cells.add(cell);
    }


    //Checks whether all cells are connected directly or indirectly

    /**
     * Checks if the given CellPanel is adjacent to any of the components in this CellPanel.
     *
     * @param panel the CellPanel to check adjacency against
     * @return true if any of the components in this CellPanel are adjacent to the given CellPanel, false otherwise
     */
    public boolean isAdjacent(CellPanel panel) {
        //go through all of my components and check if it is adjacent to any of them
        for (var cell : cells) {
            if (cell.isAdjacent(panel)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns a set of CellPanels that are adjacent to the component.
     *
     * @return a set of adjacent CellPanels
     */
    public Set<CellPanel> adjacentCellsOfComponent() {
        var adjCells = new HashSet<CellPanel>();
        for (var cell : getCells()) {
            adjCells.addAll(cell.getAdjacentCells(cell));
        }
        getCells().forEach(adjCells::remove);

        return adjCells;
    }

    //should add all cells that are not already part of the component and that are adjacent to the component to the component when it has the same color 
    public void tracePath() {
        boolean newCell;
        do {
            newCell = false;
            var newCells = new ArrayList<CellPanel>();
            for (var cell : cells) {
                var neighbors = Utility.getDisplayPanel().getNeighbors(cell);
                for (var neighbor : neighbors) {
                    if (!cells.contains(neighbor) && neighbor.getColor() == cell.getColor()) {
                        newCells.add(neighbor);
                        newCell = true;
                    }
                }
            }
            cells.addAll(newCells);
        } while (newCell);

        //update jlabel text:
        GameWindow.getPlayer1Points().setText("Player 1: " + Game.getGame().getPlayer1().getComponent().getCells().size());
        GameWindow.getPlayer2Points().setText("Player 2: " + Game.getGame().getPlayer2().getComponent().getCells().size());
    }


}
