package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 *
 */
public class LinearProbingHashMapTest {
	/** A map of Integer and Strings that will bed used to test LinearProbingHashMap class methods */
    private Map<Integer, String> map;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
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
        map = new LinearProbingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Tests non-testing constructor
     */
    @Test
    public void testNonTestingConstructors() {
    	Map<Integer, String> mapNonTest = new LinearProbingHashMap<Integer, String>(7, false);
    	assertTrue(mapNonTest.isEmpty());
    	mapNonTest = new LinearProbingHashMap<Integer, String>();
    	assertTrue(mapNonTest.isEmpty());
    	mapNonTest = new LinearProbingHashMap<Integer, String>(7);
    	assertTrue(mapNonTest.isEmpty());
    	map = new LinearProbingHashMap<Integer, String>(false);
    	assertTrue(mapNonTest.isEmpty());
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        assertEquals(6, map.size());
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey());
        assertEquals(1, (int)it.next().getKey());
        assertEquals(2, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
        
        map.remove(1);
        map.remove(2);
        
        // Test that values that should be in the same bucket are added correctly
        // for example 0 and 7 should go in bucket 1, lets test for that
        map.put(0, "String1");
        map.put(7, "String1");
        assertEquals(6, map.size());
        assertEquals(map.get(0).hashCode(), map.get(7).hashCode()); // a collision
        
        // Check that entries are ordered correctly
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey());
        assertEquals(0, (int)it.next().getKey());
        assertEquals(7, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());  
        
        // check when adding a duplicate key that the value is updated
        assertEquals("string6", map.get(6));
        map.put(6, "new string");
        assertEquals(6, map.size());
        assertEquals("new string", map.get(6));
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
        assertEquals(6, map.size());
        
        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
        assertEquals("string6", map.get(6));
        
        // null get test
        assertNull(map.get(10));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        
        assertTrue(map.isEmpty());
        
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        assertEquals(6, map.size());
        
        map.remove(1);
        assertEquals(5, map.size());
        assertNull(map.get(1));
        
        // null remove test
        assertNull(map.remove(0));
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	 assertNull(map.put(1, "string1"));
         assertNull(map.put(2, "string2"));
         assertNull(map.put(3, "string3"));
         assertNull(map.put(4, "string4"));
         assertNull(map.put(5, "string5"));
         assertNull(map.put(6, "string6"));
         assertEquals(6, map.size());
         
        // Construct iterator and check entries ordering
        Iterator<Integer> it = map.iterator();
        
        // Iterator should return the keys of the associated values in the associated order
        // when casted as an int
        assertEquals(6, (int)it.next());
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
      	assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        assertEquals(6, map.size());
         
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        
        // With iterator alone we were able to find matching key, lets tests for the keys here as well but
        // instead of just calling the iterator lets delegate to getKey() on each call and cast the result as an int
        // and make sure our keys our in the correct order
        assertEquals(6, (int)it.next().getKey());
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
        assertEquals(6, map.size());
        
        Iterator<String> it = map.values().iterator();
        
        // Now here lets test for the associated values by calling it.next() on each
        // next call to iterator since our iterator calls values() each call should return the value
        // connected the associated entry
        assertEquals("string6", it.next());
        assertEquals("string1", it.next());
        assertEquals("string2", it.next());
        assertEquals("string3", it.next());
        assertEquals("string4", it.next());
        assertEquals("string5", it.next());
    }
}
