package Graph;

import java.util.*;

public class TopologicalSorting {


    static void dfsUtil(TopologicalGraph tg, boolean visited[], int src, Stack<Integer> stack){
        visited[src]=true;


        for (int i=0;i<tg.edges[src].size();i++){

            if (!visited[tg.edges[src].get(i)]){
                dfsUtil(tg,visited,tg.edges[src].get(i),stack);
            }

        }

        stack.add(src);

    }

    static void dfs(TopologicalGraph tg){

        boolean v[]= new boolean[tg.V];
        for (int i=0;i< tg.V;i++){
            v[i]=false;
        }

        Stack<Integer> stack= new Stack<>();
        for (int i=0;i<tg.V;i++){
            if(!v[i]){
                dfsUtil(tg,v,i,stack);
            }
        }


        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    static void addEdge(TopologicalGraph tg, int src, int dest){
        tg.edges[src].add(dest);
    }


    static void topologicalSortUsingKahnMethod(TopologicalGraph tg){


        Queue<Integer> queue= new LinkedList<>();

        int indegrees[]= new int[tg.V];


        for(int i=0;i<tg.V;i++){
            LinkedList<Integer> temp= tg.edges[i];
            for (int node: temp){
                indegrees[node]++;
            }
        }

        for (int i=0;i<indegrees.length;i++){
            if (indegrees[i]==0){
                queue.add(i);
            }
        }

        Vector<Integer> topSort= new Vector<>();
        int count=0;
        while (!queue.isEmpty()){

            int node=queue.poll();
            topSort.add(node);


            for (int v: tg.edges[node]){
                if (--indegrees[v]==0){
                    queue.add(v);
                }
            }
            count++;
        }

        if (count!=tg.V){
            System.out.println("Cycle is found");
            return;
        }

        System.out.println("Method 2");
        for (int i=0;i<topSort.size();i++){
            System.out.println(topSort.get(i));
        }

    }

    public static void main(String[] args) {
        TopologicalGraph tg= new TopologicalGraph(6);

        addEdge(tg,5, 2);
        addEdge(tg,5, 0);
        addEdge(tg,4, 0);
        addEdge(tg,4, 1);
        addEdge(tg,2, 3);
        addEdge(tg,3, 1);

        TopologicalGraph tg2= new TopologicalGraph(6);
        addEdge(tg2,5, 2);
        addEdge(tg2,5, 0);
        addEdge(tg2,4, 0);
        addEdge(tg2,4, 1);
        addEdge(tg2,2, 3);
        addEdge(tg2,3, 1);


        dfs(tg2);
        topologicalSortUsingKahnMethod(tg);
        topologicalSortUsingKahnMethod(tg2);
    }

}

class TopologicalGraph{
    int V;
    LinkedList<Integer> edges[];

    TopologicalGraph(int V) {
        this.V = V;
        edges = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            edges[i] = new LinkedList<>();
        }
    }
}