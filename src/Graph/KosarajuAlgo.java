package Graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class KosarajuAlgo {

    int V;
    LinkedList<Integer> edges[];
    KosarajuAlgo(int V){
        this.V=V;
        edges=new LinkedList[this.V];
        for (int i=0;i<this.V;i++){
            edges[i]= new LinkedList<>();
        }
    }

    LinkedList<Integer>[] transpose(){
        LinkedList<Integer> revedges[]= new LinkedList[this.V];
        for (int i=0;i<this.V;i++){
            revedges[i]= new LinkedList<>();
        }

        for (int i=0;i<this.V;i++){

            Iterator<Integer> itr= this.edges[i].listIterator();
            while (itr.hasNext()){
                revedges[itr.next()].add(i);
            }
        }

        return revedges;
    }

    void dfsUtil(boolean visited[],int src,LinkedList<Integer>[] revEdges){

        visited[src]=true;
        System.out.print(src+" ");


        for (int i=0;i<revEdges[src].size();i++){
            if (!visited[revEdges[src].get(i)]){
                dfsUtil(visited,revEdges[src].get(i),revEdges);
            }
        }
    }

    void dfs(Stack<Integer> stack,LinkedList<Integer> revEdges[]){
        boolean visited[]=new boolean[this.V];
        Arrays.fill(visited,false);

       while (!stack.isEmpty()){
           int v= stack.pop();
           if (!visited[v]){
               dfsUtil(visited,v,revEdges);
               System.out.println();
           }
       }
    }

    void fillOrder(boolean visited[],int src,Stack<Integer> stack){
        visited[src]=true;

        for (int i=0;i<this.edges[src].size();i++){
            if(!visited[this.edges[src].get(i)]){
                fillOrder(visited,this.edges[src].get(i),stack);
            }
        }

        stack.add(src);
    }

    void dfsWithFillOrder(Stack<Integer> stack){
        boolean visited[]=new boolean[this.V];
        Arrays.fill(visited,false);

        for (int i=0;i<this.V;i++){
            if ((!visited[i])){
                fillOrder(visited,i,stack);
            }
        }

    }

    public void addEdge(int src,int dest){
       this.edges[src].add(dest);
    }


    public static void main(String[] args) {
        KosarajuAlgo kos= new KosarajuAlgo(5);
        kos.addEdge(1, 0);
        kos.addEdge(0, 2);
        kos.addEdge(2, 1);
        kos.addEdge(0, 3);
        kos.addEdge(3, 4);

        Stack<Integer> stack = new Stack<>();

        kos.dfsWithFillOrder(stack);

        LinkedList<Integer> revEdges[]=kos.transpose();

        kos.dfs(stack,revEdges);


    }
}
