/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Tests QuickSorter class to make sure everything functions as required
 * @author Jake Donovan
 *
 */
public class QuickSorterTest {
	/** An array of Integers in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** An array of Integers in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** An array of Integers in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** A Student object which will be used for testing */
	private Student sOne;
	/** A Student object which will be used for testing */
	private Student sTwo;
	/** A Student object which will be used for testing */
	private Student sThree;
	/** A Student object which will be used for testing */
	private Student sFour;
	/** A Student object which will be used for testing */
	private Student sFive;
	/** A QuickSorter for Integers */
	private QuickSorter<Integer> quickSorter;
	
	/**
	 * Constructs a QuickSorter and several student objects which will be used for testing
	 * @throws java.lang.Exception if the QuickSorter or any of the student object cannot be constructed
	 */
	@Before
	public void setUp() throws Exception {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		quickSorter = new QuickSorter<Integer>();
	}

	/**
	 * Tests QuickSorter.sort()
	 */
	@Test
	public void testSort() {
		// Test QuickSorter using a random element pivot selector
		quickSorter.sort(dataAscending);
		assertEquals(1, (int)dataAscending[0]);
		assertEquals(2, (int)dataAscending[1]);
		assertEquals(3, (int)dataAscending[2]);
		assertEquals(4, (int)dataAscending[3]);
		assertEquals(5, (int)dataAscending[4]);
		

		quickSorter.sort(dataDescending);
		assertEquals(1, (int)dataDescending[0]);
		assertEquals(2, (int)dataDescending[1]);
		assertEquals(3, (int)dataDescending[2]);
		assertEquals(4, (int)dataDescending[3]);
		assertEquals(5, (int)dataDescending[4]);

		quickSorter.sort(dataRandom);
		assertEquals(1, (int)dataRandom[0]);
		assertEquals(2, (int)dataRandom[1]);
		assertEquals(3, (int)dataRandom[2]);
		assertEquals(4, (int)dataRandom[3]);
		assertEquals(5, (int)dataRandom[4]);
		
		// Test QuickSorter using a first element pivot selector
		QuickSorter<Integer> firstSort = new QuickSorter<Integer>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		
		firstSort.sort(dataAscending);
		assertEquals(1, (int)dataAscending[0]);
		assertEquals(2, (int)dataAscending[1]);
		assertEquals(3, (int)dataAscending[2]);
		assertEquals(4, (int)dataAscending[3]);
		assertEquals(5, (int)dataAscending[4]);
		

		firstSort.sort(dataDescending);
		assertEquals(1, (int)dataDescending[0]);
		assertEquals(2, (int)dataDescending[1]);
		assertEquals(3, (int)dataDescending[2]);
		assertEquals(4, (int)dataDescending[3]);
		assertEquals(5, (int)dataDescending[4]);

		firstSort.sort(dataRandom);
		assertEquals(1, (int)dataRandom[0]);
		assertEquals(2, (int)dataRandom[1]);
		assertEquals(3, (int)dataRandom[2]);
		assertEquals(4, (int)dataRandom[3]);
		assertEquals(5, (int)dataRandom[4]);
		
		// Test QuickSorter using a last element pivot selector
		QuickSorter<Integer> lastSort = new QuickSorter<Integer>(QuickSorter.LAST_ELEMENT_SELECTOR);
		
		lastSort.sort(dataAscending);
		assertEquals(1, (int)dataAscending[0]);
		assertEquals(2, (int)dataAscending[1]);
		assertEquals(3, (int)dataAscending[2]);
		assertEquals(4, (int)dataAscending[3]);
		assertEquals(5, (int)dataAscending[4]);
		

		lastSort.sort(dataDescending);
		assertEquals(1, (int)dataDescending[0]);
		assertEquals(2, (int)dataDescending[1]);
		assertEquals(3, (int)dataDescending[2]);
		assertEquals(4, (int)dataDescending[3]);
		assertEquals(5, (int)dataDescending[4]);

		lastSort.sort(dataRandom);
		assertEquals(1, (int)dataRandom[0]);
		assertEquals(2, (int)dataRandom[1]);
		assertEquals(3, (int)dataRandom[2]);
		assertEquals(4, (int)dataRandom[3]);
		assertEquals(5, (int)dataRandom[4]);
		
		// Test QuickSorter using a middle sort pivot selector
		QuickSorter<Integer> middleSort = new QuickSorter<Integer>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		
		middleSort.sort(dataAscending);
		assertEquals(1, (int)dataAscending[0]);
		assertEquals(2, (int)dataAscending[1]);
		assertEquals(3, (int)dataAscending[2]);
		assertEquals(4, (int)dataAscending[3]);
		assertEquals(5, (int)dataAscending[4]);
		

		middleSort.sort(dataDescending);
		assertEquals(1, (int)dataDescending[0]);
		assertEquals(2, (int)dataDescending[1]);
		assertEquals(3, (int)dataDescending[2]);
		assertEquals(4, (int)dataDescending[3]);
		assertEquals(5, (int)dataDescending[4]);

		middleSort.sort(dataRandom);
		assertEquals(1, (int)dataRandom[0]);
		assertEquals(2, (int)dataRandom[1]);
		assertEquals(3, (int)dataRandom[2]);
		assertEquals(4, (int)dataRandom[3]);
		assertEquals(5, (int)dataRandom[4]);
		
		// Test QuickSorter with students and with a custom comparator
		QuickSorter<Student> sortGPA = new QuickSorter<Student>(new StudentGPAComparator());
		
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sortGPA.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sTwo, original[3]);
		assertEquals(sOne, original[4]);
	}
}
