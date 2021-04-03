package com.github.computeronfire.yahtzee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles logic and calculations for the game, such as scores.
 * Contains an array of int to represent the dice face values.
 * Requirements: Game Functionality, Tally Scores, Follow Yahtzee Rules
 */

public class Game {
    private int[] dice;
    /**
     * Constructs an array of dice values (int 1-6) that will be used to calculate the scores.
     * */
    public Game (int[] dice){
        this.dice = dice;
    }
}