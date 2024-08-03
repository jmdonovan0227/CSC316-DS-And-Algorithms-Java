package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 *
 */
public class AVLTreeMapTest {
	/** For testing with integer values */
    private BinarySearchTreeMap<Integer, String> tree;
    /** For testing with student objects */
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
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
        studentTree = new AVLTreeMap<Student, String>(new StudentGPAComparator());
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
    	// add to student tree
        studentTree.put(sOne, "one");
        studentTree.put(sTwo, "two");
        studentTree.put(sThree, "three");
        studentTree.put(sFour, "four");
        studentTree.put(sFive, "five");
        
        // add to integer tree
		tree.put(44, "forty-four");
        assertEquals("forty-four", tree.root().getElement().getValue());
        tree.put(17, "seventeen");
        tree.put(78, "seventy-eight");
        tree.put(32, "thirty-two");
        tree.put(50, "fifty");
        assertEquals(5, tree.size());
        
    	// Check positioning of all added nodes, basing test off of textbook tree (using same values for tree nodes)
        assertEquals(44, (int)tree.root().getElement().getKey()); // node 44
        assertEquals(17, (int)tree.left(tree.root()).getElement().getKey()); // node 17
        assertEquals(78, (int)tree.right(tree.root()).getElement().getKey()); // node 78
        assertEquals(32, (int)(tree.right(tree.left(tree.root())).getElement().getKey())); // node 32
        assertEquals(50, (int)(tree.left(tree.right(tree.root())).getElement().getKey())); // node 50
        
        // Add more nodes to initiate trinode restructuring
        tree.put(88, "eighty-eight"); // add 88
        tree.put(62, "sixty-two");
        tree.put(48, "forty-eight");
        tree.put(54, "fifty-four");
        
        // Check new size
        assertEquals(9, tree.size());
        
        // After trinode restructuing after putting 54 (like the book) the list will adjust
        // and 54 will now be in this position root -> right -> left -> right
        assertEquals(54, (int)(tree.right(tree.left(tree.right(tree.root()))).getElement().getKey())); // node 62
        
        // Check lists contents after trinode restructuring ->
        
        // Root
        assertEquals(44, (int)tree.root().getElement().getKey()); // node 44
        
        // Left subtree
        assertEquals(17, (int)tree.left(tree.root()).getElement().getKey()); // node 17
        assertEquals(32, (int)(tree.right(tree.left(tree.root())).getElement().getKey())); // node 32
        
        // Right subtree
        assertEquals(62, (int)tree.right(tree.root()).getElement().getKey()); // node 62
        assertEquals(50, (int)(tree.left(tree.right(tree.root())).getElement().getKey())); // node 50
        assertEquals(78, (int)(tree.right(tree.right(tree.root())).getElement().getKey())); // node 78
        assertEquals(88, (int)(tree.right(tree.right(tree.right(tree.root()))).getElement().getKey())); // node 88
        assertEquals(54, (int)(tree.right(tree.left(tree.right(tree.root()))).getElement().getKey())); // node 62
        assertEquals(48, (int)(tree.left(tree.left(tree.right(tree.root()))).getElement().getKey())); // node 62
        
        // Check contents of student tree
        assertEquals("two", studentTree.root().getElement().getValue());
        assertEquals("one", studentTree.right(studentTree.root()).getElement().getValue());
        assertEquals("four", studentTree.left(studentTree.root()).getElement().getValue());
        assertEquals("three", studentTree.right(studentTree.left(studentTree.root())).getElement().getValue());
        assertEquals("five", studentTree.left(studentTree.left(studentTree.root())).getElement().getValue());
        
        
        
        // Test compare method in AbstractOrderedMap
        assertEquals(0, tree.compare(44, 44));
        assertEquals(-1, tree.compare(44, 88));
        Exception exc = assertThrows(IllegalArgumentException.class, () -> tree.actionOnDelete(null));
        assertEquals("Position is not a valid linked binary tree node", exc.getMessage());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	// add to student tree
        studentTree.put(sOne, "one");
        studentTree.put(sTwo, "two");
        studentTree.put(sThree, "three");
        studentTree.put(sFour, "four");
        studentTree.put(sFive, "five");
        
        // add to integer tree
		tree.put(44, "forty-four");
        assertEquals("forty-four", tree.root().getElement().getValue());
        tree.put(17, "seventeen");
        tree.put(78, "seventy-eight");
        tree.put(32, "thirty-two");
        tree.put(50, "fifty");
        assertEquals(5, tree.size());
        
    	// Check positioning of all added nodes, basing test off of textbook tree (using same values for tree nodes)
        assertEquals(44, (int)tree.root().getElement().getKey()); // node 44
        assertEquals(17, (int)tree.left(tree.root()).getElement().getKey()); // node 17
        assertEquals(78, (int)tree.right(tree.root()).getElement().getKey()); // node 78
        assertEquals(32, (int)(tree.right(tree.left(tree.root())).getElement().getKey())); // node 32
        assertEquals(50, (int)(tree.left(tree.right(tree.root())).getElement().getKey())); // node 50
        
        // Add more nodes to initiate trinode restructuring
        tree.put(88, "eighty-eight"); // add 88
        tree.put(62, "sixty-two");
        tree.put(48, "forty-eight");
        tree.put(54, "fifty-four");
        
        assertFalse(tree.isEmpty());
        assertEquals("forty-four", tree.get(44));
        assertEquals("sixty-two", tree.get(62));
        assertEquals("one", studentTree.get(sOne));
        assertEquals("three", studentTree.get(sThree));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	// add to student tree
        studentTree.put(sOne, "one");
        studentTree.put(sTwo, "two");
        studentTree.put(sThree, "three");
        studentTree.put(sFour, "four");
        studentTree.put(sFive, "five");
        
        // add to integer tree
		tree.put(44, "forty-four");
        assertEquals("forty-four", tree.root().getElement().getValue());
        tree.put(17, "seventeen");
        tree.put(78, "seventy-eight");
        tree.put(32, "thirty-two");
        tree.put(50, "fifty");
        assertEquals(5, tree.size());
        
    	// Check positioning of all added nodes, basing test off of textbook tree (using same values for tree nodes)
        assertEquals(44, (int)tree.root().getElement().getKey()); // node 44
        assertEquals(17, (int)tree.left(tree.root()).getElement().getKey()); // node 17
        assertEquals(78, (int)tree.right(tree.root()).getElement().getKey()); // node 78
        assertEquals(32, (int)(tree.right(tree.left(tree.root())).getElement().getKey())); // node 32
        assertEquals(50, (int)(tree.left(tree.right(tree.root())).getElement().getKey())); // node 50
        
        // Add more nodes to initiate trinode restructuring
        tree.put(88, "eighty-eight"); // add 88
        tree.put(62, "sixty-two");
        tree.put(48, "forty-eight");
        tree.put(54, "fifty-four");
        
        // check list is not empty
        assertFalse(tree.isEmpty());
        
        // Root
        assertEquals(44, (int)tree.root().getElement().getKey()); // node 44
        
        // Left subtree
        assertEquals(17, (int)tree.left(tree.root()).getElement().getKey()); // node 17
        assertEquals(32, (int)(tree.right(tree.left(tree.root())).getElement().getKey())); // node 32
        
        // Right subtree
        assertEquals(62, (int)tree.right(tree.root()).getElement().getKey()); // node 62
        assertEquals(50, (int)(tree.left(tree.right(tree.root())).getElement().getKey())); // node 50
        assertEquals(78, (int)(tree.right(tree.right(tree.root())).getElement().getKey())); // node 78
        assertEquals(88, (int)(tree.right(tree.right(tree.right(tree.root()))).getElement().getKey())); // node 88
        assertEquals(54, (int)(tree.right(tree.left(tree.right(tree.root()))).getElement().getKey())); // node 54
        assertEquals(48, (int)(tree.left(tree.left(tree.right(tree.root()))).getElement().getKey())); // node 48
        
        // Check contents of student tree
        assertEquals("two", studentTree.root().getElement().getValue());
        assertEquals("one", studentTree.right(studentTree.root()).getElement().getValue());
        assertEquals("four", studentTree.left(studentTree.root()).getElement().getValue());
        assertEquals("three", studentTree.right(studentTree.left(studentTree.root())).getElement().getValue());
        assertEquals("five", studentTree.left(studentTree.left(studentTree.root())).getElement().getValue());
        
        // Remove an element from tree
        tree.remove(54);
        
        // Check updated list and size
        assertEquals(8, tree.size());
        
        // Root
        assertEquals(44, (int)tree.root().getElement().getKey()); // node 44
        
        // Left subtree
        assertEquals(17, (int)tree.left(tree.root()).getElement().getKey()); // node 17
        assertEquals(32, (int)(tree.right(tree.left(tree.root())).getElement().getKey())); // node 32
        
        // Right subtree
        assertEquals(62, (int)tree.right(tree.root()).getElement().getKey()); // node 62
        assertEquals(50, (int)(tree.left(tree.right(tree.root())).getElement().getKey())); // node 50
        assertEquals(78, (int)(tree.right(tree.right(tree.root())).getElement().getKey())); // node 78
        assertEquals(88, (int)(tree.right(tree.right(tree.right(tree.root()))).getElement().getKey())); // node 88
        assertNull(tree.right(tree.left(tree.right(tree.root()))).getElement()); // node 54 was deleted
        assertEquals(48, (int)(tree.left(tree.left(tree.right(tree.root()))).getElement().getKey())); // node 48

        // remove from student tree
        studentTree.remove(sOne);
        
        // Check size and list contents
        assertEquals(4, studentTree.size());
        
        // Check contents of student tree
        assertEquals("four", studentTree.root().getElement().getValue());
        assertEquals("two", studentTree.right(studentTree.root()).getElement().getValue());
        assertEquals("five", studentTree.left(studentTree.root()).getElement().getValue());
        assertNull(studentTree.right(studentTree.left(studentTree.root())).getElement());
        assertEquals("three", studentTree.left(studentTree.right(studentTree.root())).getElement().getValue());
    }
}
