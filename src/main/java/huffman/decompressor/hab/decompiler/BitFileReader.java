package huffman.decompressor.hab.decompiler;

import java.io.*;


public class BitFileReader {
    private static BitFileReader instance;
    private static long buffor;
    private static int unreadBits;
    private static FileInputStream file;
    private static DataInput byteFile;
    private BitFileReader(String FileName) throws Exception {
        try {
            file = new FileInputStream(FileName);
            byteFile = new DataInputStream(file);
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

    // Implementation of function 'readExactBits'
    public static int readNBits(int n) throws Exception {
        Log.println("To read: " + n);
        while(unreadBits < n){
            long a = 0;
            if(n - unreadBits <= 8){
                a = (long) byteFile.readUnsignedByte();

                Log.println(BitFileReader.toBiteString(a, 8));
                unreadBits += 8;
            }else{
                a = (long) byteFile.readUnsignedShort();
                Log.println(BitFileReader.toBiteString(a, 16));
                unreadBits += 16;
            }
            Log.println("A before: " + BitFileReader.toBiteString(a, 64));
            a <<= 32 - unreadBits;
            Log.println("A before: " + BitFileReader.toBiteString(a, 64));
            buffor += a;
        }
        // Read
        long result = buffor;
        Log.println("Result before: " + BitFileReader.toBiteString(result, 64));
        result >>>= 32 - n;
        Log.println("Result after: " + BitFileReader.toBiteString(result, 64));
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
        return file.available();
    }

}
