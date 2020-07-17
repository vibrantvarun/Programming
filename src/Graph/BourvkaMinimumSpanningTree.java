package Graph;

import java.util.Arrays;

public class BourvkaMinimumSpanningTree {


    void createMST(Graph graph){

        int cheapest[]= new int[graph.V];

        subset subset[]= new subset[graph.V];

        for (int i=0;i<graph.V;i++){
            subset s= new subset();
            s.parent=i;
            s.rank=0;
            subset[i]=s;
        }

        int numTrees=graph.V;
        int mst=0;

        while (numTrees>1){

            Arrays.fill(cheapest,-1);

            for (int i=0;i<graph.E;i++){

                int set1= CycleinUndirectedGraph.findMethod2(subset,graph.edges[i].src);
                int set2= CycleinUndirectedGraph.findMethod2(subset,graph.edges[i].dest);

                if (set1==set2){
                    continue;
                }

                if (cheapest[set1]==-1 || graph.edges[i].weight< graph.edges[cheapest[set1]].weight){
                    cheapest[set1]=i;
                }

                if (cheapest[set2]==-1 || graph.edges[i].weight< graph.edges[cheapest[set2]].weight){
                    cheapest[set2]=i;
                }
            }

            for (int i=0;i<graph.V;i++){

                if (cheapest[i]!=-1){

                    int set1= CycleinUndirectedGraph.findMethod2(subset,graph.edges[cheapest[i]].src);
                    int set2= CycleinUndirectedGraph.findMethod2(subset,graph.edges[cheapest[i]].dest);

                    if (set1==set2){
                        continue;
                    }

                    mst+=graph.edges[cheapest[i]].weight;
                    System.out.println(graph.edges[cheapest[i]].src + " ---- "+ graph.edges[cheapest[i]].dest);
                    CycleinUndirectedGraph.unionMethod2(subset,set1,set2);
                    numTrees--;

                }
            }


        }

        System.out.println("Weight of MST is "+mst);
    }

    public static void main(String[] args) {
        //Time Complexiity Elog(V)
        Graph graph= new Graph(4,5);
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

        BourvkaMinimumSpanningTree bmst= new BourvkaMinimumSpanningTree();
        bmst.createMST(graph);
    }
}
