package huffman.decompressor.hab.gui;

import huffman.decompressor.hab.decompiler.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import printingTree.TreeNode;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TreeViewSceneController {
    public Label action_time;
    public Label new_file_size;
    public Label new_file_path;
    @FXML
    private Pane paneT;
    private Scene sceneTT;
    @FXML
    private ScrollPane scrollPaneT;

    DragabbleM dragabble2 = new DragabbleM();

    DragabbleM dragabble = new DragabbleM();
    private String in_file_path;


    public void switchToTreeView(ActionEvent e) throws IOException {

        Stage stageT = (Stage) ((Node) e.getSource()).getScene().getWindow();
        paneT = new Pane();

        BorderPane borderPanT = new BorderPane();

        //scrollPaneT = new ScrollPane(new Group(paneT));
        scrollPaneT = new ScrollPane();
        scrollPaneT.setContent(paneT);
        borderPanT.setCenter(scrollPaneT);
        String treeInString = draw();
        if(Objects.equals(treeInString, "")){
            HashMap<String, Object> error = new HashMap<>();
            error.put("code", 10);
            error.put("message", "Array not created");
            switchToErrorScene(e, error);
        }else{
            Label label = new Label(treeInString);
            //label.setLayoutX(400);
            //label.setLayoutY(20);


            //dragabble2.makeitdrag(paneT,1);
            paneT.getChildren().add(label);
            //dragabble2.makeitdrag(paneT,1);
            dragabble.makeitdrag(label,1);


            Scene scene = new Scene(borderPanT, 640, 640);
            this.zoom(paneT,label);
            stageT.setScene(scene);
            stageT.show();
        }



    }

    public void setDecompressedFileInfo(HashMap<String, Object> decompressedFileInfo) {
        new_file_path.setText("Action time: " + decompressedFileInfo.get("out_file_path"));
        new_file_size.setText("New file size: " + decompressedFileInfo.get("decompressed_file_size"));
        action_time.setText("New file path: " + decompressedFileInfo.get("decompress_time"));
        in_file_path = (String) decompressedFileInfo.get("in_file_path");
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

    public void zoom( Pane pane,Label label) {
        pane.setOnScroll(
                new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent event) {
                        double zoomFactor = 1.3;
                        double deltaY = event.getDeltaY();
                        int xa,ya;
                        if (deltaY < 0){
                            zoomFactor = 0.7;
                        }else if(deltaY > 0){
                            zoomFactor = 1/0.7;
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
        try{
            ArrayList<ArrayList<Short>> simpleTree = Controller.getHuffmanTreeAsArray(new String[]{"-i", in_file_path});
            System.out.println(simpleTree);
            TreeNode tree = new TreeNode(simpleTree.size()-1,simpleTree);
            tree.printTree();
            //ShowTree.setText(tree.getTree());
            return(tree.getTree());
            //return "";
        }catch (Exception e){
            System.err.println(e.getMessage());
            return "";
        }
        //System.out.println(tree.getTree());
        //return("lol");
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
}
