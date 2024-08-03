package edu.ncsu.csc316.dsa.map.hashing;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {
	/** A table of entries aka a table of maps */
    private TableEntry<K, V>[] table;
    /** the size of the table */
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }
    
    /**
     * Returns an iterable set of entries
     * @return collection an iterable set of entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        
        for(int i = 0; i < table.length; i++) {
        	if(!isAvailable(i)) {
        		collection.add(table[i]);
        	}
        }
        
        return collection;
    }
    
    /**
     * Creates a table with a passed capacity, and initializes size to 0
     * @param capacity the capacity of the table that will be constructed
     */
    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }
    
    /**
     * Checks if an index passed as a parameter is available to insert an entry
     * by checking if the entry is null or if an entry that was occupying the entry
     * previously was deleted and the boolean isDeleted was updated to true
     * @param index the index where want to insert an entry
     * @return true if we can insert the entry, false otherwise
     */
    private boolean isAvailable(int index) {
        return table[index] == null || table[index].isDeleted();
    }
    
    /**
     * Gets an entry from a bucket within map with a passed hash integer and a passed key
     * Referenced Data Structures and Algorithms Course Book pg 427 Chapter 10 when creating this method
     * @param hash the passed hash integer
     * @param key the passed key that could be connected to an Entry within map
     * @return the value of the Entry with the connected key if it exists otherwise this will
     * return null if their is not an Entry with the associated key (the parameter key) within map
     */
    @Override
    public V bucketGet(int hash, K key) {
        int j = findBucket(hash, key);
        if(j < 0) {
        	return null;
        }
        
        return table[j].getValue();
    }
    
    /**
     * Puts a new Entry within hash map with a passed key and value and hash integer
     * if the Entry already exists, update the value with passed parameter and return
     * the value that was updated, otherwise return null and increment size indicating a successful
     * add to hash map
     * Referenced Data Structures and Algorithms Course Book pg 427 Chapter 10 when creating this method
     * @param hash a hash integer
     * @param key a passed key that could be connected to an Entry within hash map
     * @param value a passed value that will be used to either update an existing Entry or used to construct
     * a new Entry within hash map
     * @return val the updated value if an Entry already exists with the passed key parameter, otherwise this method 
     * returns null indicating a new Entry was added successfully
     */
    @Override
    public V bucketPut(int hash, K key, V value) {
        int j = findBucket(hash, key);
        
        if(j >= 0) {
        	V val = table[j].getValue();
        	table[j].setValue(value);
        	return val;
        }
        
        table[-(j + 1)] = new TableEntry<>(key, value);
        
        size++;
        
        return null;
    }
    
    /**
     * Finds the bucket where an Entry with the associated key is located (where it should be and where it should go)
     * Referenced Data Structures and Algorithms Course Book pg 427 Chapter 10 when creating this method
     * @param index the index where the Entry should be located
     * @param key the key of an existing Entry within hash map (if a valid key)
     * @return Integer the bucket where the Entry should be placed or where it is currently located within
     * hash map
     */
    private int findBucket(int index, K key) {
        int avail = -1;
        int j = index;
        
        do {
        	if(isAvailable(j)) {
        		if(avail == -1) {
        			avail = j;
        		}
        		
        		if(table[j] == null) {
        			break;
        		}
        	}
        	
        	else if(table[j].getKey().equals(key)) {
        		return j;
        	}
        	
        	j = (j + 1) % capacity();
        } while (j != index);
        
        return -(avail + 1);
    }
    
    /**
     * Removes an Entry from the associated bucket where an Entry is located within hash map
     * Referenced Data Structures and Algorithms Course Book pg 427 Chapter 10 when creating this method
     * @param hash the hash integer
     * @param key the key of an associated Entry within hash map
     * @return answer the value removed from hash map, this will be null if a matching Entry to the passed key
     * is not found
     */
    @Override
    public V bucketRemove(int hash, K key) {
    	int j = findBucket(hash, key);
    	
    	if(j < 0) {
    		return null;
    	}
    	
    	V answer = table[j].getValue();
    	
    	table[j] = new TableEntry<>(null, null);
    	
    	size--;
    	
    	table[j].setDeleted(true);
    	
    	return answer;
    }
    
    /**
     * Returns size of table
     * @return size the size of the table
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Returns the capacity of the table
     * @return Integer the capacity of the table
     */
    @Override
    protected int capacity() {
        return table.length;
    }
    
    /**
     * Private inner classed used to help create TableEntries that can be added
     * to table aka the hash map
     * @author Jake Donovan
     *
     * @param <K> the generic key value
     * @param <V> the generic value
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {
    	/** Boolean to keep track of whether an entry was deleted or not */
        private boolean isDeleted;
        
        /**
         * Constructs a new TableEntry
         * @param key the key that will be used to construct a table entry
         * @param value the value that will be used to construct a table entry
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }
        
        /**
         * Returns True or False based on whether an Entry was deleted or not
         * @return isDeleted a boolean that keep track of whether an Entry was deleted
         * or not 
         */
        public boolean isDeleted() {
            return isDeleted;
        }
        
        /**
         * Sets the isDeleted boolean value to true or false
         * @param deleted a passed boolean that will be used to set the value of isDeleted
         * to true or false
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}
