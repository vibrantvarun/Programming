package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumFlowFordFulkerson {

    static  int N=6;
    boolean bfs(int r[][],int s, int t,int parent[]){

        boolean[] visited= new boolean[N];
        Arrays.fill( visited,false);


        Queue<Integer> queue= new LinkedList<>();

        queue.add(s);
        parent[s]=-1;
        visited[s]=true;

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


    int getMaxFlow(int graph[][],int s,int t){

        int r[][]= new int[N][N];

        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                r[i][j]=graph[i][j];
            }
        }

        int [] parent= new int[N];

        int maxflow=0;
        while (bfs(r,s,t,parent)){

            int pathflow=Integer.MAX_VALUE;
            for (int v=t;v!=s;v=parent[v]){
                int u=parent[v];
                pathflow=Math.min(pathflow,r[u][v]);
            }

            for (int v=t;v!=s;v=parent[v]){
                int u=parent[v];
                r[u][v]= r[u][v]-pathflow;
                r[v][u]= r[v][u]+pathflow;
            }

            maxflow+=pathflow;
        }

        return maxflow;
    }


    public static void main(String[] args) {
        //Time Complexity V(E^2)
        int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        MaximumFlowFordFulkerson max= new MaximumFlowFordFulkerson();

        System.out.println(max.getMaxFlow(graph,0,5));
    }
}
