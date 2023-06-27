package game;

import frontend.CellPanel;
import logic.Strategies;
import logic.Strategy;
import utility.Component;
import utility.Utility;

import java.awt.*;
import java.util.HashMap;

public class AIPlayer extends Player implements Strategy {

    private final Strategies strategy;

    public AIPlayer(Component component, String name, Strategies strategy) {
        super(component, name);
        this.strategy = strategy;
    }


    @Override
    public void makeMove(int row, int column) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        super.makeMove(row, column);
    }

    public void perform() {
        var positions = switch (strategy) {
            case GREEDY -> greedyStrategy();
            case BLOCKING -> blockingStrategy();
            case STAGNATION -> stagnationStrategy();
        };
        makeMove(positions[0], positions[1]);
    }


    @Override
    public int[] greedyStrategy() {
        HashMap<CellPanel, Integer> map = calculateMoves();
        //get the panel with the highest amount
        var max = map.values().stream().max(Integer::compareTo).orElseThrow();
        var panel = map.entrySet().stream().filter(entry -> entry.getValue().equals(max)).findFirst().orElseThrow().getKey();
        return new int[]{panel.getRow(), panel.getColumn()};
    }

    private int calculateColorAmount(CellPanel[][] board, Color color) {
        var amount = 0;
        for (var row = 0; row < board.length; row++) {
            for (var col = 0; col < board[row].length; col++) {
                if (board[col][row].getBackground().equals(color)) {
                    amount++;
                }
            }
        }
        return amount;
    }

    private CellPanel[][] performFakeMove(CellPanel[][] board, CellPanel panel) {
        var boardCopy = board.clone();
        var color = panel.getBackground();
        for (var cell : getComponent().getCells()) {
            boardCopy[cell.getColumn()][cell.getRow()].setBackground(color);
        }
        return boardCopy;
    }

    @Override
    public int[] blockingStrategy() {
        return new int[0];
    }

    @Override
    public int[] stagnationStrategy() {
        HashMap<CellPanel, Integer> map = calculateMoves();
        //get the panel with the highest amount
        var min = map.values().stream().min(Integer::compareTo).orElseThrow();
        var panel = map.entrySet().stream().filter(entry -> entry.getValue().equals(min)).findFirst().orElseThrow().getKey();
        return new int[]{panel.getRow(), panel.getColumn()};

    }

    private HashMap<CellPanel, Integer> calculateMoves() {
        var boardCopy = Utility.getDisplayPanel().cloneCellPanels();
        var map = new HashMap<CellPanel, Integer>();
        for (var adjacentPanel : getComponent().adjacentCellsOfComponent()) {
            var panelOnBoard = boardCopy[adjacentPanel.getColumn()][adjacentPanel.getRow()];
            if (!validateMove(panelOnBoard)) continue;
            var fakeBoard = performFakeMove(boardCopy, panelOnBoard);
            var amount = calculateColorAmount(fakeBoard, panelOnBoard.getBackground());
            map.put(panelOnBoard, amount);
        }
        return map;
    }


}
