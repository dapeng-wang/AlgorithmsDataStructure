import java.util.LinkedList;
import java.util.Queue;


class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = null;
        right = null;
    }
}

public class BreadthFirstTree {

    Node root;

    public static void main(String[] args) {
        BreadthFirstTree tree_level = new BreadthFirstTree();
        tree_level.root = new Node(1);
        tree_level.root.left = new Node(2);
        tree_level.root.right = new Node(3);
        tree_level.root.left.left = new Node(4);
        tree_level.root.left.right = new Node(5);

        System.out.println("Level order traversal of binary tree is - ");
        tree_level.breadthFirst();
    }

    /**
     * Time Complexity: O(N) where N is the number of nodes in the binary tree.
     * Auxiliary Space: O(N) where N is the number of nodes in the binary tree.
     */
    void breadthFirst() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {

            // poll() removes the present head.
            Node tempNode = queue.poll();
            System.out.print(tempNode.data + " ");

            // Enqueue left child
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            // Enqueue right child
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
}