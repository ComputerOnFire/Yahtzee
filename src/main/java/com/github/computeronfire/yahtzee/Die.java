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
 * Requirements: Game Functionality, Object Oriented
 *
 */

public class Die {
    private int face;
    private boolean held = false;

    public void rollDie(){
        this.face = new Random().nextInt(6) + 1;
    }
    public void hold(boolean held){
        this.held = held;
    }
    public int getFace(){
        return face;
    }
    public boolean isHeld(){
        return held;
    }
}