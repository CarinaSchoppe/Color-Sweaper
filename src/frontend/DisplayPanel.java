package frontend;

import game.Game;
import game.Player;
import listeners.SelectColorAction;
import utility.Component;
import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayPanel extends JPanel {
    private CellPanel[][] cellPanels;
    private int rows;
    private int columns;

    public DisplayPanel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        setLayout(new GridLayout(rows, columns));
        setRows(rows);
        setColumns(columns);

    }

    private void updateBoardSize() {
        setLayout(new GridLayout(rows, columns));
        removeAll();
        for (int row = 0; row < rows; row++) {
            for (int cols = 0; cols < columns; cols++) {
                cellPanels[cols][row].setBackground(Color.CYAN);
                add(cellPanels[cols][row]);
            }
        }
        revalidate();
        repaint();
    }


    public void generateRandomBoard() {
        this.removeAll();
        for (var row = 0; row < this.rows; row++) {
            for (var column = 0; column < this.columns; column++) {
                Color cellColor;
                //color the fields in different colors
                do {
                    cellColor = Utility.randomColor();
                } while ((row > 0 && cellColor.equals(cellPanels[column][row - 1].getBackground()))
                        || (column > 0 && cellColor.equals(cellPanels[column - 1][row].getBackground())));

                var cellPanel = new CellPanel(cellColor, row, column);
                cellPanel.addMouseListener(new SelectColorAction());
                cellPanels[column][row] = cellPanel;
                cellPanel.setBackground(cellColor);

                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(cellPanel);
            }
        }

        // If the bottom-left and top-right squares have the same color, change the color of the bottom-left square
        while (cellPanels[columns - 1][0].getBackground().equals(cellPanels[0][rows - 1].getBackground())) {
            Color newColor;
            do {
                newColor = Utility.randomColor();
            } while (newColor.equals(cellPanels[columns - 1][0].getBackground()));

            cellPanels[0][rows - 1].setBackground(newColor);
            cellPanels[0][rows - 1].repaint();
        }
        //korrekten farben!
        revalidate();
        repaint();
    }

    public Component createComponent(int row, int column) {
        var component = new Component();
        component.addCell(cellPanels[column][row]);
        return component;
    }

    public void clearBoard() {
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                cellPanels[column][row].setBackground(null);
                cellPanels[column][row].setBorder(null);
            }
        }
    }


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


    public boolean isFinalConfiguration() {
        //iterate over all Cell panels
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < columns; j++) {
                var cell = cellPanels[i][j];
                //check if the cell is eighter the color of player1 or player2 if not return false
                if (cell.getBackground() != Game.getGame().getPlayer1().getColor() && cell.getBackground() != Game.getGame().getPlayer2().getColor()) {
                    return false;
                }
            }
        }
        return true;
    }


    public CellPanel[][] getCellPanels() {
        return cellPanels;
    }


    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
        cellPanelsCreation(columns, rows);
        System.out.println(getRows() + " rows1");
        System.out.println(getColumns() + " cols1");
        System.out.println(getCellPanels().length + " length col1");
        System.out.println(getCellPanels()[0].length + " length row1");
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
        cellPanelsCreation(columns, rows);
    }

    private void cellPanelsCreation(int columns, int rows) {
        cellPanels = new CellPanel[columns][rows];
        for (var i = 0; i < columns; i++) {
            for (var j = 0; j < rows; j++) {
                var panel = new CellPanel(Color.CYAN, i, j);
                cellPanels[i][j] = panel;
            }
        }
        updateBoardSize();
    }


    //check if there is any possible move for the player
    public boolean noPossibleMove(Player player) {
        var adjacentCells = new HashSet<CellPanel>();
        for (var cell : player.getComponent().getCells()) {
            var neighbors = getNeighbors(cell).stream().filter(cellPanel -> cellPanel.getBackground() != player.getColor() && cellPanel.getBackground() != Game.getGame().getOpponent(player).getColor()).collect(Collectors.toSet());
            adjacentCells.addAll(neighbors);
        }
        return adjacentCells.isEmpty();
    }
}
