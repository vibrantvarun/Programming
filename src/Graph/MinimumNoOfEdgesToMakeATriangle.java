package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class MinimumNoOfEdgesToMakeATriangle {

    private int getMinimumNumberOfEdges(Vector<Pair<Integer, Integer>> v, int n) {

        int adj[][] = new int[n + 1][n + 1];

        for (int i = 0; i < v.size(); i++) {
            adj[v.get(i).first][v.get(i).second] = 1;
            adj[v.get(i).second][v.get(i).first] = 1;
        }

        int edgesNeeded = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                for (int k = j + 1; k <= n; k++) {

                    if (adj[i][j] == 1 && adj[j][k] == 1 && adj[k][i] == 1) {
                        return 0;
                    }

                    if (!(adj[i][j]==1 || adj[j][k]==1 || adj[k][i]==1)){
                        edgesNeeded=Math.min(edgesNeeded,3);
                    }else{
                        if (adj[i][j] == 1 && adj[j][k] == 1 || adj[i][j] == 1 && adj[k][i] == 1 || adj[j][k] == 1 && adj[k][i] == 1) {
                            edgesNeeded=1;
                        }else {
                            edgesNeeded=Math.min(edgesNeeded,2);
                        }
                    }

                }
            }
        }

        return edgesNeeded;
    }

    public static void main(String[] args) {
        MinimumNoOfEdgesToMakeATriangle mT = new MinimumNoOfEdgesToMakeATriangle();

        Vector<Pair<Integer,Integer>> vector= new Vector<>(Arrays.asList(new Pair<Integer, Integer>(1,2),new Pair<Integer, Integer>(1,3)));

        System.out.println(mT.getMinimumNumberOfEdges(vector,3));
    }
}

class Pair<V, E> {

    V first;
    E second;

    Pair(V first, E second) {
        this.first = first;
        this.second = second;
    }

}