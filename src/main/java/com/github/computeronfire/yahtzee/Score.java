package com.github.computeronfire.yahtzee;

/**
 * TODO: requirements should match requirements and include document index number
 *
 * Score.java
 * Class representing a single score field. Contains an integer value and various flags
 * for the different categories of scores.
 *
 * Requirements: Game Functionality, Object Oriented
 */

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
    public void setYB(){
        this.yahtzeeBonus = true;
    }
}