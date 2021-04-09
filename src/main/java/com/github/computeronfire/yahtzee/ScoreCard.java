package com.github.computeronfire.yahtzee;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles logic and calculations for the game, such as scores.
 * Contains an array of int to represent the dice face values.
 * Requirements: Game Functionality, Tally Scores, Follow Yahtzee Rules
 */

public class ScoreCard {
    private Dice dice;
    private Score[] scores = new Score[20]; //must be size of 20

    public ScoreCard(){
        for (int i = 0; i < this.scores.length; ++i){
            this.scores[i] = new Score();
        }
    }
    public ScoreCard(ScoreCard scoreCard, Dice dice){
        this.scores = scoreCard.scores;
        this.dice = dice;
    }
    public Score getScore(int index){
        return scores[index];
    }

    private int chance(){ //return sum of all dice
        int sum = 0;
        for (Die die : dice.getDice()){
            sum += die.getFace();
        }
        return sum;
    }

    private int faceSum(int value){//return sum of a all dice with certain face value
        int sum = 0;
        for (Die die : dice.getDice()){
            if (die.getFace() == value){
                sum += die.getFace();
            }
        }
        return sum;
    }

    private Map<Integer, Integer> repetition() {//constructs a Key, Value map for how many times each die repeats
        Map<Integer, Integer> repetitions = new HashMap<Integer, Integer>();
        for (Die die : dice.getDice()){
            Integer repetition = repetitions.get(die.getFace());
            if(repetition == null){
                repetitions.put(die.getFace(), 1);
            }
            else{
                repetitions.put(die.getFace(), repetition + 1);
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

    private int largeStraight(){//TODO: test implementation of large Straight
        Map<Integer, Integer> repetitions = repetition();
        for(int die : repetitions.keySet()){
            if(repetitions.get(die) > 1){
                return 0;
            }
        }
        return 20;
    }

    public void calculateScores(){//TODO: decide if switch should be refactored into functions
        for (int i = 0; i < scores.length; ++i){
            switch (i) {//get rest of the scores
                default: //singles scores for die faces 1-6 (case 0-5)
                    scores[i].setScore(faceSum(i + 1));
                    break;
                case 6:
                    //Sum of all single face scores
                    int sum = 0;
                    for (int j = 0; j < i; ++j) {
                        sum += scores[j].getValue();
                    }
                    scores[i].setScore(sum);
                    break;
                case 7:
                    //Bonus score of 35 if sum is over 63
                    if (scores[6].getValue() >= 63) {
                        scores[i].setScore(35);
                    } else {
                        scores[i].setScore(0);
                    }
                    break;
                case 8:
                    //Upper Total
                    //Sum + Bonus
                    scores[i].setScore(scores[6].getValue() + scores[7].getValue());
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
                    if (xOfAKind(3) > 0 && xOfAKind(2) > 0) {
                        scores[i].setScore(xOfAKind(3) + xOfAKind(2));
                    } else {
                        scores[i].setScore(0);
                    }
                    break;
                case 12:
                    //Small Straight
                    scores[i].setScore(smallStraight());
                    break;
                case 13:
                    //Large Straight
                    scores[i].setScore(largeStraight());
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
                case 16://TODO: decide if yahtzee bonus should be removed
                    //Yahtzee Bonus (check count only), might remove
                    //scores[i].markTotalOrBonus();
                    if(scores[14].getValue() > 0){
                        scores[i].setScore(scores[i].getValue() + 1);
                    }
                    break;
                case 17:
                    //Yahtzee Bonus (score), might remove
                    scores[i].setScore(scores[16].getValue() * 50);
                    break;
                case 18:
                    //Lower Total
                    int lowerTotal = 0;
                    for (int k = 9; k < i; ++k) {
                        lowerTotal += scores[k].getValue();
                    }
                    scores[i].setScore(lowerTotal);
                    break;
                case 19:
                    //Grand Total
                    scores[i].setScore(scores[8].getValue() + scores[18].getValue());
                    break;
            }
        }
        //return scores;
    }
}