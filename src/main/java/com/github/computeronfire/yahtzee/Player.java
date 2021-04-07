package com.github.computeronfire.yahtzee;

public class Player {
    private final String name;
    private Score[] scoreCard = new Score[20];
    public Player(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public Score[] getScores(){
        return scoreCard;
    }
    public void selectScore(Score score, int index){
        scoreCard[index] = score;
    }
}