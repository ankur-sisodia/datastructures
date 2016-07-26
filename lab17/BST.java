import java.util.*;

public class BST {
    BSTNode root;

    public BST(LinkedList list) {
        root = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree(Iterator iter, int n) {
        // YOUR CODE HERE
        if (n == 1) {
            BSTNode node = new BSTNode();
            node.item = iter.next();
            return node;
        }

        else if (n == 0) {
            return null;
        }
        else {
            BSTNode node = new BSTNode();
            node.left = linkedListToTree(iter, (n/2));
            node.item = iter.next();
            node.right = linkedListToTree(iter, (n/2));
            return node;
        }
    }

    /**
     * Prints the tree to the console.
     */
    private void print() {
        print(root, 0);
    }

    private void print(BSTNode node, int dep) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < dep; i++) {
            System.out.print("  ");
        }
        System.out.println(node.item);
        print(node.left, dep + 1);
        print(node.right, dep + 1);
    }

    /**
     * Node for BST.
     */
    static class BSTNode {

        /** Item. */
        Object item;

        /** Left child * right child. */
        BSTNode left, right;
    }
}
