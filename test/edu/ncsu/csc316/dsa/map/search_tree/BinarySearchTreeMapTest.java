package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 *
 */
public class BinarySearchTreeMapTest {
	/** A map of integers and strings */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(3, "three");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(3, (int)tree.root().getElement().getKey());
        tree.put(2, "two");
        tree.put(1, "one");
        tree.put(4, "four");
        assertEquals(4, tree.size());
        
        // Check list contents
        assertEquals(3, (int)(tree.root().getElement().getKey()));
        assertEquals(4, (int)(tree.right(tree.root()).getElement().getKey()));
        assertEquals(2, (int)(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int)(tree.left(tree.left(tree.root()))).getElement().getKey());
        
        // Test iterator
        Iterator<Map.Entry<Integer, String>> it = tree.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("one", (String)(entry.getValue()));
        
        // Test toString()
        tree.toString();
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(3, "three");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(3, (int)tree.root().getElement().getKey());
        tree.put(2, "two");
        tree.put(1, "one");
        tree.put(4, "four");
        assertEquals(4, tree.size());
        
        // Check list contents
        assertEquals(3, (int)(tree.root().getElement().getKey()));
        assertEquals(4, (int)(tree.right(tree.root()).getElement().getKey()));
        assertEquals(2, (int)(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int)(tree.left(tree.left(tree.root()))).getElement().getKey());
        
        // Check get method
        assertEquals("three", tree.get(3));
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("four", tree.get(4));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(3, "three");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(3, (int)tree.root().getElement().getKey());
        tree.put(2, "two");
        tree.put(1, "one");
        tree.put(4, "four");
        assertEquals(4, tree.size());
        
        // Check list contents
        assertEquals(3, (int)(tree.root().getElement().getKey()));
        assertEquals(4, (int)(tree.right(tree.root()).getElement().getKey()));
        assertEquals(2, (int)(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int)(tree.left(tree.left(tree.root()))).getElement().getKey());
        
        // test removing 1 which should be the left child of the left child of the root
        tree.remove(1);
        assertEquals(3, tree.size());
        
        // check list contents
        assertEquals(3, (int)(tree.root().getElement().getKey()));
        assertEquals(4, (int)(tree.right(tree.root()).getElement().getKey()));
        assertEquals(2, (int)(tree.left(tree.root())).getElement().getKey());
        assertNull(tree.left(tree.left(tree.root())).getElement()); // check that 1 was removed
        
        // add an extra element to right child so we can check root removal
        tree.put(5, "five");
        assertEquals(4, tree.size());
        
        // try removal of right child
        tree.remove(5);
        assertEquals(3, tree.size());
        
        // check list contents
        assertEquals(3, (int)(tree.root().getElement().getKey()));
        assertEquals(4, (int)(tree.right(tree.root()).getElement().getKey()));
        assertEquals(2, (int)(tree.left(tree.root())).getElement().getKey());
        assertNull(tree.right(tree.right(tree.root())).getElement()); // check that 5 was removed
        
        // try root removal
        tree.remove(3);
        assertEquals(2, tree.size());
        
        // check list contents
        assertEquals(4, (int)(tree.root().getElement().getKey()));
        assertEquals(2, (int)(tree.left(tree.root()).getElement().getKey()));
    }
}

