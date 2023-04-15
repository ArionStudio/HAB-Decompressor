package huffman.decompressor.hab.decompiler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class BitFileReader {
    private static BitFileReader instance;
    private static int buffor;
    private static int unreadBits;
    private static FileInputStream file;
    private BitFileReader(String FileName) throws Exception {
        try {
            file = new FileInputStream(FileName);
        } catch (FileNotFoundException e) {
            throw new Exception(e);
        }
    }

    public static BitFileReader getInstance(String FileName) throws Exception {
        if (instance == null) {
            instance = new BitFileReader(FileName);
        }
        return instance;
    }

    public static void resetReader() throws IOException {
        buffor = 0;
        unreadBits = 0;
        file.reset();
    }


    public static int readNBits(int n){
//        try{
////            Bit
//        }
        return 0;
    }
}
