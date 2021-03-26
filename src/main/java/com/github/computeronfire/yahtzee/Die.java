package com.github.computeronfire.yahtzee;

import java.util.Random;

public class Die {
    public int face;
    public boolean held = false;

    public void rollDie(){
        this.face = new Random().nextInt(6) + 1;
    }
    public void hold(boolean held){
        this.held = held;
    }
}