package logic;

public enum Strategies {

    STAGNATION("strategy01"),
    GREEDY("strategy02"),
    BLOCKING("strategy03");

    private final String name;

    Strategies(String name) {
        this.name = name;
    }

    public static Strategies getMatchingName(String name) {
        for (var strategy : Strategies.values()) {
            if (strategy.getName().equals(name)) {
                return strategy;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
