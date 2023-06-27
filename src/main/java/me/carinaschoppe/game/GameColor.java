/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:32 PM All contents of "GameColor" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.game;

import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Represents different colors that can be used in a game with their corresponding numbers.
 */

@Getter
public enum GameColor {
    RED(1), GREEN(2), BLUE(3), YELLOW(4), BLACK(5), ORANGE(6), PINK(7), MAGENTA(8), CYAN(9);

    private final int number;

    GameColor(int number) {
        this.number = number;
    }

    public static GameColor smallestColor() {
        return Arrays.stream(values()).min(Comparator.comparingInt(GameColor::getNumber)).orElse(RED);
    }

}
