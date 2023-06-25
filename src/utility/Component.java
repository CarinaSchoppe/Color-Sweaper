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


    //Checks whether all cells are connected directly or indirectly

    public List<CellPanel> getCells() {
        return cells;
    }

    public boolean isAdjacent(CellPanel panel) {
        //go through all of my components and check if it is adjacent to any of them
        for (var cell : cells) {
            System.out.println("checking cell");
            System.out.println("coords of cell:" + cell.getRow() + " " + cell.getColumn());
            System.out.println("coords of panel:" + panel.getRow() + " " + panel.getColumn());
            if (cell.isAdjacent(panel)) {
                return true;
            }
        }
        return false;
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
    }
}
