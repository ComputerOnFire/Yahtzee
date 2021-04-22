package com.github.computeronfire.yahtzee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiceTest {

    @Test
    public void initValueTest() {//tests if dice initialize to a value of 0
        Dice dice = new Dice();
        Die[] diceArray = dice.getDice();
        int[] valueArray = new int[5];//number of dice in a hand
        for(int i = 0; i < valueArray.length; ++i){
            valueArray[i] = diceArray[i].getFace();
        }
        int[] expected = new int[] {0, 0, 0, 0, 0};
        assertArrayEquals(expected, valueArray);
    }

    @Test
    public void diceCountTest() {//tests if dice initializes to 5 dies
        Dice dice = new Dice();
        assertEquals(5, dice.getDice().length);
    }

    @Test
    public void rollDiceTest() {//tests if each die has been rolled and is not 0
        Dice dice = new Dice();
        dice.rollDice();
        for(Die die : dice.getDice()){
            assertNotEquals(0, die.getFace());
        }
    }

    @Test
    public void holdDiceTest() {//tests if each die is not rolled when held
        Dice dice = new Dice();
        for(Die die : dice.getDice()){
            die.hold(true);
        }
        dice.rollDice();
        for(Die die : dice.getDice()){
            assertEquals(0, die.getFace());
        }
    }

    @Test
    public void getDieTest() {//tests if getDie returns a die object
        Dice dice = new Dice();
        for(int i = 0; i < dice.getDice().length; ++i){
            assertNotNull(dice.getDie(i));
        }
    }
}