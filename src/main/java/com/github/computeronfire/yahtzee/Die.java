package com.github.computeronfire.yahtzee;

import java.util.Random;

/**
 * TODO: requirements should match requirements and include document index number
 *
 * Die.java
 * Represents a single die
 * Holds an integer value, representing the face value.
 * Roll function generates a new face value using the java Random library.
 * Contains a boolean flag for if the die is "held" or kept.
 *
 * Requirements: 1.0.0, 1.0.1
 *
 */

public class Die {
    private int face;//represents the face value of the die
    private boolean held = false;//user can hold die to prevent the face value from being re-rolled
    public Die(){
    }
    public Die(int value){
        this.face = value;
    }
    public void rollDie(){//sets the face value to a random number between 1 and 6
        this.face = new Random().nextInt(6) + 1;
    }
    public void hold(boolean held){//changes the state of the hold value
        this.held = held;
    }
    public int getFace(){//returns the face value
        return face;
    }
    public boolean isNotHeld(){//returns the inverse of the held state
        return !held;
    }
}