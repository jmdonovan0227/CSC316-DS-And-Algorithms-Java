package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 *
 */
public class PositionalLinkedListTest {
	/** Constructs a positional list of strings that will be used for testing */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        list.remove(first);
        assertEquals(0, list.size());
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
        Position<String> first = list.addFirst("first");
        Position<String> last = list.addLast("last");
        assertEquals(2, list.size());
        assertEquals(first, list.first());
        assertEquals(last, list.last());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(first, list.first());
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("first");
        assertEquals(1, list.size());
        Position<String> second = list.addLast("second");
        assertEquals(2, list.size());
        assertEquals(first, list.first());
        assertEquals(second, list.last());
        assertEquals("first", first.getElement());
        assertEquals("second", second.getElement());
        Position<String> inBetween = list.addBefore(second, "between");
        assertEquals(3, list.size());
        assertEquals(inBetween, list.before(second));
        assertEquals("between", list.after(first).getElement());
        Position<String> after = list.addAfter(second, "after");
        assertEquals(4, list.size());
        assertEquals(after, list.after(second));
        assertEquals("after", list.after(second).getElement());
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
    	Position<String> first = list.addFirst("first");
    	Position<String> last = list.addLast("last");
    	assertEquals(2, list.size());
    	assertEquals(first, list.before(last));
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
    	Position<String> first = list.addFirst("first");
    	Position<String> last = list.addLast("last");
    	assertEquals(2, list.size());
    	assertEquals(last, list.after(first));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
    	Position<String> first = list.addFirst("first");
    	assertEquals(1, list.size());
    	list.addBefore(first, "new");
    	assertEquals("new", list.before(first).getElement());
    	assertEquals(2, list.size());
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	Position<String> first = list.addFirst("first");
    	assertEquals(1, list.size());
    	list.addAfter(first, "newEnd");
    	assertEquals(2, list.size());
    	assertEquals("newEnd", list.after(first).getElement());
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        list.set(first, "first");
        assertEquals(1, list.size());
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	Position<String> first = list.addFirst("first");
    	Position<String> last = list.addLast("last");
    	assertEquals(2, list.size());
    	list.remove(last);
    	assertEquals(1, list.size());
    	assertEquals(first, list.first());
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {
       Position<String> first = list.addFirst("one");
       assertEquals(1, list.size());
       assertEquals(first, list.first());
       Position<String> second = list.addLast("second");
       assertEquals(2, list.size());
       assertEquals(second, list.last());
       
       Iterator<String> it = list.iterator();
       assertTrue(it.hasNext());
       assertEquals("one", it.next());
       it.remove();
       assertEquals(1, list.size());
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        it.remove();
        assertEquals(2, list.size());
        
        try {
        	it.remove();
        } catch(IllegalStateException e) {
        	assertTrue(e instanceof IllegalStateException);
        }
    }
}