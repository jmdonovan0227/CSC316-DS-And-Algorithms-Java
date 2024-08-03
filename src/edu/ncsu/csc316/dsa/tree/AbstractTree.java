package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * A skeletal implementation of the Tree abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the tree
 * abstract data type.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the tree
 */
public abstract class AbstractTree<E> implements Tree<E> {
	/**
	 * Checks if node at passed position p is an internal node
	 * @param p the passed position that will be examined
	 * @return True if the passed position p is an internal node otherwise return false
	 */
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    /**
     * Checks if node at passed position p is a leaf node
     * @param p the passed position p
     * @return True if the passed position p is a leaf node otherwise return false
     */
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }

    /**
     * Checks if node at passed position p is the root node
     * @param p the passed position p that will be examined
     * @return True if the passed position p is the root node otherwise return false
     */
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    /**
     * Check if tree is empty
     * @return True if the tree is empty otherwise return false
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * Updates the value held by a node at a position p with the passed value param and returns
     * the value that was discared
     * Referenced Data Structures and Algorithms Course book pg 328 while coding this method
     * @param p the passed position that will be examined
     * @param value the passed value that will be used to updated the node at position p
     * @return E the value that was discarded from the node at position p
     */
    @Override
    public E set(Position<E> p, E value) {
		AbstractTreeNode<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(value);
		return temp;
    }   
    
    /**
     * Sorts tree using a preOrder algorithm, passes off to a helper method to simplify process rather than
     * doing everything within one method
     * @return traversal an iterable list of positions
     */
    @Override
    public Iterable<Position<E>> preOrder() {
    	PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * Helps preOrder method sort nodes within tree using a preOrder sorting algorithm
     * @param p the position p that will be sorted
     * @param traversal a collection of positions
     */
    private void preOrderHelper(Position<E> p, PositionCollection traversal) {
        traversal.add(p);
        for (Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    } 
    
    /**
     * Sorts nodes within tree using a postOrder algorithm, passes off to helper method to simplify
     * sorting process
     * @return traversal an iterable list of positions
     */
    @Override
    public Iterable<Position<E>> postOrder() {
    	PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            postOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * A helper method to help sort nodes within tree using a postOrder algorithm
     * @param p the position p that will be sorted
     * @param traversal a collection of positions
     */
    private void postOrderHelper(Position<E> p, PositionCollection traversal) {
        for(Position<E> c : children(p)) {
        	postOrderHelper(c, traversal);
        }
        
        traversal.add(p);
    }
    
    /**
     * Sorts nodes within tree using a levelOrder algorithm
     * Referenced Data Structures and Algorithms Course book pg 342 while coding this method
     * @return traversal a collection of positions
     */
    @Override
    public Iterable<Position<E>> levelOrder() {
    	PositionCollection traversal = new PositionCollection();
    	
    	if(!isEmpty()) {
    		Queue<Position<E>> queue = new ArrayBasedQueue<>();
    		queue.enqueue(root());
    		while(!queue.isEmpty()) {
    			Position<E> p = queue.dequeue();
    			traversal.add(p);
    			for(Position<E> c : children(p)) {
    				queue.enqueue(c);
    			}
    		}
    	}
    	
    	return traversal;
    }
    
    /**
     * Safely casts a Position, p, to be an AbstractTreeNode.
     * 
     * @param p the position to cast to a AbstractTreeNode
     * @return a reference to the AbstractTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  AbstractTreeNode
     */
    protected abstract AbstractTreeNode<E> validate(Position<E> p);
    
    /**
     * Creates nodes that can be used to reference points in various types of tree hence why
     * this class is an abstract class as it deals with more than one type of tree
     * @author Jake Donovan
     *
     * @param <E> the generic data value
     */
    protected abstract static class AbstractTreeNode<E> implements Position<E> {
    	/** The element connected to abstract node */
        private E element;
        
        /**
         * Constructs an AbstractTreeNode with passed element
         * @param element the element that will be used to construct the AbstractTreeNode
         */
        public AbstractTreeNode(E element) {
            setElement(element);
        }
        
        /**
         * Gets abstract tree node element
         * @return element that abstract tree node element
         */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the abstract tree node element
         * @param element the passed element that will be the new element linked to an
         * abstract tree node
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * Returns a tree as a String (builder) representation
     * @return String the passed tree as a string list (builder)
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Helper class for toString() to help translate the contents of a tree to a string list
     * @param sb the string builder
     * @param indent an indentation String
     * @param root the root node position
     */
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
    
    /**
     * PositionCollection implements the {@link Iterable} interface to allow traversing
     * through the positions of the tree. PositionCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     *
     */
    protected class PositionCollection implements Iterable<Position<E>> {
    	/** A list of positions */
        private List<Position<E>> list;

        /**
         * Construct a new PositionCollection
         */
        public PositionCollection() {
            list = new SinglyLinkedList<Position<E>>();
        }

        /**
         * Add a position to the collection. Null positions or positions with null
         * elements are not added to the collection
         * 
         * @param position the position to add to the collection
         */
        public void add(Position<E> position) {
            if (position != null && position.getElement() != null) {
                list.addLast(position);
            }
        }

        /**
         * Return an iterator for the PositionCollection
         * @return PositionIterator a position iterator
         */
        public Iterator<Position<E>> iterator() {
            return new PositionSetIterator();
        }
        
        /**
         * Creates a position iterator for this class (this is an inner class)
         * @author Jake Donovan
         *
         */
        private class PositionSetIterator implements Iterator<Position<E>> {
        	/** An iterator that can iterate through various positions */
            private Iterator<Position<E>> it;
            
            /**
             * Construct a position iterator
             */
            public PositionSetIterator() {
                it = list.iterator();
            }
            
            /**
             * Check if position iterator has next position
             * @return True if the iterator has the next position otherwise return false
             */
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }
            
            /**
             * Gets the next position
             * @return next the next position to be examined by iterator
             */
            @Override
            public Position<E> next() {
            	Position<E> next = it.next();
            	return next;
            }
            
            /**
             * Checks for remove operation while iterating through elements. The remove operation is invalid so this method
             * will throw an UnsupportedOperationException
             * @throws UnsupportedOperationException if the user calls this remove method
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }
}

