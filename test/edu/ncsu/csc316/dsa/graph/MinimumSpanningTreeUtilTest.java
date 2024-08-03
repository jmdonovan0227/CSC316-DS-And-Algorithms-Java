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
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Tests MinimumSpanningTreeUtil class methods
 * @author Jake Donovan
 *
 */
public class MinimumSpanningTreeUtilTest {
	/** An undirectedGraph of String and Highway which will be used for testing in this class */
	private EdgeListGraph<String, Highway> undirectedGraph;
	/** A directedGrah of String and Highway which will be used for testing in this class */
	private EdgeListGraph<String, Highway> directedGraph;

	/**
	 * Constructs directed and undirected graph for testing
	 * @throws java.lang.Exception if there is a problem when constructing directed or undirected graph
	 */
	@Before
	public void setUp() throws Exception {
		undirectedGraph = new EdgeListGraph<String, Highway>();
		directedGraph = new EdgeListGraph<String, Highway>();
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
	// Used in this class mainly to check that incorrect edges are not returned when calling MinimumSpanningTreeUtil
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
	 * Tests MinimumSpanningTreeUtil.kruskal()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testKruskal() {
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
        
        PositionalList<Edge<Highway>> m = MinimumSpanningTreeUtil.kruskal(undirectedGraph);
        Iterator<Edge<Highway>> it = m.iterator();
        
        // FOR TESTING WE ARE EXPECTING KRUSKAL TO TRACE A PATH THROUGH ALL VERTICES 
        Edge<Highway>[] temp = (Edge<Highway>[])(new Edge[4]);
        int count = 0;
        Edge<Highway> next = it.next();
        temp[count] = next;
        count++;
        assertEquals(5, next.getElement().getWeight()); // e1, distance = 5
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(10, next.getElement().getWeight()); // e2, distance = 10
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(15, next.getElement().getWeight()); // e3, distance = 15
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(20, next.getElement().getWeight()); // e4, distance = 20
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1)); // now we are at e8 -> find shortest route from v4 = to v5 -> (dist = 50) (e10)
        assertTrue(arrayContains(temp, e2)); // now we are at edge e2 -> we are starting at v3 then find shortest route -> to v4 (dist = 40) (e8)
        assertTrue(arrayContains(temp, e3)); // now we are at edge e1 -> we are starting at v1 then take the next shortest route -> to v3 (dist = 10) (e2)
        assertTrue(arrayContains(temp, e4)); // starting at v2 = Asheville take the shortest possible path -> to raleigh (v1) = edge e1 (dist = 5) (e1)
        
        // Start at v1 -> v2, v1 -> v3, v1 -> v4, v1 -> v5 (we have traced through all vertexes)
        // Check to make sure we don't have any correct paths in our array we created above (to test for incorrect paths from vertex v2)
        assertFalse(arrayContains(temp, e10));
        assertFalse(arrayContains(temp, e8));
        assertFalse(arrayContains(temp, e5));
        assertFalse(arrayContains(temp, e6));
        assertFalse(arrayContains(temp, e7));
        assertFalse(arrayContains(temp, e9));
        
        // DIRECTED EX (SAME IDEA HERE)
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
        
        m = MinimumSpanningTreeUtil.kruskal(directedGraph);
        it = m.iterator();
        
        temp = (Edge<Highway>[])(new Edge[5]);
        count = 0;
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(5, next.getElement().getWeight()); // e1, distance = 5
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(10, next.getElement().getWeight()); // e2, distance = 10
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(15, next.getElement().getWeight()); // e3, distance = `15
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(20, next.getElement().getWeight()); // e4, distance = 20
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(55, next.getElement().getWeight()); // e11, distance = 55
        assertFalse(it.hasNext());
        
        // v1 -> v2, v1 -> v3, v1 -> v4, v1 -> v5, v5 -> v6 (we have traced through all vertexes)
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        assertTrue(arrayContains(temp, e11));
        
        // Check to make sure we don't have any correct paths in our array we created above (to test for incorrect paths from vertex v2)
        assertFalse(arrayContains(temp, e5));
        assertFalse(arrayContains(temp, e6));
        assertFalse(arrayContains(temp, e7));
        assertFalse(arrayContains(temp, e8));
        assertFalse(arrayContains(temp, e9));
        assertFalse(arrayContains(temp, e10));
	}

	/**
	 * Tests MinimumSpanningTreeUtil.primJarnik()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testPrimJarnik() {
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
        
        PositionalList<Edge<Highway>> m = MinimumSpanningTreeUtil.primJarnik(undirectedGraph);
        Iterator<Edge<Highway>> it = m.iterator();
        
        // FOR TESTING WE ARE EXPECTING PRIMJARNIK TO TRACE A PATH THROUGH ALL VERTICES (similarly to kruskal)
        Edge<Highway>[] temp = (Edge<Highway>[])(new Edge[4]);
        int count = 0;
        Edge<Highway> next = it.next();
        temp[count] = next;
        count++;
        assertEquals(5, next.getElement().getWeight()); // e1, distance = 5
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(10, next.getElement().getWeight()); // e2, distance = 10
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(15, next.getElement().getWeight()); // e3, distance = 15
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(20, next.getElement().getWeight()); // e4, distance = 20
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1)); // now we are at e8 -> find shortest route from v4 = to v5 -> (dist = 50) (e10)
        assertTrue(arrayContains(temp, e2)); // now we are at edge e2 -> we are starting at v3 then find shortest route -> to v4 (dist = 40) (e8)
        assertTrue(arrayContains(temp, e3)); // now we are at edge e1 -> we are starting at v1 then take the next shortest route -> to v3 (dist = 10) (e2)
        assertTrue(arrayContains(temp, e4)); // starting at v2 = Asheville take the shortest possible path -> to raleigh (v1) = edge e1 (dist = 5) (e1)
        
        // Start at v1 -> v2, v1 -> v3, v1 -> v4, v1 -> v5 (we have traced through all vertexes)
        // Check to make sure we don't have any correct paths in our array we created above (to test for incorrect paths from vertex v2)
        assertFalse(arrayContains(temp, e10));
        assertFalse(arrayContains(temp, e8));
        assertFalse(arrayContains(temp, e5));
        assertFalse(arrayContains(temp, e6));
        assertFalse(arrayContains(temp, e7));
        assertFalse(arrayContains(temp, e9));
        
        // DIRECTED EX (SAME IDEA HERE)
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
        
        m = MinimumSpanningTreeUtil.primJarnik(directedGraph);
        it = m.iterator();
        
        temp = (Edge<Highway>[])(new Edge[5]);
        count = 0;
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(5, next.getElement().getWeight()); // e1, distance = 5
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(10, next.getElement().getWeight()); // e2, distance = 10
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(15, next.getElement().getWeight()); // e3, distance = `15
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(20, next.getElement().getWeight()); // e4, distance = 20
        next = it.next();
        temp[count] = next;
        count++;
        assertEquals(55, next.getElement().getWeight()); // e11, distance = 55
        assertFalse(it.hasNext());
        
        // v1 -> v2, v1 -> v3, v1 -> v4, v1 -> v5, v5 -> v6 (we have traced through all vertexes)
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        assertTrue(arrayContains(temp, e11));
        
        // Check to make sure we don't have any correct paths in our array we created above (to test for incorrect paths from vertex v2)
        assertFalse(arrayContains(temp, e5));
        assertFalse(arrayContains(temp, e6));
        assertFalse(arrayContains(temp, e7));
        assertFalse(arrayContains(temp, e8));
        assertFalse(arrayContains(temp, e9));
        assertFalse(arrayContains(temp, e10));
	}
}
