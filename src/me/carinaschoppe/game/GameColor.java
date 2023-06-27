package me.carinaschoppe.game;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Represents different colors that can be used in a game with their corresponding numbers.
 */

public enum GameColor {
    RED(1), GREEN(2), BLUE(3), YELLOW(4), BLACK(5), ORANGE(6), PINK(7), MAGENTA(8), CYAN(9);

    private final int number;

    GameColor(int number) {
        this.number = number;
    }

    public static GameColor smallestColor() {
        return Arrays.stream(values()).min(Comparator.comparingInt(GameColor::getNumber)).orElse(RED);
    }

    public int getNumber() {
        return number;
    }
}
