package com.github.computeronfire.yahtzee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

 /**
   * TODO:
   * requirements should match requirements and include document index number
   */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/yahtzeeMenu.fxml"));
        primaryStage.setTitle("Yahtzee!");
        primaryStage.setScene(new Scene(root, 1000, 750));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}