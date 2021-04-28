package com.github.computeronfire.yahtzee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    public ScoreCard scoreSetup(int die1, int die2, int die3, int die4, int die5){//sets up a score card for the given dice faces, useful for testing
        Die[] dice = new Die[]{new Die(die1),new Die(die2), new Die(die3), new Die(die4),new Die(die5)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        return sCard;
    }
    @Test
    public void getNameTest(){
        Player player = new Player("Test");
        assertEquals("Test", player.getName());
    }
    @Test
    public void getScoreCardTest(){
        Player player = new Player("Test");
        ScoreCard sCard = scoreSetup(1,2,3,4,5);
        player.updateScoreCard(sCard);
        assertEquals(sCard, player.getScoreCard());
    }
}