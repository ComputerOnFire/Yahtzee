package com.github.computeronfire.yahtzee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GameController.java
 * Controls the Yahtzee game board UI and displays scores.
 * Contains functions for interacting with the game via the UI,
 * as well as turn logic and winner calculation.
 *
 * Requirement: UI
 */

public class GameController {

    /**
     * Initiate the array of 5 dice.
     * Constructed using the Dice class
     */

    private final int rolls = 3;
    private final int fields = 18;
    private final String[] fieldLabels = {"Player Name", "Ones", "Twos", "Threes", "Fours","Fives", "Sixes",
            "Sum", "Bonus", "UpperTotal", "ThreeOfAKind", "FourOfAKind", "FullHouse", "SmallStraight",
            "LargeStraight","Yahtzee!", "Chance", "Lower Total", "Grand Total"};
    public Label winnerDisplay;
    private final Dice dice = new Dice();
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0; //track who's turn it is
    private int rollCounter = rolls;


    //TODO: make these private, annotate with @FXML, rename to camelcase
    public Label resetBox;
    @FXML
    private Button rollButton;
    @FXML
    private GridPane grid;
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
     * sets up the grid to display scores for each player,
     * first by iterating through the fields, then by iterating over the columns and rows to insert each player name and score field
     */

    @FXML
    public void initializeBoard(List<Player> players){
        registerPlayers(players);
        rollButton.setText(String.format("Roll (%d left)", rollCounter));
        for (int i = 0; i <= fields; ++i){
            grid.add(new Label(fieldLabels[i]), 0, i);
        }
        for(int col = 1; col < players.size() + 1; ++col){
            StackPane pane = new StackPane();
            grid.add(pane, col, 0);
            Rectangle highlight = new Rectangle();
            highlight.setOpacity(0);
            highlight.setFill(Color.YELLOW);
            highlight.setWidth(grid.getColumnConstraints().get(col).getPrefWidth());
            highlight.setHeight(grid.getRowConstraints().get(0).getPrefHeight());
            pane.getChildren().add(highlight);
            Label playerNameLabel = new Label();
            String playerNameText = players.get(col - 1).getName();
            ScoreCard playerScoreCard = players.get(col - 1).getScoreCard();
            playerNameLabel.setText(playerNameText);
            pane.getChildren().add(playerNameLabel);

            for(int row = 1; row <= fields; ++row){
                Rectangle background = new Rectangle();
                background.setWidth(grid.getColumnConstraints().get(col).getPrefWidth());
                background.setHeight(grid.getRowConstraints().get(row).getPrefHeight());
                background.setOpacity(0);
                StackPane scorePane = new StackPane();
                grid.add(scorePane, col, row);
                scorePane.getChildren().add(background);
                Label score = new Label();
                String text = Integer.toString(playerScoreCard.getScore(row-1).getValue());
                score.setText(text);
                scorePane.getChildren().add(score);
            }
        }
        enableCurrentPlayer();
    }
    private boolean gameOver(){
        boolean completed = true;
        for (Player player: players){
            for (Score score: player.getScoreCard().getScores()){
                if(!score.isRetained() && score.isNotTotalOrBonus()){
                    completed = false;
                }
            }
        }
        return completed;
    }

    private Player winner(){//determines which player has the highest score, and returns the winnerDisplay
        int highScore = 0;
        Player winner = null;
        for(Player player: players){
            if (player.getScoreCard().getScore(fields-1).getValue() > highScore){
                winner = player;
                highScore = player.getScoreCard().getScore(fields-1).getValue();
            }
        }
        return winner;
    }

    private void enableCurrentPlayer() {//sets the control of the board over to the player whose turn it should be
        for(int i = 0; i < players.size(); ++i){
            int col = i + 1;
            StackPane scorePane = (StackPane) getGridNode(grid, col, 0);
            assert scorePane != null;
            Rectangle background = (Rectangle) scorePane.getChildren().get(0);
            if(i == currentPlayerIndex){
                background.setOpacity(1);
            }
            else{
                background.setOpacity(0);
            }
        }
    }

    private void registerPlayers(List<Player> players) {
        this.players = players;
    }

    @FXML
    private void resetBoard() {
        currentPlayerIndex = 0;
        grid.getChildren().clear();
        List<Player> playersCleared = new ArrayList<>();
        for(Player player: players){
            playersCleared.add(new Player(player.getName()));
        }
        initializeBoard(playersCleared);
    }

    @FXML
    private void updateScores(){ //updates the scores for the current player
        ScoreCard scoreCard = new ScoreCard(players.get(currentPlayerIndex), dice);
        scoreCard.calculateScores();
        players.get(currentPlayerIndex).updateScoreCard(scoreCard);
        for(int i = 0; i < fields; ++i){
            enableScore(scoreCard.getScore(i), currentPlayerIndex + 1, i + 1);
        }
    }

    private void endTurn() {
        disableDice();
        finalizeScores();
        if(gameOver()){
            Player winner = winner();
            winnerDisplay.setText(String.format("%s wins with %d points!", winner.getName(),winner.getScoreCard().getScore(fields-1).getValue()));
        }
        else{
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            rollCounter = rolls;
            updateRollButton();
            enableCurrentPlayer();
        }
    }

    private void disableDice() {//disables the dice buttons so that they cannot be held before a roll
        ToggleButton[] diceButtons = new ToggleButton[]{die1,die2,die3,die4,die5};
        for(int i = 0; i < diceButtons.length; ++i){
            dice.getDice()[i].hold(false);
            diceButtons[i].setSelected(false);
            diceButtons[i].setDisable(true);
        }
    }

    private void finalizeScores() {
        for(int i = 0; i < fields; ++i){
            finalizeScore(currentPlayerIndex + 1, i + 1);
        }
    }

    private void finalizeScore(int col, int row) {//if not a total or bonus, clear it. if it is a total or bonus, update new total/bonus with selection. Keep selected scores.
        StackPane scorePane = (StackPane) getGridNode(grid, col, row);
        assert scorePane != null;
        Rectangle background = (Rectangle) scorePane.getChildren().get(0);
        Label label = (Label) scorePane.getChildren().get(1);
        scorePane.setOnMouseClicked(null);
        if(players.get(currentPlayerIndex).getScoreCard().getScore(row - 1).isNotTotalOrBonus() && !players.get(currentPlayerIndex).getScoreCard().getScore(row-1).isRetained()){
            background.setOpacity(0);
            label.setText("");
        }
        else{
            players.get(currentPlayerIndex).getScoreCard().calculateScores();
            String text = Integer.toString(players.get(currentPlayerIndex).getScoreCard().getScore(row - 1).getValue());
            label.setText(text);
        }
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
        dice.getDice()[id].hold((dice.getDice()[id].isNotHeld()));
    }

    @FXML
    private void rollDice() { //TODO: move hold logic to Dice class?
        dice.rollDice();
        for (int i = 0; i < dice.getDice().length; i++){
            switch(i){
                case 0:
                    setDieImage(die1, dice.getDie(i).getFace());
                    break;
                case 1:
                    setDieImage(die2, dice.getDie(i).getFace());
                    break;
                case 2:
                    setDieImage(die3, dice.getDie(i).getFace());
                    break;
                case 3:
                    setDieImage(die4, dice.getDie(i).getFace());
                    break;
                case 4:
                    setDieImage(die5, dice.getDie(i).getFace());
                    break;
                default:
                    /*
                        should never happen
                    */
                    break;

            }
        }
        --rollCounter;
        updateRollButton();
        updateScores();
    }

    private void updateRollButton(){
        rollButton.setText(String.format("Roll (%d left)", rollCounter));
        rollButton.setDisable(rollCounter < 1);
    }

    private void setDieImage(ToggleButton die, int dieFace){
        if(die.isDisabled()){
            die.setDisable(false);
        }
        ImageView iView;
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
                /*
                    should never happen
                */
                face = null;
                break;
        }
        iView = new ImageView(face);
        iView.setFitHeight(40.0);
        iView.setFitWidth(40.0);
        iView.setPreserveRatio(true);
        iView.setPickOnBounds(true);
        die.setGraphic(iView);
    }

    private void keepScore(MouseEvent mouseEvent, int index){//TODO: when score is clicked, keep said score
        StackPane scorePane = (StackPane) mouseEvent.getSource();
        Rectangle background = (Rectangle) scorePane.getChildren().get(0);
        players.get(currentPlayerIndex).keepScore(index);
        background.setFill(Color.GREEN);
        background.setOpacity(1);
        endTurn();
    }

    private void enableScore(Score score, int col, int row){//TODO: highlight which scores are valid and available for keeping
        String text = Integer.toString(score.getValue());
        StackPane scorePane = (StackPane) getGridNode(grid, col, row);
        assert scorePane != null;
        Rectangle background = (Rectangle) scorePane.getChildren().get(0);
        Label label = (Label) scorePane.getChildren().get(1);
        if ((!score.isRetained() && score.isNotTotalOrBonus())){
            background.setOpacity(1);
            background.setFill(Color.YELLOW);
            scorePane.setOnMouseClicked(mouseEvent -> keepScore(mouseEvent, row - 1));
        }
        label.setText(text);
    }

    private Node getGridNode(GridPane gridPane, int col, int row){//gets grid node based on column and row
        for (Node node : gridPane.getChildren()) {
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row){
                return node;
            }
        }
        return null; //no grid node found
    }
}