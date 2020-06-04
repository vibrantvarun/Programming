package Graph;

import java.util.Arrays;
import java.util.Comparator;

public class KrushkalsAlgo {

    int find(subset subset[],int src){

        if (subset[src].parent!=src){
            return find(subset,subset[src].parent);
        }
        return subset[src].parent;
    }

    void Union(subset subset[],int x,int y){
        int xroot=find(subset,x);
        int yroot=find(subset,y);

        if (subset[xroot].rank<subset[yroot].rank){
            subset[xroot].parent=subset[yroot].parent;
        }else if (subset[yroot].rank<subset[xroot].rank){
            subset[yroot].parent=subset[xroot].parent;
        }else {
            subset[xroot].parent=subset[yroot].parent;
            subset[yroot].rank++;
        }

    }


    void krushAlgo(KrushkalsGraph kg){
        subset subset[]=new subset[kg.V];
        for (int i=0;i<kg.V;i++){
            subset sub= new subset();
            sub.parent=i;
            sub.rank=0;
            subset[i]=sub;
        }

        KrushkalsEdge ke[]= new KrushkalsEdge[kg.V];

        for (int i=0;i<kg.V;i++){
            ke[i]=new KrushkalsEdge();
        }

        Arrays.sort(kg.edges);

        int i=0;
        int e=0;
        while (e<kg.V-1){

            KrushkalsEdge kes= new KrushkalsEdge();
            kes=kg.edges[i++];

            int xroot= find(subset,kes.src);
            int yroot= find(subset,kes.dest);

            if (xroot!=yroot){
                ke[e++]=kes;
                Union(subset,xroot,yroot);
            }
        }

        for (i=0;i<ke.length;i++){

            System.out.println("Edge included from source "+ke[i].src+" to Destination  "+ke[i].dest+ " with weight "+ke[i].weight);
        }




    }
    public static void main(String[] args) {
        int V = 4;  // Number of vertices in graph
        int E = 5;  // Number of edges in graph
        KrushkalsGraph graph = new KrushkalsGraph(V, E);

        // add edge 0-1
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;
        graph.edges[0].weight = 10;

        // add edge 0-2
        graph.edges[1].src = 0;
        graph.edges[1].dest = 2;
        graph.edges[1].weight = 6;

        // add edge 0-3
        graph.edges[2].src = 0;
        graph.edges[2].dest = 3;
        graph.edges[2].weight = 5;

        // add edge 1-3
        graph.edges[3].src = 1;
        graph.edges[3].dest = 3;
        graph.edges[3].weight = 15;

        // add edge 2-3
        graph.edges[4].src = 2;
        graph.edges[4].dest = 3;
        graph.edges[4].weight = 4;


        KrushkalsAlgo ka= new KrushkalsAlgo();
        ka.krushAlgo(graph);
    }

}


class KrushkalsGraph{
    int V;
    int E;
    KrushkalsEdge edges[];

    KrushkalsGraph(int V,int E){
        this.V=V;
        this.E=E;
        edges=new KrushkalsEdge[E];
        for (int i=0;i<E;i++){
           edges[i]= new KrushkalsEdge();
        }
    }
}

class KrushkalsEdge implements Comparable<KrushkalsEdge> {
    int src;
    int dest;
    int weight;

    KrushkalsEdge(){

    }

    KrushkalsEdge(int src, int dest,int weight){
        this.src=src;
        this.dest=dest;
        this.weight=weight;
    }


    @Override
    public int compareTo(KrushkalsEdge e) {

        return this.weight-e.weight;
    }
}

//Time Complexity: ELogV or ELogE