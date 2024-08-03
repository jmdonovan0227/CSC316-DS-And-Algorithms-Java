package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapAdaptablePriorityQueueTest {
	/** An adaptable heap priority queue of Integers and Strings */
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        Exception e = assertThrows(IllegalArgumentException.class, () -> heap.replaceKey(null, null));
        assertEquals("Entry is not a valid adaptable priority queue entry.", e.getMessage());
        heap.replaceKey(e1, 0);
        assertEquals(0, (int)e1.getKey());
        heap.replaceKey(e2, 1);
        assertEquals(1, (int)e2.getKey());
        heap.replaceKey(e3, 2);
        assertEquals(2, (int)e3.getKey());
        heap.replaceKey(e0, -1);
        assertEquals(-1, (int)e0.getKey());
        heap.replaceKey(e4, 3);
        assertEquals(3, (int)e4.getKey());
        heap.replaceKey(e5, 4);
        assertEquals(4, (int)e5.getKey());
        heap.replaceKey(e6, 5);
        assertEquals(5, (int)e6.getKey());
        heap.replaceKey(e7, 6);
        assertEquals(6, (int)e7.getKey());
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("EIGHT",  e8.getValue());
        heap.replaceValue(e0, "New Zero");
        assertEquals("New Zero", heap.min().getValue());
        heap.replaceValue(e1, "New One");
        assertEquals("New One", e1.getValue());
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("New Zero", heap.min().getValue());
        heap.replaceValue(e2, "New Two");
        assertEquals("New Two", e2.getValue());
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("New Zero", heap.min().getValue());
        heap.replaceValue(e3, "New Three");
        assertEquals("New Three", e3.getValue());
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("New Zero", heap.min().getValue());
        heap.replaceValue(e4, "New Four");
        assertEquals("New Four", e4.getValue());
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("New Zero", heap.min().getValue());
        heap.replaceValue(e5, "New Five");
        assertEquals("New Five", e5.getValue());
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("New Zero", heap.min().getValue());
        heap.replaceValue(e6, "New Six");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("New Zero", heap.min().getValue());
        assertEquals("New Six", e6.getValue());
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("New Zero", heap.min().getValue());
        heap.replaceValue(e7, "New Seven");
        assertEquals("New Seven", e7.getValue());
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("New Zero", heap.min().getValue());
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(8, heap.size());
        heap.remove(e5);
        assertEquals(7, heap.size());
        Exception e = assertThrows(IllegalArgumentException.class, () -> heap.remove(e5));
        assertEquals("Invalid Adaptable PQ Entry.", e.getMessage());
        heap.remove(e1);
        assertEquals(6, heap.size());
        heap.remove(e2);
        assertEquals(5, heap.size());
        heap.remove(e3);
        assertEquals(4, heap.size());
        heap.remove(e4);
        assertEquals(3, heap.size());
        heap.remove(e6);
        assertEquals(2, heap.size());
        heap.remove(e7);
        assertEquals(1, heap.size());
        heap.remove(e8);
        assertTrue(heap.isEmpty());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        sHeap.insert(s1, "One");
        assertEquals(1, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        sHeap.insert(s2, "Two");
        assertEquals(2, sHeap.size());
        sHeap.insert(s3, "Three");
        assertEquals(3, sHeap.size());
        sHeap.insert(s4, "Four");
        assertEquals(4, sHeap.size());
        sHeap.insert(s5, "Five");
        assertEquals(5, sHeap.size());
        sHeap.remove(sHeap.min());
        assertEquals(4, sHeap.size());
        assertEquals(s2, sHeap.min().getKey());
        sHeap.replaceValue(sHeap.min(), "New Two");
        assertEquals("New Two", sHeap.min().getValue());
        sHeap.replaceKey(sHeap.min(), s3);
        assertEquals(s3, sHeap.min().getKey());
    }
}
