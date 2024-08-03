package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * MapEntry implements the Entry abstract data type.
     * 
     * @author Dr. King
     *
     * @param <K> the type of key stored in the entry
     * @param <V> the type of value stored in the entry
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {
    	/** A key associated with a map entry */
        private K key;
        /** A value associated with a map entry */
        private V value;

        /**
         * Constructs a MapEntry with a provided key and a provided value
         * 
         * @param key   the key to store in the entry
         * @param value the value to store in the entry
         */
        public MapEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }
        
        /**
         * Gets map entry key
         * @return key the map entry key
         */
        @Override
        public K getKey() {
            return key;
        }
        
        /**
         * Gets map entry value
         * @return value the map entry value
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Set the key of the entry to the provided key
         * 
         * @param key the key to store in the entry
         */
        private void setKey(K key) {
            this.key = key;
        }

        /**
         * Set the value of the entry to the provided value
         * 
         * @param value the value to store in the entry
         */
        public void setValue(V value) {
            this.value = value;
        }
        
        /**
         * Compares entries to determine their ordering within map
         * @param o the Entry the will be used for comparison
         * @return Integer an int value that will indicate an entries' position within the map which will be
         * 0 for same ordering precedence, 1 meaning the entry should come after the one it is being compared to
         * and -1 meaning that an entry should come after the one it is being compared to
         */
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>)this.key).compareTo(o.getKey());
        }       
    }
    
    /**
     * EntryCollection implements the {@link Iterable} interface to allow traversing
     * through the entries stored in the map. EntryCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     *
     */
    protected class EntryCollection implements Iterable<Entry<K, V>> {
    	/** A list of entries */
        private List<Entry<K, V>> list;
        
        /**
         * Constructs an entry collection
         */
        public EntryCollection() {
            list = new SinglyLinkedList<Entry<K, V>>();
        }
        
        /**
         * Adds an entry to the entry collection
         * @param entry the entry that will be added to list (aka the entry collection)
         */
        public void add(Entry<K, V> entry) {
            list.addLast(entry);
        }
        
        /**
         * Returns an entry iterator that can be used to iterate through
         * the entry collection
         * @return EntryCollectionIterator an entry collection iterator
         */
        public Iterator<Entry<K, V>> iterator() {
            return new EntryCollectionIterator();
        }
        
        /**
         * This private inner class constructs and EntryCollectionIterator which can examine all elements in an EntryCollection
         * @author Jake Donovan
         *
         */
        private class EntryCollectionIterator implements Iterator<Entry<K, V>> {
        	/** An iterator that can examine entries with keys and values */
            private Iterator<Entry<K, V>> it;
            
            /**
             * Constructs an EntryCollectionIterator
             */
            public EntryCollectionIterator() {
                it = list.iterator();
            }
            
            /**
             * Checks if entry collection iterator has next entry
             * @return true if the entry collection iterator has another entry to examine, false otherwise.
             */
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }
            
            /**
             * Gets the next entry in the entry collection iterator
             * @return it.next() an entry in the entry collection iterator
             */
            @Override
            public Entry<K, V> next() {
                return it.next();
            }
            
            /**
             * Checks to make sure the a remove() operation is not performed in the entry collection iterator
             * @throws UnsupportedOperationException if a remove operation is performed in entry collection iterator class
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }

    /**
     * KeyIterator implements the {@link Iterator} interface to allow traversing
     * through the keys stored in the map
     * 
     * @author Dr. King
     *
     */
    protected class KeyIterator implements Iterator<K> {
    	/** Contructs a KeyIterator that can be used to examine keys in entries */
    	private Iterator<Entry<K, V>> it;
    	
    	/**
    	 * Constructs a KeyIterator
    	 */
    	public KeyIterator() {
    		it = entrySet().iterator();
    	}
    	
    	/**
    	 * Checks if key iterator has next entry (aka the next key is what we are looking at)
    	 * @return true if there is another key to examine, returns false otherwise
    	 */
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}
		
		/**
		 * Gets next key in the key iterator
		 * @return K the next key from the key iterator (the next key being examined)
		 */
		@Override
		public K next() {
			return it.next().getKey();
		}
		
		/**
		 * Checks if a remove operation is performed with key iterator which is not valid, this method
		 * should throw an Exception
		 * @throws UnsupportedOperationException if a remove operation is performed in key iterator class
		 */
		public void remove() {
			 throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
    }

    /**
     * ValueIterator implements the {@link Iterator} interface to allow traversing
     * through the values stored in the map
     * 
     * @author Dr. King
     *
     */
    protected class ValueIterator implements Iterator<V> {
    	/** The value iterator */
    	private Iterator<Entry<K, V>> it;
    	
    	/**
    	 * Constructs a value iterator
    	 */
    	public ValueIterator() {
    		it = entrySet().iterator();
    	}
    	
    	/**
    	 * Checks if ValueIterator has next Value
    	 * @return true if the value iterator has next value, false otherwise
    	 */
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}
		
		/**
		 * Gets next Value in ValueIterator
		 * @return V the next value in the ValueIterator
		 */
		@Override
		public V next() {
			return it.next().getValue();
		}
		
		/**
		 * Checks for a remove operation which is not valid within ValueIterator class
		 * @throws UnsupportedOperationException if the remove operation method is called
		 */
		public void remove() {
			 throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
    }
    
    /**
     * Checks if AbstractMap is empty
     * @return true if the AbstractMap is empty, returns false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * Gets a KeyIterator
     * @return KeyIterator an iterator used to examine keys in entries
     */
    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }
    
    /**
     * Gets a ValueIterable which iterates values in entries
     * @return ValueIterable a private class used to iterate through values in entries
     */
    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }
    
    /**
     * This private inner class is responsible for contructing and utilizing a value iterator to iterate
     * through values in entries
     * @author Jake Donovan
     *
     */
    private class ValueIterable implements Iterable<V> {
    	
    	/**
    	 * Gets a ValueIterator that can be used to iterate through values in entries
    	 * @return ValueIterator the value iterator
    	 */
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
    }
}
