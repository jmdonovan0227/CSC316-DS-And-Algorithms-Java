package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The UpTreeDisjointSetForest is implemented as a forest of linked up-trees.
 * Using balanced union, {@link DisjointSetForest#union} has worst-case runtime
 * of O(1). Using path-compression find, {@link DisjointSetForest#find} has
 * worst-case O(logn), but over time has worst-case runtime O(log*(n))
 * [log-star].
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the disjoint set
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

    // We need a secondary map to quickly locate an entry within the forest of
    // up-trees
    // NOTE: The textbook implementation does not include this
    // functionality; instead, the textbook implementation leaves
    // the responsibility of tracking positions to the client in a
    // separate map structure
	/** Constructs a LinearProbingHashMap with each entry containing a key and a UpTreeNode*/
    private Map<E, UpTreeNode<E>> map;

    /**
     * Constructs a new DisjointSetForest
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

    /**
     * An UpTreeNode maintains an element, a reference to the node's parent, and (if
     * it's the root of an up-tree) the number of nodes stored within that up-tree.
     * 
     * @author Dr. King
     *
     * @param <E> the generic data element E
     */
    private static class UpTreeNode<E> implements Position<E> {
    	/** The element connected to UpTreeNode */
        private E element;
        /** The parent of the UpTreeNode */
        private UpTreeNode<E> parent;
        /** The number of nodes connected to the UpTreeNode */
        private int count;

        /**
         * Constructs a new UpTreeNode with the given element, a reference to itself as
         * the parent, and a count of 1.
         * 
         * @param element the element to store in the new UpTreeNode
         */
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }

        /**
         * Sets the element of the UpTreeNode to the given element
         * 
         * @param element the element to store in the UpTreeNode
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        /**
         * Gets the element connected to an UpTreeNode
         * @return E the element connected to an UpTreeNode
         */
        @Override
        public E getElement() {
            return element;
        }

        /**
         * Sets the parent of the UpTreeNode to the given UpTreeNode
         * 
         * @param parent the UpTreeNode to set as the current node's parent
         */
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }

        /**
         * Returns a reference to the parent of the current UpTreeNode
         * 
         * @return a reference to the parent of the current UpTreeNode
         */
        public UpTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the number of nodes contained in the UpTree rooted at the current
         * UpTreeNode
         * 
         * @param count the number of nodes held by an UpTreeNode
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * If the current UpTreeNode is the root of an up-tree, returns the number of
         * elements stored within the UpTree. Otherwise, if the current UpTreeNode is
         * not the root of an up-tree, count is undefined.
         * 
         * @return the number of elements stored within the UpTree, if the current
         *         UpTreeNode is the root; otherwise, count is undefined.
         */
        public int getCount() {
            return count;
        }
    }
    
    /**
     * Adds a new entry to the map with a key and a newly constructed
     * UpTreeNode
     * @param value the key and the value that will be used to construct a new UpTreeNode
     * @return node the newly constructed UpTreeNode
     */
    @Override
    public Position<E> makeSet(E value) {
    	UpTreeNode<E> node = new UpTreeNode<E>(value);
        map.put(value, node);
        return node;
    }
    
    /**
     * Finds the root node in a cluster of UpTreeNodes (helpful to find root when you union elements)
     * @param value the passed value that will be used as a starting point to search for the root node
     * @return Position the root node in a cluster of UpTreeNodes
     */
    @Override
    public Position<E> find(E value) {
        // NOTE: The textbook solution requires the client to keep
        // track of the location of each position in the forest.
        // Our implementation includes a Map to handle this for the
        // client, so we should allow the client to find the set
        // that contains a node by specifying the element
        return findHelper(map.get(value));
    }
    
    /**
     * Helps find() method find root node in a cluster of UpTreeNodes
     * Referenced Lecture slides and Data Structures and Algorithms Course Book pg 676 when coding this method
     * @param current the current node we are located at
     * @return UpTreeNode the root node of a cluster of UpTreeNodes
     */
    private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
        // Implement path-compression find
        if(current.parent != current) {
        	current.parent = findHelper(current.parent);
        }
        
        return current.getParent();
    }
    
    /**
     * Links two UpTreeNodes to make a cluster (aka a tree) of UpTreeNodes and updates count
     * which indicates a node now holds a child node (aka more nodes)
     * @param s the position of the first node t1 (as described in lecture notes)
     * @param t the position of the second node t2 (as described in lecture notes)
     */
    @Override
    public void union(Position<E> s, Position<E> t) {
        UpTreeNode<E> a = validate(s);
        UpTreeNode<E> b = validate(t);
        
        if(!a.equals(b)) {
        	if(a.getCount() > b.getCount()) {
        		b.setParent(a);
        		a.count += b.count;
        	}
        	
        	else {
        		a.setParent(b);
        		b.count += a.count;
        	}
        }
    }
    
    /**
     * Checks that passed position is a valid UpTreeNode
     * @param p the passed position that could be a valid UpTreeNode
     * @throws IllegalArgumentException if the passed position p cannot be cast as an UpTreeNode
     * @return UpTreeNode a valid UpTreeNode otherwise an Exception will be thrown
     */
    private UpTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>) p;
    }
}
