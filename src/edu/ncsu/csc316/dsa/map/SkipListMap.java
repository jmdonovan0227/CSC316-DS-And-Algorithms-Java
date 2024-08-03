package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /**
     * Coin tosses are used when inserting entries into the data structure to ensure
     * 50/50 probability that an entry will be added to the current level of the
     * skip list structure
     */
    private Random coinToss;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /**
     * The number of entries stored in the map
     */
    private int size;

    /**
     * The number of levels of the skip list data structure
     */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    // Helper method to determine if an entry is one of the sentinel
    // -INFINITY or +INFINITY nodes (containing a null key)
    /**
     * Checks if the passed node is sentinel node
     * @param node the passed node
     * @return true if the node is a sentinel node otherwise this returns false
     */
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }
    
    /**
     * Looks for a matching SkipListNode within list by using the passed key param
     * @param key the key that will be cross examined with other keys within list
     * @return current the position of the listnode within list (where it should be located, gets element on lowest level if it is on multiple levels)
     */
    private SkipListNode<K, V> lookUp(K key) {
    	   SkipListNode<K, V> current = start;
    	   
           while (current.below != null) {
               current = current.below;
               while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
                   current = current.next;
               }
           }
    	   
           return current;
    }
    
    /**
     * Gets the value of the SkipListNode with associated key (contains a map entry with a key and value, returns the value if it exists)
     * otherwise this method will return null if a SkipListNode with the associated key does not exist
     * @param key the key that will be used for searching in the SkipListMap
     * @return V the value with the associated SkipListNode (found by searching with key), otherwise this method will return as null
     */
    @Override
    public V get(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        
        if(!isSentinel(temp) && compare(key, temp.getEntry().getKey()) == 0) {
        	return temp.getEntry().getValue();
        }
        
        else {
        	return null;
        }
    }
    
    /**
     * Inserts a new node within list and adjusts all associated previous, and next references within SkipList
     * @param prev the previous node reference (the element before the new node)
     * @param down the node after the new node element
     * @param entry the entry that will be used to construct new node
     * @return newNode the new node that was added to SkipList
     */
    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
        SkipListNode<K, V> newNode = new SkipListNode<K, V>(entry);
        newNode.setBelow(down);
        newNode.setPrevious(prev);
        
        if(prev != null) {
        	newNode.setNext(prev.getNext());
        	newNode.getPrevious().setNext(newNode);
        }
        
        if(newNode.getNext() != null) {
        	newNode.getNext().setPrevious(newNode);
        }
        
        if(down != null) {
        	down.setAbove(newNode);
        }
        
        return newNode;
    }
    
    /**
     * Adds or puts an Entry within SkipList
     * @param key the will either be connected to an existing entry within list or will be used to construct a new SkipListNode within list
     * @param value the value connected to the associated key (will be used even if there is a key linked to the param key, the value will be updated
     * to this passed value).
     * @return V the value that was updated if a SkipListNode already exists with passed key or returns null otherwise (indicating a successful add)
     */
    @Override
    public V put(K key, V value) {
        SkipListNode<K, V> p = lookUp(key);
        
        if(!isSentinel(p) && compare(key, p.getEntry().getKey()) == 0) {
        	V originalValue = p.getEntry().getValue();
        	
        	while(p != null) {
        		p.setEntry(new MapEntry<K, V>(key, value));
        		p = p.getAbove();
        	}
        	
        	return originalValue;
        }
        
        SkipListNode<K, V> q = null;
        
        int currentLevel = -1;
        
        //int coinFlip = coinToss.nextInt(2); // cycles between 0 and 1
        // 0 indicates a head toss while 1 indicates heads.
        int coinFlip = 1;
        
        while(coinFlip != 0) { // Note this may not work with start, may need to check above (above reference)
        // for when we hit the upper level that is a sentinel node.
        currentLevel = currentLevel + 1;
        
        if(currentLevel >= height) {
        	height = height + 1;
        	SkipListNode<K, V> tail = start.next;
        	start = insertAfterAbove(null, start, null);
        	insertAfterAbove(start, tail, null);
        }
        
        q = insertAfterAbove(p, q, new MapEntry<K, V>(key, value));
        
        while(p.getAbove() == null) {
        	p = p.getPrevious();
        }
        
        p = p.getAbove();
        
        //coinFlip = coinToss.nextInt(2); // create a new random int that should equal 0 or 1
        coinFlip = coinToss.nextInt(2);
        }
        
        size = size + 1;
        return null;
    }
    
    /**
     * Removes the value with associated key from the SkipList if a matching SkipListNode cannot be found with the passed key,
     * this method will return null, otherwise this method will return the removed value
     * @param key the key that will be used to search for the element to be removed from SkipList
     * @return V the value that was removed from SkipList otherwise this value will be null if there isn't an SkipListNode within the list with a matching key
     * (to the param key)
     */
    @Override
    public V remove(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        
        if(isSentinel(temp) || compare(key, temp.getEntry().getKey()) != 0) {
        	return null;
        }
        
        else {
        	V value = temp.getEntry().getValue();
        	temp.prev.next = temp.next;
        	int currentHeight = 0;
        	
        	while(temp.above != null) {
        		if(currentHeight == this.height) {
        			temp = temp.above;
            		temp.prev.next = temp.next;
            		height--;
            		break;
        		}
        		
        		else {
        			temp = temp.above;
            		temp.prev.next = temp.next;
            		currentHeight++;
        		}
        	}
        	
        	size--;
        	
        	return value;
        }
    }
    
    /**
     * Gets the size of the SkipList
     * @return size the size of the skip list
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Constructs an interable (iterator) set of entries to examine all entries within skip list
     * @return set an iterable set of entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
        }
        current = current.next;
        while (!isSentinel(current)) {
            set.add(current.getEntry());
            current = current.next;
        }
        return set;
    }
    
    /**
     * Returns the contents of the skip list as a constructed String
     * @return String the contents of the SkipList (map) as a String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.
  //  public String toFullString() {
      //  StringBuilder sb = new StringBuilder("SkipListMap[\n");
      //  SkipListNode<K, V> cursor = start;
      //  SkipListNode<K, V> firstInList = start;
     //   while (cursor != null) {
     //       firstInList = cursor;
      //      sb.append("-INF -> ");
      //      cursor = cursor.next;
     //       while (cursor != null && !isSentinel(cursor)) {
     //           sb.append(cursor.getEntry().getKey() + " -> ");
     //           cursor = cursor.next;
     //       }
      //      sb.append("+INF\n");
     //       cursor = firstInList.below;
     //   }
    //    sb.append("]");
   //     return sb.toString();
  //  }
    
    /**
     * Constructs a SkipListNode that can be used for navigating the SkipList and updating references within SkipList
     * @author Jake Donovan
     *
     * @param <K> the associated key
     * @param <V> the associated value
     */
    private static class SkipListNode<K, V> {
    	/** the passed entry */
        private Entry<K, V> entry;
        /** the above node reference */
        private SkipListNode<K, V> above;
        /** the node below reference */
        private SkipListNode<K, V> below;
        /** the previous node reference */
        private SkipListNode<K, V> prev;
        /** the next node reference */
        private SkipListNode<K, V> next;
        
        /**
         * Constructs a SkipListNode with a passed entry
         * @param entry the passed entry
         */
        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }
        
        /**
         * Gets the node above reference (the node located above the current node within SkipList)
         * @return above the node above the current node within SkipList
         */
        public SkipListNode<K, V> getAbove() {
            return above;
        }
        
        /**
         * Gets the associated entry
         * @return entry the associated entry to the SkipListNode
         */
        public Entry<K, V> getEntry() {
            return entry;
        }
        
        /**
         * Gets the next SkipListNode
         * @return next the next SkipListNode
         */
        public SkipListNode<K, V> getNext() {
            return next;
        }
        
        /**
         * Gets the previous SkipListNode
         * @return prev the previous SkipListNode
         */
        public SkipListNode<K, V> getPrevious() {
            return prev;
        }
        
        /**
         * Sets the above SkipListNode (above current SkipListNode)
         * @param up the new above node reference
         */
        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }
        
        /**
         * Sets the node below reference
         * @param down the node below reference
         */
        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }
        
        /**
         * Sets the entry within SkipListNode
         * @param entry the entry that will be the new entry within SkipListNode
         */
        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }
        
        /**
         * Sets the next SkipListNode
         * @param next the next SkipListNode
         */
        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }
        
        /**
         * Sets the previous SkipListNode
         * @param prev the previous SkipListNode
         */
        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }
}

