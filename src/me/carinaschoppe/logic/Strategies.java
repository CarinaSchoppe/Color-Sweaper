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
