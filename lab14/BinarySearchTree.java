
/** A class implementing a BST.
 * @author CS 61BL Staff.
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	BinarySearchTree<T> bstTree;

	/* Constructs an empty BST. */
	public BinarySearchTree() {
		//YOUR CODE HERE
		super();
	}

	/* Constructs a BST with root MYROOT. */
	public BinarySearchTree(TreeNode root) {
		//YOUR CODE HERE
		super(root);
	}

	public boolean containsHelper(TreeNode node, T key) {
		//YOUR CODE HERE
		if (node == null) {
			return false;
		}
		if (node.item.compareTo(key) == 0) {
			return true;
		}
		else if (node.item.compareTo(key) > 0) {
			return containsHelper(node.left, key);
		}
		else if (node.item.compareTo(key) < 0) {
			return containsHelper(node.right, key);
		}
		else {
			return false;
		}
	}

	/* Returns true if and only if KEY is in the BST. */
	public boolean contains(T key) {
		//YOUR CODE HERE
		if (root == null) {
			return false;
		}
		if (root.item.compareTo(key) == 0) {
			return true;
		}
		else {
			return containsHelper(root, key);
		}
	}

	public TreeNode addHelper(TreeNode node, T key) {
		if (node == null) {
			return new TreeNode(key);
		}
		else if (key.compareTo(node.item) < 0) {
			node.left = addHelper(node.left, key);
			return node;
		}
		else {
			node.right = addHelper(node.right, key);
			return node;
		}

	}

	/* Adds a node for KEY iff it isn't in the BST already. */
	public void add(T key) {
		//YOUR CODE HERE
		if (contains(key)) {
			return;
		}
		else {
			root = addHelper(root, key);
		}
	}

	/* Deletes the node with KEY. */
	public T delete(T key) {
		TreeNode parent = null;
		TreeNode current = root;
		TreeNode deleteNode = null;
		TreeNode replacement = null;
		boolean rightSide = false;

		while (current != null && !current.item.equals(key)) {
			if (((Comparable<T>) current.item).compareTo(key) > 0) {
				parent = current;
				current = current.left;
				rightSide = false;
			} else {
				parent = current;
				current = current.right;
				rightSide = true;
			}
		}
		deleteNode = current;
		if (current == null) {
			return null;
		}

		if (deleteNode.right == null) {
			if (root == deleteNode) {
				root = root.left;
			} else {
				if (rightSide) {
					parent.right = deleteNode.left;
				} else {
					parent.left = deleteNode.left;
				}
			}
		} else {
			current = deleteNode.right;
			replacement = current.left;
			if (replacement == null) {
				replacement = current;
			} else {
				while (replacement.left != null) {
					current = replacement;
					replacement = replacement.left;
				}
				current.left = replacement.right;
				replacement.right = deleteNode.right;
			}
			replacement.left = deleteNode.left;
			if (root == deleteNode) {
				root = replacement;
			} else {
				if (rightSide) {
					parent.right = replacement;
				} else {
					parent.left = replacement;
				}
			}
		}
		return deleteNode.item;
	}

	public static void main(String[] args) {
		BinarySearchTree<String> x = new BinarySearchTree<String>();
		x.add("C");
		x.add("A");
		x.add("E");
		x.add("B");
		x.add("D");
	}
}