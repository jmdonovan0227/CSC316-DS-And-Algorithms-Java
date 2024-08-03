package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a separate chaining hash map data structure 
 *
 * @author Dr. King
 *
 */
public class SeparateChainingHashMapTest {
	/** A map of Integers and Strings that will be used to test SeparateChainingHashMap class methods */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of a separate chaining hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are TESTING.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        // Remember that our secondary map (an AVL tree) is a search
        // tree, which means the entries should be sorted in order within
        // that tree
        map = new SeparateChainingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Tests non-testing constructor
     */
    @Test
    public void testNonTestingConstructors() {
    	Map<Integer, String> mapNonTest = new SeparateChainingHashMap<Integer, String>(7, false);
    	assertTrue(mapNonTest.isEmpty());
    	mapNonTest = new SeparateChainingHashMap<Integer, String>();
    	assertTrue(mapNonTest.isEmpty());
    	mapNonTest = new SeparateChainingHashMap<Integer, String>(7);
    	assertTrue(mapNonTest.isEmpty());
    	mapNonTest = new SeparateChainingHashMap<Integer, String>(false);
    	assertTrue(mapNonTest.isEmpty());
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        // Should both be in bucket 1
        map.put(0, "String0");
        map.put(7, "String0");
        assertEquals(4, map.size());
        
        it = map.entrySet().iterator();
        assertEquals(0, (int)it.next().getKey());
        assertEquals(7, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        
        // You should create some collisions to check that entries
        // are placed in the correct buckets
        map.put(1, "String1");
        map.put(8, "String1");
        
        assertEquals(map.get(1).hashCode(), map.get(8).hashCode());
        it = map.entrySet().iterator(); // check ordering after collisions
        assertEquals(0, (int)it.next().getKey());
        assertEquals(7, (int)it.next().getKey());
        assertEquals(1, (int)it.next().getKey());
        assertEquals(8, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        //assertNull(map.put(7, "string7"));
        
        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
        assertEquals("string6", map.get(6));
        //assertEquals("string7", map.get(7));
        assertEquals(6, map.size());
        assertNull(map.get(0)); // null bucket test, should go in bucket 1 but we did not add a value that would create that bucket so trying to get this value
        // should return null
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string3"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        //assertNull(map.put(7, "string7"));
        assertEquals(6, map.size());
        
        map.remove(1); // remove 1
        assertEquals(5, map.size()); // size should now be 6
        assertNull(map.get(1)); // check 1 was removed, should return null as the entry should not exist within map after removal
        assertNull(map.remove(0)); // null bucket test
        assertEquals(5, map.size());
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */   
    @Test
    public void testIterator() {
    	assertTrue(map.isEmpty());
    	
    	assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertEquals(7, map.size());
        
        Iterator<Integer> it = map.iterator();
        
        // This entry will return the key of the connected TableEntry by calling
        // next() so we want to check keys by casting each next() as an int with this
        // iterator
        assertEquals(6, (int)it.next());
        assertEquals(7, (int)it.next());
        assertEquals(1, (int)it.next());
        assertEquals(2, (int)it.next());
        assertEquals(3, (int)it.next());
        assertEquals(4, (int)it.next());
        assertEquals(5, (int)it.next());
    }
    
    /**
     * Test the output of the entrySet() behavior
     */   
    @Test
    public void testEntrySet() {
    	assertTrue(map.isEmpty());
    	
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertEquals(7, map.size());
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        
        // Here we want to call getKey() or getValue(), here we are going to cast the result
        // of it.next().getKey() as an int so we can check that keys are in the correct order since
        // this iterator calls entrySet()
        
        assertEquals(6, (int)it.next().getKey());
        assertEquals(7, (int)it.next().getKey());
        assertEquals(1, (int)it.next().getKey());
        assertEquals(2, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the values() behavior
     */   
    @Test
    public void testValues() {
    	assertTrue(map.isEmpty());
    	
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertEquals(7, map.size());
        
        Iterator<String> it = map.values().iterator();
        
        // With this iterator each call to next() will return the value of the associated
        // TableEntry, so we check that the values are in the correct with each call to the iterator
        // no need to cast here
        
        assertEquals("string6", it.next());
        assertEquals("string7", it.next());
        assertEquals("string1", it.next());
        assertEquals("string2", it.next());
        assertEquals("string3", it.next());
        assertEquals("string4", it.next());
        assertEquals("string5", it.next());
    }
}
