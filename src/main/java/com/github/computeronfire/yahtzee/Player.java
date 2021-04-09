package com.github.computeronfire.yahtzee;

public class Player {
    private final String name;
    private ScoreCard scoreCard;
    public Player(String name){
        this.name = name;
        this.scoreCard = new ScoreCard();
    }
    public String getName(){
        return name;
    }
    public ScoreCard getScoreCard(){
        return scoreCard;
    }
    public void keepScore(int index){
        scoreCard.getScore(index).retainScore();
    }
}