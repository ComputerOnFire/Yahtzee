package com.github.computeronfire.yahtzee;

public class Dice {
    /**
     * Constructs an array of 5 dice.
     * Represents each die on the board.
     */
    public Die[] diceArray = new Die[5];
    public Dice() {
        for (int i = 0; i < diceArray.length; i++) {
            diceArray[i] = new Die();
        }
    }
}
