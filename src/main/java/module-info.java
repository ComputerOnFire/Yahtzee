module Yahtzee {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.computeronfire.Yahtzee to javafx.fxml;
    exports com.github.computeronfire.Yahtzee;
}