package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.ResourceBundle;

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

    public GameViewController(){
        player = new Player("bob", PlayerType.Human);
        AI = new Player("jens",PlayerType.AI);
        gameManager = new GameManager(player,AI);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void MakeMove(ActionEvent actionEvent) {
        Button btnclicked = (Button) actionEvent.getSource();

        if (btnclicked == Rock){
            playerMove = Move.Rock;
        }
        if (btnclicked == Paper){
            playerMove = Move.Paper;
        }
        if (btnclicked == Scissor){
            playerMove = Move.Scissor;
        }
        gamePlay();
    }

    private void gamePlay(){
        gameManager.playRound(playerMove);
        Result result = null;
        int botwins = 0;
        int playerwins = 0;
        for (Result result1:gameManager.getGameState().getHistoricResults()){
            if (result1.getWinnerPlayer().getPlayerType()==PlayerType.AI && result1.getType()!=ResultType.Tie){
                botwins++;
               }
            if (result1.getWinnerPlayer().getPlayerType()==PlayerType.Human && result1.getType()!=ResultType.Tie){
                playerwins ++;
            }

            result = result1;
        }
        if (result.getType() == ResultType.Win){
            Results.setText(result.getWinnerPlayer().getPlayerType()+"Winner");
        }
        if (result.getType()== ResultType.Tie){
            Results.setText(result.getWinnerPlayer().getPlayerType()+"No one wins,try again");
        }

        winsLoses.setText("Player wins "+playerwins + " Bot wins" + botwins);
    }

    public void setPlayerName(ActionEvent actionEvent) {

        PlayerName.setText(setName.getText());
        setName.setVisible(false);
        Botname.setText("Hal");
        setplayerName.setVisible(false);
    }


}
