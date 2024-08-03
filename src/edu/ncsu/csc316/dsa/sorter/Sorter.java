package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @param <E> the generic type of data to sort
 */
public interface Sorter<E> {
    /** abstract method that is utilized by each sorting class which will be specify
     * how each class will sort data elements (whether it is using a selection sort algorithm, radix, counting, etc)
     * @param data the list of elements that will be sorted
     */
	void sort(E[] data);
}
