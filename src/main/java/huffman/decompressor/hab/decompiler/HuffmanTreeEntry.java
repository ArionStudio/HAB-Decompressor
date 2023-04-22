package huffman.decompressor.hab.decompiler;

public class HuffmanTreeEntry {
    short code;
    short translation;
    HuffmanTreeEntry parent, leftChild, rightChild;

    public HuffmanTreeEntry(short code, HuffmanTreeEntry leftChild, HuffmanTreeEntry rightChild) {
        this.code = code;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
