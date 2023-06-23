package frontend;

import game.Game;
import listeners.SelectColorAction;
import utility.Component;
import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayPanel extends JPanel {
    private CellPanel[][] cellPanels;
    private int rows;
    private int columns;
    private CellPanel start;
    private CellPanel end;

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
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cellPanels[i][j].setBackground(Color.CYAN);
                add(cellPanels[i][j]);
            }
        }
        revalidate();
        repaint();
    }

    public void generateRandomBoard() {
        this.removeAll();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                boolean isStartingField = (i == 0 && j == 0) || (i == rows - 1 && j == columns - 1);
                Color cellColor;
                //color the fields in different colors
                do {
                    cellColor = Utility.randomColor();
                } while ((i > 0 && cellColor.equals(cellPanels[i - 1][j].getBackground()))
                        || (j > 0 && cellColor.equals(cellPanels[i][j - 1].getBackground())));

                var cellPanel = new CellPanel(cellColor, i, j, isStartingField);
                cellPanel.addMouseListener(new SelectColorAction());
                cellPanels[i][j] = cellPanel;
                cellPanels[i][j].setBackground(cellColor);
                cellPanels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(cellPanels[i][j]);
            }
        }

        // If the bottom-left and top-right squares have the same color, change the color of the bottom-left square
        while (cellPanels[0][columns - 1].getBackground().equals(cellPanels[rows - 1][0].getBackground())) {
            Color newColor;
            do {
                newColor = Utility.randomColor();
            } while (newColor.equals(cellPanels[0][columns - 1].getBackground()));

            cellPanels[rows - 1][0].setBackground(newColor);
            cellPanels[rows - 1][0].repaint();
        }

        revalidate();
        repaint();
    }

    public Component getComponent(int row, int column) {
        Component component = new Component();
        component.addCell(cellPanels[row][column]);
        return component;
    }

    public void clearBoard() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                cellPanels[i][j].setBackground(null);
                cellPanels[i][j].setBorder(null);
            }
        }
    }

    //Check if cells are neighbors
    public boolean areAdjacent(CellPanel c1, CellPanel c2) {
        return Math.abs(c1.getColumn() - c2.getColumn()) + Math.abs(c1.getColumn() - c2.getColumn()) == 1;
    }

    public List<CellPanel> getNeighbors(CellPanel cell) {
        var neighbors = new ArrayList<CellPanel>();

        var dr = new int[]{-1, 0, 1, 0};
        var dc = new int[]{0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            var newRow = cell.getRow() + dr[i];
            var newColumn = cell.getColumn() + dc[i];

            if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns) {
                var neighbor = cellPanels[newRow][newColumn];
                if (areAdjacent(cell, neighbor)) {
                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    //Check uf there is a path between start and end panel
    private boolean isPath() {
        start = cellPanels[0][columns - 1];
        var component = getComponent(start.getRow(), start.getColumn());
        end = cellPanels[rows - 1][0];

        //Check if cell is in the component of the start cell
        return component.getCells().contains(end);

    }

    //TODO
    private boolean graphSearch(CellPanel current, Component component, boolean[][] visited) {
        return false;
    }

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

    void colorBoard(Color color) {
        //Logic to recolor board
    }


    public CellPanel[][] getCellPanels() {
        return cellPanels;
    }

    public void setCellPanels(CellPanel[][] cellPanels) {
        this.cellPanels = cellPanels;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
        cellPanelsCreation(columns, rows);
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
        cellPanelsCreation(columns, rows);
    }

    private void cellPanelsCreation(int columns, int rows) {
        cellPanels = new CellPanel[rows][columns];
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < columns; j++) {
                var panel = new CellPanel(Color.CYAN, i, j, false);
                cellPanels[i][j] = panel;
                //register actionListener

            }
        }
        updateBoardSize();
    }

    public CellPanel getStart() {
        return start;
    }

    public void setStart(CellPanel start) {
        this.start = start;
    }

    public CellPanel getEnd() {
        return end;
    }

    public void setEnd(CellPanel end) {
        this.end = end;
    }


}
