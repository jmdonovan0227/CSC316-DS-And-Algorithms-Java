package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Dr. King
 *
 */
public class StudentGPAComparator implements Comparator<Student> {
	/**
	 * Compares students based on GPA in descending order
	 * @param one the first Student object that will be compared
	 * @param two the second Student object that will be compared
	 * @return Integer the position of Student one when being compared to Student two
	 * based on their GPAs
	 */
	@Override
	public int compare(Student one, Student two) {
		if(one.getGpa() > two.getGpa()) {
			return -1;
		}
		
		else if(one.getGpa() < two.getGpa()) {
			return 1;
		}
		
		else {
			return one.compareTo(two);
		}
	}
}
