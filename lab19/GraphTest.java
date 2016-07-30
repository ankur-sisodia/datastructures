import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by asisodia on 7/29/2016.
 */
public class GraphTest {
    @org.junit.Test
    public void addEdge() throws Exception {
        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        assertTrue(g1.isAdjacent(0, 1));
        assertTrue(g1.isAdjacent(1, 2));
        assertFalse(g1.isAdjacent(2, 2));
    }

    @org.junit.Test
    public void addUndirectedEdge() throws Exception {
        Graph g1 = new Graph(5);
        g1.addUndirectedEdge(0, 1);
        g1.addUndirectedEdge(1, 2);
        g1.addUndirectedEdge(2, 0);
        assertTrue(g1.isAdjacent(0, 1));
        assertTrue(g1.isAdjacent(1, 0));
        assertFalse(g1.isAdjacent(2, 2));
        assertTrue(g1.isAdjacent(1, 2));
        assertTrue(g1.isAdjacent(2, 1));
    }

    @org.junit.Test
    public void isAdjacent() throws Exception {



        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);

        List l = g1.visitAll(0);
        //for(Object o : l) System.out.println(o);
        //System.out.println("running");
        g1.addUndirectedEdge(3, 1);
        assertFalse(g1.isAdjacent(3, 3));
        assertTrue(g1.isAdjacent(3, 1));
        assertFalse(g1.isAdjacent(1, 0));
        assertFalse(g1.isAdjacent(2, 1));
        assertTrue(g1.isAdjacent(1, 3));
    }

    @Test
    public void inDegree() throws Exception {
        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addUndirectedEdge(3, 1);
        assertEquals(2, g1.inDegree(1));
        assertEquals(1, g1.inDegree(2));
        assertEquals(1, g1.inDegree(0));
        assertEquals(1, g1.inDegree(3));
    }

    @Test
    public void pathExists() throws Exception {
        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addUndirectedEdge(3, 1);
        assertTrue(g1.pathExists(0, 1));
        assertTrue(g1.pathExists(1, 2));
        assertTrue(g1.pathExists(3, 1));
        assertTrue(g1.pathExists(1, 3));
        assertTrue(g1.pathExists(3, 3));
        assertTrue(g1.pathExists(1, 2));
        assertTrue(g1.pathExists(0, 2));
        assertTrue(g1.pathExists(1, 0));
        assertTrue(g1.pathExists(2, 1));
        Graph g2 = new Graph(5);
        g2.addEdge(2, 0);
        g2.addEdge(3, 0);
        assertFalse(g2.pathExists(0, 2));
        assertFalse(g2. pathExists(0, 3));
        g2.addEdge(0, 4);
        assertFalse(g2.pathExists(4, 2));
    }
}