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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * GameController.java
 * Controls the Yahtzee game board UI and displays scores.
 * Contains functions for interacting with the game via the UI,
 * as well as turn logic and winner calculation.
 *
 * Requirements: 1.0.0, 2.0.0, 2.0.1, 3.0.0, 4.0.0
 */

public class GameController {

    private final int width = 280;
    private final int height = 500;

    /**
     * Initiate the array of 5 dice.
     * Constructed using the Dice class
     */

    private final int rolls = 3;//maximum number of rolls per turn
    private final int fields = 18;//number of different score fields
    private final String[] fieldLabels = {" ", "Ones", "Twos", "Threes", "Fours","Fives", "Sixes",
            "Sum", "Bonus", "Upper Total", "3 of A Kind", "4 of A Kind", "Full House", "Small Straight",
            "Large Straight","Yahtzee!", "Chance", "Lower Total", "Grand Total"};

    private final Dice dice = new Dice();
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0; //track who's turn it is
    private int rollCounter = rolls; //initialize the roll counter as the maximum number of rolls
    
    private final File saveFile = new File("last_save.txt");

    @FXML
    private Button loadButton;//button to load last save
    @FXML
    private Button saveButton;//button to save current state
    @FXML
    private Button rollButton;//button to roll the dice
    @FXML
    private GridPane grid;//game board is set on a grid for dynamic displays
    @FXML
    private ToggleButton die1;//toggle buttons to represent each die, user can toggle to hold the die
    @FXML
    private ToggleButton die2;
    @FXML
    private ToggleButton die3;
    @FXML
    private ToggleButton die4;
    @FXML
    private ToggleButton die5;
    @FXML
    public Label messageDisplay;//displays the name of the winner

    /**
     * sets up the grid to display scores for each player,
     * first by iterating through the fields, then by iterating over the columns and rows to insert each player name and score field
     *
     */

    @FXML
    public void initializeBoard(List<Player> players){//initializes the game state and draws the board
        registerPlayers(players);//players are taken from the main menu
        loadButton.setDisable(!saveFile.exists());//disable load button if save file does not exist
        saveButton.setDisable(true);//disable save button until user begins playing
        rollButton.setText(String.format("Roll (%d left)", rollCounter));//set the text on the roll button
        for (int i = 0; i <= fields; ++i){//add a label for each score type
            grid.add(new Label(fieldLabels[i]), 0, i);
        }
        for(int col = 1; col < players.size() + 1; ++col){//populate the grid with each player
            StackPane pane = new StackPane();
            grid.add(pane, col, 0);
            Rectangle highlight = new Rectangle();
            highlight.setOpacity(0);
            highlight.setFill(Color.YELLOW);
            highlight.setWidth(grid.getColumnConstraints().get(col).getPrefWidth());//sets the size of each grid cell
            highlight.setHeight(grid.getRowConstraints().get(0).getPrefHeight());
            pane.getChildren().add(highlight);
            Label playerNameLabel = new Label();
            String playerNameText = players.get(col - 1).getName();
            ScoreCard playerScoreCard = players.get(col - 1).getScoreCard();
            playerNameLabel.setText(playerNameText);
            pane.getChildren().add(playerNameLabel);

            for(int row = 1; row <= fields; ++row){//populates each player's grid column with score labels
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
        enableCurrentPlayer();//start the game
    }

    /**
     * Returns to the Start Menu when the "Main Menu" button is pressed.
     * Will end the current game.
     */

    @FXML
    private void exitToMenu(ActionEvent actionEvent) throws IOException {//changes the scene back to the main menu
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/yahtzeeMenu.fxml"));
        Parent parent  = fxmlLoader.load();
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void resetBoard() {//clears the board and starts a new game with the current players
        endTurn();
        currentPlayerIndex = 0;
        grid.getChildren().clear();
        List<Player> playersCleared = new ArrayList<>();
        for(Player player: players){
            playersCleared.add(new Player(player.getName()));
        }
        initializeBoard(playersCleared);
        messageDisplay.setText("");
    }
    
    @FXML
    private void loadLastSave() throws FileNotFoundException {// loads data from the last created savefile
        try {
            // Timestamp
            DateTimeFormatter d = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            
            Scanner in = new Scanner(saveFile);
            
            ArrayList<Player> players = new ArrayList<>(); // clear player list

            while(in.hasNext()){ // read file line by line
                String next = in.nextLine(), trim = next.trim(); // remove all the "fat" (leading/trailing whitespace)
                
                if (trim.contains("="))
                {
                    String[] split = trim.split("="); // get key/value for each line with "="
                    String key = split[0].trim(),
                           value = split[1].trim();
                    
                    // Read player information
                    if (key.startsWith("[") && key.endsWith("]"))
                    {
                        ScoreCard scoreCard;
                        
                        String name = key.substring(1, key.length() - 1);
                        
                        // Get value by trimming off braces and splitting by whitespace
                        String[] strScores = value.substring(1, value.length() - 1).trim().split(" ");
                        Score[] scores = new Score[fields];
                        
                        for (int i = 0; i < strScores.length; i++)
                        {
                            if (!strScores[i].equals(""))
                                {
                                if (strScores[i].equalsIgnoreCase("X"))
                                    scores[i] = new Score(0);
                                else
                                {
                                    scores[i] = new Score(Integer.parseInt(strScores[i]));
                                    scores[i].retainScore();
                                }
                            }
                        }
                        scoreCard = new ScoreCard(scores,dice.getDice());
                        scoreCard.calculateScores();//used mostly to mark retained scores when loading, sometimes previews scores of next player, but does not impact gameplay
                        Player p = new Player(name, scoreCard);
                        players.add(p);
                        
                        grid.getChildren().clear();

                    }
                    else
                    {
                        if (key.toUpperCase().startsWith("P"))
                        {
                            currentPlayerIndex = Integer.parseInt(value);
                        }
                        else if (key.toUpperCase().startsWith("R"))
                        {
                            rollCounter = Integer.parseInt(value);
                        }
                    }
                }
            }

            in.close();
            initializeBoard(players);
            for (int i = 0; i < players.size(); i++){
                for (int j = 0; j < players.get(i).getScoreCard().getScores().length; j++){
                    if((players.get(i).getScoreCard().getScores()[j].isRetained()) && (players.get(i).getScoreCard().getScores()[j].isNotTotalOrBonus())){
                        StackPane scorePane = (StackPane) getGridNode(grid,i+1,j+1);
                        Rectangle background = (Rectangle) scorePane.getChildren().get(0);
                        players.get(i).keepScore(j);
                        background.setFill(Color.GREEN);//highlight the score with green
                        background.setOpacity(1);
                    }
                }
            }
            
            enableCurrentPlayer();
            updateRollButton();
            
            messageDisplay.setText("Gamesave loaded at " + d.format(now));
        } catch (Exception ex) {
            messageDisplay.setText("An error occurred loading the game: " + ex.getLocalizedMessage());
            throw(ex);
        }
    }
    
    @FXML
    private void saveToDisk() throws IOException {// saves data for this game to a text file for later
        try {
            // Timestamp
            DateTimeFormatter d = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            
            FileWriter fw = new FileWriter(saveFile);
            
            // Write rolls remaining
            fw.write("R=" + rollCounter + "\n");
            
            // Write current player index
            fw.write("P=" + currentPlayerIndex + "\n");
            
            // Write subscores for each player
            for (Player currPlayer : players) {
                fw.write("[" + currPlayer.getName() + "]="); // player name
                fw.write("{ ");

                // Pull each subscore from the current scorecard
                for (int j = 0; j < currPlayer.getScoreCard().getScores().length; j++) {
                    Score score = currPlayer.getScoreCard().getScore(j);
                    fw.write((score.isRetained() || !score.isNotTotalOrBonus() ? score.getValue() : "X") + " ");
                }

                fw.write("}\n");
            }
            
            fw.close();
            
            loadButton.setDisable(!saveFile.exists());
            
            messageDisplay.setText("Game progress saved at " + d.format(now));
        }
        catch (Exception ex) {
            messageDisplay.setText("An error occurred saving the game: " + ex.getLocalizedMessage());
            throw(ex);
        }
    }

    @FXML
    private void holdDie(ActionEvent actionEvent) {
        ToggleButton dieButton = (ToggleButton) actionEvent.getSource();
        int id = Integer.parseInt(dieButton.getId().substring("die".length())) - 1;
        dice.getDice()[id].hold((dice.getDice()[id].isNotHeld()));
    }

    @FXML
    private void rollDice() { //rolls the dice and updates each die image accordingly
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
        saveButton.setDisable(false);
    }

    @FXML
    private void updateScores(){ //updates the scores for the current player
        ScoreCard scoreCard = new ScoreCard(players.get(currentPlayerIndex).getScoreCard().getScores(), dice.getDice());//creates a new scorecard with the current players scorecard and the dice on board
        scoreCard.calculateScores();
        players.get(currentPlayerIndex).updateScoreCard(scoreCard);
        for(int i = 0; i < fields; ++i){
            enableScore(scoreCard.getScore(i), currentPlayerIndex + 1, i + 1);
        }
    }

    private void enableScore(Score score, int col, int row){//highlights which scores are valid and available for keeping
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

    private void keepScore(MouseEvent mouseEvent, int index){//when score is clicked, keep said score and end the player's turn
        StackPane scorePane = (StackPane) mouseEvent.getSource();
        Rectangle background = (Rectangle) scorePane.getChildren().get(0);
        players.get(currentPlayerIndex).keepScore(index);
        background.setFill(Color.GREEN);//highlight the score with green
        background.setOpacity(1);
        endTurn();//end the player's turn
    }

    private boolean gameOver(){//checks if all score cells are filled, if so, it returns true
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

    private Player winner(){//determines which player has the highest score, and returns the messageDisplay
        int highScore = 0;
        Player winner = null;
        for(Player player: players) {
            if (player.getScoreCard().getScore(fields - 1).getValue() > highScore) {
                winner = player;
                highScore = player.getScoreCard().getScore(fields - 1).getValue();
            }
            else if(player.getScoreCard().getScore(fields-1).getValue() == highScore && !winner.getName().equals(player.getName())){//calculates if there is more than one highscore holder (draw)
                winner = new Player("Neither");//draw condition
            }
        }
        return winner;
    }

    private void endTurn() {//ends the turn of the current player when a score is selected
        disableDice();
        finalizeScores();
        if(gameOver()){
            Player winner = winner();
            messageDisplay.setText(String.format("%s wins with %d points!", winner.getName(),winner.getScoreCard().getScore(fields-1).getValue()));
        }
        else{
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            rollCounter = rolls;
            updateRollButton();
            enableCurrentPlayer();
        }
    }

    private void updateRollButton(){//updates the text on the roll button to reflect how many rolls are left
        rollButton.setText(String.format("Roll (%d left)", rollCounter));
        rollButton.setDisable(rollCounter < 1);
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

    private void disableDice() {//disables the dice buttons so that they cannot be held before a roll
        ToggleButton[] diceButtons = new ToggleButton[]{die1,die2,die3,die4,die5};
        for(int i = 0; i < diceButtons.length; ++i){
            dice.getDice()[i].hold(false);
            diceButtons[i].setSelected(false);
            diceButtons[i].setDisable(true);
        }
    }

    private void finalizeScores() {//for each field, finalize the score
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

    private Node getGridNode(GridPane gridPane, int col, int row){//gets grid node based on column and row
        for (Node node : gridPane.getChildren()) {
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row){
                return node;
            }
        }
        return null; //no grid node found, shouldn't happen
    }

    private void setDieImage(ToggleButton die, int dieFace){//sets the image on the die button used to represent the face
        if(die.isDisabled()){
            die.setDisable(false);
        }
        ImageView iView;
        Image face;
        switch(dieFace){//different images for different face values
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
    private void registerPlayers(List<Player> players) {//used to set up the list of players input from the main menu
        this.players = players;
    }
}