package huffman.decompressor.hab.decompiler;

import java.util.ArrayList;

public class HuffmanDictionaryEntry {
    short code;
    ArrayList<Boolean> huffmanTranslation;

    public HuffmanDictionaryEntry(short code, ArrayList<Boolean> huffmanTranslation) {
        this.code = code;
        this.huffmanTranslation = huffmanTranslation;
    }

    @Override
    public String toString() {
        return "Code: " + (char)code + " huffmanTranslation: " + getHuffmanTranslation();
    }

    public String getHuffmanTranslation() {
        String biteCode = "";
        for (boolean bite:
             huffmanTranslation) {
            biteCode += (bite ? "1" : "0");
        }
        return biteCode;
    }
}
