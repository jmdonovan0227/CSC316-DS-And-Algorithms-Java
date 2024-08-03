package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests StudentGPAComparator class
 * @author Jake Donovan
 *
 */
public class StudentGPAComparatorTest {
	/** A student object used for testing */
	private Student sOne;
	/** A student object used for testing */
	private Student sTwo;
	/** A student object used for testing */
	private Student sThree;
	/** A student object used for testing */
	private Student sFour;
	/** A student object used for testing */
	private Student sFive;
	
	/** A student GPA comparator which will be used to help correctly sorted Student objects
	 *  based on GPA
	 */
	private StudentGPAComparator comparator;
	
	/**
	 * Constructs student objects and new StudentGPAComparator
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}
	
	/**
	 * Tests StudentGPAComparator.sort()
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sThree, sFour) > 0);
		assertTrue(comparator.compare(sFour, sFive) > 0);
	}
}
