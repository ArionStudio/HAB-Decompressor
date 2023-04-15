module huffman.decompressor.hab {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens huffman.decompressor.hab to javafx.fxml;
    exports huffman.decompressor.hab;
    exports huffman.decompressor.hab.gui;
    opens huffman.decompressor.hab.gui to javafx.fxml;
}