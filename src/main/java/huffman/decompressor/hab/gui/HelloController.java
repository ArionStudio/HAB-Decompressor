package huffman.decompressor.hab.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Affine;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import printingTree.TreeNode;

import javax.swing.*;
import java.awt.MouseInfo;

import java.awt.dnd.DragGestureEvent;
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
    @FXML
    private Pane paneT;
    private BorderPane borderPanT;
    private Scene sceneTT;
    @FXML
    private ScrollPane scrollPaneT;
    DragabbleM dragabble = new DragabbleM();
    DragabbleM dragabble2 = new DragabbleM();
    public void switchToTreeView(ActionEvent e) throws IOException {

        stageT = (Stage)((Node)e.getSource()).getScene().getWindow();



       // Button button = new Button("Button");

        paneT = new Pane();
        //paneT.getChildren().add(button);


        borderPanT = new BorderPane();

        //scrollPaneT = new ScrollPane(new Group(paneT));
        scrollPaneT = new ScrollPane();
        scrollPaneT.setContent(paneT);
        borderPanT.setCenter(scrollPaneT);

        Label label = new Label(draw());
        //label.setLayoutX(400);
        //label.setLayoutY(20);


        //dragabble2.makeitdrag(paneT,1);
        paneT.getChildren().add(label);
        //dragabble2.makeitdrag(paneT,1);
        dragabble.makeitdrag(label,1);


        scene = new Scene(borderPanT, 640, 640);
        this.zoom(paneT,label);
        stageT.setScene(scene);
        stageT.show();


    }
    public void zoom( Pane pane,Label label) {
        pane.setOnScroll(
                new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent event) {
                        double zoomFactor = 1.05;
                        double deltaY = event.getDeltaY();
                        int xa,ya;
                        if (deltaY < 0){
                            zoomFactor = 0.95;
                        }
                        xa = MouseInfo.getPointerInfo().getLocation().x;
                        ya = MouseInfo.getPointerInfo().getLocation().y;
                        //pane.setScaleX(pane.getScaleX() * zoomFactor);
                        //pane.setScaleY(pane.getScaleY() * zoomFactor);
                        //dragabble.makeitdrag(pane);
                        label.setScaleX(label.getScaleX()* zoomFactor);
                        label.setScaleY(label.getScaleY()* zoomFactor);
                        //dragabble2.makeitdrag(pane,4);
                        dragabble.makeitdrag(label,1);

                        System.out.println("LX: "+label.getLayoutX()+" LY: "+label.getLayoutY()+"\n"+"X: "+pane.getLayoutX()+" Y: "+pane.getLayoutY()+"\n"+"MouseX: "+xa+" MOuseY: "+ya);
                        event.consume();
                    }
                });

    }

    @FXML
    protected String draw() {
        ArrayList<ArrayList<Short>> simpleTree = new ArrayList<>();
        TreeNode tree = new TreeNode(3,simpleTree);
        tree.printTree();
        //ShowTree.setText(tree.getTree());
        return(tree.getTree());
        //System.out.println(tree.getTree());
        //return("lol");
    }
    private class DragabbleM{
        double mouseX;
        double mouseY;

        public void makeitdrag(Node node,double zoomi){
            node.setOnMousePressed(mouseEvent -> {
                mouseX = zoomi*mouseEvent.getX();
                mouseY = zoomi*mouseEvent.getY();

            });
            node.setOnMouseDragged(mouseEvent -> {
                node.setLayoutX(mouseEvent.getSceneX()*zoomi - mouseX);
                node.setLayoutY(mouseEvent.getSceneY()*zoomi - mouseY);
                System.out.println("X: "+mouseEvent.getX()+" Y: "+mouseEvent.getY());
            });
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}