/**
 * 
 */
package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests SinglyLinkedList class to make sure everything is functioning as intended
 * @author Jake Donovan
 *
 */
public class SinglyLinkedListTest {
	/** An abstract list of strings */
	private List<String> list;
	
	/**
	 * Construct list
	 * @throws java.lang.Exception if list cannot be constructed
	 */
	@Before
	public void setUp() throws Exception {
		list = new SinglyLinkedList<String>();
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.list.SinglyLinkedList#addLast(java.lang.Object)}.
	 */
	@Test
	public void testAddLast() {
		list.add(0, "one");
		list.add(1, "two");
		assertEquals(2, list.size());
		list.addLast("three");
		assertEquals(3, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));
		list.addFirst("newFirst");
		assertEquals(4, list.size());
		assertEquals("newFirst", list.first());
		assertEquals("one", list.get(1));
		assertEquals("two", list.get(2));
		assertEquals("three", list.last());
		list.addLast("newLast");
		assertEquals(5, list.size());
		assertEquals("newLast", list.last());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.list.SinglyLinkedList#last()}.
	 */
	@Test
	public void testLast() {
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		assertEquals(3, list.size());
		assertEquals("three", list.last());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.list.SinglyLinkedList#SinglyLinkedList()}.
	 */
	@Test
	public void testSinglyLinkedList() {
		list.add(0, "one");
		assertEquals(1, list.size());
		list = new SinglyLinkedList<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.list.SinglyLinkedList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAdd() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.add(0, "two"); // update front of list check that tail is still first value
        assertEquals(2, list.size()); // check size
        assertEquals("two", list.get(0)); // check new element is at front
        assertEquals("one", list.get(1)); // check that last element is at back
        list.add(2, "updateTail"); // check adding at the end of list that tail is updated and this value is at end of list
        assertEquals(3, list.size()); // check size
        assertEquals("two", list.get(0)); // check front
        assertEquals("one", list.get(1)); // check next element
        assertEquals("updateTail", list.get(2)); // check element is added to end
        list.add(1, "something");
        assertEquals(4, list.size());
        assertEquals("two", list.get(0));
        assertEquals("something", list.get(1));
        assertEquals("one", list.get(2));
        assertEquals("updateTail", list.get(3));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.list.SinglyLinkedList#get(int)}.
	 */
	@Test
	public void testGet() {
		list.add(0, "one");
		assertEquals(1, list.size());
		
		try {
			list.get(1);
		} catch(IndexOutOfBoundsException e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		assertEquals("one", list.last());
		list.add(0, "newFront");
		assertEquals(2, list.size());
		assertEquals("newFront", list.first());
		list.removeFirst();
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		list.removeLast();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.list.SinglyLinkedList#remove(int)}.
	 */
	@Test
	public void testRemove() {
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		assertEquals(5, list.size());
		
		list.remove(0);
		assertEquals(4, list.size());
		assertEquals("two", list.get(0));
		assertEquals("three", list.get(1));
		assertEquals("four", list.get(2));
		assertEquals("five", list.get(3));
		
		list.remove(3);
		assertEquals(3, list.size());
		assertEquals("two", list.get(0));
		assertEquals("three", list.get(1));
		assertEquals("four", list.get(2));
		
		list.remove(1);
		assertEquals(2, list.size());
		assertEquals("two", list.get(0));
		assertEquals("four", list.get(1));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.list.SinglyLinkedList#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSet() {
		list.add(0, "one");
		assertEquals(1, list.size());
		list.set(0, "newOne");
		assertEquals(1, list.size());
		assertEquals("newOne", list.get(0));
		
		list.add(0, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(0));
		assertEquals("newOne", list.get(1));
		list.set(1, "newTwo");
		assertEquals("two", list.get(0));
		assertEquals("newTwo", list.get(1));
		assertEquals(2, list.size());
		list.add(1, "place");
		assertEquals(3, list.size());
		list.set(1, "newPlace");
		assertEquals("two", list.get(0));
		assertEquals("newPlace", list.get(1));
		assertEquals("newTwo", list.get(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.list.SinglyLinkedList#size()}.
	 */
	@Test
	public void testSize() {
		list.add(0, "one");
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.list.SinglyLinkedList#iterator()}.
	 */
	@Test
	public void testIterator() {
		 // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //
        it.remove();
        assertEquals(0, list.size());
	}
}
