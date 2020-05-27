package Graph;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LongestPathOfUndirectedGraph {

    static class Pair<T, V> {
        T first;
        V second;

        Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }
    }


    static Pair<Integer, Integer> Bfs(CycleGraph cg, int src) {
        int dist[] = new int[cg.V];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();

        dist[src] = 0;

        q.add(src);
        while (!q.isEmpty()) {
            int u = q.peek();
            q.remove();

            for (int i = 0; i < cg.edges[u].size(); i++) {
                int v = cg.edges[u].get(i);
                if (dist[v] == -1) {
                    q.add(v);
                    dist[v] = dist[u] + 1;
                }
            }
        }

        int maxDis = 0;
        int node_index = 0;

        for (int i = 0; i < dist.length; i++) {
            if (dist[i] > maxDis) {
                maxDis = dist[i];
                node_index = i;
            }
        }


        return new Pair<>(node_index, maxDis);
    }

    static void addEdge(CycleGraph cg, int src, int dest) {
        cg.edges[src].add(dest);
        cg.edges[dest].add(src);
    }

    static void findLongestPath(CycleGraph cg) {
        Pair<Integer, Integer> t1, t2;

        t1 = Bfs(cg, 0);
        t2 = Bfs(cg, t1.first);

        System.out.println("Longest path is from " + t1.first
                + " to " + t2.first + " of length " + t2.second);
    }

    public static void main(String[] args) {

        CycleGraph cg = new CycleGraph(10);

        addEdge(cg, 0, 1);
        addEdge(cg, 1, 2);
        addEdge(cg, 2, 3);
        addEdge(cg, 2, 9);
        addEdge(cg, 2, 4);
        addEdge(cg, 4, 5);
        addEdge(cg, 1, 6);
        addEdge(cg, 6, 7);
        addEdge(cg, 6, 8);

        findLongestPath(cg);
    }
}



