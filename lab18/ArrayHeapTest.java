import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by asisodia on 7/29/2016.
 */
public class ArrayHeapTest {
    @Test
    public void peek() throws Exception {
        ArrayHeap<Integer> test = new ArrayHeap<Integer>();
        test.insert(5,2.0);
        System.out.println("peek: " + test.peek().toString());
    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void removeMin() throws Exception {

    }

    @Test
    public void changePriority() throws Exception {

    }

}