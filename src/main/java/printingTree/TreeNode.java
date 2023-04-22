package printingTree;

public class TreeNode {
    private int maxDepth;
    public short[] tab = {30234, 12453, 23554,21111,22222,10012,12212,30234,12342,12452,32142,12111,23332,12441,12412};   // 5  5  5      15  5  15
    public TreeNode(int max){
        this.maxDepth = max;
    }
    public void printTree(){
        if(maxDepth == 0){
            System.out.println(tab[0]);
        }else{
            int width = 5, widthOrigin = 5,shortWidth = 5;
            int leftWidth = 5, rightWidth=5,middleWidth = 5;
            int iterate = 1;
            int iterateByArray = 0;
            int amountOfArray = tab.length;
            int currentLayer = 0;
            int upperIterator = 5;
            int odds = 0;
            int it = maxDepth-1,it2 = maxDepth-1;

            int []tabOfMiddleValuesWidths = new int[maxDepth];
            int jtr=0,jtw=0;
            for(int i = 1;i <= maxDepth;i++){

                tabOfMiddleValuesWidths[jtr] = width;
                jtr++;

                width *= 2;
                width += 5;
                if(i == (maxDepth - 1)){
                    leftWidth = width;
                    rightWidth = width;
                    middleWidth = width;
                }
            }
            while(amountOfArray != 0) {
                int iterateByShortWidth = 5;
                boolean k = false;
                int count = 0;
                for(int j = 0; j < iterate;j++) {
                    if(k == false) {
                        for (int i = 0; i < leftWidth -odds; i++) {
                            System.out.print(" ");
                        }
                        k = true;
                    }
                    System.out.print(tab[iterateByArray]);
                    for (int i = 0; i < /*middleWidth*/tabOfMiddleValuesWidths[it+count]; i++) {
                        System.out.print(" ");
                    }
                    iterateByArray++;
                    amountOfArray--;
                    //if((it+count) < maxDepth-1)
                    //count++;
                }

                leftWidth--;
                rightWidth++;
                if(amountOfArray == 0)
                    break;
                //TODO:Trzeba uwzglednic to ze laczniki "/" "\" moga byc nie tylko od jednego noda ale dalej sie rozciagac np / \  / \
                int tempMiddleWidth = middleWidth;

                System.out.println();
                middleWidth-=2;
                if(jtw>0) {
                    leftWidth -= 3;
                    upperIterator -= 2;
                    it--;
                }
                if(currentLayer > 0)
                    upperIterator = tabOfMiddleValuesWidths[it2]-2;

                for(int h = tempMiddleWidth;h >= 5;h-=2) {
                    boolean l = false;
                    for (int j = 0; j < java.lang.Math.pow(2, currentLayer); j++) {

                        if (l == false) {
                            for (int i = 0; i < leftWidth; i++) {
                                System.out.print(" ");
                            }
                            l = true;
                        }
                        System.out.print("/");
                        for (int i = 0; i < iterateByShortWidth; i++) {
                            System.out.print(" ");
                        }
                        System.out.print("\\");
                        for (int i = 0; i < /*bylo middleWidth*/ upperIterator; i++) {
                            System.out.print(" ");
                        }
                        iterateByShortWidth += 2;
                        rightWidth++;
                        if(j < java.lang.Math.pow(2, currentLayer)-1){
                            iterateByShortWidth -= 2;
                        }
                    }
                    upperIterator -= 2;
                    leftWidth--;
                    System.out.println();
                }
                middleWidth+=2;
                middleWidth-=5;
                middleWidth/=2;
                iterate*=2;
                if(currentLayer > 0)
                    it2--;
                currentLayer++;
                //leftWidth-=5;
                if(leftWidth < 0)
                    leftWidth = 0;
                //rightWidth+=5;
                odds = 3;
                upperIterator = iterateByShortWidth;
                jtw++;

            }



        }
    }
}
