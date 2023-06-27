package me.carinaschoppe.logic;

public interface Strategy {
    /**
     * This method implements the Greedy Algorithmic Strategy to solve a problem.
     *
     * @return an array of integers that represent the solution of the problem through the use of the Greedy Algorithmic Strategy.
     */
    int[] greedyStrategy();

    /**
     * Determines the blocking strategy to be used in a particular scenario.
     * This method returns an array of integers representing the strategy.
     * Element at index 0 represents the duration of blocking.
     * Element at index 1 represents the number of attempts for blocking.
     *
     * @return An array of integers representing the blocking strategy, where the
     * first element represents the duration of blocking, and the second
     * element represents the number of attempts for blocking.
     */
    int[] blockingStrategy();

    /**
     * Computes the stagnation strategy for a given task.
     * The method calculates how long a task can remain inactive before considering it as "stagnant".
     *
     * @return an array of integers representing the time, in seconds, at which a task is considered "stagnant"
     */
    int[] stagnationStrategy();

}
