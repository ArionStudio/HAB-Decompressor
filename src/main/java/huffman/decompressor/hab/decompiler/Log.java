package huffman.decompressor.hab.decompiler;

public class Log {

    public static void println(String line){
        System.out.println("[Log]: " + line);
    }
    public static void printf(String format, String line){
        System.out.printf(format, "[Log]: " + line + "\n");
    }
    public static void error(String line){
        System.err.println("[Error]: " + line);
    }
}
