package Graph;

import java.util.concurrent.ThreadLocalRandom;

public class KargerMinimumCut {


    int find(subset subset[],int s){

        if (subset[s].parent!=s){
            return find(subset,subset[s].parent);
        }
        return subset[s].parent;
    }

    void union(subset subset[],int x, int y){
        int xroot=find(subset,x);
        int yroot=find(subset,y);

        if (subset[xroot].rank<subset[yroot].rank){
             subset[xroot].parent=yroot;
        }else if (subset[xroot].rank>subset[yroot].rank){
             subset[yroot].parent=xroot;
        }else {
             subset[xroot].parent=yroot;
             subset[yroot].rank++;
        }
    }

    int karger(Graph g){

        subset subset[]= new subset[g.V];
        for (int i=0;i<g.V;i++){
            subset sub= new subset();
            sub.parent=i;
            sub.rank=0;
            subset[i]=sub;
        }

        int vertices=g.V;

        while (vertices>2){

            //choosing a random edge probability is 1/v^2
            int randNumber= ThreadLocalRandom.current().nextInt(g.E);

            int subset1= find(subset,g.edges[randNumber].src);
            int subset2=find(subset,g.edges[randNumber].dest);

            if (subset1==subset2){
                continue;
            }else {
                System.out.println("Contracting Edges "+ g.edges[randNumber].src + " - "+ g.edges[randNumber].dest);
                vertices--;
                union(subset, subset1,subset2);
            }

        }

        int cutEdges=0;
        for (int i=0;i<g.edges.length;i++){
            int subset1= find(subset,g.edges[i].src);
            int subset2= find(subset,g.edges[i].dest);
            if (subset1!=subset2){
                cutEdges++;
            }
        }

        return cutEdges;
    }



    public static void main(String[] args) {

        //Time Complexity //O(v^2)
        Graph graph= new Graph(4,5);
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;

        // add edge 0-2
        graph.edges[1].src = 0;
        graph.edges[1].dest = 2;

        // add edge 0-3
        graph.edges[2].src = 0;
        graph.edges[2].dest = 3;

        // add edge 1-3
        graph.edges[3].src = 1;
        graph.edges[3].dest = 3;

        // add edge 2-3
        graph.edges[4].src = 2;
        graph.edges[4].dest = 3;

        KargerMinimumCut kargerMinimumCut= new KargerMinimumCut();
        System.out.println(kargerMinimumCut.karger(graph));

    }
}
