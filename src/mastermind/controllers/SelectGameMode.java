package mastermind.controllers;

import java.io.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SelectGameMode {

    private String gameMode;

    @FXML
    void onGuessModeClick(ActionEvent event) throws IOException {
        gameMode = "guess";
        loadGameBoard(event);
    }
    
    @FXML
    void onCreateModeClick(ActionEvent event) throws IOException {
        gameMode = "create";
        loadGameBoard(event);
    }

    private void loadGameBoard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mastermind/gui/fxml/GameBoard.fxml"));
        Parent parent = loader.load();

        GameBoard gameBoard = loader.getController();
        gameBoard.setGameMode(gameMode);
        
        Scene gameBoardScene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(gameBoardScene);
        window.show();
    }
}
