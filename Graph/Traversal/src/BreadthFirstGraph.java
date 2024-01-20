import java.util.Iterator;
import java.util.LinkedList;

public class BreadthFirstGraph {
    // No. of vertices
    private int nodes;

    // Adjacency Lists
    private LinkedList<Integer> edges[];

    // Constructor
    BreadthFirstGraph(int nodes)
    {
        this.nodes = nodes;
        edges = new LinkedList[nodes];
        for (int i = 0; i < nodes; ++i) {
            edges[i] = new LinkedList();
        }
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) { edges[v].add(w); }

    public static void main(String[] args) {
        BreadthFirstGraph g = new BreadthFirstGraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal (starting from vertex 2)");

        g.breadthFirst(2);
    }


    // A function used by DFS
    void breadthFirst(int node) {
        boolean visited[] = new boolean[nodes];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[node] = true;
        queue.add(node);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            node = queue.poll();
            System.out.print(node + " ");

            // Get all adjacent vertices of the dequeued vertex s.
            // If an adjacent has not been visited, then mark it visited and enqueue it
            Iterator<Integer> i = edges[node].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}