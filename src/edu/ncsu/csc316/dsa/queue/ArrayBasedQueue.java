package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * The Array-based Queue is implemented as a circular array-based data structure
 * to support efficient, O(1) worst-case Queue abstract data type behaviors. The
 * internal array dynamically resizes using the doubling strategy to ensure O(1)
 * amortized cost for adding to the queue.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

    /**
     * Internal array to store the data within the queue
     */
    private E[] data;

    /**
     * A reference to the index of the first element in the queue
     */
    private int front;

    /**
     * A reference to the index immediately after the last element in the queue
     */
    private int rear;

    /**
     * The number of elements stored in the queue
     */
    private int size;

    /**
     * The initial default capacity of the internal array that stores the data
     */
    private static final int DEFAULT_CAPACITY = 0;

    /**
     * Constructs a new array-based queue with the given initialCapcity for the
     * array
     * 
     * @param initialCapacity the initial capacity to use when creating the initial
     *                        array
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedQueue(int initialCapacity) {
        data = (E[]) (new Object[initialCapacity]);
        size = 0;
    }

    /**
     * Constructs a new array-based queue with the default initial capacity for the
     * array
     */
    public ArrayBasedQueue() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * To ensure amortized O(1) cost for adding to the array-based queue, use the
     * doubling strategy on each resize. Here, we add +1 after doubling to handle
     * the special case where the initial capacity is 0 (otherwise, 0*2 would still
     * produce a capacity of 0).
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
            
            @SuppressWarnings("unchecked")
            E[] newData = (E[]) (new Object[newCapacity]);
            for(int i = 0; i < data.length; i++) {
            	newData[i] = data[front++];
            }
            
            // update data to resized array
            data = newData;
            // update front reference
            front = 0;
            // update rear reference
            rear = front + size;
            
            // Remember that we cannot copy an array the same way we do
            // when resizing an array-based list since we do not want to
            // "break" the elements in the queue since there may be wrap-around
            // at the end of the array
        }
    }
    
    /**
     * Adds an element to the end of the queue, and updates rear reference to point to index after last element in queue
     * @param element the element to be added to the end of the queue
     * Used Data and Algorithms Course Book Section 6.2 pg 243 as a reference when creating this method.
     */
	@Override
	public void enqueue(E element) {
		ensureCapacity(size + 1);
		// Determines insertion index for element so that it can correctly be added to queue
		int insertion = (front + size) % data.length;
		// place element at insertion index
		data[insertion] = element;
		// increment size
		size++;
		// update rear reference
		rear = front + size;
	}
	
	/**
	 * Removes an element from the front of the queue, and updates front of queue reference to point to new front
	 * and rear reference to point to index of last element (after remove operation)
	 * @throws NoSuchElementException if the queue is empty
	 * @return remove the element that was removed from the front of the queue
	 * Used Data and Algorithms Course Book Section 6.2 pg 243 as a reference when creating this method.
	 */
	@Override
	public E dequeue() {
		if(front == rear) {
			throw new NoSuchElementException();
		}
		
		else {
			E remove = data[front];
			data[front] = null;
			data[front] = data[(front + 1) % data.length];
			//front = (front + 1) % data.length; 
			//data[size - 1] = null;
			for(int i = 1; i < size - 1; i++) {
				data[front + i] = data[front + i + 1];
			}
			
			data[size - 1] = null;
			
			size--;
			rear = front + size;
			return remove;
		}
	}
	
	/**
	 * Gets the element at the front queue and returns without removing the element
	 * @throws NoSuchElementException if the queue is empty
	 * @return E the element at the front of the queue 
	 */
	@Override
	public E front() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		else {
			return data[front];
		}
	}
	
	/**
	 * Returns the size of the queue
	 * @return size the size of the queue
	 */
	@Override
	public int size() {
		return size;
	}
}   
