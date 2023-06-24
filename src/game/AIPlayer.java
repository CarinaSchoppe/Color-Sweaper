package game;

import utility.Component;

import java.awt.*;

public class AIPlayer extends Player {

    private Strategy strategy;

    public AIPlayer(Component component, Color color, String name) {
        super(component, color, name);
    }

    @Override
    public void makeMove(int x, int y) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        super.makeMove(x, y);
    }

    public int[] findPosition() {
        return new int[0];
    }
}
