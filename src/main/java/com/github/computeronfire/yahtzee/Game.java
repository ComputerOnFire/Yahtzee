package com.github.computeronfire.yahtzee;

/**
 * Handles calculations for the game, such as scores.
 * Contains an array of int to represent the dice face values.
 * Requirements: Game Functionality, Tally Scores, Follow Yahtzee Rules
 */

public class Game {
    private int[] dice;

    public Game (int[] dice){
        this.dice = dice;
    }
}
