/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:56 PM All contents of "AIPlayer" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.game;

import me.carinaschoppe.frontend.CellPanel;
import me.carinaschoppe.logic.Strategies;
import me.carinaschoppe.logic.Strategy;
import me.carinaschoppe.utility.Component;
import me.carinaschoppe.utility.Utility;

import java.awt.*;
import java.util.HashMap;

public class AIPlayer extends Player implements Strategy {

    /**
     * This variable represents the selected strategy to be used by the software.
     * The strategy should be set at initialization and remains constant during the
     * execution of the software.
     * <p>
     * The strategy can be used to define different algorithms or approaches to
     * solving a particular problem. By storing the strategy in this variable, it
     * can be easily accessed and used throughout the software.
     * <p>
     * Since the variable is final, it cannot be changed once it has been set, ensuring
     * that the selected strategy remains consistent throughout the execution of the
     * software.
     */
    private final Strategies strategy;

    /**
     * This constructor is used to create a new instance of the AIPlayer class.
     *
     * @param component the component where the game is played.
     * @param name      the name of the AIPlayer.
     * @param strategy  the strategy that the AIPlayer should use to make moves in the game.
     */
    public AIPlayer(Component component, String name, Strategies strategy) {
        super(component, name);
        this.strategy = strategy;
    }


    /**
     * This method pauses for one second before making a move in the game specified by the parameters.
     *
     * @param row    an integer representing the row of the cell where the move will be made.
     * @param column an integer representing the column of the cell where the move will be made.
     */
    @Override
    public void makeMove(int row, int column) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        super.makeMove(row, column);
    }

    /**
     * This method performs a move in a game using different strategies based on the current game state and the available options.
     * The strategy used is determined by the value of the 'strategy' field of the Game object.
     * If the strategy is 'GREEDY', the 'greedyStrategy' method is called to determine the best move.
     * If the strategy is 'BLOCKING', the 'blockingStrategy' method is called to determine the best move.
     * If the strategy is 'STAGNATION', the 'stagnationStrategy' method is called to determine the best move.
     * The chosen move is then used to perform a game move by calling the 'makeMove' method of the Game object.
     */
    public void perform() {
        var positions = switch (strategy) {
            case GREEDY -> greedyStrategy();
            case STAGNATION -> stagnationStrategy();
            default -> throw new IllegalStateException("Unexpected value: " + strategy);
        };
        makeMove(positions[0], positions[1]);
    }


    /**
     * This method implements a greedy strategy in order to determine the best move to play in a game.
     *
     * @return an int array of size 2 containing the row and column values of the cell that has been determined to be the best move.
     */
    @Override
    public int[] greedyStrategy() {
        HashMap<CellPanel, Integer> map = calculateMoves();
        //get the panel with the highest amount
        var max = map.values().stream().max(Integer::compareTo).orElseThrow();
        var panel = map.entrySet().stream().filter(entry -> entry.getValue().equals(max)).findFirst().orElseThrow().getKey();
        return new int[]{panel.getRow(), panel.getColumn()};
    }

    /**
     * Calculates the amount of times a given color appears in a 2D array of CellPanels.
     *
     * @param board A 2D array of CellPanels representing the game board.
     * @param color The Color object to be counted in the game board.
     * @return An integer representing the number of times the given color appears in the game board.
     */
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

    /**
     * Creates a clone of a 2D array of CellPanels and updates cells in the clone to simulate a fake move in a game.
     *
     * @param board A 2D array of CellPanels representing the game board.
     * @param panel A CellPanel object representing the cell that was selected for the fake move.
     * @return A new 2D array of CellPanels where the cells that were part of the fake move have been updated with the
     * color of the selected cell.
     */
    private CellPanel[][] performFakeMove(CellPanel[][] board, CellPanel panel) {
        var boardCopy = board.clone();
        var color = panel.getBackground();
        for (var cell : getComponent().getCells()) {
            boardCopy[cell.getColumn()][cell.getRow()].setBackground(color);
        }
        return boardCopy;
    }

    /**
     * Defines the blocking strategy of an entity implementing the BlockingStrategy interface.
     *
     * @return An integer array representing the blocking strategy.
     */


    /**
     * Implements a stagnation strategy for AI players in a game. The method calculates the moves available to the
     * AI player and chooses the move that leads to the lowest number of available moves for the opponent player.
     *
     * @return An integer array containing the row and column index of the chosen move.
     */
    @Override
    public int[] stagnationStrategy() {
        HashMap<CellPanel, Integer> map = calculateMoves();
        //get the panel with the highest amount
        var min = map.values().stream().min(Integer::compareTo).orElseThrow();
        var panel = map.entrySet().stream().filter(entry -> entry.getValue().equals(min)).findFirst().orElseThrow().getKey();
        return new int[]{panel.getRow(), panel.getColumn()};

    }

    /**
     * Calculates the possible moves for the current component by simulating each adjacent CellPanel being clicked.
     *
     * @return A HashMap containing each adjacent CellPanel as the key and the number of times the color of the chosen component appears
     * on the game board after simulating the move as the value.
     */
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
