package utility;

import frontend.CellPanel;

import java.util.ArrayList;
import java.util.List;

public class Component {
    private final List<CellPanel> cells;

    public Component() {
        this.cells = new ArrayList<>();
    }

    public void addCell(CellPanel cell) {
        cells.add(cell);
    }

    private int size() {
        return cells.size();
    }

    //Checks whether all cells are connected directly or indirectly

    public List<CellPanel> getCells() {
        return cells;
    }

    public boolean isAdjacent(CellPanel panel) {
        //go through all of my components and check if it is adjacent to any of them
        for (var cell : cells) {
            if (cell.isAdjacent(panel)) {
                return true;
            }
        }
        return false;
    }
}
