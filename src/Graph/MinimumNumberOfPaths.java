package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumNumberOfPaths {
    int V;
    LinkedList<Integer> edges[];
    MinimumNumberOfPaths(int V){
       this.V=V;
       edges= new LinkedList[this.V];
       for (int i=0;i<edges.length;i++){
           edges[i]=new LinkedList<>();
       }
    }



    void  Bfs(int src,int dest){
        boolean visited[]= new boolean[this.V];

        Queue<Integer> queue= new LinkedList<>();

        int dist[]= new int[this.V];


        visited[src]=true;
        queue.add(src);

        while (!queue.isEmpty()){

            int u= queue.remove();


            for (int i=0;i<this.edges[u].size();i++){
                int v= this.edges[u].get(i);
                if (!visited[v]){

                    dist[v]=dist[u]+1;
                    visited[v]=true;
                    queue.add(v);
                }

            }

        }

        for (int i=0;i<dist.length;i++){
            System.out.println(dist[i]);
        }
    }

    public void addEdge(int src,int dest){
       this.edges[src].add(dest);
       this.edges[dest].add(src);
    }

    public static void main(String[] args) {
        MinimumNumberOfPaths mp= new MinimumNumberOfPaths(9);

        mp.addEdge(0, 1);
        mp.addEdge( 0, 7);
        mp.addEdge( 1, 7);
        mp.addEdge( 1, 2);
        mp.addEdge( 2, 3);
        mp.addEdge( 2, 5);
        mp.addEdge( 2, 8);
        mp.addEdge( 3, 4);
        mp.addEdge( 3, 5);
        mp.addEdge( 4, 5);
        mp.addEdge( 5, 6);
        mp.addEdge( 6, 7);
        mp.addEdge(7, 8);

        mp.Bfs(0,5);
    }

}
