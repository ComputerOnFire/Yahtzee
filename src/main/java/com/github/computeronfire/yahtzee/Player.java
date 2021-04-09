package com.github.computeronfire.yahtzee;

public class Player {
    private final String name;
    private ScoreCard scoreCard;
    private int yahtzeeBonus = 0;
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
        if(scoreCard.getScore(index).isYB()){
            scoreCard.getScore(index).setScore(scoreCard.getScore(index).getValue() + 1);
        }
        else{
            scoreCard.getScore(index).retainScore();
        }
    }
    public int getYahtzeeBonus(){
        return yahtzeeBonus;
    }
}