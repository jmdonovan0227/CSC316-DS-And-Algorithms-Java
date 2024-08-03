package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests StudentIDComparator class
 * @author Jake Donovan
 *
 */
public class StudentIDComparatorTest {
	/** A Student object that will be used for testing */
	private Student sOne;
	/** A Student object that will be used for testing */
	private Student sTwo;
	/** A Student object that will be used for testing */
	private Student sThree;
	/** A Student object that will be used for testing */
	private Student sFour;
	/** A Student object that will be used for testing */
	private Student sCopy;
	/** A custom comparator that will be used to sort student's based on their student id */
	private StudentIDComparator comparator;
	
	/**
	 * Construct student objects and StudentIdComparator
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sCopy = new Student("Name", "LastName", 1, 1, 1.0, "lLast");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("TwoFirst", "TwoLast", 3, 2, 3.0, "twoUnityID");
		sFour = new Student("TwoFirst", "TwoLast", 4, 2, 4.0, "twoUnityID");
		comparator = new StudentIDComparator();
	}
	
	/**
	 * Tests StudentIDComparator.sort()
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sThree) > 0);
		assertFalse(comparator.compare(sOne, sFour) > 0);
		assertFalse(comparator.compare(sOne, sCopy) > 0);
		assertFalse(comparator.compare(sOne, sCopy) < 0);
	}


}
