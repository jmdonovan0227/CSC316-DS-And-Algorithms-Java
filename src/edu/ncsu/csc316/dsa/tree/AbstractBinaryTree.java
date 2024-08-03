package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
	/**
	 * Sorts nodes using inOrder Algorithm
	 * @return traversal an iterable set of positions
	 */
    @Override
    public Iterable<Position<E>> inOrder() {
    	PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * Helper method for inOrder algorithm to help correctly sort nodes using an inOrder algorithm
     * @param p the passed position p
     * @param traversal a collection of positions
     */
    private void inOrderHelper(Position<E> p, PositionCollection traversal) {
    	if(left(p) != null) {
    		inOrderHelper(left(p), traversal);
    	}
    	
    	traversal.add(p);
    	
    	if(right(p) != null) {
    		inOrderHelper(right(p), traversal);
    	}
    }
    
    /**
     * Gets the number of children for a node at a specific position
     * Referenced Data Structures and Algorithms Course Book pg 320 while coding this method
     * @param p the position of a node within a tree
     * @return count the number of children connected to the node at position p
     */
    @Override
    public int numChildren(Position<E> p) {
		int count = 0;
		
		if(left(p) != null) {
			count++;
		}
		
		if(right(p) != null) {
			count++;
		}
		
		return count;
    }
    
    /**
     * Gets the sibling of a node at a passed position p
     * Referenced Data Structures and Algorithms Course book pg 320 Chapter 8 while coding this method
     * @param p the passed position that will be used to determine where the sibling of that position p is
     * @return parent (the sibling to one of the child nodes of the parent position node
     */
    @Override
    public Position<E> sibling(Position<E> p) {
		Position<E> parent = parent(p);
		if(parent == null) {
			return null;
		}
		
		if(p == left(parent)) {
			return right(parent);
		}
		
		else {
			return left(parent);
		}
    }
    
    /**
     * Gets the children of a node a position p
     * @param p the position of a node within a tree that will be used to determine it's children nodes
     * @return childrenCollection an Iterable list of children nodes connect to the the positional param p
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}
