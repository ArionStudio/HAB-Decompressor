package huffman.decompressor.hab.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import printingTree.TreeNode;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private int status;
    private File fileD;
    private Stage thisStage;
    private FXMLLoader thisFMXL;
    private FileChooser fileChooser = new FileChooser();
    @FXML
    private Button fileExplorer;
    @FXML
    private Button toDecompress;
    @FXML
    private Label welcomeText;
    @FXML
    private Label ShowTree;
    @FXML
    private Label noText;

    @FXML
    private Label errorMessage;
    @FXML
    private Label errorCode;
    @FXML
    private Label compressed;
    @FXML
    private Label encrypted;
    @FXML
    private Label fileSize;
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
    protected void openFileExplorer() {
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            welcomeText.setText("The file you want to decompress: " + file.toString());
        }
        else{
            //status = funkcja adiego z arguemntami -file -i -basic
            welcomeText.setText("You didn't choose any file!" );
        }
    }
    @FXML
    protected void fileWhereWillBeDecompressed() {
        fileD = fileChooser.showOpenDialog(null);
        if(fileD != null) {
            outfile.setText("Outfile: "+ fileD.toString());
        }
        else{
            //status = funkcja adiego z arguemntami -file -i -basic
            welcomeText.setText("You didn't choose any file!" );
        }
    }
    @FXML
    protected void nobasek() throws IOException {
        welcomeText.setText("Zly plik!");
        errorMessage.setText("Error message: "+"Głupiś ty");
        errorCode.setText("Error code: "+777);
    }
    @FXML
    protected void sukces() throws IOException {
        boolean c = true;
        boolean e = true;
        if(c && e){
            toDecompress.setDisable(false);
        }else{
            toDecompress.setDisable(true);
        }
        welcomeText.setText("Dobry plik!");
        compressed.setText("Compressed: "+ c );
        encrypted.setText("Encrypted: "+ e );
        fileSize.setText("File Size: "+"DUZOOOO "+"MG");

    }

    private Stage stage;
    private Stage stageF;
    private Stage stageT;
    private Scene scene;
    private Scene sceneF;
    private Scene sceneT;
    private Parent parent;
    private Parent parentF;
    private Parent parentT;
    @FXML
    public void changingScreens(ActionEvent e) throws IOException {
        int f = 1;
        if(f == 0){
            switchToScene2(e);
            //setCompressedInfo();
        }else{
            switchToScene1(e);
        }
    }
    public void switchToScene1(ActionEvent e) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("Correct-verified-file-scene.fxml"));
        stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene2(ActionEvent e) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("Error-scene.fxml"));
        stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFinalScene(ActionEvent e) throws IOException {
        parentF = FXMLLoader.load(getClass().getResource("Final-and-tree-there-is.fxml"));
        stageF =(Stage)((Node)e.getSource()).getScene().getWindow();
        sceneF = new Scene(parentF);
        stageF.setScene(sceneF);
        stageF.show();
    }
    public void switchToTreeView(ActionEvent e) throws IOException {

        parentT = FXMLLoader.load(getClass().getResource("Tree-View.fxml"));
        stageT =(Stage)((Node)e.getSource()).getScene().getWindow();
        sceneT = new Scene(parentT);
        stageT.setScene(sceneT);
        stageT.show();
    }
    @FXML
    protected void draw() {
        ArrayList<ArrayList<Short>> simpleTree = new ArrayList<>();
        TreeNode tree = new TreeNode(3,simpleTree);

        ShowTree.setText(tree.getTree());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}