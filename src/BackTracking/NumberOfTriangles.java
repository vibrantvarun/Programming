//package BackTracking;
//
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.math.BigInteger;
//import java.nio.Buffer;
//import java.util.*;
//
//public class NumberOfTriangles {
//
//    LinkedList<Integer> adj[];
//
//    NumberOfTriangles(int N){
//        adj= new LinkedList[N+1];
//        for (int i=1;i<=N;i++){
//            adj[i]=new LinkedList<>();
//        }
//        addEdge(N);
//    }
//
//    private void addEdge(int N){
//        for (int i=1;i<N;i++){
//            adj[i].add(i+1);
//            adj[i+1].add(i);
//        }
//        adj[N].add(1);
//        adj[1].add(N);
//    }
//
//    private void removeEdges(int x,int y){
//        for (Integer a:adj[x]){
//            adj[a].remove(new Integer(x));
//        }
//
//        for (Integer a:adj[y]){
//            adj[a].remove(new Integer(y));
//        }
//
//        adj[x]=new LinkedList<>();
//        adj[y]=new LinkedList<>();
//
//    }
//
//
//
//
//    void getNumber(int count){
//
//
//
//
//    }
//
//    void getTriangles(int N,int x,int y){
//
//        removeEdges(x,y);
//        boolean visited[][]= new boolean[N+1][N+1];
//        Vector<Edge> edges= new Vector<>();
//        int count=0;
//        for (int i=1;i<=N;i++){
//
//              for (int a: adj[i]){
//                   edges.add(new Edge(i,a));
//                   edges.add(new Edge(a,i));
//              }
//        }
//
//        for (int i=0;i<edges.size();i++){
//            if ()
//                if (adj[i].size()==4){
//                    count=count+N-4+N-5;
//                }else {
//                    count=count+N-4;
//                }
//        }
//
//
//        System.out.println(count);
//
//    }
//
//
//    public static void main(String[] args) throws IOException {
//
//      NumberOfTriangles numberOfTriangles= new NumberOfTriangles(7);
//
//      numberOfTriangles.getTriangles(7,2,5);
//
//        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//
//        Long bigInteger= Long.parseLong(br.readLine());
//
//        StringTokenizer st= new StringTokenizer(br.readLine());
//
//        Long n=0l;
//        BigInteger n= new BigInteger(st.nextToken());
//
//       if (bigInteger==null){
//
//       }
//
//    }
//
//}
//
//class Edge{
//    int a;
//    int b;
//    Edge(int a, int b){
//        this.a=a;
//        this.b=b;
//    }
//
//}
