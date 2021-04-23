package com.github.computeronfire.yahtzee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCardTest {

    @Test
    public void onesTest(){
        Die[] dice = new Die[]{new Die(1),new Die(2), new Die(1), new Die(4),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(2, sCard.getScore(0).getValue());

    }
    @Test
    public void noOnesTest(){
        Die[] dice = new Die[]{new Die(2),new Die(2), new Die(2), new Die(4),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(0, sCard.getScore(0).getValue());
    }

    @Test
    public void twosTest(){
        Die[] dice = new Die[]{new Die(1),new Die(2), new Die(2), new Die(4),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(4, sCard.getScore(1).getValue());
    }

    @Test
    public void noTwosTest(){
        Die[] dice = new Die[]{new Die(1),new Die(1), new Die(1), new Die(4),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(0, sCard.getScore(1).getValue());
    }

    @Test
    public void threesTest(){
        Die[] dice = new Die[]{new Die(3),new Die(2), new Die(3), new Die(4),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(6, sCard.getScore(2).getValue());
    }
    @Test
    public void noThreesTest(){
        Die[] dice = new Die[]{new Die(4),new Die(2), new Die(5), new Die(4),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(0, sCard.getScore(2).getValue());
    }

    @Test
    public void foursTest(){
        Die[] dice = new Die[]{new Die(1),new Die(2), new Die(4), new Die(4),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(8, sCard.getScore(3).getValue());
    }
    @Test
    public void noFoursTest(){
        Die[] dice = new Die[]{new Die(1),new Die(2), new Die(3), new Die(5),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(0, sCard.getScore(3).getValue());
    }

    @Test
    public void fivesTest(){
        Die[] dice = new Die[]{new Die(1),new Die(2), new Die(1), new Die(5),new Die(5)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(10, sCard.getScore(4).getValue());
    }

    @Test
    public void noFivesTest(){
        Die[] dice = new Die[]{new Die(1),new Die(2), new Die(1), new Die(4),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(0, sCard.getScore(4).getValue());
    }

    @Test
    public void sixesTest(){
        Die[] dice = new Die[]{new Die(6),new Die(2), new Die(1), new Die(4),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(12, sCard.getScore(5).getValue());
    }

    @Test
    public void noSixesTest(){
        Die[] dice = new Die[]{new Die(3),new Die(2), new Die(1), new Die(4),new Die(5)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(0, sCard.getScore(5).getValue());
    }

    @Test
    public void fullHouseTest(){//tests if fullHouse returns the correct values given the dice array
        Die[] dice = new Die[]{new Die(2),new Die(2), new Die(3), new Die(3),new Die(3)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(25, sCard.getScore(11).getValue());
    }
    @Test
    public void notFullHouseTest(){
        Die[] dice = new Die[]{new Die(2),new Die(2), new Die(3), new Die(3),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(0, sCard.getScore(11).getValue());
    }

    @Test
    public void smallStraightTest(){//tests if smallStraight returns the correct values given the dice array
        Die[] dice = new Die[]{new Die(1),new Die(2), new Die(3), new Die(4),new Die(4)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(15, sCard.getScore(12).getValue());
        assertEquals(0, sCard.getScore(13).getValue());
    }
    @Test
    public void notSmallStraightTest(){
        Die[] dice = new Die[]{new Die(1),new Die(2), new Die(3), new Die(3),new Die(3)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(0, sCard.getScore(12).getValue());
        assertEquals(0, sCard.getScore(13).getValue());
    }

    @Test
    public void largeStraightTest(){//tests if largeStraight returns the correct values given the dice array
        Die[] dice = new Die[]{new Die(1),new Die(2), new Die(3), new Die(4),new Die(5)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(15, sCard.getScore(12).getValue());
        assertEquals(20, sCard.getScore(13).getValue());
    }
    @Test
    public void notLargeStraightTest(){
        Die[] dice = new Die[]{new Die(1),new Die(2), new Die(3), new Die(4),new Die(4)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(0, sCard.getScore(13).getValue());
    }
}