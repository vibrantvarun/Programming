package Graph;


import java.util.ArrayList;
import java.util.LinkedList;

public class AllTopologicalOrders{

    int V;
    LinkedList<Integer> edges[];
    AllTopologicalOrders(int V){
        this.V=V;
        edges=new LinkedList[V];
        for (int i=0;i<V;i++){
            edges[i]=new LinkedList<>();
        }
    }


    void AllTopSortUtil(ArrayList<Integer> stack, boolean[] visited,int [] indegrees){

        boolean flag=false;
        for (int i=0;i<this.V;i++){

            if (!visited[i] && indegrees[i]==0){

                visited[i]=true;
                stack.add(i);

                for (int j=0;j<edges[i].size();j++){
                    indegrees[edges[i].get(j)]--;
                }

                AllTopSortUtil(stack,visited,indegrees);


                visited[i]=false;
                stack.remove(stack.size()-1);
                for (int j=0;j<edges[i].size();j++){
                    indegrees[edges[i].get(j)]++;
                }

                flag=true;
            }
        }

        if (!flag){
            stack.forEach(i -> System.out.print(i + " "));
            System.out.println();
        }


    }



    void  AllTopSort(){
        boolean [] visited= new boolean[this.V];
        ArrayList<Integer> stack= new ArrayList<>();
        int indegrees[]= new int[this.V];

        for (int i=0;i<this.V;i++){

            for (int node:this.edges[i]){
                indegrees[node]++;
            }
        }

        AllTopSortUtil(stack,visited,indegrees);

    }

    void addEdge(int src,int dest){
      this.edges[src].add(dest);
    }
    public static void main(String[] args) {
        AllTopologicalOrders ato= new AllTopologicalOrders(6);
        ato.addEdge(5, 2);
        ato.addEdge(5, 0);
        ato.addEdge(4, 0);
        ato.addEdge(4, 1);
        ato.addEdge(2, 3);
        ato.addEdge(3, 1);

        ato.AllTopSort();
    }
}