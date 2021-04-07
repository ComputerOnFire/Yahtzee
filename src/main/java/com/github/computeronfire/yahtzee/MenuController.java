/**
 * Controls the Main Menu, contains function for construction of game board scene
 * Requirement: UI
 */
package com.github.computeronfire.yahtzee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuController {

    public void startGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/yahtzeeGame.fxml"));
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        Parent parent = fxmlLoader.load();
        GameController controller = fxmlLoader.getController();
        controller.registerPlayers(players);
        controller.initializeBoard();

        Scene scene = new Scene(parent, 1000, 750);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}