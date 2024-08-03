/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests SelectionSorter class
 * @author Jake Donovan
 *
 */
public class SelectionSorterTest {
	/** An array of Integers in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** An array of Integers in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** An array of Integers in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** An instance of the SelectionSorter class which will be used to sort data using a SelectionSorter algorithm */
	private SelectionSorter<Integer> integerSorter;

	/**
	 * Construct SelectionSorter instance to use for sorting in test class
	 * @throws java.lang.Exception if SelectionSorter instance cannot be constructed
	 */
	@Before
	public void setUp() throws Exception {
		integerSorter = new SelectionSorter<Integer>();
	}

	/**
	 * Tests SelectionSorter.sort()
	 */
	@Test
	public void testSort() {
		integerSorter.sort(dataAscending);
		assertEquals(1, (int) dataAscending[0]);
		assertEquals(2, (int) dataAscending[1]);
		assertEquals(3, (int) dataAscending[2]);
		assertEquals(4, (int) dataAscending[3]);
		assertEquals(5, (int) dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals(1, (int) dataDescending[0]);
		assertEquals(2, (int) dataDescending[1]);
		assertEquals(3, (int) dataDescending[2]);
		assertEquals(4, (int) dataDescending[3]);
		assertEquals(5, (int) dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals(1, (int) dataRandom[0]);
		assertEquals(2, (int) dataRandom[1]);
		assertEquals(3, (int) dataRandom[2]);
		assertEquals(4, (int) dataRandom[3]);
		assertEquals(5, (int) dataRandom[4]);
	}
}