package Graph;

import java.util.ArrayList;
import java.util.Arrays;


public class FleuryEulerianPathPrint {

    //Time Complexity O((V+E)*(V+E))
    int vertices;
    ArrayList<Integer> adj[];
    FleuryEulerianPathPrint(int v){
        this.vertices=v;
        this.adj= new ArrayList[this.vertices];
        for (int i=0;i<this.vertices;i++){
           this.adj[i]= new ArrayList<>();
        }
    }

    private void addEdge(int u,int v){
        adj[u].add(v);
        adj[v].add(u);
    }

    private void removeEdge(int u,int v){
        adj[u].remove(new Integer(v));
        adj[v].remove(new Integer(u));
    }

    private void eulerPathPrint(){
        int u=0;
        for (int i=0;i<this.vertices;i++){
            if (this.adj[i].size()%2==1){
                u=i;
                break;
            }
        }

        //calculate first odd degree
        eulerPathUtil(u);

        System.out.println();

    }

    private void  eulerPathUtil(int src){

        //DFS on that odd vertex source and start removing valid edges
        for (int i=0;i<this.adj[src].size();i++){
            Integer v= adj[src].get(i);
            if (validEdge(src,v)){

                System.out.print(" Edge is "+src+" - "+v+"  ");

                removeEdge(src,v);
                eulerPathUtil(v);
            }
        }
    }

    private boolean validEdge(int u,int v){

        //if size ==1 then only 1 adjacent edge
        if (adj[u].size()==1){
            return true;
        }

        //check for bridge and  a non-bridge
        boolean visited[]= new boolean[this.vertices];
        Arrays.fill(visited,false);
        //get Count of reachability
        int count1= dfsUtil(u,visited);

        removeEdge(u,v);
        Arrays.fill(visited,false);
        //get count of reachablity by removing that edge
        int count2= dfsUtil(u,visited);

        //again add
        addEdge(u,v);

        //if count after removing not same then it was a bridge
        return (count1>count2)?false:true;

    }

    private int dfsUtil(int src,boolean visited[]){
        //count of depth
        int count=1;
        visited[src]=true;

        for(int adjNode:adj[src]){
            if (!visited[adjNode]){
                count+=dfsUtil(adjNode,visited);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //TimeComplexity O(E*E)
        FleuryEulerianPathPrint g1= new FleuryEulerianPathPrint(4);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.eulerPathPrint();


        FleuryEulerianPathPrint g2= new FleuryEulerianPathPrint(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0);
        g2.eulerPathPrint();

        FleuryEulerianPathPrint g3= new FleuryEulerianPathPrint(5);
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(3, 2);
        g3.addEdge(3, 1);
        g3.addEdge(2, 4);
        g3.eulerPathPrint();


    }
}
