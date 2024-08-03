/**
 * 
 */
package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Tests ShortestPathUtil class methods
 * @author Jake Donovan
 *
 */
public class ShortestPathUtilTest {
	/** An undirectedGraph of String and Highway which will be used for testing in this class */
	private EdgeListGraph<String, Highway> undirectedGraph;
	/** A directedGrah of String and Highway which will be used for testing in this class */
	private EdgeListGraph<String, Highway> directedGraph;
	/** A graph that holds undirectedGraph shortest distances */
	private Map<Vertex<String>, Integer> distUndirected;
	/** A graph that holds directedGraph shortest distances */
	private Map<Vertex<String>, Integer> distDirected;
	

	/**
	 * Constructs directed and undirected graph for testing
	 * @throws java.lang.Exception if there is a problem when constructing directed and undirected graphs
	 */
	@Before
	public void setUp() throws Exception {
		undirectedGraph = new EdgeListGraph<String, Highway>();
		directedGraph = new EdgeListGraph<String, Highway>();
		distUndirected = new LinearProbingHashMap<Vertex<String>, Integer>();
		distDirected = new LinearProbingHashMap<Vertex<String>, Integer>();
	}
	
	/**
	 * Highway class which implements Weighted interface which will be used for testing in this class,
	 * this will be used as the type for Edges in this test class
	 * @author Jake Donovan
	 *
	 */
	public class Highway implements Weighted {
		/** The name of the highway object */
        private String name;
        /** The length (distance) of the highway object */
        private int length;
        
        /**
         * Constructs a highway object with a passed name n and a length l
         * @param n the name parameter for the highway object
         * @param l the length parameter for the highway object
         */
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }
        
        /**
         * Sets the name of the highway object with passed string name
         * @param name the new name for the highway object that will be set
         */
        public void setName(String name) {
            this.name = name;
        }
        
        /**
         * Gets length of the highway object (it's distance)
         * @return length the length (distance) of the highway object
         */
        public int getLength() {
            return length;
        }
        
        /**
         * Sets the length of the highway object
         * @param length the length of the highway object (distance) that will be set
         */
        public void setLength(int length) {
            this.length = length;
        }
        
        /**
         * Gets the name of the highway object
         * @return name the name of the highway object
         */
        public String getName() {
        	return name;
        }
        
        /**
         * Gets the weight of the highway object
         * @return Integer the weight of the highway object (distance)
         */
        @Override
        public int getWeight() {
            return getLength();
        }
    }
	
    // Helper method to check that an array contains a certain target.
    // This is helpful for testing graph ADT behaviors where an order
    // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
	// Used in this class mainly to check that incorrect edges are not returned when calling ShortestPathUtil
	// methods that are not expected to be returned
	/**
	 * Checks passed array to see if it contains a target element
	 * @param temp the passed array of edges of type highway
	 * @param target the target edge that we want to search for in the temp array
	 * @return True if the temp array contains the target element aka the edge
	 */
    private boolean arrayContains(Integer[] temp, int target) {
        for(Integer e : temp) {
            if(e == target) {
                return true;
            }
        }
        return false;
    }
	

	/**
	 * Tests ShortestPathUtil.dijkstra()
	 */
	@Test
	public void testDijkstra() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        // Using this to think about this when testing
        Highway h1 = new Highway("h1", 5); // this is highway 1, which connects
        // Raleigh to Asheville in a distance of 5
        
        // Highway 2 connects Raleigh and Wilmington in a distance 10
        Highway h2 = new Highway("h2", 10);
        
        // Highway 3 connects Raleigh and Durham in Distance 15
        Highway h3 = new Highway("h3", 15);
        
        // Highway 4 connects Raleigh to Greenville in a distance 20
        Highway h4 = new Highway("h4", 20);
        
        // Highway 5 connects Asheville and Wilmington in a distance 25
        Highway h5 = new Highway("h5", 25);
        
        // Highway 6 connects Asheville and Durham in a distance 30
        Highway h6 = new Highway("h6", 30);
        
        // Highway 7 connects Asheville and Greenville in a distance 35
        Highway h7 = new Highway("h7", 35);
        
        // Highway 8 connects Wilmington and Durham in a distance 40
        Highway h8 = new Highway("h8", 40);
        
        // Highway 9 connects Wilmington and Greenville in a distance 45
        Highway h9 = new Highway("h9", 45);
        
        // Highway 10 connects Durham and Greenville in a distance 50
        Highway h10 = new Highway("h10", 50);
        
        // Highway 11 connects Greenville and Boone in a distance 55
        Highway h11 = new Highway("h11", 55);
        
        // v1 = Raleigh, v2 = Asheville, v3 = Wilmington, v4 = Durham, v5 = Greenville
        Edge<Highway> e1 = undirectedGraph.insertEdge(v1, v2, h1); // h1
        Edge<Highway> e2 = undirectedGraph.insertEdge(v1, v3, h2); // h2
        Edge<Highway> e3 = undirectedGraph.insertEdge(v1, v4, h3); // h3
        Edge<Highway> e4 = undirectedGraph.insertEdge(v1, v5, h4); // h4
        Edge<Highway> e5 = undirectedGraph.insertEdge(v2, v3, h5); // h5
        Edge<Highway> e6 = undirectedGraph.insertEdge(v2, v4, h6); // h6
        Edge<Highway> e7 = undirectedGraph.insertEdge(v2, v5, h7); // h7
        Edge<Highway> e8 = undirectedGraph.insertEdge(v3, v4, h8); // h8
        Edge<Highway> e9 = undirectedGraph.insertEdge(v3, v5, h9); // h9
        Edge<Highway> e10 = undirectedGraph.insertEdge(v4, v5, h10); // h10
        // Changed how we are testing so we need to verify contents so that edges are being used
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
        
        Map<Vertex<String>, Integer> m = ShortestPathUtil.dijkstra(undirectedGraph, v2);
        Iterator<Entry<Vertex<String>, Integer>> it = m.entrySet().iterator();
        
        // diskstra creates a cloud that holds all vertices (shortest paths from one vertex to another)
        Integer[] temp = new Integer[5];
        int count = 0;
        Integer next = it.next().getValue();
        temp[count] = next;
        count++;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, 0)); // Asheville is the starting point with a distance of 0 to itself
        assertTrue(arrayContains(temp, 5));
        assertTrue(arrayContains(temp, 15)); 
        assertTrue(arrayContains(temp, 20));
        assertTrue(arrayContains(temp, 25)); 
        
        // DIRECTED EX
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        // v1 = Raleigh, v2 = Asheville, v3 = Wilmington, v4 = Durham, v5 = Greenville
        e1 = directedGraph.insertEdge(v1, v2, h1); // h1
        e2 = directedGraph.insertEdge(v1, v3, h2); // h2
        e3 = directedGraph.insertEdge(v1, v4, h3); // h3
        e4 = directedGraph.insertEdge(v1, v5, h4); // h4
        e5 = directedGraph.insertEdge(v2, v3, h5); // h5
        e6 = directedGraph.insertEdge(v2, v4, h6); // h6
        e7 = directedGraph.insertEdge(v2, v5, h7); // h7
        e8 = directedGraph.insertEdge(v3, v4, h8); // h8
        e9 = directedGraph.insertEdge(v3, v5, h9); // h9
        e10 = directedGraph.insertEdge(v4, v5, h10); // h10
        Edge<Highway> e11 = directedGraph.insertEdge(v5, v6, h11);
        // Changed how we are testing so we need to verify contents so that edges are being used
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
        
        
        m = ShortestPathUtil.dijkstra(directedGraph, v2);
        it = m.entrySet().iterator();
        
        // diskstra creates a cloud that holds all vertices (shortest paths from one vertex to another)
        temp = new Integer[6];
        count = 0;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, 0)); // Asheville is starting point with distance 0 to itself
        assertTrue(arrayContains(temp, 5));
        assertTrue(arrayContains(temp, 15));
        assertTrue(arrayContains(temp, 20));
        assertTrue(arrayContains(temp, 25));
        assertTrue(arrayContains(temp, 80));
	}

	/**
	 * Tests ShortestPathUtil.shortestPathTree()
	 */
	@Test
	public void testShortestPathTree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        // Using this to think about this when testing
        Highway h1 = new Highway("h1", 5); // this is highway 1, which connects
        // Raleigh to Asheville in a distance of 5
        
        // Highway 2 connects Raleigh and Wilmington in a distance 10
        Highway h2 = new Highway("h2", 10);
        
        // Highway 3 connects Raleigh and Durham in Distance 15
        Highway h3 = new Highway("h3", 15);
        
        // Highway 4 connects Raleigh to Greenville in a distance 20
        Highway h4 = new Highway("h4", 20);
        
        // Highway 5 connects Asheville and Wilmington in a distance 25
        Highway h5 = new Highway("h5", 25);
        
        // Highway 6 connects Asheville and Durham in a distance 30
        Highway h6 = new Highway("h6", 30);
        
        // Highway 7 connects Asheville and Greenville in a distance 35
        Highway h7 = new Highway("h7", 35);
        
        // Highway 8 connects Wilmington and Durham in a distance 40
        Highway h8 = new Highway("h8", 40);
        
        // Highway 9 connects Wilmington and Greenville in a distance 45
        Highway h9 = new Highway("h9", 45);
        
        // Highway 10 connects Durham and Greenville in a distance 50
        Highway h10 = new Highway("h10", 50);
        
        // Highway 11 connects Greenville and Boone in a distance 55
        Highway h11 = new Highway("h11", 55);
        
        // v1 = Raleigh, v2 = Asheville, v3 = Wilmington, v4 = Durham, v5 = Greenville
        Edge<Highway> e1 = undirectedGraph.insertEdge(v1, v2, h1); // h1
        Edge<Highway> e2 = undirectedGraph.insertEdge(v1, v3, h2); // h2
        Edge<Highway> e3 = undirectedGraph.insertEdge(v1, v4, h3); // h3
        Edge<Highway> e4 = undirectedGraph.insertEdge(v1, v5, h4); // h4
        Edge<Highway> e5 = undirectedGraph.insertEdge(v2, v3, h5); // h5
        Edge<Highway> e6 = undirectedGraph.insertEdge(v2, v4, h6); // h6
        Edge<Highway> e7 = undirectedGraph.insertEdge(v2, v5, h7); // h7
        Edge<Highway> e8 = undirectedGraph.insertEdge(v3, v4, h8); // h8
        Edge<Highway> e9 = undirectedGraph.insertEdge(v3, v5, h9); // h9
        Edge<Highway> e10 = undirectedGraph.insertEdge(v4, v5, h10); // h10
        // Changed how we are testing so we need to verify contents so that edges are being used
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
        
        // getShortestDistances
        distUndirected = ShortestPathUtil.dijkstra(undirectedGraph, v1);
        
        // call shortestpath tree
        Map<Vertex<String>, Edge<Highway>> m = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, distUndirected);
        assertEquals(e1, m.get(v2));
        assertEquals(e2, m.get(v3));
        assertEquals(e3, m.get(v4));
        assertEquals(e4, m.get(v5));
        
        // DIRECTED EX
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        // v1 = Raleigh, v2 = Asheville, v3 = Wilmington, v4 = Durham, v5 = Greenville
        e1 = directedGraph.insertEdge(v1, v2, h1); // h1
        e2 = directedGraph.insertEdge(v1, v3, h2); // h2
        e3 = directedGraph.insertEdge(v1, v4, h3); // h3
        e4 = directedGraph.insertEdge(v1, v5, h4); // h4
        e5 = directedGraph.insertEdge(v2, v3, h5); // h5
        e6 = directedGraph.insertEdge(v2, v4, h6); // h6
        e7 = directedGraph.insertEdge(v2, v5, h7); // h7
        e8 = directedGraph.insertEdge(v3, v4, h8); // h8
        e9 = directedGraph.insertEdge(v3, v5, h9); // h9
        e10 = directedGraph.insertEdge(v4, v5, h10); // h10
        Edge<Highway> e11 = directedGraph.insertEdge(v5, v6, h11);
        // Changed how we are testing so we need to verify contents so that edges are being used
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
        
        // get map of shortest paths for directed starting at v1
        distDirected = ShortestPathUtil.dijkstra(directedGraph, v1);
        
        // pass to ShortestPathUtil.shortestPathTree()
        m = ShortestPathUtil.shortestPathTree(directedGraph, v1, distDirected);
        assertEquals(e1, m.get(v2));
        assertEquals(e2, m.get(v3));
        assertEquals(e3, m.get(v4));
        assertEquals(e4, m.get(v5));
        assertEquals(e11, m.get(v6));
	}
}
