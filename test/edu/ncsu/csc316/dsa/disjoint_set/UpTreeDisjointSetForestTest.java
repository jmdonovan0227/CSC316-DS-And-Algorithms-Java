package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 *
 */
public class UpTreeDisjointSetForestTest {
	/** A disjoint set used for testing */
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
    	// Test 1 used test on pg 674 of Data Structures and Algorithms Course Book to create this test
        DisjointSetForest<Integer> setOne = new UpTreeDisjointSetForest<>();
        Position<Integer> two = setOne.makeSet(2);
        Position<Integer> three = setOne.makeSet(3);
        Position<Integer> six = setOne.makeSet(6);
        Position<Integer> nine = setOne.makeSet(9);
        assertEquals(two, setOne.find(2));
        setOne.union(three, two);
        assertEquals(two, setOne.find(2));
        setOne.union(six, two);
        assertEquals(two, setOne.find(2));
        setOne.union(nine, six);
        assertEquals(two, setOne.find(2));
        
        Position<Integer> five = setOne.makeSet(5);
        Position<Integer> eight = setOne.makeSet(8);
        Position<Integer> ten = setOne.makeSet(10);
        Position<Integer> eleven = setOne.makeSet(11);
        Position<Integer> twelve = setOne.makeSet(12);
        setOne.union(eight, five);
        assertEquals(five, setOne.find(5));
        setOne.union(ten, five);
        assertEquals(five, setOne.find(5));
        setOne.union(eleven, eight);
        assertEquals(five, setOne.find(5));
        setOne.union(twelve, eleven);
        assertEquals(five, setOne.find(5));
        setOne.union(five, two);
        assertEquals(two, setOne.find(2));
        
        // Test 2 test for invalid union
        Exception e = assertThrows(IllegalArgumentException.class, () -> setOne.union(null, null));
        assertEquals("Position is not a valid up tree node.", e.getMessage());
        
        // Test 3 make sure to test that starting at a node other than root will take you back to root with
        // find operation
        Position<Integer> fifteen = setOne.makeSet(15);
        Position<Integer> twenty = setOne.makeSet(20);
        setOne.union(twenty, fifteen);
        assertEquals(fifteen, setOne.find(15));
        Position<Integer> thirty = setOne.makeSet(30);
        setOne.union(fifteen, thirty);
        assertEquals(fifteen, setOne.find(30));
        
        // test x
        DisjointSetForest<Integer> setTwo = new UpTreeDisjointSetForest<>();
        setTwo.makeSet(4);
        setTwo.makeSet(6);
        setTwo.makeSet(0);
        setTwo.makeSet(1);
        setTwo.makeSet(-4);
        setTwo.makeSet(0);
        setTwo.makeSet(-3);
      //  setTwo.find(9);
        //setTwo.find(3);
        
        
        // you should draw out examples by hand (or use the examples
        // in the lecture slides or textbook) to help guide your test cases.
        // Be sure to perform find operations followed by union
        // operations to make sure the appropriate root notes are
        // returned and used when union-ing
    }
}

