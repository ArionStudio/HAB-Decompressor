package huffman.decompressor.hab.decompiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private GetOpt getOpt;

    public void main(String[] args) {
        HashMap<String, Object> back = new HashMap<>();
        try {
            getOpt = new GetOpt(args, new String[]{"h", "m"}, new String[]{"i", "o", "d"});
        } catch (GetOpt.OptExeption e) {
            getOptExeptionMap(back, e);
        } catch (Exception e) {
            getExeptionMap(back, e);
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

    //TODO: MAKE ORDER WHITH CODES
    public HashMap<String, Object> getBasicFileData(String[] args) {
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

    private void getOptExeptionMap(HashMap<String, Object> back, GetOpt.OptExeption e) {
        HashMap<String, Object> error = new HashMap<>();
        error.put("code", e.getErrorCode());
        error.put("message", e.getMessage());
        back.put("file_status", Boolean.FALSE);
        back.put("error", error);
    }

    private void getExeptionMap(HashMap<String, Object> back, Exception e) {
        HashMap<String, Object> error = new HashMap<>();
        error.put("code", 9);
        error.put("message", e.getMessage());
        back.put("file_status", Boolean.FALSE);
        back.put("error", error);
    }

    public ArrayList<ArrayList<Short>> getHuffmanTreeAsArray(String[] args) throws Exception{
        getOpt = new GetOpt(args, new String[]{"h", "m"}, new String[]{"i", "o", "d"});
        Decompiler decompiler = new Decompiler(getOpt.getOption("i"));
        decompiler.getBasicInfo();
        return decompiler.getHuffmanTreeAsArray();
    }
}