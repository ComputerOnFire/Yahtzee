package com.github.computeronfire.yahtzee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuController.java
 * Controls the Main Menu, contains function for construction of game board scene, as well as fields for player names.
 * Requirements: 2.0.0, 4.0.0
 */

public class MenuController {
    private final int width = 1000;
    private final int height = 600;

    @FXML
    private TextField player1;//text field for the user to input the name of each player
    @FXML
    private TextField player2;//Player 1 and Player 2 are filled by default, the rest are empty by default
    @FXML
    private TextField player3;//if a player name field is empty, it will not be added to the game board
    @FXML
    private TextField player4;
    @FXML
    private TextField player5;
    @FXML
    private TextField player6;
    @FXML
    private TextField player7;

    public void startGame(ActionEvent actionEvent) throws IOException {//starts the game when button is pressed, takes player names input by the user
        TextField[] playerFields = new TextField[]{player1, player2, player3, player4, player5, player6, player7};//labels put into an array for iteration
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/yahtzeeGame.fxml"));//load the fxml file outlining the game board
        List<Player> players = new ArrayList<>();//dynamic arraylist representing the players
        for (TextField player : playerFields){//iterate over the player text fields to check if they are empty
            if(!player.getText().isEmpty()){//if they are not empty, add the player to the players ArrayList
                players.add(new Player(player.getText()));
            }
        }
        if(players.isEmpty()){//if all player name fields are empty, add one default player so the game will start
            players.add(new Player("Player 1"));
            //players.add(new Player("Player 2"));
        }
        Parent parent = fxmlLoader.load();
        GameController controller = fxmlLoader.getController(); //load the game controller

        controller.initializeBoard(players);//call the initializeBoard() function from the loaded game controller, passing the input players

        Scene scene = new Scene(parent, width, height);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();//take properties from the parent window, (icon and background color)

        primaryStage.setScene(scene);//set the scene with the game board
        primaryStage.show();//show the game board
        //primaryStage.setResizable(false);//make the game board not resizable by the user
    }
}