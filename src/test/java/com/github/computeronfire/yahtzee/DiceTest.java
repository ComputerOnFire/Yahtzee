package com.github.computeronfire.yahtzee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiceTest {
    @Test
    public void initValueTest() {//tests if the die face value initializes to 0
        Dice dice = new Dice();
        for (Die die : dice.getDice()){
            assertEquals(0, die.getFace());
        }
    }
}
