package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Constructs comparators for sorting classes and can also help when sorting in Natural Order which can be indicated
 * by the way a sorting list is constructed (does it have a comparator or not will help this class determine which method to call)
 * @author Jake Donovan
 *
 * @param <E> the generic type of data to sort
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	/** A custom comparator used for sorting other than Natural Order */
    private Comparator<E> comparator;
    
    /**
     * Constructs a new Comparison sorter with a passed Comparator that will be used
     * @param comparator the comparator that will be used (Could be StudentGPA which sorts based on Student's
     * GPA, or it could be StudentID which sorts based on a student's id)
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Sets the Comparator that will be used
     * @param comparator the comparator that will be used
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }
    
    /**
     * Sorts a data object (a student for ex) in Natural ordering which is based on the classes'
     * compareTo() method specifications (aka how it wants to sort an object like a Student)
     * @author Jake Donovan
     *
     */
    private class NaturalOrder implements Comparator<E> {
    	/**
    	 * Sorts an object in it's natural order when a comparator is present
    	 * @param first the first object (data element) that will be compared
    	 * @param second the second object (data element) that will be compared
    	 * @return Integer object one's positioning when compared to object two in natural ordering
    	 * aka -1, 1, or 0
    	 */
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * Compares student's object and returns their positioning in ordering when compared to eachother =
     * -1, 1, 0, passes to private Natural Order compare class
     * @param data1 the first element to be compared
     * @param data2 the second element to be compared
     * @return Integer the positioning (ordering) when comparing data 1 to data 2 (-1 = comes before data 2, 1 = after, 0 they have same priority)
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}
