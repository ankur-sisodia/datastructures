import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Spliterator;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Graph implements Iterable<Integer>{

    private LinkedList<Edge>[] adjLists;
    private int vertexCount;
    private int startVertex;

    // Initialize a graph with the given number of vertices and no edges.
    public Graph(int numVertices) {
        adjLists = new LinkedList[numVertices];
        startVertex = 0;
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        vertexCount = numVertices;
    }

    // Change the vertex the iterator will start DFS from
    public void setStartVertex(int v){
        if (v < vertexCount && v >= 0){
            startVertex = v;
        } else {
            throw new IllegalArgumentException("Cannot set iteration start vertex to " + v + ".");
        }
    }


    // Add to the graph a directed edge from vertex v1 to vertex v2.
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, null);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2.
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, null);
    }

    // Add to the graph a directed edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addEdge(int v1, int v2, Object edgeInfo) {
        Edge temp = new Edge(v1, v2, edgeInfo);
        adjLists[v1].add(temp);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
        Edge temp1 = new Edge(v1, v2, edgeInfo);
        Edge temp2 = new Edge(v2, v1, edgeInfo);
        adjLists[v1].add(temp1);
        adjLists[v2].add(temp2);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
        return pathExists(from, to);
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Edge e : adjLists[vertex]) {
            list.add(e.to);
        }
        return list;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i = 0; i < adjLists.length; i++) {
            for (Edge e : adjLists[i]) {
                if (e.to == vertex) {
                    count++;
                }
            }
        }
        return count;
    }

    public Iterator<Integer> iterator(){
        return new TopologicalIterator();
    }

    // A class that iterates through the vertices of this graph, starting with a given vertex.
    // Does not necessarily iterate through all vertices in the graph: if the iteration starts
    // at a vertex v, and there is no path from v to a vertex w, then the iteration will not
    // include w
    private class DFSIterator implements Iterator<Integer> {

        private Stack<Integer> fringe = new Stack<Integer>();
        private HashSet<Integer> visited = new HashSet<Integer>();

        public DFSIterator(Integer start) {
            //your code here
            fringe.push(start);
            visited.add(start);
        }

        public boolean hasNext() {
            //your code here
            return !fringe.isEmpty();
        }

        public Integer next() {
            //your code here
            Integer next = fringe.pop();
            visited.add(next);
            for (Edge e : adjLists[next]) {
                if (!visited.contains(e.to)) {
                    fringe.push(e.to);
                    visited.add(e.to);
                }
            }
            return next;
        }
        
        //ignore this method
        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

        @Override
        public void forEachRemaining(Consumer<? super Integer> action) {
            // TODO Auto-generated method stub
            
        }
    }

    // Return the collected result of iterating through this graph's
    // vertices as an ArrayList.
    public ArrayList<Integer> visitAll(int startVertex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);

        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    // Returns true iff there exists a path from STARVETEX to
    // STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
    // in this graph. If STARVERTEX == STOPVERTEX, returns true.
    public boolean pathExists(int startVertex, int stopVertex) {
        // your code here
        ArrayList<Integer> startVisit = visitAll(startVertex);
        
        return startVisit.contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        // you supply the body of this method
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);

        if (startVertex == stopVertex) {
            result.add(startVertex);
            return result;
        }
        if (!pathExists(startVertex, stopVertex))
            return result;

        while (iter.hasNext()) {
            result.add(iter.next());
            if (result.contains(stopVertex))
                break;
        }
        ArrayList<Integer> forwardPath = new ArrayList<Integer>();
        forwardPath.add(0,stopVertex);
        while (forwardPath.get(0)!=startVertex) {
            int i = 0;
            //while(!isAdjacent(result.get(i), stopVertex) || ((!isAdjacent(stopVertex, result.get(i))) && (!isAdjacent(result.get(i), stopVertex))))
            //{
            while(!isAdjacent(result.get(i), stopVertex)) {
                i++;
            }
            stopVertex = result.get(i);
            forwardPath.add(0,stopVertex);
        }


        return result;

    }
    
    HashMap<Integer, Integer> distances;
    
    public Edge getEdge(int start, int end) {
        for (Edge edge : adjLists[start]) {
            if (edge.to == end) {
                return edge;
            }
        }
        return null;
    }
    
    public ArrayList<Integer> shortestPath(int startVertex, int endVertex){
        //your code here...
        Comparator<Integer> comparator = new IntegerComparator();
        distances = new HashMap<Integer, Integer>();
        PriorityQueue<Integer> fringe = new PriorityQueue<Integer>(10, comparator);
        ArrayList<Integer> path = new ArrayList<Integer>();
        ArrayList<Integer> visited = new ArrayList<Integer>();
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        
        int[] vertexDistance = new int[adjLists.length];
        
        Integer pred = 0;
        Integer distance = 0;
        
        distances.put(startVertex, 0);
        fringe.add(startVertex);
        
        while (!fringe.isEmpty()) {
            Integer v = fringe.poll();
            
            if (v == endVertex) {
                break;
            }
            
            if (visited.contains(v)) {
                continue;
            }
            
            visited.add(v);
            neighbors = (ArrayList<Integer>) neighbors(v);
            
            for (Integer w : neighbors) {
                if (visited.contains(w)) {
                    continue;
                }
                if (!fringe.contains(w)) {
                    distance = distances.get(v);
                    distance += (int) getEdge(v, w).edgeInfo;
                    distances.put(w, distance);
                    fringe.add(w);
                    vertexDistance[w] = v;
                } else {
                    pred = distances.get(w);
                    distance = distances.get(v);
                    distance += (int) getEdge(v, w).edgeInfo;
                    vertexDistance[w] = v;
                    if (distance < pred) {
                        distances.put(w, distance);
                    } else {
                        continue;
                    }
                }

                }

            }
        
        int curr = endVertex;
        
        while (curr != startVertex) {
            path.add(curr);
            curr = vertexDistance[curr];
        }
        
        path.add(startVertex);
        
        Collections.reverse(path);
        
        return path;
    }
    
    public class IntegerComparator implements Comparator<Integer>
    {
        @Override
        public int compare(Integer x, Integer y)
        {
            
            int xVal = distances.get(x);
            int yVal = distances.get(y);
            
            if (xVal < yVal)
            {
                return -1;
            }
            if (xVal > yVal)
            {
                return 1;
            }
            return 0;
        }

        @Override
        public Comparator<Integer> reversed() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Comparator<Integer> thenComparing(
                Comparator<? super Integer> other) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public <U> Comparator<Integer> thenComparing(
                Function<? super Integer, ? extends U> keyExtractor,
                Comparator<? super U> keyComparator) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public <U extends Comparable<? super U>> Comparator<Integer> thenComparing(
                Function<? super Integer, ? extends U> keyExtractor) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Comparator<Integer> thenComparingInt(
                ToIntFunction<? super Integer> keyExtractor) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Comparator<Integer> thenComparingLong(
                ToLongFunction<? super Integer> keyExtractor) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Comparator<Integer> thenComparingDouble(
                ToDoubleFunction<? super Integer> keyExtractor) {
            // TODO Auto-generated method stub
            return null;
        }

        public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
            // TODO Auto-generated method stub
            return null;
        }

        public <T> Comparator<T> nullsFirst(
                Comparator<? super T> comparator) {
            // TODO Auto-generated method stub
            return null;
        }

        public <T> Comparator<T> nullsLast(
                Comparator<? super T> comparator) {
            // TODO Auto-generated method stub
            return null;
        }

        public <T, U> Comparator<T> comparing(
                Function<? super T, ? extends U> keyExtractor,
                Comparator<? super U> keyComparator) {
            // TODO Auto-generated method stub
            return null;
        }

        public <T, U extends Comparable<? super U>> Comparator<T> comparing(
                Function<? super T, ? extends U> keyExtractor) {
            // TODO Auto-generated method stub
            return null;
        }

        public <T> Comparator<T> comparingInt(
                ToIntFunction<? super T> keyExtractor) {
            // TODO Auto-generated method stub
            return null;
        }

        public <T> Comparator<T> comparingLong(
                ToLongFunction<? super T> keyExtractor) {
            // TODO Auto-generated method stub
            return null;
        }

        public <T> Comparator<T> comparingDouble(
                ToDoubleFunction<? super T> keyExtractor) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private HashMap<Integer, Integer> currentInDegree;
        ArrayList<Integer> results;

        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            // more statements go here
            currentInDegree = new HashMap<Integer, Integer>();
            results = new ArrayList<Integer>();
            Integer degree;
            
            for (int i = 0; i < adjLists.length; i++) {
                for (Edge e : adjLists[i]) {
                    degree = inDegree(e.to);
                    
                    if (!currentInDegree.containsKey(e.to)) {
                        currentInDegree.put(e.to, degree);
                    }
                    
                    if (degree == 0) {
                        fringe.add(e.to);
                    }
                }
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            // you supply the real body of this method
            Integer next = fringe.pop();
            results.add(next);
            ArrayList<Integer> list = (ArrayList<Integer>) neighbors(next);
            int val;
            for (Integer i : list) {
                if (currentInDegree.containsKey(i)) {
                    val = currentInDegree.get(i);
                    currentInDegree.replace(i, val - 1);
                }
            }
            for (HashMap.Entry<Integer, Integer> entry : currentInDegree.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                
                if (value == 0) {
                    fringe.add(key);
                }
            }
            return next;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

        @Override
        public void forEachRemaining(Consumer<? super Integer> action) {
            // TODO Auto-generated method stub
            
        }

    }

    private class Edge {

        private Integer from;
        private Integer to;
        private Object edgeInfo;

        public Edge(int from, int to, Object info) {
            this.from = new Integer(from);
            this.to = new Integer(to);
            this.edgeInfo = info;
        }

        public Integer to() {
            return to;
        }

        public Object info() {
            return edgeInfo;
        }

        public String toString() {
            return "(" + from + "," + to + ",dist=" + edgeInfo + ")";
        }

    }

    public static void main(String[] args) {
        ArrayList<Integer> result;

        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(0, 4);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(2, 3);
        g1.addEdge(4, 3);
        System.out.println("Traversal starting at 0");
        result = g1.visitAll(0);
        Iterator<Integer> iter;
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 2");
        result = g1.visitAll(2);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 3");
        result = g1.visitAll(3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 4");
        result = g1.visitAll(4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 3");
        result = g1.path(0, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 4");
        result = g1.path(0, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 3");
        result = g1.path(1, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 4");
        result = g1.path(1, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 4 to 0");
        result = g1.path(4, 0);
        if (result.size() != 0) {
            System.out.println("*** should be no path!");
        }

        Graph g2 = new Graph(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(0, 4);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.addEdge(4, 3);
        System.out.println();
        System.out.println();
        System.out.println("Topological sort");
        result = g2.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Spliterator<Integer> spliterator() {
        // TODO Auto-generated method stub
        return null;
    }

}
