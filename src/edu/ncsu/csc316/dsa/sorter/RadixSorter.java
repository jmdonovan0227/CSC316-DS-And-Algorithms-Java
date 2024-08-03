package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {
	/**
	 * Sorts a list of elements using the RadixSort algorithm (sorting using RadixSort methodology
	 * @param data the list of elements that will be sorted
	 */
	@Override
	public void sort(E[] data) {
		int k = 0;
		int max = data[0].getId();
		for(int i = 0; i <= data.length - 1; i++) {
			if(data[i].getId() > max) {
				max = data[i].getId();
			}
		}
		
		k = max; // set k to be the largest value
		
		// Determine how many digits are in the largest value
		int x = (int) Math.log10(k) + 1;
		
		int p = 1;
		
		for(int j = 1; j <= x; j++) {
			int [] b = new int[10];
			
			for(int i = 0; i <= data.length - 1; i++) {
				b[(data[i].getId() / p) % 10] = b[(data[i].getId() / p) % 10] + 1;
			}
			
			
			for(int i = 1; i <= 9; i++) {
				b[i] = b[i - 1] + b[i];
			}
			
			// New array with length n (data's length)
			@SuppressWarnings("unchecked")
			E[] f = (E[])(new Identifiable[data.length]);
			
			for(int i = data.length - 1; i >= 0; i--) {
				f[b[(data[i].getId() / p) % 10] - 1] = data[i];
				b[(data[i].getId() / p) % 10] = b[(data[i].getId() / p) % 10] - 1;
			}
			
			for(int i = 0; i <= data.length - 1; i++) {
				data[i] = f[i];
			}
			
			p = p * 10;
		}
	}
}
