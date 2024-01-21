import java.util.Iterator;
import java.util.LinkedList;

public class DepthFirstGraph {
    private int nodes;

    // Array  of lists for Adjacency List Representation
    private LinkedList<Integer>[] edges;

    // Constructor
    @SuppressWarnings("unchecked")
    DepthFirstGraph(int nodes) {
        this.nodes = nodes;
        edges = new LinkedList[nodes];
        for (int i = 0; i < nodes; ++i) {
            edges[i] = new LinkedList();
        }
    }

    public static void main(String[] args) {
        DepthFirstGraph g = new DepthFirstGraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Depth First Traversal (starting from vertex 2)");

        g.depthFirst(2);
    }

    // Function to add an edge into the graph
    void addEdge(int startNode, int endNode) {
        edges[startNode].add(endNode);
    }

    // A function used by DFS
    void dfsRecursive(int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;

        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = edges[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                dfsRecursive(n, visited);
            }
        }
    }

    void depthFirst(int v) {
        boolean[] visited = new boolean[nodes];
        dfsRecursive(v, visited);
    }
}