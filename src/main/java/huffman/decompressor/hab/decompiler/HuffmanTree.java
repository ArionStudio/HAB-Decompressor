package huffman.decompressor.hab.decompiler;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HuffmanTree {
    static HuffmanTreeEntry root;
    static HuffmanTree instance;

    private HuffmanTree(String FileName) {

    }

    public static HuffmanTree getInstance(String FileName) throws Exception {
        if (instance == null) {
            instance = new HuffmanTree(FileName);
        }
        return instance;
    }


}
