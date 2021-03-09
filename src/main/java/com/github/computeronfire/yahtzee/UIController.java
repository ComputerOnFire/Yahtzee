package com.github.computeronfire.yahtzee;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class UIController {
    public Label helloWorld;

    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("Hello World!");
    }
}
