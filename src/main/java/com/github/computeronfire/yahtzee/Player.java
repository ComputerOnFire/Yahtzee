package com.github.computeronfire.yahtzee;

/**
 * TODO: requirements should match requirements and include document index number
 * Player.java
 * Class representing the Player object, contains a Name and ScoreCard.
 *
 * Requirements: Game Functionality
 */

public class Player {
    private final String name;//holds the name of the player, input on the main menu
    private ScoreCard scoreCard;//holds the scores of the player
    public Player(String name){
        this.name = name;
        this.scoreCard = new ScoreCard();
    }
    public Player(String name, ScoreCard sCard){
        this.name = name;
        this.scoreCard = sCard;
    }
    public String getName(){//returns the name of the player
        return name;
    }
    public ScoreCard getScoreCard(){//returns the score card of the player
        return scoreCard;
    }
    public void updateScoreCard(ScoreCard scoreCard){//updates the score card of the player
        this.scoreCard = scoreCard;
    }
    public void keepScore(int index){//keeps a given score when the score is selected
        scoreCard.getScore(index).retainScore();
    }
}