package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {
	/** An unordered positional list */
    private PositionalList<Entry<K, V>> list;
    
    /**
     * Constructs an unordered linked map
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    /**
     * Searches for a position with an associated key within list (uses key to find the Position with associated entry (with the matching key))
     * Had assistance from Griffin Bish on adjusting this Workshop 5 class. Already filled out document, just making sure if anything shows up that I am giving
     * him credit on this method. CSC316-065 Summer 2022
     * @param key the key that will be used to search within Positional List (used to search for matching position)
     * @return e the position that is connected with the passed key, otherwise if there is no matching Position this will be null
     */
    private Position<Entry<K, V>> lookUp(K key)
    {
   	    Position<Entry<K, V>> element = list.first();
    	
    	while(element != null) {
    		if(element.getElement().getKey().equals(key)) {
    			return element;
    		}
    		element = list.after(element);
    	}
    	return null;
    }
    
    /**
     * Gets the value of the Position within PositionalList with the passed key, if the key doesn't have a matching Position within
     * Positional list, this method will return null
     * @param key the key that will be used for cross examining with other elements within list
     * @return V the value with associated position if it does not exist this method will return null
     */
    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        
        if(p == null) {
        	return null;
        }
        
        else {
        	//Position<Entry<K, V>> temp = p;
        	//list.remove(p);
        	if(p != list.first()) {
        		moveToFront(p);
        	}
        	
        	return p.getElement().getValue();
        }
    }
    
    /**
     * Moves the Position to the front of UnsortedLinkedMap
     * @param position the passed Position that will be moved to front
     */
    private void moveToFront(Position<Entry<K, V>> position) {
    	if(position != list.first()) {
    		list.addFirst(position.getElement());
    	}
    }
    
    /**
     * Puts or adds a new Position within UnsortedLinkedMap, if the passed key has a linked Position
     * the value will be updated and the Position will be moved to front
     * @param key the associated key with an element or the key that will be used to construct a new Position
     * @param value the value that will be used to update an existing position or to construct a new Position
     * @return V the value that was replaced if the Position already exists, otherwise this method returns null
     */
    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        
        if(p == null) {
        	list.addFirst(new MapEntry<K, V>(key, value));
        	return null;
        }
        
        else {
        	// list.set returns entry
        	V replaced = p.getElement().getValue();
        	
        	if(p != list.first()) {
        		moveToFront(p);
        	}
        	
        	list.set(p, new MapEntry<K, V>(key, value));
        	return replaced;
        }
    }
    
    /**
     * Removes the Position with the associated key if it exists within UnsortedLinkedMap, otherwise this returns null
     * @param key the key that will be used to find an associated Position within UnsortedLinkedMap
     * @return V the value that was removed or null if there is no existing position connected with the passed key
     */
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       
       if(p == null) {
    	   return null;
       }
       
       else {
    	   V value = p.getElement().getValue();
    	   list.remove(p);
    	   return value;
       }
    }
    
    /**
     * Gets the size of the unsorted linked map
     * @return Integer the size of the unsorted linked map
     */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
     * Returns an Iterable set of entries
     * @return collection an Iterable collection of entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    /**
     * Returns the contents of the UnsortedLinkedMap as String
     * @return String the contents of the UnsortedLinkedMap as a String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
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

