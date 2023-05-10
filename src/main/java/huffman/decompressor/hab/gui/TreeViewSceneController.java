package huffman.decompressor.hab.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

public class TreeViewSceneController {
    @FXML
    private Pane paneT;
    private Scene sceneTT;
    @FXML
    private ScrollPane scrollPaneT;

    DragabbleM dragabble2 = new DragabbleM();

    DragabbleM dragabble = new DragabbleM();


    public void switchToTreeView(ActionEvent e) throws IOException {

        Stage stageT = (Stage) ((Node) e.getSource()).getScene().getWindow();



        // Button button = new Button("Button");

        paneT = new Pane();
        //paneT.getChildren().add(button);


        BorderPane borderPanT = new BorderPane();

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


        Scene scene = new Scene(borderPanT, 640, 640);
        this.zoom(paneT,label);
        stageT.setScene(scene);
        stageT.show();


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
}
