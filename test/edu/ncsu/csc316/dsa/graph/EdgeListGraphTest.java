package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * Test class for EdgeListGraph
 * Checks the expected outputs of the Graph abstract data type behaviors when using
 * an edge list graph data structure
 *
 * @author Dr. King
 *
 */
public class EdgeListGraphTest {
	/** A graph of strings and integers which will be represented as our undirected graph for testing purposes */
    private Graph<String, Integer> undirectedGraph;
    /** A graph of strings and integers which will be represented as our directed graph for testing purposes */
    private Graph<String, Integer> directedGraph;
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new EdgeListGraph<String, Integer>();
        directedGraph = new EdgeListGraph<String, Integer>(true);
    }
    
    /**
     * Populates our undirected graph
     */
    private void buildUndirectedSample() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
    }
    
    /**
     * Populates our directed graph
     */
    private void buildDirectedSample() {
        Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = directedGraph.insertVertex("Asheville");
        Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = directedGraph.insertVertex("Durham");
        Vertex<String> v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
    }

    /**
     * Test the output of the numVertices() behavior
     */     
    @Test
    public void testNumVertices() {
        buildUndirectedSample();
        assertEquals(5, undirectedGraph.numVertices());
        
        buildDirectedSample();       
        assertEquals(6, directedGraph.numVertices());
    }

    /**
     * Test the output of the vertices() behavior
     */ 
    @Test
    public void testVertices() {
        // We cannot call buildUndirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        // Testing here
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertEquals(v1, it.next());
        assertEquals(v2, it.next());
        assertEquals(v3, it.next());
        assertEquals(v4, it.next());
        assertEquals(v5, it.next());
        
        
        // DIRECTED
        // We cannot call buildDirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing     
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        // Testing here
        Iterator<Vertex<String>> itTwo = directedGraph.vertices().iterator();
        assertEquals(v1, itTwo.next());
        assertEquals(v2, itTwo.next());
        assertEquals(v3, itTwo.next());
        assertEquals(v4, itTwo.next());
        assertEquals(v5, itTwo.next());
    }

    /**
     * Test the output of the numEdges() behavior
     */ 
    @Test
    public void testNumEdges() {
        buildUndirectedSample();
        assertEquals(10, undirectedGraph.numEdges());
        buildDirectedSample();
        assertEquals(11, directedGraph.numEdges());
    }

    /**
     * Test the output of the edges() behavior
     */ 
    @Test
    public void testEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // Testing here
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertEquals(e1, it.next());
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e4, it.next());
        assertEquals(e5, it.next());
        assertEquals(e6, it.next());
        assertEquals(e7, it.next());
        assertEquals(e8, it.next());
        assertEquals(e9, it.next());
        assertEquals(e10, it.next());
        assertFalse(it.hasNext());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // Testing here
        Iterator<Edge<Integer>> itTwo = directedGraph.edges().iterator();
        assertEquals(e1, itTwo.next());
        assertEquals(e2, itTwo.next());
        assertEquals(e3, itTwo.next());
        assertEquals(e4, itTwo.next());
        assertEquals(e5, itTwo.next());
        assertEquals(e6, itTwo.next());
        assertEquals(e7, itTwo.next());
        assertEquals(e8, itTwo.next());
        assertEquals(e9, itTwo.next());
        assertEquals(e10, itTwo.next());
        assertEquals(e11, itTwo.next());
        assertFalse(itTwo.hasNext());
    }

    /**
     * Test the output of the getEdge(v1,v2) behavior
     */ 
    @Test
    public void testGetEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // Testing here
        assertEquals(e1, undirectedGraph.getEdge(v1, v2));
        assertEquals(e2, undirectedGraph.getEdge(v1, v3));
        assertEquals(e3, undirectedGraph.getEdge(v1, v4));
        assertEquals(e4, undirectedGraph.getEdge(v1, v5));
        assertEquals(e5, undirectedGraph.getEdge(v2, v3));
        assertEquals(e6, undirectedGraph.getEdge(v2, v4));
        assertEquals(e7, undirectedGraph.getEdge(v2, v5));
        assertEquals(e8, undirectedGraph.getEdge(v3, v4));
        assertEquals(e9, undirectedGraph.getEdge(v3, v5));
        assertEquals(e10, undirectedGraph.getEdge(v4, v5));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // Testing here
        assertEquals(e1, directedGraph.getEdge(v1, v2));
        assertEquals(e2, directedGraph.getEdge(v1, v3));
        assertEquals(e3, directedGraph.getEdge(v1, v4));
        assertEquals(e4, directedGraph.getEdge(v1, v5));
        assertEquals(e5, directedGraph.getEdge(v2, v3));
        assertEquals(e6, directedGraph.getEdge(v2, v4));
        assertEquals(e7, directedGraph.getEdge(v2, v5));
        assertEquals(e8, directedGraph.getEdge(v3, v4));
        assertEquals(e9, directedGraph.getEdge(v3, v5));
        assertEquals(e10, directedGraph.getEdge(v4, v5));
        assertEquals(e11, directedGraph.getEdge(v5, v6));
    }

    /**
     * Test the output of the endVertices(e) behavior
     */ 
    @Test
    public void testEndVertices() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // Testing here
        assertEquals(v1, undirectedGraph.endVertices(e1)[0]);
        assertEquals(v2, undirectedGraph.endVertices(e1)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e2)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e2)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e3)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e3)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e4)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e4)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e5)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e5)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e6)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e6)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e7)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e7)[1]);
        assertEquals(v3, undirectedGraph.endVertices(e8)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e8)[1]);
        assertEquals(v3, undirectedGraph.endVertices(e9)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e9)[1]);
        assertEquals(v4, undirectedGraph.endVertices(e10)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e10)[1]);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // Testing here
        assertEquals(v1, directedGraph.endVertices(e1)[0]);
        assertEquals(v2, directedGraph.endVertices(e1)[1]);
        assertEquals(v1, directedGraph.endVertices(e2)[0]);
        assertEquals(v3, directedGraph.endVertices(e2)[1]);
        assertEquals(v1, directedGraph.endVertices(e3)[0]);
        assertEquals(v4, directedGraph.endVertices(e3)[1]);
        assertEquals(v1, directedGraph.endVertices(e4)[0]);
        assertEquals(v5, directedGraph.endVertices(e4)[1]);
        assertEquals(v2, directedGraph.endVertices(e5)[0]);
        assertEquals(v3, directedGraph.endVertices(e5)[1]);
        assertEquals(v2, directedGraph.endVertices(e6)[0]);
        assertEquals(v4, directedGraph.endVertices(e6)[1]);
        assertEquals(v2, directedGraph.endVertices(e7)[0]);
        assertEquals(v5, directedGraph.endVertices(e7)[1]);
        assertEquals(v3, directedGraph.endVertices(e8)[0]);
        assertEquals(v4, directedGraph.endVertices(e8)[1]);
        assertEquals(v3, directedGraph.endVertices(e9)[0]);
        assertEquals(v5, directedGraph.endVertices(e9)[1]);
        assertEquals(v4, directedGraph.endVertices(e10)[0]);
        assertEquals(v5, directedGraph.endVertices(e10)[1]);
        assertEquals(v5, directedGraph.endVertices(e11)[0]);
        assertEquals(v6, directedGraph.endVertices(e11)[1]);
    }

    /**
     * Test the output of the opposite(v, e) behavior
     */ 
    @Test
    public void testOpposite() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // Testing here
        assertEquals(v1, undirectedGraph.opposite(v2, e1));
        assertEquals(v3, undirectedGraph.opposite(v1, e2));
        assertEquals(v1, undirectedGraph.opposite(v4, e3));
        assertEquals(v5, undirectedGraph.opposite(v1, e4));
        assertEquals(v2, undirectedGraph.opposite(v3, e5));
        assertEquals(v4, undirectedGraph.opposite(v2, e6));
        assertEquals(v5, undirectedGraph.opposite(v2, e7));
        assertEquals(v3, undirectedGraph.opposite(v4, e8));
        assertEquals(v5, undirectedGraph.opposite(v3, e9));
        assertEquals(v4, undirectedGraph.opposite(v5, e10));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // Testing here
        assertEquals(v1, directedGraph.opposite(v2, e1));
        assertEquals(v3, directedGraph.opposite(v1, e2));
        assertEquals(v1, directedGraph.opposite(v4, e3));
        assertEquals(v5, directedGraph.opposite(v1, e4));
        assertEquals(v2, directedGraph.opposite(v3, e5));
        assertEquals(v4, directedGraph.opposite(v2, e6));
        assertEquals(v5, directedGraph.opposite(v2, e7));
        assertEquals(v3, directedGraph.opposite(v4, e8));
        assertEquals(v5, directedGraph.opposite(v3, e9));
        assertEquals(v4, directedGraph.opposite(v5, e10));
        assertEquals(v6, directedGraph.opposite(v5, e11));
    }

    /**
     * Test the output of the outDegree(v) behavior
     */ 
    @Test
    public void testOutDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // Testing here
        assertEquals(4, undirectedGraph.outDegree(v1));
        assertEquals(4, undirectedGraph.outDegree(v2));
        assertEquals(4, undirectedGraph.outDegree(v3));
        assertEquals(4, undirectedGraph.outDegree(v4));
        assertEquals(4, undirectedGraph.outDegree(v5));
        assertEquals(0, undirectedGraph.outDegree(v6));
        Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
        assertEquals(e1, it.next());
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e4, it.next());
        Iterator<Edge<Integer>> itTwo = undirectedGraph.outgoingEdges(v2).iterator();
        assertEquals(e1, itTwo.next());
        assertEquals(e5, itTwo.next());
        assertEquals(e6, itTwo.next());
        assertEquals(e7, itTwo.next());
        Iterator<Edge<Integer>> itThree = undirectedGraph.outgoingEdges(v3).iterator();
        assertEquals(e2, itThree.next());
        assertEquals(e5, itThree.next());
        assertEquals(e8, itThree.next());
        assertEquals(e9, itThree.next());
        Iterator<Edge<Integer>> itFour = undirectedGraph.outgoingEdges(v4).iterator();
        assertEquals(e3, itFour.next());
        assertEquals(e6, itFour.next());
        assertEquals(e8, itFour.next());
        assertEquals(e10, itFour.next());
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // Testing here
        assertEquals(4, directedGraph.outDegree(v1));
        assertEquals(3, directedGraph.outDegree(v2));
        assertEquals(2, directedGraph.outDegree(v3));
        assertEquals(1, directedGraph.outDegree(v4));
        assertEquals(1, directedGraph.outDegree(v5));
        assertEquals(0, directedGraph.outDegree(v6));
        Iterator<Edge<Integer>> itD = directedGraph.outgoingEdges(v1).iterator();
        assertEquals(e1, itD.next());
        assertEquals(e2, itD.next());
        assertEquals(e3, itD.next());
        assertEquals(e4, itD.next());
        Iterator<Edge<Integer>> itDTwo = directedGraph.outgoingEdges(v2).iterator();
        assertEquals(e5, itDTwo.next());
        assertEquals(e6, itDTwo.next());
        assertEquals(e7, itDTwo.next());
        Iterator<Edge<Integer>> itDThree = directedGraph.outgoingEdges(v3).iterator();
        assertEquals(e8, itDThree.next());
        assertEquals(e9, itDThree.next());
        Iterator<Edge<Integer>> itDFour = directedGraph.outgoingEdges(v4).iterator();
        assertEquals(e10, itDFour.next());
        Iterator<Edge<Integer>> itDFive = directedGraph.outgoingEdges(v5).iterator();
        assertEquals(e11, itDFive.next());
    }

    /**
     * Test the output of the inDegree(v) behavior
     */ 
    @Test
    public void testInDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // Testing here
        assertEquals(4, undirectedGraph.inDegree(v1));
        assertEquals(4, undirectedGraph.inDegree(v2));
        assertEquals(4, undirectedGraph.inDegree(v3));
        assertEquals(4, undirectedGraph.inDegree(v4));
        assertEquals(4, undirectedGraph.inDegree(v5));
        assertEquals(0, undirectedGraph.inDegree(v6));
        Iterator<Edge<Integer>> it = undirectedGraph.incomingEdges(v1).iterator();
        assertEquals(e1, it.next());
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e4, it.next());
        Iterator<Edge<Integer>> itTwo = undirectedGraph.incomingEdges(v2).iterator();
        assertEquals(e1, itTwo.next());
        assertEquals(e5, itTwo.next());
        assertEquals(e6, itTwo.next());
        assertEquals(e7, itTwo.next());
        Iterator<Edge<Integer>> itThree = undirectedGraph.incomingEdges(v3).iterator();
        assertEquals(e2, itThree.next());
        assertEquals(e5, itThree.next());
        assertEquals(e8, itThree.next());
        assertEquals(e9, itThree.next());
        Iterator<Edge<Integer>> itFour = undirectedGraph.incomingEdges(v4).iterator();
        assertEquals(e3, itFour.next());
        assertEquals(e6, itFour.next());
        assertEquals(e8, itFour.next());
        assertEquals(e10, itFour.next());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // Testing here
        // Testing here
        assertEquals(0, directedGraph.inDegree(v1));
        assertEquals(1, directedGraph.inDegree(v2));
        assertEquals(2, directedGraph.inDegree(v3));
        assertEquals(3, directedGraph.inDegree(v4));
        assertEquals(4, directedGraph.inDegree(v5));
        assertEquals(1, directedGraph.inDegree(v6));
        Iterator<Edge<Integer>> itDTwo = directedGraph.incomingEdges(v2).iterator();
        assertEquals(e1, itDTwo.next());
        Iterator<Edge<Integer>> itDThree = directedGraph.incomingEdges(v3).iterator();
        assertEquals(e2, itDThree.next());
        assertEquals(e5, itDThree.next());
        Iterator<Edge<Integer>> itDFour = directedGraph.incomingEdges(v4).iterator();
        assertEquals(e3, itDFour.next());
        assertEquals(e6, itDFour.next());
        assertEquals(e8, itDFour.next());
        Iterator<Edge<Integer>> itDFive = directedGraph.incomingEdges(v5).iterator();
        assertEquals(e4, itDFive.next());
        assertEquals(e7, itDFive.next());
        assertEquals(e9, itDFive.next());
        assertEquals(e10, itDFive.next());
        Iterator<Edge<Integer>> itDSix = directedGraph.incomingEdges(v6).iterator();
        assertEquals(e11, itDSix.next());
    }

    /**
     * Test the output of the outgoingEdges(v) behavior
     */ 
    @SuppressWarnings("unchecked")
    @Test
    public void testOutgoingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // Testing here
        Edge<Integer>[] temp2 = (Edge<Integer>[])(new Edge[4]);
        int countTwo = 0;
        Iterator<Edge<Integer>> itTwo = undirectedGraph.outgoingEdges(v2).iterator();
        assertTrue(itTwo.hasNext());
        temp2[countTwo] = itTwo.next();
        countTwo++;
        temp2[countTwo] = itTwo.next();
        countTwo++;
        temp2[countTwo] = itTwo.next();
        countTwo++;
        temp2[countTwo] = itTwo.next();
        countTwo++;
        assertTrue(arrayContains(temp2, e1));
        assertTrue(arrayContains(temp2, e5));
        assertTrue(arrayContains(temp2, e7));
        assertTrue(arrayContains(temp2, e6));
        
        Edge<Integer>[] temp3 = (Edge<Integer>[])(new Edge[4]);
        int countThree = 0;
        Iterator<Edge<Integer>> itThree = undirectedGraph.outgoingEdges(v3).iterator();
        assertTrue(itThree.hasNext());
        temp3[countThree] = itThree.next();
        countThree++;
        temp3[countThree] = itThree.next();
        countThree++;
        temp3[countThree] = itThree.next();
        countThree++;
        temp3[countThree] = itThree.next();
        countThree++;
        assertTrue(arrayContains(temp3, e2));
        assertTrue(arrayContains(temp3, e5));
        assertTrue(arrayContains(temp3, e8));
        assertTrue(arrayContains(temp3, e9));
        
        Edge<Integer>[] temp4 = (Edge<Integer>[])(new Edge[4]);
        int countFour = 0;
        Iterator<Edge<Integer>> itFour = undirectedGraph.outgoingEdges(v4).iterator();
        assertTrue(itFour.hasNext());
        temp4[countFour] = itFour.next();
        countFour++;
        temp4[countFour] = itFour.next();
        countFour++;
        temp4[countFour] = itFour.next();
        countFour++;
        temp4[countFour] = itFour.next();
        countFour++;
        assertTrue(arrayContains(temp4, e3));
        assertTrue(arrayContains(temp4, e6));
        assertTrue(arrayContains(temp4, e8));
        assertTrue(arrayContains(temp4, e10));
        // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[4]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        
        // Testing here 2
        Edge<Integer>[] tempD2 = (Edge<Integer>[])(new Edge[3]);
        int countDTwo = 0;
        Iterator<Edge<Integer>> itDTwo = directedGraph.outgoingEdges(v2).iterator();
        assertTrue(itDTwo.hasNext());
        tempD2[countDTwo] = itDTwo.next();
        countDTwo++;
        tempD2[countDTwo] = itDTwo.next();
        countDTwo++;
        tempD2[countDTwo] = itDTwo.next();
        countDTwo++;
        assertTrue(arrayContains(tempD2, e5));
        assertTrue(arrayContains(tempD2, e7));
        assertTrue(arrayContains(tempD2, e6));
        
        
        // 3
        Edge<Integer>[] tempD3 = (Edge<Integer>[])(new Edge[2]);
        int countDThree = 0;
        Iterator<Edge<Integer>> itDThree = directedGraph.outgoingEdges(v3).iterator();
        assertTrue(itDThree.hasNext());
        tempD3[countDThree] = itDThree.next();
        countDThree++;
        tempD3[countDThree] = itDThree.next();
        countDThree++;

        assertTrue(arrayContains(tempD3, e8));
        assertTrue(arrayContains(tempD3, e9));
        
        // 4
        Edge<Integer>[] tempD4 = (Edge<Integer>[])(new Edge[1]);
        int countDFour = 0;
        Iterator<Edge<Integer>> itDFour = directedGraph.outgoingEdges(v4).iterator();
        assertTrue(itDFour.hasNext());
        tempD4[countDFour] = itDFour.next();
        countDFour++;

        assertTrue(arrayContains(tempD4, e10));
        
        // 5
        Edge<Integer>[] tempD5 = (Edge<Integer>[])(new Edge[4]);
        int countDFive = 0;
        Iterator<Edge<Integer>> itDFive = directedGraph.outgoingEdges(v5).iterator();
        tempD5[countDFive] = itDFive.next();
        countDFive++;
        
        assertTrue(arrayContains(tempD5, e11));
        // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Edge<Integer>[] tempD = (Edge<Integer>[])(new Edge[4]);
        int countD = 0;
        Iterator<Edge<Integer>> itD = directedGraph.outgoingEdges(v1).iterator();
        assertTrue(itD.hasNext());
        tempD[countD] = itD.next();
        countD++;
        tempD[countD] = itD.next();
        countD++;
        tempD[countD] = itD.next();
        countD++;
        tempD[countD] = itD.next();
        countD++;
        assertFalse(itD.hasNext());
        assertTrue(arrayContains(tempD, e1));
        assertTrue(arrayContains(tempD, e2));
        assertTrue(arrayContains(tempD, e3));
        assertTrue(arrayContains(tempD, e4));
    }
    
    // Helper method to check that an array contains a certain target.
    // This is helpful for testing graph ADT behaviors where an order
    // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
    private boolean arrayContains(Edge<Integer>[] temp, Edge<Integer> target) {
        for(Edge<Integer> e : temp) {
            if(e == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test the output of the incomingEdges(v) behavior
     */ 
    @Test
    public void testIncomingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // Testing here
        Iterator<Edge<Integer>> it = undirectedGraph.incomingEdges(v1).iterator();
        assertEquals(e1, it.next());
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e4, it.next());
        assertFalse(it.hasNext());
        
        // test with v2
        it = undirectedGraph.incomingEdges(v2).iterator();
        assertEquals(e1, it.next());
        assertEquals(e5, it.next());
        assertEquals(e6, it.next());
        assertEquals(e7, it.next());
        assertFalse(it.hasNext());
        
        // test with v3
        it = undirectedGraph.incomingEdges(v3).iterator();
        assertEquals(e2, it.next());
        assertEquals(e5, it.next());
        assertEquals(e8, it.next());
        assertEquals(e9, it.next());
        assertFalse(it.hasNext());
        
        // test with v4
        it = undirectedGraph.incomingEdges(v4).iterator();
        assertEquals(e3, it.next());
        assertEquals(e6, it.next());
        assertEquals(e8, it.next());
        assertEquals(e10, it.next());
        assertFalse(it.hasNext());
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        // Testing here
        Iterator<Edge<Integer>> itD = directedGraph.incomingEdges(v1).iterator();
        
        // test with v2
        itD = directedGraph.incomingEdges(v2).iterator();
        assertEquals(e1, itD.next());
        assertFalse(itD.hasNext());
        
        // test with v3
        itD = directedGraph.incomingEdges(v3).iterator();
        assertEquals(e2, itD.next());
        assertEquals(e5, itD.next());
        
        // test with v4
        itD = directedGraph.incomingEdges(v4).iterator();
        assertEquals(e3, itD.next());
        assertEquals(e6, itD.next());
        assertEquals(e8, itD.next());
        assertFalse(itD.hasNext());
        
        // test with v5
        itD = directedGraph.incomingEdges(v5).iterator();
        assertEquals(e4, itD.next());
        assertEquals(e7, itD.next());
        assertEquals(e9, itD.next());
        assertEquals(e10, itD.next());
        assertFalse(itD.hasNext());
        
        // test with v6
        itD = directedGraph.incomingEdges(v6).iterator();
        assertEquals(e11, itD.next());
        assertFalse(itD.hasNext());
    }

    /**
     * Test the output of the insertVertex(x) behavior
     */ 
    @Test
    public void testInsertVertex() {
        assertEquals(0, undirectedGraph.numVertices());
        Vertex<String> v1 = undirectedGraph.insertVertex("Fayetteville");
        assertEquals(1, undirectedGraph.numVertices());
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        assertEquals(v1, it.next());
        assertFalse(it.hasNext());      
    }

    /**
     * Test the output of the insertEdge(v1, v2, x) behavior
     */ 
    @Test
    public void testInsertEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        
        assertEquals(0, undirectedGraph.numEdges());
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
        assertEquals(1, undirectedGraph.numEdges());
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1, it.next());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the removeVertex(v) behavior
     */ 
    @Test
    public void testRemoveVertex() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        
        // Invalid remove
        Exception e = assertThrows(IllegalArgumentException.class, () -> undirectedGraph.removeVertex(null));
        assertEquals("Vertex is not a valid edge list vertex.", e.getMessage());
        
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertEquals(e1, it.next());
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e4, it.next());
        assertEquals(e5, it.next());
        assertEquals(e6, it.next());
        assertEquals(e7, it.next());
        assertEquals(e8, it.next());
        assertEquals(e9, it.next());
        assertEquals(e10, it.next());
        assertFalse(it.hasNext());
        assertEquals(5, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v5);
        assertEquals(4, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        
        // Testing here
        // after deleting v5, e4 e7 e9 and e10 should be gone
        it = undirectedGraph.edges().iterator();
        assertEquals(e1, it.next());
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e5, it.next());
        assertEquals(e6, it.next());
        assertEquals(e8, it.next());
        assertFalse(it.hasNext());
        
        // remove v2
        undirectedGraph.removeVertex(v2);
        assertEquals(3, undirectedGraph.numVertices());
        assertEquals(3, undirectedGraph.numEdges());
        // after deleting v5, e1 e6 e5 should be gone
        it = undirectedGraph.edges().iterator();
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e8, it.next());
        assertFalse(it.hasNext());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        Iterator<Edge<Integer>> itD = directedGraph.edges().iterator();
        assertEquals(e1, itD.next());
        assertEquals(e2, itD.next());
        assertEquals(e3, itD.next());
        assertEquals(e4, itD.next());
        assertEquals(e5, itD.next());
        assertEquals(e6, itD.next());
        assertEquals(e7, itD.next());
        assertEquals(e8, itD.next());
        assertEquals(e9, itD.next());
        assertEquals(e10, itD.next());
        assertEquals(e11, itD.next());
        assertFalse(itD.hasNext());
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(11, directedGraph.numEdges());
        directedGraph.removeVertex(v6);
        assertEquals(5, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        
        // test remove v1
        directedGraph.removeVertex(v1);
        assertEquals(4, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
        
        // e1, e2, e3, and e4 should have been removed
        itD = directedGraph.edges().iterator();
        assertEquals(e5, itD.next());
        assertEquals(e6, itD.next());
        assertEquals(e7, itD.next());
        assertEquals(e8, itD.next());
        assertEquals(e9, itD.next());
        assertEquals(e10, itD.next());
        assertFalse(itD.hasNext());
    }

    /**
     * Test the output of the removeEdge(e) behavior
     */ 
    @Test
    public void testRemoveEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e1);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(9, undirectedGraph.numEdges());
        
        // Invalid remove edge
        // Invalid remove
        Exception e = assertThrows(IllegalArgumentException.class, () -> undirectedGraph.removeEdge(null));
        assertEquals("Edge is not a valid graph edge.", e.getMessage());
        
        // Testing here
        undirectedGraph.removeEdge(e2);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(8, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e3);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(7, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e4);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e5);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(5, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e6);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(4, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e7);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(3, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e8);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(2, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e9);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(1, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e10);
        assertEquals(6, undirectedGraph.numVertices());
        undirectedGraph.removeVertex(v6);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        directedGraph.removeEdge(e1);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(9, directedGraph.numEdges());

        // Testing here
        directedGraph.removeEdge(e2);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(8, directedGraph.numEdges());
        directedGraph.removeEdge(e3);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(7, directedGraph.numEdges());
        directedGraph.removeEdge(e4);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
        directedGraph.removeEdge(e5);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(5, directedGraph.numEdges());
        directedGraph.removeEdge(e6);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(4, directedGraph.numEdges());
        directedGraph.removeEdge(e7);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(3, directedGraph.numEdges());
        directedGraph.removeEdge(e8);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(2, directedGraph.numEdges());
        directedGraph.removeEdge(e9);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(1, directedGraph.numEdges());
        directedGraph.removeEdge(e10);
        assertEquals(6, directedGraph.numVertices());
    }
}
