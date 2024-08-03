package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
	/** Used to keep track of pivot selector */
	private PivotSelector selector;
	
    /**
     * Pivot selection strategy that uses the element at the first index each time a
     * pivot must be selected
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the last index each time a
     * pivot must be selected
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the middle index each time
     * a pivot must be selected
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at a randomly-chosen index
     * each time a pivot must be selected
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
	
    /**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy
     * 
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }

    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy
     * 
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }

    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy
     */
    public QuickSorter() {
        this(null, null);
    }
    
    /**
     * Sets the pivot selector so that a pivot index can be generated and a pivot element can be selected
     * and used to sort elements in the array
     * @param selector the pivot selector that will be used to generate a pivot index that will
     * be then used to sort elements in array.
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = new RandomElementSelector();
        } else {
            this.selector = selector;
        }
    }
    
    
    /**
     * Defines the behaviors of a PivotSelector
     * 
     * @author Dr. King
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * 
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class FirstElementSelector implements PivotSelector {
    	/**
    	 * Generates a pivot by using the index of the first element in the list
    	 * @param low the low index
    	 * @param high the high index
    	 * @return low the index of the first element in list
    	 */
        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * LastElementSelector chooses the last index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class LastElementSelector implements PivotSelector {
    	/**
    	 * Generates a pivot by using the index of the final element in the list
    	 * @param low the low index
    	 * @param high the high index
    	 * @return high the high index aka the index containing the final element in the list
    	 */
        @Override
        public int selectPivot(int low, int high) {
            return high;
        }
    }
    
    /**
     * MiddleElementSelector chooses the middle index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class MiddleElementSelector implements PivotSelector {
    	/**
    	 * Generates a pivot at the index of the middle of the list
    	 * @param low the low index
    	 * @param high the high index
    	 * @return Integer the pivot index which should be in the middle of list
    	 */
        @Override
        public int selectPivot(int low, int high) {
            return (high + low) / 2;
        }
    }
    
    /**
     * RandomElementSelector chooses the a random index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class RandomElementSelector implements PivotSelector {
    	/**
    	 * Selects a random pivot using Math.random() and the low and high index
    	 * @param low the low index
    	 * @param high the high index
    	 * @return Integer the generated pivot index 
    	 */
        @Override
        public int selectPivot(int low, int high) {
        	return (int) (Math.random() * (high - low)) + low;
        }
    }
    
    /**
     * Sorts elements using a QuickSort algorithm
     * @param data the array of elements that will be sorted
     */
	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
	}
	
	/**
	 * Helper method which generates the pivot location and then passes to other
	 * helper methods until the array of elements is sorted
	 * @param data the array of elements
	 * @param low the low index
	 * @param high the high indexx
	 */
	private void quickSort(E[] data, int low, int high) {
		if(low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}
	
	/**
	 * Determines the pivot index and swap the pivot index element with
	 * the element at the end of the list and then passes the low index and
	 * the high index (which now has the pivot element) and partitions the array
	 * so that it can be sorted
	 * @param data the array with all elements
	 * @param low the passed low index
	 * @param high the passed high index
	 * @return Integer the pivot index
	 */
	private int partition(E[] data, int low, int high) {
		int pivotIndex = this.selector.selectPivot(low, high);
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}
	
	/**
	 * Helper method which helps partition the array so that all elements can be sorted
	 * against the pivot so that the overall array of elements can be sorted
	 * @param data the array of elements
	 * @param low the low index
	 * @param high the high index
	 * @return index the index of the pivot 
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		
		int index = low;
		
		for(int j = low; j <= high - 1; j++) {
			if(compare(data[j], pivot) < 0) {
				swap(data, index, j);
				index = index + 1;
			}
		}
		
		swap(data, index, high);
		return index;
	}
	
	/**
	 * Helps swap position of elements used for swapping pivot and other elements in list
	 * while sorting
	 * @param data an array of elements
	 * @param pivotIndex the index of element that will be swapped
	 * @param high the index of an element that will be swapped
	 */
	private void swap(E[] data, int pivotIndex, int high) {
		E pivotElement = data[pivotIndex];
		E endOfListElement = data[high];
		data[pivotIndex] = endOfListElement;
		data[high] = pivotElement;
	}
}