package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * When a comparator is received, the comparator will be passed to super class and be constructed 
	 * so when a SelectionSorter (that is created) wants to use a custom comparator it can be utilized
	 * @param comparator the comparator that will be used
	 */
	public SelectionSorter(Comparator<E> comparator){
		super(comparator);
	}
	
	/**
	 * When a comparator is not received, a SelectionSorter will be constructed that sorts in Natural Order
	 */
	public SelectionSorter() {
		this(null);
	}
	
	/**
	 * Sorts a list of elements using a Selection Sort algorithm
	 * @param data the list of elements that will be sorted
	 */
	@Override
	public void sort(E[] data) {
		for(int i = 0; i <= data.length - 1; i++) {
			int min = i;
			for(int j = i + 1; j <= data.length - 1; j++) {
				if(compare(data[j], data[min]) < 0) {
					min = j;
				}
			}
			
			if(i != min) {
				E x = data[i];
				data[i] = data[min];
				data[min] = x;
			}
		}
	}
}
