package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 *
 */
public class LinkedBinaryTreeTest {
    /** A linked binary tree that will be used for testing */
    private LinkedBinaryTree<String> tree;
    /** A position to a node with an element 1 */
    private Position<String> one;
    /** A position to a node with an element 2 */
    private Position<String> two;
    /** A position to a node with an element 3 */
    private Position<String> three;
    /** A position to a node with an element 4 */
    private Position<String> four;
    /** A position to a node with an element 5 */
    private Position<String> five;
    /** A position to a node with an element 6 */
    private Position<String> six;
    /** A position to a node with an element 7 */
    private Position<String> seven;
    /** A position to a node with an element 8 */
    private Position<String> eight;
    /** A position to a node with an element 9 */
    private Position<String> nine;
    /** A position to a node with an element 10 */
    private Position<String> ten;
    
    /**
     * Helper class to create an invalid position to help test validate(p)
     * @param <E> the generic data element
     */
    private class InvalidPosition<E> implements Position<E> {

        @Override
        public E getElement() {
            return null;
        }
        
    }

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        assertEquals("one", tree.root().getElement());
        tree.set(one, "root");
        assertEquals("root", tree.root().getElement());
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(tree.root()));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        Position<String> node = tree.right(tree.root());
        assertEquals("three", node.getElement());
        assertEquals("one", tree.parent(node).getElement());
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        Position<String> node = tree.right(tree.root());
        assertEquals("three", node.getElement());
        assertEquals("two", tree.sibling(node).getElement());
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        Position<String> node = tree.left(tree.root());
        assertEquals("two", node.getElement());
        assertTrue(tree.isInternal(node));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        Position<String> node = tree.left(tree.root());
        assertEquals("two", node.getElement());
        assertFalse(tree.isLeaf(node));
        Position<String> leaf = tree.left(ten);
        assertEquals("seven", leaf.getElement());
        assertTrue(tree.isLeaf(leaf));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        Position<String> node = tree.left(tree.root());
        assertEquals("two", node.getElement());
        assertFalse(tree.isRoot(node));
        Position<String> root = tree.root();
        assertTrue(tree.isRoot(root));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> pre = tree.preOrder().iterator();
        assertEquals("one", pre.next().getElement());
        assertEquals("two", pre.next().getElement());
        assertEquals("six", pre.next().getElement());
        assertEquals("ten", pre.next().getElement());
        assertEquals("seven", pre.next().getElement());
        assertEquals("five", pre.next().getElement());
        assertEquals("three", pre.next().getElement());
        assertEquals("four", pre.next().getElement());
        assertEquals("eight", pre.next().getElement());
        assertEquals("nine", pre.next().getElement());
        assertFalse(pre.hasNext());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> post = tree.postOrder().iterator();
        assertEquals("six", post.next().getElement());
        assertEquals("seven", post.next().getElement());
        assertEquals("five", post.next().getElement());
        assertEquals("ten", post.next().getElement());
        assertEquals("two", post.next().getElement());
        assertEquals("eight", post.next().getElement());
        assertEquals("nine", post.next().getElement());
        assertEquals("four", post.next().getElement());
        assertEquals("three", post.next().getElement());
        assertEquals("one", post.next().getElement());
        assertFalse(post.hasNext());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> in = tree.inOrder().iterator();
        assertEquals("six", in.next().getElement());
        assertEquals("two", in.next().getElement());
        assertEquals("seven", in.next().getElement());
        assertEquals("ten", in.next().getElement());
        assertEquals("five", in.next().getElement());
        assertEquals("one", in.next().getElement());
        assertEquals("eight", in.next().getElement());
        assertEquals("four", in.next().getElement());
        assertEquals("nine", in.next().getElement());
        assertEquals("three", in.next().getElement());
        assertFalse(in.hasNext());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
        assertTrue(tree.isEmpty());
    }
    
    /**
     * Test level order sorting algorithm
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> level = tree.levelOrder().iterator();
        assertEquals("one", level.next().getElement());
        assertEquals("two", level.next().getElement());
        assertEquals("three", level.next().getElement());
        assertEquals("six", level.next().getElement());
        assertEquals("ten", level.next().getElement());
        assertEquals("four", level.next().getElement());
        assertEquals("seven", level.next().getElement());
        assertEquals("five", level.next().getElement());
        assertEquals("eight", level.next().getElement());
        assertEquals("nine", level.next().getElement());
        assertFalse(level.hasNext());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	createTree();
        tree.addLeft(eight, "hello");
        assertEquals("hello", tree.left(eight).getElement());
        assertEquals(11, tree.size());
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	createTree();
        tree.addRight(eight, "new");
        assertEquals("new", tree.right(eight).getElement());
        assertEquals(11, tree.size());
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        assertEquals(10, tree.size());
        Exception e = assertThrows(IllegalArgumentException.class, () -> tree.remove(one));
        assertEquals("The node has two children", e.getMessage());
        assertEquals(10, tree.size());
        tree.remove(eight);
        assertEquals(9, tree.size());
        tree.remove(nine);
        assertEquals(8, tree.size());
        tree.remove(four);
        assertEquals(7, tree.size());
        tree.remove(three);
        tree.remove(seven);
        tree.remove(five);
        tree.remove(ten);
        tree.remove(six);
        tree.remove(two);
        assertEquals(1, tree.size());
        assertEquals("LinkedBinaryTree[" + "\n" +
        		     "one" + "\n" +
                     "]", tree.toString());
        
        tree.addLeft(one, "two");
        Exception exc = assertThrows(IllegalArgumentException.class, () -> tree.addLeft(one, "nope"));
        assertEquals("Node already has a left child.", exc.getMessage());
        tree.addRight(one, "three");
        Exception exc2 = assertThrows(IllegalArgumentException.class, () -> tree.addRight(one, "nope"));
        assertEquals("Node already has a right child.", exc2.getMessage());
        assertEquals(3, tree.size());
        tree.remove(tree.left(one));
        Exception exc3 = assertThrows(IllegalArgumentException.class, () -> tree.addLeft(null, null));
        assertEquals("Position is not a valid linked binary tree node", exc3.getMessage());
        tree.remove(tree.right(one));
        tree.remove(one);
        assertTrue(tree.isEmpty());
        createTree();
        tree.setRoot(eight);
        assertEquals("eight", tree.root().getElement());
        
        // Testing again for a invalid position
        InvalidPosition<String> position = null;
        Exception exc4 = assertThrows(IllegalArgumentException.class, () -> tree.validate(position));
        assertEquals("Position is not a valid linked binary tree node", exc4.getMessage());
    }
}
