module Yahtzee{
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.computeronfire.yahtzee to javafx.fxml;
    exports com.github.computeronfire.yahtzee;
}