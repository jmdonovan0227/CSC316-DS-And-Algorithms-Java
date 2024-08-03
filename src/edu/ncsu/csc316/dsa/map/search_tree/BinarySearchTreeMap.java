package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.AbstractOrderedMap;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree;

/**
 * The BinarySearchTreeMap is implemented as a linked data structure to support
 * efficient Tree and Map abstract data type behaviors.
 * 
 * Because of single-inheritance, we can extend only a single superclass: {$link
 * AbstractOrderedMap}. To overcome the limitation of single-inheritance, we
 * create a {@link BalanceableBinaryTree} class to handle rotation and relinking
 * logic. This allows us to adapt our implementation to delegate to the
 * {@link BalanceableBinaryTree} instead of extending {@link LinkedBinaryTree}.
 * 
 * BinarySearchTreeMap uses sentinel leaves. Every leaf node should have 2
 * sentinel children.
 * 
 * The BinarySearchTreeMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the binary search tree
 * @param <V> the type of values associated with keys in the binary search tree
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /** A balanceable binary tree */
    private BalanceableBinaryTree<K, V> tree;

    /**
     * Constructs a new binary search tree map that uses natural ordering of keys
     * when performing comparisons
     */
    public BinarySearchTreeMap() {
        this(null);
    }

    /**
     * Constructs a new binary search tree map that uses a provided
     * {@link Comparator} when performing comparisons of keys within the tree
     * @param compare a passed Comparator
     */
    public BinarySearchTreeMap(Comparator<K> compare) {
        super(compare);
        tree = new BalanceableBinaryTree<K, V>();
        tree.addRoot(null);
    }
    
    /**
     * Gets actual size of list because list also adds sentinel nodes
     * @return Integer the actual size of list
     */
    @Override
    public int size() {
        // Our search trees will all use dummy/sentinel leaf nodes,
        // so the actual number of elements in the tree will be (size-1)/2
        return (tree.size() - 1) / 2;
    }

    /**
     * To preserve the property of having all sentinel leaves, expandLeaf converts a
     * sentinel leaf into a position with an entry, then adds 2 new sentinel
     * children to the position
     * 
     * @param p     the position in the tree to update to store the provided entry
     * @param entry the entry to store in the provided position of the tree
     */
    private void expandLeaf(Position<Entry<K, V>> p, Entry<K, V> entry) {
        // This method is used to add dummy/sentinel left and right children as leaves

        // initially, p is a dummy/sentinel node,
        // so replace the null entry with the new actual entry
        tree.set(p, entry);

        // Then add new dummy/sentinel children
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    /**
     * Performs a traversal down a single path of the tree to locate and return the
     * position with the provided key. If no position in the tree contains the
     * provided key, then return a reference to the sentinel node where the downward
     * path traversal stopped
     * 
     * @param p   the position that represents the root of the subtree being
     *            searched
     * @param key the target key to locate within the subtree
     * @return the position that contains the provided key, or the target key is not
     *         contained within the tree then return the sentinel position at which
     *         the search terminated
     */
    private Position<Entry<K, V>> lookUp(Position<Entry<K, V>> p, K key) {
        // This helper method traces a path down the tree to locate the position
        // that contains an entry with the given key.
        // Think of "lookUp" as returning the last position visited when tracing
        // a path down the tree to find the given key

        // If we have reached a dummy/sentinel node (a leaf), return that sentinel node
        if (isLeaf(p)) {
            return p;
        }
        int comp = compare(key, p.getElement().getKey());
        if (comp == 0) {
            // Return the position that contains the entry with the key
            return p;
        } else if (comp < 0) {
            return lookUp(left(p), key);
        } else {
            return lookUp(right(p), key);
        }
    }
    
    /**
     * Gets the value of the node with the passed key
     * @param key the passed key
     * @return V the value of the node with the passed key
     */
    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(tree.root(), key);
        // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
        actionOnAccess(p);
        if (isLeaf(p)) {
            return null;
        }
        return p.getElement().getValue();
    }
    
    /**
     * Adds new entry to BinarySearchTreeMap, if the entry already exists update the value and return
     * the changed value
     * @param key the passed key that can be added to map
     * @param value the passed value that can be added to map
     * @return V the value that was added to map, returns the updated value if entry already exists
     */
    @Override
    public V put(K key, V value) {
        // Create the new map entry
        Entry<K, V> newEntry = new MapEntry<K, V>(key, value);

        // Get the last node visited when looking for the key
        Position<Entry<K, V>> p = lookUp(root(), key);

        // If the last node visited is a dummy/sentinel node
        if (isLeaf(p)) {
            expandLeaf(p, newEntry);
            // actionOnInsert is a "hook" for our AVL, Splay, and Red-Black Trees to use
            actionOnInsert(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            set(p, newEntry);
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
            actionOnAccess(p);
            return original;
        }
    }
    
    /**
     * Removes node from the map with the passed key
     * @param key the passed key
     * @return V the value removed or null if the value does not exist
     */
    @Override
    public V remove(K key) {
        // Get the last node visited when looking for the key
        Position<Entry<K, V>> p = lookUp(root(), key);

        // If p is a dummy/sentinel node
        if (isLeaf(p)) {
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
            actionOnAccess(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            // If the node has two children (that are not dummy/sentinel nodes)
            if (isInternal(left(p)) && isInternal(right(p))) {
                // Replace with the inorder successor
                Position<Entry<K, V>> replacement = treeMin(right(p));
                set(p, replacement.getElement());
                // Move the reference p to the replacement node in the right subtree
                p = replacement;
            }
            // Get the dummy/sentinel node (in case the node has an actual entry as a
            // child)...
            Position<Entry<K, V>> leaf = isLeaf(left(p)) ? left(p) : right(p);
            // ... then get its sibling (will be another sentinel or an actual entry node)
            Position<Entry<K, V>> sib = sibling(leaf);
            // Remove the leaf NODE (this is your LinkedBinaryTree remove method)
            remove(leaf);
            // Remove the NODE (this is your LinkedBinaryTree remove method)
            // which will "promote" the sib node to replace p
            remove(p);
            // actionOnDelete is a "hook" for our AVL, Splay, and Red-Black Trees to use
            actionOnDelete(sib);
            return original;
        }
    }

    /**
     * Locates and returns the position in the tree that stores the inorder
     * successor of the key in p. In other words, find the position that contains
     * the minimum key in the subtree rooted at p.
     * 
     * @param p the position that represents the root of the subtree being searched
     * @return the position in the tree that stores the inorder successor of the key
     *         in p
     */
    private Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> current = p;
        while (isInternal(current)) {
            current = left(current);
        }
        return parent(current);
    }
    
    /**
     * An iterable set of entries from the map
     * @return collection an iterable collection of entries within map
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for (Position<Entry<K, V>> n : tree.inOrder()) {
            collection.add(n.getElement());
        }
        return collection;
    }
    
    /**
     * Returns map as a String representation
     * @return String the String representation of map
     */
    @Override
    public String toString() {
        return tree.toString();
    }

    /**
     * A method hook that is executed whenever a tree position is accessed
     * 
     * @param p the position p that should be acted upon
     */
    protected void actionOnAccess(Position<Entry<K, V>> p) {
        // Do nothing for BST
    }

    /**
     * A method hook that is executed whenever a tree position is inserted into the
     * tree
     * 
     * @param p the position p that should be acted upon
     */
    protected void actionOnInsert(Position<Entry<K, V>> p) {
        // Do nothing for BST
    }

    /**
     * A method hook that is executed whenever a tree position is removed from the
     * tree
     * 
     * @param p the position p that should be acted upon
     */
    protected void actionOnDelete(Position<Entry<K, V>> p) {
        // Do nothing for BST
    }

    /**
     * The BalanceableBinaryTree is implemented as a linked data structure to
     * support efficient Tree abstract data type behaviors.
     * 
     * We create a {@link BalanceableBinaryTree} class to handle rotation and
     * relinking logic for binary trees. All behaviors delegate to
     * {@link LinkedBinaryTree}.
     * 
     * The BalanceableBinaryTree class is based on the implementation developed for
     * use with the textbook:
     *
     * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
     * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
     * 
     * @author Dr. King
     *
     * @param <K> the type of keys stored in the binary tree
     * @param <V> the type of values associated with keys in the binary tree
     */
    protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

        /**
         * Relink two positions to create a parent-child relationship
         * 
         * @param parent        the position that will become the parent of the child
         * @param child         the position that will be come a child of the parent
         * @param makeLeftChild indicates whether the child should be a left child
         *                      (true) or not (false)
         * Used Data Structures and Algorithms Course Book pg 478 Chapter 11 for reference in this method
         */
        private void relink(BinaryTreeNode<Entry<K, V>> parent, BinaryTreeNode<Entry<K, V>> child,
                boolean makeLeftChild) {
            child.setParent(parent);
            if(makeLeftChild) {
            	parent.setLeft(child);
            }
            
            else {
            	parent.setRight(child);
            }
        }

        /**
         * Performs a single rotation of a position, p, around it's parent. If
         * necessary, the grandparent must be updated to now refer to p as its child; p
         * must be updated to indicate its parent is now its child
         * Used Data Structures and Algorithms Course Book pg 478 Chapter 11 for reference in this method
         * @param p the position to rotate around its parent
         */
        public void rotate(Position<Entry<K, V>> p) {
            BinaryTreeNode<Entry<K, V>> node = validate(p);
            BinaryTreeNode<Entry<K, V>> parent = node.getParent();
            BinaryTreeNode<Entry<K, V>> grandparent = parent.getParent();
            
            if(grandparent == null) {
            	setRoot(node);
            	node.setParent(null);
            }
            
            else {
            	if(parent == grandparent.getLeft()) {
            		relink(grandparent, node, true);
            	}
            	
            	else {
            		relink(grandparent, node, false);
            	}
            }
            
            if(node == parent.getLeft()) {
            	relink(parent, node.getRight(), true);
            	relink(node, parent, false);
            }
            
            else {
            	relink(parent, node.getLeft(), false);
            	relink(node, parent, true);
            }
        }

        /**
         * Performs a trinode restructuring and returns the position at its final,
         * rotated position.
         * Used Data Structures and Algorithms Course Book pg 478 Chapter 11 for reference in this method
         * @param x the position that represents x in a trinode restructuring of x, its
         *          parent y, and its grandparent z
         * @return the position at its final, rotated position
         */
        public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        	BinaryTreeNode<Entry<K, V>> node = validate(x);
            BinaryTreeNode<Entry<K, V>> parent = node.getParent();
            BinaryTreeNode<Entry<K, V>> grandparent = parent.getParent();
            
            if(node == right(parent) && parent == right(grandparent) || node == left(parent) && parent == left(grandparent)) {
            	rotate(parent);
            	return parent;
            }
            
            else {
            	rotate(node);
            	rotate(node);
            	return node;
            }
        }
        
        /**
         * Creates a node with passed element and parent, left, and right child nodes
         * @param element the passed entry
         * @param parent the parent node
         * @param left the left child node
         * @param right the right child node
         * @return newNode the newly created node
         */
        @Override
        protected BinaryTreeNode<Entry<K, V>> createNode(Entry<K, V> element, BinaryTreeNode<Entry<K, V>> parent,
                BinaryTreeNode<Entry<K, V>> left, BinaryTreeNode<Entry<K, V>> right) {
            BSTNode<Entry<K, V>> newNode = new BSTNode<Entry<K, V>>(element);
            newNode.setParent(parent);
            newNode.setLeft(left);
            newNode.setRight(right);
            newNode.setProperty(0);
            return newNode;
        }

        /**
         * A BSTNode is a binary search tree node. A binary search tree node extends
         * {@link BinaryTreeNode} to saved an additional piece of information that is
         * necessary for some search trees: In an AVL tree, this extra information
         * represents the height of the node; In a red-black tree, this extra
         * information represents the color of the node
         * 
         * @author Dr. King
         *
         * @param <E> the element stored in the binary search tree node
         */
        protected static class BSTNode<E> extends BinaryTreeNode<E> {
        	/** The property information, height, color, etc */
            private int property;

            /**
             * Constructs a binary search tree node with the provided element
             * 
             * @param element the element to store in the binary search tree node
             */
            public BSTNode(E element) {
                super(element);
                setProperty(0);
            }

            /**
             * Sets the property of the binary search tree node.
             * 
             * @param property the property information (height, color, etc.) of the binary
             *                 search tree node
             */
            public void setProperty(int property) {
                this.property = property;
            }

            /**
             * Returns the propert of the binary search tree node.
             * 
             * @return the property information (height, color, etc.) of the binary search
             *         tree node
             */
            public int getProperty() {
                return property;
            }
        }

        /**
         * Returns the property of a given position. The position must first be cast to
         * a {@link BSTNode}.
         * 
         * @param p the position for which to retrieve the property information
         * @return the property information for the provided position in the tree
         */
        public int getProperty(Position<Entry<K, V>> p) {
            if (p == null) {
                return 0;
            }
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) p;
            return node.getProperty();
        }

        /**
         * Sets the property of a given position. The position must first be cast to a
         * {@link BSTNode}.
         * 
         * @param p     the position for which to set the property information
         * @param value the value to set for the property information for the provided
         *              position in the tree
         */
        public void setProperty(Position<Entry<K, V>> p, int value) {
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) (p);
            node.setProperty(value);
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // All the methods below delegate to the BalanceableBinaryTree class, which
    // extends your linked binary tree implementation
    /////////////////////////////////////////////////////////////////////////////
    
    /**
     * Gets the root node in map
     * @return root the root node in map
     */
    public Position<Entry<K, V>> root() {
    	Position<Entry<K, V>> root = tree.root();
        return root;
    }
    
    /**
     * Gets the parent node in map
     * @param p the position of a node within map
     * @return parent the parent node in map
     */
    public Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
    	Position<Entry<K, V>> parent = tree.parent(p);
        return parent;
    }
    
    /**
     * Checks if a node at a passed position is an internal node
     * @param p the position of node within map
     * @return True if the node is internal, false otherwise
     */
    public boolean isInternal(Position<Entry<K, V>> p) {
        return tree.isInternal(p);
    }
    
    /**
     * Sets the node at a passed position with passed entry
     * @param p the passed position of a node within map
     * @param entry an entry that will become the new entry for a node within map at passed position p
     * @return Entry a passed entry
     */
    public Entry<K, V> set(Position<Entry<K, V>> p, Entry<K, V> entry) {
        return tree.set(p, entry);
    }
    
    /**
     * Checks if passed node at a position p is a leaf node
     * @param p the passed position of a node within map
     * @return True if the passed position of a node within map is a leaf node, false otherwise
     */
    public boolean isLeaf(Position<Entry<K, V>> p) {
        return tree.isLeaf(p);
    }
    
    /**
     * Checks if a passed node at position p is a root node
     * @param p the passed position of a node within map
     * @return True if the node at passed position p is the root node, false otherwise
     */
    public boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }
    
    /**
     * Gets the left child of the node at position p within map
     * @param p the passed position of a node within map
     * @return left the left child of the passed position p (node)
     */
    public Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
    	Position<Entry<K, V>> left = tree.left(p);
        return left;
    }
    
    /**
     * Gets the right child of the node at the passed position p
     * @param p the passed position of a node within map
     * @return right the right child of the node at position p
     */
    public Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
    	Position<Entry<K, V>> right = tree.right(p);
        return right;
    }
    
    /**
     * Gets the sibling node of the node at position p within map
     * @param p the position of a node within map
     * @return sibling the sibling node of the node at position p
     */
    public Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
    	Position<Entry<K, V>> sibling = tree.sibling(p);
        return sibling;
    }

    /**
     * Rotates the given position up 1 level in the tree
     * {@see BalanceableBinaryTree#rotate}
     * 
     * @param p the position to rotate
     */
    protected void rotate(Position<Entry<K, V>> p) {
        tree.rotate(p);
    }

    /**
     * Performs a trinode restructuring where the given position, p, is the
     * grandchild {@see BalanceableBinaryTree#restructure}
     * 
     * @param x the position at which to begin the trinode restructuring rotations
     * @return the position at the root of the balanced subtree
     */
    protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        return tree.restructure(x);
    }

    /**
     * Returns the property of the given position
     * {@see BalanceableBinaryTree#getProperty}
     * 
     * @param p the position for which to retrieve the property
     * @return the property of the given position
     */
    protected int getProperty(Position<Entry<K, V>> p) {
        return tree.getProperty(p);
    }

    /**
     * Sets the property of the given position to have the given value
     * {@see BalanceableBinaryTree#setProperty}
     * 
     * @param p     the position for which to set the property
     * @param value the property value to be set
     */
    protected void setProperty(Position<Entry<K, V>> p, int value) {
        tree.setProperty(p, value);
    }

    protected Entry<K, V> remove(Position<Entry<K, V>> p) {
        return tree.remove(p);
    }
}
