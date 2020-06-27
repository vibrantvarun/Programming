package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DinceMaxFlowAlgo {



    boolean bfs(int s, int t,MaxFlowGraph maxG){

        Arrays.fill(maxG.level,-1);

        Queue<Integer> queue= new LinkedList<>();

        queue.add(s);
        maxG.level[s]=0;

        while (!queue.isEmpty()){
            int u= queue.peek();
            queue.remove();

            for (int i=0;i<maxG.adj[u].size();i++){
                MaxFlowEdge edge= maxG.adj[u].get(i);
                if (maxG.level[edge.v]<0 && edge.flow<edge.C){
                    queue.add(edge.v);
                    maxG.level[edge.v]=maxG.level[u]+1;
                }
            }
        }

        return maxG.level[t]<0?false:true;
    }


    private void addEdge(int u,int v,int capacity,MaxFlowGraph maxG){
        maxG.adj[u].add(new MaxFlowEdge(v,capacity));

    }

    int sendFlow(int u,int t, int flow,int start[],MaxFlowGraph maxG){
        if (u==t){
            return flow;
        }

        for (;start[u]<maxG.adj[u].size();start[u]++){

            MaxFlowEdge edge= maxG.adj[u].get(start[u]);

            if (maxG.level[edge.v]==maxG.level[u]+1 && edge.flow<edge.C){
                int currentFlow= Math.min(flow,edge.C-edge.flow);

                int tempflow= sendFlow(edge.v,t,currentFlow,start,maxG);

                if (tempflow>0){
                    edge.flow+=tempflow;
                    return tempflow;
                }

            }

        }
        return 0;

    }

    int DinceMaxFlowAlgo(MaxFlowGraph maxG,int s,int t){
        if (s==t){
            return -1;
        }
        int total =0;

        while (bfs(s,t,maxG)){
            int start[]= new int[maxG.V+1];

            int flow=Integer.MAX_VALUE;
            while (flow!=0){
                flow=sendFlow(s,t,Integer.MAX_VALUE,start,maxG);
                total+=flow;
            }
        }

        return total;
    }
    public static void main(String[] args) {
        MaxFlowGraph maxG= new MaxFlowGraph(6);
        DinceMaxFlowAlgo dince= new DinceMaxFlowAlgo();

        dince.addEdge(0, 1, 16 ,maxG);
        dince.addEdge(0, 2, 13 ,maxG);
        dince.addEdge(1, 2, 10 ,maxG);
        dince.addEdge(1, 3, 12,maxG );
        dince.addEdge(2, 1, 4 ,maxG);
        dince.addEdge(2, 4, 14,maxG);
        dince.addEdge(3, 2, 9 ,maxG);
        dince.addEdge(3, 5, 20 ,maxG);
        dince.addEdge(4, 3, 7 ,maxG);
        dince.addEdge(4, 5, 4,maxG);

        System.out.println(dince.DinceMaxFlowAlgo(maxG,0,5));
    }
}

class MaxFlowGraph{
    int V;
    int level[];
    LinkedList<MaxFlowEdge> adj[];

    MaxFlowGraph(int V){
        this.V=V;
        this.level=new int[this.V];
        adj=new LinkedList[this.V];

        for (int i=0;i<this.V;i++){
            this.adj[i]=new LinkedList<>();
        }

    }

}


class MaxFlowEdge{

    int v;
    int flow; // flow of data in edge
    int C; //Capacity

    MaxFlowEdge(int v,int C){
        this.v=v;
        this.C=C;
        this.flow=0;
    }

}
