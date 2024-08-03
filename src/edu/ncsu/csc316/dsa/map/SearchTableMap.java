package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A Search Table map is an ordered (meaning entries are stored in a sorted
 * order based on the keys of the entries) contiguous-memory representation of
 * the Map abstract data type. This array-based map delegates to an existing
 * array-based list. To improve efficiency of lookUps, the search table map
 * implements binary search to locate entries in O(logn) worst-case runtime.
 * Insertions and deletions have O(n) worst-case runtime.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {
	/** An array based list  that can be used to examine entries with keys and values and add them to list*/
    private ArrayBasedList<Entry<K, V>> list;

    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SearchTableMap() {
        this(null);
    }
    
    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on a
     * provided {@link Comparator}
     * 
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */ 
    public SearchTableMap(Comparator<K> compare) {
        super(compare);
        list = new ArrayBasedList<Entry<K, V>>();
    }
    
    /**
     * Looks for referenced key within list by delegating binarySearchHelper class
     * @param key the key that will be used to search for a matchin key within list
     * @return Integer the index where the element is located if it exists within the list (where it should be placed within list)
     */
    private int lookUp(K key) {
    	return binarySearchHelper(0, size() - 1, key);
    }
    
    /**
     * Uses a binary search algorithm to search for elements within list using a min index, max index, and a key
     * @param min the minimum index
     * @param max the maximum index
     * @param key the key that will be used to compare with other elements within list
     * @return Integer the index of where the element should be placed
     */
    private int binarySearchHelper(int min, int max, K key) {
    	if(max < min) {
    		//return max + 1;
    		return -1 * (min + 1);
    	}
    	
    	int mid = (min + max) / 2;
    	
    	int compare = compare(key, list.get(mid).getKey());
    	
    	if(compare == 0) {
    		return mid;
    	}
    	
    	else if(compare < 0) {
    		return binarySearchHelper(min, mid - 1, key);
    	}
    	
    	else {
    		return binarySearchHelper(mid + 1, max, key);
    	}
    }
    
    /**
     * Gets size of list
     * @return Integer the list size
     */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
     * Gets the Value by searching for the entry with the associated key (the param key)
     * @param key the key that could be linked to an entry that (is possibly) within list
     * @return V the value of entry with associated key (found a matching key, return its value) otherwise return null
     * if an element with the passed key does not exist
     */
    @Override
    public V get(K key) {
        int index = lookUp(key);
        
        if(index == size() || compare(key, list.get(index).getKey()) != 0) {
        	return null;
        }
        
        else {
        	return list.get(index).getValue();
        }
    }
    
    /**
     * Gets a Iterable set of entries (the entries within list)
     * @return set an iterable set of entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        for (Entry<K, V> entry : list) {
            set.add(entry);
        }
        return set;
    }
    
    /**
     * Adds or puts entries within list
     * @param key the passed key value
     * @param value the passed value
     * @return V the value that was changed if the entry with a matching key otherwise if there is not matching key found
     * this method will return null
     */
    @Override
    public V put(K key, V value) {
        int index = lookUp(key);
        
        if(index < 0) {
        	index = -1 * (index + 1);
        }
        
        if(index < size() && compare(key, list.get(index).getKey()) == 0) {
        	return list.set(index, new MapEntry<K, V>(key, value)).getValue();
        }
        
        list.add(index, new MapEntry<K, V>(key, value));
        return null;
    }
    
    /**
     * Removes an entry and returns the removed value, or if there is no existing entry (search for with param key) then it cannot be removed
     * so this method will return null
     * @param key the passed key
     * @return V the removed value if the entry exists, otherwise this returns as null
     */
    @Override
    public V remove(K key) {
        int index = lookUp(key);
        
        if(index < 0) {
        	index = -1 * (index + 1);
        }
        
        if(index == size() || compare(key, list.get(index).getKey()) != 0) {
        	return null;
        }
        
        else {
        	return list.remove(index).getValue();
        }
    }
    
    /**
     * Returns the elements within the SearchTableMap as a String
     * @return String the map entries (the keys specifically) as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SearchTableMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}