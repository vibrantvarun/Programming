package Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class ShortestPathAlgoForDAG {
    int V;
    LinkedList<Edge> []edges;
    ShortestPathAlgoForDAG(int V){
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


    public void dfsTopSortUtil(Stack<Integer> stack, boolean[] visited,int src){

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

        calculateShortestDistanceOfAllNodes(stack,src);

    }

    public  void  calculateShortestDistanceOfAllNodes(Stack<Integer> stack,int src){

        int distance[]= new int[this.V];

        for (int i=0;i<this.V;i++){
            distance[i]=Integer.MAX_VALUE;
        }

        distance[src]=0;

        while (!stack.isEmpty()){

            int element=stack.pop();

            Iterator<Edge> itr;
            if (distance[element]!=Integer.MAX_VALUE){
                itr=this.edges[element].listIterator();
                while (itr.hasNext()){
                    Edge edge=itr.next();
                    if (distance[edge.v]>distance[element]+edge.weight){
                        distance[edge.v]=distance[element]+edge.weight;
                    }
                }
            }
        }

        for (int i = 0; i < V; i++)
        {
            if (distance[i] == Integer.MAX_VALUE)
                System.out.print( "INF ");
            else
                System.out.print( distance[i] + " ");
        }
    }
    public class Edge{
        int v;
        int weight;
        Edge(int v, int weight){
            this.v=v;
            this.weight=weight;
        }
    }



    public static void main(String[] args) {

        ShortestPathAlgoForDAG sad= new ShortestPathAlgoForDAG(6);
        sad.addEdge(0, 1, 5);
        sad.addEdge(0, 2, 3);
        sad.addEdge(1, 3, 6);
        sad.addEdge(1, 2, 2);
        sad.addEdge(2, 4, 4);
        sad.addEdge(2, 5, 2);
        sad.addEdge(2, 3, 7);
        sad.addEdge(3, 4, -1);
        sad.addEdge(4, 5, -2);

        sad.dfsTopSort(1);
    }
}
