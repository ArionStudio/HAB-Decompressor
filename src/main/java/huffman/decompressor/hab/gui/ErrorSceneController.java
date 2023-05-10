package huffman.decompressor.hab.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ErrorSceneController implements Initializable {

    public Label error;
    public Label welcomeText;
    @FXML
    private Label errorCode;

    @FXML
    private Label errorMessage;

    public void setError(HashMap<String, Object> error) {
        errorCode.setText("Error code: " + error.get("code"));
        errorMessage.setText("Error message: " + error.get("message"));
    }

    @FXML
    protected void setToPrimaryScene(ActionEvent e) throws IOException {
        Parent parentF = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stageF = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneF = new Scene(parentF);
        stageF.setScene(sceneF);
        stageF.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
