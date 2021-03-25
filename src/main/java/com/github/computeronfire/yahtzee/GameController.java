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
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    public Label helloWorld;
    public Label p1name; /** Player 1 name */
    public Label p1t1;
    public Label p1t2;
    public Label p1t3;
    public Label p1t4;
    public Label p1t5;
    public Label p1t6;
    public Label p1t7;
    public Label p1t8;  /** Player 1 scores */
    public Label p1t9;
    public Label p1t10;
    public Label p1t11;
    public Label p1t12;
    public Label p1t13;
    public Label p1t14;
    public Label p1t15;
    public Label p1t16;
    public Label p1t17;
    public Label p2name; /** Player 2 name */
    public Label p2t1;
    public Label p2t2;
    public Label p2t3;
    public Label p2t4;
    public Label p2t5;
    public Label p2t6;
    public Label p2t7;
    public Label p2t8;  /** Player 2 scores */
    public Label p2t9;
    public Label p2t10;
    public Label p2t11;
    public Label p2t12;
    public Label p2t13;
    public Label p2t14;
    public Label p2t15;
    public Label p2t16;
    public Label p2t17;

    /**
     * Initiate an array of 5 dice.
     * Represents each die on the board.
     */
    public Die[] dice = new Die[5];

    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("This is Yahztee!");

        p1name.setText("Player 1");
        p1t1.setText("test");
        p1t2.setText("test");
        p1t3.setText("test");
        p1t4.setText("test");
        p1t5.setText("test");
        p1t6.setText("test");
        p1t7.setText("test");
        p1t8.setText("test");
        p1t9.setText("test");
        p1t10.setText("test");
        p1t11.setText("test");
        p1t12.setText("test");
        p1t13.setText("test");
        p1t14.setText("test");
        p1t15.setText("test");
        p1t16.setText("test");
        p1t17.setText("test");

        p2name.setText("Player 2");
        p2t1.setText("test");
        p2t2.setText("test");
        p2t3.setText("test");
        p2t4.setText("test");
        p2t5.setText("test");
        p2t6.setText("test");
        p2t7.setText("test");
        p2t8.setText("test");
        p2t9.setText("test");
        p2t10.setText("test");
        p2t11.setText("test");
        p2t12.setText("test");
        p2t13.setText("test");
        p2t14.setText("test");
        p2t15.setText("test");
        p2t16.setText("test");
        p2t17.setText("test");
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

    }
}
