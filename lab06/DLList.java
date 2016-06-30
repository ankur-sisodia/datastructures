public class DLList {
    DLNode sentinel;
    int size;

    public class DLNode {
        Object item;
        DLNode prev, next;

        public DLNode(Object item, DLNode prev, DLNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Construct a new DLList with a sentinel that points to itself.
     */
    public DLList() {
        sentinel = new DLNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Insert into the end of this list
     * @param o Object to insert
     */
    public void insertBack(Object o) {
        DLNode n = new DLNode(o, sentinel.prev, sentinel);
        n.next.prev = n;
        n.prev.next = n;
        size++;
    }


    /**
     * Get the value at position pos. If the position does not exist, return null (the item of
     * the sentinel).
     * @param position to get from
     * @return the Object at the position in the list.
     */
    public Object get(int position) {
        DLNode curr = sentinel.next;
        while (position > 0 && curr != sentinel) {
            curr = curr.next;
            position--;
        }
        return curr.item;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("DLList(");
        DLNode curr = sentinel.next;
        while (curr != sentinel) {
            s.append(curr.item.toString());
            if (curr.next != sentinel) s.append(", ");
            curr = curr.next;
        }
        s.append(')');
        return s.toString();
    }

    /* Fill these in! */

    /**
     * Insert a new node into the DLList.
     * @param o Object to insert
     * @param position position to insert into. If position exceeds the size of the list, insert into
     *            the end of the list.
     */
    public void insert(Object o, int position) {
        // fill me in
        DLNode n = new DLNode(o, sentinel, sentinel.next);
        if(position==0) {
            n.next.prev = n;
            n.prev.next = n;
            size++;
            return;
        }
        else if (position >= size) {
            insertBack(o);
            return;
        }
        else {
            System.out.println("reached");
            DLNode copy = sentinel;
            for (int i=0; i< position; i++) {
                copy = copy.next;
            }
            n.prev = copy;
            n.next = copy.next;
            copy.next = n;
            copy.next.next.prev = n;
            size++;
        }
    }

    /**
     * Insert into the front of this list. You should can do this with a single call to insert().
     * @param o Object to insert
     */
    public void insertFront(Object o) {
        // fill me in
        insert(o,0);
    }

    /**
     * Remove all copies of Object o in this list
     * @param o Object to remove
     */
    public void remove(Object o) {
        // fill me in
        DLNode current = sentinel.next;
        while (current != sentinel){
            if (o == current.item){
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size --;
            }
            current = current.next;
        }
        return;

        /*DLNode copy = sentinel.next;
        for (int i=0; i< size; i++) {
            if (copy.item.equals(o))
            {
                copy.prev.next = copy.next;
                copy.next.prev = copy.prev;
                size--;
            }
            copy = copy.next;
        }
        return;*/
    }

    /**
     * Remove a DLNode from this list. Does not error-check to make sure that the node actually
     * belongs to this list.
     * @param n DLNode to remove
     */
    public void remove(DLNode n) {
        // fill me in
        //DLNode tempPrev, tempNext;
        //tempPrev = n.prev; tempNext = n.next;
        //n.prev.next = tempNext;
        //n.next.prev = tempPrev;
        System.out.println("DLNode: "+ this.toString());
        DLNode curr = sentinel.next;
        while (curr!= sentinel){
            if(n.item == curr.item){
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                size--;
                return;
            }
            curr = curr.next;
        }
        System.out.println("DLNode: "+ this.toString());
        return;
    }


    /**
     * Duplicate each node in this linked list destructively.
     */
    public void doubleInPlace() {
        // fill me in
        DLNode current = sentinel.next;
        DLNode prev = current.prev;

        while (current != sentinel){
            DLNode n = new DLNode(current.item, prev,current);
            prev.next = n;
            current.prev = n;
            size++;

            prev = current;
            current = current.next;
        }
        return;
    }

    /**
     * Reverse the order of this list destructively.
     */
    public void reverse() {
        // fill me in
        DLNode current = sentinel.next;
        sentinel.next = sentinel.prev;
        sentinel.prev = current;

        while (current!=sentinel){
            DLNode tempNode = current.prev;
            current.prev = current.next;
            current.next = tempNode;
            current = current.prev;
        }

    }

    public static void main(String[] args) {
        // you can add some quick tests here if you would like
    }
}
