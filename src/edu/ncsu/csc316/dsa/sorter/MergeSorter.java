package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }
    
    /**
     * Sorts elements using a MergeSort Algorithm
     * @param data the array that will be sorted
     */
	@Override
	public void sort(E[] data) {
		int n = data.length;
		
		if(n < 2) {
			return;
		}
		
		else {
			int mid = n / 2;
			E[] left = copyArray(data, 0, mid - 1);
			E[] right = copyArray(data, mid, n - 1);
			sort(left);
			sort(right);
			merge(left, right, data);
		}
	}
	
	/**
	 * Helps with breaking array's into smaller arrays that can be compared against eachother and later merged into a sorted array
	 * @param array the entire array which will be split into a smaller array which the elements will be sorted the size of the array
	 * is based on the low and high index values passed (size = high - low + 1)
	 * @param low the low index
	 * @param high the high index
	 * @return copiedArray the smaller array which will be sorted
	 */
	private E[] copyArray(E[] array, int low, int high) {
		// Make size of copied array the high index minus low index + 1, this works
		// for ex say if high index is 4 and low is 0, which is 5 elements, so in this case
		// 4(high) - 0 (low) + 1 = 5 (the size we want).
		@SuppressWarnings("unchecked")
		E[] copiedArray = (E[]) new Comparable[high - low + 1];
		int x = 0; // the actual start of copy array, makes sure the elements
		// from range low to high are added in the copy array at index 0 to the size
		// of elements that are contained within the range of low to high
		for(int i = low; i <= high; i++) {
			copiedArray[x] = array[i];
			x++;
		}
		
		return copiedArray;
	}
	
	/**
	 * Sorts and merges the left and right chunk of the array data so that entire array is now in sorted order
	 * @param left the left part of the array that will be used for sorting (left chunk of array)
	 * @param right the right part of the array that will be used for sorting (right chunk of array)
	 * @param data the array with all contents that will be adjusted when the left array and right array are compared against eachother
	 * they will then be "merged" and they will sort the array data (change its value so the array is now sorted)
	 */
	private void merge(E[] left, E[] right, E[] data) {
		int n = data.length;
		int leftIndex = 0;
		int rightIndex = 0;
		
		while(leftIndex + rightIndex < n) {
			if(rightIndex == right.length || (leftIndex < left.length) && compare(left[leftIndex], right[rightIndex]) < 0) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex = leftIndex + 1;
			}
			
			else {
				data[leftIndex + rightIndex] = right[rightIndex];
				rightIndex = rightIndex + 1;
			}
		}
	}
}
