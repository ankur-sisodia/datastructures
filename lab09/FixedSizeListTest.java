import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by asisodia on 7/7/2016.
 */
public class FixedSizeListTest {
    @Test
    public void add() throws Exception {
        FixedSizeList f = new FixedSizeList(3);
        f.add(1);
        f.add(1);
        f.add(1);
    }

    @Test
    public void remove() throws Exception {
        FixedSizeList f = new FixedSizeList(3);
        f.add(1);
        f.add(1);
        f.add(1);
        f.remove(1);
        f.remove(1);
        f.remove(1);
    }

    @Test
    public void add1() throws Exception {

    }

    @Test
    public void removeIndex() throws Exception {

    }

    @Test
    public void count() throws Exception {
        FixedSizeList f = new FixedSizeList(3);
        f.add(1);
        assertTrue(f.size()==1);
        f.add(1);
        assertTrue(f.size()==2);
        f.add(2, 1);
        assertTrue(f.size()==3);
        f.removeIndex(1);
        assertTrue(f.size()==2);
        f.remove(1);
        assertTrue(f.size()==1);
        f.remove(1);
        assertTrue(f.size()==0);
    }

}