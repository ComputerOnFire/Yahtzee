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

    private int xOfAKind(int x){ //returns the sum of a X of a kind combination of dice, (3 of a kind, 4 of a kind) TODO: implement xOfAKind
        int die = bestRepetition(x);
        if(die != 0){
            return die * x;
        }
        else{
            return die;
        }
    }

    public int[] scoreCard(){
        int[] scores = new int[18];
        for (int i = 1; i < scores.length; ++i){
            if(i <= 6){ //singles for 1-6
                scores[i-1] = faceSum(i);
            }
            else{
                switch(i){//get rest of the scores
                    case 7:
                        //Sum
                        break;
                    case 8:
                        //Bonus
                        break;
                    case 9:
                        //Upper Total
                        break;
                    case 10:
                        //Three of a Kind
                        break;
                    case 11:
                        //Four of a Kind
                        break;
                    case 12:
                        //Small Straight
                        break;
                    case 13:
                        //Large Straight
                        break;
                    case 14:
                        //Yahtzee!
                        break;
                    case 15:
                        //Chance (total of all 5, no conditions)
                        break;
                    case 16:
                        //Yahtzee Bonus (check count only)
                        break;
                    case 17:
                        //Yahtzee Bonus (score)
                        break;
                    case 18:
                        //Lower Total
                        break;
                    case 19:
                        //Grand Total
                        break;
                    default:
                        //should never happen
                        break;
                }
            }
        }
        return scores;
    }
}