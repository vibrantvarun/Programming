package Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class MaximumEdgesInDAGItRemainsDAG {

    int V;
    LinkedList<Integer> edges[];
    private MaximumEdgesInDAGItRemainsDAG(int V){
        this.V=V;
        this.edges=new LinkedList[this.V];
        for (int i=0;i<this.V;i++){
            edges[i]=new LinkedList<>();
        }
    }

    private void addEdge(int src,int dest){
       this.edges[src].add(dest);
    }


    private Vector<Integer> topSort(){

        int inorders[]=new int[this.V];
        for (int i=0;i<this.V;i++){
            for (int j=0;j<this.edges[i].size();j++){
                int v=this.edges[i].get(j);
                inorders[v]++;
            }
        }

        Queue<Integer> queue= new LinkedList<>();

        Vector<Integer> topSort= new Vector<>();
        for (int i=0;i<inorders.length;i++){
            if (inorders[i]==0){
                queue.add(i);
            }
        }


        while (!queue.isEmpty()){

            int node= queue.remove();
            topSort.add(node);
            for (int i=0;i<this.edges[node].size();i++){
                if (--inorders[this.edges[node].get(i)]==0){
                    queue.add(this.edges[node].get(i));
                }
            }

        }

        return topSort;
    }

    private void getMaxNoOfEdges(){

        Vector<Integer> topSort= topSort();

        boolean visited[]= new boolean[this.V];
        for (int i=0;i<=topSort.size()-1;i++){

            Iterator<Integer> itr= this.edges[topSort.get(i)].listIterator();

            while (itr.hasNext()){
                visited[itr.next()]=true;
            }

           for (int j=i+1;j<=topSort.size()-1;j++){

               if ((!visited[topSort.get(j)])){
                   System.out.println(topSort.get(i)+" --- "+topSort.get(j));
               }
               visited[topSort.get(j)]=false;
           }
        }
    }


    public static void main(String[] args) {
        MaximumEdgesInDAGItRemainsDAG maxDag= new MaximumEdgesInDAGItRemainsDAG(6);

        maxDag.addEdge(5, 2);
        maxDag.addEdge(5, 0);
        maxDag.addEdge(4, 0);
        maxDag.addEdge(4, 1);
        maxDag.addEdge(2, 3);
        maxDag.addEdge(3, 1);

        maxDag.getMaxNoOfEdges();
    }
}
