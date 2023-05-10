package huffman.decompressor.hab.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {
    private static int zalezna;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("hello-view.fxml"));
        Scene scenePrimary = new Scene(fxmlLoader.load(), 1360, 720);
        primaryStage.setTitle("HAB Decompressor");

        primaryStage.setScene(scenePrimary);
        primaryStage.show();
    }

    public static void startGui() {
        launch();
    }


}