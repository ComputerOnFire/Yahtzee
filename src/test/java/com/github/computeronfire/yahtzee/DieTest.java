package com.github.computeronfire.yahtzee;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DieTest {
    @Test
    public void initValueTest() {//tests if the die face value initializes to 0
        Die die = new Die();
        assertEquals(0, die.getFace());
    }

    @Test
    public void initNotHeldTest() {//tests if the die is initialized without the held state
        Die die = new Die();
        assertEquals(true, die.isNotHeld());
    }

    @Test
    public void holdTest(){//tests if the die's held state can be modified
        Die die = new Die();
        die.hold(true);
        assertEquals(false,die.isNotHeld());
    }

    @Test
    public void rollTest() {//tests the die roll function
        Die die = new Die();
        die.rollDie();
        assertNotEquals(0,die.getFace());
    }
}