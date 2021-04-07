package com.github.computeronfire.yahtzee;

public class Dice {
    /**
     * Constructs an array of 5 dice.
     * Represents each die on the board.
     */
    private Die[] dice = new Die[5];
    public Dice() {
        for (int i = 0; i < dice.length; ++i) {
            this.dice[i] = new Die();
        }
    }
    public Die[] getDice(){
        return dice;
    }
    public Die getDie(int index){
        return dice[index];
    }
    public void rollDice(){
        for (Die die: dice){
            if(!die.isHeld()){
                die.rollDie();
            }
        }
    }
}