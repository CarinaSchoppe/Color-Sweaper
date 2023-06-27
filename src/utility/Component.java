package utility;

import frontend.CellPanel;
import frontend.GameWindow;
import game.Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            if (cell.isAdjacent(panel)) {
                return true;
            }
        }
        return false;
    }


    public Set<CellPanel> adjacentCellsOfComponent() {
        var adjCells = new HashSet<CellPanel>();
        for (var cell : getCells()) {
            adjCells.addAll(cell.getAdjacentCells(cell));
        }
        getCells().forEach(adjCells::remove);

        return adjCells;
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
        
        //update jlabel text:
        GameWindow.getPlayer1Points().setText("Player 1: " + Game.getGame().getPlayer1().getComponent().getCells().size());
        GameWindow.getPlayer2Points().setText("Player 2: " + Game.getGame().getPlayer2().getComponent().getCells().size());
    }


}
