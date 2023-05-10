package huffman.decompressor.hab.gui;

import huffman.decompressor.hab.decompiler.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import printingTree.TreeNode;

import java.awt.MouseInfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    private FileChooser fileChooser = new FileChooser();

    private String filePath;
    @FXML
    private Label welcomeText;
    @FXML
    private Label ShowTree;
    @FXML
    private Label noText;

    @FXML
    private Label compressed;
    @FXML
    private Label encrypted;
    @FXML
    private Label outfile;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    public void setCompressedInfo() {
        noText.setText("File : Compressed");
    }

    @FXML
    protected void getFileDataAndGoToScene(ActionEvent e) throws IOException {
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            filePath = file.getAbsolutePath();
            Map<String, Object> map = Controller.getBasicFileData(new String[]{"-i", filePath});
            if(((Boolean) map.get("file_status"))){
                switchToInfoScene(e, (Map<String, Object>)map.get("file_info"));
            }else{
                switchToErrorScene(e, (HashMap<String, Object>)map.get("error"));
            }
        }
        else{
            HashMap<String, Object> error = new HashMap<>();
            error.put("code", 1);
            error.put("message", "File not choose");
            switchToErrorScene(e, error);
        }
    }


    public void switchToInfoScene(ActionEvent e, Map<String, Object> fileInfo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Correct-verified-file-scene.fxml"));
        Parent parent = loader.load();
        GetInfoSceneController controller = loader.getController();
        controller.setFileInfo(fileInfo);


        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToErrorScene(ActionEvent e, HashMap<String, Object> error) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Error-scene.fxml"));
        Parent parent = loader.load();
        ErrorSceneController controller = loader.getController();
        controller.setError(error);

        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}