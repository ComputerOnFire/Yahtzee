package com.github.computeronfire.yahtzee;

import java.util.Random;

public class Die {
    private int face;
    private boolean held = false;

    public void rollDie(){
        this.face = new Random().nextInt(6);
    }
}
