import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by asisodia on 7/29/2016.
 */
public class ArrayHeapTest {
    @Test
    public void peek() throws Exception {
        ArrayHeap<String> test = new ArrayHeap<String>();
        test.insert("test1",2.0);
        assertEquals(test.peek(), "test1");
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