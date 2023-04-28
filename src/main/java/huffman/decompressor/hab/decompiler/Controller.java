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
            String fileName = "C:\\Users\\Adrian\\OneDrive - Politechnika Warszawska\\SEMEMESTR 2\\JIMP2\\Coder_Huffman_in_C\\KAPPA\\data\\" + getOpt.getOption("i");
            Decompiler decompiler = new Decompiler(fileName);
            decompiler.getBasicInfo();
            Log.println(decompiler.toString());
            decompiler.makeHuffmanDictionary();
            decompiler.decompressFile();
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
            Decompiler decompiler = new Decompiler(getOpt.getOption("i"));
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
        Decompiler decompiler = new Decompiler(getOpt.getOption("i"));
        decompiler.getBasicInfo();
        return decompiler.getHuffmanTreeAsArray();
    }
}