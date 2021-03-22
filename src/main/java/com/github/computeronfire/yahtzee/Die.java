package com.github.computeronfire.yahtzee;

import java.util.Random;

public class Die {
    private int face;
    private boolean held = false;

    public void rollDie(){
        this.face = new Random().nextInt(6);
    }
    public void hold(boolean held){
        this.held = held;
    }
}
