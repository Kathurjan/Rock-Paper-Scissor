package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static rps.bll.player.PlayerType.Human;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {
    public Label winsLoses;
    @FXML
    private Label Botname;
    @FXML
    private Button Paper;
    @FXML
    private Label PlayerName;
    @FXML
    private Button Rock;
    @FXML
    private Button Scissor;
    @FXML
    private Button setplayerName;
    @FXML
    private Label Results;
    @FXML
    private TextField setName;
    private IPlayer player;
    private IPlayer AI;
    private GameManager gameManager;
    Move playerMove;
    private boolean validated = false;

    public GameViewController() {
       /*try {player = new Player(PlayerName.getText(), PlayerType.Human);}
        catch(NullPointerException ex){
            System.out.println(ex);
        }*/
        player = new Player("bob", Human);
        AI = new Player("jens", PlayerType.AI);
        gameManager = new GameManager(player, AI);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void checkPlayerMove(ActionEvent actionEvent) {


        Button btnclicked = (Button) actionEvent.getSource();

        if (validated == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Name missing");
            alert.setHeaderText("set a name");
            alert.setContentText("please write a name");
            alert.showAndWait();
        } else if (btnclicked == Rock && setName != null) {
            playerMove = Move.Rock;
            startGame();


        } else if (btnclicked == Paper && setName != null) {
            playerMove = Move.Paper;
            startGame();

        } else if (btnclicked == Scissor && setName != null) {
            playerMove = Move.Scissor;
            startGame();

        }
    }


    private void startGame() {
        gameManager.playRound(playerMove);
        Result result = null;
        int botwins = 0;
        int playerwins = 0;

        for (Result rs : gameManager.getGameState().getHistoricResults()) {
            if (rs.getWinnerPlayer().getPlayerType() == PlayerType.AI && rs.getType() != ResultType.Tie) {
                botwins++;

            }
            if (rs.getWinnerPlayer().getPlayerType() == Human && rs.getType() != ResultType.Tie) {
                playerwins++;

            }

            result = rs;
        }
        if (result.getType() == ResultType.Win && result.getWinnerPlayer().getPlayerType() == Human) {
            Results.setText(PlayerName.getText() + " Wins");
        }
        if (result.getType() == ResultType.Win && result.getWinnerPlayer().getPlayerType() == PlayerType.AI) {
            Results.setText(getBotName() + " wins");
        }
        if (result.getType() == ResultType.Tie) {
            Results.setText("No one wins,try again");
        }

        winsLoses.setText("Player wins: " + playerwins + " Bot wins: " + botwins);


    }







    public void setPlayerName(ActionEvent actionEvent) {

        PlayerName.setText(setName.getText());
        setName.setVisible(false);
        setplayerName.setVisible(false);
        validated = true;

    }
    private String getBotName(){
        String[] botNames = new String[]{
       "HAL",
       "Ash",
        "Wall-E",
       "Ava",
       " T-800"};
        int random = new Random().nextInt(botNames.length);

        return botNames[random];


    }

}
