package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML
    private Button Paper;

    @FXML
    private Label PlayerName;

    @FXML
    private Button Rock;

    @FXML
    private Button Scissor;

    @FXML
    private Label draws;

    @FXML
    private Label loses;

    @FXML
    private TextField setName;

    @FXML
    private Label wins;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setPlayerName(ActionEvent actionEvent) {

        PlayerName.setText(setName.getText());
        setName.setVisible(false);

    }
}
