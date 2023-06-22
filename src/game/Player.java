package game;


import utility.Component;

import java.awt.*;

public class Player {
    private Component component;
    private Color color;
    
    private Strategy strategy;

    public Player(Component component, Color color, Strategy strategy) {
        this.component = component;
        this.color = color;
        this.strategy = strategy;
    }

    private boolean isValidMove(Color newColor, Player opponent) {
        return !newColor.equals(color) && !newColor.equals(opponent.color);
    }

    private void performMove(Color newColor, Game game) {
        if (!isValidMove(newColor, game.getOpponent(this))) {
            System.out.println("Invalid Move!");
            return;
        }

        this.color = newColor;
        this.component = game.updateComponent(this);
    }


    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
