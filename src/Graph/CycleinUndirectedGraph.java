package Graph;

public class CycleinUndirectedGraph {



    static int findMethod1(int parent[],int i){

        if(parent[i]==-1){
            return i;
        }
        return findMethod1(parent,parent[i]);
    }

    static void unionMethod1(int parent[],int i, int j){
        int xset= findMethod1(parent,i);
        int yset=findMethod1(parent,j);

        parent[xset]=yset;

    }

    static int cyclefromMethod1(Graph g){
        int parent[]=new int[g.V];

        for (int i=0;i<g.V;i++){
            parent[i]=-1;
        }

        for (int i=0;i<g.E;i++){

            int src= findMethod1(parent,g.edges[i].src);
            int dest=findMethod1(parent,g.edges[i].dest);

            if (src==dest){
                return 1;
            }

            unionMethod1(parent,src,dest);
        }

        return 0;

    }

    public static int findMethod2(subset subset[],int i){

        if (subset[i].parent!=i){
            findMethod2(subset,subset[i].parent);
        }
        return subset[i].parent;
    }

    public static void unionMethod2(subset subset[],int x,int y){
        int xroot=findMethod2(subset,x);
        int yroot=findMethod2(subset,y);

        if ((subset[xroot].rank<subset[yroot].rank)){
            subset[xroot].parent=yroot;
        }else if(subset[xroot].rank>subset[yroot].rank){
            subset[yroot].parent=xroot;
        }else {
            subset[xroot].parent=yroot;
            subset[yroot].rank++;
        }
    }

    static int cyclefromMethod2(Graph g){

        subset subset[]=new subset[g.V];
        for (int i=0;i<g.V;i++){
            subset s= new subset();
            s.parent=i;
            s.rank=0;
            subset[i]=s;
        }

        for (int i=0;i<g.E;i++){

            int x=findMethod2(subset,g.edges[i].src);
            int y=findMethod2(subset,g.edges[i].dest);

            if (x==y){
                return 1;
            }

            unionMethod2(subset,x,y);

        }

        return 0;

    }

    public static void main(String[] args) {
        Graph g= new Graph(3,3);

        g.edges[0].src=0;
        g.edges[0].dest=1;


        g.edges[1].src=1;
        g.edges[1].dest=2;

        g.edges[2].src=0;
        g.edges[2].dest=2;


        if(cyclefromMethod1(g)==1){
            System.out.println("Cycle Found");
        }else {
            System.out.println("No Cycle Found");
        }


        if (cyclefromMethod2(g)==1){
            System.out.println("Cycle Found");
        }else {
            System.out.println("No Cycle Found");
        }

    }



}


class subset{
    int parent;
    int rank;
}