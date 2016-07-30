import java.util.ArrayList;

/** A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T) and an associated priority value.
 * @author CS 61BL Staff */
public class ArrayHeap<T> {

	/* DO NOT CHANGE THESE METHODS. */

	/* An ArrayList that stores the nodes in this binary heap. */
	private ArrayList<Node> contents;

	/* A constructor that initializes an empty ArrayHeap. */
	public ArrayHeap() {
		contents = new ArrayList<>();
		contents.add(null);
	}

	/* Returns the node at index INDEX. */
	private Node getNode(int index) {
		if (index > contents.size()) {
			return null;
		} else {
			return contents.get(index);
		}
	}

	private void setNode(int index, Node n) {
		// In the case that the ArrayList is not big enough
		// add null elements until it is the right size
		while (index + 1 >= contents.size()) {
			contents.add(null);
		}
        System.out.println("pre set node contents: " + contents.toString());
		contents.set(index, n);
        System.out.println("post set node contents: " + contents.toString());

    }

	/* Swap the nodes at the two indices. */
	private void swap(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		this.contents.set(index1, node2);
		this.contents.set(index2, node1);
	}

	/* Prints out the heap sideways. Use for debugging. */
	@Override
	public String toString() {
		return toStringHelper(1, "");
	}

	/* Recursive helper method for toString. */
	private String toStringHelper(int index, String soFar) {
		if (getNode(index) == null) {
			return "";
		} else {
			String toReturn = "";
			int rightChild = getRightOf(index);
			toReturn += toStringHelper(rightChild, "        " + soFar);
			if (getNode(rightChild) != null) {
				toReturn += soFar + "    /";
			}
			toReturn += "\n" + soFar + getNode(index) + "\n";
			int leftChild = getLeftOf(index);
			if (getNode(leftChild) != null) {
				toReturn += soFar + "    \\";
			}
			toReturn += toStringHelper(leftChild, "        " + soFar);
			return toReturn;
		}
	}

	/* A Node class that stores items and their associated priorities. */
	public class Node {
		private T item;
		private double priority;

		private Node(T item, double priority) {
			this.item = item;
			this.priority = priority;
		}

		public T item(){
			return this.item;
		}

		public double priority() {
			return this.priority;
		}

		@Override
		public String toString() {
			return this.item.toString() + ", " + this.priority;
		}
	}



	/* FILL IN THE METHODS BELOW. */

	/* Returns the index of the node to the left of the node at i. */
	private int getLeftOf(int i) {
		//YOUR CODE HERE
		return 2*i;

	}

	/* Returns the index of the node to the right of the node at i. */
	private int getRightOf(int i) {
		//YOUR CODE HERE
		return 2*i+1;
	}

	/* Returns the index of the node that is the parent of the node at i. */
	private int getParentOf(int i) {
		//YOUR CODE HERE
        if (i < 0) {return 0;}
        if (i < 2) {return i;}
        return (i/2);

	}

	/* Adds the given node as a left child of the node at the given index. */
	private void setLeft(int index, Node n) {
		//YOUR CODE HERE
        setNode(getLeftOf(index), n);
	}

	/* Adds the given node as the right child of the node at the given index. */
	private void setRight(int index, Node n) {
		//YOUR CODE HERE
        setNode(getRightOf(index), n);
	}

	/** Returns the index of the node with smaller priority. Precondition: not
	 * both nodes are null. */
	private int min(int index1, int index2) {
		//YOUR CODE HERE
        if(getNode(index1) == null & getNode(index2) == null)
            return -1;
        if(getNode(index1) == null)
            return index2;
        if(getNode(index2) == null)
            return index1;
		if(getNode(index1).priority() <= getNode(index2).priority())
			return index1;
		return index2;
	}

	/* Returns the Node with the smallest priority value, but does not remove it
	 * from the heap. */
	public Node peek() {
		//YOUR CODE HERE
        if (contents != null && getNode(1) != null) {
            return getNode(1);
        }
        return null;
	}

	/* Bubbles up the node currently at the given index. */
	private void bubbleUp(int index) {
		//YOUR CODE HERE
        /*while (getParentOf(index) != -1 && (getNode(index).priority() >= getNode(getParentOf(index)).priority())) {
            swap(index, getParentOf(index));
            index = index/2;
        }*/

        int parent = getParentOf(index);
        while (index > 0 && getNode(parent).priority() > getNode(index).priority())
        {
            swap(parent, index);
            index = parent;
            parent = getParentOf(index);
        }
	}

	/* Bubbles down the node currently at the given index. */
	private void bubbleDown(int index) {
		//YOUR CODE HERE
        /*int min;
        while(!(getLeftOf(index) == -1 && getRightOf(index) == -1) ||
                getNode(index).priority() > getNode(min(getLeftOf(index),getLeftOf(index))).priority()) {
            min = min(getLeftOf(index),getLeftOf(index));
            swap(index,min);
            index = min;
        }*/

        int curr = index;
        int leftChild = getLeftOf(index);
        int rightChild = getRightOf(index);
        int minIndex = min(curr, min(leftChild, rightChild));
        if (minIndex > 0) {
            if (getNode(minIndex).priority() < getNode(index).priority()) {
                swap(curr, minIndex);
                bubbleDown(minIndex);
            }
        }


        /*while (getNode(index).priority() > getNode(min(getNode(getLeftOf(index)).priority()
                ,getNode(getRightOf(index)).priority()))) {
            swap(index, getParentOf(index));
        }
        if (getLeftOf(index) == -1 && getRightOf(index) == -1)
            return;
        if (getRightOf(index) == -1)
            swap(index,getRightOf(index));
		swap(index, min(getLeftOf(index), getRightOf(index)));*/
	}

	/* Inserts an item with the given priority value. Same as enqueue, or offer. */
	public void insert(T item, double priority) {
		//YOUR CODE HERE
        Node inserting = new Node(item, priority);
        contents.add(inserting);
        int sizeOfContents = contents.size() - 1;
        bubbleUp(sizeOfContents);

        /*Node newNode = new Node(item, priority);
        int index = 1;
        for (int i = 1; i <= contents.size(); i++) {
            index = i;
            if (index == contents.size() || contents.get(index) == null)
                break;
        }
        //contents.add(newNode);
        setNode(index,newNode);
        bubbleUp(index);*/
	}

	/* Returns the Node with the smallest priority value, and removes it from
	 * the heap. Same as dequeue, or poll. */
	public Node removeMin() {
		//YOUR CODE HERE
       /* Node node = peek();
        contents.set(0, contents.get(contents.size() - 1));
        contents.set(contents.size()-1, null);
        bubbleDown(0);
		return node; */
        if (contents.size() < 1) { return null; }
        int sizeOfContents = contents.size() - 1;
        Node first = peek();
        Node removeNode = getNode(sizeOfContents);
        contents.remove(sizeOfContents);
        setNode(0, removeNode);
        bubbleDown(0);
        return first;
	}

	/* Changes the node in this heap with the given item to have the given
	 * priority. You can assume the heap will not have two nodes with the same
	 * item. Check for item equality with .equals(), not == */
	public void changePriority(T item, double priority) {
		//YOUR CODE HERE

        Node repl = new Node(item, priority);

        for (int i = 1; i < contents.size(); i++)
        {
            if (getNode(i).item().equals(item))
            {
                setNode(i, repl);
                bubbleDown(i);
                break;
            }
        }
    }

}