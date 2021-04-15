package com.github.computeronfire.yahtzee;

/**
 * TODO: requirements should match requirements and include document index number
 * Player.java
 * Class representing the Player object, contains a Name and ScoreCard.
 *
 * Requirements: Game Functionality, Object Oriented
 */

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
    public void updateScoreCard(ScoreCard scoreCard){
        this.scoreCard = scoreCard;
    }
    public void keepScore(int index){
        scoreCard.getScore(index).retainScore();
    }
}