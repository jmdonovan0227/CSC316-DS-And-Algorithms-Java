package edu.ncsu.csc316.dsa.set;

/**
 * A skeletal implementation of the Set abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the set
 * abstract data type.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the set
 */
public abstract class AbstractSet<E> implements Set<E> {
	/**
	 * Adds all elements from a passed set to constructed set (in class)
	 * @param other the passed set, which it's elements will be "all" added to the class
	 * set
	 */
    @Override
    public void addAll(Set<E> other) {
        for(E element : other) {
            add(element);
        }
    }
    
    /**
     * Removes all elements from class elements so it only contains elements
     * within passed param set (aka retains the elements that are in param set in the class constructed set)
     * @param other the passed set which elements we want to retain in our class set
     */
    @Override
    public void retainAll(Set<E> other) {
        for(E element : this) {
            if(!other.contains(element)) {
                remove(element);
            }
        }
    }
    
    /**
     * Removes all matching elements from class set that are contained within passed param set other
     * @param other the passed set which we want to remove that matching elements (aka elements we have in class set that are also in the
     * param set, we want to remove those elements from class set)
     */
    @Override
    public void removeAll(Set<E> other) {
        for(E element : other) {
            remove(element);
        }
    }
    
    /**
     * Checks if class set is empty
     * @return True if class set is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}

