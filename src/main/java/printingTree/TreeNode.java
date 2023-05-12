package printingTree;

import java.util.ArrayList;

public class TreeNode {
    private int maxDepth;

    //private String saveAsTree="";
    private StringBuilder sT;
    private ArrayList<Short> tempValuesOfTree;
    private ArrayList<ArrayList<Short>> forik;// = new ArrayList<>();

    public TreeNode(int max, ArrayList<ArrayList<Short>> forik) {
        tempValuesOfTree = new ArrayList<>();
        this.maxDepth = max;
        this.forik = forik;
        sT = new StringBuilder();
    }
    public String getTree(){
        return sT.toString();
    }

    public void printTree() {
        if (maxDepth == 0) {
            System.out.println(forik.get(0).get(0));
        } else {
            int width = 5;
            int leftWidth = 5, middleWidth = 5;
            int iterate = 1;
            int iterateByArray = 0;
            int simpleArrayListFounder =0;
            int amountOfArray = 0;//tab.length;
            int lengthOfArrayOfArrays = forik.size();
            for(int i = maxDepth; i>=0;i--){
                amountOfArray += java.lang.Math.pow(2, i);
            }

            int currentLayer = 0;
            int upperIterator = 5;
            int odds = 0;
            int it = maxDepth - 1, it2 = maxDepth - 1;


            int[] tabOfMiddleValuesWidths = new int[maxDepth];
            int jtr = 0, jtw = 0;
            for (int i = 1; i <= maxDepth; i++) {

                tabOfMiddleValuesWidths[jtr] = width;
                jtr++;

                width *= 2;
                width += 5;
                if (i == (maxDepth - 1)) {
                    leftWidth = width;
                    middleWidth = width;
                }
            }
            while (amountOfArray != 0) {
                int iterateByShortWidth = 5;
                boolean k = false;
                int count = 0;

                if(simpleArrayListFounder < lengthOfArrayOfArrays)
                    tempValuesOfTree.addAll(forik.get(simpleArrayListFounder));

                int smth = tempValuesOfTree.size();
                for (int j = 0; j < iterate; j++) {
                    if (k == false) {
                        for (int i = 0; i < leftWidth - odds; i++) {
                            //System.out.print(" ");
                            //saveAsTree+=" ";
                            sT.append(" ");
                        }
                        k = true;
                    }
                    //tempValuesOfTree.add(tab[iterateByArray]);
                    if(j < smth-1 ) {
                        if (tempValuesOfTree.get(j) >= 0) {
                            if ((tempValuesOfTree.get(j) < 10) && (tempValuesOfTree.get(j) >= 0)) {
                                //System.out.print("  " + tempValuesOfTree.get(j) + "  ");
                                //saveAsTree+="  " + tempValuesOfTree.get(j) + "  ";
                                sT.append("  " + tempValuesOfTree.get(j) + "  ");
                            } else if ((tempValuesOfTree.get(j) < 100) && (tempValuesOfTree.get(j) >= 10)) {
                                //System.out.print(" " + tempValuesOfTree.get(j) + "  ");
                                //saveAsTree+=" " + tempValuesOfTree.get(j) + "  ";
                                sT.append(" " + tempValuesOfTree.get(j) + "  ");
                            } else if ((tempValuesOfTree.get(j) < 1000) && (tempValuesOfTree.get(j) >= 100)) {
                                //System.out.print(" " + tempValuesOfTree.get(j) + " ");
                                //saveAsTree+=" " + tempValuesOfTree.get(j) + " ";
                                sT.append(" " + tempValuesOfTree.get(j) + " ");
                            } else if ((tempValuesOfTree.get(j) < 10000) && (tempValuesOfTree.get(j) >= 1000)) {
                                //System.out.print(" " + tempValuesOfTree.get(j));
                                //saveAsTree+=" " + tempValuesOfTree.get(j);
                                sT.append(" " + tempValuesOfTree.get(j));
                            } else if ((tempValuesOfTree.get(j) <= 65535) && (tempValuesOfTree.get(j) >= 10000)) {
                                //System.out.print(tempValuesOfTree.get(j));
                                //saveAsTree+=tempValuesOfTree.get(j);
                                sT.append(tempValuesOfTree.get(j));
                            }
                        } else {
                            if (tempValuesOfTree.get(j) == -1) {
                                //System.out.print("[   ]");
                                //saveAsTree+="[   ]";
                                sT.append("[   ]");
                            }
                        }
                    }else{
                        //System.out.print("     ");
                        //saveAsTree+="     ";
                        sT.append("     ");
                    }
                    if(j < iterate-1) {
                        for (int i = 0; i < /*middleWidth*/tabOfMiddleValuesWidths[it + count]; i++) {
                            //System.out.print(" ");
                            //saveAsTree += " ";
                            sT.append(" ");
                        }
                    }
                    iterateByArray++;
                    amountOfArray--;
                }

                leftWidth--;
                if (amountOfArray == 0)
                    break;
                //TODO:Trzeba uwzglednic to ze laczniki "/" "\" moga byc nie tylko od jednego noda ale dalej sie rozciagac np / \  / \
                int tempMiddleWidth = middleWidth;

                //System.out.println();
                //saveAsTree+="\n";
                sT.append("\n");
                middleWidth -= 2;
                if (jtw > 0) {
                    leftWidth -= 3;
                    upperIterator -= 2;
                    it--;
                }
                if (currentLayer > 0)
                    upperIterator = tabOfMiddleValuesWidths[it2] - 2;

                for (int h = tempMiddleWidth; h >= 5; h -= 2) {
                    boolean l = false;
                    for (int j = 0; j < java.lang.Math.pow(2, currentLayer); j++) {

                        if (l == false) {
                            for (int i = 0; i < leftWidth; i++) {
                                //System.out.print(" ");
                                //saveAsTree+=" ";
                                sT.append(" ");
                            }
                            l = true;
                        }
                        if(j < smth-1 ) {
                            if (tempValuesOfTree.get(j) >= 0) {
                                //System.out.print(" ");
                                //saveAsTree+=" ";
                                sT.append(" ");
                            } else {
                                //System.out.print("/");
                                //saveAsTree+="/";
                                sT.append("/");
                            }
                        }else{
                            //System.out.print(" ");
                            //saveAsTree+=" ";
                            sT.append(" ");
                        }

                        for (int i = 0; i < iterateByShortWidth; i++) {
                            //System.out.print(" ");
                            //saveAsTree+=" ";
                            sT.append(" ");
                        }

                        if(j < smth-1 ) {
                            if (tempValuesOfTree.get(j) >= 0) {
                                //System.out.print(" ");
                                //saveAsTree+=" ";
                                sT.append(" ");
                            } else {
                                //System.out.print("\\");
                                //saveAsTree+="\\";
                                sT.append("\\");
                            }
                        }else{
                            //System.out.print(" ");
                            //saveAsTree+=" ";
                            sT.append(" ");
                        }
                        if(j < iterate-1) {
                            for (int i = 0; i < /*bylo middleWidth*/ upperIterator; i++) {
                                //System.out.print(" ");
                                //saveAsTree+=" ";
                                sT.append(" ");
                            }
                        }
                        iterateByShortWidth += 2;
                        if (j < java.lang.Math.pow(2, currentLayer) - 1) {
                            iterateByShortWidth -= 2;
                        }
                    }
                    upperIterator -= 2;
                    leftWidth--;
                    //System.out.println();
                    //saveAsTree+="\n";
                    sT.append("\n");
                }
                tempValuesOfTree.removeAll(tempValuesOfTree);
                middleWidth += 2;
                middleWidth -= 5;
                middleWidth /= 2;
                iterate *= 2;
                if (currentLayer > 0)
                    it2--;
                currentLayer++;
                //leftWidth-=5;
                if (leftWidth < 0)
                    leftWidth = 0;
                //rightWidth+=5;
                odds = 3;
                upperIterator = iterateByShortWidth;
                jtw++;
                simpleArrayListFounder++;

            }


        }
    }
}