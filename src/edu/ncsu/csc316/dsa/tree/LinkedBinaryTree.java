package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * The LinkedBinaryTree is implemented as a linked data structure to support
 * efficient Binary Tree abstract data type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The LinkedBinaryTree class is based on the implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the binary tree
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
	/** A root node */
    private BinaryTreeNode<E> root;
    /** The size of a linked binary tree */
    private int size;

    /**
     * Create a new empty binary tree
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }
    
    /**
     * Safely casts a Position, p, to be a BinaryTreeNode.
     * 
     * @param p the position to cast to a BinaryTreeNode
     * @return a reference to the BinaryTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  BinaryTreeNode
     */
    protected BinaryTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof BinaryTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid linked binary tree node");
        }
        return (BinaryTreeNode<E>) p;
    }

    /**
     * A BinaryTreeNode stores an element and references the node's parent, left
     * child, and right child
     * 
     * @author Dr. King
     *
     * @param <E> the type of element stored in the node
     */
    public static class BinaryTreeNode<E> extends AbstractTreeNode<E> {
    	/** A parent node */
        private BinaryTreeNode<E> parent;
        /** A left child node */
        private BinaryTreeNode<E> left;
        /** A right child node */
        private BinaryTreeNode<E> right;

        /**
         * Constructs a new BinaryTreeNode with the provided element
         * 
         * @param element the element to store in the node
         */
        public BinaryTreeNode(E element) {
            this(element, null);
        }

        /**
         * Constructs a new BinaryTreeNode with the provided element and provided parent
         * reference
         * 
         * @param element the element to store in the node
         * @param parent  the parent of the newly created node
         */
        public BinaryTreeNode(E element, BinaryTreeNode<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Returns the left child of the current node
         * 
         * @return the left child of the current node
         */
        public BinaryTreeNode<E> getLeft() {
            return left;
        }

        /**
         * Returns the right child of the current node
         * 
         * @return the right child of the current node
         */
        public BinaryTreeNode<E> getRight() {
            return right;
        }

        /**
         * Sets the left child of the current node
         * 
         * @param left the node to set as the left child of the current node
         */
        public void setLeft(BinaryTreeNode<E> left) {
            this.left = left;
        }

        /**
         * Sets the right child of the current node
         * 
         * @param right the node to set as the right child of the current node
         */
        public void setRight(BinaryTreeNode<E> right) {
            this.right = right;
        }

        /**
         * Returns the parent of the current node
         * 
         * @return the parent of the current node
         */
        public BinaryTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the parent of the current node
         * 
         * @param parent the node to set as the parent of the current node
         */
        public void setParent(BinaryTreeNode<E> parent) {
            this.parent = parent;
        }
    }
    
    /**
     * Gets node to left of position p
     * @param p the passed position of a node within tree
     * @return node (the node to the left of the node with the position p)
     */
    @Override
    public Position<E> left(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getLeft();
    }
    
    /**
     * Gets node to the right of the node at position p
     * @param p the passed position of a node within tree
     * @return node (the node to right of the node at the passed position p)
     */
    @Override
    public Position<E> right(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getRight();
    }
    
    /**
     * Adds a node to the left of the node at position p and adds the passed value
     * Referenced Data Structures and Algorithms Course Book pg 328 while coding this method
     * @param p the passed position p of the node we are comparing to
     * @param value the passed value of node we are adding
     * @throws IllegalArgumentException if the node at the passed position p already has a left child
     * @return child the left child node
     */
    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        BinaryTreeNode<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        
        BinaryTreeNode<E> child = createNode(value, node, null, null);
        node.setLeft(child);
        size++;
        return child;
    }
    
    /**
     * Adds a node to right of the node at position p and adds the value that is passed to this method
     * Referenced Data Structures and Algorithms Course Book while coding this method on pg 328
     * @param p the passed position we are comparing to
     * @param value the value connected to the node that will be added to the right of the node at position p
     * @throws IllegalArgumentException if the node at position p already has a right child
     * @return child the new right child of the node at position p
     */
    @Override
    public Position<E> addRight(Position<E> p, E value) {
        BinaryTreeNode<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        
        BinaryTreeNode<E> child = createNode(value, node, null, null);
        node.setRight(child);
        size++;
        return child;
    }
    
    /**
     * Gets root node
     * @return root the root node
     */
    @Override
    public Position<E> root() {
        return root;
    }
    
    /**
     * Gets the parent to a node at position p
     * @param p a node at a passed position that will be used for comparison
     * @return node the parent node of the node at the passed position p
     */
    @Override
    public Position<E> parent(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getParent();
    }
    
    /**
     * Adds a root node to tree with a passed value
     * @param value the new value of the root node
     * @throws IllegalArgumentException if the tree already has a root
     * @return root the root node position
     */
    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }
    
    /**
     * Removes a node at a position p
     * Referenced Data Structures and Algorithms Course Book pg 329 while coding this method
     * @param p the node that will be removed that was passed (as a position p where it is in tree)
     * @throws IllegalArgumentException if the node is not a leaf node
     * @return temp the removed value
     */
    @Override
    public E remove(Position<E> p) {
        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("The node has two children");
        }
        
        BinaryTreeNode<E> node = validate(p);
        
        BinaryTreeNode<E> child = node.getLeft() != null ? node.getLeft() : node.getRight();
        
        if(child != null) {
        	child.setParent(node.getParent());
        }
        
        if(node == root) {
        	root = child;
        }
        
        else {
        	BinaryTreeNode<E> parent = node.getParent();
        	if(node == parent.getLeft()) {
        		parent.setLeft(child);
        	}
        	
        	else {
        		parent.setRight(child);
        	}
        }
        
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
    }
    
    /**
     * Gets the size of the LinkedBinaryTree
     * @return size the size of the LinkedBinaryTree
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Creates a node with passed element, parent node, left child node, and right child node
     * @param e a passed value for node
     * @param parent the parent node
     * @param left the left child node
     * @param right the right child node
     * @return newNode the newly created node
     */
    protected BinaryTreeNode<E> createNode(E e, BinaryTreeNode<E> parent, BinaryTreeNode<E> left,
            BinaryTreeNode<E> right) {
        BinaryTreeNode<E> newNode = new BinaryTreeNode<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    // setRoot is needed for a later lab...
    // ...but THIS DESIGN IS BAD! If a client arbitrarily changes
    // the root by using the method, the size may no longer be correct/valid.
    // Instead, the precondition for this method is that
    // it should *ONLY* be used when rotating nodes in 
    // balanced binary search trees. We could instead change
    // our rotation code to not need this setRoot method, but that
    // makes the rotation code messier. For the purpose of this lab,
    // we will sacrifice a stronger design for cleaner/less code.
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
}

