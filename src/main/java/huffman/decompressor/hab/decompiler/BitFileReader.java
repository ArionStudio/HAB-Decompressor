package huffman.decompressor.hab.decompiler;

import java.io.*;


public class BitFileReader {
    private static String fileName;
    private static BitFileReader instance;
    private static long buffor;
    private static int unreadBits;
    private static FileInputStream file;
    private static DataInput byteFile;
    private static long fileSize;
    private BitFileReader(String FileName) throws Exception {
        buffor = 0;
        unreadBits = 0;
        try {
            file = new FileInputStream(FileName);
            fileSize = file.available();
            byteFile = new DataInputStream(file);
            fileName = FileName;
        } catch (FileNotFoundException e) {
            throw new Exception(e);
        }
    }

    public static void getInstance(String FileName) throws Exception {
        instance = new BitFileReader(FileName);
    }

    public static void resetReader() throws Exception {
        buffor = 0;
        unreadBits = 0;
        try {
            file = new FileInputStream(fileName);
            byteFile = new DataInputStream(file);
        } catch (FileNotFoundException e) {
            throw new Exception(e);
        }
    }

    public static long getFileSize(){
        return fileSize;
    }

    // Implementation of function 'readExactBits'
    public static int readNBits(int n) throws Exception {
        while(unreadBits < n){
            long a = 0;
            if(n - unreadBits <= 8){
                a = (long) byteFile.readUnsignedByte();
                unreadBits += 8;
            }else{
                a = (long) byteFile.readUnsignedShort();
                unreadBits += 16;
            }
            a <<= 32 - unreadBits;
            buffor += a;
        }
        // Read
        long result = buffor;
        result >>>= 32 - n;
        unreadBits -= n;
        buffor <<= n;
        buffor &= 0xfFFFFFFFL;

        return (int) result;
    }

    public static short decrypt(byte  text, String password){
        short result = text;
        for (int i = 0; i < password.length(); i++) {
            result ^= (byte) password.charAt(i);
        }
        if(result < 0){
            result += 256;
        }
        return result;
    }


    public static int readNBitsWithPassword(int n, String password) throws IOException {
        while(unreadBits < n){
            long a = 0;
            if(n - unreadBits < 8){
                a = (long) byteFile.readUnsignedByte();
                a = (long) decrypt((byte)a, password);
                unreadBits += 8;
            }
            a <<= 32 - unreadBits;
            buffor += a;
        }
        // Read
        long result = buffor;
        result >>>= 32 - n;
        unreadBits -= n;
        buffor <<= n;
        buffor &= 0xfFFFFFFFL;

        return (int) result;
    }

    public static String toBiteString(long x, int bites){
        StringBuilder bitesString = new StringBuilder();
        int j = 0;
        for(int i = 0; i < bites; i++){
            bitesString.append(x % 2);
            x >>= 1;
        }
        return bitesString.reverse().toString();
    }

    public static int readUnreadBits() throws Exception {
        return readNBits(unreadBits);
    }


    public static int byteToEnd() throws IOException {
        return file.available() * 8;
    }

    public static int getUnreadBits() {
        return unreadBits;
    }

    public static long getBuffor() {
        return buffor;
    }
}
