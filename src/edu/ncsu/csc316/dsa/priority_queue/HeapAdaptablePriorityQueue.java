package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * A HeapAdaptablePriorityQueue is an array-based min-heap implementation of the
 * {@link AdaptablePriorityQueue} abstract data type. HeapAdaptablePriorityQueue
 * ensures a O(logn) worst-case runtime for {@link PriorityQueue#insert},
 * {@link PriorityQueue#deleteMin}, {@link AdaptablePriorityQueue#remove}, and
 * {@link AdaptablePriorityQueue#replaceKey}. HeapAdaptablePriorityQueue ensures
 * a O(1) worst-case runtime for {@link PriorityQueue#min},
 * {@link PriorityQueue#size}, {@link PriorityQueue#isEmpty}, and
 * {@link AdaptablePriorityQueue#replaceValue}.
 * 
 * The HeapAdaptablePriorityQueue class is based on an implementation developed
 * for use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys (priorities) stored in the adaptable priority
 *            queue
 * @param <V> the type of values that are associated with keys in the adaptable
 *            priority queue
 */
public class HeapAdaptablePriorityQueue<K extends Comparable<K>, V> extends HeapPriorityQueue<K, V>
        implements AdaptablePriorityQueue<K, V> {

    /**
     * An AdaptablePQEntry extends {@link PQEntry} to maintain a reference of the
     * entry's current index within the array-based heap data structure.
     * 
     * Adaptable PQ Entries must be location-aware so that the worst-case runtime of
     * replaceKey, replaceValue, and remove are O(log n)
     * 
     * @author Dr. King
     *
     * @param <K> the type of key (priority) stored in the adaptable prioriy queue
     *            entry
     * @param <V> the type of value stored in the adaptable priority queue entry
     */
    public static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {
    	/** The index of an entry within an AdaptablePQEntry */
        private int index;

        /**
         * Constructs a new AdaptablePQEntry with the given key, value, and index
         * 
         * @param key   the key (priority) of the adaptable priority queue entry
         * @param value the value of the adaptable priority queue entry
         * @param index the index within the array where the entry is currently located
         */
        public AdaptablePQEntry(K key, V value, int index) {
            super(key, value);
            setIndex(index);
        }

        /**
         * Returns the index of the entry within the array-based heap structure
         * 
         * @return the index of the entry within the array-based heap structure
         */
        public int getIndex() {
            return index;
        }

        /**
         * Sets the index of the entry within the array-based heap structure
         * 
         * @param index the index of the entry within the array-based heap structure
         */
        public void setIndex(int index) {
            this.index = index;
        }
    }

    /**
     * Constructs a new HeapAdaptablePriorityQueue using a custom comparator
     * 
     * @param c compare the custom Comparator to use when comparing keys (priorities)
     */
    public HeapAdaptablePriorityQueue(Comparator<K> c) {
        super(c);
    }

    /**
     * Constructs a new HeapAdaptablePriorityQueue using the natural ordering of
     * keys
     */
    public HeapAdaptablePriorityQueue() {
        this(null);
    }

    /**
     * {@inheritDoc}
     * 
     * Specifically, creates a new AdaptablePQEntry with an initial index set to the
     * end of the array-based heap structure
     */
    @Override
    protected AdaptablePQEntry<K, V> createEntry(K key, V value) {
        AdaptablePQEntry<K, V> temp = new AdaptablePQEntry<K, V>(key, value, size());
        return temp;
    }
    
    /**
     * Checks passed entries to make sure they are valid AdaptablePQEntries, if they are
     * valid entries they will be returned, otherwise an IllegalArgumentException will be thrown
     * @param entry a passed Entry to check if it is a valid AdaptablePQEntry
     * @throws IllegalArgumentException if entry is not a valid AdaptablePQEntry  or if the index of
     * temp is greater than or equal to size of if the index of temp does not match entry within queue (does queue get index of temp equal temp?)
     * @return temp a valid AdaptablePQEntry, otherwise an IllegalArgumentException will be thrown
     */
    private AdaptablePQEntry<K, V> validate(Entry<K, V> entry) {
        if (!(entry instanceof AdaptablePQEntry)) {
            throw new IllegalArgumentException("Entry is not a valid adaptable priority queue entry.");
        }
        AdaptablePQEntry<K, V> temp = (AdaptablePQEntry<K, V>) entry;
        if (temp.getIndex() >= list.size() || list.get(temp.getIndex()) != temp) {
            throw new IllegalArgumentException("Invalid Adaptable PQ Entry.");
        }
        return temp;
    }
    
    /**
     * This method is used for swapping the indexes of entries within queue by using the passed indexes
     * of entry 1 (index 1) and entry 2 (index 2)
     * @param index1 the first index of an entry within the queue that will be swapped
     * @param index2 the second index of an entry within the queue that will be swapped
     */
    @Override
    public void swap(int index1, int index2) {
        // Delegate to the super class swap method
        super.swap(index1, index2);
        // But then update the index of each entry so that they remain location-aware
        ((AdaptablePQEntry<K, V>) list.get(index1)).setIndex(index1);
        ((AdaptablePQEntry<K, V>) list.get(index2)).setIndex(index2);
    }
    
    /**
     * Removes an entry from the queue by using the passed entry to connect with an existing entry within queue
     * Referenced Data Structures and Algorithms Course Book Pg 394 Chapter 9 while creating this method
     * @param entry the passed entry that can be linked to an existing entry within queue (if it exists within the list currently)
     */
    @Override
    public void remove(Entry<K, V> entry) {
        AdaptablePQEntry<K, V> locator = validate(entry);
        int j = locator.getIndex();
        if(j == list.size() - 1) {
        	list.remove(list.size() - 1);
        }
        
        else {
        	swap(j, list.size() - 1);
        	list.remove(list.size() - 1);
        	bubble(j);
        }
    }
    
    /**
     * Moves an entry within queue by using the passed index and comparing the with the parent index to determine
     * whether we need to pass our param index to the upHeap helper method or the downHeap helper method
     * @param index the passed index of an entry within queue
     */
    private void bubble(int index) {
        if (index > 0 && compare(list.get(index).getKey(), list.get(parent(index)).getKey()) < 0) {
            upHeap(index);
        } else {
            downHeap(index);
        }
    }
    
    /**
     * Replaces the key within the passed entry within Queue
     * Referenced Data Structures and Algorithms Course Book Pg 394 Chapter 9 while creating this method
     * @param entry the passed entry which is linked to an entry within queue
     * @param key the passed key that will be used to update the entry
     */
    @Override
    public void replaceKey(Entry<K, V> entry, K key) {
        AdaptablePQEntry<K, V> locator = validate(entry);
        locator.setKey(key);
        bubble(locator.getIndex());
    }
    
    /**
     * Replaces the value connected to the passed entry within queue by using a passed entry
     * and a new value that will be used to updated the entry
     * Referenced Data Structures and Algorithms Course Book Pg 394 Chapter 9 while creating this method
     * @param entry the passed entry which is linked to an entry within queue
     * @param value the passed value that will be used to update the entry within the queue
     */
    @Override
    public void replaceValue(Entry<K, V> entry, V value) {
        AdaptablePQEntry<K, V> locator = validate(entry);
        locator.setValue(value);
    }
}