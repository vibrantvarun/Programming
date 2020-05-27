package Graph;

import javax.swing.*;
import java.util.*;

public class DijkstraAlgo {

    static final int V = 9;


    int getMinimumDistanceVertex(int dist[],boolean sptSet[]){
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int i=0;i<V;i++){
            if (dist[i]<min && !sptSet[i]){
                min_index=i;
                min=dist[i];
            }

        }
       return min_index;
    }

    void printSolution(int dist[]) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }


    void getShortestPath(int graph[][],int src){

        int dist[]=new int[V];
        boolean sptSet[]=new boolean[V];
        for (int i=0;i<V;i++){
            dist[i]=Integer.MAX_VALUE;
            sptSet[i]=false;
        }

        dist[src]=0;

        for (int i=0;i<V-1;i++){

            int u = getMinimumDistanceVertex(dist,sptSet);

            sptSet[u]=true;

            for (int j=0;j<V;j++){

                if(graph[u][j]!=0 && !sptSet[j] && dist[u]!=Integer.MAX_VALUE && dist[j]> dist[u]+graph[u][j]){
                   dist[j]=dist[u]+graph[u][j];
                }
            }
        }

        printSolution(dist);
    }

    public void addEdge(GraphData graphData,int src, int dest, int weight){

        GraphNode graphNode= new GraphNode(dest,weight);

        graphData.nodes[src].add(graphNode);

    }

    public void dijkStra2(GraphData graphData,int V,int src){

        Set<Integer> settled= new HashSet<>();

        int dist[]= new int[V];

        PriorityQueue<GraphNode> pq= new PriorityQueue<GraphNode>(V,new GraphNode());

        for (int i=0;i<V;i++){
            dist[i]=Integer.MAX_VALUE;
        }

        dist[src]=0;

        pq.add(new GraphNode(src,0));

        while (settled.size()!=V){

           int dest= pq.remove().dest;

           settled.add(dest);


           processNearestEdges(graphData,dest,dist,settled,pq);
        }

        for (int i = 0; i < dist.length; i++)
            System.out.println(src + " to " + i + " is "
                    + dist[i]);

    }

    public void processNearestEdges(GraphData gd,int nodeDest,int dist[],Set<Integer> settled,PriorityQueue<GraphNode> pq){

        int newDistance;

        for (int i=0;i<gd.nodes[nodeDest].size();i++){
            GraphNode node1=gd.nodes[nodeDest].get(i);


            if (!settled.contains(node1.dest)){
                newDistance=node1.weight+dist[nodeDest];
                if (newDistance<dist[node1.dest]){

                    dist[node1.dest]=newDistance;
                }

                pq.add(new GraphNode(node1.dest,dist[node1.dest]));
            }

        }
    }


    public static void main(String[] args) {

        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};


        DijkstraAlgo dka= new DijkstraAlgo();
        dka.getShortestPath(graph,0);


        GraphData graphData= new GraphData(V);

        dka.addEdge(graphData, 0, 1, 4);
        dka.addEdge(graphData, 0, 7, 8);
        dka.addEdge(graphData, 1, 2, 8);
        dka.addEdge(graphData, 1, 7, 11);
        dka.addEdge(graphData, 2, 3, 7);
        dka.addEdge(graphData, 2, 8, 2);
        dka.addEdge(graphData, 2, 5, 4);
        dka.addEdge(graphData, 3, 4, 9);
        dka.addEdge(graphData, 3, 5, 14);
        dka.addEdge(graphData, 4, 5, 10);
        dka.addEdge(graphData, 5, 6, 2);
        dka.addEdge(graphData, 6, 7, 1);
        dka.addEdge(graphData, 6, 8, 6);
        dka.addEdge(graphData, 7, 8, 7);

        dka.dijkStra2(graphData,V,0);

    }
}

class GraphNode implements Comparator<GraphNode> {

    int dest;
    int weight;

    GraphNode(){

    }

    GraphNode(int dest,int weight){
        this.dest=dest;
        this.weight=weight;
    }

    @Override
    public int compare(GraphNode u,GraphNode v) {

        if (u.weight<v.weight){
            return -1;
        }

        if (u.weight>v.weight){
            return 1;
        }

        return 0;
    }
}

class GraphData{

    int V;
    LinkedList<GraphNode> nodes[];

    GraphData(int V){
        this.V=V;
        nodes=new LinkedList[V];
        for (int i=0;i<V;i++){
            nodes[i]= new LinkedList<>();
        }
    }
}