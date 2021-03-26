package com.github.computeronfire.yahtzee;

public class Dice {
    /**
     * Constructs an array of 5 dice.
     * Represents each die on the board.
     */
    public Die[] dice = new Die[5];
    public Dice() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }
    }
}
