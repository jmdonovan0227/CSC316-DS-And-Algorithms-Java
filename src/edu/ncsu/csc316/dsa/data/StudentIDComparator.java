package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 *
 */
public class StudentIDComparator implements Comparator<Student> {
	/**
	 * Compares students based on id number in ascending order
	 * @param one the first Student (Object) that will be compared
	 * @param two the second Student (Object) that will be compared
	 * @return Integer the positioning of Student one within a sorted list based
	 * on Student IDs when compared to Student two (Object), (-1 = before, 1 = after, 0 = they have same priority))
	 */
	@Override
	public int compare(Student one, Student two) {
		if(one.getId() > two.getId()) {
			return 1;
		}
		
		else if(one.getId() < two.getId()) {
			return -1;
		}
		
		return 0;
	}
}
