package Graph;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class HierholzerEulerianPathPrint {

    int vertices;
    LinkedList<Integer> adj[];
    HierholzerEulerianPathPrint(int v){
        this.vertices=v;
        adj=new LinkedList[this.vertices];
        for (int i=0;i<this.vertices;i++){
            adj[i]= new LinkedList<>();
        }
    }


    private void addEdge(int u, int v){
        this.adj[u].add(v);
    }

    private void removeEdge(int u,int v){

        this.adj[u].remove(new Integer(v));
    }


    private void hierholzer(int src){

        if (adj.length==0){
            return;
        }

        Stack<Integer> stack= new Stack<>();

        Vector<Integer> circuit= new Vector<>();

        stack.add(src);
        while (!stack.isEmpty()){
            int curr= stack.peek();
            if (adj[curr].size()>0){
                stack.add(adj[curr].removeLast());

            }else {
                circuit.add(stack.pop());
            }
        }

        for (int i=circuit.size()-1;i>=0;i--){
            System.out.print(circuit.get(i)+ "  ");
        }
    }

    public static void main(String[] args) {
        //TimeComplexity O(V+E)
        HierholzerEulerianPathPrint hier= new HierholzerEulerianPathPrint(3);
        hier.addEdge(0,1);
        hier.addEdge(1,2);
        hier.addEdge(2,0);
        hier.hierholzer(0);

        System.out.println();
        HierholzerEulerianPathPrint hier1= new HierholzerEulerianPathPrint(7);
        hier1.addEdge(0,1);
        hier1.addEdge(0,6);
        hier1.addEdge(1,2);
        hier1.addEdge(2,0);
        hier1.addEdge(2,3);
        hier1.addEdge(3,4);
        hier1.addEdge(4,2);
        hier1.addEdge(4,5);
        hier1.addEdge(5,0);
        hier1.addEdge(6,4);
        hier1.hierholzer(0);



    }
}
