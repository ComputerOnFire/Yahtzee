package com.github.computeronfire.yahtzee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCardTest {
    @Test public void fullHouseTest(){//tests if fullHouse returns the correct values given the dice array
        Die[] dice = new Die[]{new Die(2),new Die(2), new Die(3), new Die(3),new Die(3)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(25, sCard.getScore(11).getValue());
    }
    @Test public void notFullHouseTest(){
        Die[] dice = new Die[]{new Die(2),new Die(2), new Die(3), new Die(3),new Die(6)};
        Player testPlayer = new Player("Test");
        ScoreCard sCard = new ScoreCard(testPlayer, dice);
        sCard.calculateScores();
        assertEquals(0, sCard.getScore(11).getValue());
    }

    @Test
    public void smallStraightTest(){
        Dice dice = new Dice();
        Player testPlayer = new Player("Test");

        while(!(dice.getDie(1).getFace() == 1 && dice.getDie(2).getFace() == 2 && dice.getDie(3).getFace() == 3 && dice.getDie(4).getFace() == 4)){
            dice.rollDice();
        }
        ScoreCard sCard = new ScoreCard(testPlayer, dice.getDice());
        sCard.calculateScores();
        assertEquals(15, sCard.getScore(12).getValue());
        assertEquals(0, sCard.getScore(13).getValue());
    }

    @Test
    public void largeStraightTest(){
        Dice dice = new Dice();
        Player testPlayer = new Player("Test");
        dice.rollDice();
        while(!(dice.getDie(0).getFace() == 1 && dice.getDie(1).getFace() == 2 && dice.getDie(2).getFace() == 3 && dice.getDie(3).getFace() == 4 && dice.getDie(4).getFace() == 5)){
            dice.rollDice();
        }
        ScoreCard sCard = new ScoreCard(testPlayer, dice.getDice());
        sCard.calculateScores();
        assertEquals(15, sCard.getScore(12).getValue());
        assertEquals(20, sCard.getScore(13).getValue());
    }

    @Test
    public void calculateStraightTest(){
        Dice dice = new Dice();
        Player testPlayer = new Player("Test");
        dice.rollDice();
        while(!(dice.getDie(0).getFace() == 2 && dice.getDie(1).getFace() == 3 && dice.getDie(2).getFace() == 4 && dice.getDie(3).getFace() == 5 && dice.getDie(4).getFace() == 6)){
            dice.rollDice();
        }
        ScoreCard sCard = new ScoreCard(testPlayer, dice.getDice());
        sCard.calculateScores();
        assertEquals(15, sCard.getScore(12).getValue());
        assertEquals(20, sCard.getScore(13).getValue());
    }
}
