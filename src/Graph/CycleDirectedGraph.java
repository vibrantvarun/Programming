package Graph;

import java.util.LinkedList;


public class CycleDirectedGraph {


    boolean dfsUtil(boolean[] visited,int src,CycleGraph cg,boolean recstack[]){

        if (recstack[src]){
            return true;
        }
        if (visited[src]){
            return false;
        }
        visited[src]=true;

        recstack[src]=true;

        System.out.print(src+" ");
        for (int i=0;i<cg.edges[src].size();i++){

            if (dfsUtil(visited,cg.edges[src].get(i),cg,recstack)){
                return true;
            }
        }

        recstack[src]=false;
        return false;
    }

    boolean dfsCyclic(CycleGraph cg){

        boolean v[]= new boolean[cg.V];
        boolean recstack[]= new boolean[cg.V];


        for (int i=0;i<cg.V;i++){
            if (!v[i]){
                 if(dfsUtil(v,i,cg,recstack)){
                     return true;
                 }
            }
        }

        return false;
    }


    static void addEdge(CycleGraph cg,int src,int dest){
        cg.edges[src].add(dest);
    }

    public static void main(String[] args) {
        CycleGraph cg=new CycleGraph(3);

        addEdge(cg,0,1);
        addEdge(cg,1,2);
        addEdge(cg,2,0);
//        addEdge(cg,3,0);
//        addEdge(cg,3,4);
//        addEdge(cg,4,5);
//        addEdge(cg,5,3);


        CycleDirectedGraph cdg= new CycleDirectedGraph();

        boolean isCycleFound=cdg.dfsCyclic(cg);

        if (isCycleFound){
            System.out.println("Cycle is Found");
        }else {
            System.out.println("No Cycle is Found");
        }

    }



}

class CycleGraph{
    int V;
    LinkedList<Integer> edges[];

    CycleGraph(int V){
        this.V=V;
        edges=new LinkedList[V];
        for (int i=0;i<edges.length;i++){
            edges[i]=new LinkedList<>();
        }
    }

}
