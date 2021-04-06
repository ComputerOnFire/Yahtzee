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
        int mostFrequentDie = 0;
        for(Map.Entry<Integer, Integer> die : repetitions.entrySet()) {
            if (die.getValue() >= x){
                if (mostFrequentDie == 0 || die.getKey() > mostFrequentDie){
                    mostFrequentDie = die.getKey();
                }
            }
        }
        return mostFrequentDie;
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

    private int smallStraight(){//TODO: implement small Straight
        return 0;
    }

    private int largeStraight(){//TODO: implement large Straight
        return 0;
    }

    public int[] scoreCard(){
        int[] scores = new int[19];
        for (int i = 0; i < scores.length; ++i){
            if(i <= 5){ //singles for 1-6
                scores[i] = faceSum(i+1);
            }
            else{
                switch(i){//get rest of the scores
                    case 6:
                        //Sum of all single face scores
                        int sum = 0;
                        for (int j = 0; j < 6; ++j){
                            sum += scores[j];
                        }
                        scores[i] = sum;
                        break;
                    case 7:
                        //Bonus score of 35 if sum is over 63
                        if (scores[6] >= 63){
                            scores[i] = 35;
                        }
                        else{
                            scores[i] = 0;
                        }
                        break;
                    case 8:
                        //Upper Total
                        //Sum + Bonus
                        scores[i] = scores[6] + scores[7];
                        break;
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
                        break;
                    case 12:
                        if(xOfAKind(3) > 0 && xOfAKind(2) > 0){
                            scores[i] = xOfAKind(3) + xOfAKind(2);
                        }
                        else{
                            scores[i] = 0;
                        }
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
                        scores[i] = chance();
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