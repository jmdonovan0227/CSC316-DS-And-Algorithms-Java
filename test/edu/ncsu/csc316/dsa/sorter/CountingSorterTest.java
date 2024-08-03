package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
/**
 * Tests CountingSorter class
 * @author Jake Donovan
 *
 */
public class CountingSorterTest {
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
	/** An instance of the ComparisonSorter class which will be used to sort data using a CountingSort algorithm */
	private CountingSorter<Student> sorter;
	
	/**
	 * Construct Student objects and CountingSorter instance
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new CountingSorter<Student>();
	}
	
	/**
	 * Tests CountingSorter.sort()
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
}
