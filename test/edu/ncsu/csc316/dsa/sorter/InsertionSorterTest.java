package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.io.StudentReader;
/**
 * Tests InsertionSorter class
 * @author Jake Donovan
 *
 */
public class InsertionSorterTest {
	/** An array of Integers in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** An array of Integers in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** An array of Integers in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** An instance of the InsertionSorter class which will be used to sort data using an InsertionSorter algorithm */
	private InsertionSorter<Integer> integerSorter;
	
	/**
	 * Construct InsertionSorter instance
	 */
	@Before
	public void setUp() {
		integerSorter = new InsertionSorter<Integer>();
	}
	
	/**
	 * Test InsertionSorter.sort()
	 */
	@Test
	public void testSortIntegers() {
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
	
	/**
	 * Tests InsertionSorter.sort() for Student objects
	 */
	@Test
	public void testSortStudent() {
		Student[] s = StudentReader.readInputAsArray("input/student_randomOrder.csv");
		InsertionSorter<Student> studentSorter = new InsertionSorter<Student>();
		studentSorter.sort(s);
		assertEquals("Tanner", s[0].getFirst());
		assertEquals("Roxann", s[1].getFirst());
		assertEquals("Shanti", s[2].getFirst());
		assertEquals("Dante", s[3].getFirst());
		assertEquals("Cristine", s[4].getFirst());
		assertEquals("Ara", s[5].getFirst());
		assertEquals("Lewis", s[6].getFirst());
		assertEquals("Charlene", s[7].getFirst());
		assertEquals("Amber", s[8].getFirst());
		assertEquals("Lacie", s[9].getFirst());
		assertEquals("Idalia", s[10].getFirst());
		assertEquals("Tyree", s[11].getFirst());
		assertEquals("Evelin", s[12].getFirst());
		assertEquals("Alicia", s[13].getFirst());
		assertEquals("Loise", s[14].getFirst());
		assertEquals("Nichole", s[15].getFirst());		
	}
}
