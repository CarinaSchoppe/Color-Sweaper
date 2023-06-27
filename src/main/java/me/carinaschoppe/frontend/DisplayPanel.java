/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:50 PM All contents of "DisplayPanel" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.frontend;


import lombok.Getter;
import me.carinaschoppe.game.Game;
import me.carinaschoppe.game.Player;
import me.carinaschoppe.listeners.SelectColorAction;
import me.carinaschoppe.utility.Component;
import me.carinaschoppe.utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class DisplayPanel extends JPanel {
    /**
     * A 2D array of CellPanels representing a grid of panels in a graphical user interface.
     * Each CellPanel represents a single panel on the grid.
     * -- GETTER --
     *  Returns a 2D array of CellPanels representing the game board.
     *  Each CellPanel represents a single cell on the board.
     *
     * @return a 2D array of CellPanels

     */
    private CellPanel[][] cellPanels;
    /**
     * This variable represents the number of rows in a given data structure.
     * -- GETTER --
     *  Returns the number of rows in the data set.
     *
     * @return the number of rows in the data set

     */
    private int rows;
    /**
     * The number of columns in a grid or table.
     * -- GETTER --
     *  Retrieves the number of columns in the data structure.
     *
     * @return the number of columns

     */
    private int columns;

    /**
     * Constructs a new DisplayPanel object with a specified number of rows and columns
     *
     * @param rows    - the number of rows in the DisplayPanel
     * @param columns - the number of columns in the DisplayPanel
     * @return void
     */
    public DisplayPanel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        setLayout(new GridLayout(columns, rows));
        setRows(rows);
        setColumns(columns);

    }


    /**
     * Creates a clone of the cellPanels 2D array and its contents
     *
     * @return A new 2D array of CellPanel objects with identical colors, rows, and columns as the original
     */
    public CellPanel[][] cloneCellPanels() {
        //create a clone of that cellPanels and there objects
        CellPanel[][] clone = new CellPanel[columns][rows];
        for (var column = 0; column < columns; column++) {
            for (var row = 0; row < rows; row++) {
                clone[column][row] = new CellPanel(cellPanels[column][row].getColor(), row, column);
            }
        }
        return clone;
    }

    /**
     * Update the size of the board layout and populate it with cell panels.
     *
     * @return void
     */
    private void updateBoardSize() {
        setLayout(new GridLayout(columns, rows));
        removeAll();
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                add(cellPanels[column][row]);
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Returns a unique color for a specified cell panel based on its adjacent cell panels
     *
     * @param row    the row index of the cell panel
     * @param column the column index of the cell panel
     * @return a Color object representing a unique color for the cell panel
     */
    public Color getUniqueColor(int row, int column) {
        Set<Color> adjacentColors = new HashSet<>();
        // Überprüfe die umliegenden Zellen und füge ihre Farben zum Set hinzu
        for (int i = Math.max(0, row - 1); i <= Math.min(this.rows - 1, row + 1); i++) {
            for (int j = Math.max(0, column - 1); j <= Math.min(this.columns - 1, column + 1); j++) {
                if ((i != row || j != column) && cellPanels[j][i] != null) { // Ignoriere die aktuelle Zelle
                    adjacentColors.add(cellPanels[j][i].getBackground());
                }
            }
        }

        // Wähle eine neue Farbe, die nicht im Set ist
        Color newColor;
        do {
            newColor = Utility.randomColor();
        } while (adjacentColors.contains(newColor));

        return newColor;
    }

    /**
     * Generates a new board with unique colors for each cell panel
     *
     * @return void
     */
    public void generateRandomBoard() {
        this.removeAll();
        Color previousColor = null;
        for (var column = 0; column < this.columns; column++) {
            for (var row = 0; row < this.rows; row++) {
                Color cellColor;
                //color the fields in different colors
                //Get matching color
                do {
                    cellColor = getUniqueColor(row, column);
                } while (cellColor.equals(previousColor)); // Generiere eine neue Farbe, solange sie gleich der vorherigen ist


                var cellPanel = new CellPanel(cellColor, row, column);
                cellPanel.addMouseListener(new SelectColorAction());
                cellPanels[column][row] = cellPanel;
                cellPanel.setBackground(cellColor);
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(cellPanel);
                previousColor = cellColor;
            }
        }

        colorEdges();

        revalidate();
        repaint();
    }


    /**
     * Assign unique colors to the top right and bottom left cell panels
     * making sure that they have different colors
     *
     * @return void
     */
    private void colorEdges() {
        var topRight = cellPanels[0][rows - 1];
        var bottomLeft = cellPanels[columns - 1][0];

        //make sure these both have different colors
        while (topRight.getBackground().equals(bottomLeft.getBackground())) {
            var color = getUniqueColor(0, columns - 1);
            bottomLeft.setBackground(color);
            bottomLeft.setColor(color);
        }


    }

    /**
     * Creates a Component object with the specified row and column.
     *
     * @param row    An integer representing the row number of the cell panel.
     * @param column An integer representing the column number of the cell panel.
     * @return A Component object with the specified row and column.
     */
    public Component createComponent(int row, int column) {
        var component = new Component();
        component.addCell(cellPanels[column][row]);
        return component;
    }

    /**
     * Clears the game board of all background colors and borders.
     * Goes through every cell in the game board and sets their background
     * color and border to null. This resets the game board for a new game.
     */
    public void clearBoard() {
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                cellPanels[column][row].setBackground(null);
                cellPanels[column][row].setBorder(null);
            }
        }
    }


    /**
     * Returns a list of adjacent cell panels to the given cell panel.
     *
     * @param cell a CellPanel object representing the anchor cell.
     * @return an ArrayList of CellPanel objects representing adjacent cells.
     */
    public List<CellPanel> getNeighbors(CellPanel cell) {
        var neighbors = new ArrayList<CellPanel>();
        var up = cell.getColumn() - 1;
        var down = cell.getColumn() + 1;
        var left = cell.getRow() - 1;
        var right = cell.getRow() + 1;
        if (up >= 0)
            neighbors.add(cellPanels[up][cell.getRow()]);
        if (down < columns)
            neighbors.add(cellPanels[down][cell.getRow()]);
        if (left >= 0)
            neighbors.add(cellPanels[cell.getColumn()][left]);
        if (right < rows)
            neighbors.add(cellPanels[cell.getColumn()][right]);

        return neighbors;
    }

    //Check uf there is a path between start and end panel


    /**
     * This method checks if all cells in the game board have been assigned a color. If not,  it returns false, otherwise true.
     *
     * @return a boolean value that represents if all cells have been assigned a color or not.
     */
    public boolean isFinalConfiguration() {
        //iterate over all Cell panels
        for (var column = 0; column < rows; column++) {
            for (var row = 0; row < rows; row++) {
                var cell = cellPanels[column][row];
                //check if the cell is eighter the color of player1 or player2 if not return false
                if (cell.getBackground() != Game.getGame().getPlayer1().getColor() && cell.getBackground() != Game.getGame().getPlayer2().getColor()) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Sets the number of rows of the cell panels in the grid layout and calls the method for
     * creating the cell panels.
     *
     * @param rows the number of rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
        cellPanelsCreation(columns, rows);
    }

    /**
     * Sets the number of columns of the grid and updates the layout of the cell panels accordingly.
     *
     * @param columns the new number of columns in the grid.
     */
    public void setColumns(int columns) {
        this.columns = columns;
        cellPanelsCreation(columns, rows);
    }

    /**
     * Creates the cell panels for the board.
     *
     * @param columns the number of columns of the board
     * @param rows    the number of rows of the board
     */
    private void cellPanelsCreation(int columns, int rows) {
        cellPanels = new CellPanel[columns][rows];
        for (var column = 0; column < columns; column++) {
            for (var row = 0; row < rows; row++) {
                var panel = new CellPanel(Color.CYAN, row, column);
                cellPanels[column][row] = panel;
            }
        }
        updateBoardSize();
    }


    /**
     * Checks
     */
    //check if there is any possible move for the player
    public boolean noPossibleMove(Player player) {
        for (var cell : player.getComponent().adjacentCellsOfComponent()) {
            if (player.validateMove(cell))
                return false;
        }
        return true;
    }
}
