package Graph;

import java.util.Arrays;
import java.util.Vector;

public class BFSWithoutQueue {

    static void BfsUtil(int dist[], boolean visited[], int curr, CycleGraph cg, Vector<Integer> v){

        while (curr<=cg.V){

            int node=v.get(curr-1);

            System.out.println(node+" : ");
            for (int i=0;i<cg.edges[node].size();i++){

                if (!visited[cg.edges[node].get(i)]){
                    visited[cg.edges[node].get(i)]=true;
                    v.add(cg.edges[node].get(i));
                    if(dist[node]+1>dist[cg.edges[node].get(i)]){
                        dist[cg.edges[node].get(i)]=dist[node]+1;
                    }

                }

            }
            curr+=1;
        }

    }

    static void Bfs(CycleGraph cg,int src){

        int dist[]=new int[cg.V];

        Arrays.fill(dist,-1);

        boolean visited[]= new boolean[cg.V];

        Vector<Integer> v= new Vector<>();
        Arrays.fill(visited,false);
        dist[src]=0;
        v.add(src);

        BfsUtil(dist,visited,1,cg,v);
    }

    static void addEdge(CycleGraph cg,int src,int dest){
        cg.edges[src].add(dest);
    }
    public static void main(String[] args) {
        CycleGraph cg= new CycleGraph(4);
        addEdge(cg,0, 1);
        addEdge(cg,0, 2);
        addEdge(cg,1, 2);
        addEdge(cg,2, 0);
        addEdge(cg,2, 3);
        addEdge(cg,3, 3);

        Bfs(cg,2);
    }
}
