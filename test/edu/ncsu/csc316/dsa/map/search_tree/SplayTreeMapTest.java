package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 *
 */
public class SplayTreeMapTest {
	/** A map of integers and strings */
    private BinarySearchTreeMap<Integer, String> tree;
    /** A map of students and strings */
    private BinarySearchTreeMap<Student, String> studentTree;
    /** Student one used for testing */
    private Student sOne;
    /** Student two used for testing */
    private Student sTwo;
    /** Student three used for testing */
    private Student sThree;
    /** Student four used for testing */
    private Student sFour;
    /** Student five used for testing */
    private Student sFive;
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
        studentTree = new SplayTreeMap<Student, String>(new StudentGPAComparator());
        sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
    	sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
    	sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
    	sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
    	sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        studentTree.put(sOne, "one");
        studentTree.put(sTwo, "two");
        studentTree.put(sThree, "three");
        studentTree.put(sFour, "four");
        studentTree.put(sFive, "five");
        assertEquals(5, studentTree.size());
        
        // check contents
        assertEquals("five", studentTree.root().getElement().getValue());
        assertEquals("four", studentTree.right(studentTree.root()).getElement().getValue());
        assertEquals("three", studentTree.right(studentTree.right(studentTree.root())).getElement().getValue());
        assertEquals("two", studentTree.right(studentTree.right(studentTree.right(studentTree.root()))).getElement().getValue());
        assertEquals("one", studentTree.right(studentTree.right(studentTree.right(studentTree.right(studentTree.root())))).getElement().getValue());
        
        // add to tree
        tree.put(1, "one");
        assertEquals(1, tree.size());
        
        // check contents of list
        assertEquals("one", tree.root().getElement().getValue());
        
        // add 3 and trigger a zig step
        tree.put(3, "three");
        
        // check contents of list
        assertEquals("three", tree.root().getElement().getValue());
        assertEquals("one", tree.left(tree.root()).getElement().getValue());
        
        // insert 2 to trigger a zig zag
        tree.put(2, "two");
        assertEquals(3, tree.size());
        
        // check contents
        assertEquals("two", tree.root().getElement().getValue());
        assertEquals("one", tree.left(tree.root()).getElement().getValue());
        assertEquals("three", tree.right(tree.root()).getElement().getValue());
        
        // insert 4 to trigger a zig zig step
        tree.put(4,  "four");
        assertEquals(4, tree.size());
        
        // check contents
        assertEquals("four", tree.root().getElement().getValue());
        assertEquals("three", tree.left(tree.root()).getElement().getValue());
        assertEquals("two", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("one", tree.left(tree.left(tree.left(tree.root()))).getElement().getValue());
        
        //Exception exc = assertThrows(IllegalArgumentException.class, () -> tree.actionOnAccess(null));
        //assertEquals("Position is not a valid linked binary tree node", exc.getMessage());
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
        studentTree.put(sOne, "one");
        studentTree.put(sTwo, "two");
        studentTree.put(sThree, "three");
        studentTree.put(sFour, "four");
        studentTree.put(sFive, "five");
        assertEquals(5, studentTree.size());
        
        // check contents
        assertEquals("five", studentTree.root().getElement().getValue());
        assertEquals("four", studentTree.right(studentTree.root()).getElement().getValue());
        assertEquals("three", studentTree.right(studentTree.right(studentTree.root())).getElement().getValue());
        assertEquals("two", studentTree.right(studentTree.right(studentTree.right(studentTree.root()))).getElement().getValue());
        assertEquals("one", studentTree.right(studentTree.right(studentTree.right(studentTree.right(studentTree.root())))).getElement().getValue());
        
        // add to tree
        tree.put(1, "one");
        assertEquals(1, tree.size());
        
        // check contents of list
        assertEquals("one", tree.root().getElement().getValue());
        
        // add 3 and trigger a zig step
        tree.put(3, "three");
        
        // check contents of list
        assertEquals("three", tree.root().getElement().getValue());
        assertEquals("one", tree.left(tree.root()).getElement().getValue());
        
        // insert 2 to trigger a zig zag
        tree.put(2, "two");
        assertEquals(3, tree.size());
        
        // check contents
        assertEquals("two", tree.root().getElement().getValue());
        assertEquals("one", tree.left(tree.root()).getElement().getValue());
        assertEquals("three", tree.right(tree.root()).getElement().getValue());
        
        // check getter method
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("three", tree.get(3));
        
        // check getter method for student tree
        assertEquals("one", studentTree.get(sOne));
        assertEquals("two", studentTree.get(sTwo));
        assertEquals("three", studentTree.get(sThree));
        assertEquals("four", studentTree.get(sFour));
        assertEquals("five", studentTree.get(sFive));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        studentTree.put(sOne, "one");
        studentTree.put(sTwo, "two");
        studentTree.put(sThree, "three");
        studentTree.put(sFour, "four");
        studentTree.put(sFive, "five");
        assertEquals(5, studentTree.size());
        
        // check contents
        assertEquals("five", studentTree.root().getElement().getValue());
        assertEquals("four", studentTree.right(studentTree.root()).getElement().getValue());
        assertEquals("three", studentTree.right(studentTree.right(studentTree.root())).getElement().getValue());
        assertEquals("two", studentTree.right(studentTree.right(studentTree.right(studentTree.root()))).getElement().getValue());
        assertEquals("one", studentTree.right(studentTree.right(studentTree.right(studentTree.right(studentTree.root())))).getElement().getValue());
        
        // add to tree
        tree.put(1, "one");
        assertEquals(1, tree.size());
        
        // check contents of list
        assertEquals("one", tree.root().getElement().getValue());
        
        // add 3 and trigger a zig step
        tree.put(3, "three");
        
        // check contents of list
        assertEquals("three", tree.root().getElement().getValue());
        assertEquals("one", tree.left(tree.root()).getElement().getValue());
        
        // insert 2 to trigger a zig zag
        tree.put(2, "two");
        assertEquals(3, tree.size());
        
        // check contents
        assertEquals("two", tree.root().getElement().getValue());
        assertEquals("one", tree.left(tree.root()).getElement().getValue());
        assertEquals("three", tree.right(tree.root()).getElement().getValue());
        
        // insert 4 to trigger a zig zig step
        tree.put(4,  "four");
        assertEquals(4, tree.size());
        
        // check contents
        assertEquals("four", tree.root().getElement().getValue());
        assertEquals("three", tree.left(tree.root()).getElement().getValue());
        assertEquals("two", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("one", tree.left(tree.left(tree.left(tree.root()))).getElement().getValue());
        
        // try removing left child
        tree.remove(1);
        assertEquals(3, tree.size());
        
        // check contents
        assertEquals("two", tree.root().getElement().getValue());
        assertEquals("three", tree.right(tree.root()).getElement().getValue());
        assertEquals("four", tree.right(tree.right(tree.root())).getElement().getValue());
        
        // try removing right child
        tree.remove(3);
        assertEquals(2, tree.size());
        
        // check contents
        assertEquals("two", tree.root().getElement().getValue());
        assertEquals("four", tree.right(tree.root()).getElement().getValue());
        
        // try removing root node
        tree.remove(2);
        assertEquals(1, tree.size());
        
        // check contents
        assertEquals("four", tree.root().getElement().getValue());
    }
}
