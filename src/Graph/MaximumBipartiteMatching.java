package Graph;

import java.util.Arrays;

public class MaximumBipartiteMatching {

    static int M=6;
    static int N=6;

    boolean bpm(boolean graph[][],boolean[] seen,int src,int matchR[]){

        for (int i=0;i<N;i++){

            if (graph[src][i] && !seen[i]){

                seen[i]=true;

                if (matchR[i]<0 || bpm(graph,seen,matchR[i],matchR)){
                    matchR[i]=src;
                    return true;
                }
            }

        }

        return false;
    }




    int  maximumBipartiteMatching(boolean graph[][]) {
        int matchR[]= new int[N];
        Arrays.fill(matchR,-1);

        int result=0;


        for (int i=0;i<M;i++){
             boolean seen[]=new boolean[N];
             Arrays.fill(seen,false);

             if (bpm(graph,seen,i,matchR)){
                 result++;
             }

        }


        return result;
    }

    public static void main(String[] args) {

        boolean graph[][] = new boolean[][]{
                {false, true, true,
                        false, false, false},
                {true, false, false,
                        true, false, false},
                {false, false, true,
                        false, false, false},
                {false, false, true,
                        true, false, false},
                {false, false, false,
                        false, false, false},
                {false, false, false,
                        false, false, true}};

        MaximumBipartiteMatching maxB= new MaximumBipartiteMatching();
        System.out.println(maxB.maximumBipartiteMatching(graph));

    }
}
