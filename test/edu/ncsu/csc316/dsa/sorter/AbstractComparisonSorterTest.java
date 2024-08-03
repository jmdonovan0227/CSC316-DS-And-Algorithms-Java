/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests AbstractComparisonSorter class
 * @author Jake Donovan
 *
 */
public class AbstractComparisonSorterTest {

	/**
	 * Tests AbstractComparisonSorter.compare()
	 */
	@Test
	public void testCompare() {
		Student one = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		AbstractComparisonSorter<Student> sorter = new BubbleSorter<Student>();
		Student oneC = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		assertEquals(0, sorter.compare(one, oneC));
	}

}
