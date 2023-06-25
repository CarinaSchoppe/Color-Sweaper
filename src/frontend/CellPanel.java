package frontend;

import javax.swing.*;
import java.awt.*;

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
        var a = Math.abs(this.row - panel.row) == 1;
        System.out.println(a);
        var b = (Math.abs(this.column - panel.column) == 1 && this.row == panel.row);
        System.out.println(b);
        return (Math.abs(this.row - panel.row) == 1 && this.column == panel.column)
                || (Math.abs(this.column - panel.column) == 1 && this.row == panel.row);
    }


}
