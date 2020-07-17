package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinimumCutProblem {

    static int N=6;


    void dfs(int r[][],int s,int t,boolean[] visited){
        visited[s]=true;
        for (int i=0;i<r.length;i++){
            if (r[s][i]>0  && !visited[i]){
                dfs(r,i,t,visited);
            }
        }
    }


    boolean bfs(int s,int t, int []parent,int r[][]){

        parent[s]=-1;

        boolean[] visited= new boolean[N];

        visited[s]=true;

        Queue<Integer> queue= new LinkedList<>();

        queue.add(s);

        while (!queue.isEmpty()){

            int u= queue.peek();
            queue.remove();

            for (int i=0;i<N;i++){
                if (!visited[i] && r[u][i]>0){
                    queue.add(i);
                    parent[i]=u;
                    visited[i]=true;
                }
            }


        }

        return visited[t];
    }


    int getMinCut(int graph[][],int s,int t){

        int r[][]= new int[N][N];

        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                r[i][j]=graph[i][j];
            }
        }

        int parent[]= new int[N];


        while (bfs(s,t,parent,r)){
            int pathflow=Integer.MAX_VALUE;

            for (int v=t;v!=s;v=parent[v]){

                int u= parent[v];
                pathflow=Math.min(pathflow,r[u][v]);
            }

            for (int v=t;v!=s;v=parent[v]){
                int u= parent[v];
                r[u][v]-=pathflow;
                r[v][u]+=pathflow;
            }
        }


        boolean[] visited=new  boolean[graph.length];
        Arrays.fill(visited,false);

        dfs(r,s,t,visited);

       for (int i=0;i<graph.length;i++){
           for (int j=0;j<graph.length;j++){
               if (graph[i][j]>0 && !visited[j] && visited[i]){
                   System.out.println(i + " - " + j);
               }

           }
       }

        return 0;
    }



    public static void main(String[] args) {
        int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        MinimumCutProblem mincut= new MinimumCutProblem();
        mincut.getMinCut(graph,0,5);
    }

}
