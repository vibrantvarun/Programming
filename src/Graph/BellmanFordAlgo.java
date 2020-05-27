package Graph;

public class BellmanFordAlgo {

    public void bellmanFord(Graph graph,int src){

        int V=graph.V;
        int E=graph.E;
        int dist[]=new int[V];

        for(int i=0;i<V;i++){
            dist[i]=Integer.MAX_VALUE;
        }

        dist[src]=0;

        for (int i=1;i<V;i++){

            for (int j=0;j<E;j++){
                int source= graph.edges[j].src;
                int des=graph.edges[j].dest;
                int weight=graph.edges[j].weight;
                if(dist[source]!=Integer.MAX_VALUE && dist[source]+weight<dist[des] ){
                    dist[des]=dist[source]+weight;
                }

            }
        }

        for (int j=0;j<E;j++){

            int source= graph.edges[j].src;
            int des=graph.edges[j].dest;
            int weight=graph.edges[j].weight;
            if(dist[source]!=Integer.MAX_VALUE && dist[source]+weight<dist[des] ){
                System.out.println("Negative cycle is found ");
                return;
            }

        }

        for (int i=0;i<dist.length;i++){
            System.out.println("Vertex is "+i+" Distance is "+dist[i]);
        }


    }


    public static void main(String[] args) {

        Graph graph=new Graph(5,8);
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;
        graph.edges[0].weight = -1;

        // add edge 0-2 (or A-C in above figure)
        graph.edges[1].src = 0;
        graph.edges[1].dest = 2;
        graph.edges[1].weight = 4;

        // add edge 1-2 (or B-C in above figure)
        graph.edges[2].src = 1;
        graph.edges[2].dest = 2;
        graph.edges[2].weight = 3;

        // add edge 1-3 (or B-D in above figure)
        graph.edges[3].src = 1;
        graph.edges[3].dest = 3;
        graph.edges[3].weight = 2;

        // add edge 1-4 (or A-E in above figure)
        graph.edges[4].src = 1;
        graph.edges[4].dest = 4;
        graph.edges[4].weight = 2;

        // add edge 3-2 (or D-C in above figure)
        graph.edges[5].src = 3;
        graph.edges[5].dest = 2;
        graph.edges[5].weight = 5;

        // add edge 3-1 (or D-B in above figure)
        graph.edges[6].src = 3;
        graph.edges[6].dest = 1;
        graph.edges[6].weight = 1;

        // add edge 4-3 (or E-D in above figure)
        graph.edges[7].src = 4;
        graph.edges[7].dest = 3;
        graph.edges[7].weight = -3;


        BellmanFordAlgo bfl= new BellmanFordAlgo();
        bfl.bellmanFord(graph,0);


        Graph graph1=new Graph(4,10);
        graph1.edges[0].src = 0;
        graph1.edges[0].dest = 1;
        graph1.edges[0].weight = 3;

        // add edge 0-2 (or A-C in above figure)
        graph1.edges[1].src = 1;
        graph1.edges[1].dest = 0;
        graph1.edges[1].weight = 3;

        // add edge 1-2 (or B-C in above figure)
        graph1.edges[2].src = 0;
        graph1.edges[2].dest = 2;
        graph1.edges[2].weight = 5;

        // add edge 1-3 (or B-D in above figure)
        graph1.edges[3].src = 2;
        graph1.edges[3].dest = 0;
        graph1.edges[3].weight = 5;

        // add edge 1-4 (or A-E in above figure)
        graph1.edges[4].src = 1;
        graph1.edges[4].dest = 2;
        graph1.edges[4].weight = 2;

        // add edge 3-2 (or D-C in above figure)
        graph1.edges[5].src = 2;
        graph1.edges[5].dest = 1;
        graph1.edges[5].weight = 2;

        // add edge 3-1 (or D-B in above figure)
        graph1.edges[6].src = 1;
        graph1.edges[6].dest = 3;
        graph1.edges[6].weight = 1;

        // add edge 4-3 (or E-D in above figure)
        graph1.edges[7].src = 3;
        graph1.edges[7].dest = 1;
        graph1.edges[7].weight = 1;

        graph1.edges[8].src = 2;
        graph1.edges[8].dest = 3;
        graph1.edges[8].weight = -7;

        graph1.edges[9].src = 3;
        graph1.edges[9].dest = 2;
        graph1.edges[9].weight = -7;

        bfl.bellmanFord(graph1,1);


    }
}

class Graph{
    int V, E;
    Edge edges[];

    Graph(int V, int E){
        this.V=V;
        this.E=E;
        this.edges=new Edge[E];
        for (int i=0; i<E;i++){
            edges[i]=new Edge();
        }
    }


}

class Edge {

    int src, dest, weight;

    Edge() {
        this.src = this.dest = this.weight = 0;
    }
}