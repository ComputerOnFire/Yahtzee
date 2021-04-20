package com.github.computeronfire.yahtzee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * TODO: requirements should match requirements and include document index number
 * Launcher.java
 * Launches the main menu UI, starting the Yahtzee application.
 *
 * Requirement: UI, game functionality
 */

public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{//starts the application
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/yahtzeeMenu.fxml"))); //loads the fxml file outlining the UI
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/yahtzee.png"))));//loads the icon graphic
        primaryStage.setTitle("Yahtzee!");
        primaryStage.setScene(new Scene(root, 250, 500));
        primaryStage.show();
        primaryStage.setResizable(false);//user cannot resize the window
    }
    public static void main(String[] args) {
        launch(args);//launch the application
    }
}