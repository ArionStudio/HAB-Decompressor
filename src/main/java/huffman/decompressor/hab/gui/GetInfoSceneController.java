package huffman.decompressor.hab.gui;

import huffman.decompressor.hab.decompiler.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetInfoSceneController {

    public Button toDecompress;
    public Label noText;
    public Label compressed;
    public Label welcomeText;
    public Label encrypted;
    public Label outfile;
    public Label filePath;
    public Button toDecrypt;
    public TextField passInput;
    public Label status;
    @FXML
    private Label fileSize;
    private final FileChooser fileChooser = new FileChooser();

    private String outFilePath;
    private String inFilePath;
    @FXML
    private String password;

    public void switchToFinalScene(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Final-and-tree-there-is.fxml"));
        Parent parent = loader.load();
        TreeViewSceneController controller = loader.getController();
        HashMap<String, Object> map;
        password = passInput.getText();
        status.setText("Loading...");
        if(password.length() == 0){
            map = Controller.decompressFile(new String[]{"-i", inFilePath, "-o", outFilePath});
        }else{
            map = Controller.decompressFile(new String[]{"-i", inFilePath, "-o", outFilePath, "-d", password});
        }
        map.put("in_file_path", inFilePath);


        controller.setDecompressedFileInfo(map);

        Stage stageF = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneF = new Scene(parent);
        stageF.setScene(sceneF);
        stageF.show();
    }
    @FXML
    protected void setToPrimaryScene(ActionEvent e) throws IOException {
        Parent parentF = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stageF = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene sceneF = new Scene(parentF);
        stageF.setScene(sceneF);
        stageF.show();
    }

    @FXML
    protected void setFileInfo(Map<String, Object> fileInfo) throws IOException {
        byte c = (Byte) fileInfo.get("compressed");
        boolean e = (Boolean) fileInfo.get("encrypted");
        System.err.println(e + "  " + c);
        toDecompress.setVisible(c > 0);
        passInput.setVisible(e );
        toDecrypt.setVisible(e && c == 0);
        compressed.setText("Compressed: " + (c > 0) );
        encrypted.setText("Encrypted: " + e );
        fileSize.setText("File size: " + fileInfo.get("size"));
        inFilePath = (String) fileInfo.get("file_path");
        filePath.setText("File path: " + inFilePath);
        outFilePath = "decompressed\\result.txt";
        password = passInput.getText();
    }

    @FXML
    protected void getFileInfo(){

    }

    @FXML
    protected void fileWhereWillBeDecompressed() {
        File fileD = fileChooser.showOpenDialog(null);
        if(fileD != null) {
            outFilePath = fileD.getAbsolutePath();
            outfile.setText("Outfile: "+ outFilePath);
        }
        else{
            outfile.setText("You didn't choose any file! [Swap to default: result.txt]" );
        }
    }
}
