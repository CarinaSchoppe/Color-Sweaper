/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:56 PM All contents of "Strategies" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.logic;

public enum Strategies {

    STAGNATION("Stagnation"),
    GREEDY("Greedy"),

    MULTIPLAYER("Multiplayer");

    private final String name;

    Strategies(String name) {
        this.name = name;
    }

    public static Strategies getMatchingName(String name) {
        for (var strategy : Strategies.values()) {
            if (strategy.getName().equalsIgnoreCase(name)) {
                return strategy;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
