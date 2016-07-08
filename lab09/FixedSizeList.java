public class FixedSizeList implements SimpleList {

    // instance variables
    protected int[] values;   // list elements
    int count;                // number of array cells used by list

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public FixedSizeList() {
        // YOUR CODE HERE
    }

    public FixedSizeList(int capacity) {
        // YOUR CODE HERE
        values = new int[capacity];
        count = 0;
    }

    // This method should return the number of items 
    // contained in values
    public int size() {
        // YOUR CODE HERE
        return count;
    }

    // This method should return true if the list
    // is empty, otherwise return false
    public boolean isEmpty() {
        // YOUR CODE HERE
        if (count == 0) return true;
        return false;
    }

    // Add the argument to the list by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int k) {
        // YOUR CODE HERE
        if (count == values.length ){
            throw new ListException("List Exception");
        }
        values[count-1] = k;
        count++;
    }

    // This method removes k from the list, if it is present.
    // If k appears multiple times, it removes the first occurence of k
    public void remove(int k) {
        // YOUR CODE HERE
        for (int i = 0; i < count; i++) {
            if (values[i] == k) {
                for (int j = i; j < count; j++) {
                    values[j] = values[j+1];
                }
                count--;
                return;
            }
        }
    }

    // This method returns whether or not the collection contains k
    public boolean contains(int k) {
        // YOUR CODE HERE
        for (int i = 0; i < count; i++) {
            if (values[i] == k) return true;
        }
        return false;
    }

    // Returns the integer stored at the i-th index in the List
    public int get(int i) {
        // YOUR CODE HERE
        return values[i];
    }

    // Insert k into the sequence at position i,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. count < values.length
    // Also, i is between 0 and count, inclusive.
    public void add(int i, int k) {
        if ((i < 0) || (i > count) || (count >= values.length)) {
            throw new ListException("List Exception");
        }
        for (int j = i + 1; j <= count; j++) {
            values[j] = values[j-1];
        }
        values[i] = k;
        count++;
    }


    // Removes the integer in the i-th position in the list,
    // note now this is different from the one-argument remove 
    public void removeIndex(int i) {
        // YOUR CODE HERE
        for (int j = i; i < count; i++) {
            values[j] = values[j+1];
        }
        count--;
    }




}