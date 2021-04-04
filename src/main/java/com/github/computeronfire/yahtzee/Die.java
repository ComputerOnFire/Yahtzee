package com.github.computeronfire.yahtzee;

import java.util.Random;

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