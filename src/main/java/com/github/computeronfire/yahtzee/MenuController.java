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
 * Requirements: UI
 */

public class MenuController {

    @FXML
    private TextField player1;
    @FXML
    private TextField player2;
    @FXML
    private TextField player3;
    @FXML
    private TextField player4;
    @FXML
    private TextField player5;
    @FXML
    private TextField player6;
    @FXML
    private TextField player7;

    public void startGame(ActionEvent actionEvent) throws IOException {
        TextField[] playerFields = new TextField[]{player1, player2, player3, player4, player5, player6, player7};
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/yahtzeeGame.fxml"));
        List<Player> players = new ArrayList<>();
        for (TextField player : playerFields){
            if(!player.getText().isEmpty()){
                players.add(new Player(player.getText()));
            }
        }
        if(players.isEmpty()){
            players.add(new Player("Player 1"));
            //players.add(new Player("Player 2"));
        }
        Parent parent = fxmlLoader.load();
        GameController controller = fxmlLoader.getController();
        controller.initializeBoard(players);

        Scene scene = new Scene(parent, 1000, 600);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}