package huffman.decompressor.hab.decompiler;

public class Log {

    public static void println(String line){
        System.out.println("[Log]: " + line);
    }

    public static void error(String line){
        System.err.println("[Error]: " + line);
    }
}
