package huffman.decompressor.hab.decompiler;

import java.util.ArrayList;

public class HuffmanTreeEntry {
    short code;
    HuffmanTreeEntry parent, leftChild, rightChild;

    public HuffmanTreeEntry(short code, HuffmanTreeEntry leftChild, HuffmanTreeEntry rightChild) {
        this.code = code;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public HuffmanTreeEntry(HuffmanTreeEntry leftChild, HuffmanTreeEntry rightChild) {
        this.code = -1;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }


}
