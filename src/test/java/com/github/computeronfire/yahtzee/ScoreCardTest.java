package com.github.computeronfire.yahtzee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCardTest {

    private ScoreCard scoreSetup(int die1, int die2, int die3, int die4, int die5){//sets up a score card for the given dice faces, useful for testing
        Die[] dice = new Die[]{new Die(die1),new Die(die2), new Die(die3), new Die(die4),new Die(die5)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        return sCard;
    }

    @Test
    public void onesTest(){
        ScoreCard sCard = scoreSetup(1,2,1,4,6);
        assertEquals(2, sCard.getScore(0).getValue());
    }
    @Test
    public void noOnesTest(){
        ScoreCard sCard = scoreSetup(2,2,2,4,6);
        assertEquals(0, sCard.getScore(0).getValue());
    }

    @Test
    public void twosTest(){
        ScoreCard sCard = scoreSetup(1,2,2,4,6);
        assertEquals(4, sCard.getScore(1).getValue());
    }

    @Test
    public void noTwosTest(){
        ScoreCard sCard = scoreSetup(1,1,1,4,6);
        assertEquals(0, sCard.getScore(1).getValue());
    }

    @Test
    public void threesTest(){
        ScoreCard sCard = scoreSetup(3,2,3,4,6);
        assertEquals(6, sCard.getScore(2).getValue());
    }
    @Test
    public void noThreesTest(){
        ScoreCard sCard = scoreSetup(4,2,5,4,6);
        assertEquals(0, sCard.getScore(2).getValue());
    }

    @Test
    public void foursTest(){
        ScoreCard sCard = scoreSetup(1,2,4,4,6);
        assertEquals(8, sCard.getScore(3).getValue());
    }
    @Test
    public void noFoursTest(){
        ScoreCard sCard = scoreSetup(1,2,3,5,6);
        assertEquals(0, sCard.getScore(3).getValue());
    }

    @Test
    public void fivesTest(){
        ScoreCard sCard = scoreSetup(1,2,1,5,5);
        assertEquals(10, sCard.getScore(4).getValue());
    }

    @Test
    public void noFivesTest(){
        ScoreCard sCard = scoreSetup(1,2,1,4,6);
        assertEquals(0, sCard.getScore(4).getValue());
    }

    @Test
    public void sixesTest(){
        ScoreCard sCard = scoreSetup(6,2,1,4,6);
        assertEquals(12, sCard.getScore(5).getValue());
    }

    @Test
    public void noSixesTest(){
        ScoreCard sCard = scoreSetup(1,2,1,4,1);
        assertEquals(0, sCard.getScore(5).getValue());
    }
    @Test
    public void threeOfAKindTest(){
        ScoreCard sCard = scoreSetup(1,1,1,4,4);
        assertEquals(0, sCard.getScore(9).getValue());
    }
    @Test
    public void fourOfAKindTest(){
        ScoreCard sCard = scoreSetup(1,1,1,1,4);
        assertEquals(0, sCard.getScore(10).getValue());
    }
    @Test
    public void fullHouseTest(){//tests if fullHouse returns the correct values given the dice array
        ScoreCard sCard = scoreSetup(2,2,3,3,3);
        assertEquals(25, sCard.getScore(11).getValue());
    }
    @Test
    public void notFullHouseTest(){
        ScoreCard sCard = scoreSetup(2,2,3,3,6);
        assertEquals(0, sCard.getScore(11).getValue());
    }

    @Test
    public void smallStraightTest(){//tests if smallStraight returns the correct values given the dice array
        ScoreCard sCard = scoreSetup(1,2,3,4,4);
        assertEquals(30, sCard.getScore(12).getValue());
        assertEquals(0, sCard.getScore(13).getValue());
    }
    @Test
    public void notSmallStraightTest(){
        ScoreCard sCard = scoreSetup(1,2,3,3,3);
        assertEquals(0, sCard.getScore(12).getValue());
        assertEquals(0, sCard.getScore(13).getValue());
    }

    @Test
    public void largeStraightTest(){//tests if largeStraight returns the correct values given the dice array
        ScoreCard sCard = scoreSetup(1,2,3,4,5);
        assertEquals(30, sCard.getScore(12).getValue());
        assertEquals(40, sCard.getScore(13).getValue());
    }
    @Test
    public void notLargeStraightTest(){
        ScoreCard sCard = scoreSetup(1,2,3,4,4);
        assertEquals(0, sCard.getScore(13).getValue());
    }
}