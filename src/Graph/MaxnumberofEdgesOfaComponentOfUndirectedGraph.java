package Graph;

import java.util.LinkedList;

public class MaxnumberofEdgesOfaComponentOfUndirectedGraph {

    int V;
    LinkedList<Integer> edges[];

    MaxnumberofEdgesOfaComponentOfUndirectedGraph(int V){
        this.V=V;
        edges= new LinkedList[this.V+1];
        for (int i=0;i<=this.V;i++){
            this.edges[i]= new LinkedList<>();
        }

    }

    public void addEdge(int src,int dest){
        this.edges[src].add(dest);
        this.edges[dest].add(src);
    }

    public Integer dfsUtil(boolean visited[],int src){
        Integer res=this.edges[src].size();
        visited[src]=true;

        for (int i=0;i<this.edges[src].size();i++){
            if (!visited[this.edges[src].get(i)]){
                res+=dfsUtil(visited,this.edges[src].get(i));
            }
        }

        return res;
    }

    public void dfs(){
        boolean [] visited= new boolean[this.V+1];
        int res= Integer.MIN_VALUE;

        for (int i=0;i<=this.V;i++){
            int numberOfEdges=dfsUtil(visited,i);
            res=Math.max(res,numberOfEdges/2);
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
         MaxnumberofEdgesOfaComponentOfUndirectedGraph max= new MaxnumberofEdgesOfaComponentOfUndirectedGraph(3);
         max.addEdge(1,2);
         max.addEdge(2,3);
         max.dfs();
    }
}
