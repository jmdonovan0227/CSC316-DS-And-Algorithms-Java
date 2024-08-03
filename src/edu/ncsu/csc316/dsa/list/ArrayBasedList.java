package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }
    
    /**
     * Adds an element to the ArrayBasedList at an index as long the index specified is not less than 0 or greater than size
     * @param index the index where the element will be added
     * @param element the element that will be added at an index
     * @throws IndexOutOfBoundsException if the index is less than zero or the index is greater than size
     */
	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		
		ensureCapacity(size + 1);
		
		for(int i = size() - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		
		data[index] = element;
		size++;
	}
	
	/**
	 * Gets the element from the list at a specified index as long as the index is not less than 0 and is not greater than size
	 * @param index the index of the element that will be retrieved
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size
	 * @return E the element at a specified index
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}
	
	/**
	 * Removes an element from the list at a specified index is not less than 0 or greater than size
	 * @param index the index of the element to be removed
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size
	 * @return E the element that was removed at the specified index
	 */
	@Override
	public E remove(int index) {
		checkIndex(index);
		
		E removedValue = data[index];
		for(int i = index; i < size() - 1; i++) {
			data[i] = data[i + 1];
		}
		
		data[size() - 1] = null;
		size--;
		
		return removedValue;
	}
	
	/**
	 * Sets the element at a specified index as long as the index is not less than 0 or greater than size
	 * @param index the index of where the element will be set
	 * @param element the element that will be set at the specified index
	 * @return E the element that was set at the specified index
	 */
	@Override
	public E set(int index, E element) {
		checkIndex(index);
		E value = data[index];
		data[index] = element;
		return value;
	}
	
	/**
	 * Gets the size of the list
	 * @return size the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Returns the element iterator that iterates through all elements in list
	 * @return ElementIterator the element iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
    
	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity * 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
    /**
     * This inner class is responsible for constructing an ElementIterator that can iterate through the list's contents, implements java.util.Iterator
     * Used Data Structures and Algorithms Sixth Edition Course Book Page 285 in Section 7.4 Iterators as as a reference
     *  when creating this ArrayBasedList ElementIterator class
     * @author Jake Donovan
     *
     */
    private class ElementIterator implements Iterator<E> {
    	/** The iterator's current position in list */
        private int position;
        /** A boolean used to regulate when a remove is valid when using the iterator and when it is not valid */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            position = 0;
            removeOK = false;
        }
        
        /**
         * Check if ElementIterator has another element to examine
         * @return True if there is another element to examine otherwise return false
         */
        @Override
        public boolean hasNext() {
            return position < size();
        }
        
        /**
         * Gets the next element in the list while iterating through list
         * @throws NoSuchElementException if there are no more elements to examine in list
         * @return E the next element in list
         */
        @Override
        public E next() {
			if(position == size()) throw new NoSuchElementException();
			removeOK = true;
			position++;
			return get(position - 1);
        }
        
        /**
         * Removes the last element returned by next(), sets removeOK to false after each call to not allow multiple removes
         * @throws IllegalStateException if no element have been read in by next() or the list is empty
         */
        @Override
        public void remove() {
        	if(!removeOK) throw new IllegalStateException();
        	ArrayBasedList.this.remove(position - 1);
        	position--;
        	removeOK = false;
        }
    }
}