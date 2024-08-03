package edu.ncsu.csc316.dsa.map.hashing;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.AVLTreeMap;

/**
 * The SeparateChainingHashMap is implemented as a hash table that uses separate
 * chaining for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@see Map#put}, {@see Map#get}, and {@see Map#remove}.
 * 
 * The secondary map that appears within each bucket (with separate chaining)
 * supports worst-case O(logn) runtime for {@see Map#put}, {@see Map#get}, and
 * {@link Map#remove} within each bucket.
 * 
 * The SeparateChainingHashMap class is based on the implementation developed
 * for use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {
	/** A table of maps aka a hash map */
    private Map<K, V>[] table;
    /** The size of table */
    private int size;

    /**
     * Constructs a new separate chaining hash map that uses natural ordering of
     * keys when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public SeparateChainingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new separate chaining hash map that
     * uses natural ordering of keys when performing comparisons. The created hash
     * table uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public SeparateChainingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new separate chaining hash map that uses natural ordering of
     * keys when performing comparisons. The created hash table is initialized to
     * have the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public SeparateChainingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new separate chaining hash map that
     * uses natural ordering of keys when performing comparisons. The created hash
     * table is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public SeparateChainingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }
    
    /**
     * Returns an iterable set of entries (so we can iterate through entries within table)
     * @return collection an iterable collection of entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                // Each bucket contains a map, so include
                // all entries in the entrySet for the map
                // at the current bucket
                for (Entry<K, V> entry : table[i].entrySet()) {
                    collection.add(entry);
                }
            }
        }
        return collection;
    }
    
    /**
     * Creates a new table with a passed capacity and sets size as 0, constructs the table
     * with an AVLTreeMap as the secondary map structure
     * @param capacity the initial capacity of the newly constructed hash map
     */
    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        // You can choose to use any EFFICIENT secondary map.
        // UnorderedLinkedMap, SearchTableMap, and BinarySearchTreeMap are NOT the most
        // efficient maps we have discussed this semester since UnorderedLinkedMap has
        // O(n) put, get, and remove; SearchTableMap has O(n) put and remove; and
        // BinarySearchTreeMap has O(n) put, get, and remove. Therefore, use a
        // SkipListMap with expected O(logn) runtime, or a balanced binary search tree
        // for guaranteed O(logn) worst-case runtime.
        table = new AVLTreeMap[capacity];
        size = 0;
    }
    
    /**
     * Gets a TableEntry from the associated bucket within table aka hash map
     * Referenced Data Structures and Algorithms Course Book pg 425 Chapter 10 when creating this method
     * @param hash a hash Integer
     * @param key the key that could be connected to an associated TableEntry
     * @return V the value of the TableEntry that was found with passed key or null if a TableEntry
     * with the passed key does not exist
     */
    @Override
    public V bucketGet(int hash, K key) {
        // Get the bucket at the specified index in the hash table
        Map<K, V> bucket = table[hash];
        // If there is no map in the bucket, then the entry does not exist
        if (bucket == null) {
            return null;
        }
        // Otherwise, delegate to the existing map's get method to return the value
        return bucket.get(key);
    }
    
    /**
     * Puts a new TableEntry within table aka the hash map with a passed hash integer, a key, and value
     * if a TableEntry already exists the value is updated to be passed param value
     * Referenced Data Structures and Algorithms Course Book pg 425 Chapter 10 when creating this method
     * @param hash a hash Integer
     * @param key the key that could be connected an existing TableEntry within table aka hash map or the key
     * that will be used to construct a new TableEntry and then be added to table
     * @param value the value that will be used to update an existing TableEntry within table or that will be
     * used to construct a new TableEntry that will be added to table
     * @return V null if a new TableEntry was constructed successfully and added or the previously held value
     * of a TableEntry which was changed to the passed value parameter after calling this method
     */
    @Override
    public V bucketPut(int hash, K key, V value) {
		Map<K, V> bucket = table[hash];
		
		if(bucket == null) {
			table[hash] = new AVLTreeMap<>();
			bucket = table[hash];
		}
		
		int oldSize = bucket.size();
		
		V answer = bucket.put(key, value);
		
		size += bucket.size() - oldSize;
		
		return answer;
    }
    
    /**
     * Removes an associated TableEntry from the table if it exists within table, uses
     * a passed hash integer and a passed key to locate a matching TableEntry
     * Referenced Data Structures and Algorithms Course Book pg 425 Chapter 10 when creating this method
     * @param hash a hash Integer
     * @param key a key that could be connected to an associated TableEntry within table
     * @return V the value removed if an existing TableEntry was found within table and was removed, 
     * if an existing TableEntry was not able to be found this will return null indicating a TableEntry
     * with the passed key does not exist within table currently
     */
    @Override
    public V bucketRemove(int hash, K key) {
		Map<K, V> bucket = table[hash];
		
		if(bucket == null) {
			return null;
		}
		
		int oldSize = bucket.size();
		
		V answer = bucket.remove(key);
		
		size -= oldSize - bucket.size();
		
		return answer;
    }
    
    /**
     * Returns the size of the table
     * @return size the size of the table
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Returns the capacity of the table
     * @return capacity the capacity of the table
     */
    @Override
    protected int capacity() {
        return table.length;
    }
}