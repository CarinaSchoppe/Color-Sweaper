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

    public Color[] generateRandomBoard() {
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
        //korrekten farben!
        revalidate();
        repaint();

        return new Color[]{cellPanels[columns - 1][0].getBackground(), cellPanels[0][rows - 1].getBackground()};
    }

    public Component createComponent(int row, int column) {
        var component = new Component();
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


    public List<CellPanel> getNeighbors(CellPanel cell) {
        var neighbors = new ArrayList<CellPanel>();
        var up = cell.getRow() - 1;
        var down = cell.getRow() + 1;
        var left = cell.getColumn() - 1;
        var right = cell.getColumn() + 1;
        if (up >= 0)
            neighbors.add(cellPanels[up][cell.getColumn()]);
        if (down < rows)
            neighbors.add(cellPanels[down][cell.getColumn()]);
        if (left >= 0)
            neighbors.add(cellPanels[cell.getRow()][left]);
        if (right < columns)
            neighbors.add(cellPanels[cell.getRow()][right]);

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


    //check if there is any possible move for the player
    public boolean noPossibleMove(Player player) {
        var adjecentCells = new HashSet<CellPanel>();
        for (var cell : player.getComponent().getCells()) {
            var neighbors = getNeighbors(cell).stream().filter(cellPanel -> cellPanel.getBackground() != player.getColor() && cellPanel.getBackground() != Game.getGame().getOpponent(player).getColor()).collect(Collectors.toSet());
            adjecentCells.addAll(neighbors);
        }
        return adjecentCells.size() == 0;
    }
}
