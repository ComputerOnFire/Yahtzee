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

    private Map<Integer, Integer> repetition() {//calculates how many times each die repeats
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

    private int mostFrequentDie(int[] dice){//returns value of the most frequently repeating die TODO: will be re-written with repititon map
        Arrays.sort(dice);
        int current = 1;
        int maximum = 1;
        int mostFrequent = dice[0];
        int last = dice[0];

        for (int i = 0; i < dice.length - 1; i++){
            if (dice[0] == last){
                current++;
            }
            else{
                if (current > maximum){
                    mostFrequent = dice[i];
                    maximum = current;
                }
                last = dice[i+1];
                current = 1;
            }
        }
        return mostFrequent;
    }
}