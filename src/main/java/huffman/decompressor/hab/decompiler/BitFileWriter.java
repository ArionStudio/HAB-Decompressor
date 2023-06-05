package huffman.decompressor.hab.decompiler;

import java.io.*;

public class BitFileWriter {
    private static String fileName;
    private static BitFileWriter instance;
    private static long buffor;
    private static int unwriteBits;
    private static FileOutputStream file;
    private static DataOutput byteFile;
    private BitFileWriter(String FileName) throws FileNotFoundException {
        buffor = 0;
        unwriteBits = 0;
        file = new FileOutputStream(FileName);
        byteFile = new DataOutputStream(file);
        fileName = FileName;
    }

    public static BitFileWriter getInstance(String FileName) throws Exception {
        if (instance == null) {
            instance = new BitFileWriter(FileName);
        }
        return instance;
    }

    public static long getFileSize(){
        try{
            file.close();
            FileInputStream inFile = new FileInputStream(fileName);
            return inFile.available();
        }catch (FileNotFoundException e){
            return 0;
        }catch (IOException IOe){
            return 0;
        }
    }


    public  static void fileClose(){
        try {
            if(file != null){
                file.close();
            }
        } catch (IOException e) {
            System.err.println("File is closed already!");
        }
    }

    public static void writeExactBits(int bits, long data) throws IOException {
        data <<= 64 - (unwriteBits + bits);
        buffor += data;
        unwriteBits += bits;
        while (unwriteBits >= 8) {
            long toSave = buffor & 0xFF00000000000000L;
            toSave >>>= 64 - 8;
            byteFile.writeByte((byte)toSave);
            // We move new 8 bit beside of bits readed earlier
            buffor <<= 8;
            unwriteBits -= 8;  // We now have 8 + lastRest bits in buffor
        }
    }

}
