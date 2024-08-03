package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;

/**
 * Tests StudentManager class
 * @author Jake Donovan
 *
 */
public class StudentManagerTest {
	/** Create a new StudentManager instance */
	private StudentManager sm;
	
	/**
	 * Construct student manager instance
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_randomOrder.csv");
	}
	
	/**
	 * Test StudentManager.sort(), should call InsertionSort class
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
		
		// Check sort based on Student GPA
		Student[] sorted2 = sorted;
		InsertionSorter<Student> sorter = new InsertionSorter<Student>(new StudentGPAComparator());
	    sorter.sort(sorted2);
		assertEquals("Nichole", sorted2[0].getFirst());
		assertEquals("Alicia", sorted2[1].getFirst());
		assertEquals("Charlene", sorted2[2].getFirst());
		assertEquals("Cristine", sorted2[3].getFirst());
		assertEquals("Dante", sorted2[4].getFirst());
		assertEquals("Lacie", sorted2[5].getFirst());
		assertEquals("Idalia", sorted2[6].getFirst());
		assertEquals("Ara", sorted2[7].getFirst());
		assertEquals("Loise", sorted2[8].getFirst());
		assertEquals("Tanner", sorted2[9].getFirst());
		assertEquals("Amber", sorted2[10].getFirst());
		assertEquals("Roxann", sorted2[11].getFirst());
		assertEquals("Tyree", sorted2[12].getFirst());
		assertEquals("Evelin", sorted2[13].getFirst());
		assertEquals("Shanti", sorted2[14].getFirst());
		assertEquals("Lewis", sorted2[15].getFirst());
		
		// Check sort based on Student ID
		Student[] sorted3 = sorted;
		InsertionSorter<Student> sorter2 = new InsertionSorter<Student>(new StudentIDComparator());
		sorter2.sort(sorted3);
		assertEquals("Amber", sorted3[0].getFirst());
		assertEquals("Ara", sorted3[1].getFirst());
		assertEquals("Lacie", sorted3[2].getFirst());
		assertEquals("Idalia", sorted3[3].getFirst());
		assertEquals("Evelin", sorted3[4].getFirst());
		assertEquals("Lewis", sorted3[5].getFirst());
		assertEquals("Alicia", sorted3[6].getFirst());
		assertEquals("Tyree", sorted3[7].getFirst());
		assertEquals("Loise", sorted3[8].getFirst());
		assertEquals("Roxann", sorted3[9].getFirst());
		assertEquals("Nichole", sorted3[10].getFirst());
		assertEquals("Charlene", sorted3[11].getFirst());
		assertEquals("Shanti", sorted3[12].getFirst());
		assertEquals("Cristine", sorted3[13].getFirst());
		assertEquals("Tanner", sorted3[14].getFirst());
		assertEquals("Dante", sorted3[15].getFirst());
}
}