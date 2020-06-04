package Graph;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class TotalNoOfPathsinDAG {

    int V;
    LinkedList<Integer> edges[];

    TotalNoOfPathsinDAG(int V){
        this.V=V;
        this.edges= new LinkedList[this.V+1];
        for (int i=0;i<=V;i++){
            this.edges[i]=new LinkedList<>();
        }
    }

    void dfsUtil(boolean[] visited,int src,Stack<Integer> stack){
        visited[src]=true;

        for (int i=0;i<this.edges[src].size();i++){
            int node= this.edges[src].get(i);

            if (!visited[node]) {
                dfsUtil(visited, node, stack);
            }
        }

        stack.add(src);
    }

    void dfs(){
        boolean []visited= new boolean[this.V+1];
        int dp[]= new int[this.V+1];
        dp[1]=1;
        Stack<Integer> stack= new Stack<>();
        for (int i=1;i<=this.V;i++){
            if (!visited[i]){
                dfsUtil(visited,i,stack);
            }
        }

        Vector<Integer> vector= new Vector<>();
        while (!stack.isEmpty()){
            int node= stack.pop();

            vector.add(node);

        }

        for (int i=0;i<vector.size();i++){
            for (int j=0;j<this.edges[vector.get(i)].size();j++){
                dp[this.edges[vector.get(i)].get(j)]+=dp[vector.get(i)];
            }
        }

        for(int i=0;i<dp.length;i++){
            System.out.println(dp[i]);
        }
    }



    public void  addEdge(int src, int dest){

        this.edges[src].add(dest);
    }



    public static void main(String[] args) {
           TotalNoOfPathsinDAG tag= new TotalNoOfPathsinDAG(6);
           tag.addEdge(1,2);
           tag.addEdge(1,4);
           tag.addEdge(2,3);
           tag.addEdge(3,6);
           tag.addEdge(4,5);
           tag.addEdge(5,2);
           tag.addEdge(5,3);

           tag.dfs();


    }
}


