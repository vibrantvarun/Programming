package Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class LongestPathDAG {

    int V;
    LinkedList<Edge>[]edges;
    LongestPathDAG(int V){
        this.V=V;
        edges= new LinkedList[this.V];
        for (int i=0;i<edges.length;i++){
            edges[i]= new LinkedList<>();
        }

    }

    public void addEdge(int src,int dest, int weight){
        Edge edge= new Edge(dest,weight);
        this.edges[src].add(edge);
    }

    public class Edge{
        int v;
        int weight;
        Edge(int v, int weight){
            this.v=v;
            this.weight=weight;
        }
    }

    public void dfsTopSortUtil(Stack<Integer> stack, boolean[] visited, int src){

        visited[src]=true;


        for (int i=0;i<this.edges[src].size();i++){


            if (!visited[this.edges[src].get(i).v]){
                dfsTopSortUtil(stack,visited,this.edges[src].get(i).v);
            }
        }

        stack.add(src);
    }


    public void dfsTopSort(int src){

        boolean visited[]= new  boolean[this.V];

        Stack<Integer> stack = new Stack<>();


        for (int i=0;i<this.V;i++){
            if (!visited[i]){
                dfsTopSortUtil(stack,visited,i);
            }
        }

        calculateLongestDistanceOfAllNodes(stack,src);

    }

    public  void  calculateLongestDistanceOfAllNodes(Stack<Integer> stack,int src){

        int distance[]= new int[this.V];

        for (int i=0;i<this.V;i++){
            distance[i]=Integer.MIN_VALUE;
        }

        distance[src]=0;

        while (!stack.isEmpty()){

            int element=stack.pop();

            Iterator<Edge> itr;
            if (distance[element]!=Integer.MIN_VALUE){
                itr=this.edges[element].listIterator();
                while (itr.hasNext()){
                    Edge edge=itr.next();
                    if (distance[edge.v]<distance[element]+edge.weight){
                        distance[edge.v]=distance[element]+edge.weight;
                    }
                }
            }
        }

        for (int i = 0; i < V; i++)
        {
            if (distance[i] == Integer.MIN_VALUE)
                System.out.print( "INF ");
            else
                System.out.print( distance[i] + " ");
        }
    }

    public static void main(String[] args) {
        LongestPathDAG lad= new LongestPathDAG(6);
        lad.addEdge(0, 1, 5);
        lad.addEdge(0, 2, 3);
        lad.addEdge(1, 3, 6);
        lad.addEdge(1, 2, 2);
        lad.addEdge(2, 4, 4);
        lad.addEdge(2, 5, 2);
        lad.addEdge(2, 3, 7);
        lad.addEdge(3, 5, 1);
        lad.addEdge(3, 4, -1);
        lad.addEdge(4, 5, -2);

        lad.dfsTopSort(1);
    }

}
