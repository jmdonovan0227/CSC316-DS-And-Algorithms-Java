package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	/** A student's first name */
	private String first;
	/** A student's last name */
	private String last;
	/** A student's id */
	private int id;
	/** A student's credit hours*/
	private int creditHours;
	/** A student's gpa */
	private double gpa;
	/** A student's unity id*/
	private String unityID;
	
	/**
	 * Constructs a new Student object with passed parameters
	 * @param first the student's first name
	 * @param last the student's last name
	 * @param id the student's id
	 * @param creditHours the student's credit hours
	 * @param gpa the student's gpa
	 * @param unityID the student's unity id
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
	}
	
	/**
	 * Gets student's first name
	 * @return first the student's first name
	 */
	public String getFirst() {
		return first;
	}
	
	/**
	 * Sets the student's first name with a passed String
	 * @param first the student's first name
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	
	/**
	 * Gets student's last name
	 * @return last the student's last name
	 */
	public String getLast() {
		return last;
	}
	
	/**
	 * Sets a student's last name with a passed String
	 * @param last the student's last name
	 */
	public void setLast(String last) {
		this.last = last;
	}
	
	/**
	 * Gets a student's id
	 * @return id the student's id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets a student's id
	 * @param id the student's id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets a student's credit hours
	 * @return creditHours the student's credit hours
	 */
	public int getCreditHours() {
		return creditHours;
	}
	
	/**
	 * Sets a student's credit hours
	 * @param creditHours the student's credit hours
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	
	/**
	 * Gets a student's gpa
	 * @return gpa the student's gpa
	 */
	public double getGpa() {
		return gpa;
	}
	
	/**
	 * Sets the student's gpa
	 * @param gpa the student's gpa
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	/**
	 * Gets a student's unity id
	 * @return unityID the student's unity id
	 */
	public String getUnityID() {
		return unityID;
	}
	
	/**
	 * Sets the student's unity id
	 * @param unityID the student's unity id
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}
	
	/**
	 * Gets hashCode for Student class
	 * @return result the student's hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}
	
	/**
	 * Compares Student object to verify they are equal based on their last name, first name, and student id
	 * @param obj the Object to be compared against another Student object to verify they are equal
	 * @return True if obj and the other Student obj are equal to eachother otherwise return false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id != other.id)
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}
	
	/**
	 * Compares Student object's to find their natural ordering in a sorted list. compareTo() first
	 * checks a student's last name, then compares their first name's, then their student ids
	 * @param student the Student object being compared to
	 * @return Integer an int value which related to the compared Student's object position in natural ordering
	 * versus the Student object it is being compared to (for example -1 means it comes before the other Student object, 1
	 * after, and 0 means they are equal (aka one does not have priority in positioning over the other))
	 */
	public int compareTo(Student student) {
		if(this.getLast().compareTo(student.getLast()) > 0) {
			return 1;
		}
		
		else if(this.getLast().compareTo(student.getLast()) < 0) {
			return -1;
		}
		
		else {
			if(this.getFirst().compareTo(student.getFirst()) > 0) {
				return 1;
			}
			
			else if(this.getFirst().compareTo(student.getFirst()) < 0) {
				return -1;
			}
			
			else {
				if(this.getId() < student.getId()) {
					return -1;
				}
				
				else if(this.getId() > student.getId()) {
					return 1;
				}
			}
		}
		
		return 0;
	}
	
	/**
	 * Returns a String representation of Student class
	 * @return s the String representation of a Student object created in the Student class
	 */
	public String toString() {
		String s = this.getFirst() + ", " + this.getLast() + ", " + this.getId() + ", " + this.getCreditHours() + ", " + this.getGpa() + ", " + this.getUnityID();
		return s;
	}
}
