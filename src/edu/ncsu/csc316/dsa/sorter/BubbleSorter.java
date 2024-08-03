/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Sorts a list of elements using bubble sorter method
 * @author Jake Donovan
 *
 * @param <E> the generic type of data to sort
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * When a comparitor is recieved when sorting through bubble sorter it will be passed to super class
	 * and constructed so that method knows how to order elements (id? or GPA?)
	 * @param comparator the comparator that will be used
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * When no comparator is recieved construct a general BubbleSorter() that will sort in Natural Order
	 */
	public BubbleSorter() {
		this(null);
	}
	
	/**
	 * Sorts elements from a list of E in bubble sort
	 * @param data the list of elements that will be sorted
	 */
	@Override
	public void sort(E[] data) {
		boolean r = true;
		
		while(r) {
			r = false;
			for(int i = 1; i <= data.length - 1; i++) {
				if(compare(data[i], data[i - 1]) < 0) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
	}
}
