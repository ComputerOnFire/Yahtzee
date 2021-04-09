package com.github.computeronfire.yahtzee;

public class Score {
    private int value = 0;
    private boolean yahtzeeBonus = false;
    private boolean retained = false;
    private boolean totalOrBonus = false;
    public void setScore(int value){
        if(!retained){
            this.value = value;
        }
    }
    public int getValue(){
        return value;
    }
    public void retainScore(){
        this.retained = true;
    }
    public boolean isRetained(){
        return retained;
    }
    public void markTotalOrBonus(){//could be refactored, see ScoreCard usages
        this.totalOrBonus = true;
    }
    public boolean isTotalOrBonus(){
        return totalOrBonus;
    }
    public boolean isYB(){
        return yahtzeeBonus;
    }
}
