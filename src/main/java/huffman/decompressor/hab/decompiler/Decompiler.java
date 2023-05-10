package huffman.decompressor.hab.decompiler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Decompiler {

    private final String inputFileName;
    private String outputFileName;
    private  String authors;
    private boolean cryptedStatus;
    private boolean compressionStatus;
    private byte compressionLevel;
    private short validationCode;
    private short treeMaxDepth;
    private short treeMinDepth;
    private short treeLayerSize;
    private byte originUsslessBits;
    private boolean fileStatus;

    private ArrayList<ArrayList<Short>> treeLayerArray;

    private HuffmanDictionary dictionary;
    private static final short CRC_CODE = (short) 252;
    private String password;

    public Decompiler(String inputFileName, String password) throws Exception {
        this.inputFileName = inputFileName;
        BitFileReader.getInstance(inputFileName);
        this.password = password;
        outputFileName = "";
        treeLayerArray = new ArrayList<>();
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void getBasicInfo() throws Exception {
        BitFileReader.resetReader();
        int iterator = 0;
        try{
            StringBuilder text = new StringBuilder();
            for(int i = 0; i < 3; i++){
                text.append((char) BitFileReader.readNBits(8));
            }
            authors = text.toString();
            cryptedStatus = BitFileReader.readNBits(1) != 0;
            compressionStatus = BitFileReader.readNBits(1) != 0;
            if (!compressionStatus) {
                return;
            }
            validationCode = (short) (BitFileReader.readNBits(8));
            compressionLevel = (byte) BitFileReader.readNBits(5);
            treeMaxDepth = (short) BitFileReader.readNBits(compressionLevel);
            treeMinDepth = (short) BitFileReader.readNBits(compressionLevel - 1);
            treeLayerSize = (short) BitFileReader.readNBits(compressionLevel);
            originUsslessBits = (byte) BitFileReader.readNBits(3);

            treeLayerArray = getSimpleDictionaryFromFile();
            fileStatus = xorValidte();


//
        }catch (Exception e){
            throw new Exception("File error: unexpected end\n"); //
        }
        try{
            makeHuffmanDictionary();
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("\n" + e.getMessage());
            System.exit(1);
        }
    }

    public void makeHuffmanDictionary() {
        HuffmanTree huffmanTree = new  HuffmanTree(treeLayerArray);
        huffmanTree.printTree();
        huffmanTree.printArrayTree();
        dictionary = new HuffmanDictionary(huffmanTree);
    }

    public ArrayList<ArrayList<Short>> getHuffmanTreeAsArray(){
        HuffmanTree huffmanTree = new  HuffmanTree(treeLayerArray);
        System.err.println(twoDemensionArrayToString(huffmanTree.makeFullArrayTree()));
        return huffmanTree.makeFullArrayTree();
    }

    public void decompressFile(String outFilePath) throws Exception {
        StringBuilder decompressedFile = new StringBuilder();
        int array [] = {8, 8, 8, 1, 1, 8, 5, compressionLevel, compressionLevel - 1, compressionLevel, 3};
        BitFileReader.resetReader();
        for (int i = 0; i < array.length; i++) {
            short readedBits = (short) BitFileReader.readNBits(array[i]);
        }

        short x;
        int layerCounter = treeMaxDepth - treeMinDepth + 1;
        while (layerCounter-- > 0){
            short layerBreakPoint = (short) BitFileReader.readNBits(treeLayerSize);
            for(int j = 0; j < layerBreakPoint; j++){
                x = (short)  BitFileReader.readNBits(compressionLevel);
            }
        }

        BitFileReader.readUnreadBits();

        BitFileWriter.getInstance(outFilePath);

        try{
            while(BitFileReader.byteToEnd() > 0 || BitFileReader.getUnreadBits() > originUsslessBits ){
                short code = dictionary.findInDictionary(password);
                if(code != -1){
                    System.out.println(code);
                    BitFileWriter.writeExactBits(compressionLevel, code);
//                    decompressedFile.append((char)code);
                }else{
                    if(BitFileReader.byteToEnd() <= originUsslessBits){
                        Log.println("File decompressed");
                    }else{
                        Log.error("when decompress file");
                    }
                }
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
        System.out.println(decompressedFile);
    }

    public HashMap<String, Object> getBasicInfoAsMap(){
        HashMap<String, Object> basicInfo = new HashMap<>();
        basicInfo.put("compressed", compressionLevel);
        basicInfo.put("encrypted", cryptedStatus);
        basicInfo.put("size", BitFileReader.getFileSize());
        basicInfo.put("estimated_time_in_secound", 0);
        basicInfo.put("file_path", inputFileName);

        return basicInfo;
    }


    private ArrayList<ArrayList<Short>> getSimpleDictionaryFromFile() throws Exception {
        ArrayList<ArrayList<Short>> array = new ArrayList<>();
        int layerCounter = treeMaxDepth - treeMinDepth + 1;
        int i = 0;
        short x;
        while (layerCounter-- > 0){
            short layerBreakPoint = (short) BitFileReader.readNBits(treeLayerSize);
            array.add(new ArrayList<>());
            for(int j = 0; j < layerBreakPoint; j++){
                x = (short)  BitFileReader.readNBits(compressionLevel);
                array.get(i).add(x);
            }
            Collections.reverse(array.get(i));
            i++;
        }
        BitFileReader.readUnreadBits();
        return array;
    }

    @Override
    public String toString() {
        return "Decompiler{" +
                "\n\tfile status='" + fileStatus + "'" +
                ",\n\tinputFileName='" + inputFileName + "'" +
                ",\n\toutputFileName='" + outputFileName + "'" +
                ",\n\tauthors=" + authors +
                ",\n\tcryptedStatus=" + cryptedStatus +
                ",\n\tcompressionStatus=" + compressionStatus +
                ",\n\tcompressionLevel=" + compressionLevel +
                ",\n\tvalidationCode=" + validationCode +
                ",\n\ttreeMaxDepth=" + treeMaxDepth +
                ",\n\ttreeMinDepth=" + treeMinDepth +
                ",\n\ttreeLayerSize=" + treeLayerSize +
                ",\n\toriginUsslessBits=" + originUsslessBits +
                ",\n\ttreeLayerArray=" + twoDemensionArrayToString(treeLayerArray) +
                ",\n\tpassword='" + password + "'"  +
                "\n}";
    }

    String twoDemensionArrayToString(ArrayList<ArrayList<Short>> treeLayerArray) {
        StringBuilder result = new StringBuilder("\t[\n");
        treeLayerArray.forEach(array -> {
            result.append("\t\t[ ");
            for (Short element : array) {
                result.append(element).append(" ");
            }
            result.append("]\n");
        });
        result.append("\t]");
        return result.toString();
    }

    public boolean xorValidte() throws Exception {
        short crc = validationCode;
        while (BitFileReader.byteToEnd() > 0) {
            crc ^= (byte) BitFileReader.readNBits(8);
        }
        return crc == CRC_CODE;
    }
}
