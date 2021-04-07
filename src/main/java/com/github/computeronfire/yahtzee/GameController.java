/**
 * Controlls the Yahtzee game board UI, contains functions for interacting with the game
 * Requirement: UI
 */
package com.github.computeronfire.yahtzee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    //TODO: make these private, annotate with @FXML, rename to camelcase
    public Label testBox;

    @FXML
    private GridPane grid;

    /**
     * Initiate the array of 5 dice.
     * Constructed using the Dice class
     */

    private Dice dice = new Dice();
    private List<Player> players = new ArrayList<>();
    Score[] p1Score = new Score[20];
    Score[] p2Score = new Score[20];
    ScoreCard score1 = new ScoreCard();
    ScoreCard score2 = new ScoreCard();

    @FXML
    private ToggleButton die1;
    @FXML
    private ToggleButton die2;
    @FXML
    private ToggleButton die3;
    @FXML
    private ToggleButton die4;
    @FXML
    private ToggleButton die5;

    /**
     * Tests score labels on the board.
     * Temporary. Tests will be moved to the test folder and be made more formal.
     */

    @FXML
    public void initializeBoard(){
        grid.add(new Label("Name"), 0, 0);
        grid.add(new Label("Ones"), 0, 1);
        grid.add(new Label("Twos"), 0, 2);
        grid.add(new Label("Threes"), 0, 3);
        grid.add(new Label("Fours"), 0, 4);
        grid.add(new Label("Fives"), 0, 5);
        grid.add(new Label("Sixes"), 0, 6);
        grid.add(new Label("Sum"), 0, 7);
        grid.add(new Label("Bonus"), 0, 8);
        grid.add(new Label("UpperTotal"), 0, 9);
        grid.add(new Label("ThreeOfAKind"), 0, 10);
        grid.add(new Label("FourOfAKind"), 0, 11);
        grid.add(new Label("FullHouse"), 0, 12);
        grid.add(new Label("SmallStraight"), 0, 13);
        grid.add(new Label("LargeStraight"), 0, 14);
        grid.add(new Label("Yahtzee!"), 0, 15);
        grid.add(new Label("Chance"), 0, 16);
        grid.add(new Label("Yahtzee! Bonuses"), 0, 17);
        grid.add(new Label("Yahtzee! Bonus"), 0, 18);
        grid.add(new Label("Lower Total"), 0, 19);
        grid.add(new Label("Grand Total"), 0, 20);
        for(int col = 1; col < players.size() + 1; ++col){
            StackPane pane = new StackPane();
            grid.add(pane, col, 0);
            Label playerNameLabel = new Label();
            String playerNameText = players.get(col - 1).getName();
            ScoreCard playerScoreCard = players.get(col - 1).getScoreCard();
            playerNameLabel.setText(playerNameText);
            pane.getChildren().add(playerNameLabel);
           // grid.add(playerNameLabel, col, 0);
            for(int row = 1; row < playerScoreCard.getScores().length + 1; ++row){
                StackPane scorePane = new StackPane();
                grid.add(scorePane, col, row);
                Label score = new Label();
                String text = Integer.toString(playerScoreCard.getScores()[row-1].getScore());
                score.setText(text);
                scorePane.getChildren().add(score);
            }
        }
    }

    @FXML
    private void testScoreFields(ActionEvent actionEvent) {
        initializeBoard();

    }
    @FXML
    private void updateScores(Dice dice){ //change dice to player? use player here?
        score1 = new ScoreCard(dice, score1.getScores());
        score1.calculateScores();
        p1Score = score1.getScores();
        enableScore(p1Score[0],1,1);
        enableScore(p1Score[1],1,2);
        enableScore(p1Score[2],1,3);
        enableScore(p1Score[3],1,4);
        enableScore(p1Score[4],1,5);
        enableScore(p1Score[5],1,6);
        enableScore(p1Score[6],1,7);
        enableScore(p1Score[7],1,8);
        enableScore(p1Score[8],1,9);
        enableScore(p1Score[9],1,10);
        enableScore(p1Score[10],1,11);
        enableScore(p1Score[11],1,12);
        enableScore(p1Score[12],1,13);
        enableScore(p1Score[13],1,14);
        enableScore(p1Score[14],1,15);
        enableScore(p1Score[15],1,16);
        enableScore(p1Score[16],1,17);
        enableScore(p1Score[17],1,18);
        enableScore(p1Score[18],1,19);
        enableScore(p1Score[19],1,20);


        score2 = new ScoreCard(dice, score2.getScores());
        score2.calculateScores();
        p2Score = score2.getScores();
        enableScore(p1Score[0],2,1);
        enableScore(p1Score[1],2,2);
        enableScore(p2Score[2],2,3);
        enableScore(p2Score[3],2,4);
        enableScore(p2Score[4],2,5);
        enableScore(p2Score[5],2,6);
        enableScore(p2Score[6],2,7);
        enableScore(p2Score[7],2,8);
        enableScore(p2Score[8],2,9);
        enableScore(p2Score[9],2,10);
        enableScore(p2Score[10],2,11);
        enableScore(p2Score[11],2,12);
        enableScore(p2Score[12],2,13);
        enableScore(p2Score[13],2,14);
        enableScore(p2Score[14],2,15);
        enableScore(p2Score[15],2,16);
        enableScore(p2Score[16],2,17);
        enableScore(p2Score[17],2,18);
        enableScore(p2Score[18],2,19);
        enableScore(p2Score[19],2,20);
    }

    /**
     * Returns to the Start Menu when the "Main Menu" button is pressed.
     * Will end the current game. This may be changed to prompt the user to save the game.
     */

    @FXML
    private void exitToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/yahtzeeMenu.fxml"));
        Parent parent  = fxmlLoader.load();
        //MenuController controller = fxmlLoader.getController();
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void holdDie(ActionEvent actionEvent) {
        ToggleButton dieButton = (ToggleButton) actionEvent.getSource();
        int id = Integer.parseInt(dieButton.getId().substring("die".length())) - 1;
        dice.getDice()[id].hold((!dice.getDice()[id].isHeld()));
    }

    @FXML
    private void rollDice(ActionEvent actionEvent) { //TODO: move hold logic to Dice class?
        for (int i = 0; i < dice.getDice().length; i++){
            if(!dice.getDice()[i].isHeld()){
                dice.getDice()[i].rollDie();
                switch(i){
                    case 0:
                        setDieImage(die1, dice.getDice()[i].getFace());
                        break;
                    case 1:
                        setDieImage(die2, dice.getDice()[i].getFace());
                        break;
                    case 2:
                        setDieImage(die3, dice.getDice()[i].getFace());
                        break;
                    case 3:
                        setDieImage(die4, dice.getDice()[i].getFace());
                        break;
                    case 4:
                        setDieImage(die5, dice.getDice()[i].getFace());
                        break;
                    default:
                        /** should never happen */
                        setDieImage(null, dice.getDice()[i].getFace());
                        break;
                }
            }
        }
        updateScores(dice);
    }

    private void setDieImage(ToggleButton die, int dieFace){
        if(die.isDisabled()){
            die.setDisable(false);
        }
        ImageView iview;
        Image face;
        switch(dieFace){
            case 1:
                face = new Image("/die1.png");
                break;
            case 2:
                face = new Image("/die2.png");
                break;
            case 3:
                face = new Image("/die3.png");
                break;
            case 4:
                face = new Image("/die4.png");
                break;
            case 5:
                face = new Image("/die5.png");
                break;
            case 6:
                face = new Image("/die6.png");
                break;
            default:
                /** should never happen */
                face = null;
                break;
        }
        iview = new ImageView(face);
        iview.setFitHeight(40.0);
        iview.setFitWidth(40.0);
        iview.setPreserveRatio(true);
        iview.setPickOnBounds(true);
        die.setGraphic(iview);
    }
    private void keepScore(){//TODO: when score is clicked, keep said score

    }
    private void enableScore(Score score, int col, int row){//TODO: highlight which scores are valid and available for keeping
        //if(score.getScore() > 0){
            String text = Integer.toString(score.getScore());
            StackPane pane = (StackPane) getGridNode(grid, col, row);
            Label label = (Label) pane.getChildren().get(0);
            label.setText(text);
            label.setTextFill(Color.YELLOW);
        //}
    }
    private Node getGridNode(GridPane gridPane, int col, int row){//gets grid node based on column and row
        for (Node node : gridPane.getChildren()) {
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row){
                return node;
            }
        }
        return null; //no grid node found
    }

    public void registerPlayers(List<Player> players) {
        this.players = players;
    }
}