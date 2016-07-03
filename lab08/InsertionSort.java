public class InsertionSort {

    // Precondition: elements 0 through k-1 of list are in increasing order.
    // Postcondition: elements 0 through k of list are in increasing order.
    public static void insert (int list[], int k) {
        int temp1;
        try {
            if (k > 0) {
                isOK(list, (k - 1));
                for (int i = 0; i < k; i++) {
                    if (list[i] > list[k]) {
                        temp1 = list[i];
                        list[i] = list[k];
                        list[k] = temp1;
                    }
                }
            }


        } catch (IllegalStateException e) {
            System.err.println("inconsistency at position " + k);
        }

        try {
            isOK(list, k);
        } catch (IllegalStateException e) {
            System.err.println("inconsistency at position " + k);
        }

    }

    // Does nothing when elements 0 through k of list are sorted in
    // increasing order.
    // Throws an IllegalStateException otherwise.
    public static void isOK (int[] list, int k) {

        if (k > (list.length - 1)) {
            throw new IllegalStateException("K is larger than list.");
        } else if (k < 0) {
            throw new IllegalStateException("K is less than 0.");
        }

        for (int i = 0; i < k; i++) {
            if (list[i] > list[i+1]) {
                throw new IllegalStateException("List not in sorted.");
            }
        }

    }

    public static int[] insertionSort(int[] list) {
        int[] retn = new int[list.length];
        for (int k = 0; k < list.length; k++) {
            retn[k] = list[k];
        }
        for (int k = 0; k < retn.length; k++) {
            insert(retn, k);
            try {
                isOK(retn, k);
            } catch (IllegalStateException e) {
                System.err.println("inconsistency at position " + k);
            }
        }
        return retn;
    }

    public static void main (String[] args) {
        int[] list = {3, 1, 7, 4, 5, 9, 2, 8, 6};
        list = insertionSort(list);
        for (int k = 0; k < list.length; k++) {
            System.out.print(list[k]);
        }
        System.out.println();
    }

}
