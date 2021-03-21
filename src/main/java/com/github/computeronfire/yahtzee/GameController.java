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
    public Label p1t1;
    public Label p1t2;
    public Label p1t3;
    public Label p1t4;
    public Label p1t5;
    public Label p1t6;
    public Label p1t7;
    public Label p1t8;
    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("This is Yahztee!");
        p1t1.setText("This is Yahztee!");
        p1t2.setText("This is Yahztee!");
        p1t3.setText("This is Yahztee!");
        p1t4.setText("This is Yahztee!");
        p1t5.setText("This is Yahztee!");
        p1t6.setText("This is Yahztee!");
        p1t7.setText("This is Yahztee!");
        p1t8.setText("This is Yahztee!");
    }

    public void exitToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/yahtzeeMenu.fxml"));

        Parent parent  = fxmlLoader.load();
        //MenuController controller = fxmlLoader.getController();
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
