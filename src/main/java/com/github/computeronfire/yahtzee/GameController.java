/**
 * Controlls the Yahtzee game board UI, contains functions for interacting with the game
 * Requirement: UI
 */
package com.github.computeronfire.yahtzee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    public Label testBox;
    public Label p1name; /** Player 1 name */
    public Label p1ones;
    public Label p1twos;
    public Label p1threes;
    public Label p1fours;
    public Label p1fives;
    public Label p1sixes;
    public Label p1upperTotal;
    public Label p1bonus;  /** Player 1 scores */
    public Label p1threeOfAKind;
    public Label p1fourOfAKind;
    public Label p1fullHouse;
    public Label p1smallStraight;
    public Label p1largeStraight;
    public Label p1yahtzee;
    public Label p1chance;
    public Label p1lowerTotal;
    public Label p1grandTotal;
    public Label p2name; /** Player 2 name */
    public Label p2ones;
    public Label p2twos;
    public Label p2threes;
    public Label p2fours;
    public Label p2fives;
    public Label p2sixes;
    public Label p2upperTotal;
    public Label p2bonus;  /** Player 2 scores */
    public Label p2threeOfAKind;
    public Label p2fourOfAKind;
    public Label p2fullHouse;
    public Label p2smallStraight;
    public Label p2largeStraight;
    public Label p2yahtzee;
    public Label p2chance;
    public Label p2lowerTotal;
    public Label p2grandTotal;

    /**
     * Initiate the array of 5 dice.
     * Constructed using the Dice class
     */

    private Dice dice = new Dice();
    public ToggleButton die1;
    public ToggleButton die2;
    public ToggleButton die3;
    public ToggleButton die4;
    public ToggleButton die5;

    /**
     * Tests score labels on the board.
     * Temporary. Tests will be moved to the test folder and be made more formal.
     */
    public void testDisplayScores(ActionEvent actionEvent) {
        testBox.setText("This is Yahztee!");

        p1name.setText("Player 1");
        p1ones.setText("test");
        p1twos.setText("test");
        p1threes.setText("test");
        p1fours.setText("test");
        p1fives.setText("test");
        p1sixes.setText("test");
        p1upperTotal.setText("test");
        p1bonus.setText("test");
        p1threeOfAKind.setText("test");
        p1fourOfAKind.setText("test");
        p1fullHouse.setText("test");
        p1smallStraight.setText("test");
        p1largeStraight.setText("test");
        p1yahtzee.setText("test");
        p1chance.setText("test");
        p1lowerTotal.setText("test");
        p1grandTotal.setText("test");

        p2name.setText("Player 2");
        p2ones.setText("test");
        p2twos.setText("test");
        p2threes.setText("test");
        p2fours.setText("test");
        p2fives.setText("test");
        p2sixes.setText("test");
        p2upperTotal.setText("test");
        p2bonus.setText("test");
        p2threeOfAKind.setText("test");
        p2fourOfAKind.setText("test");
        p2fullHouse.setText("test");
        p2smallStraight.setText("test");
        p2largeStraight.setText("test");
        p2yahtzee.setText("test");
        p2chance.setText("test");
        p2lowerTotal.setText("test");
        p2grandTotal.setText("test");
    }

    /**
     * Returns to the Start Menu when the "Main Menu" button is pressed.
     * Will end the current game. This may be changed to prompt the user to save the game.
     */
    public void exitToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/yahtzeeMenu.fxml"));

        Parent parent  = fxmlLoader.load();
        //MenuController controller = fxmlLoader.getController();
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void holdDie(ActionEvent actionEvent) {

    }

    public void rollDice(ActionEvent actionEvent) {
        for (int i = 0; i < dice.length; i++){
            dice[i].rollDie();
            switch(i){
                case 0:
                    setDieImage(die1, dice[i].face);
                case 1:
                    setDieImage(die2, dice[i].face);
                case 2:
                    setDieImage(die3, dice[i].face);
                case 3:
                    setDieImage(die4, dice[i].face);
                case 4:
                    setDieImage(die5, dice[i].face);
            }
        }
    }
    public void setDieImage(ToggleButton die, int dieFace){
        ImageView iview;
        Image face;
        switch(dieFace){
            case 1:
                face = new Image("/die1.png");
                break;
            case 2:
                face = new Image("/die2.png");
                break;
            case 3:
                face = new Image("/die3.png");
                break;
            case 4:
                face = new Image("/die4.png");
                break;
            case 5:
                face = new Image("/die5.png");
                break;
            case 6:
                face = new Image("/die6.png");
                break;
            default: /** should never happen */
                face = null;
                break;
        }
        iview = new ImageView(face);
        die.setGraphic(iview);
    }
}
