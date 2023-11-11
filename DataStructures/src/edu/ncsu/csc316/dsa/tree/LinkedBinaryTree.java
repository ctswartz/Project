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
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Courtney T Swartz (ctswartz)
 *
 * @param <E> the type of elements stored in the binary tree
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    /** The root. */
    private BinaryTreeNode<E> root;
    
    /** The size. */
    private int size;

    /**
     * Create a new empty binary tree.
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
//        if (!(p instanceof BinaryTreeNode)) {
//            throw new IllegalArgumentException("Position is not a valid linked binary tree node");
//        }
        return (BinaryTreeNode<E>) p;
    }

    /**
     * A BinaryTreeNode stores an element and references the node's parent, left
     * child, and right child.
     *
     * @author Dr. King
     * @param <E> the type of element stored in the node
     */
    public static class BinaryTreeNode<E> extends AbstractTreeNode<E> {
        
        /** The parent. */
        private BinaryTreeNode<E> parent;
        
        /** The left. */
        private BinaryTreeNode<E> left;
        
        /** The right. */
        private BinaryTreeNode<E> right;

        /**
         * Constructs a new BinaryTreeNode with the provided element.
         *
         * @param element the element to store in the node
         */
        public BinaryTreeNode(E element) {
            this(element, null);
        }

        /**
         * Constructs a new BinaryTreeNode with the provided element and provided parent
         * reference.
         *
         * @param element the element to store in the node
         * @param parent  the parent of the newly created node
         */
        public BinaryTreeNode(E element, BinaryTreeNode<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Returns the left child of the current node.
         *
         * @return the left child of the current node
         */
        public BinaryTreeNode<E> getLeft() {
            return left;
        }

        /**
         * Returns the right child of the current node.
         *
         * @return the right child of the current node
         */
        public BinaryTreeNode<E> getRight() {
            return right;
        }

        /**
         * Sets the left child of the current node.
         *
         * @param left the node to set as the left child of the current node
         */
        public void setLeft(BinaryTreeNode<E> left) {
            this.left = left;
        }

        /**
         * Sets the right child of the current node.
         *
         * @param right the node to set as the right child of the current node
         */
        public void setRight(BinaryTreeNode<E> right) {
            this.right = right;
        }

        /**
         * Returns the parent of the current node.
         *
         * @return the parent of the current node
         */
        public BinaryTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the parent of the current node.
         *
         * @param parent the node to set as the parent of the current node
         */
        public void setParent(BinaryTreeNode<E> parent) {
            this.parent = parent;
        }
    }

    /**
     * Retrieves the left child at the position.
     *
     * @param p the position to get the left child
     * @return the left child of the position
     */
    @Override
    public Position<E> left(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Retrieves the right child of the position.
     *
     * @param p the position to get the right child
     * @return the right child of the position
     */
    @Override
    public Position<E> right(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getRight();
    }

    /**
     * Adds the left child at the given position with the value given.
     *
     * @param p the position to add the left child to
     * @param value the value to store as the new left child
     * @return the position of the newly added left child
     * @throws IllegalArgumentException is the node already has a left child
     */
    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        BinaryTreeNode<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        // Use the createNode method to create a new node
        BinaryTreeNode<E> createdNode = createNode(value, node, null, null);
        node.setLeft(createdNode);
        
        size++;
        return createdNode;
    }

    /**
     * Adds the right child at the given position with the value given.
     *
     * @param p the position to add the right child to
     * @param value the value to store as the new right child
     * @return the position of the newly added right child
     * @throws IllegalArgumentException is the node already has a right child
     */
    @Override
    public Position<E> addRight(Position<E> p, E value) {
        BinaryTreeNode<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        // Use the createNode method to create a new node
        BinaryTreeNode<E> createdNode = createNode(value, node, null, null);
        node.setRight(createdNode);
        
        size++;
        return createdNode;
        
    }

    /**
     * Return the root of the tree.
     *
     * @return the position
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Return the parent.
     *
     * @param p the position
     * @return the position
     */
    @Override
    public Position<E> parent(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Adds the root of the tree.
     *
     * @param value the value
     * @return the position
     * @throws IllegalArgumentException if there is already a root
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
     * Removes the node at the position.
     *
     * @param p the position of the node being removed
     * @return the element that was removed
     * throws IllegalArgumentException if the node has two children
     */
    @Override
    public E remove(Position<E> p) {
        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("The node has two children");
        }
        BinaryTreeNode<E> node = validate(p);
        
        // Store removed element
        E element = node.getElement();

        // Find the child and replace the node
        BinaryTreeNode<E> replace;
        if (node.getLeft() != null) {
        	replace = node.getLeft();
        } else {
        	replace = node.getRight();
        }

        if (replace != null) {
            // Update the parent of the replacement node
        	replace.setParent(node.getParent());

            // Check if the node is the root
            if (node == root) {
            	// replace the root
                root = replace;
            } else {
                BinaryTreeNode<E> parent = node.getParent();
                if (node == parent.getLeft()) {
                    parent.setLeft(replace);
                } else {
                    parent.setRight(replace);
                }
            }
        } else if (node == root) {
            // If the tree only has one node, remove it
            root = null;
        } else {
            BinaryTreeNode<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }

        size--;
        return element;
    }

    /**
     * Returns the size.
     *
     * @return the size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Creates the node.
     *
     * @param e the element to store
     * @param parent the parent of the new node
     * @param left the left child of the node
     * @param right the right child of the node
     * @return the binary tree node
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
    /**
     * Sets the root.
     *
     * @param p the position
     * @return the position
     */
    // we will sacrifice a stronger design for cleaner/less code.
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
}