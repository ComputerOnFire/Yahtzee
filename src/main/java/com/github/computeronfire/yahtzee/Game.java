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

    private int chance(){ //return sum of all dice
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

    private Map<Integer, Integer> repetition() {//constructs a Key, Value Map for how many times each die repeats
        Map<Integer, Integer> repetitions = new HashMap<Integer, Integer>();
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

    private int bestOfAKind(int x){//returns the highest value repeating die, given the minimum threshold. Returns 0 if no repeating dice meet the minimum threshold x, used in xOfAKind()
        Map<Integer, Integer> repetitions = repetition();
        int bestRepeatingDie = 0;
        for(Map.Entry<Integer, Integer> die : repetitions.entrySet()) {
            if (die.getValue() >= x){
                if (bestRepeatingDie == 0 || die.getKey() > bestRepeatingDie){
                    bestRepeatingDie = die.getKey();
                }
            }
        }
        return bestRepeatingDie;
    }

    private int xOfAKind(int x){ //returns the sum of a X of a kind combination of dice, (3 of a kind, 4 of a kind), returns 0 if no combination of X is available.
        int die = bestOfAKind(x);
        if(die != 0){
            return die * x;
        }
        else{
            return 0;
        }
    }

    private int smallStraight(){//TODO: test implementation of small Straight
        Map<Integer, Integer> repetitions = repetition();
        for(int die : repetitions.keySet()){
            if(repetitions.get(die) > 2){
                return 0;
            }
        }
        return 15;
    }

    private int largeStraight(){//TODO: implement large Straight
        return 0;
    }

    public int[] scoreCard(){//TODO: decide if switch should be refactored into functions
        int[] scores = new int[19];
        for (int i = 0; i < scores.length; ++i){
            switch (i) {//get rest of the scores
                default: //singles scores for die faces 1-6 (case 0-5)
                    scores[i] = faceSum(i + 1);
                    break;
                case 6:
                    //Sum of all single face scores
                    int sum = 0;
                    for (int j = 0; j < i; ++j) {
                        sum += scores[j];
                    }
                    scores[i] = sum;
                    break;
                case 7:
                    //Bonus score of 35 if sum is over 63
                    if (scores[6] >= 63) {
                        scores[i] = 35;
                    } else {
                        scores[i] = 0;
                    }
                    break;
                case 8:
                    //Upper Total
                    //Sum + Bonus
                    scores[i] = scores[6] + scores[7];
                    break;
                //start of Lower section of scores
                case 9:
                    //Three of a Kind
                    scores[i] = xOfAKind(3);
                    break;
                case 10:
                    //Four of a Kind
                    scores[i] = xOfAKind(4);
                    break;
                case 11:
                    //Full house
                    if (xOfAKind(3) > 0 && xOfAKind(2) > 0) {
                        scores[i] = xOfAKind(3) + xOfAKind(2);
                    } else {
                        scores[i] = 0;
                    }
                    break;
                case 12:
                    //Small Straight
                    scores[i] = smallStraight();
                    break;
                case 13:
                    //Large Straight
                    scores[i] = largeStraight();
                    break;
                case 14:
                    //Yahtzee!
                    if (xOfAKind(5) > 0) {
                        scores[i] = 50;
                    } else {
                        scores[i] = 0;
                    }
                    break;
                case 15:
                    //Chance (total of all 5, no conditions)
                    scores[i] = chance();
                    break;
                case 16://TODO: decide if yahtzee bonus should be removed
                    //Yahtzee Bonus (check count only), might remove
                    scores[i] = 0;
                    break;
                case 17:
                    //Yahtzee Bonus (score), might remove
                    scores[i] = 50 * scores[16];
                    break;
                case 18:
                    //Lower Total
                    int lowerTotal = 0;
                    for (int k = 9; k < i; ++k) {
                        lowerTotal += scores[k];
                    }
                    scores[i] = lowerTotal;
                    break;
                case 19:
                    //Grand Total
                    int grandTotal = 0;
                    for (int l = 0; l < i; ++l) {
                        grandTotal += scores[l];
                    }
                    scores[i] = grandTotal;
                    break;
            }
        }
        return scores;
    }
}