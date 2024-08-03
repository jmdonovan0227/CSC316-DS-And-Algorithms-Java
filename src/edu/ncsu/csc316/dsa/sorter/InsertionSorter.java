package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * 
 * @param <E> the generic type of data to sort
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * When a comparator is recieved, it will be passed to super class and be constructed so when
	 * using InsertionSort.sort(), the method will sort based on the comparator it recieved (based of ids, or gpas for ex)
	 * @param comparator the comparator that will be used
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Otherwise construct an InsertionSorter that will sort in Natural Order
	 */
	public InsertionSorter() {
		this(null);
	}
	
	/**
	 * Sorts a list of elements using an InsertionSort (way of sorting) algorithm
	 * @param data the list of elements that will be sorted
	 */
	@Override
	public void sort(E[] data) {
		for(int i = 1; i <= data.length - 1; i++) {
			E x = data[i];
			int j = i - 1;
			
			while(j >= 0 && compare(data[j], x)  > 0) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			
			data[j + 1] = x;
		}
	}
}
