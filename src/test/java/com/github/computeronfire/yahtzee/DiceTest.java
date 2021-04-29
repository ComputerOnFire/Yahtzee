package com.github.computeronfire.yahtzee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DiceTest {
    @Test
    public void initValueTest() {//tests if the dice face values initialize to 0
        Dice dice = new Dice();
        for (Die die : dice.getDice()){
            assertEquals(0, die.getFace());
        }
    }
    @Test
    public void initNotHeldTest() {//tests if the dice are initialized without the held state
        Dice dice = new Dice();
        for (Die die : dice.getDice()){
            assertEquals(true, die.isNotHeld());
        }
    }
    @Test
    public void holdTest(){//tests if the dice held state can be modified
        Dice dice = new Dice();
        dice.getDie(1).hold(true);
        assertEquals(false,dice.getDie(1).isNotHeld());
    }
    @Test
    public void rollTest(){//tests if the dice values have been changed
        Dice dice = new Dice();
        dice.rollDice();
        for (Die die : dice.getDice()){
            assertNotEquals(0, die.getFace());
        }
    }
    @Test
    public void rollHeldTest(){//tests if the dice values have been not changed if they are held
        Dice dice = new Dice();
        dice.getDie(0).hold(true);
        dice.rollDice();
        assertEquals(0,dice.getDie(0).getFace());
    }
}