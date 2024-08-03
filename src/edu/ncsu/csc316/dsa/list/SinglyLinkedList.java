package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }
    
    /**
     * Adds and element at a specified index as long as the index is not less than 0 or greater than size
     * @param index the index that the passed element will be added
     * @param element the element that will be added at the specified index
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size
     */
	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		
	    if(index == 0 && size() == 0) {
			front.next = new LinkedListNode<E>(element, front.next);
			tail = front.next;
			size++;
			return;
		}
		
	    else if(index == 0 && size() != 0) {
	    	front.next = new LinkedListNode<E>(element, front.next);
	    	size++;
	    	return;
	    }
		
		else {
			LinkedListNode<E> current = front.next;
			
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			
			if(index - 1 == size() - 1) {
				current.next = new LinkedListNode<E>(element, current.next);
				tail = current.next;
				size++;
				return;
			}
			
			else {
				current.next = new LinkedListNode<E>(element, current.next);
				size++;
				return;
			}
		}
	}
	
	/**
	 * Gets the element at a specified index as long at the index is not less than 0 or greater than size() - 1
	 * @param index the index where the element will be retrieved
	 * @throws IndexOutOfBoundsException if the passed index is less than 0 or greater than size() - 1
	 * @return E the element at the specified index
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
			
		LinkedListNode<E> current = front.next;
			
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
			
		return current.data;
	}
	
	/**
	 * Removes the element at the specified index as long as the index is not less than or greater than or equal to size
	 * @param index the specified index where an element will be removed
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to size
	 * @return E the element removed at the specified index
	 */
	@Override
	public E remove(int index) {
		checkIndex(index);
		
		E data = null;
		
		if(index == 0 && size() == 1) {
			data = front.next.data;
			front.next = front.next.next;
			tail = null;
			size--;
			return data;
		}
		
		else if(index == 0 && size() == 2) {
			data = front.next.data;
			front.next = front.next.next;
			size--;
			tail = front.next;
			return data;
		}
		
		else if(index == 0 && size() > 2) {
			data = front.next.data;
			front.next = front.next.next;
			size--;
			return data;
		}
		
		else {
			LinkedListNode<E> current = front.next;
			
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			
			if(index == size() - 1) {
				tail = current;
				data = current.next.data;
				current.next = current.next.next;
				size--;
			}
			
			else {
				data = current.next.data;
				current.next = current.next.next;
				size--;
			}
		}
		
		return data;
	}
	
	/**
	 * Sets the element at the specified index as long as the index is not less than zero or greater than size() - 1
	 * @param index the index where an element will be set
	 * @param element the passed element that will be placed at the specified index
	 * @return E the element set at the specified index
	 */
	@Override
	public E set(int index, E element) {
		checkIndex(index);
		
		E data = null;
		
		if(index == 0 && size() == 1) {
			data = front.next.data;
			front.next.setElement(element);
			tail = front.next;
			return data;
		}
		
		else {
			LinkedListNode<E> current = front.next;
			
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			
			if(index == size() - 1) {
				data = current.next.data;
				current.next.setElement(element);
				tail = current.next;
			}
			
			else {
				data = current.next.data;
				current.next.setElement(element);
			}
		}
		
		return data;
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
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     * @throws IndexOutOfBoundsException if the list is empty
     * @return E the element at the tail of the list
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.getElement();
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     * @param element the element to be added at the back of the list
     */    
    @Override
    public void addLast(E element) {
    	if(size() == 0) {
    		front.next = new LinkedListNode<E>(element, front.next);
    		tail = front.next;
    		size++;
    		return;
    	}
    	
    	else {
    		LinkedListNode<E> current = front.next;
            for(int i = 0; i < size() - 1; i++) {
            	current = current.next;
            }
        
            current.next  = new LinkedListNode<E>(element, current.next);
            tail = current.getNext();
            size++;
    	}
    }
    
    /**
     * Returns a new ElementIterator that iterates through the lists contents
     * @return ElementIterator the ElementIterator
     */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	/**
	 * This private inner class is responsible for iterating through lists contents,
	 * implement iterator interface
	 * @author Jake Donovan
	 */
	private class ElementIterator implements Iterator<E> {
	    /**
	     * Keep track of the next node that will be processed
	     */
	    private LinkedListNode<E> current;
	    
	    /** 
	     * Keep track of the node that was processed on the last call to 'next'
	     */
	    private LinkedListNode<E> previous;
	    
	    /** 
	     * Keep track of the previous-previous node that was processed
	     * so that we can update 'next' links when removing
	     */
	    private LinkedListNode<E> previousPrevious;
	    
	    /**
	     * Keep track of whether it's ok to remove an element (based on whether
	     * next() has been called immediately before remove())
	     */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	        previous = front; // start at sentinel node
	        previousPrevious = front; // start at sentinel node
	        current = front.next; // this hasn't been read in yet, current points to the next element to be read in
	        tail = previous;
	    }
	    
	    /**
	     * Checks if ElementIterator has another element to examine
	     * @return True if there is another element to examine otherwise this returns false
	     */
	    @Override
	    public boolean hasNext() {
			return current != null; // is there an element to read in?
	    }
	    
	    /**
	     * Returns the next element in the list if there are more elements to examine in list
	     * @throws NoSuchElementException if there are no more elements examine in the list
	     * @return E the next element in the list
	     */
	    @Override
	    public E next() {
			if(!hasNext()) throw new NoSuchElementException(); // if not throw an Exception
			E result = current.data; // read in the next element data
			previousPrevious = previous; // don't move 
			previous = current;
			tail = previous;
			current = current.next;
			removeOK = true;
			return result;
	    }
	    
	    /**
	     * Removes the last element returned by next() as long as there has been at least one call to next() and there are elements in list
	     * @throws IllegalStateException if list is empty or there are no more elements to examine in list
	     */
	    @Override    
	    public void remove() {
	    	if(previous == null || previous == front) throw new IllegalStateException();
	        if(!hasNext()) tail = previousPrevious;
	        if(removeOK) {
	        	previousPrevious.next = current;
	            previous = previousPrevious;
	            removeOK = false;
	            size--; // not sure if this is needed might remove later
	        }
	    }
	}
	
	/**
	 * This private inner class is responsible for creating LinkedListNodes that can be used to iterate through list and examine each element in list
	 * @author Jake Donovan
	 *
	 * @param <E> the generic data element (E)
	 */
	private static class LinkedListNode<E> {
		/** The element data */
		private E data;
		/** The next node in list of elements */
		private LinkedListNode<E> next;
		
		/**
		 * Creates a new LinkedListNode with a passed element aka data
		 * @param data the passed element that will be referenced to a LinkedListNode
		 */
		public LinkedListNode(E data) {
			this(data, null);
		}
		
		/**
		 * Creates a LinkedListNode with a passed element and a reference the node's position
		 * @param data the element that will be connected to a LinkedListNode
		 * @param next the node where the element will be referenced
		 */
		public LinkedListNode(E data, LinkedListNode<E> next) {
			setNext(next);
			setElement(data);
		}
		
		/**
		 * Gets the next node, will remove SuppressedWarnings before finishing project
		 * @return next the next node
		 */
		//@SuppressWarnings("unused")
		public LinkedListNode<E> getNext(){
			return next;
		}
		
		/**
		 * Gets the element linked to a specific node in the list
		 * @return data the element connected a node in the list
		 */
		public E getElement() {
			return data;
		}
		
		/**
		 * Sets the next node with a passed node reference
		 * @param next the next node
		 */
		public void setNext(LinkedListNode<E> next) {
			this.next = next;
		}
		
		/**
		 * Sets the element at a specified node
		 * @param data the element that will be set at a specified node
		 */
		public void setElement(E data) {
			this.data = data;
		}
	}
}