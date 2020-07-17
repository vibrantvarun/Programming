package Graph;

import java.util.Arrays;

public class BipartiteCheck {


    boolean checkBipartite(int graph[][],int s){

        int colorArray[]= new int[graph.length];
        Arrays.fill(colorArray,-1);

        colorArray[s]=0;
        for (int i=0;i<graph.length;i++){
            for (int j=0;j<graph.length;j++){

                if (i==j && graph[i][j]>0){
                   return false;
                }

                if (graph[i][j]>0 && colorArray[j]==-1){
                    colorArray[j]=1-colorArray[i];
                }

                if (graph[i][j]>0 && colorArray[i]==colorArray[j]){
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {

        int graph[][]= {{0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}};


        BipartiteCheck bp= new BipartiteCheck();
        if (bp.checkBipartite(graph,0)){
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }



    }
}
