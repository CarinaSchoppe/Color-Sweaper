package frontend;

import utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class CellPanel extends JPanel {
    private int row;
    private int column;
    private Color color;

    public CellPanel(Color color, int row, int column) {
        this.row = row;
        this.column = column;

        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isAdjacent(CellPanel panel) {
        return (Math.abs(this.row - panel.row) == 1 && this.column == panel.column)
                || (Math.abs(this.column - panel.column) == 1 && this.row == panel.row);
    }


    public HashSet<CellPanel> getAdjacentCells(CellPanel panel) {
        return new HashSet<>(Utility.getDisplayPanel().getNeighbors(panel));
    }

    @Override
    public String toString() {
        return "CellPanel{" +
                "row=" + row +
                ", column=" + column +
                ", color=" + color +
                '}';
    }
}
