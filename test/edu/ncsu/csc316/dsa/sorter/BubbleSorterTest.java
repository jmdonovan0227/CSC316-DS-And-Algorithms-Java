/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests BubbleSorter class
 * @author Jake Donovan
 *
 */
public class BubbleSorterTest {
	/** An array of Integers in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** An array of Integers in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** An array of Integers in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** A BubbleSorter class instance which will be used to sort the above Integer arrays using a bubble sort algorithm */
	private BubbleSorter<Integer> integerSorter;
	
	/**
	 * Construct bubble sorter instance
	 * @throws java.lang.Exception if bubble sorter cannot be constructed
	 */
	@Before
	public void setUp() throws Exception {
		integerSorter = new BubbleSorter<Integer>();
	}

	/**
	 * Tests BubbleSorter.sort()
	 */
	@Test
	public void testSort() {
		integerSorter.sort(dataAscending);
		assertEquals(1, (int)dataAscending[0]);
		assertEquals(2, (int)dataAscending[1]);
		assertEquals(3, (int)dataAscending[2]);
		assertEquals(4, (int)dataAscending[3]);
		assertEquals(5, (int)dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals(1, (int)dataDescending[0]);
		assertEquals(2, (int)dataDescending[1]);
		assertEquals(3, (int)dataDescending[2]);
		assertEquals(4, (int)dataDescending[3]);
		assertEquals(5, (int)dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals(1, (int)dataRandom[0]);
		assertEquals(2, (int)dataRandom[1]);
		assertEquals(3, (int)dataRandom[2]);
		assertEquals(4, (int)dataRandom[3]);
		assertEquals(5, (int)dataRandom[4]);
	}
}
