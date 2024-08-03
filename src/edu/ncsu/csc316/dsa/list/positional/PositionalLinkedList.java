package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * When coding this project please take note I did reference our Course Book Data Structures and Algorithms pages 277-280 in section 7.3
 * Postional Lists when coding my inner classes and methods in this class (Jake Donovan)
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, front, null);
        front.setNext(tail);
        size = 0;
    }
    
    /**
     * Private helper method to get position of each postional node in list if the node is the front of the list or tail return null
     * Referenced from Data Structures and Algorithms Course Book pg 278 section 7.3
     * @param node the positional nodes who's position will be returned (position in the list)
     * @return node the positional node who's position will be returned (position in the list)
     */
    private Position<E> position(PositionalNode<E> node){
    	if(node == front || node == tail) {
    		return null;
    	}
    	
    	return node;
    }
    
    /**
     * Adds an element between the node before (where I want to add in list) and the next node (in the list in between next and prev) of the list with a passed element
     * Referenced from Data Structures and Algorithms Course Book pg 279 section 7.3
     * @param element the element that will be added in list
     * @param prev the previous node
     * @param next the next node
     * @return newest the positional node who's position in the list will be returned
     */
    private Position<E> addBetween(E element, PositionalNode<E> prev, PositionalNode<E> next) {
		PositionalNode<E> newest = new PositionalNode<E>(element, prev, next);
		prev.setNext(newest);
		next.setPrevious(newest);
		size++;
		return newest;
    }
    
    /**
     * Returns an ElementIterator that iterates through the elements in the list
     * @return ElementIterator the ElementIterator
     */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	/**
	 * Adds an element after the specified position in the list
	 * Referenced from Data Structures and Algorithms Course Book pg 279 section 7.3
	 * @param p the position the element will be added after
	 * @param element the element that will be added
	 * @return Position the positional node who's position will be returned (position in the list where the element was added)
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node, node.getNext());
	}
	
	/**
	 * Adds an element before a specified postional node (it's position) in the list
	 * Referenced from Data Structures and Algorithms Course Book pg 279 section 7.3
	 * @param p the position of the postional node that the element will be added before
	 * @param element the element that will added to the list
	 * @return Position the positional node who's position (where the element was added) will be returned
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node.getPrevious(), node);
	}
	
	/**
	 * Adds and element to the front of the list
	 * Referenced from Data Structures and Algorithms Course Book pg 279 section 7.3
	 * @param element the element that will be added
	 * @return Position the positional node who's position where the element was added will be returned
	 */
	@Override
	public Position<E> addFirst(E element) {
		return addBetween(element, front, front.getNext());
	}
	
	/**
	 * Adds an element to the tail of the list
	 * Referenced from Data Structures and Algorithms Course Book pg 279 section 7.3
	 * @param element the element that will be added to the tail of the list
	 * @return Position the positional node whos position where the element was added will be returned
	 */
	@Override
	public Position<E> addLast(E element) {
		return addBetween(element, tail.getPrevious(), tail);
	}
	
	/**
	 * Places a node at the position after a specified positional node (a specified position in the list where we want to look)
	 * Referenced from Data Structures and Algorithms Course Book pg 278 section 7.3
	 * @param p the position in the list we want to point after
	 * @return Position the positional node who's position (after specified position, aka the next position) will be returned
	 */
	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> node = validate(p);
		return position(node.getNext());
	}
	
	/**
	 * Gets or points to position before a position p passed to method
	 * Referenced from Data Structures and Algorithms Course Book pg 278 section 7.3
	 * @param p the position we want to point before (get the position before the passed position p)
	 * @return Position the position of the element before the passed position p
	 */
	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> node = validate(p);
		return position(node.getPrevious());
	}
	
	/**
	 * Gets the first positional node in the list (its position)
	 * Referenced from Data Structures and Algorithms Course Book pg 278 section 7.3
	 * @return Position the position of the first positional node in list
	 */
	@Override
	public Position<E> first() {
		return position(front.getNext());
	}
	
	/**
	 * Checks if list is empty
	 * @return True if the list is empty otherwise this will return false
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Gets the positional node at the tail of the list
	 * Referenced from Data Structures and Algorithms Course Book pg 278 section 7.3
	 * @return Position the positional node's position (the position of the last node in the list aka last element's position)
	 */
	@Override
	public Position<E> last() {
		return position(tail.getPrevious());
	}
	
	/**
	 * Iterates through positions in list
	 * @return PositionIterable the iterator that iterates through the positions in the list
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}
	
	/**
	 * Private inner class used to create an iterator to cycle through positions within the list (step through list)
	 * @author Jake Donovan
	 *
	 */
    private class PositionIterable implements Iterable<Position<E>> {
    	/**
    	 * Returns an iterator that goes through positions within list
    	 * @return PositionIterator the PositionIterator
    	 */
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
    
    /**
     * Removes element at a specified position
     * Referenced from Data Structures and Algorithms Course Book pg 280 section 7.3
     * @param p the specified position in list
     * @return E the element that was removed at the specified position
     */
	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> node = validate(p);
		PositionalNode<E> previousNode = node.getPrevious();
		PositionalNode<E> nextNode = node.getNext();
		previousNode.setNext(nextNode);
		nextNode.setPrevious(previousNode);
		size--;
		E removed = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrevious(null);
		return removed;
	}
	
	/**
	 * Sets the passed element at a specified position within the list
	 * Referenced from Data Structures and Algorithms Course Book pg 279 section 7.3
	 * @param p the position where the element will be added
	 * @param element the element that will be added at the specified position
	 * @return E the element that was added at the specified position in the list
	 */
	@Override
	public E set(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		E elementRemoved = node.getElement();
		node.setElement(element);
		return elementRemoved;
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
	 * This private inner class in responsible for created positional nodes that will be used to cycle through element in the list
	 * with a specified element and positional node that element will be connected to (or just element if setting a new element)
	 * referenced Data Structures and Algorithms Course Book Section 7.3 Positional Lists pg 277 when creating this inner class
	 * @author Jake Donovan
	 *
	 * @param <E> the generic data element
	 */
    private static class PositionalNode<E> implements Position<E> {
    	/** The element connected to a positional node */
        private E element;
        /** The next positional node in list */
        private PositionalNode<E> next;
        /** The previous positional node in list */
        private PositionalNode<E> previous;
        
        /**
         * Constructs a positional node with a passed data element aka value
         * @param value the data element that will be used to construct the positional node
         */
        public PositionalNode(E value) {
            this(value, null);
        }
        
        /**
         * Constructs a positional node with a passed value and node that element will be connected to
         * @param value the data or element that will be connected to a positional node
         * @param next the positional node that the element will be connected to
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }
        
        /**
         * Constructs a positional node with a passed value and the positional node after this positional node and the previous positional node being passed
         * as well (lets you know where it's being constructed (sort of), what comes before and after this positional node being constructed
         * @param value the element or data that will be connected to a positional node
         * @param next the next positional node after the one being constructed
         * @param prev the previous positional node of the one being constructed
         */
        public PositionalNode(E value, PositionalNode<E> prev, PositionalNode<E> next) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }
        
        /**
         * Sets the previous positional node with a passed positional node
         * @param prev the node that will become the previous positional node
         */
        public void setPrevious(PositionalNode<E> prev) {
            this.previous = prev;
        }
        
        /**
         * Gets the previous positional node
         * @return previous the previous positional node
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Sets the next positional node
         * @param next the positional node that will be referenced as the next positional node in list
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }
        
        /**
         * Gets the next positional node in list
         * @return next the next positional node in list
         */
        public PositionalNode<E> getNext() {
            return next;
        }
        
        /**
         * Gets the element connected to a positional node in the list
         * @return element the element, data or value connected to a positional node in list
         */
        @Override
        public E getElement() {
        	if(next == null) {
        		throw new IllegalStateException();
        	}
        	
            return element;
        }
        
        /**
         * Sets the element for a positional node in list
         * @param element the element that will be connected (new element) to a positional node in the list
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /**
     * This private inner class is responsible for creating a positional iterator that will be able to iterate through the positions within the list (as each element)
     * is examined the position will change)
     * Referenced Data Structures and Algorithms Course Book pg 287 section 7.4 when creating this method
     * @author Jake Donovan
     *
     */
    private class PositionIterator implements Iterator<Position<E>> {
    	/** The current position in the list */
        private Position<E> current;
        /** The previous position in the list */
        private Position<E> previous;
        /** A boolean that keeps track of whether a remove is valid or not within the list */
        private boolean removeOK;
        
        /**
         * Constructs a new PositionIterator
         */
        public PositionIterator() {
            current = first();
            previous = null;
            removeOK = false;
        }
        
        /**
         * Checks is PositionIterator has next element (if there are more elements to examine in list)
         * @return True if there are more elements to examine in the list otherwise this will return false
         */
        @Override
        public boolean hasNext() {
			return current != null;
        }
        
        /**
         * Gets the next element (what current is currently pointing to in list) in list
         * @throws NoSuchElementException if there are no more elements to read in list
         * @return Position the position of the positional node in the list (returned by next())
         */
        @Override
        public Position<E> next() {
			if(current == null) throw new NoSuchElementException();
			previous = current;
			current = after(current);
			removeOK = true;
			return previous;
        }
        
        /**
         * Removes a positional node from list if at least one call has been made to next(), removeOK boolean makes sure that only one call to remove() can be 
         * made at a time
         * @throws IllegalStateException if next() hasn't been called or the list is empty
         */
        @Override
        public void remove() {
           if(!removeOK) throw new IllegalStateException();
           PositionalLinkedList.this.remove(previous);
           removeOK = false;
        }
    }
    
    /**
     * This private inner class is responsible for iterating through each element within list
     * referenced Algorithms and Data Structures Course Book Section 7.4 Iterators pg 287 when creating this class
     * @author Jake Donovan
     *
     */
    private class ElementIterator implements Iterator<E> {
    	/** A passed iterator that iterates through positional nodes in list */
        private Iterator<Position<E>> it;
        
        /**
         * Constructs a new PositionIterator so that we can examine the element at each positional node
         */
        public ElementIterator() {
            it = new PositionIterator();
        }
        
        /**
         * Checks if there is another element to examine in list
         * @return True if there are more elements to examine in the list otherwise return false
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }
        
        /**
         * Gets the next positional node's element (next positional node in list)
         * @return E the element at the next positional node in the list
         */
        @Override
        public E next() {
            return it.next().getElement();
        }
        
        /**
         * Removes the positional node in the list
         */
        @Override
        public void remove() {
            it.remove();
        }
    }
}
