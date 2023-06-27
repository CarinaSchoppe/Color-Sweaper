/*
 * Copyright Notice for NiklasSuperProf Copyright (c) at Carina Sophie Schoppe 2023 File created on 6/27/23, 7:01 PM by Carina The Latest changes made by Carina on 6/27/23, 6:50 PM All contents of "Strategy" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package me.carinaschoppe.logic;

public interface Strategy {
    /**
     * This method implements the Greedy Algorithmic Strategy to solve a problem.
     *
     * @return an array of integers that represent the solution of the problem through the use of the Greedy Algorithmic Strategy.
     */
    int[] greedyStrategy();

  
    /**
     * Computes the stagnation strategy for a given task.
     * The method calculates how long a task can remain inactive before considering it as "stagnant".
     *
     * @return an array of integers representing the time, in seconds, at which a task is considered "stagnant"
     */
    int[] stagnationStrategy();

}
