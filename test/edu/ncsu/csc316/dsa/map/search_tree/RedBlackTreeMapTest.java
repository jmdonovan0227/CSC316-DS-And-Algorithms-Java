package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 *
 */
public class RedBlackTreeMapTest {
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
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
        studentTree = new RedBlackTreeMap<Student, String>(new StudentGPAComparator());
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
        
        // add first node
        tree.put(4, "four");
        assertEquals(1, tree.size());
        
        // add second node
        tree.put(7, "seven");
        assertEquals(2, tree.size());
        
        // add a 3rd node this should trigger a double red
        tree.put(12, "twelve");
        assertEquals(3, tree.size());
        
        // check contents
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("twelve", tree.right(tree.root()).getElement().getValue());
        
        // add 15 which should trigger which should trigger a double red
        tree.put(15, "fifteen");
        assertEquals(4, tree.size());
        
        // check contents
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("twelve", tree.right(tree.root()).getElement().getValue());
        assertEquals("fifteen", tree.right(tree.right(tree.root())).getElement().getValue());
        
        // is red = 1, is black = 0
        
        // root is black = 7
        assertEquals(0, tree.getProperty(tree.root()));
        // left child of root = 4 = black
        assertEquals(0, tree.getProperty(tree.left(tree.root())));
        // right child of root = 12 = black
        assertEquals(0, tree.getProperty(tree.right(tree.root())));
        // right child of right child of root = 15 = red
        assertEquals(1, tree.getProperty(tree.right(tree.right(tree.root()))));
        
        // add 3
        tree.put(3, "three");
        assertEquals(5, tree.size());
        
        // check contents of list
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("twelve", tree.right(tree.root()).getElement().getValue());
        assertEquals("fifteen", tree.right(tree.right(tree.root())).getElement().getValue());
        assertEquals("three", tree.left(tree.left(tree.root())).getElement().getValue());
        
        // add 5
        tree.put(5, "five");
        assertEquals(6, tree.size());
        
        // check contents
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("twelve", tree.right(tree.root()).getElement().getValue());
        assertEquals("fifteen", tree.right(tree.right(tree.root())).getElement().getValue());
        assertEquals("three", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("five", tree.right(tree.left(tree.root())).getElement().getValue());
        
        // add 14 which should trigger a double red condition
        tree.put(14, "fourteen");
        assertEquals(7, tree.size());
        
        // check contents of list and colors of nodes
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("fourteen", tree.right(tree.root()).getElement().getValue());
        assertEquals("fifteen", tree.right(tree.right(tree.root())).getElement().getValue());
        assertEquals("three", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("five", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals("twelve", tree.left(tree.right(tree.root())).getElement().getValue());
        
        // check node colors red = 1, black = 0
        // root is black = 7
        assertEquals(0, tree.getProperty(tree.root()));
        // left child of root = 4 = black
        assertEquals(0, tree.getProperty(tree.left(tree.root())));
        // right child of root = 14 = black
        assertEquals(0, tree.getProperty(tree.right(tree.root())));
        // right child of right child of root = 15 = red
        assertEquals(1, tree.getProperty(tree.right(tree.right(tree.root()))));
        // left child of right child of root = 12 = red
        assertEquals(1, tree.getProperty(tree.left(tree.right(tree.root()))));
        // left child of left child of root = 3 = red
        assertEquals(1, tree.getProperty(tree.left(tree.left(tree.root()))));
        // right child of left child of root = 5 = red
        assertEquals(1, tree.getProperty(tree.right(tree.left(tree.root()))));
        
        // add 18 which should cause a double red condition
        tree.put(18, "eighteen");
        assertEquals(8, tree.size());
        
        // check contents of list and red and black nodes
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("fourteen", tree.right(tree.root()).getElement().getValue());
        assertEquals("fifteen", tree.right(tree.right(tree.root())).getElement().getValue());
        assertEquals("three", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("five", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals("twelve", tree.left(tree.right(tree.root())).getElement().getValue());
        assertEquals("eighteen", tree.right(tree.right(tree.right(tree.root()))).getElement().getValue());
        
        // check for red and black nodes
        // check node colors red = 1, black = 0
        // root is black = 7
        assertEquals(0, tree.getProperty(tree.root()));
        // left child of root = 4 = black
        assertEquals(0, tree.getProperty(tree.left(tree.root())));
        // right child of root = 14 = black
        assertEquals(1, tree.getProperty(tree.right(tree.root())));
        // right child of right child of root = 15 = red
        assertEquals(0, tree.getProperty(tree.right(tree.right(tree.root()))));
        // left child of right child of root = 12 = red
        assertEquals(0, tree.getProperty(tree.left(tree.right(tree.root()))));
        // left child of left child of root = 3 = red
        assertEquals(1, tree.getProperty(tree.left(tree.left(tree.root()))));
        // right child of left child of root = 5 = red
        assertEquals(1, tree.getProperty(tree.right(tree.left(tree.root()))));
        // right child, right child, right child of root = 18 = red = 1
        assertEquals(1, tree.getProperty(tree.right(tree.right(tree.right(tree.root())))));
        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        // add first node
        tree.put(4, "four");
        assertEquals(1, tree.size());
        
        // add second node
        tree.put(7, "seven");
        assertEquals(2, tree.size());
        
        // add a 3rd node this should trigger a double red
        tree.put(12, "twelve");
        assertEquals(3, tree.size());
        
        // check contents
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("twelve", tree.right(tree.root()).getElement().getValue());
        
        // add 15 which should trigger which should trigger a double red
        tree.put(15, "fifteen");
        assertEquals(4, tree.size());
        
        // check contents
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("twelve", tree.right(tree.root()).getElement().getValue());
        assertEquals("fifteen", tree.right(tree.right(tree.root())).getElement().getValue());
        
        // check getter method
        assertEquals("seven", tree.get(7));
        assertEquals("four", tree.get(4));
        assertEquals("twelve", tree.get(12));
        assertEquals("fifteen", tree.get(15));
        
       // Check for exception
       Exception exc = assertThrows(IllegalArgumentException.class, () -> tree.actionOnDelete(null));
       assertEquals("Position is not a valid linked binary tree node", exc.getMessage());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        // add first node
        tree.put(4, "four");
        assertEquals(1, tree.size());
        
        // add second node
        tree.put(7, "seven");
        assertEquals(2, tree.size());
        
        // add a 3rd node this should trigger a double red
        tree.put(12, "twelve");
        assertEquals(3, tree.size());
        
        // check contents
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("twelve", tree.right(tree.root()).getElement().getValue());
        
        // add 15 which should trigger which should trigger a double red
        tree.put(15, "fifteen");
        assertEquals(4, tree.size());
        
        // check contents
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("twelve", tree.right(tree.root()).getElement().getValue());
        assertEquals("fifteen", tree.right(tree.right(tree.root())).getElement().getValue());
        
        // is red = 1, is black = 0
        
        // root is black = 7
        assertEquals(0, tree.getProperty(tree.root()));
        // left child of root = 4 = black
        assertEquals(0, tree.getProperty(tree.left(tree.root())));
        // right child of root = 12 = black
        assertEquals(0, tree.getProperty(tree.right(tree.root())));
        // right child of right child of root = 15 = red
        assertEquals(1, tree.getProperty(tree.right(tree.right(tree.root()))));
        
        // test remove right child
        tree.remove(15);
        assertEquals(3, tree.size());
        
        // check locations and colors
        assertEquals("seven", tree.root().getElement().getValue());
        assertEquals("four", tree.left(tree.root()).getElement().getValue());
        assertEquals("twelve", tree.right(tree.root()).getElement().getValue());
        
        // colors
        // root is black = 7
        assertEquals(0, tree.getProperty(tree.root()));
        // left child of root = 4 = black
        assertEquals(0, tree.getProperty(tree.left(tree.root())));
        // right child of root = 12 = black
        assertEquals(0, tree.getProperty(tree.right(tree.root())));
        
        // test remove left child
        tree.remove(4);
        assertEquals(2, tree.size());
        
        // check locations and colors
        assertEquals("seven", tree.root().getElement().getValue());
        assertNull(tree.left(tree.root()).getElement());
        assertEquals("twelve", tree.right(tree.root()).getElement().getValue());
        
        // colors
        assertEquals(0, tree.getProperty(tree.root()));
        // right child of root = 12 = red (after remove)
        assertEquals(1, tree.getProperty(tree.right(tree.root())));
        
        // try to remove root node
        tree.remove(7);
        assertEquals(1, tree.size());
        
        // check contents and colors
        assertEquals("twelve", tree.root().getElement().getValue());
        
        // check colors
        assertEquals(0, tree.getProperty(tree.root()));
        
        // Extra remove operations with separate list to cover other statements when removing
        BinarySearchTreeMap<Integer, String> treeExtra = new RedBlackTreeMap<Integer, String>();
		treeExtra.put(44, "forty-four");
        assertEquals("forty-four", treeExtra.root().getElement().getValue());
        treeExtra.put(17, "seventeen");
        treeExtra.put(78, "seventy-eight");
        treeExtra.put(32, "thirty-two");
        treeExtra.put(50, "fifty");
        treeExtra.put(88, "eighty-eight");
        treeExtra.put(62, "sixty-two");
        treeExtra.put(48, "forty-eight");    
        assertEquals(44, (int)treeExtra.root().getElement().getKey()); // node 44
        assertEquals(17, (int)treeExtra.left(treeExtra.root()).getElement().getKey()); // node 17
        assertEquals(78, (int)treeExtra.right(treeExtra.root()).getElement().getKey()); // node 78
        assertEquals(32, (int)(treeExtra.right(treeExtra.left(treeExtra.root())).getElement().getKey())); // node 32
        assertEquals(50, (int)(treeExtra.left(treeExtra.right(treeExtra.root())).getElement().getKey())); // node 50
        assertEquals(88, (int)(treeExtra.right(treeExtra.right(treeExtra.root())).getElement().getKey())); // node 88
        assertEquals(62, (int)(treeExtra.right(treeExtra.left(treeExtra.right(treeExtra.root()))).getElement().getKey())); // node 62
        assertEquals(48, (int)(treeExtra.left(treeExtra.left(treeExtra.right(treeExtra.root()))).getElement().getKey())); // node 62
        assertEquals(8, treeExtra.size());
        treeExtra.remove(44);
        treeExtra.remove(32);
        treeExtra.remove(17);
        treeExtra.remove(78);
        treeExtra.remove(88);
        treeExtra.remove(62);
        treeExtra.remove(48);
        treeExtra.remove(50);
        treeExtra.remove(90);
        assertEquals(0, treeExtra.size());
    }
}
