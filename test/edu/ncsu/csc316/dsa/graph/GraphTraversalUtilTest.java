/**
 * 
 */
package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests GraphTraversalUtil class methods
 * @author Jake Donovan
 *
 */
public class GraphTraversalUtilTest {
	/** An undirected graph of String and Highway which will be used for testing in this class */
	private AdjacencyListGraph<String, Highway> undirectedGraph;
	/** A directed graph of String and Highway which will be used for testing in this class */
	private AdjacencyListGraph<String, Highway> directedGraph;

	/**
	 * Constructs directedGraph and undirectedGraph of String and highways of type AdjacencyListGraph
	 * @throws java.lang.Exception an exception indicating something went wrong when constructing the undirected or directed graph
	 */
	@Before
	public void setUp() throws Exception {
		undirectedGraph = new AdjacencyListGraph<String, Highway>();
		directedGraph = new AdjacencyListGraph<String, Highway>(true);
		//buildUndirectedSample();
		// REMEMBER WE CAN ACCESS THIS WAY
		//GraphTraversalUtil.depthFirstSearch(test, null);
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
	// Used in this class mainly to check that incorrect edges are not returned when calling GraphTraversalUtil
	// methods that are not expected to be returned
	/**
	 * Checks passed array to see if it contains a target element
	 * @param temp the passed array of edges of type highway
	 * @param target the target edge that we want to search for in the temp array
	 * @return True if the temp array contains the target element aka the edge
	 */
    private boolean arrayContains(Edge<Highway>[] temp, Edge<Highway> target) {
        for(Edge<Highway> e : temp) {
            if(e == target) {
                return true;
            }
        }
        return false;
    }

	/**
	 * Tests GraphTraversalUtil.depthFirstSearch()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testDepthFirstSearch() {
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
        Map<Vertex<String>, Edge<Highway>> m = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v2);
        Iterator<Entry<Vertex<String>, Edge<Highway>>> it = m.entrySet().iterator();
        // First entry should be largest and last entry should have the shortest path
        Edge<Highway>[] temp = (Edge<Highway>[])(new Edge[4]);
        int count = 0;
        Edge<Highway> next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(50, next.getElement().getWeight()); // e10, distance = 50
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(40, next.getElement().getWeight()); // e8, distance = 40
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(10, next.getElement().getWeight()); // e2, distance = 10
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(5, next.getElement().getWeight()); // e1, distance = 5
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e10)); // now we are at e8 -> find shortest route from v4 = to v5 -> (dist = 50) (e10)
        assertTrue(arrayContains(temp, e8)); // now we are at edge e2 -> we are starting at v3 then find shortest route -> to v4 (dist = 40) (e8)
        assertTrue(arrayContains(temp, e2)); // now we are at edge e1 -> we are starting at v1 then take the next shortest route -> to v3 (dist = 10) (e2)
        assertTrue(arrayContains(temp, e1)); // starting at v2 = Asheville take the shortest possible path -> to raleigh (v1) = edge e1 (dist = 5) (e1)
        
        // Check to make sure we don't have any correct paths in our array we created above (to test for incorrect paths from vertex v2)
        assertFalse(arrayContains(temp, e3));
        assertFalse(arrayContains(temp, e4));
        assertFalse(arrayContains(temp, e5));
        assertFalse(arrayContains(temp, e6));
        assertFalse(arrayContains(temp, e7));
        assertFalse(arrayContains(temp, e9));
        
        
        // DIRECTED EX (NOTE WORKS SIMILARLY BUT IN DIRECTED YOU CANNOT BACKTRACK)
        // EX = if you start at v1 you must start here and move to a destination (ex = (v1, v2, 5), you cannot go backwards if you were at v2 you could not go back to v1 to get dist = 5)
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        e1 = directedGraph.insertEdge(v1, v2, h1);
        e2 = directedGraph.insertEdge(v1, v3, h2);
        e3 = directedGraph.insertEdge(v1, v4, h3);
        e4 = directedGraph.insertEdge(v1, v5, h4);
        e5 = directedGraph.insertEdge(v2, v3, h5);
        e6 = directedGraph.insertEdge(v2, v4, h6);
        e7 = directedGraph.insertEdge(v2, v5, h7);
        e8 = directedGraph.insertEdge(v3, v4, h8);
        e9 = directedGraph.insertEdge(v3, v5, h9);
        e10 = directedGraph.insertEdge(v4, v5, h10);
        Edge<Highway> e11 = directedGraph.insertEdge(v5, v6, h11);
        
        m = GraphTraversalUtil.depthFirstSearch(directedGraph, v2);
        it = m.entrySet().iterator();
        
        temp = (Edge<Highway>[])(new Edge[4]);
        count = 0;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(55, next.getElement().getWeight()); // e11, distance = 55
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(50, next.getElement().getWeight()); // e10, distance = 50
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(40, next.getElement().getWeight()); // e8, distance = 40
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(25, next.getElement().getWeight()); // e5, distance = 25
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e10));
        assertTrue(arrayContains(temp, e8));
        assertTrue(arrayContains(temp, e11));
        assertTrue(arrayContains(temp, e5)); 
        
        // Check to make sure we don't have any correct paths in our array we created above (to test for incorrect paths from vertex v2)
        assertFalse(arrayContains(temp, e3));
        assertFalse(arrayContains(temp, e4));
        assertFalse(arrayContains(temp, e1));
        assertFalse(arrayContains(temp, e6));
        assertFalse(arrayContains(temp, e7));
        assertFalse(arrayContains(temp, e9));
        assertFalse(arrayContains(temp, e2));
	}

	/**
	 * Tests GraphTraversalUtil.breadthFirstSearch()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testBreadthFirstSearch() {
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
        Map<Vertex<String>, Edge<Highway>> m = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v2);
        Iterator<Entry<Vertex<String>, Edge<Highway>>> it = m.entrySet().iterator();
        // First entry should be largest and last entry should have the shortest path
        Edge<Highway>[] temp = (Edge<Highway>[])(new Edge[4]);
        int count = 0;
        Edge<Highway> next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(35, next.getElement().getWeight()); // e7, distance = 35
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(30, next.getElement().getWeight()); // e6, distance = 30
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(25, next.getElement().getWeight()); // e5, distance = 25
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(5, next.getElement().getWeight()); // e1, distance = 5
        assertFalse(it.hasNext());
        
        // For breadth first search undirected we visit every edge that is connected to starting vertex
        assertTrue(arrayContains(temp, e5));
        assertTrue(arrayContains(temp, e6)); 
        assertTrue(arrayContains(temp, e7)); 
        assertTrue(arrayContains(temp, e1)); 
        
        // Check to make sure we don't have any correct paths in our array we created above (to test for incorrect paths from vertex v2)
        assertFalse(arrayContains(temp, e3));
        assertFalse(arrayContains(temp, e4));
        assertFalse(arrayContains(temp, e2));
        assertFalse(arrayContains(temp, e10));
        assertFalse(arrayContains(temp, e8));
        assertFalse(arrayContains(temp, e9));
        
        
        // DIRECTED EX similar to undirected but will take a path that does not
        // start at starting vertex to visit all vertices say if there isn't a vertex v2 -> v6, if
        // it has as vertex v2 -> v5 it can visit v5 -> v6 to cover vertex v6
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        e1 = directedGraph.insertEdge(v1, v2, h1);
        e2 = directedGraph.insertEdge(v1, v3, h2);
        e3 = directedGraph.insertEdge(v1, v4, h3);
        e4 = directedGraph.insertEdge(v1, v5, h4);
        e5 = directedGraph.insertEdge(v2, v3, h5);
        e6 = directedGraph.insertEdge(v2, v4, h6);
        e7 = directedGraph.insertEdge(v2, v5, h7);
        e8 = directedGraph.insertEdge(v3, v4, h8);
        e9 = directedGraph.insertEdge(v3, v5, h9);
        e10 = directedGraph.insertEdge(v4, v5, h10);
        Edge<Highway> e11 = directedGraph.insertEdge(v5, v6, h11);
        
        m = GraphTraversalUtil.breadthFirstSearch(directedGraph, v2);
        it = m.entrySet().iterator();
        
        temp = (Edge<Highway>[])(new Edge[4]);
        count = 0;
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(55, next.getElement().getWeight()); // e11, distance = 55
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(35, next.getElement().getWeight()); // e7, distance = 35
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(30, next.getElement().getWeight()); // e6, distance = 30
        next = it.next().getValue();
        temp[count] = next;
        count++;
        assertEquals(25, next.getElement().getWeight()); // e5, distance = 25
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e7));
        assertTrue(arrayContains(temp, e11));
        assertTrue(arrayContains(temp, e5)); 
        
        // Check to make sure we don't have any correct paths in our array we created above (to test for incorrect paths from vertex v2)
        assertFalse(arrayContains(temp, e3));
        assertFalse(arrayContains(temp, e4));
        assertFalse(arrayContains(temp, e1));
        assertFalse(arrayContains(temp, e8));
        assertFalse(arrayContains(temp, e10));
        assertFalse(arrayContains(temp, e9));
        assertFalse(arrayContains(temp, e2));
	}
}