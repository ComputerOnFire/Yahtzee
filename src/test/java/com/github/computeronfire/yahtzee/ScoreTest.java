package com.github.computeronfire.yahtzee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {
    @Test
    public void scoreTest(){//tests if the score set is correctly returned
        Score score = new Score();
        score.setScore(1);
        assertEquals(1, score.getValue());
    }
    @Test
    public void retainTest(){//tests if the score set is correctly returned
        Score score = new Score();
        score.retainScore();
        assertEquals(true, score.isRetained());
    }
    @Test
    public void totalOrBonusTest(){//tests if the score set is correctly returned
        Score score = new Score();
        score.markTotalOrBonus();
        assertEquals(false, score.isNotTotalOrBonus());
    }
}
