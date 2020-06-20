package Graph;

import java.util.Arrays;
import java.util.LinkedList;


public class HamilonianPath {

    int vertices;
    LinkedList<Integer> adj[];
    int [] path;

    HamilonianPath(int v){
       this.vertices=v;
       path=new int[this.vertices];
       adj=new LinkedList[this.vertices];
       for (int i=0;i<this.vertices;i++){
           adj[i]= new LinkedList<>();
       }
    }

    private void addEdge(int u,int v){
        this.adj[u].add(v);
        this.adj[v].add(u);
    }

    boolean isSafe(int v,int pos){

        if (!adj[path[pos-1]].contains(v)){
            return false;
        }

        for (int i=0;i<pos;i++){
            if (path[i]==v){
                return false;
            }
        }

        return true;
    }

    private boolean checkHamiltonian(int pos){
        if (pos==vertices){
            if (adj[path[pos-1]].contains(path[0])){
                return true;
            }else {
                return false;
            }
        }

        for (int i=1;i<this.vertices;i++){
           if (isSafe(i,pos)){
               path[pos]=i;

               if (checkHamiltonian(pos+1)){
                   return true;
               }

               path[pos]=-1;
           }

        }
        return false;
    }

    private int hamiltonianPath(){
        Arrays.fill(path,-1);


        path[0]=0;
        if (!checkHamiltonian(1)){
            System.out.println("\nSolution does not exist");
            return 0;
        }

        printSolution(path);

        return 1;
    }

    void printSolution(int path[])
    {
        System.out.println("Solution Exists: Following" +
                " is one Hamiltonian Cycle");
        for (int i = 0; i < this.vertices; i++)
            System.out.print(" " + path[i] + " ");

        // Let us print the first vertex again to show the
        // complete cycle
        System.out.println(" " + path[0] + " ");
    }

    public static void main(String[] args) {

        HamilonianPath hp= new HamilonianPath(5);
        hp.addEdge(0,1);
        hp.addEdge(0,3);
        hp.addEdge(1,2);
        hp.addEdge(1,3);
        hp.addEdge(1,4);
        hp.addEdge(2,4);
        hp.addEdge(3,4);
        hp.hamiltonianPath();

        HamilonianPath hp1= new HamilonianPath(5);
        hp1.addEdge(0,1);
        hp1.addEdge(0,3);
        hp1.addEdge(1,2);
        hp1.addEdge(1,3);
        hp1.addEdge(1,4);
        hp1.addEdge(2,4);
        hp1.hamiltonianPath();
    }

}
