package huffman.decompressor.hab.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class GetInfoSceneController {

    public Button toDecompress;
    public Label noText;
    public Label compressed;
    public Label welcomeText;
    public Label encrypted;
    public Label outfile;
    public Label filePath;
    @FXML
    private Label fileSize;
    private final FileChooser fileChooser = new FileChooser();

    public void switchToFinalScene(ActionEvent e) throws IOException {
        Parent parentF = FXMLLoader.load(getClass().getResource("Final-and-tree-there-is.fxml"));
        Stage stageF = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneF = new Scene(parentF);
        stageF.setScene(sceneF);
        stageF.show();
    }

    @FXML
    protected void setFileInfo(Map<String, Object> fileInfo) throws IOException {
        byte c = (Byte) fileInfo.get("compressed");
        boolean e = (Boolean) fileInfo.get("encrypted");
        if(c == 1 && e){
//            toDecompress.setDisable(false);
        }else{
//            toDecompress.setDisable(true);
        }
        compressed.setText("Compressed: " + (c > 0) );
        encrypted.setText("Encrypted: " + e );
        fileSize.setText("File size: " + fileInfo.get("size"));
        filePath.setText("File path: " + fileInfo.get("file_path"));
    }

    @FXML
    protected void getFileInfo(){

    }

    @FXML
    protected void fileWhereWillBeDecompressed() {
        File fileD = fileChooser.showOpenDialog(null);
        if(fileD != null) {
            outfile.setText("Outfile: "+ fileD.toString());
        }
        else{
            //status = funkcja adiego z arguemntami -file -i -basic
            welcomeText.setText("You didn't choose any file!" );
        }
    }
}
