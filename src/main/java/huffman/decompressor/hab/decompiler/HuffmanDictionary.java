package huffman.decompressor.hab.decompiler;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.RandomAccess;

public class HuffmanDictionary {
    private ArrayList<HuffmanDictionaryEntry> huffmanDictionary;

    public HuffmanDictionary(HuffmanTree huffmanTree) {

        huffmanDictionary = new ArrayList<>();
        makeHuffmanDictionary(huffmanTree.root, new ArrayList<>());
        printHuffmanDictionary();
        
    }

  

    private void makeHuffmanDictionary(HuffmanTreeEntry huffmanTree, ArrayList<Boolean> huffmanTranslation){
        if(huffmanTree == null){
            return;
        }
        if(huffmanTree.code != -1){
            huffmanDictionary.add(new HuffmanDictionaryEntry(huffmanTree.code, huffmanTranslation));
        }else{
            ArrayList<Boolean> toRight = new ArrayList<>(huffmanTranslation);
            toRight.add(false);
            makeHuffmanDictionary(huffmanTree.rightChild, toRight);

            ArrayList<Boolean> toLeft = new ArrayList<>(huffmanTranslation);
            toLeft.add(true);
            makeHuffmanDictionary(huffmanTree.leftChild, toLeft);



        }
    }

    private void printHuffmanDictionary() {
        for (HuffmanDictionaryEntry huffmanDictionaryEntry : huffmanDictionary) {
            Log.println(huffmanDictionaryEntry.toString());
        }
    }

    public short findInDictionary() throws Exception {
        ArrayList<Boolean> translation = new ArrayList<>();
        int translationReaded = 0;
        for (int i = 0; i < huffmanDictionary.size(); i++) {
            int counter = 0;
            ArrayList <Boolean> hT = huffmanDictionary.get(i).huffmanTranslation;
            for (int j = 0; j < hT.size(); j++) {
                if(translationReaded <= counter){
                    translation.add(BitFileReader.readNBits(1) > 0);
                    translationReaded++;
                }
                if(translation.get(counter) != hT.get(j)){
                    break;
                }
                counter++;
            }
            if(hT.equals(translation)){
                return  huffmanDictionary.get(i).code;
            }
        }
        return -1;
    }
}
