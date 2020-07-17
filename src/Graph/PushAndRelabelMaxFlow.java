package Graph;

import java.util.Vector;

public class PushAndRelabelMaxFlow {

    Vertex vertices[];
    Vector<EDGE> edges;

    PushAndRelabelMaxFlow(int V) {
        vertices = new Vertex[V];
        edges = new Vector<>();
        for (int i=0;i<V;i++){
            vertices[i]=new Vertex(0,0);
        }
    }

    private void addEdge(int u, int v, int capacity) {
        edges.add(new EDGE(u, v, capacity, 0));
    }

    public void preflow(int s) {
        vertices[s].h = vertices.length;

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).u == s) {
                edges.get(i).flow = edges.get(i).capacity;
                vertices[edges.get(i).v].extraflow += edges.get(i).flow;
                edges.add(new EDGE(edges.get(i).v, edges.get(i).u, 0, -edges.get(i).flow));
            }
        }
    }

    int overFlowVertex() {

        for (int i = 1; i < vertices.length - 1; i++) {
            if (vertices[i].extraflow > 0) {
                return i;
            }
        }
        return -1;
    }


    void updateReverseEdgeflow(int i, int flow) {

        int u = edges.get(i).v;
        int v = edges.get(i).u;

        for (int j = 0; j < edges.size(); j++) {
            if (edges.get(j).u == u && edges.get(j).v == v) {
                edges.get(j).flow -= flow;
                return;
            }
        }
        EDGE e = new EDGE(u, v, flow, 0);
        edges.add(e);
    }

    boolean push(int s) {

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).u == s) {
                if (edges.get(i).flow == edges.get(i).capacity) {
                    continue;
                }

                if (vertices[edges.get(i).u].h > vertices[edges.get(i).v].h) {

                    int flow = Math.min(edges.get(i).capacity - edges.get(i).flow, vertices[s].extraflow);
                    vertices[s].extraflow -= flow;
                    vertices[edges.get(i).v].extraflow += flow;
                    edges.get(i).flow += flow;
                    updateReverseEdgeflow(i, flow);
                    return true;
                }
            }
        }

        return false;
    }

    void relabel(int s) {

        int mh = Integer.MAX_VALUE;
        for (int i = 0; i < edges.size(); i++) {

            if (edges.get(i).u == s) {

                if (edges.get(i).flow == edges.get(i).capacity) {
                    continue;
                }

                if (vertices[edges.get(i).v].h < mh) {
                    mh = vertices[edges.get(i).v].h;
                    vertices[s].h = mh + 1;
                }
            }
        }
    }


    int karger(int s, int t) {

        //Preflow is to set all the edges flow and capacity from source
        preflow(s);

        //Check for overflow
        while (overFlowVertex() != -1) {
            //calculate overflow vertex(Vertex with extra flow)
            int u = overFlowVertex();
            //push flow means height of u is greater than v and flow from u to v is possible
            if (!push(u)) {
                //Relabel when no further push is possible because of equal height and we have to relabel them.
                relabel(u);
            }
        }
        return vertices[t].extraflow;
    }

    public static void main(String[] args) {

        PushAndRelabelMaxFlow pushAndRelabelMaxFlow = new PushAndRelabelMaxFlow(6);
        pushAndRelabelMaxFlow.addEdge(0, 1, 16);
        pushAndRelabelMaxFlow.addEdge(0, 2, 13);
        pushAndRelabelMaxFlow.addEdge(1, 2, 10);
        pushAndRelabelMaxFlow.addEdge(2, 1, 4);
        pushAndRelabelMaxFlow.addEdge(1, 3, 12);
        pushAndRelabelMaxFlow.addEdge(2, 4, 14);
        pushAndRelabelMaxFlow.addEdge(3, 2, 9);
        pushAndRelabelMaxFlow.addEdge(3, 5, 20);
        pushAndRelabelMaxFlow.addEdge(4, 3, 7);
        pushAndRelabelMaxFlow.addEdge(4, 5, 4);

        System.out.println(pushAndRelabelMaxFlow.karger(0,5));
    }
}


class Vertex {
    int h;
    int extraflow;

    Vertex(int h,int extraflow){
        this.h=h;
        this.extraflow=extraflow;
    }
}

class EDGE {
    int u;
    int v;
    int capacity;
    int flow;

    EDGE(int u, int v, int capacity, int flow) {
        this.u = u;
        this.v = v;
        this.capacity = capacity;
        this.flow = flow;
    }
}