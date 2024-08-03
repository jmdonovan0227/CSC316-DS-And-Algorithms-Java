package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests Student class
 * @author Jake Donovan
 *
 */
public class StudentTest {
	/** A student object that will be used for testing */
	private Student sOne;
	/** A student object that will be used for testing */
	private Student sTwo;
	
	/**
	 * Create student objects
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
	}
	
	/**
	 * Test Student.setFirst()
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}
	
	/**
	 * Test Student.setLast()
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}
	
	/**
	 * Test Student.setId()
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}
	
	/**
	 * Test Student.setGpa()
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Test Student.setUnityID()
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}
	
	/**
	 * Test Student.compareTo()
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertFalse(sOne.compareTo(sOne) > 0);
		assertFalse(sOne.compareTo(sOne) < 0);
	}
	
	/**
	 * Test Student.getCreditHours()
	 */
	@Test
	public void testGetCreditHours() {
		Student one = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		assertEquals(1, one.getCreditHours());
	}
	
	/**
	 * Test Student.equals()
	 */
	@Test
	public void testEquals() {
		Student one = new Student("OneFirst", "Wrong", 1, 1, 1.0, "oneUnityID");
		Student oneC = new Student("OneFirst", "Wrong", 1, 1, 1.0, "oneUnityID");
		Student two = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		assertTrue(one.equals(oneC));
		assertFalse(one.equals(two));
		Student three = new Student("Ara", "Same", 1, 1, 1.0, "oneUnityID");
		assertEquals(-1, three.compareTo(one));
		Student four = new Student("Amy", "Same", 2, 1, 1.0, "oneUnityID");
		assertEquals(-1, four.compareTo(three));
		//Student five = new Student("Ara", "Same", 2, 1, 1.0, "oneUnityID");
		//assertEquals(1, five.compareTo(four));
	}
	
	/**
	 * Test Student.hashCode()
	 */
	@Test
	public void testHashcode() {
		Student one = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		Student oneC = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		assertEquals(one.hashCode(), oneC.hashCode());
	}
	
	/**
	 * Test Student.toString()
	 */
	@Test
	public void testToString() {
		Student one = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		String studentOne = "OneFirst, OneLast, 1, 1, 1.0, oneUnityID";
		assertEquals(studentOne, one.toString());
	}
}
