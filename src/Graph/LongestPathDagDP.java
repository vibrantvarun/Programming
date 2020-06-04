package Graph;

import java.util.LinkedList;

public class LongestPathDagDP {

    int vertices;
    LinkedList<Integer> edges[];

    LongestPathDagDP(int V){
        this.vertices=V;
        edges= new LinkedList[V+1];
        for (int i=0;i<=V;i++){
            edges[i]= new LinkedList<>();
        }
    }

    public void addEdge(int src,int dest){
        this.edges[src].add(dest);
    }
    public void dfsUtil(int dp[],boolean[] visited,int src){
        visited[src]=true;

        for (int i=0;i<this.edges[src].size();i++){
            int node= this.edges[src].get(i);
            if (!visited[node]){
                dfsUtil(dp,visited,node);
            }
            dp[src]=Math.max(dp[src],1+dp[node]);
        }

    }


    public void findLongestPathtoAllVertices(){

        boolean visited[]= new boolean[this.vertices+1];
        int dp[]= new int[this.vertices+1];
        for (int i=0;i<=this.vertices;i++){
              if (!visited[i]){
                  dfsUtil(dp,visited,i);
              }
        }

        int ans=0;

        for (int i=0;i<this.vertices;i++){
            ans=Math.max(ans,dp[i]);
        }

        System.out.println(ans);


    }

    public static void main(String[] args) {
        LongestPathDagDP dpLp= new LongestPathDagDP(5);
        dpLp.addEdge( 1, 2);
        dpLp.addEdge( 1, 3);
        dpLp.addEdge( 3, 2);
        dpLp.addEdge( 2, 4);
        dpLp.addEdge( 3, 4);

        dpLp.findLongestPathtoAllVertices();

    }
}
