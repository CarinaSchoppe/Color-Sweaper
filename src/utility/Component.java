package utility;

import frontend.CellPanel;

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

    private int size() {
        return cells.size();
    }

    //Checks whether all cells are connected directly or indirectly
    private boolean isContiguous() {
        if (cells.isEmpty()) return true;

        var visited = new HashSet<CellPanel>();

        dfs(cells.get(0), visited);

        return visited.size() == cells.size();
    }

    private void dfs(CellPanel current, Set<CellPanel> visited) {
        visited.add(current);

        for (var cell : cells) {
            if (!visited.contains(cell) && Utility.getDisplayPanel().areAdjacent(current, cell)) {
                dfs(cell, visited);
            }
        }
    }

    public List<CellPanel> getCells() {
        return cells;
    }
}
