package huffman.decompressor.hab.decompiler;

public class Controller {
    public static void main(String[] args) {
        GetOpt getOpt;
        try{
            getOpt = GetOpt.getInstance(args, new String[]{"h", "m"}, new String[]{"i", "o", "d"});
        }catch (GetOpt.OptExeption e){
            if(e.getErrorCode() != 0){
                Log.error(e.getMessage());
            }
            return;
        } catch (Exception e) {
            return;
        }
        try {
            String fileName = "C:\\Users\\cylwi\\OneDrive\\Pulpit\\" + getOpt.getOption("i");
            Decompiler decompiler = new Decompiler(fileName);
            decompiler.getBasicInfo();
            Log.println(decompiler.toString());
            decompiler.makeHuffmanDictionary();
            decompiler.decompressFile();
        } catch (Exception e) {
            Log.error("File not found");
            Log.error(e.getMessage());
            return;
        }

    }
}