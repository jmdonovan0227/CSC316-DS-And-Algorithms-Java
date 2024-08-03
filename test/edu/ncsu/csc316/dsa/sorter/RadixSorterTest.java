/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests RadixSorter class
 * @author Jake Dononan
 *
 */
public class RadixSorterTest {
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
	/** An instance of RadixSorter class which will be used to sort data using a RadixSorter algorithm */
	private RadixSorter<Student> sorter;
	
	/**
	 * Construct Student objects and RadixSorter instance
	 * @throws java.lang.Exception if RadixSorter instance cannot be constructed
	 */
	@Before
	public void setUp() throws Exception {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new RadixSorter<Student>();
	}

	/**
	 * Tests RadixSorter.sort()
	 */
	@Test
	public void testSort() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
		
		Student sO = new Student("Tony", "Sheffield", 1, 1, 1.0, "oneUnityID");
		Student sT = new Student("Zola", "Sawyers", 100, 2, 2.0, "twoUnityID");
		Student sTh = new Student("Bob", "Word", 20, 3, 3.0, "threeUnityID");
		Student[] two = {sT, sO, sTh};
		sorter.sort(two);
		assertEquals("Tony", two[0].getFirst());
		assertEquals("Bob", two[1].getFirst());
		assertEquals("Zola", two[2].getFirst());
	}
}
