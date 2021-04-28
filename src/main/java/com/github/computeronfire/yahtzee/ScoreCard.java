package com.github.computeronfire.yahtzee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: requirements should match requirements and include document index number
 *
 * ScoreCard.java
 * Object that each Player holds, representing their scores.
 * Handles logic and calculations for the game, such as scores.
 * Contains an array of int to represent the dice face values to run calculations on.
 *
 * Requirements: Game Functionality, Follow Yahtzee Rules
 */

public class ScoreCard {
    private Die[] dice;//represents the dice, which will be passed from the game board
    private Score[] scores = new Score[18]; //must be size of 20

    public ScoreCard(){//construct the array of score objects
        for (int i = 0; i < this.scores.length; ++i){
            this.scores[i] = new Score();
        }
    }
    public ScoreCard(Player player, Die[] dice){//updates the scores and dice
        this.scores = player.getScoreCard().scores;
        this.dice = dice;
    }
    public Score getScore(int index){//returns the score at a given position
        return scores[index];
    }
    public Score[] getScores(){//returns the full array of scores
        return scores;
    }

    private int chance(){ //return sum of all dice
        int sum = 0;
        for (Die die : dice){
            sum += die.getFace();
        }
        return sum;
    }

    private int faceSum(int value){//return sum of a all dice with certain face value
        int sum = 0;
        for (Die die : dice){
            if (die.getFace() == value){
                sum += die.getFace();
            }
        }
        return sum;
    }

    private Map<Integer, Integer> repetition() {//constructs a Key, Value map for how many times each die repeats
        Map<Integer, Integer> repetitions = new HashMap<>();
        for (Die die : dice){
            repetitions.merge(die.getFace(), 1, Integer::sum);
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
    private int fullHouse(){//checks for a full house (3 of a kind + 2 of a kind), then returns the score of 25
        Map<Integer, Integer> repetitions = repetition();
        boolean pair = false;

        for (Integer die : repetitions.keySet()) {
            if (repetitions.get(die) == 2) {
                pair = true;
            }
        }
        if(pair && repetitions.size() == 2){
            return 25;
        }
        else{
            return 0;
        }
    }

    private int smallStraight(int straight){//returns a score of 15 if 4 dice make a sequence (ie, 1 2 3 4 1)
        if(straight > 3) {
            return 30;
        }
        else{
            return 0;
        }
    }

    private int largeStraight(int straight){//returns a score of 20 if 5 dice make a sequence (ie, 1 2 3 4 6)
        if(straight > 4) {
            return 40;
        }
        else{
            return 0;
        }
    }
    private int calculateStraight() {//calculates how many dice are not in a sequence
        int[] diceFaces = new int[5];
        for (int i = 0; i < dice.length; ++i) {
            diceFaces[i] = dice[i].getFace();
        }
        Arrays.sort(diceFaces);//sorts the dice by face value for counting
        int straight = 1;
        for (int i = 0; i < diceFaces.length - 1; i++) {
            if (diceFaces[i] + 1 == diceFaces[i + 1]) {
                straight++;
            }
        }
        return straight;
    }

    public void calculateScores(){//updates the value of each score in the array of scores with it's unique score calculation
        for (int i = 0; i < scores.length; ++i){//each function is implemented from logic based on the official Yahtzee rule set
            switch (i) {//get rest of the scores
                default: //singles scores for die faces 1-6 (case 0-5)
                    scores[i].setScore(faceSum(i + 1));
                    break;
                case 6:
                    //Sum of all single face scores
                    int sum = 0;
                    for (int j = 0; j < i; ++j) {
                        if(scores[j].isRetained()){
                            sum += scores[j].getValue();
                        }
                    }
                    scores[i].setScore(sum);
                    scores[i].markTotalOrBonus();
                    break;
                case 7:
                    //Bonus score of 35 if sum is over or equal to 63
                    if (scores[6].getValue() >= 63) {
                        scores[i].setScore(35);
                    } else {
                        scores[i].setScore(0);
                    }
                    scores[i].markTotalOrBonus();
                    break;
                case 8:
                    //Upper Total
                    //Sum + Bonus
                    scores[i].setScore(scores[6].getValue() + scores[7].getValue());
                    scores[i].markTotalOrBonus();
                    break;
                    //start of Lower section of scores
                case 9:
                    //Three of a Kind
                    scores[i].setScore(xOfAKind(3));
                    break;
                case 10:
                    //Four of a Kind
                    scores[i].setScore(xOfAKind(4));
                    break;
                case 11:
                    //Full house
                    scores[i].setScore(fullHouse());
                    break;
                case 12:
                    //Small Straight
                    scores[i].setScore(smallStraight(calculateStraight()));
                    break;
                case 13:
                    //Large Straight
                    scores[i].setScore(largeStraight(calculateStraight()));
                    break;
                case 14:
                    //Yahtzee!
                    if (xOfAKind(5) > 0) {
                        scores[i].setScore(50);
                    } else {
                        scores[i].setScore(0);
                    }
                    break;
                case 15:
                    //Chance (total of all 5, no conditions)
                    scores[i].setScore(chance());
                    break;
                case 16:
                    //Lower Total
                    int lowerTotal = 0;
                    for (int k = 9; k < i; ++k) {
                        if(scores[k].isRetained()){
                            lowerTotal += scores[k].getValue();
                        }
                    }
                    scores[i].setScore(lowerTotal);
                    scores[i].markTotalOrBonus();
                    break;
                case 17:
                    //Grand Total
                    scores[i].setScore(scores[8].getValue() + scores[16].getValue());
                    scores[i].markTotalOrBonus();
                    break;
            }
        }
    }
}