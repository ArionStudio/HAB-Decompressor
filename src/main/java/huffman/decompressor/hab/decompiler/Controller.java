package huffman.decompressor.hab.decompiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private static GetOpt getOpt;

    // It's only test function
    public static void main(String[] args) {
        HashMap<String, Object> back = new HashMap<>();
        try {
            getOpt = new GetOpt(args, new String[]{"h", "m"}, new String[]{"i", "o", "d"});
        } catch (GetOpt.OptExeption e) {
            getOptExeptionMap(back, e);
        } catch (Exception e) {
            getExeptionMap(back, e);
        }
        try {
            String fileName = getOpt.getOption("i");
            String inFilePath = "C:\\Users\\Adrian\\OneDrive - Politechnika Warszawska\\SEMEMESTR 2\\JIMP2\\Coder_Huffman_in_C\\KAPPA\\data\\" + fileName;
            String password = getOpt.getOption("d");
            String outFilePath = getOpt.getOption("o");

//            getBasicFileData(new String[]{"-i", filePath});
            decompressFile(new String[]{"-i", inFilePath, "-d", password, "-o", outFilePath});

        } catch (Exception e) {
            Log.error("File not found");
            Log.error(e.getMessage());
        }

    }

    //TODO: MAKE ORDER WHITH CODES
    public static HashMap<String, Object> getBasicFileData(String[] args) {
        HashMap<String, Object> back = new HashMap<>();
        try {
            getOpt = new GetOpt(args, new String[]{"h", "m"}, new String[]{"i", "o", "d"});
            Decompiler decompiler = new Decompiler(getOpt.getOption("i"), "");
            decompiler.getBasicInfo();
            HashMap<String, Object> file_info = decompiler.getBasicInfoAsMap();
            back.put("file_status", true);
            back.put("file_info", file_info);

        } catch (GetOpt.OptExeption e) {
            getOptExeptionMap(back, e);
        } catch (Exception e) {
            getExeptionMap(back, e);
        }
        return back;
    }


    private static void getOptExeptionMap(HashMap<String, Object> back, GetOpt.OptExeption e) {
        HashMap<String, Object> error = new HashMap<>();
        error.put("code", e.getErrorCode());
        error.put("message", e.getMessage());
        back.put("file_status", Boolean.FALSE);
        back.put("error", error);
    }

    private static void getExeptionMap(HashMap<String, Object> back, Exception e) {
        HashMap<String, Object> error = new HashMap<>();
        error.put("code", 9);
        error.put("message", e.getMessage());
        back.put("file_status", Boolean.FALSE);
        back.put("error", error);
    }

    public static ArrayList<ArrayList<Short>> getHuffmanTreeAsArray(String[] args) throws Exception {
        getOpt = new GetOpt(args, new String[]{"h", "m"}, new String[]{"i", "o", "d"});
        Decompiler decompiler = new Decompiler(getOpt.getOption("i"), "");
        decompiler.getBasicInfo();
        return decompiler.getHuffmanTreeAsArray();
    }

    public static void decompressFile(String [] args) throws Exception {
        getOpt = new GetOpt(args, new String[]{"h", "m"}, new String[]{"i", "o", "d"});
        String password = getOpt.getOption("d");
        String inFilePath = getOpt.getOption("i");
        String outFilePath = getOpt.getOption("o");
        Decompiler decompiler = new Decompiler(inFilePath, password);
        decompiler.getBasicInfo();
        decompiler.decompressFile(outFilePath);
    }
}