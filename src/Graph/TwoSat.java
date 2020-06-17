package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class TwoSat {

    static int max=100000;
    static LinkedList<Integer> edges[];
    static LinkedList<Integer> revEdges[];
    static Stack<Integer> stack= new Stack<>();
    static boolean visited[]=new boolean[max];
    static int scc[]= new int[max];
    static int counter=0;

    TwoSat(){
        edges=new LinkedList[max];
        revEdges=new LinkedList[max];
        for (int i=0;i<max;i++){
            edges[i]=new LinkedList<>();
            revEdges[i]=new LinkedList<>();
        }
    }

    public void addEdge(int a, int b){
        this.edges[a].add(b);
    }

    public void addRevEdge(int a,int b){
        this.edges[b].add(a);
    }

    void dfsFirst(int src){
        visited[src]=true;

        for (int i=0;i<edges[src].size();i++){
            if (!visited[edges[src].get(i)]){
                dfsFirst(edges[src].get(i));
            }
        }
        stack.push(src);
    }


    void dfsSecond(int src){
        visited[src]=true;

        for (int i=0;i<revEdges[src].size();i++){
            if (!visited[revEdges[src].get(i)]){
                dfsFirst(revEdges[src].get(i));
            }
        }
        scc[src]=counter;
    }

    void is2Sat(int a[],int b[],int n, int m){

        for (int i=0;i<m;i++){

            if(a[i]>0 && b[i]>0){
                addEdge(a[i]+n,b[i]);
                addRevEdge(a[i]+n,b[i]);
                addEdge(b[i]+n,a[i]);
                addRevEdge(b[i]+n,a[i]);
            } else if(a[i]>0 && b[i]<0){
                addEdge(a[i]+n,n-b[i]);
                addRevEdge(a[i]+n,n-b[i]);
                addEdge(-b[i],a[i]);
                addRevEdge(-b[i],a[i]);
            } else if (a[i]<0 && b[i]>0){
                addEdge(-a[i],b[i]);
                addRevEdge(-a[i],b[i]);
                addEdge(b[i]+n,n-a[i]);
                addRevEdge(b[i]+n,n-a[i]);
            } else {
                addEdge(-a[i],n-b[i]);
                addRevEdge(-a[i],n-b[i]);
                addEdge(-b[i],n-a[i]);
                addRevEdge(-b[i],n-a[i]);
            }
        }

        Arrays.fill(visited,false);
        for (int i=1;i<=2*n;i++){
            if (!visited[i]){
                dfsFirst(i);
            }
        }

        Arrays.fill(visited,false);
        while (!stack.isEmpty()){

            int src= stack.pop();

            if (!visited[src]){
                dfsSecond(src);
                counter++;
            }
        }

        for (int i=1;i<=n;i++){
            if (scc[i]==scc[i+n]){
                System.out.println("The given expression is unsatisfiable");
                return;
            }
        }
        System.out.println("The given expression is satisfiable");
        return;
    }

    public static void main(String[] args) {
       // int n = 5, m = 7;

        int n =2, m=4;
        //int a[] = {1, -2, -1, 3, -3, -4, -3};
        //int b[] = {2, 3, -2, 4, 5, -5, 4};

        int a[]={1, -1, 1, -1};
        int b[]={2, 2, -2, -2};

        TwoSat twoSat= new TwoSat();
        twoSat.is2Sat(a,b,n,m);

    }
}
