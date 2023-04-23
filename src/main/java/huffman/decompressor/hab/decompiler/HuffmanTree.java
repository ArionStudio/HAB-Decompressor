package huffman.decompressor.hab.decompiler;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public HuffmanTreeEntry root;
    private ArrayList<ArrayList<HuffmanTreeEntry>> huffmanArray;

    public HuffmanTree(ArrayList<ArrayList<Short>> simpleTree) {

        huffmanArray = new ArrayList<>();
        for (int i = 0; i < simpleTree.size(); i++) {

            huffmanArray.add(new ArrayList<>());
            for (int j = 0, k = 0; i > 0 && j < huffmanArray.get(i - 1).size(); j += 2, k++) {

                if(j + 1 < huffmanArray.get(i - 1).size()){
                    huffmanArray.get(i).add(
                            new HuffmanTreeEntry(
                                    huffmanArray.get(i - 1).get(j),
                                    huffmanArray.get(i - 1).get(j + 1)
                            )
                    );
                }else{
                    huffmanArray.get(i).add(
                            new HuffmanTreeEntry(
                                    huffmanArray.get(i - 1).get(j),
                                    null
                            )
                    );
                }

                huffmanArray.get(i - 1).get(j).parent = huffmanArray.get(i).get(k);
                if(j + 1 < huffmanArray.get(i - 1).size()){
                    huffmanArray.get(i - 1).get(j + 1).parent = huffmanArray.get(i).get(k);
                }
            }


            for (int j = 0; j < simpleTree.get(i).size(); j++) {
                huffmanArray.get(i).add(new HuffmanTreeEntry(simpleTree.get(i).get(j), null, null));
            }
        }
        int lastI = huffmanArray.size() - 1;
        while (huffmanArray.get(lastI).size() > 1){
            huffmanArray.add(new ArrayList<>());
            for (int i = 0, k = 0; i < huffmanArray.get(lastI).size(); i += 2, k++) {
                huffmanArray.get(lastI + 1).add(
                        new HuffmanTreeEntry(
                                huffmanArray.get(lastI).get(i),
                                huffmanArray.get(lastI).get(i + 1)
                        )
                );
                huffmanArray.get(lastI).get(i).parent = huffmanArray.get(lastI + 1).get(k);
                huffmanArray.get(lastI).get(i + 1).parent = huffmanArray.get(lastI + 1).get(k);
            }
            lastI++;
        }

        root = huffmanArray.get(lastI).get(0);

    }


    public void printTree(){
        for (int i = 0; i < huffmanArray.size(); i++) {
            for (int j = 0; j < huffmanArray.get(i).size(); j++) {
                System.out.print("[" + j + "]: " + huffmanArray.get(i).get(j).code + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

