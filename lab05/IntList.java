/** A data structure to represent a Linked List of Integers.
  * Each IntList represents one node in the overall Linked List.
  *
  * @author Maurice Lee and Wan Fung Chui
  */

public class IntList {

    /** The integer stored by this node. */
    private int item;
    /** The next node in this IntList. */
    private IntList next;

    /** Constructs an IntList storing ITEM and next node NEXT. */
    public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }

    /** Constructs an IntList storing ITEM and no next node. */
    public IntList(int item) {
        this(item, null);
    }

    /** Returns an IntList consisting of the elements in ITEMS.
      * IntList L = IntList.list(1, 2, 3);
      * System.out.println(L.toString()) // Prints (1 2 3) */
    public static IntList list(int... items) {
        /** Check for cases when we have no element given. */
        if (items.length == 0) {
            return null;
        }
        /** Create the first element. */
        IntList head = new IntList(items[0]);
        IntList last = head;
        /** Create rest of the list. */
        for (int i = 1; i < items.length; i++) {
            last.next = new IntList(items[i]);
            last = last.next;
        }
        return head;
    }

    /** Returns the integer stored by this IntList. */
    public int item() {
        return item;
    }

    /** Returns the next node stored by this IntList. */
    public IntList next() {
        return next;
    }

    /**
     * Returns [position]th item in this list. Throws IllegalArgumentException
     * if index out of bounds.
     *
     * @param position, the position of element.
     * @return The element at [position]
     */
    public int get(int position) {
        // YOUR CODE HERE
        IntList currList = new IntList(this.item, this.next);
        if (position <0)
            throw new IllegalArgumentException("Position is out of range!");
        try {
            for (int i = 0; i <=position; i++) {
                if (i == position) {
                    return currList.item();
                }
                currList = currList.next();
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Position is out of range!");
        }
        return 0;
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        // YOUR CODE HERE
        IntList currList = new IntList(this.item, this.next);
        int sizeValue = 1;
        while (currList.next != null) {
            currList = currList.next();
            sizeValue++;
            }
            return sizeValue++;
    }


    /**
     * Returns the string representation of the list. For the list (1, 2, 3),
     * returns "( 1 2 3 )".
     *
     * @return The String representation of the list.
     */
    public String toString() {
        IntList currList = new IntList(this.item, this.next);
        String s = "( ";
        for (int i=0; i< this.size();i++){
            s = s.concat(currList.item + " ");
            currList = currList.next();
        }
        s = s.concat(")");
        return s;
    }

    /**
     * Returns whether this and the given list or object are equal.
     *
     * @param obj, another list (object)
     * @return Whether the two lists are equal.
     */
    public boolean equals(Object obj) {
        // YOUR CODE HERE
        IntList a = new IntList(this.item, this.next);
        if (!(obj instanceof IntList))
            return false;
        IntList b = (IntList)obj;
        if (a.size()!=b.size())
            return false;
        for (int i =0; i< a.size(); i++){
            if(a.get(i) != b.get(i))
                return false;
        }
        return true;
    }

    /**
     * Adds the given item at the end of the list.
     *
     * @param item, the int to be added.
     */
    public void add(int item) {
        // YOUR CODE HERE
        IntList a = this;
        while (true)
        {
            if (a.next() == null) {break;}
            a = a.next();
        }
        a.next = new IntList(item,null);
    }

    /**
     * Returns the smallest element in the list.
     *
     * @return smallest element in the list
     */
    public int smallest() {
        // YOUR CODE HERE
        IntList currList = new IntList(this.item, this.next);
        int smallest = currList.item;

        while (true)
        {
            if (currList == null) {break;}
            smallest = Math.min(smallest, currList.item());
            currList = currList.next();

        }

        return smallest;
    }

    /**
     * Returns the sum of squares of all elements in the list.
     *
     * @return The sum of squares of all elements.
     */
    public int squaredSum() {
        // YOUR CODE HERE
        IntList currList = new IntList(this.item, this.next);
        int squaredSumValue = 0;

        while (true)
        {
            if (currList == null) {break;}
            squaredSumValue = squaredSumValue + currList.item()*currList.item();
            currList = currList.next();

        }
        return squaredSumValue;
    }

    /**
     * Returns a new IntList consisting of L1 followed by L2,
     * non-destructively.
     *
     * @param l1 list to be on the front of the new list.
     * @param l2 list to be on the back of the new list.
     * @return new list with L1 followed by L2.
     */
    public static IntList append(IntList l1, IntList l2) {
        // YOUR CODE HERE
        /*IntList appendList = new IntList;
        int head1 = l1.item(); int head2 = l2.item();
        IntList next1 = l1.next(); IntList next2 = l2.next();

        for (int i = 0; i<l1.size();i++)
        {
            appendList.add(l1.item());
            l1 = l1.next();
        }
        for (int i = 0; i<l2.size();i++)
        {
            appendList.add(l2.item());
            l2 = l2.next();
        }
        l1 =

        return null;*/

        if (l1 ==null){
            return l2;
        }
        IntList appendList = new IntList(l1.item, append(l1.next,l2));
        return appendList;

    }
}
