package Graph;

import java.util.Arrays;
import java.util.LinkedList;

public class EulerianPathCheck {

    int vertices;
    LinkedList<Integer> adj[];
    EulerianPathCheck(int vertices){
        this.vertices=vertices;
        adj=new LinkedList[this.vertices];
        for (int i=0;i<this.vertices;i++){
            adj[i]=new LinkedList<>();
        }
    }

    private void addEdge(int u, int v){
        this.adj[u].add(v);
        this.adj[v].add(u);
    }

    void dfsUtil(int src, boolean visited[]){
       visited[src]=true;

       for (int i=0;i<adj[src].size();i++){
           if (!visited[adj[src].get(i)]){
               dfsUtil(adj[src].get(i),visited);
           }
       }
    }

    boolean isConnected(){

        boolean visited[]= new boolean[this.vertices];
        Arrays.fill(visited,false);
        int i=0;
        for (;i<this.vertices;i++){
            if (adj[i].size()!=0){
                break;
            }
        }

        //if no edges are there at all
        if (i==this.vertices){
            return true;
        }

        //check from first Non-zero degree vertex
        dfsUtil(i,visited);


        for (int j=0;j<this.vertices;j++){
            if (visited[j]==false && adj[j].size()!=0){
                return false;
            }
        }

        return true;
    }

    int isEulerian(){

        //Check if all non-zero degrees edges are connected
        if (!isConnected()){
            return 0;
        }

        //check if the odd degree count is not greator than 2
        int oddDegreeCount=0;
        for (int i=0;i<this.vertices;i++){
            if (adj[i].size()%2!=0){
                oddDegreeCount++;
            }
        }

        if (oddDegreeCount>2){
            return 0;
        }

        //unweightedGraph cannot have 1 odddegree
        return (oddDegreeCount==2)?1:2;
    }


    void test()
    {
        int res = isEulerian();
        if (res == 0)
            System.out.println("graph is not Eulerian");
        else if (res == 1)
            System.out.println("graph has a Euler path");
        else
            System.out.println("graph has a Euler cycle");
    }

    public static void main(String[] args) {

        EulerianPathCheck g1= new EulerianPathCheck(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.test();

        EulerianPathCheck g2= new EulerianPathCheck(5);
        g2.addEdge(1, 0);
        g2.addEdge(0, 2);
        g2.addEdge(2, 1);
        g2.addEdge(0, 3);
        g2.addEdge(3, 4);
        g2.addEdge(4, 0);
        g2.test();

        EulerianPathCheck g3= new EulerianPathCheck(5);
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(1, 3);
        g3.test();

        EulerianPathCheck g4= new EulerianPathCheck(3);
        g4.addEdge(0, 1);
        g4.addEdge(1, 2);
        g4.addEdge(2, 0);
        g4.test();

        EulerianPathCheck g5= new EulerianPathCheck(3);
        g5.test();


    }
}
