package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

    /** Delegate to our existing singly linked list class **/
    private SinglyLinkedList<E> list;

    /**
     * Construct a new singly-linked list to use when modeling the last-in-first-out
     * paradigm for the stack abstract data type.
     */
    public LinkedStack() {
        list = new SinglyLinkedList<E>();
    }
    
    /**
     * Pushes an element to the top of the stack
     * @param element the element that will be added to the top of the stack
     */
	@Override
	public void push(E element) {
		if(element != null) {
			list.addFirst(element);
		}
	}
	
	/**
	 * Removes and returns the element at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 * @return E the element that was at the top of the stack
	 */
	@Override
	public E pop() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		}
		
		else {
			return list.removeFirst();
		}
	}
	
	/**
	 * Returns but does not remove the element at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 * @return E the element at the top of the stack
	 */
	@Override
	public E top() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		}
		
		else {
			return list.first();
		}
	}
	
	/**
	 * Returns the size of the stack
	 * @return int the size of stack
	 */
	@Override
	public int size() {
		return list.size();
	}
}
