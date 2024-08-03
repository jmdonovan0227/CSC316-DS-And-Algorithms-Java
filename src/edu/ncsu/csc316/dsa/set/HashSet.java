package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The HashSet is implemented as a linear probing hash table data structure to
 * support efficient set abstract data type behaviors.
 * 
 * Using the linear probing hash table ensures expected runtime of O(1) for
 * {@link Set#add}, {@link Set#remove}, and {@link Set#contains}; O(n) expected
 * runtime for {@link Set#addAll}, {@link Set#removeAll}, and
 * {@link Set#retainAll}; and O(1) worst-case runtime for {@link Set#size} and
 * {@link Set#isEmpty}.
 * 
 * The HashSet class is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the set
 */
public class HashSet<E> extends AbstractSet<E> {
    // Since our hash map uses linear probing, the entries are not ordered.
    // As a result, we do not restrict our hash set to use Comparable elements.
    // This also gives you an option if you need a set to manage elements
    // that are *NOT* Comparable (versus a TreeSet)
    /** A map of comparable elements that will be used for HashSet class */
    private Map<E, E> map;

    /**
     * Constructs a new HashSet
     */
    public HashSet() {
        // This constructor will use our "production version" of our hash map
        // meaning random values for alpha and beta will be used
        this(false);
    }

    /**
     * Constructs a new HashSet to be used ONLY when testing the data structure
     * @param isTesting a boolean that will be either true or false based on whether a hashset will be used
     * for repeatable testing
     */
    public HashSet(boolean isTesting) {
        // If isTesting is true, this constructor will use our "development version" of
        // our hash map
        // meaning alpha=1, beta=1, and prime=7
        map = new LinearProbingHashMap<E, E>(isTesting);
    }
    
    /**
     * Constructs an iterator to be able to iterate through LinearProbingHashMap set comparable elements in this class
     * @return Iterator an iterator that can iterate through LinearProbingHashMap set comparable elements in this class
     */
    @Override
    public Iterator<E> iterator() {
        return map.iterator();
    }
    
    /**
     * Adds passed param value to LinearProbingHashMap set
     * @param value the value that will be added to LinearProbingHashMap set
     */
    @Override
    public void add(E value) {
        map.put(value, value);
    }
    
    /**
     * Checks if passed param value exists within LinearProbingHashMap set
     * @param value the value that will be compared to elements within LinearProbingHashMap set to check if the passed value exits
     * @return True if the passed value exists within LinearProbingHashMap set, false otherwise
     */
    @Override
    public boolean contains(E value) {
       return map.get(value) != null;
    }
    
    /**
     * Removes the passed param value from LinearProbingHashMap set and return removed value, if the passed param value does
     * not exist, return null
     * @param value the value that will be removed from the LinearProbingHashMap set, if it does not exist, return null
     */
    @Override
    public E remove(E value) {
        return map.remove(value);
    }
    
    /**
     * Return the size of the LinearProbingHashMap set
     * @return Integer the size of the LinearProbingHashMap set
     */
    @Override
    public int size() {
        return map.size();
    }
}
