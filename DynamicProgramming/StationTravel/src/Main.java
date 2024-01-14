import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String[] args) {
//        int[] A = {1, 3, 1, 4, 3};
//        int[] B = {1, 0, 3, 0, 1};
        int[] A = {1, 2, 1, 4, 3};
        int[] B = {1, 0, 3, 0, 1};
        int s = 2;
        int t = 4;
        System.out.println(travelIteration(A, B, A.length, s, t));


        boolean[] stationVisited = new boolean[A.length];
        System.out.println(travelRecursion(A, B, t, s, stationVisited));

    }

    public static boolean travelRecursion(int[] A, int[] B, int t, int current, boolean[] stationVisited) {
        System.out.println("Check " + current);
        // Done
        if (current == t) {
            return true;
        }
        if (!stationVisited[current]) {
            // not visited before, mark es visited to avoid double processing
            stationVisited[current] = true;
        } else {
            // if already visited, target not reached, this path doesn't work out
            return false;
        }
        // Check next station by following A
        int nextA = A[current];
        boolean foundA = travelRecursion(A, B, t, nextA, stationVisited);
        // If A works out, we are done
        if (foundA) {
            return foundA;
        }
        // If A doesn't work out, we check B, which is also the final result
        int nextB = B[current];
        return travelRecursion(A, B, t, nextB, stationVisited);
    }

    public static boolean travelIteration(int[] A, int[] B, int n, int s, int t) {
        boolean[] dpA = new boolean[n];
        boolean[] dpB = new boolean[n];
        int currentA = s;
        int currentB = s;
        boolean finishedA = false;
        boolean finishedB = false;
        do {
            System.out.println("Check A " + currentA + " B " + currentB);
            if (currentA == t) {
                return true;
            }
            if (currentB == t) {
                return true;
            }
            if (!dpA[currentA]) {
                // no visited
                dpA[currentA] = true;
            } else {
                // visited, A finished
                finishedA = true;
            }
            if (!dpB[currentB]) {
                dpB[currentB] = true;
            } else {
                // visited, B finished
                finishedB = true;
            }
            currentA = A[currentA];
            currentB = B[currentB];
        } while(!finishedA && !finishedB);
        return false;
    }

    public static class Node {
        final int value;
        boolean visited;
        boolean addedToQueue;
        LinkedList<Node> neighbors = new LinkedList<>();

        public int getValue() {
            return value;
        }

        public LinkedList<Node> getNeighbors() {
            return neighbors;
        }

        public Node(int value) {
            this.value = value;
            this.visited = false;
            this.addedToQueue = false;
        }

    }

    public static LinkedList<Node> buildGraph(int[] A, int[] B, int n) {
        LinkedList<Node> graph = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Node node = new Node(i);
            graph.add(node);
        }
        for (int i = 0; i < n; i++) {
            Node currentNode = graph.get(i);
            currentNode.neighbors.add(graph.get(A[i]));
            currentNode.neighbors.add(graph.get(B[i]));
        }
        return graph;
    }

    public static boolean traverse(Node start, Node target, LinkedList<Node> graph) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        start.addedToQueue = true;
        int counter = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println("Visit node " + current.value + " Counter " + counter);
            counter++;
            if (current.equals(target)) {
                return true;
            }
            if (!current.visited) {
                current.visited = true;
                for (Node neighbour: current.neighbors) {
                    if (!neighbour.addedToQueue) {
                        queue.add(neighbour);
                        neighbour.addedToQueue = true;
                    }
                }
            }

        }
        return false;
    }

    public static boolean travel(int[] A, int[] B, int n, int s, int t) {
        LinkedList<Node> graph = new LinkedList<>();
        graph = buildGraph(A, B, n);
        return traverse(graph.get(s), graph.get(t), graph);
        /**if(graph.get(t).visited){
         return true;
         } else {
         return false;
         }*/
    }
}
