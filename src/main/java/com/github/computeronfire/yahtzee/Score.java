package com.github.computeronfire.yahtzee;

public class Score {
    private int value = 0;
    private boolean retained = false;
    private boolean totalOrBonus = false;
    public void setScore(int value){
        if(!retained){
            this.value = value;
        }
    }
    public int getScore(){
        return value;
    }
    public void retainScore(){
        this.retained = true;
    }
    public boolean isRetained(){
        return retained;
    }
    public void markTotalOrBonus(){
        this.totalOrBonus = true;
    }
}
