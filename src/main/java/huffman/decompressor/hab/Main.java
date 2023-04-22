package huffman.decompressor.hab;

import huffman.decompressor.hab.gui.MainController;
import printingTree.TreeNode;

public class Main {
    public static void main(String[] args) {
        MainController.startGui();
        TreeNode tree = new TreeNode(3);
        tree.printTree();
    }
}
