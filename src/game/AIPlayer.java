package game;

import logic.Strategies;
import logic.Strategy;
import utility.Component;

public class AIPlayer extends Player implements Strategy {

    private Strategies strategy;

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
        var position = switch (strategy) {
            case GREEDY -> greedyStrategy();
            case BLOCKING -> blockingStrategy();
            case STAGNATION -> stagnationStrategy();
        };
        makeMove(position[0], position[1]);
    }


    @Override
    public int[] greedyStrategy() {
        return new int[0];
    }

    @Override
    public int[] blockingStrategy() {
        return new int[0];
    }

    @Override
    public int[] stagnationStrategy() {
        return new int[0];
    }


    public void setStrategy(Strategies strategy) {
        this.strategy = strategy;
    }
}
