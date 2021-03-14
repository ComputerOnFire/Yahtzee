package com.github.computeronfire.yahtzee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public void startGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/yahtzeeGame.fxml"));

        Parent parent  = fxmlLoader.load();
        //GameController controller = fxmlLoader.getController();
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
