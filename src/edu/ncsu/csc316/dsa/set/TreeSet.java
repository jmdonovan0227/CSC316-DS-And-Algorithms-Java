package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.AVLTreeMap;

/**
 * The TreeSet is implemented as a [REPLACE THIS WITH THE DATA STRUCTURE TYPE YOU CHOSE
 * TO USE IN YOUR CONSTRUCTOR]
 * data structure to support efficient set abstract data type behaviors.
 * 
 * Using a [DATA STRUCTURE TYPE YOU CHOSE] tree ensures worst-case runtime of
 * O(logn) for {@link Set#add}, {@link Set#remove}, and {@link Set#contains};
 * O(nlogn) worst-case runtime for {@link Set#addAll}, {@link Set#removeAll},
 * and {@link Set#retainAll}; and O(1) worst-case runtime for {@link Set#size}
 * and {@link Set#isEmpty}.
 * 
 * The TreeSet class is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the set
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {
    // Since we will delegate to an existing balanced search tree, the entries will
    // be ordered.
    // As a result, we must also restrict our tree set to use Comparable elements.
    /** A map that will be used to construct an AVLTreeMap set */
    private Map<E, E> tree;

    /**
     * Constructs a new TreeSet
     */
    public TreeSet() {
        tree = new AVLTreeMap<E, E>(); // USE AND EFFICIENT, BALANCED SEARCH TREE HERE
    }
    
    /**
     * Constructs an iterator that can iterate through all comparable elements E that 
     * are within AVLTreeMap set
     * @return Iterator an iterator that holds all comparable elements E that are within AVLTreeMap set
     */
    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }
    
    /**
     * Adds passed value to AVLTreeMap set
     * @param value the value that will be added to AVLTreeMap set
     */
    @Override
    public void add(E value) {
    	tree.put(value, value);
    }
    
    /**
     * Checks if passed value is already within AVLTreeMap set
     * @return True if the AVLTreeMap set has the passed value, false otherwise
     */
    @Override
    public boolean contains(E value) {
    	return tree.get(value) != null;
    }
    
    /**
     * Removes the passed param value from the AVLTreeMap set if it exists and returns the removed element,
     * if the element does not exist, return null
     * @param value the value that will be removed (if it exists) from AVLTreeMap set
     * @return E the removed element, or null if it did not exist within AVLTreeMap set
     */
    @Override
    public E remove(E value) {
        return tree.remove(value);
    }
    
    /**
     * Returns the size of the AVLTreeMap set
     * @return Integer the size of the AVLTreeMap set
     */
    @Override
    public int size() {
        return tree.size();
    }
}

