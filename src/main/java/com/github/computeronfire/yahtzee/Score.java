package com.github.computeronfire.yahtzee;

/**
 * TODO: requirements should match requirements and include document index number
 *
 * Score.java
 * Class representing a single score field. Contains an integer value and various flags
 * for the different categories of scores.
 *
 * Requirements: 1.0.0, 1.0.2
 */

public class Score {
    private int value = 0;//number value of the score
    private boolean retained = false;//flag representing of the score is kept by the player
    private boolean totalOrBonus = false;//flag representing if the score represents a total or bonus score
    public Score(){
    }
    public Score(int value){
        this.value = value;
    }
    public void setScore(int value){//sets the value of the score if it is not already retained by the player
        if(!retained){
            this.value = value;
        }
    }
    public int getValue(){//returns the number value of the score
        return value;
    }
    public void retainScore(){//sets the state of the score to retained, keeping it
        this.retained = true;
    }
    public boolean isRetained(){//returns the retained state of the score
        return retained;
    }
    public void markTotalOrBonus(){//sets the score as a total or bonus value
        this.totalOrBonus = true;
    }
    public boolean isNotTotalOrBonus(){//returns the inverse state of the total/bonus flag
        return !totalOrBonus;
    }
}