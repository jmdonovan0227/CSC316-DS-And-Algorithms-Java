package edu.ncsu.csc316.dsa.data;

/**
 * An identifiable object that has an ID number (integer)
 * @author Dr. King
 *
 */
public interface Identifiable {
	/** Used to get Student's id (an integer) to be used by Counting and Radix
	 * sorter to correctly sort Identifiable objects that are of type E
	 * @return Integer which is a Student's id number
	 */
	int getId();
}
