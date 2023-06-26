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
import java.util.Set;
import java.util.stream.Collectors;

public class DisplayPanel extends JPanel {
    private CellPanel[][] cellPanels;
    private int rows;
    private int columns;

    public DisplayPanel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        setLayout(new GridLayout(columns, rows));
        setRows(rows);
        setColumns(columns);

    }


    private void updateBoardSize() {
        //DO NOT TOUCH THIS METHOD
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


    public CellPanel[][] getCellPanels() {
        return cellPanels;
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
        cellPanels = new CellPanel[columns][rows];
        for (var column = 0; column < columns; column++) {
            for (var row = 0; row < rows; row++) {
                var panel = new CellPanel(Color.CYAN, row, column);
                cellPanels[column][row] = panel;
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
