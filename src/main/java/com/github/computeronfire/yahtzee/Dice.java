package com.github.computeronfire.yahtzee;

/**
 * TODO: requirements should match requirements and include document index number
 *
 * Dice.java
 * Contains an array representing a set of 5 Dice
 * Roll function rolls each die that is not held.
 *
 * Requirements: Game Functionality
 *
 */

public class Dice {
    /**
     * Constructs an array of 5 dice.
     * Represents each die on the board.
     */
    private final Die[] dice = new Die[5];//array of 5 dice
    public Dice() {
        for (int i = 0; i < dice.length; ++i) {
            this.dice[i] = new Die();//fills the array with die objects
        }
    }
    public Die[] getDice(){//returns the dice array
        return dice;
    }
    public Die getDie(int index){//gets a die at a given index
        return dice[index];
    }
    public void rollDice(){//rolls each die, if it is not held
        for (Die die: dice){
            if(die.isNotHeld()){
                die.rollDie();
            }
        }
    }
}