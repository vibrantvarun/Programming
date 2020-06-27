package Graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class DeBruijnSequenceEuler {

    static Set<String> seen = new HashSet<>();
    static Vector<Integer> edges = new Vector<>();

    String DeBruijnSequenceEuler(int n,int k,String A){

        seen.clear();
        edges.clear();
        String startingNode= getString(n-1,A.charAt(0));

        dfsUtil(A,k,startingNode);

        int totalNodeOfEdges= (int) Math.pow(k,n);

        String str="";

        for (int i=0;i<totalNodeOfEdges;i++){
            str+=A.charAt(edges.get(i));
        }
        str+=startingNode;


        return str;
    }

    void dfsUtil(String A, int k,String node){

        for (int i=0;i<k;i++){
            String str= node+A.charAt(i);
            if (!seen.contains(str)){
               seen.add(str);
               dfsUtil(A,k,str.substring(1));
               edges.add(i);
            }

        }

    }

    String getString(int size,char a){
        String str="";
        for (int i=0;i<size;i++){
            str+=a;
        }
        return str;
    }


    public static void main(String[] args) {
        int n=3;
        int k=2;
        String A= "01";

        DeBruijnSequenceEuler de= new DeBruijnSequenceEuler();
        System.out.println(de.DeBruijnSequenceEuler(n,k,A));

    }
}
