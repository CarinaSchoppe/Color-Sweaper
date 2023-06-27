/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:50 PM All contents of "CellPanel" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.frontend;


import me.carinaschoppe.utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * CellPanel is a JPanel representing a single cell in a grid. It contains information about the cell's location
 * in the grid as well as its color, and can be used to determine adjacent cells.
 */

public class CellPanel extends JPanel {
    /**
     * Represents the row of a matrix or table.
     * <p>
     * The row is used to identify a specific row in a matrix or table.
     * It is represented as an integer value and is used in various
     * operations to manipulate the elements of the row.
     */
    private int row;
    /**
     * Represents an integer value that denotes the column number of a matrix or a table.
     */
    private int column;
    /**
     * Represents a color object.
     * The color variable stores the RGB color values for the color object.
     */
    private Color color;

    /**
     * Constructs a CellPanel object with specified color, row and column.
     *
     * @param color  the color of the cell
     * @param row    the row of the cell in a grid
     * @param column the column of the cell in a grid
     */
    public CellPanel(Color color, int row, int column) {
        this.row = row;
        this.column = column;

        this.color = color;
    }

    /**
     * Returns the row number of the object.
     *
     * @return an int value representing the row number of the object.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the value of the current row index.
     *
     * @param row the row index to be set.
     */
    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    /**
     * Sets the specified column number of the object to the given value.
     *
     * @param column the column number to be set
     */
    public void setColumn(int column) {
        this.column = column;
    }


    /**
     * Retrieves the color of the instance.
     *
     * @return the color of the instance.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color for this object
     *
     * @param color the new color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks whether the given cell panel is adjacent to this cell panel
     *
     * @param panel - the cell panel to check for adjacency
     * @return true if the given panel is adjacent to this panel, false otherwise
     */
    public boolean isAdjacent(CellPanel panel) {
        return (Math.abs(this.row - panel.row) == 1 && this.column == panel.column)
                || (Math.abs(this.column - panel.column) == 1 && this.row == panel.row);
    }


    /**
     * Returns a HashSet of the adjacent cells to the given panel.
     *
     * @param panel the CellPanel for which adjacent cells are needed
     * @return a HashSet containing adjacent cells to the given panel
     */
    public HashSet<CellPanel> getAdjacentCells(CellPanel panel) {
        return new HashSet<>(Utility.getDisplayPanel().getNeighbors(panel));
    }

    /**
     * Returns a string representation of the CellPanel object. The string representation includes row, column and color fields.
     *
     * @return a string containing the row, column and color fields of the CellPanel object.
     */
    @Override
    public String toString() {
        return "CellPanel{" +
                "row=" + row +
                ", column=" + column +
                ", color=" + color +
                '}';
    }
}
