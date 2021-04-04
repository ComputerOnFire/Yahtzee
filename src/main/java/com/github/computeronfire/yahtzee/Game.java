package com.github.computeronfire.yahtzee;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles logic and calculations for the game, such as scores.
 * Contains an array of int to represent the dice face values.
 * Requirements: Game Functionality, Tally Scores, Follow Yahtzee Rules
 */

public class Game {
    private int[] dice;
    /**
     * Constructs an array of dice values (int 1-6) that will be used to calculate the scores.
     * */
    public Game (int[] dice){
        this.dice = dice;
    }

    private int sum(){ //return sum of all dice
        int sum = 0;
        for (int face : dice){
            sum += face;
        }
        return sum;
    }

    private int faceSum(int value){//return sum of a all dice with certain face value
        int sum = 0;
        for (int face : dice){
            if (face == value){
                sum += face;
            }
        }
        return sum;
    }

    private int xOfAKind(int x){ //returns the sum of a X of a kind combination of dice, (3 of a kind, 4 of a kind) TODO: implement xOfAKind
        return x;
    }

    private Map<Integer, Integer> repetition() {//constructs a map for how many times each die repeats
        Map<Integer, Integer> repetitions = new HashMap<Integer, Integer>;
        for (int die : dice){
            Integer repetition = repetitions.get(die);
            if(repetition == null){
                repetitions.put(die, 1);
            }
            else{
                repetitions.put(die, repetition + 1);
            }
        }
        return repetitions;
    }

    private int bestRepetition(int threshold){//returns the highest value repeating die, given the minimum threshold. Returns 0 if no repeating dice meet the minimum threshold
        Map<Integer, Integer> frequency = repetition();
        int mostFrequent = 0;
        for(Map.Entry<Integer, Integer> die : frequency.entrySet()) {
            if (die.getValue() >= threshold){
                if (mostFrequent == 0 || die.getKey() > mostFrequent){
                    mostFrequent = die.getKey();
                }
            }
        }
        return mostFrequent;
    }
}