package com.github.computeronfire.yahtzee;

public class Score {
    private int value = 0;
    private boolean retained = false;
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
    public void releaseScore(){//only used for updating repeated Yahtzee Bonus scores (checkboxes)
        this.retained = false;
    }
    public boolean isRetained(){
        return retained;
    }
}
